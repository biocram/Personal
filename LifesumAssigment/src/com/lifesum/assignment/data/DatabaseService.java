package com.lifesum.assignment.data;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lifesum.assignment.data.db.DbHelper;
import com.lifesum.assignment.data.db.bean.FavouriteBean;
import com.lifesum.assignment.data.db.dao.FavouriteDAO.FavouriteEntry;
import com.lifesum.assignment.data.db.listener.DataBaseListener;

public class DatabaseService {
	
	private static final String TAG = DatabaseService.class.getSimpleName();
	
	private static final int THREAD_POOL_SIZE = 1;
	
	private ExecutorService mExecutor;
	
	private static DatabaseService _instance;
	
	private DbHelper dbHelper;
	
	private DatabaseService(Context context,int nPool)
	{
		dbHelper = new DbHelper(context);
		this.initExecutors(nPool);
	}
	
	static synchronized DatabaseService getInstance(Context context) {
		if (_instance == null) {
			_instance = new DatabaseService(context,THREAD_POOL_SIZE);
		}
		return _instance;
	}
	
	private void initExecutors(int nPool) {
		this.mExecutor = Executors.newFixedThreadPool(nPool);
	}
	
	void getFavourites(final DataBaseListener<FavouriteBean> listener)
	{
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				SQLiteDatabase db = dbHelper.getReadableDatabase();
				final List<FavouriteBean> items = dbHelper.getFavoriteDao().findAll(db);
				listener.onSuccess(items);
			}
		};
		mExecutor.execute(runnable);
	}
	
	void setFavourites(final FavouriteBean item)
	{
		Runnable task = new Runnable() {
			
			@Override
			public void run() {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				try {
					Long id = dbHelper.getFavoriteDao().insert(db, item);
					Log.d(TAG, "New inserted row id: "+id);
				} catch (SQLException e) {
					Log.e(TAG, "",e);
				}finally
				{
					db.close();
				}
				
			}
		};
		mExecutor.execute(task);
	}
	
	void setFavourites(final List<FavouriteBean> items)
	{
		Runnable task = new Runnable() {
			
			@Override
			public void run() {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				try {
					List<Long> ids = dbHelper.getFavoriteDao().insert(db, items);
				} catch (SQLException e) {
					Log.e(TAG, "",e);
				}finally
				{
					db.close();
				}
				
			}
		};
		mExecutor.execute(task);
	}
	
	int removeFavourites(String[] ids)
	{
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		return dbHelper.getFavoriteDao().removeBy(db, FavouriteEntry.FAVOURITE_ID, ids);
	}
	
}
