package com.lifesum.assignment.data.listener;

import java.util.List;

import com.lifesum.assignment.data.dto.BasePageBean;


public interface DataListener {
	
	public void onSuccess(List<BasePageBean> beans);
	
	public void onFailure(int errorResource);

}
