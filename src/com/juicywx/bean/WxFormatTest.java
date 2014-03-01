package com.juicywx.bean;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import org.junit.Test;

public class WxFormatTest {

	@Test
	public void test() {
		Message msg=null;
		try {
			msg = WxFormat.prase(new FileInputStream(new File("test.xml")));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("zh", msg.getFromUserName());
	}
	@Test
	public void testObjectToXml(){
		TextReply tp = new TextReply();
		tp.setFromUserName("zh");
		tp.setToUserName("li");
		tp.setContent("hello world");
		tp.setMsgType("text");
		tp.setCreateTime(new Date().getTime());;
		String str = WxFormat.replyToXml(tp);
	
		assertEquals("wx", str);
	}

}
