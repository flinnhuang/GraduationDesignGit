<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gree.q.sys.mo.MpMo"%>
<%
request.setCharacterEncoding("gb2312");
String qqid = request.getParameter("qqid");

MpMo mpmo = MpMo.newInstance();
List list = mpmo.getInfo(qqid);
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
String qsubmittime = row.get("q_submittime").toString();
String qbacktime = row.get("q_backtime")!=null?row.get("q_backtime").toString():"还没有回复哦~";

//String titl = mpmo.getItem(qqid, "q_titl");
if(qnote == null || qnote == ""){
	qnote = "内容为空！";
}
String degreeStr = "";
switch(qdegree){
		case 1: degreeStr = "特急";
				break;
		case 2: degreeStr = "急";
				break;
		case 3: degreeStr = "普通";
	}
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>info JSP</title>
	<link rel="stylesheet" href="../themes/default/default.css" />
	<link rel="stylesheet" href="../plugins/code/prettify.css" />
	<script charset="utf-8" src="../kindeditor.js"></script>
	<script charset="utf-8" src="../lang/zh_CN.js"></script>
	<script charset="utf-8" src="../plugins/code/prettify.js"></script>
	
</head>
<body>
<div align="left" id = "infodiv">
<lable>反馈用户:</lable>
<%=qmail%><br><br>
<lable>提交主题:</lable>
<%=titl%><br><br>
<lable>问题系统:</lable>
<%=qsystem%><br><br>
<lable>提交类型:</lable>
<%=qtype%><br><br>
<lable>紧急程度:</lable>
<%=degreeStr%><br><br>
<lable>提交时间:</lable>
<%=qsubmittime%><br><br>
<lable>提交内容:</lable><br>
<%=qnote%><br>
<lable>最后回复时间:</lable>
<%=qbacktime%><br><br>
<hr/>

</div>
</body>
</html>
