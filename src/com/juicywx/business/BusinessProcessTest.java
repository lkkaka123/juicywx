package com.juicywx.business;

import static org.junit.Assert.*;

import org.junit.Test;

public class BusinessProcessTest {
	@Test
	public void test() {

		String result = null;
		long t=System.currentTimeMillis();
		System.out.println(t);
		result=WXService.getInstance().getAccessToken();
		long tt=System.currentTimeMillis();
		System.out.println(tt-t);
		t=tt;
		System.out.println(t);
		result=WXService.getInstance().getAccessToken();
		tt=System.currentTimeMillis();
		System.out.println(tt-t);
		System.out.println(t);
		result=WXService.getInstance().getAccessToken();
		t=tt;
		tt=System.currentTimeMillis();
		System.out.println(tt-t);
		assertEquals("ji", result);
	}

	public static String getATValue(String result) {
		String[] array = result.split("\"");
		return array[3];
	}

	public void testgetATValue() {
		assertEquals(
				"123",
				getATValue("{\"access_token\":\"MP_S5jCYPlhgzl10Jr_7VObQVEZqjcywQEbrzXwM-tLFlwGnqAherXNSNzl41EpDKsrp_WgE_-6ZiH2Il-3GFZe82tC8S34nbYSb6qQFyZcVcqFgaol6QZnTBUmlXozxRv31tuQrY32n8Bz7SXWQwQ\",\"expires_in\":7200}"));
	}

	
	public void testPostTicket() {
		WXService wx = WXService.getInstance();
		String TOKEN = wx.getAccessToken();

		String ticket = wx.getQRCodeTicket("123");
		assertEquals("i dont ", ticket);
	}
}
