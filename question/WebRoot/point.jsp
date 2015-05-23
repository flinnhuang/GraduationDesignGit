<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page import="com.question.dao.impl.functionmethod"%>
    <%
    request.setCharacterEncoding("utf-8");
    int qqid = Integer.parseInt(request.getParameter("qqid"));
    int st = Integer.parseInt(request.getParameter("st"));
    
    WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());    
	functionmethod fm = (functionmethod)wac.getBean("functionmethod");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="refresh" content="2; URL=abnormal_feedback.jsp">
<title>Insert title here</title>
</head>
<body>
<% 
	if(fm.updatePointById(qqid, st)){
		%>
			<center><h1>评分成功！</h1>2秒后退出~</center>
		<%
	}
		%>
</body>
</html>