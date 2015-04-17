<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.gree.q.util.LdapUtil"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gree.q.sys.mo.Cbase000MO"%>
<%@ page import="com.gree.q.sys.vo.SysType"%>
<%@ page import="com.gree.q.sys.mo.Aqus001MO"%>
<%@ page import="com.gree.q.util.MailUtil"%>

<%@ page import="com.gree.q.aq.aqDAOImpl"%>
<%
Aqus001MO aq001mo = Aqus001MO.newInstance();
request.setCharacterEncoding("ISO-8859-1");

String titl = request.getParameter("titl");
if(titl == null) titl = "";
titl = new String(titl.getBytes("ISO-8859-1"), "UTF-8");

aqDAOImpl qd = new aqDAOImpl();   
List Typelist = qd.getAllTypeList();

SysType s001 = (SysType)Typelist.get(0);
String sys001 = s001.getS_system();//添加页面，用于在问题系统input元素中赋值初始value
if(sys001 == null || sys001.equals("")) sys001 = "数据库错误，没有问题系统数据";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/css.css" type="text/css"></link>
<link rel="stylesheet" href="../../css/selectOption.css" type="text/css"></link>
<script type="text/javascript" src="../../js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="jquery/aq.js"></script>
<title>Insert title here</title>

<%
 	String u=(String)session.getAttribute("mail");
	String umail = u.substring(0, u.indexOf("@"));
%>
</head>
<body>
<div style="width: 100%;margin-left: 8px;margin-right: 8px;">
<form name="example" method="post" action="aqUpdateMethor.jsp" >
<table  width="100%" border="0" cellpadding="0" cellspacing="0" align="center" bgcolor="#FFFFFF">
	<tr>
	<td>
	反馈用户:
	<input type="text" readonly style="border-style: none;" name="mail" id="mail" value="<%=umail%>"/></td>
	</tr>
	<tr>
	<td width="20%">反馈主题:&nbsp;<input id="titl" name="titl" type="text" value="<%=titl%>"/></td>
	</tr>
	<tr><td>
	问题系统:<b class="location001"></b><span class="selectlist">
	<input name="systype" type="text" value="<%=sys001%>" readonly="readonly"/>
	<div id="select_option1" class="option1">
		<ul style="margin-left: -40px;margin-top: -1px;" >
		<%
		if(Typelist.size()>0){
			for(int i = 0;i<Typelist.size();i++){
				SysType st = (SysType)Typelist.get(i);
		%>
			<li class="td001" style="font-size: 13px;cursor: pointer;" onMouseMove="style.backgroundColor='#B4DBFE'"
			onMouseOut="style.backgroundColor='#f3f2f2'"><%=st.getS_system()%></li>
		<% 
			}
		}%>
		</ul>
	</div></span>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	问题分类：<b class="location001"></b><span class="selectlist">
	<input class="selectlist" name="type" type="text" id="type" value="异常反馈" readonly="readonly"/>
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
			<span class="td3">普通<input name="degree" type="radio" value="3" checked="checked" style="border: 0;"/></span>
			&nbsp;<span class="td2">急<input name="degree" type="radio" value="2" style="border: 0;"/></span>
			&nbsp;<span class="td1">特急<input name="degree" type="radio" value="1" style="border: 0;"/></span>
	</td></tr>
	<tr><td>
		<input type="hidden" name="note" id="note"/>
		<input type="hidden" name="opt" id="opt"/>
		<input type="hidden" name="opt1" id="opt1"/>
		<input type="hidden" name="isupdate" id="isupdate" value="insert"/>
	</td></tr>
	<tr>
	<td colspan="6" align="left">
	<jsp:include page="demo.jsp" flush="true"/>
	</td>
	</tr>
<tr>
<td align="left">
<input id="onlysave" type="submit" name="button1" value="保存"/>
<input id="entersubmit" type=submit name="button2" value="提交并保存"/>
<input id="back" type="button" name="button3" value="返回" 
	onclick="window.close();parent.location.reload();"/>
</td>
</tr>
</table>
</form>
</div>
</body>
</html>