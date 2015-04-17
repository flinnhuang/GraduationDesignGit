package com.gree.q.util;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64 {
	public static String encode(String str) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encodeBuffer(str.getBytes()).trim();
	}

	public static String decode(String str) {
		BASE64Decoder dec = new BASE64Decoder();
		try {
			return new String(dec.decodeBuffer(str));
		} catch (IOException io) {
			throw new RuntimeException(io.getMessage(), io.getCause());
		}
	}
	

}
