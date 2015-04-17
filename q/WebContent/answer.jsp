<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.gree.q.util.DateTime"%>
<%@ page import="com.gree.q.util.LdapUtil"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gree.q.sys.dao.*"%>
<%@ page import="com.gree.q.sys.vo.*"%>
<%@ page import="com.gree.q.sys.mo.Aqus001MO"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<% 
	
	request.setCharacterEncoding("gb2312");
	String titl = request.getParameter("titl");

	  
	%>
   <%
  //  QuestionDao qd = new QuestionDao();   
	//List list = qd.getAllQuestionListByKeyWord("n");
	//if(list.size()>0){
	//for(int i = 0;i<list.size();i++){
	//QuestionVO qvo = (QuestionVO)list.get(i);
	%>
	
	<ul>
	<li> <%=titl%></li>
	</ul>
	


</body>
</html>