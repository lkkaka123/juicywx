package com.juicywx.bean;

import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class VoiceReply extends Reply{
//	@XStreamAlias("ToUserName")
//	private String toUserName;
//	@XStreamAlias("FromUserName")
//	private String fromUserName;
//	@XStreamAlias("CreateTime")
//	private Date createTime;
//	@XStreamAlias("CreateTime")
//	private String msgType;
	@XStreamAlias("MediaId")
	private String mediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
}
