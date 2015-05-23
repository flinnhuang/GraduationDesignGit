<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.question.dao.impl.zsgcQuestionDaoImpl"%>
<%@ page import="com.model.SYSTEMS0"%>
<%@ page import="com.main.daoimpl.*"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());    
zsgcQuestionDaoImpl zsgcqdi = (zsgcQuestionDaoImpl)wac.getBean("zsgcQuestionDaoImpl");
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

int qid = Integer.parseInt(request.getParameter("qid"));

Map row=zsgcqdi.getQuestionInfoByQidLcnum(qid,1);
	
String opt = request.getParameter("opt");
String opt1 = request.getParameter("opt1");

String id = row.get("ID").toString();
String QID = row.get("QID").toString();
String u = row.get("YHMC").toString();//反馈问题用户
String titl = row.get("TITLE").toString();//主题
String qsystem = row.get("SYSNAME").toString();//问题系统
String qtype = row.get("QTYPE").toString();//异常类型
int qdegree = Integer.parseInt(row.get("DEGREE").toString());//紧急程度
String qnote = row.get("QCONTENT").toString();//内容
String SUBMITSTATE = row.get("SUBMITSTATE").toString();
String REPLYSTATE = row.get("REPLYSTATE").toString();
String GRADE = row.get("GRADE").toString();
String ACCESSORY = row.get("ACCESSORY").toString();
String CREATETIME = row.get("CREATETIME").toString();
String LCNUM = row.get("CREATETIME").toString();
request.setAttribute("cont2", qnote);//保存到request中，提供到demo_.jsp中显示
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/css.css" type="text/css"></link>
<link rel="stylesheet" href="../../css/selectOption.css" type="text/css"></link>

<script type="text/javascript" src="../../jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="jquery/aq.js"></script>
<title>修改</title>

</head>
<body>
<div style="width: 100%;margin-left: 8px;margin-right: 8px;">
<form name="example" method="post" action="addQuestion.action" >
<table  width="100%" border="0" cellpadding="0" cellspacing="0" align="center" bgcolor="#FFFFFF">
	<tr>
	<td>
	反馈用户:
	<input type="text" readonly style="border-style: none;" name="qu.YHMC" id="mail" value="<%=u %>"/></td>
	</tr>
	<tr>
	<td align="left">反馈主题:&nbsp;<input id="titl" name="titl" type="text" value="<%=titl%>"/></td>
	</tr>
	<tr><td>
	问题系统:<b class="location001"></b><span class="selectlist">
	<input name="sysname" type="text" value="<%=qsystem%>" readonly="readonly"/>
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
	<input class="selectlist" name="typename" type="text" id="type" value="<%=qtype%>" readonly="readonly"/>
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
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;紧急程度：	
			<span class="td3">普通<input name="degree" type="radio" value="3" checked="checked"/></span>
			&nbsp<span class="td2">急<input name="degree" type="radio" value="2"/></span>
			&nbsp<span class="td1">特急<input name="degree" type="radio" value="1"/></span>
	
	</td></tr>
	<tr><td>
		<input type="hidden" name="note" id="note"/>
		<input type="hidden" name="opt" id="opt"/>
		<input type="hidden" name="opt1" id="opt1"/>
		
		<input type="hidden" name="contenttext" id="QCONTENT" value="<%=qnote%>"/>
		<input type="hidden" name="id" id="id" value="<%=id%>"/>
		<input type="hidden" name="qid" id="qid" value="<%=qid%>"/>
		<input type="hidden" name="LCNUM" id="LCNUM" value="<%=LCNUM%>"/>
		<input type="hidden" name="radiochecked" id="radiochecked" value="<%=qdegree%>"/>
		
		<input type="hidden" name="update" id="update" value="update"/>
	</td></tr>
	<tr>
	<td colspan="6" align="left">
	<jsp:include page="demo_.jsp" flush="true"/>
	</td>
	</tr>
<tr>
<td align="left">
<input id="onlysave" type="submit" name="button1" value="保存"/>
<input id="entersubmit" type="submit" name="button2" value="提交并保存"/>
<input id="back" type="button" name="button3" value="返回"
	onclick="window.close();parent.location.reload();"/>
</td>
</tr>
</table>
</form>
</div>

</body>
</html>