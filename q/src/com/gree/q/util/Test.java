package com.gree.q.util;

import java.text.ParseException;

public class Test {
	public static void main(String[] args) {
		java.text.SimpleDateFormat df002 = new java.text.SimpleDateFormat("yyyy-MMM-dd");//dd-MMM-yy
		
		try {
			java.util.Date d = df002.parse("2013-����-04");
			
			System.out.println(d);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
