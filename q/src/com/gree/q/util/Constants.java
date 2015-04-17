// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2005-6-28 19:39:04
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Constants.java

package com.gree.q.util;

import java.util.Date;

public class Constants {
	public static final boolean INFO = true;

	//public static final String UPLOAD_PATH = "\\\\192.13.183.83\\q_file\\";
	public static final String UPLOAD_PATH = "\\\\172.16.16.148\\q_file\\";
	
	public static final String JNDI_MAIL = "mailSession";
	
	public static final void out(String msg) {
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] - "
				+ msg);
	}
}