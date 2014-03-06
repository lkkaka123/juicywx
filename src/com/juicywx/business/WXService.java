package com.juicywx.business;

import com.juicywx.bean.TextReply;
import com.juicywx.http.HttpClient;

public class WXService {
	// private Authorized info of each WX-connected system
	private static String appID = "wx96022e4689c2c1ea";
	private static String appSecret = "60a2e77f637d1699a29357a4149f8b6f";
	// TOKEN_URL
	public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
			+ appID + "&secret=" + appSecret;
	public final static String TICKET_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
	// temperate QR
	public final static String QR_SCENE_TEMP = "QR_SCENE";
	// permanent QR
	public final static String QR_SCENE_PER = "QR_LIMIT_SCENE";
	
	public final static String KF_URL="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

	private String TOKEN =null;
	private long tokenExpireTime=0;
	private Object tokenLock = new Object();
	public final static String TAG = "WXService";
	private static class Internal{
		private static WXService instance = new WXService();
	}
	private WXService(){
		
	}
	
	public static WXService getInstance(){
		return Internal.instance;
	}
	/**
	 * any request to Professional API provided by WeiXin will ask for the TOKEN
	 * 
	 * @return TOKEN
	 */
	private void accessToken() {
		String lines = HttpClient.httpGet(TOKEN_URL);
		TOKEN = getTokenValue(lines);
		tokenExpireTime =System.currentTimeMillis()+Integer.parseInt(getExpireValue(lines))*1000L;
	}
	
	public String getAccessToken(){
		synchronized(tokenLock){
			if(System.currentTimeMillis()>=tokenExpireTime){
				//update token and expire
				accessToken();
			}
		}
		return TOKEN;
	}

	/**
	 * {"key":"value","key","value"} get fist value
	 */
	private String getTokenValue(String result) {
		String[] array = result.split("\"");
		return array[3];
	}
	private String getExpireValue(String result){
		String[] array = result.split("\"");
		return array[6].substring(1,array[6].length()-1);
	}
	private String getTicketValue(String result){
		String[] array = result.split("\"");
		return array[3];
	}

	/**
	 * WeiXin will give the ticket for downloading the QR
	 * 
	 * @return ticket
	 */
	public String getQRCodeTicket(String sceneId) {
		String lines = HttpClient.httpPost(TICKET_URL+getAccessToken(),
				ticketInfo("1800", QR_SCENE_TEMP, sceneId));
		return getTicketValue(lines);

	}

	/**
	 * @return data to be sent when asking for the QR
	 */
	private String ticketInfo(String expire, String actionName, String id) {
		String tmp = "{\"expire_seconds\": " + expire + ", \"action_name\": \""
				+ actionName
				+ "\", \"action_info\": {\"scene\": {\"scene_id\": " + id
				+ "}}}";
		return tmp;
	}
	
	public void sendKFText(String openID,String content){
		TextReply reply = new TextReply();
		reply.setToUserName(openID);
		reply.setContent(content);
		String toSendStr = TextKFToJsonStr(reply);
		String token = WXService.getInstance().getAccessToken();
		HttpClient.httpPost(KF_URL+token, toSendStr);
	}
	
	private String TextKFToJsonStr(TextReply reply){
		StringBuilder str =new StringBuilder();
		str.append("{");
		str.append("\"touser\":\""+reply.getToUserName()+"\",");
		str.append("\"msgtype\":\"text\",");
		str.append("\"text\":{");
			str.append("\"content\":\""+reply.getContent()+"\"");
		str.append("}");
		str.append("}");
		return str.toString();
	}
	
}
