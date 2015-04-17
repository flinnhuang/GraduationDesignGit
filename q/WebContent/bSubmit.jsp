<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gree.q.util.MailUtil"%>
<%@ page import="com.gree.q.aq.aqDAOImpl"%>
<%@ page import="com.gree.q.sys.mo.MpMo"%>
<%
MailUtil mailutil = new MailUtil();
MpMo mpmo = MpMo.newInstance();
String u=(String)session.getAttribute("mail");
int l = (u.indexOf("@")<=0)?u.length():u.indexOf("@");
String qmail = u.substring(0, l);

String qid = request.getParameter("qid");
String titl="";
String tosendmail="";
String de = "";
if(qid =="" ||qid == null){
	out.print("<script language='javascript' type='text/javascript'> "
			+"alert('错误！！问题id为空！');"
			+"history(-1);"
			+"</script> ");
}else{
	titl = mpmo.getItem(qid,"Q_TITL");
	tosendmail = mpmo.getItem(qid,"Q_MAIL");
	de = mpmo.getItem(qid,"Q_DEGREE");
		
	System.out.println("+++++++++++++++++++++++++qid="+qid+" titl="+titl+" tosendmail="+tosendmail);
	//if(qid == null) qid = "";

	//sendMail = mpmo.getMail(qid);
	String notes = "用户"+qmail+"已处理！";

	mailutil.sendmail(tosendmail, titl, notes, Integer.parseInt(de));

	out.print("<script language='javascript' type='text/javascript'> "
			+"alert('提交成功，系统将发送邮件给相应负责人处理！请等待邮件回复！');"
			+"window.location.href='b.jsp'"
			+"</script> ");
}


%>