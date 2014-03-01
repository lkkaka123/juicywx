package com.juicywx.business;

public class BusinessProcess {
	public String getTextOrder(String input){
		return "";
	}
	public String getEventOrder(String eventType){
		if(eventType.equals("subscribe")){
			return "订阅成功";
		}else{
			return "解绑成功";
		}
		
	}
}
