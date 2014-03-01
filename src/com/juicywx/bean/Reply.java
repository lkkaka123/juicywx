package com.juicywx.bean;


import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Reply {
	public static final String TEXT="text";
	public static final String VOICE="voice";
	public static final String VIDEO="video";
	public static final String LOCATION="location";
	public static final String MUSIC="music";
	public static final String NEWS="news";

	@XStreamAlias("ToUserName")
	private String toUserName;
	@XStreamAlias("FromUserName")
	private String fromUserName;
	@XStreamAlias("CreateTime")
	private long createTime;
	@XStreamAlias("MsgType")
	private String msgType;
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
}
