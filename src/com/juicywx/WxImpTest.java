package com.juicywx;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class WxImpTest {

	@Test
	public void test() {
		String TOKEN = "TOKEN";
		String timestamp = "timestamp";
		String nonce = "nonce";
		String[] array = new String[]{TOKEN,timestamp,nonce};
		Arrays.sort(array,String.CASE_INSENSITIVE_ORDER);
		String tmpStr = "";
		for (String str : array) {
			tmpStr = tmpStr + str;
		}
		assertEquals("noncetimestampTOKEN", tmpStr);
	}

}
