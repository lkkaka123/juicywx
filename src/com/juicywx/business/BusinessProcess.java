package com.juicywx.business;

import com.juicywx.bean.Message;
import com.juicywx.http.HttpClient;

public class BusinessProcess {
	//private Authorized info of each WX-connected system
	private static String appID = "wx96022e4689c2c1ea";
	private static String appSecret = "60a2e77f637d1699a29357a4149f8b6f";
	//TOKEN
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
	/**
	 * any request to Professional API provided by WeiXin will ask for the TOKEN
	 * @return TOKEN
	 */
	public String getAccessToken(String url) {
		String lines = HttpClient.httpGet(url);
		return getATValue(lines);
	}
	/**
	 * {"key":"value","key","value"}
	 * get fist value
	 */
	public static String getATValue(String result){
//		result = result.substring(1, result.length()-1);
		String[] array = result.split("\"");
//		array = array[0].split(":");
		return array[3];
	}
	/**
	 * 	WeiXin will give the ticket for downloading the QR
	 * @return ticket
	 */
	public  String getQRCodeTicket(String url) {
		String lines = HttpClient.httpPost(url, ticketInfo("1800", QR_SCENE_TEMP, "123"));
		return getATValue(lines);

	}
	//temperate QR
	public final static String QR_SCENE_TEMP="QR_SCENE";
	//permanent QR
	public final static String QR_SCENE_PER="QR_LIMIT_SCENE";
	/**
	 * @return data to be sent when asking for the QR 
	 */
	public static String ticketInfo(String expire,String actionName,String id){
		String tmp ="{\"expire_seconds\": "+expire+", \"action_name\": \""+actionName+"\", \"action_info\": {\"scene\": {\"scene_id\": "+id+"}}}";
		return tmp;
	}
}

