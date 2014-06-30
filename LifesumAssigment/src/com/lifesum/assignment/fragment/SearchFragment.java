package com.lifesum.assignment.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.lifesum.assignment.R;
import com.lifesum.assignment.adapter.SearchAdapter;
import com.lifesum.assignment.data.DataManager;
import com.lifesum.assignment.data.dto.BasePageBean;
import com.lifesum.assignment.data.listener.DataListener;

public class SearchFragment extends BaseFragment implements OnFavouriteListener {

	private static final String TAG = SearchFragment.class.getSimpleName();

	private ListView mListView;
	private SearchAdapter mAdapter;

	private ImageView mFavIcon;
	private Animation mAnim;

	private String mCurrentCategory;
	private List<String> mFavIds;

	public static SearchFragment newInstance() {
		SearchFragment fragment = new SearchFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView()");
		View content = inflater.inflate(R.layout.fragment_search, container, false);
		mListView = (ListView) content.findViewById(R.id.listview_search);
		mFavIcon = (ImageView) content.findViewById(R.id.imageview_add_to_fav);
		return content;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.d(TAG, "onActivityCreated()");
		super.onActivityCreated(savedInstanceState);

		mAdapter = new SearchAdapter(getActivity());
		mAdapter.setOnFavouriteListener(this);
		mListView.setAdapter(mAdapter);

		refreshFavourites();

		mAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.favourite_icon_anim);
		mAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				mFavIcon.setVisibility(View.INVISIBLE);
			}
		});

	}
	
	@Override
	public void onResume() {
		Log.d(TAG, "onResume()");
		super.onResume();
	}

	@Override
	public void onFavouriteIconClicked(BasePageBean bean) {
		if (mFavIds.contains(bean.getId())) {
			// item is already in favourites
			int itemRemoved = DataManager.getInstance().removeFavourite(getActivity(), bean);
			if (itemRemoved == 1) {
				mFavIds.remove(bean.getId());
			}

		} else {
			// add to favourites
			DataManager.getInstance().addToFavourite(getActivity().getApplicationContext(), mCurrentCategory, bean);
			mFavIds.add(bean.getId());
			mFavIcon.startAnimation(mAnim);
		}

	}

	public void doSearch(String query) {
		mCurrentCategory = query;
		mBridge.showProgressDialog();
		// get items from wfor category
		DataManager.getInstance().search(query, new DataListener() {

			@Override
			public void onSuccess(List<BasePageBean> beans) {
				mAdapter.updateItem(beans);
				mBridge.hideProgressDialog();
			}

			@Override
			public void onFailure(int errorMessage) {
				mBridge.hideProgressDialog();
				Toast.makeText(getActivity(), getResources().getString(errorMessage), Toast.LENGTH_LONG).show();
			}
		});
	}

	public void refreshFavourites() {
		DataManager.getInstance().getFavourites(getActivity(), new DataListener() {

			@Override
			public void onSuccess(List<BasePageBean> beans) {
				mFavIds = new ArrayList<String>(beans.size());
				for (BasePageBean bean : beans) {
					mFavIds.add(bean.getId());
				}
				mAdapter.setFavoritesIds(mFavIds);
			}

			@Override
			public void onFailure(int errorResource) {

			}
		});
	}

}
