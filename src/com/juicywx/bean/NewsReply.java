package com.juicywx.bean;


import com.thoughtworks.xstream.annotations.XStreamAlias;

public class NewsReply extends Reply{
//	@XStreamAlias("ToUserName")
//	private String toUserName;
//	@XStreamAlias("FromUserName")
//	private String fromUserName;
//	@XStreamAlias("CreateTime")
//	private Date createTime;
//	@XStreamAlias("CreateTime")
//	private String msgType;
	@XStreamAlias("ArticleCount")
	private String articleCount ;
	@XStreamAlias("Articles")
	private String articles ;
	@XStreamAlias("url")
	private String Url ;
	@XStreamAlias("PicUrl")
	private String picUrl;
	@XStreamAlias("Title")
	private String title;
	@XStreamAlias("Description")
	private String description;
}
