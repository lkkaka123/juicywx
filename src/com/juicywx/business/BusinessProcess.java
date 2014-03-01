package com.juicywx.business;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.juicywx.bean.Message;

import sun.net.www.http.HttpClient;

public class BusinessProcess {
	private static String appID = "wx96022e4689c2c1ea";
	private static String appSecret = "60a2e77f637d1699a29357a4149f8b6f";
	public final static String URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
			+ appID + "&secret=" + appSecret;

	public String getTextOrder(String input) {
		return "";
	}

	public String getEventOrder(Message msg) {
		
		if (msg.getEvent().equals("subscribe")) {
			return "¶©ÔÄ³É¹¦";
		} else {
			return msg.getEventKey();
		}

	}

	public String getAccessToken(String url) {

		URL getUrl;
		String lines = null;
		try {
			getUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) getUrl
					.openConnection();
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
		return getATValue(lines);
	}
	public static String getATValue(String result){
//		result = result.substring(1, result.length()-1);
		String[] array = result.split("\"");
//		array = array[0].split(":");
		
		return array[3];
	}
	public  String getQRCodeTicket(String url) {
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
			connection.connect();
			DataOutputStream out = new DataOutputStream(connection
		                .getOutputStream());
			String outStr = ticketInfo("1800", QR_SCENE_TEMP, "123");
			out.writeBytes(outStr); 
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
		return getATValue(lines);

	}
	public final static String QR_SCENE_TEMP="QR_SCENE";
	public final static String QR_SCENE_PER="QR_LIMIT_SCENE";
	public static String ticketInfo(String expire,String actionName,String id){
		String tmp ="{\"expire_seconds\": "+expire+", \"action_name\": \""+actionName+"\", \"action_info\": {\"scene\": {\"scene_id\": "+id+"}}}";
		return tmp;
	}
}

