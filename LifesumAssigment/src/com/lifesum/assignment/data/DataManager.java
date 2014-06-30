package com.lifesum.assignment.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;

import com.lifesum.assignment.R;
import com.lifesum.assignment.data.db.bean.FavouriteBean;
import com.lifesum.assignment.data.db.listener.DataBaseListener;
import com.lifesum.assignment.data.dto.BasePageBean;
import com.lifesum.assignment.data.listener.DataListener;
import com.lifesum.assignment.data.network.listener.NetworkServiceListener;
import com.lifesum.assignment.data.network.model.SearchResponse.WSItem;
import com.lifesum.assignment.util.Utils;

public class DataManager {

	private Handler mUIHandler;

	private Map<String, List<WSItem>> cache;

	private static DataManager _instance;

	private DataManager() {
		cache = new HashMap<String, List<WSItem>>();
		mUIHandler = new Handler();
	}

	public synchronized static DataManager getInstance() {
		if (_instance == null) {
			_instance = new DataManager();
		}
		return _instance;
	}
	
	//------------------------------------
	//
	// PUBLIC METHODS
	//
	//------------------------------------

	/**
	 * 
	 * Search a category from web service
	 * 
	 * @param category
	 * @param listener
	 */
	public void search(final String category, final DataListener listener) {
		// retrieve data if already in cache
		if (cache.containsKey(category))
			runOnUITHreadOnSuccess(listener,
					Utils.adaptWSItems2PageBean(cache.get(category)));
		else
			// if not in cache get items from web service
			getItemsFromWebService(category, listener);
	}

	/**
	 * Retrieve current favourites from db
	 * 
	 * @param context
	 * @param listener
	 */
	public void getFavourites(Context context, final DataListener listener) {
		DatabaseService.getInstance(context).getFavourites(
				new DataBaseListener<FavouriteBean>() {

					@Override
					public void onSuccess(final List<FavouriteBean> beans) {
						// send back response on UI thread
						runOnUITHreadOnSuccess(listener,
								Utils.adaptDatabaseBean2PageBean(beans));
					}

					@Override
					public void onFailure() {
						// send back response on UI thread
						runOnUiThreadOnFailure(listener,
								R.string.default_error_message);
					}
				});
	}

	/**
	 * 
	 * Add an item to the db
	 * 
	 * @param context
	 * @param category
	 * @param bean
	 */
	public void addToFavourite(Context context, String category,
			BasePageBean bean) {
		List<WSItem> cachedItems = cache.get(category);
		FavouriteBean favBean = new FavouriteBean();
		for (WSItem item : cachedItems) {
			if (item.getId().equals(bean.getId())) {
				favBean = Utils.adaptWSItem2FavouriteBean(item);
				break;
			}
		}
		DatabaseService.getInstance(context).setFavourites(favBean);
	}

	/**
	 * 
	 * Add a list of items to the db
	 * 
	 * @param context
	 * @param category
	 * @param pageBeans
	 */
	public void addToFavourites(Context context, String category,
			List<BasePageBean> pageBeans) {
		List<WSItem> cachedItems = cache.get(category);
		List<FavouriteBean> favBeans = new ArrayList<FavouriteBean>();
		for (BasePageBean bean : pageBeans) {
			for (WSItem item : cachedItems) {
				if (item.getId().equals(bean.getId())) {
					favBeans.add(Utils.adaptWSItem2FavouriteBean(item));
					break;
				}
			}

		}
		DatabaseService.getInstance(context).setFavourites(favBeans);
	}
	
	/**
	 * 
	 * Remove a favourite
	 * 
	 * @param context
	 * @param bean
	 */
	public int removeFavourite(Context context, BasePageBean bean) {
		return DatabaseService.getInstance(context).removeFavourites(new String[]{bean.getId()});
	}

	/**
	 * 
	 * Remove a list of favourites
	 * 
	 * @param context
	 * @param beans
	 */
	public int removeFavourites(Context context, List<BasePageBean> beans) {
		String[] ids = new String[beans.size()];
		int count = 0;
		for (BasePageBean bean : beans) {
			ids[count++] = bean.getId();
		}
		return DatabaseService.getInstance(context).removeFavourites(ids);
	}
	
	//------------------------------------
	//
	// PRIVATE METHODS
	//
	//------------------------------------
	
	private void getItemsFromWebService(final String category,
			final DataListener listener) {
		NetworkService.getInstance().search(category,
				new NetworkServiceListener() {

					@Override
					public void onSuccess(final List<WSItem> items) {
						cache.put(category, items);
						// send back response on UI thread
						runOnUITHreadOnSuccess(listener, Utils.adaptWSItems2PageBean(items));
					}

					@Override
					public void onFailure() {
						// send back response on UI thread
						runOnUiThreadOnFailure(listener, R.string.default_error_message);
					}
				});

	}

	/**
	 * 
	 * Run on UI Thread onSuccess() method from listener
	 * 
	 * @param listener
	 * @param beans
	 */
	private void runOnUITHreadOnSuccess(final DataListener listener,
			final List<BasePageBean> beans) {
		mUIHandler.post(new Runnable() {

			@Override
			public void run() {
				listener.onSuccess(beans);
			}
		});
	}

	/**
	 * 
	 * Run on UI Thread onFailure() method from listener
	 * 
	 * @param listener
	 * @param beans
	 */
	private void runOnUiThreadOnFailure(final DataListener listener,
			final int errorResource) {
		mUIHandler.post(new Runnable() {

			@Override
			public void run() {
				listener.onFailure(errorResource);
			}
		});
	}

}
