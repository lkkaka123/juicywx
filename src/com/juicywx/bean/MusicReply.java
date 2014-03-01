package com.juicywx.bean;

import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class MusicReply extends Reply{
//	@XStreamAlias("ToUserName")
//	private String toUserName;
//	@XStreamAlias("FromUserName")
//	private String fromUserName;
//	@XStreamAlias("CreateTime")
//	private Date createTime;
//	@XStreamAlias("CreateTime")
//	private String msgType;
	@XStreamAlias("Title")
	private String title;
	@XStreamAlias("Description")
	private String description;
	@XStreamAlias("MusicURL")
	private String musicURL ;
	@XStreamAlias("HQMusicUrl")
	private String hQMusicUrl ;
	@XStreamAlias("ThumbMediaId")
	private String thumbMediaId;
}
