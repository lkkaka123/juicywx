package com.juicywx.bean;


import java.util.List;

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
	private List<Article> articles;
	public String getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
}
