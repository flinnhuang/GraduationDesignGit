<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reply.dao.impl.zsgcReplyDaoImpl"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());    
zsgcReplyDaoImpl zsgcrdi = (zsgcReplyDaoImpl)wac.getBean("zsgcReplyDaoImpl");

String opt = request.getParameter("opt");

int qid = Integer.parseInt(request.getParameter("qqid"));
System.out.println("---回复页面----qid-"+qid);
Map row = zsgcrdi.getInfoforhuifu(qid);
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

String qnote = row.get("QCONTENT").toString();//最新回复内容
request.setAttribute("cont2", qnote);//保存到request中，提供到demo_.jsp中显示

int qlcnum = Integer.parseInt(row.get("QLCNUM").toString());//提问问题的楼层
String submittime = row.get("SUBMITTIME").toString();
request.setAttribute("submittime", submittime);//保存到request中，提供到demo_.jsp中显示

String degreeStr = "";
switch(qdegree){
		case 1: degreeStr = "特急";
				break;
		case 2: degreeStr = "急";
				break;
		case 3: degreeStr = "普通";
	}

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/aq.css" type="text/css"></link>
<link rel="stylesheet" type="text/css" href="../../css/css.css">
<script type="text/javascript" src="../../jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="jquery/replyquestion.js"></script>
<title>回复页面</title>

</head>
<body>
<div style="width: 100%;margin-left: 8px;margin-right: 8px;">
<form name="example" method="post" action="replyQuestion.action" >
<table  width="100%" border="0" cellpadding="3" cellspacing="0" align="center" bgcolor="#FFFFFF">
	<tr>
	<td>
	反馈用户:<%=u%></td>
	</tr>
	<tr>
	<td width="20%">反馈主题:<%=titl%></td>
	</tr>
	<tr><td>
	问题系统:<%=qsystem%>
	&nbsp&nbsp&nbsp&nbsp问题分类：<%=qtype%>
	&nbsp&nbsp&nbsp&nbsp&nbsp紧急程度：<span class="td<%=qdegree %>"><%=degreeStr %>
	</span>
	</td></tr>
	<tr><td>
		<input type="hidden" name="note" id="note"/>
		<input type="hidden" name="opt" id="opt"/>
		<input type="hidden" name="qid" id="qid" value="<%=qid%>"/>
		<input type="hidden" name="yhmc" id="yhmc" value="<%=u %>"/>
		<input type="hidden" name="re.RCONTENT" id="RCONTENT"/>
		<input type="hidden" name="re.LCNUM" id="LCNUM" value="<%=qlcnum%>"/>
	</td></tr>
	<tr>
	<td colspan="6" align="left">
	<jsp:include page="replydemo_.jsp" flush="true"/>
	</td>
	</tr>
<tr>
<td align="left">
<input id="huifusubmit" type="button" name="button" value="确认回复"/>
<input id="back" type="button" name="button" value="返回"
	onclick="window.close();parent.location.reload();"/>
</tr>

</table>
</form>
</div>
</body>
</html>