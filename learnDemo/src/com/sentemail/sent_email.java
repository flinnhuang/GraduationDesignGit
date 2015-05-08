package com.sentemail;
  
import java.io.*;  
import java.text.*;  
import java.util.*;  
import javax.activation.DataHandler;  
//import javax.activation.FileDataSource;  
//import javax.mail.*;  
//import javax.mail.internet.*;

import org.apache.commons.mail.HtmlEmail;

public class sent_email {
	public static void main(String[] args) {  

	//sent_email email = new sent_email(); 
	HtmlEmail email = new HtmlEmail();  
	email.setHostName("smtp.163.com");//邮件服务器  
	email.setAuthentication("xingui5624", "******");//smtp认证的用户名和密码  
	try {  
			email.addTo("xingui5624@163.com");//收信者  
			email.setFrom("xingui5624@126.com", "******");//发信者
			email.setSubject("xingui5624的测试邮件");//标题  
			email.setCharset("UTF-8");//编码格式  
			email.setMsg("这是一封xingui5624的测试邮件");//内容  
			email.send();//发送  
			System.out.println("send ok..........");  
		} catch (EmailException e) {  
			e.printStackTrace();  
		}   
	}
} 

