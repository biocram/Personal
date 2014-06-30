package com.lifesum.assignment.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.lifesum.assignment.Bridge;

public class BaseFragment extends Fragment {
	
	private static final String TAG = BaseFragment.class.getSimpleName();
	
	protected Bridge mBridge;
	
	@Override
	public void onAttach(Activity activity) {
		Log.d(TAG, "onAttach()");
		super.onAttach(activity);
		mBridge = (Bridge) activity;
		mBridge.addFragment(this);
	}
	
	@Override
	public void onDetach() {
		Log.d(TAG, "onDetach()");
		super.onDetach();
		mBridge.removeFragment(this);
		mBridge = null;
	}

}
