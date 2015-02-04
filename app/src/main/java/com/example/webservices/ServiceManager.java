package com.example.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ServiceManager extends AsyncTask<String, Void, Void> {

	private final HttpClient client = new DefaultHttpClient();
	private ProgressDialog dialog;
	private Context context;
	private String data;

	public ServiceManager(Context context) {
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = new ProgressDialog(context);
		dialog.setMessage("Please wait...");
		dialog.show();

		try {
			data += "&" + URLEncoder.encode("data", "UTF-8") + "=vibhor";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected Void doInBackground(String... urls) {
		BufferedReader reader = null;

		try {
			//Set url to send data
			URL url = new URL(urls[0]);
			
			//Send request
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
