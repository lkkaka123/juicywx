package com.juicywx.bean;

import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class VideoReply extends Reply{
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
	@XStreamAlias("Title")
	private String title;
	@XStreamAlias("Description")
	private String description;
}
