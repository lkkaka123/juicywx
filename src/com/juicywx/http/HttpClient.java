package com.juicywx.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpClient {
	public static String httpGet (String url){
		URL getUrl;
		String lines = null;
		try {
			getUrl = new URL(url); 
			HttpURLConnection connection = (HttpURLConnection) getUrl
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			StringBuilder str = new StringBuilder();
			while ((lines = reader.readLine()) != null) {
				str.append(lines);
			}
			lines=str.toString();
			reader.close();
			connection.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
	}
	public static String httpPost(String url,String postData){
		URL getUrl;
		String lines = null;
		try {
			getUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			 connection.setInstanceFollowRedirects(false);
			 connection.setRequestProperty("Content-Type",
		                "application/json");
			 connection.setConnectTimeout(5000);
			connection.connect();
			DataOutputStream out = new DataOutputStream(connection
		                .getOutputStream());
			out.writeBytes(postData); 
	        out.flush();
	        out.close();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			StringBuilder str = new StringBuilder();
			while ((lines = reader.readLine()) != null) {
				str.append(lines);
			}
			lines=str.toString();
			reader.close();
			connection.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
	}
}
