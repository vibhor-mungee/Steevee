package com.example.webservices;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LoginRestWebserviceCI {

	public static String URL = "http://192.168.0.219/CodeIgniter/index.php/";
	static InputStream is = null;
	static String json = "";
	static JSONObject jObj = null;
	private static String KEY_SUCCESS = "success";

	public static boolean invokeLoginWS(String username, String password,
			String methName) {
		String url = URL + methName;
		Boolean loginResponse = false;
		try {

			HttpClient client = new DefaultHttpClient();
			url = url.replace(" ", "%20");
			// HttpGet httpGet = new HttpGet(url);
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("email", username));
			nameValuePairs.add(new BasicNameValuePair("password", password));
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
 			HttpResponse response = client.execute(httpPost);
			// loginResponse = Boolean.parseBoolean(response.toString());
			HttpEntity httpEntity = response.getEntity();
			is = httpEntity.getContent();

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
			Log.e("JSON", json);
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		try {
			jObj = new JSONObject(json);
			if (jObj.getString(KEY_SUCCESS) != null) {
				String res = jObj.getString(KEY_SUCCESS);
				if (Integer.parseInt(res) == 1) {
					loginResponse = true;
				} else {
					loginResponse = false;
				}
			}
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		return loginResponse;

	}

}
