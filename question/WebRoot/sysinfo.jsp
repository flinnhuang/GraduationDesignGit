<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>系统信息页面</title>
<% 
	request.setCharacterEncoding("GBK");
    String dis=request.getParameter("dis");
    if(dis==null)dis="";
String u=(String)session.getAttribute("YHMC");
// if(u==null){
// 	out.println("<br/><center><img alt='loginError' src='images/loginError.PNG'/></center>"+
// 	"<br/><center>会话时间已过期！</center>");
// 	return;
// }
%>
</head>
<body>

	<input type="hidden" name="dis" value="<%=dis%>"/>
	<div class="show" style="margin-top:100px;">
	<h1>您共反馈问题
	<a href="abnormal_feedback.jsp"  onClick="document.getElementById('b').style.display='none';document.getElementById('c').style.display='none';auto();">
	xx</a>个，
	<a href="abnormal_feedback.jsp"  onClick="document.getElementById('b').style.display='none';document.getElementById('c').style.display='none';auto();">
	xx</a>个已得到处理，
	<a href="abnormal_feedback.jsp"  onClick="document.getElementById('b').style.display='none';document.getElementById('c').style.display='none';auto();">
	xx</a>个尚未处理</h1>

	</div>
	

</body>
</html>