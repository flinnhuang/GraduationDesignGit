package com.gree.q.util;
import java.io.*;
import java.util.*;

public class StringLength {
  public StringLength() {
  }
  /**
   *
   * @param str String  ������ַ�
   * @param length int  ����
   * @param maxlength long  ��󳤶�
   * @return boolean
   */
  public static boolean getStringLength(String str, int length,long maxlength) {
    long l=str.getBytes().length;
    boolean result=false;
    if(l*length>maxlength){
      result=true;
    }
    return result;
  }

 public static String setItem(String str) {
  String result="";
  if(str.trim().length()<20){
    for(int i=0;i<9;i++){
      result+=" ";
    }
  }
  result=result+str.trim();
  return result;
}

  /**
   *
   * @param str String
   * @param length int
   * @param maxlength long
   * @return boolean
   */
  public static String getModifyTime(String file) {
        File f=new File("e:\\����.txt");
        long l=f.lastModified();
        long d=f.length();
        System.out.println(d);
        java.text.SimpleDateFormat df= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date(l));
  }

  public static void main(String[] args) throws IOException {
    System.out.println(StringLength.getStringLength("��",2,3));
    File f=new File("StringLength.java");
    System.out.println(f.getCanonicalPath());
  }
}
