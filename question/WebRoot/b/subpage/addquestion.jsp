<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page import="com.model.SYSTEMS0"%>
<%@ page import="com.main.daoimpl.*"%>
<%

String titl = request.getParameter("titl");
if(titl == null) titl = "";

WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());    
MainDaoimpl mdi = (MainDaoimpl)wac.getBean("MainDaoimpl"); 
List<SYSTEMS0> Typelist = mdi.getSyslist();
String sys001 = "";
if(Typelist == null){
	sys001 = "数据库错误，没有问题系统数据";
}else{
	SYSTEMS0 s001 = (SYSTEMS0)Typelist.get(0);
	sys001 = s001.getSYSNAME();//添加页面，用于在问题系统input元素中赋值初始value
	if(sys001 == null || sys001.equals("")) sys001 = "数据库错误，没有问题系统数据";
}

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/css.css" type="text/css"></link>
<link rel="stylesheet" href="../../css/selectOption.css" type="text/css"></link>
<script type="text/javascript" src="../../jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="jquery/aq.js"></script>
<title>添加问题</title>

<%
 	String u=(String)session.getAttribute("YHMC");
	//if(u==null){
	//	out.println("<br/><center><img alt='loginError' src='images/loginError.PNG'/></center>"+
	//	"<br/><center>会话时间已过期！</center>");
	//	return;
	//}
%>
</head>
<body>
<div style="width: 100%;margin-left: 8px;margin-right: 8px;">
<form name="example" method="post" action="addQuestion.action" >
<table  width="100%" border="0" cellpadding="0" cellspacing="0" align="center" bgcolor="#FFFFFF">
	<tr>
	<td>
	反馈用户:
	<input type="text" readonly style="border-style: none;" name="qu.YHMC" id="yhmc" value="<%=u%>"/></td>
	</tr>
	<tr>
	<td width="20%">反馈主题:&nbsp;<input id="titl" name="qu.TITLE" type="text" value="<%=titl%>"/></td>
	</tr>
	<tr><td>
	问题系统:<b class="location001"></b><span class="selectlist">
	<input name="qu.SYSNAME" type="text" value="<%=sys001%>" readonly="readonly"/>
	<div id="select_option1" class="option1">
		<ul style="margin-left: -40px;margin-top: -1px;" >
		<%
		if(Typelist.size()>0){
			for(int i = 0;i<Typelist.size();i++){
				SYSTEMS0 st = (SYSTEMS0)Typelist.get(i);
		%>
			<li class="td001" style="font-size: 13px;cursor: pointer;" onMouseMove="style.backgroundColor='#B4DBFE'"
			onMouseOut="style.backgroundColor='#f3f2f2'"><%=st.getSYSNAME()%></li>
		<% 
			}
		}%>
		</ul>
	</div></span>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	问题分类：<b class="location001"></b><span class="selectlist">
	<input name="qu.QTYPE" type="text" id="type" value="异常反馈" readonly="readonly"/>
	<div id="select_option1" class="option1">
		<ul style="margin-left: -40px;margin-top: -1px;">
			<li class="td001" style="font-size: 13px;cursor: pointer;" onMouseMove="style.backgroundColor='#B4DBFE'"
			onMouseOut="style.backgroundColor='#f3f2f2'">异常反馈</li>
			<li class="td001" style="font-size: 13px;cursor: pointer;" onMouseMove="style.backgroundColor='#B4DBFE'"
			onMouseOut="style.backgroundColor='#f3f2f2'">功能改进</li>
			<li class="td001" style="font-size: 13px;cursor: pointer;" onMouseMove="style.backgroundColor='#B4DBFE'"
			onMouseOut="style.backgroundColor='#f3f2f2'">跟踪处理</li>
		</ul>
	</div>
	</span>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>紧急程度：	</span>
			<span class="td3">普通<input name="qu.DEGREE" type="radio" value="3" checked="checked" style="border: 0;"/></span>
			&nbsp;<span class="td2">急<input name="qu.DEGREE" type="radio" value="2" style="border: 0;"/></span>
			&nbsp;<span class="td1">特急<input name="qu.DEGREE" type="radio" value="1" style="border: 0;"/></span>
	</td></tr>
	<tr><td>
		<input type="hidden" name="qu.QCONTENT" id="QCONTENT" value="问题内容"/>
	</td></tr>
	<tr>
	<td colspan="6" align="left">
	<jsp:include page="demo_.jsp" flush="true"/>
	</td>
	</tr>
<tr>
<td align="left">
<input id="onlysave" type="button" name="button1" value="保存"/>
<input id="entersubmit" type="button" name="button2" value="提交并保存"/>
<input id="back" type="button" name="button3" value="返回" 
	onclick="window.close();parent.location.reload();"/>
</td>
</tr>
</table>
</form>
</div>
</body>
</html>