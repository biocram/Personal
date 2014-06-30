package com.lifesum.assignment.data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lifesum.assignment.data.network.listener.NetworkServiceListener;
import com.lifesum.assignment.data.network.task.NetworkTask;
import com.lifesum.assignment.data.network.task.SearchTask;

public class NetworkService {
	
	private static final String TAG = NetworkService.class.getSimpleName();
	
	private static final int THREAD_POOL_SIZE = 4;
	
	private ExecutorService mExecutor;
	
	private static NetworkService _instance;
	
	private NetworkService(){}
	
	static synchronized NetworkService getInstance() {
		if (_instance == null) {
			_instance = new NetworkService(THREAD_POOL_SIZE);
		}
		return _instance;
	}
	
	private NetworkService(int nPool) {
		this.initExecutors(nPool);
	}

	private void initExecutors(int nPool) {
		this.mExecutor = Executors.newFixedThreadPool(nPool);
	}
	
	void search(String category, NetworkServiceListener listener)
	{
		NetworkTask task = new SearchTask(listener, category);
		mExecutor.execute(task);
	}
	
	

}
