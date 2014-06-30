package com.lifesum.assignment;

import com.lifesum.assignment.fragment.BaseFragment;

public interface Bridge {
	
	void showProgressDialog();
	void hideProgressDialog();
	void addFragment(BaseFragment baseFragment);
	void removeFragment(BaseFragment baseFragment);

}
