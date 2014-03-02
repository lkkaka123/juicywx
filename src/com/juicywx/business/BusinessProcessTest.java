package com.juicywx.business;

import static org.junit.Assert.*;

import org.junit.Test;

public class BusinessProcessTest {

	
	public void test() {
		
		String result = null;
		BusinessProcess process = new BusinessProcess();
		result = process.getAccessToken(BusinessProcess.URL);
		assertEquals("ji", result);
	    
	}
	public static String getATValue(String result){
//		result = result.substring(1, result.length()-1);
		String[] array = result.split("\"");
//		array = array[0].split(":");
		
		return array[3];
	}
	
	public void testgetATValue(){
		assertEquals("123", getATValue("{\"access_token\":\"MP_S5jCYPlhgzl10Jr_7VObQVEZqjcywQEbrzXwM-tLFlwGnqAherXNSNzl41EpDKsrp_WgE_-6ZiH2Il-3GFZe82tC8S34nbYSb6qQFyZcVcqFgaol6QZnTBUmlXozxRv31tuQrY32n8Bz7SXWQwQ\",\"expires_in\":7200}"));
	}
	@Test
	public void testPostTicket(){
		BusinessProcess process = new BusinessProcess();
		
		String TOKEN = process.getAccessToken(BusinessProcess.URL);
		
		String ticket = process.getQRCodeTicket("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+TOKEN);
		assertEquals("i dont ", ticket);
	}
}

