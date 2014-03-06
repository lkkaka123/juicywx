package com.juicywx.business;

import javax.servlet.http.HttpSession;

public class SessionService {
	private static SessionService instance = new SessionService();
	private HttpSession mSession;
	public static SessionService getInstance(){
		return instance;
	}
	public void setSession(HttpSession session){
		mSession = session;
	}
	
	public Object getValue(String key){
		return mSession.getAttribute(key);
	}
	public void setKeyVal(String key,Object val){
		mSession.setAttribute(key, val);
	}
}
