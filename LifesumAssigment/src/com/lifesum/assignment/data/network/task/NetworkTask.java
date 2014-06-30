package com.lifesum.assignment.data.network.task;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public abstract class NetworkTask implements Runnable {

	private static final String BASE_URL = "https://api.lifesum.com/v1/search/query";
	private static final String AUTH_TOKEN = "a794ecd348a3f71894426c65c37fea35da89a295bcbad687ca68a96fbfc7d371";
	private static final String ENCODING = "UTF-8";

	private static final int HTTP_CONNECTION_TIMEOUT = 15000;
	private static final int HTTP_SOCKET_TIMEOUT = 15000;

	protected HttpResponse mResponse;

	protected HttpClient mClient;

	public NetworkTask() {
		mClient = newClient();
	}

	private HttpClient newClient() {

		AbstractHttpClient client = new DefaultHttpClient();

		HttpParams params = client.getParams();
		HttpConnectionParams.setConnectionTimeout(params,
				HTTP_CONNECTION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(params, HTTP_SOCKET_TIMEOUT);
		client.setParams(params);

		return client;

	}

	protected URI createUri(List<NameValuePair> parameters)
			throws URISyntaxException {
		URI uri = new URI(BASE_URL + "?"
				+ URLEncodedUtils.format(parameters, ENCODING));
		return uri;
	}

	protected void setHeaders(HttpGet request) {
		request.setHeader("Content-type", "application/json");
		request.addHeader("Authorization", AUTH_TOKEN);
	}

	protected HttpResponse execute(HttpRequestBase request) throws IOException {
		return this.mClient.execute(request);
	}

	protected abstract List<NameValuePair> createParameters();

}
