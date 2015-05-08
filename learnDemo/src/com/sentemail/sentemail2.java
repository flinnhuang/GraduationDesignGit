package com.sentemail;
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
public class sentemail2 {  
	public static void main(String[] args)  {
		sentemail2.sendMail();  
		//sentemail2.receiveMail();  
		//sentemail2.deleteMail();  
		}   
	/**  * send mail  */  
	public static void sendMail()  {  
		String host = "prism.coresolutions.com";//邮件服务器  
		String from = "flinn.huang@cbxsoftware.com";//发件人地址 
		String to = "872081258@qq.com";//接受地址（必须支持pop3协议）  
		String userName = "flinn.huang";//发件人邮件名称  
		String pwd = "ASZXhun87";//发件人邮件密码 
		
		Properties props = new Properties();//获得系统属性对象   
		props.put("mail.smtp.host", host);  //设置SMTP主机 
		props.put("mail.smtp.auth", "true");   //是否到服务器用户名和密码验证 
		Session session = Session.getDefaultInstance(props);  
		session.setDebug(true);   
		MimeMessage msg = new MimeMessage(session);  
		try {  
			msg.setFrom(new InternetAddress(from));  
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));//发送  
			msg.setSubject("我的测试...........","gb2312");//邮件主题  
			//msg.setText("测试内容。。。。。。。<br/><p>标题1</p>");//邮件内容  
			msg.setContent("测试内容。。。5/8/2015。。。。<br/><p>标题1</p>", "text/html;charset=gb2312");//带格式的html内容
			msg.saveChanges();   
			Transport transport = session.getTransport("smtp"); //设置传输协议  
			transport.connect(host, userName, pwd);//邮件服务器验证  
			transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));  
			System.out.println("send ok...........................");  
			} catch (AddressException e) {  
				e.printStackTrace();  
				} catch (MessagingException e) {  
					e.printStackTrace();  
					}  
		}   
	/**  * receive mail  */ 
	public static void receiveMail()  {  
		String host = "pop3.sina.com";  
		String userName = "xingui5624";  
		String passWord = "******";   
		Properties props = new Properties();  
		Session session = Session.getDefaultInstance(props);  
		session.setDebug(true);  
		try {  
			System.out.println("receive...............................");  
			Store store = session.getStore("pop3");  
			store.connect(host, userName,passWord);//验证  
			Folder folder = store.getFolder("INBOX");//取得收件文件夹  
			folder.open(Folder.READ_WRITE);  
			Message msg[] = folder.getMessages();  
			System.out.println("邮件个数:" + msg.length);   
			for(int i=0; i<msg.length; i++)  {  
				Message message = msg[i];  
				Address address[] = message.getFrom();  
				StringBuffer from = new StringBuffer();  
				/**  * 此for循环是我项目测试用的  */  
				for(int j=0; j<address.length; j++)  {  
					if (j > 0)  from.append(";");  
					from.append(address[j].toString());  
					} 
					System.out.println(message.getMessageNumber()); 
					System.out.println("来自：" + from.toString()); 
					System.out.println("大小：" + message.getSize()); 
					System.out.println("主题：" + message.getSubject());  
					System.out.println("时间：：" + message.getSentDate());  
					System.out.println("===================================================");  
				}  
				folder.close(true);//设置关闭  
				store.close();  
				System.out.println("receive over............................");  
				} catch (NoSuchProviderException e) {  
					e.printStackTrace();  
				} catch (MessagingException e) 
				{  
					e.printStackTrace();  
				}  
			}   
	/**  * delete mail  */ 
		public static void deleteMail()  {  
			String host = "pop3.sina.com"; 
			String userName = "xingui5624";  
			String passWord = "******";  
			Properties props = new Properties();  
			//Properties props = System.getProperties();这种方法创建 Porperties 同上  
			Session session = Session.getDefaultInstance(props);  
			session.setDebug(true);  
			try {  
				System.out.println("begin delete ...........");  
				Store store = session.getStore("pop3"); 
				store.connect(host, userName, passWord);//验证邮箱  
				Folder folder = store.getFolder("INBOX"); 
				folder.open(Folder.READ_WRITE);//设置我读写方式打开  
				int countOfAll = folder.getMessageCount();//取得邮件个数  
				int unReadCount = folder.getUnreadMessageCount();//已读个数
				int newOfCount = folder.getNewMessageCount();//未读个数  
				System.out.println("总个数：" +countOfAll);  
				System.out.println("已读个数：" +unReadCount);  
				System.out.println("未读个数：" +newOfCount); 
				for(int i=1; i<=countOfAll; i++)  { 
					Message message = folder.getMessage(i); 
					message.setFlag(Flags.Flag.DELETED, true);//设置已删除状态为true  
					if(message.isSet(Flags.Flag.DELETED))  
						System.out.println("已经删除第"+i+"邮件。。。。。。。。。");  
					}  folder.close(true);  store.close();  
					System.out.println("delete ok.................................");  
					} catch (NoSuchProviderException e) {  
						e.printStackTrace();  
					} catch (MessagingException e) { 
						e.printStackTrace();  
						}  
					}   
		/**  * reply mail  */ 
	public static void replyMail()  {  //test  
			
	}
}