package com.gree.q.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 * Utility class to send e-mail.
 * 
 * <p>
 * <a href="MailUtil.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @version $Revision: 1.4 $ $Date: 2014/03/15 06:17:44 $
 * 
 */
public class DJMailUtil extends Object {
	// ~ Static fields/initializers
	// =============================================
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	// ~ Methods
	// ================================================================

	/**
	 * Retrieves a Mail session from Tomcat's Resource Factory (JNDI)
	 */
	public static Session getSession() {
		Session session = null;

		try {
			session = (Session) new InitialContext()
					.lookup(Constants.JNDI_MAIL);
		} catch (NamingException ex) {
			Properties props = new Properties();
			// props.setProperty("mail.debug",
			// mailProps.getString("mail.debug"));
			// props.setProperty("mail.transport.protocol",
			// mailProps.getString("mail.transport.protocol"));
			// props.put("mail.smtp.host",host);
			props.put("mail.smtp.host", "10.1.1.168");
			props.put("mail.smtp.auth", "false");
			session = Session.getDefaultInstance(props, null);
		}

		return session;
	}

	/**
	 * This method is used to send a Message with an attachment.
	 * 
	 * @param from
	 *            e-mail address of sender
	 * @param to
	 *            e-mail address(es) of recipients
	 * @param subject
	 *            subject of e-mail
	 * @param content
	 *            the body of the e-mail
	 * @param mimeType
	 *            type of message, i.e. text/plain or text/html
	 * @throws MessagingException
	 *             the exception to indicate failure
	 */
	public static void sendMessageWithAttachment(String from, String[] to,
			String[] cc, String subject, String content, String mimeType,
			File attachment) throws MessagingException {
		Message message = new MimeMessage(getSession());

		// TODO: Refactor to use a default from address (maybe in config?!)
		if (true) {
			InternetAddress sentFrom = new InternetAddress(from);
			message.setFrom(sentFrom);

		
		}

		InternetAddress[] sendTo = new InternetAddress[to.length];

		for (int i = 0; i < to.length; i++) {
			sendTo[i] = new InternetAddress(to[i]);

		
		}

		if ((cc.length > 0) && (cc[0] != null)) {
			InternetAddress[] copyTo = new InternetAddress[cc.length];

			for (int i = 0; i < cc.length; i++) {
				copyTo[i] = new InternetAddress(cc[i]);

			
			}

			message.setRecipients(Message.RecipientType.CC, copyTo);
		}

		message.setRecipients(Message.RecipientType.TO, sendTo);

		message.setSubject(subject);

		// create the message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();

		messageBodyPart.setContent(content, mimeType);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment
		messageBodyPart = new MimeBodyPart();

		DataSource source = new FileDataSource(attachment);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(attachment.getName());

	

		multipart.addBodyPart(messageBodyPart);

		// Put parts in message
		message.setContent(multipart);

		Transport.send(message);
	}

	public static void sendMessageWithAttachments(String from, String[] to,
			String[] cc, String subject, String content, String mimeType,
			File[] attachment) throws MessagingException {
		Message message = new MimeMessage(getSession());

		// TODO: Refactor to use a default from address (maybe in config?!)
		if (true) {
			InternetAddress sentFrom = new InternetAddress(from);
			message.setFrom(sentFrom);

		}

		InternetAddress[] sendTo = new InternetAddress[to.length];

		for (int i = 0; i < to.length; i++) {
			sendTo[i] = new InternetAddress(to[i]);

	
		}

		if ((cc.length > 0) && (cc[0] != null)) {
			InternetAddress[] copyTo = new InternetAddress[cc.length];

			for (int i = 0; i < cc.length; i++) {
				copyTo[i] = new InternetAddress(cc[i]);

			}

			message.setRecipients(Message.RecipientType.CC, copyTo);
		}

		message.setRecipients(Message.RecipientType.TO, sendTo);

		message.setSubject(subject);

		// create the message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();

		messageBodyPart.setContent(content, mimeType);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment
		for (int i = 0; i < attachment.length; i++) {
			messageBodyPart = new MimeBodyPart();

			DataSource source = new FileDataSource(attachment[i]);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(attachment[i].getName());

		

			multipart.addBodyPart(messageBodyPart);

		}

		// Put parts in message
		message.setContent(multipart);

		Transport.send(message);
	}

	/**
	 * This method is used to send a Message with a pre-defined mime-type.
	 * 
	 * @param from
	 *            e-mail address of sender
	 * @param to
	 *            e-mail address(es) of recipients
	 * @param subject
	 *            subject of e-mail
	 * @param content
	 *            the body of the e-mail
	 * @param mimeType
	 *            type of message, i.e. text/plain or text/html
	 * @throws MessagingException
	 *             the exception to indicate failure
	 */
	public static void sendMessage(String from, String[] to, String[] cc,
			String subject, String content, String mimeType)
			throws MessagingException {
		Message message = new MimeMessage(getSession());

		// TODO: Refactor to use a default from address (maybe in config?!)
		if (true) {
			InternetAddress sentFrom = new InternetAddress(from);
			message.setFrom(sentFrom);

		}

		InternetAddress[] sendTo = new InternetAddress[to.length];

		for (int i = 0; i < to.length; i++) {
			sendTo[i] = new InternetAddress(to[i] + "@gree.com.cn");

		
		}

		if ((cc.length > 0) && (cc[0] != null)) {
			InternetAddress[] copyTo = new InternetAddress[cc.length];

			for (int i = 0; i < cc.length; i++) {
				copyTo[i] = new InternetAddress(cc[i] + "@gree.com.cn");

			
			}

			message.setRecipients(Message.RecipientType.CC, copyTo);
		}

		message.setRecipients(Message.RecipientType.TO, sendTo);

		message.setSubject(subject);
		message.setContent(content, mimeType);

		Transport.send(message);
	}

	/**
	 * This method is used to send a Text Message.
	 * 
	 * @param from
	 *            e-mail address of sender
	 * @param to
	 *            e-mail addresses of recipients
	 * @param subject
	 *            subject of e-mail
	 * @param content
	 *            the body of the e-mail
	 * @throws MessagingException
	 *             the exception to indicate failure
	 */
	public static void sendTextMessage(String from, String[] to, String[] cc,
			String subject, String content) throws MessagingException {
		sendMessage(from, to, cc, subject, content, "text/plain");
	}

	/**
	 * This method overrides the sendTextMessage to specify only one sender,
	 * rather than an array of senders.
	 * 
	 * @param from
	 *            e-mail address of sender
	 * @param to
	 *            e-mail address of recipients
	 * @param subject
	 *            subject of e-mail
	 * @param content
	 *            the body of the e-mail
	 * @throws MessagingException
	 *             the exception to indicate failure
	 */
	public static void sendTextMessage(String from, String to, String cc,
			String subject, String content) throws MessagingException {
		String[] recipient = { to };
		String[] copy = { cc };
		sendMessage(from, recipient, copy, subject, content, "text/plain");
	}

	/**
	 * This method allows you to specify the mimeType as part of the method
	 * call.
	 */
	public static void sendMessage(String from, String to, String cc,
			String subject, String content, String mimeType)
			throws MessagingException {
		String[] recipient = { to };
		String[] copy = { cc };
		sendMessage(from, recipient, copy, subject, content, mimeType);
	}

	/**
	 * Convenience method for sending messages with attachments.
	 */
	public static void sendMessage(String from, String to, String cc,
			String subject, String content, String mimeType, File attachment)
			throws MessagingException {
		String[] recipient = { to };
		String[] copy = { cc };
		sendMessageWithAttachment(from, recipient, copy, subject, content,
				mimeType, attachment);
	}

	/**
	 * This method is used to send a HTML Message
	 * 
	 * @param from
	 *            e-mail address of sender
	 * @param to
	 *            e-mail address(es) of recipients
	 * @param subject
	 *            subject of e-mail
	 * @param content
	 *            the body of the e-mail
	 * @throws MessagingException
	 *             the exception to indicate failure
	 */
	public static void sendHTMLMessage(String from, String[] to, String[] cc,
			String subject, String content) throws MessagingException {
		sendMessage(from, to, cc, subject, content, "text/html");
	}

	/**
	 * This method overrides the sendHTMLMessage to specify only one sender,
	 * rather than an array of senders.
	 * 
	 * @param from
	 *            e-mail address of sender
	 * @param to
	 *            e-mail address of recipients
	 * @param subject
	 *            subject of e-mail
	 * @param content
	 *            the body of the e-mail
	 * @throws MessagingException
	 *             the exception to indicate failure
	 */
	public static void sendHTMLMessage(String from, String to, String cc,
			String subject, String content) throws MessagingException {
		String[] recipient = { to };
		String[] copy = { cc };
		sendMessage(from, recipient, copy, subject, content, "text/html");
	}

	public static void sendmail(String userid, String titl, String djno,String notes,int sta1)
			throws MessagingException {
        
		String subject="";
		if (sta1==0){
			subject= "����δ����ĵ���,�뼰ʱ��¼ϵͳ����!";
		}
		if (sta1==1){
			subject= "(���Ｑ������)����δ����ĵ���,�뼰ʱ��¼ϵͳ����!";
		}
		
		String contentType = "text/html;charset=UTF-8";
		String[] to = { userid };
		String[] cc = {};
        
		String content = getContent(userid, titl, djno,notes);
		sendMessage("ContractMessager", to, cc, subject, content, contentType);
	}
	
	public static void sendcnam(String userid,String djno, String app1, String fdsca, String rdsca)
	throws MessagingException {

	String subject="����:"+djno+"�ѳɹ��ύ��"+fdsca+";��ǰ������:"+app1+"";
	String contentType = "text/html;charset=UTF-8";
	String[] to = { userid };
	String[] cc = {};
	
	String content = getContentCnam(userid, djno,app1,fdsca,rdsca);
	sendMessage("ContractMessager", to, cc, subject, content, contentType);
	}
	
	public static void sendinfo(String userid,String djno, String rdsca)
	throws MessagingException {

	String subject="����:"+djno+";�ѵ�����ִ�и����!";
	String contentType = "text/html;charset=UTF-8";
	String[] to = { userid };
	String[] cc = {};
	
	String content = getContentInfo(userid, djno,rdsca);
	sendMessage("ContractMessager", to, cc, subject, content, contentType);
	}
	
	private static String getContent(String userid, String titl, String cono,String notes) {
		return "<HTML><HEAD><TITLE>��ͬ����ϵͳ</TITLE>"
				+ "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"
				+ "<STYLE type=text/css>BODY {FONT-SIZE: 9pt; MARGIN: 0px; OVERFLOW: auto; FONT-FAMILY: Verdana, Geneva, Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #ffffff}"
				+ "BODY {FONT-SIZE: 12px; COLOR: #0377C1; FONT-FAMILY: arial}"
				+ "TABLE {FONT-SIZE: 12px; COLOR: #0377C1; FONT-FAMILY: arial}"
				+ "DIV#default {BORDER-RIGHT: black 0px solid; PADDING-RIGHT: 10px; BORDER-TOP: black 0px solid; PADDING-LEFT: 10px; BACKGROUND: #fff; PADDING-BOTTOM: 20px; MARGIN: 10pt auto 20pt 20pt; BORDER-LEFT: black 0px solid; WIDTH: 700px; PADDING-TOP: 10px; BORDER-BOTTOM: black 0px solid; TEXT-ALIGN: left}"
				+ "</STYLE>"
				+ "<BODY>"
				+ "<DIV id=default><B>��ͬ����ϵͳ�ʼ���ʾ:</B><BR>"
				+ "<TABLE width=\"100%\">"
				+ "  <TBODY>"
				+ "  <TR>"
				+ "    <TD colSpan=2>"
				+ " <LI>��ã�����: <FONT color=red><U>"
				+ cono
				+ "("+ titl+ ") ;"
				+ notes
				+ "</U></FONT> �� <FONT color=green>ע���뼰ʱ<A HREF='http://10.2.32.35:7101/htweb'>"+"��¼"+"</A>"+"ϵͳ���?</FONT></LI></TD></TR></TR>"
				+ "<TR>"
				+ "<TD colSpan=2>"
				+ "<LI><FONT color=red><B>ע:</B></FONT> "
				+ "���ʼ�Ϊϵͳ��Ϣ,�벻Ҫ���ظ�������</LI></TD></TR></TBODY></TABLE></DIV></BODY></HTML>";

	}
	
	private static String getContentCnam(String userid,String djno, String app1, String fdsca,String rdsca) {
		return "<HTML><HEAD><TITLE>��ͬ����ϵͳ</TITLE>"
				+ "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"
				+ "<STYLE type=text/css>BODY {FONT-SIZE: 9pt; MARGIN: 0px; OVERFLOW: auto; FONT-FAMILY: Verdana, Geneva, Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #ffffff}"
				+ "BODY {FONT-SIZE: 12px; COLOR: #0377C1; FONT-FAMILY: arial}"
				+ "TABLE {FONT-SIZE: 12px; COLOR: #0377C1; FONT-FAMILY: arial}"
				+ "DIV#default {BORDER-RIGHT: black 0px solid; PADDING-RIGHT: 10px; BORDER-TOP: black 0px solid; PADDING-LEFT: 10px; BACKGROUND: #fff; PADDING-BOTTOM: 20px; MARGIN: 10pt auto 20pt 20pt; BORDER-LEFT: black 0px solid; WIDTH: 700px; PADDING-TOP: 10px; BORDER-BOTTOM: black 0px solid; TEXT-ALIGN: left}"
				+ "</STYLE>"
				+ "<BODY>"
				+ "<DIV id=default><B>��ͬ����ϵͳ�ʼ���ʾ:</B><BR>"
				+ "<TABLE width=\"100%\">"
				+ "  <TBODY>"
				+ "  <TR>"
				+ "    <TD colSpan=2>"
				+ " <LI>��ã�����: <FONT color=red><U>"
				+ djno+ "</U></FONT> �ѳɹ��ʹﵽ "+app1+"��</LI></TD></TR></TR>"
				+ "<TR>"
				+ "<TD colSpan=2>"
				+ "<LI><FONT color=red><B>ע:</B></FONT> "
				+ "���ʼ�Ϊϵͳ��Ϣ,�벻Ҫ���ظ�������</LI></TD></TR></TBODY></TABLE></DIV></BODY></HTML>";

	}
	
	private static String getContentInfo(String userid,String djno,String rdsca) {
		return "<HTML><HEAD><TITLE>��ͬ����ϵͳ</TITLE>"
				+ "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"
				+ "<STYLE type=text/css>BODY {FONT-SIZE: 9pt; MARGIN: 0px; OVERFLOW: auto; FONT-FAMILY: Verdana, Geneva, Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #ffffff}"
				+ "BODY {FONT-SIZE: 12px; COLOR: #0377C1; FONT-FAMILY: arial}"
				+ "TABLE {FONT-SIZE: 12px; COLOR: #0377C1; FONT-FAMILY: arial}"
				+ "DIV#default {BORDER-RIGHT: black 0px solid; PADDING-RIGHT: 10px; BORDER-TOP: black 0px solid; PADDING-LEFT: 10px; BACKGROUND: #fff; PADDING-BOTTOM: 20px; MARGIN: 10pt auto 20pt 20pt; BORDER-LEFT: black 0px solid; WIDTH: 700px; PADDING-TOP: 10px; BORDER-BOTTOM: black 0px solid; TEXT-ALIGN: left}"
				+ "</STYLE>"
				+ "<BODY>"
				+ "<DIV id=default><B>��ͬ����ϵͳ�ʼ���ʾ:</B><BR>"
				+ "<TABLE width=\"100%\">"
				+ "  <TBODY>"
				+ "  <TR>"
				+ "    <TD colSpan=2>"
				+ " <LI>��ã�����: <FONT color=red><U>"
				+ djno+ "</U></FONT> �ֽ������ǩ��,������Ϊ����ʱ���˵������2����������֧������ע����ա��������ʣ�����ѯ�����ʽ�ƣ��绰82097!������Ϊ����ʱ���뾡�쵽�����ʽ�ư��?����������Ϊ0ʱ���˵��Ѱ����������</LI></TD></TR></TR>"
				+ "<TR>"
				+ "<TD colSpan=2>"
				+ "<LI><FONT color=red><B>ע:</B></FONT> "
				+ "���ʼ�Ϊϵͳ��Ϣ,�벻Ҫ���ظ�������</LI></TD></TR></TBODY></TABLE></DIV></BODY></HTML>";

	}
}
