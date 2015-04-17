package com.gree.q.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
  public DateUtil() {}
  public static String getDatePattern() {
    return defaultDatePattern;
  }
  public static String getDateString(String aDate, long aTime) {
      String returnValue = "";
      if(!"".equals(aDate)){
          aDate = aDate.replaceAll("/", "-");
          if (!"1970-01-01".equals(aDate.substring(0, 10))) {
              SimpleDateFormat df = null;
              Date returnDate = null;
              if (aDate != null) {
                  df = new SimpleDateFormat(defaultDatePattern);
                  try {
                      returnDate = df.parse(aDate);
                      long a = aTime * 60 * 60 * 1000;
                      long total = returnDate.getTime() + a;
                      returnDate = new Date(total);
                      returnValue = df.format(returnDate);
                  }
                  catch (ParseException e) {
                      e.printStackTrace();
                  }
              }
          }
      }
    return returnValue;
  }
  public static String getDateStringDate(String aDate, long aTime) {
    aDate=aDate.replaceAll("/","-");
    SimpleDateFormat df = null;
    SimpleDateFormat df1 = null;
    Date returnDate = null;
    String returnValue = "";
    if (aDate != null) {
      df = new SimpleDateFormat(defaultDatePattern1);
      df1 = new SimpleDateFormat(defaultDatePattern);
      try {
        returnDate = df.parse(aDate);
        long a = aTime * 60 * 60 * 1000;
        long total = returnDate.getTime() + a;
        returnDate = new Date(total);
        returnValue = df1.format(returnDate);
      }
      catch (ParseException e) {
        e.printStackTrace();
      }
    }
    return returnValue;
  }
  public static String getDate(String aDate, long aTime) {
    String returnValue = "";
    if(!"".equals(aDate)){
    if(!"1970/01/01".equals(aDate.substring(0,10)) && !"0001/01/01".equals(aDate.substring(0,10))){
        aDate = aDate.replaceAll("/", "-");
      SimpleDateFormat df = null;
      SimpleDateFormat df1 = null;
      Date returnDate = null;
      if (aDate != null) {
        df = new SimpleDateFormat(defaultDatePattern);
        df1 = new SimpleDateFormat("yyyy/MM/dd");
        try {
          returnDate = df.parse(aDate);
          long a = aTime * 60 * 60 * 1000;
          long total = returnDate.getTime() + a;
          returnDate = new Date(total);
          returnValue = df1.format(returnDate);
        }
        catch (ParseException e) {
          e.printStackTrace();
        }
      }
    }
    }
    return returnValue;
  }
  private static String defaultDatePattern = "yyyy-MM-dd HH:mm:ss";
  private static String defaultDatePattern1 = "yyyy-MM-dd";
  //����
  public static void main(String[] args) {
//    Date dat1 = new Date();
//    long dd = dat1.getTime();
    //for (int i = 0; i < 10000; i++) {
      System.out.println(DateUtil.getDate("2007/09/14", -8));
     // DateUtil.getDateString("2006-11-8 8:32:45", -8);
    //}
//    Date dat2 = new Date();
//    long ff = dat2.getTime();
//    System.out.println(ff - dd);
  }
}
