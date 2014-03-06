package com.juicywx.business;

import java.util.HashMap;

import com.google.appengine.api.datastore.Entity;
import com.juicywx.data.Guest;
import com.juicywx.http.HttpClient;
import com.juicywx.store.Sql;

/**
 * 
 * @author yueqi
 * 
 */
public class GuestService {
	private static final String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?lang=zh_CN&";

	public static void addVIPUser(String openID) {
		if(true==checkVIPUser(openID))
			return;
		Entity entity = new Entity(Guest.Kind, openID);
		Guest guest = getUserInfo(openID);
		entity.setProperty("openId", guest.openId);
		entity.setProperty("name", guest.name);
		entity.setProperty("exp", guest.Exp);
		entity.setProperty("use", guest.Use);
		entity.setProperty("sex", guest.sex);
		entity.setProperty("city", guest.city);
		entity.setProperty("headImg", guest.headImg);
		Sql.put(entity);
	}
	
	public static boolean checkVIPUser(String openID){
		Entity entity = Sql.get(Guest.Kind,openID);
		if(entity==null){
			return false;
		}else{
			return true;
		}
	}
	
	public static boolean addGuestExp(String openID,int plus){
		if(false==checkVIPUser(openID))
			return false;
		Entity entity = Sql.get(Guest.Kind,openID);
		if(entity!=null){
			int exp =Integer.parseInt(((Long)entity.getProperty("exp")).toString());
			exp+=plus;
			entity.setProperty("exp",exp);
			Sql.put(entity);
			return true;
		}else{
			return false;
		}
	}
	public static boolean addGuestUse(String openID,int plus){
		if(false==checkVIPUser(openID))
			return false;
		Entity entity = Sql.get(Guest.Kind,openID);
		if(entity!=null){
			int use = Integer.parseInt(((Long)entity.getProperty("use")).toString());
			use+=plus;
			entity.setProperty("use",use);
			Sql.put(entity);
			return true;
		}else{
			return false;
		}
	}
	
	public static Guest getVIPInfo(String openID){
		if(false==checkVIPUser(openID))
			return null;
		Entity entity = Sql.get(Guest.Kind,openID);
		Guest guest=null;
		if(entity!=null){
			guest = new Guest();
			guest.openId=(String) entity.getProperty("openId");
			guest.name=(String) entity.getProperty("name");
			guest.Exp=Integer.parseInt(((Long)entity.getProperty("exp")).toString());
			guest.Use=Integer.parseInt(((Long)entity.getProperty("use")).toString());
			guest.sex=(String) entity.getProperty("sex");
			guest.city=(String) entity.getProperty("city");
			guest.headImg=(String) entity.getProperty("headImg");
			return guest;
		}
		else{
			return null;
		}
		
	}

	private static Guest getUserInfo(String openID) {
		WXService GAT = WXService.getInstance();
		String paramOpenId = "openid=" + openID;
		String paramAcsessToken = "access_token" + GAT.getAccessToken();
		String url = USER_INFO_URL + paramOpenId + "&" + paramAcsessToken;
		String jsonStr = HttpClient.httpGet(url);
		HashMap<String, String> hm = parseStrToJson(jsonStr);
		Guest guest = new Guest();

		guest.openId = hm.get("openid");
		guest.name = hm.get("nickname");
		guest.city = hm.get("city");
		guest.headImg = hm.get("headimgurl");
		guest.sex = hm.get("sex");

		return guest;
	}

	public static HashMap<String, String> parseStrToJson(String str) {
		str = str.substring(1, str.length() - 1);
		String[] arr1 = str.split(",");
		String[] tmp;
		String val;
		String key;
		HashMap<String, String> hm = new HashMap<>();
		for (int i = 0; i < arr1.length; i++) {
			tmp = arr1[i].split(":", 2);
			val = tmp[1].trim();
			if (val.startsWith("\""))
				val = val.substring(1, val.length() - 1);
			key = tmp[0].trim();
			key = key.substring(1, key.length() - 1);
			hm.put(key, val);
		}
		return hm;
	}

}
