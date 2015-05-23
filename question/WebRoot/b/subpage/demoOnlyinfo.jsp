<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.main.daoimpl.MainDaoimpl"%>
<%@ page import="com.question.dao.impl.zsgcQuestionDaoImpl"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>

<%
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
MainDaoimpl mdi = (MainDaoimpl)wac.getBean("MainDaoimpl");
zsgcQuestionDaoImpl zsgcqdi = (zsgcQuestionDaoImpl)wac.getBean("zsgcQuestionDaoImpl");

int qqid = Integer.parseInt(request.getParameter("qqid"));
System.out.println("-----------------qqid"+qqid);
Map row = zsgcqdi.getQuestionInfoByQidLcnum(qqid,1);
String qnote = mdi.getqrallinfo(qqid);//内容

if(row.get("error") != null || qnote.equals("") == true || qnote==null){
	out.println("<br/><center><img alt='loginError' src='images/loginError.PNG'/></center>"+
		"<br/><center>内容获取出错！</center>");
		return ;
}

String qmail = row.get("YHMC").toString();//反馈问题用户
String titl = row.get("TITLE").toString();//主题
String qsystem = row.get("SYSNAME").toString();//问题系统
String qtype = row.get("QTYPE").toString();//异常类型
int qdegree = Integer.parseInt(row.get("DEGREE").toString());//紧急程度


String degreeStr = "";
switch(qdegree){
		case 1: degreeStr = "特急";
				break;
		case 2: degreeStr = "急";
				break;
		case 3: degreeStr = "普通";
	}
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>info JSP</title>
	<link rel="stylesheet" href="../themes/default/default.css" />
	<link rel="stylesheet" href="../plugins/code/prettify.css" />
	<script charset="utf-8" src="../kindeditor.js"></script>
	<script charset="utf-8" src="../lang/zh_CN.js"></script>
	<script charset="utf-8" src="../plugins/code/prettify.js"></script>
	
</head>
<body>
<div align="left" id = "infodiv">
<table>
	<tr>
		<td><b style="color:#3BC9B1;">反馈用户:</b></td><td><%=qmail%></td><td>&nbsp;</td><td>&nbsp;</td>
		<td><b style="color:#3BC9B1;">提交主题:</b></td><td><%=titl%></td><td>&nbsp;</td><td>&nbsp;</td>
		<td><b style="color:#3BC9B1;">问题系统:</b></td><td><%=qsystem%></td>
	</tr>
	<tr>
		<td><b style="color:#3BC9B1;">提交类型:</b></td><td><%=qtype%></td><td>&nbsp;</td><td>&nbsp;</td>
		<td><b style="color:#3BC9B1;">紧急程度:</b></td><td><%=degreeStr%></td>
	</tr>
	
</table>
<br>
<b style="color:#3BC9B1;">内容:</b><br>
<%=qnote%><br>

</div>
</body>
</html>
