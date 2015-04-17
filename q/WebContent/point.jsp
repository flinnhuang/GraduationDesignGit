<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.gree.q.sys.dao.*"%>  
    <%
    request.setCharacterEncoding("ISO-8859-1");
    String qqid = request.getParameter("qqid");
    int st = Integer.parseInt(request.getParameter("st"));
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="refresh" content="2; URL=qm.jsp">
<title>Insert title here</title>
</head>
<body>
<% 
	QuestionDao qd = new QuestionDao();
	qd.updatePointById(st, qqid);
	if(st<3){
		%>
			<center><h1>亲，你的问题还没有解决吗？跟你什么怨什么仇，连3星好评都不给我！</h1>2秒后返回~</center>
		<%
	}else{
		%>
			<center><h1>亲，很高兴解决了你的问题！谢谢你的<font color="red"><%=st%></font>星好评！</h1>
			<h1>您的满意是我们最大的动力！</h1>2秒后返回~</center>
	    <%
	}

%>
</body>
</html>