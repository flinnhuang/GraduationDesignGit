<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.gree.q.util.DateTime"%>
<%@page import="java.text.SimpleDateFormat"%>
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
String u=(String)session.getAttribute("mail");
String umail = u.substring(0, u.indexOf("@"));

String qid = request.getParameter("qqid");
//String qid ="180191";
List list = mpmo.getInfo(qid);
/*if(qid==null||qid==""){
	out.print("<script language='javascript' type='text/javascript'> "
			+"alert('出现错误，不存在此记录！');closeadd();"
			+"</script> ");
}*/
Iterator iter = list.iterator();
Map row = null;
while(iter.hasNext()){
	row = (Map)iter.next();
}

String qmail = row.get("q_mail").toString();
String titl = row.get("q_titl").toString();
String qsystem = row.get("q_system").toString();
String qtype = row.get("q_type").toString();
String subTime = row.get("q_submittime").toString();
int qdegree = Integer.parseInt(row.get("q_degree").toString());
String qnote = row.get("q_note").toString();
request.setAttribute("cont2", qnote);//保存到request中，提供到demo.jsp中显示

String degreeStr = "";
switch(qdegree){
		case 1: degreeStr = "特急";
				break;
		case 2: degreeStr = "急";
				break;
		case 3: degreeStr = "普通";
	}
Map mpdaoimpl001=null;
int result=0;
if("doSave".equals(opt)){
	System.out.println("doSave---"+opt+"----qid="+qid);
	mpdaoimpl001=new HashMap(); 
	Date nowdate = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	String nowtime = sdf.format(nowdate);//获取保存时间

	mpdaoimpl001.put("q_id",qid);	//流水号
	String str1 = new String(request.getParameter("content1").getBytes("ISO-8859-1"), "UTF-8");
	System.out.println("------------------->>>>>"+str1);
	String content = qnote+"<br/>A("+nowtime+"):<br/>"+str1+"<br/>";
	//content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
	mpdaoimpl001.put("q_note",content);//回复内容
	mpdaoimpl001.put("q_backtime",nowtime);//负责人回复时间
	System.out.println(">>>>>"+content);
	mpdaoimpl001.put("q_accessories",0);//附件，暂时不做
	
	//System.out.println("aqForb.jsp页面负责人处理回复钮测试------------------");
	int ii = mpmo.UpdateTable(mpdaoimpl001);
	if(ii > 0){
	//	System.out.println("提交保存成功，返回值="+ii);
		MailUtil mailutil = new MailUtil();
		
		String notes = "已由管理员"+umail+"处理完成";
		mailutil.sendmail(qmail, titl, notes, qdegree);
		out.print("<script language='javascript' type='text/javascript'> "
					+"window.close();"
					+"parent.location.reload();"
					+"</script> ");
		}
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/aq.css" type="text/css"></link>
<link rel="stylesheet" type="text/css" href="../../css/css.css">
<script type="text/javascript" src="../../js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="jquery/aq.js"></script>
<title>Insert title here</title>

</head>
<body>
<div style="width: 100%;margin-left: 8px;margin-right: 8px;">
<form name="example" method="post" action="aqForb.jsp" >
<table  width="100%" border="0" cellpadding="3" cellspacing="0" align="center" bgcolor="#FFFFFF">
	<tr>
	<td>
	反馈用户:<%=qmail%></td>
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
	提交时间：<%=subTime %>
	</td></tr>
	<tr><td>
		<input type="hidden" name="note" id="note"/>
		<input type="hidden" name="opt" id="opt"/>
		<input type="hidden" name="qqid" id="qid" value="<%=qid%>"/>
	</td></tr>
	<tr>
	<td colspan="6" align="left">
	<jsp:include page="demo.jsp" flush="true"/>
	</td>
	</tr>
<tr>
<td align="left">
<input id="huifusubmit" type="submit" name="button" value="确认回复"/>
<input id="back" type="button" name="button" value="返回"
	onclick="window.close();parent.location.reload();"/>
</tr>

</table>
</form>
</div>
</body>
</html>