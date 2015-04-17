<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gree.q.mainpage.mainDAOImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>系统信息页面</title>
<% 
	request.setCharacterEncoding("GBK");
    String dis=request.getParameter("dis");
    if(dis==null)dis="";
%>
		<link rel="stylesheet" type="text/css" href="css/css.css">
		<link rel="stylesheet" href="css/commons.css" type="text/css"></link>
		<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
		<script type="text/javascript" src="jquery/main.js"></script>
        <script type="text/javascript" src="easyui132/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="js/uploadfile.js"></script>
        <link rel="stylesheet" href="easyui132/themes/icon.css" type="text/css"></link>
        <link rel="stylesheet" href="easyui132/themes/gray/easyui.css" type="text/css"></link>	
<%
String u=(String)session.getAttribute("mail");
if(u==null){
	out.println("<br/><center><img alt='loginError' src='images/loginError.PNG'/></center>"+
	"<br/><center>会话时间已过期！</center>");
	return;
}
int l = (u.indexOf("@")<=0)?u.length():u.indexOf("@");
String umail = u.substring(0, l);
mainDAOImpl mdi = new mainDAOImpl();
int qall = mdi.getCount(umail, 0, true);//所反馈问题总数
int qnodo = mdi.getCount(umail, 1, false);//反馈问题还没处理
int qhaddo = mdi.getCount(umail, 0, false);//反馈问题已处理
int needdo = mdi.getCountByAdmin(umail, 1);//需要处理的问题
int haddo = mdi.getCountByAdmin(umail, 0);//已经处理的问题

%>
</head>
<body>

	<input type="hidden" name="dis" value="<%=dis%>"/>
	<div class="show" style="margin-top:100px;">
	<h1>您共反馈问题
	<a href="b.jsp"  onClick="document.getElementById('b').style.display='none';document.getElementById('c').style.display='none';auto();">
	<%=qall %></a>个，
	<a href="b.jsp"  onClick="document.getElementById('b').style.display='none';document.getElementById('c').style.display='none';auto();">
	<%=qhaddo %></a>个已得到处理，
	<a href="b.jsp"  onClick="document.getElementById('b').style.display='none';document.getElementById('c').style.display='none';auto();">
	<%=qnodo %></a>个尚未处理</h1>
	
	
	<%
	if(needdo != 0 ||haddo != 0){
	%>
	<h1>您有未处理问题
	<a href="b.jsp"  onClick="document.getElementById('b').style.display='none';document.getElementById('c').style.display='none';auto();">
	<%=needdo %></a>个，
	已处理问题<a href="b.jsp" onClick="document.getElementById('a').style.display='none';document.getElementById('c').style.display='none';auto1();">
	<%=haddo %></a>个
	<%
	}
	%>
	</div>
	

</body>
</html>