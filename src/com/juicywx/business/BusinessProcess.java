package com.juicywx.business;

import java.util.Date;

import com.juicywx.bean.Article;
import com.juicywx.bean.Message;
import com.juicywx.bean.NewsReply;
import com.juicywx.bean.WxFormat;
import com.juicywx.data.Guest;
import com.juicywx.http.HttpClient;

public class BusinessProcess {
	
	public String getTextOrder(String input) {
		return "";
	}

	public String getEventOrder(Message msg) {
		
		if (msg.getEvent().equals("subscribe")) {
			GuestService.addVIPUser(msg.getFromUserName());
			return "订阅成功";
		}else if(msg.getEvent().equals("SCAN")){
			int exp=0;
			if(msg.getEventKey().equals(SceneManager.SCENE_ADD_EXP)){
				//add experience
				//send a link to guest
				NewsReply newsReply = new NewsReply();
				newsReply.setArticleCount("1");
				long time = new Date().getTime();
				newsReply.setCreateTime(time);
				newsReply.setFromUserName(msg.getToUserName());
				newsReply.setToUserName(msg.getFromUserName());
				Article article =new Article();
				article.setDescription("add exp");
				article.setPicUrl("http://mine.juicywx.com/image/ilike.jpg");
				article.setTitle("confirm the order");
				article.setUrl("http://mine.juicywx.com/action.jsp?action=first&timestamp="+time+"&openId="+msg.getFromUserName());
				return WxFormat.replyToXml(newsReply);
			}else if(msg.getEventKey().equals("321")){
				
				Guest guest = GuestService.getVIPInfo(msg.getFromUserName());
				exp=guest.Exp-guest.Use;
				if(exp>0){
					boolean gs = GuestService.addGuestUse(msg.getFromUserName(), 1);
					exp-=1;
				}
				return "你有"+exp+"积分";
			}else{
				return "";
			}
			
		}else{
			return msg.getEventKey();
		}

	}
	
}

