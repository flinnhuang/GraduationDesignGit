<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.gree.q.util.DateTime"%>
<%@ page import="com.gree.q.util.LdapUtil"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gree.q.sys.mo.Cbase000MO"%>
<%@ page import="com.gree.q.sys.vo.SysType"%>
<%@ page import="com.gree.q.sys.mo.Aqus001MO"%>
<%@ page import="com.gree.q.util.MailUtil"%>
<%@ page import="com.gree.q.sys.mo.MpMo"%>
<%@ page import="com.gree.q.aq.aqDAOImpl"%>
<%
request.setCharacterEncoding("ISO-8859-1");
MpMo mpmo = MpMo.newInstance();

String opt = request.getParameter("opt");
String opt1 = request.getParameter("opt1");

String qid = request.getParameter("qqid");
List list = mpmo.getInfo(qid);
Iterator iter = list.iterator();
Map row = null;
while(iter.hasNext()){
	row = (Map)iter.next();
}

String qmail = row.get("q_mail").toString();//反馈问题用户
String titl = row.get("q_titl").toString();//主题
String qsystem = row.get("q_system").toString();//问题系统
String qtype = row.get("q_type").toString();//异常类型
int qdegree = Integer.parseInt(row.get("q_degree").toString());//紧急程度
String qnote = row.get("q_note").toString();//之前保存的回复内容
request.setAttribute("cont2", qnote);//保存到request中，提供到demo.jsp中显示

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/css.css" type="text/css"></link>
<link rel="stylesheet" href="../../css/selectOption.css" type="text/css"></link>

<script type="text/javascript" src="../../js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="jquery/aq.js"></script>
<title>问题追加</title>

</head>
<body>
<div style="width: 100%;margin-left: 8px;margin-right: 8px;">
<form name="example" method="post" action="aqUpdateMethor.jsp" >
<table  width="100%" border="0" cellpadding="0" cellspacing="0" align="center" bgcolor="#FFFFFF">
	<tr>
	<td>
	反馈用户:
	<input type="text" readonly style="border-style: none;" name="mail" id="mail" value="<%=qmail%>"/></td>
	</tr>
	<tr>
	<td align="left">反馈主题:<input id="titl" name="titl" type="text" readonly value="<%=titl%>"/></td>
	</tr>
	<tr><td>
	问题系统:<input name="systype" type="text" readonly value="<%=qsystem%>"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	问题分类：<input class="selectlist" name="type" type="text" id="type" readonly value="<%=qtype%>"/>
	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp紧急程度：
			普通<input name="degree" type="radio" value="3" checked="checked" readonly style="border: 0;"/>
			&nbsp急<input name="degree" type="radio" value="2" readonly style="border: 0;"/>
			&nbsp特急<input name="degree" type="radio" value="1" readonly style="border: 0;"/>
	
	</td></tr>
	<tr><td>
		<input type="hidden" name="note" id="note"/>
		<input type="hidden" name="opt" id="opt"/>
		<input type="hidden" name="opt1" id="opt1"/>
		<input type="hidden" name="isupdate" id="isupdate" value="isupdate"/>
		<input type="hidden" name="zj" id="zj" value="zhuijia"/>
		<input type="hidden" name="qqid" id="qid" value="<%=qid%>"/>
		<input type="hidden" name="radiochecked" id="radiochecked" value="<%=qdegree%>"/>
	</td></tr>
	<tr>
	<td colspan="6" align="left">
	<jsp:include page="demo.jsp" flush="true"/>
	</td>
	</tr>
<tr>
<td align="left">
<input id="onlysave" type="submit" name="button" value="保存"/>
<input id="entersubmit" type="submit" name="button" value="提交并保存"/>
<input id="back" type="button" name="button" value="返回" 
onclick="window.close();parent.location.reload();"/>
</td>
</tr>
</table>
</form>
</div>

</body>
</html>