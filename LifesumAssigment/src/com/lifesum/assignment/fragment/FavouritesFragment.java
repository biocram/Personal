package com.lifesum.assignment.fragment;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.lifesum.assignment.Bridge;
import com.lifesum.assignment.R;
import com.lifesum.assignment.adapter.FavouritesAdapter;
import com.lifesum.assignment.data.DataManager;
import com.lifesum.assignment.data.dto.BasePageBean;
import com.lifesum.assignment.data.listener.DataListener;

public class FavouritesFragment extends BaseFragment implements OnFavouriteListener {

	private static final String TAG = FavouritesFragment.class.getSimpleName();

	private ListView mListView;
	private FavouritesAdapter mAdapter;

	public static FavouritesFragment newInstance() {
		FavouritesFragment fragment = new FavouritesFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView()");
		View content = inflater.inflate(R.layout.fragment_search, container, false);
		mListView = (ListView) content.findViewById(R.id.listview_search);
		return content;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.d(TAG, "onActivityCreated()");
		super.onActivityCreated(savedInstanceState);

		mAdapter = new FavouritesAdapter(getActivity());
		mAdapter.setOnFavouriteListener(this);
		mListView.setAdapter(mAdapter);

	}

	@Override
	public void onFavouriteIconClicked(BasePageBean bean) {
		int deletedRows = DataManager.getInstance().removeFavourite(getActivity().getApplicationContext(), bean);
		if (deletedRows == 1) {
			Toast.makeText(getActivity(), "Item removed", Toast.LENGTH_SHORT).show();
		}
		refreshFavourites();
	}

	public void refreshFavourites() {
		DataManager.getInstance().getFavourites(getActivity().getApplicationContext(), new DataListener() {

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

}
