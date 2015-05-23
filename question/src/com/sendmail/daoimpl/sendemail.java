package com.sendmail.daoimpl;
import java.util.List;
import java.util.Properties;  
import javax.mail.Address;  
import javax.mail.Flags;  
import javax.mail.Folder;  
import javax.mail.Message;  
import javax.mail.MessagingException;  
import javax.mail.NoSuchProviderException;  
import javax.mail.Session;  
import javax.mail.Store;  
import javax.mail.Transport;  
import javax.mail.internet.AddressException;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  

import com.model.QUESTION;
public class sendemail {  
	public sendemail()  {
	}
	static String host = "smtp.qq.com";//邮件服务器  
	static String from = "872081258@qq.com";//发件人地址
	static String userName = "872081258";//发件人邮件名称  
	static String pwd = "QWAShun87";//发件人邮件密码   
	
	
	/**  * send mail  */  
	public void sendMail(List tolist, String sendcontent, int dg)  {
		//String to = "872081258@qq.com";//接受地址（必须支持pop3协议）  
		Properties props = new Properties();//获得系统属性对象 
		props.put("mail.smtp.host", host);  //设置SMTP主机 
		props.put("mail.smtp.auth", "utre");   //是否到服务器用户名和密码验证 
		
		String subject="";
		if (dg==3){
			subject= "异常处理 邮件提示:您的普通异常任务单有更新,请登录系统处理!";
		}
		if (dg==2){
			subject= "异常处理 邮件提示:您的紧急异常任务单有更新,请及时登录系统处理!";
		}
		if (dg==1){
			subject= "异常处理 邮件提示:您的特急异常任务单有更新,请尽快登录系统处理!";
		}
		
		Session session = Session.getDefaultInstance(props);  
		session.setDebug(true);   
		MimeMessage msg = new MimeMessage(session);
		try {  
			msg.setFrom(new InternetAddress(from));  
			msg.setSubject(subject,"UTF-8");//邮件主题  
			msg.setContent(sendcontent, "text/html;charset=UTF-8");//带格式的html内容
			msg.saveChanges();   
			Transport transport = session.getTransport("smtp"); //设置传输协议  
			transport.connect(host, userName, pwd);//邮件服务器验证  
			int length = tolist.size();
			String to = "";
			System.out.println("send mail.......for循环.............."+tolist.size());
			for(int i = 0;i<length;i++){
				to = tolist.get(i).toString();
				System.out.println("send mail..........................."+to);
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			}
			transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));//发送
			System.out.println("send ok...........................");
		} catch (AddressException e) {  
			e.printStackTrace();  
		} catch (MessagingException e) {  
			e.printStackTrace();  
		}  
	} 

}