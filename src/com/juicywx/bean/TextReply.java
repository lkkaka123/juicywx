package com.juicywx.bean;


import com.thoughtworks.xstream.annotations.XStreamAlias;

public class TextReply extends Reply{

	@XStreamAlias("Content")
	private String content;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
