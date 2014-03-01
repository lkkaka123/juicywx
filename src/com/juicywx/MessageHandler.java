package com.juicywx;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.juicywx.bean.Message;
import com.juicywx.bean.Reply;
import com.juicywx.bean.TextReply;
import com.juicywx.bean.WxFormat;
import com.juicywx.business.BusinessProcess;

public class MessageHandler {
	protected Message mMessage;
	protected String mReply;
	
	public MessageHandler(){
		
	}
	public void dispath(HttpServletRequest req){
		Message msg=null;
		try {
			msg = WxFormat.prase(req.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(msg == null) return;
		mMessage=msg;
		if(msg.getMsgType().equals("text")) handlerText();
		else if(msg.getMsgType().equals("image")) handlerImage();
		else if(msg.getMsgType().equals("voice")) handlerVoice();
		else if(msg.getMsgType().equals("location")) handlerLocation();
		else if(msg.getMsgType().equals("news")) handlerLink();
		else if(msg.getMsgType().equals("video")) handlerVideo();
		else if(msg.getMsgType().equals("event")) handlerEvent();
	}
	private void handlerVideo() {
		// TODO Auto-generated method stub
		
	}
	private void handlerLink() {
		// TODO Auto-generated method stub
		
	}
	protected void handlerEvent() {
		TextReply reply = new TextReply();
		
		reply.setFromUserName(mMessage.getToUserName());
		reply.setToUserName(mMessage.getFromUserName());
		reply.setCreateTime(new Date().getTime());
		reply.setMsgType(Reply.TEXT);
		BusinessProcess process = new BusinessProcess();
		reply.setContent(process.getEventOrder(mMessage.getEvent()));
		mReply=WxFormat.replyToXml(reply);
	}
	protected void handlerVoice() {
		// TODO Auto-generated method stub
		
	}
	protected void handlerLocation() {
		// TODO Auto-generated method stub
		
	}
	protected void handlerImage() {
		// TODO Auto-generated method stub
		
	}
	protected void handlerText(){
		TextReply reply = new TextReply();
	
		reply.setFromUserName(mMessage.getToUserName());
		reply.setToUserName(mMessage.getFromUserName());
		reply.setCreateTime(new Date().getTime());
		reply.setMsgType(Reply.TEXT);
		reply.setContent("欢迎");
		
		mReply=WxFormat.replyToXml(reply);
	}
	public String getmReply() {
		return mReply;
	}

}
