package com.lifesum.assignment.data.db.listener;

import java.util.List;

import com.lifesum.assignment.data.db.bean.DatabaseBean;


public interface DataBaseListener<T extends DatabaseBean> {
	
	public void onSuccess(List<T> items);
	
	public void onFailure();

}
