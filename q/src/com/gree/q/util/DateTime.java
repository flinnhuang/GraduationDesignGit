// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2006-3-4 10:55:11
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DateTime.java

package com.gree.q.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateTime {

	private DateTime() {
	}

	public static Date getDateNowTime() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	public static String getStringNowTime(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(format);
		String date = df.format(cal.getTime());
		return date;
	}

	public static Date ValueOfTime(String dateStr, String format) {
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			date = df.parse(dateStr);
		} catch (ParseException ex) {
			ex.printStackTrace();
			date = null;
		}
		return date;
	}

	public static String getDateToString(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		String dat = df.format(date);
		return dat;
	}

	public static Date addYear(Date date, int year) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + year);
		return cal.getTime();
	}

	public static Date addMonth(Date date, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + month);
		return cal.getTime();
	}

	public static Date addDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + day);
		return cal.getTime();
	}

	public static Date addHour(Date date, int hour) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + hour);
		return cal.getTime();
	}

	public static Date addMinute(Date date, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + minute);
		return cal.getTime();
	}

	public static Date addSecond(Date date, int second) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + second);
		return cal.getTime();
	}

	public static Date addMilliSecond(Date date, int millisecond) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MILLISECOND, cal.get(Calendar.MILLISECOND)
				+ millisecond);
		return cal.getTime();
	}
}