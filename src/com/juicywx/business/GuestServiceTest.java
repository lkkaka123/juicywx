package com.juicywx.business;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import com.juicywx.data.Guest;


public class GuestServiceTest {

	@Test
	public void test() {
		String src="{\"subscribe\": 1,\"openid\": \"o6_bmjrPTlm6_2sgVt7hMZOPfL2M\",\"nickname\": \"Band\", \"sex\": 1, \"language\": \"zh_CN\", \"city\": \"广州\", \"province\": \"广东\", \"country\": \"中国\", \"headimgurl\":    \"http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0\", \"subscribe_time\": 1382694957}";
		HashMap<String,String> map = GuestService.parseStrToJson(src);
		assertEquals("1382694957",map.get("headimgurl"));
	}
	
	public  void testAddExp(){
		GuestService.addVIPUser("123456");
		GuestService.addGuestExp("123456",12);
		Guest guest = GuestService.getVIPInfo("123456");
		assertEquals("12", guest.Exp);
	}

}
