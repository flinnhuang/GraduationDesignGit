<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.question.dao.impl.zsgcQuestionDaoImpl"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page import="java.util.*"%>

<%
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());    
zsgcQuestionDaoImpl zsgcqdi = (zsgcQuestionDaoImpl)wac.getBean("zsgcQuestionDaoImpl");

String opt = request.getParameter("opt");
String opt1 = request.getParameter("opt1");

int qid = Integer.parseInt(request.getParameter("qqid"));

Map row = zsgcqdi.getInfoforZhuijia(qid);
if(row.get("error") != null){
	out.println("<br/><center><img alt='loginError' src='images/loginError.PNG'/></center>"+
		"<br/><center>"+row.get("error")+"</center>");
		return ;
}

String u = row.get("YHMC").toString();//反馈问题用户
String titl = row.get("TITLE").toString();//主题
String qsystem = row.get("SYSNAME").toString();//问题系统
String qtype = row.get("QTYPE").toString();//异常类型
int qdegree = Integer.parseInt(row.get("DEGREE").toString());//紧急程度
String qnote = row.get("RCONTENT").toString();//最新回复内容
request.setAttribute("cont", qnote);//保存到request中，提供到demo_.jsp中显示
int rlcnum = Integer.parseInt(row.get("RLCNUM").toString());
String replytime = row.get("REPLYTIME").toString();
request.setAttribute("replytime", replytime);//保存到request中，提供到demo_.jsp中显示
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/css.css" type="text/css"></link>
<link rel="stylesheet" href="../../css/selectOption.css" type="text/css"></link>

<script type="text/javascript" src="../../jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="jquery/zhuijiaaq.js"></script>
<title>问题追加</title>

</head>
<body>
<div style="width: 100%;margin-left: 8px;margin-right: 8px;">
<form name="example" method="post" action="zhuijiaQuestion.action" >
<table  width="100%" border="0" cellpadding="0" cellspacing="0" align="center" bgcolor="#FFFFFF">
	<tr>
	<td>
	反馈用户:
	<input type="text" readonly style="border-style: none;" name="mail" id="mail" value="<%=u %>"/></td>
	</tr>
	<tr>
	<td align="left">反馈主题:<input id="titl" name="titl" type="text" readonly value="<%=titl%>"/></td>
	</tr>
	<tr><td>
	问题系统:<input name="sysname" type="text" readonly value="<%=qsystem%>"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	问题分类：<input class="selectlist" name="typename" type="text" id="type" readonly value="<%=qtype%>"/>
	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp紧急程度：
			<span class="td3">普通<input name="degree" type="radio" value="3" checked="checked" disabled style="border: 0;"/></span>
			&nbsp<span class="td2">急<input name="degree" type="radio" value="2" disabled style="border: 0;"/></span>
			&nbsp<span class="td1">特急<input name="degree" type="radio" value="1" disabled style="border: 0;"/></span>
	
	</td></tr>
	<tr><td>
		<input type="hidden" name="opt" id="opt"/>
		<input type="hidden" name="opt1" id="opt1"/>
		<input type="hidden" name="qid" id="qid" value="<%=qid%>"/>
		<input type="hidden" name="rlcnum" id="rlcnum" value="<%=rlcnum%>"/>
		<input type="hidden" name="contenttext" id="QCONTENT"/>
		<input type="hidden" name="radiochecked" id="radiochecked" value="<%=qdegree%>"/>
	</td></tr>
	<tr>
	<td colspan="6" align="left">
	<jsp:include page="demo_.jsp" flush="true"/>
	</td>
	</tr>
<tr>
<td align="left">
<input id="entersubmit" type="button" name="button" value="提交"/>
<input id="back" type="button" name="button" value="返回" 
onclick="window.close();parent.location.reload();"/>
</td>
</tr>
</table>
</form>
</div>

</body>
</html>