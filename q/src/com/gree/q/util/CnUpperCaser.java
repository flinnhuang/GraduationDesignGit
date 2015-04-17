// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2005-6-28 19:39:04
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Constants.java
package com.gree.q.util;
/**
 * 将10亿以内的阿拉伯数字转成汉字大写形式
 * @author xizhenyin
 * 
 */
public class CnUpperCaser {
  // 整数部分
  private String integerPart;
  // 小数部分
  private String floatPart;

  // 将数字转化为汉字的数组,因为各个实例都要使用所以设为静态
  private static final char[] cnNumbers={'零','壹','贰','叁','肆','伍','陆','柒','捌','玖'};
  // 供分级转化的数组,因为各个实例都要使用所以设为静态
  private static final char[] series={'元','拾','佰','仟','万','拾','佰','仟','亿'};

  public CnUpperCaser(String original){ 
    // 成员变量初始化
    integerPart="";
    floatPart="";
    
    if(original.split("\\.").length==2){
    	
      // 如果包含小数点
      int dotIndex=original.indexOf(".");
      integerPart=original.substring(0,dotIndex);
      floatPart=original.substring(dotIndex+1);
      
    }else{
      // 不包含小数点
      integerPart=original;
    }
    
   
  }
  
  /** 
   * 取得大写形式的字符串
   * @return
   */
  public String getCnString(){
    // 因为是累加所以用StringBuffer
    StringBuffer sb=new StringBuffer();
    
    // 整数部分处理
    for(int i=0;i<integerPart.length();i++){
      int number=getNumber(integerPart.charAt(i));
      
      sb.append(cnNumbers[number]);
      sb.append(series[integerPart.length()-1-i]);
    }
    
    // 小数部分处理
    if(floatPart.length()>0){
    	if(Float.parseFloat(floatPart)==0){
    		//sb.append("整");
    	}else{
    		sb.append("点");
    		for(int i=0;i<floatPart.length();i++){
    			int number=getNumber(floatPart.charAt(i));
    			
    			sb.append(cnNumbers[number]);
    		}
    	}
    }
    
   
    // 返回拼接好的字符串
    return sb.toString();
  }
  
  /**
   * 将字符形式的数字转化为整形数字
   * 因为所有实例都要用到所以用静态修饰
   * @param c
   * @return
   */
  private static int getNumber(char c){
    String str=String.valueOf(c);   
    return Integer.parseInt(str);
  }
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(new CnUpperCaser("12,345,6789.12345".replaceAll("\\,", "")).getCnString());
    System.out.println(new CnUpperCaser("123456789").getCnString());
    System.out.println(new CnUpperCaser(".123456789").getCnString());
    System.out.println(new CnUpperCaser("0.1234").getCnString());
    System.out.println(new CnUpperCaser("1").getCnString());
    System.out.println(new CnUpperCaser("12").getCnString());
    System.out.println(new CnUpperCaser("123").getCnString());
    System.out.println(new CnUpperCaser("1234").getCnString());
    System.out.println(new CnUpperCaser("12345").getCnString());
    System.out.println(new CnUpperCaser("123456").getCnString());
    System.out.println(new CnUpperCaser("1234567").getCnString());
    System.out.println(new CnUpperCaser("12345678").getCnString());
    System.out.println(new CnUpperCaser("123456789.14").getCnString());
    System.out.println(new CnUpperCaser("123456789.00").getCnString());
  }
}

