package com.lifesum.assignment;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.lifesum.assignment.fragment.ProgressDialogFragment;

public abstract class AbstractActivity extends ActionBarActivity {

	private static final String TAG = AbstractActivity.class.getSimpleName();

	private static final String PROGRESS_TAG = "progress_dialog";

	protected DialogFragment mProgressDialog;

	public void showProgressDialog() {
		Log.d(TAG, "showProgressDialog()");
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		mProgressDialog = ProgressDialogFragment.newInstance();
		mProgressDialog.show(ft, PROGRESS_TAG);
	}

	public void hideProgressDialog() {
		Log.d(TAG, "hideProgressDialog()");
		DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager()
				.findFragmentByTag(PROGRESS_TAG);
		if (dialogFragment != null)
			dialogFragment.dismissAllowingStateLoss();
		else
			Log.d(TAG, "Unable to close dialog fragment");
	}

}
