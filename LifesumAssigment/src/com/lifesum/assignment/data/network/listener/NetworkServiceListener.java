package com.lifesum.assignment.data.network.listener;

import java.util.List;

import com.lifesum.assignment.data.network.model.SearchResponse.WSItem;


public interface NetworkServiceListener {
	
	public void onSuccess(List<WSItem> items);
	
	public void onFailure();

}
