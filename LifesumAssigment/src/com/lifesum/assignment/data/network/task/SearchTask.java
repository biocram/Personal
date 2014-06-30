package com.lifesum.assignment.data.network.task;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

import com.google.gson.Gson;
import com.lifesum.assignment.data.network.listener.NetworkServiceListener;
import com.lifesum.assignment.data.network.model.SearchResponse;
import com.lifesum.assignment.util.Utils;

public class SearchTask extends NetworkTask {

	private static final String TAG = SearchTask.class.getSimpleName();

	private final String mCategory;
	private final NetworkServiceListener mListener;

	public SearchTask(NetworkServiceListener listener, String category) {
		super();
		this.mListener = listener;
		this.mCategory = category;
	}

	@Override
	public void run() {
		Log.d(TAG, "run() with category: " + mCategory);

		try {
			List<NameValuePair> parameters = this.createParameters();
			URI uri = this.createUri(parameters);
			HttpGet httpGet = new HttpGet(uri);
			this.setHeaders(httpGet);

			HttpResponse response = this.execute(httpGet);
			final int statusCode = response.getStatusLine().getStatusCode();
			Log.d(TAG, "statusCode: " + statusCode);
			if (statusCode == HttpStatus.SC_OK) {
				
				
				
				Reader reader = new InputStreamReader(response.getEntity().getContent());
				
				//deserializing json stream
				Gson gson = new Gson();
				SearchResponse responseDeserialized = gson.fromJson(reader, SearchResponse.class);
				mListener.onSuccess(responseDeserialized.getItems());
			}else
			{
				mListener.onFailure();
			}
		} catch (Exception e) {
			Log.e(TAG, "", e);
			mListener.onFailure();
		}

	}

	@Override
	protected List<NameValuePair> createParameters() {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("type", "food"));
		parameters.add(new BasicNameValuePair("search", mCategory));
		return parameters;
	}

}