<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.gree.q.util.DateTime"%>
<%@ page import="com.gree.q.util.LdapUtil"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gree.q.sys.mo.MpMo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
MpMo mpmo = MpMo.newInstance();

//用户验证 
	request.setCharacterEncoding("ISO-8859-1");
    String opt=request.getParameter("opt");
    String dis=request.getParameter("dis");
   // String doSubmit=request.getParameter("doSubmit");
    if(dis==null)dis="";  
   // if(doSubmit==null)doSubmit="";
    String u=(String)session.getAttribute("mail");
    int l = (u.indexOf("@")<=0)?u.length():u.indexOf("@");
	String qmail = u.substring(0, l);
%>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>异常处理页面</title>
	<link rel="stylesheet" type="text/css" href="css/css.css">
	<link rel="stylesheet" href="css/commons.css" type="text/css"></link>
	<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="jquery/b.js"></script>
	<script type="text/javascript" src="easyui132/jquery.easyui.min.js"></script>
	<link rel="stylesheet" href="easyui132/themes/icon.css" type="text/css"></link>
	<link rel="stylesheet" href="easyui132/themes/gray/easyui.css" type="text/css"></link>
  
	<style>
		.navPoint {
		COLOR:#FF0000; CURSOR: hand; FONT-FAMILY: Webdings; FONT-SIZE: 9pt
		}
		body {
		margin-left: 0px;
		margin-top: 10px;
		margin-right: 0px;
		margin-bottom: 0px;
		}
	</style>
	</head>
	<body scroll=no style="MARGIN: 0px">
	<form  action="b.jsp" method="post" name="frm" id="frm">
		<input type="hidden" name="dis" value="<%=dis%>"/>
        <input type="hidden" name="opt" value=""/>
		<table border="0" width="100%" class="table" cellspacing="0"
			cellpadding="0">
			
<%
//分页
int rowpage=0;//每页显示行数
if(request.getParameter("rowpage")==null){
	rowpage=15;
}else{
	try{
		rowpage=new Integer(request.getParameter("rowpage")).intValue();
	}catch(Exception ex){
		rowpage=15;
	}
}
if(rowpage<15)//每页最少显示15行
	rowpage=15;

int noncepage=0;
if(request.getParameter("noncepage")==null){
	noncepage=1;
}else{
	try{
		noncepage=new Integer(request.getParameter("noncepage")).intValue();
	}catch(Exception ex){
		noncepage=1;
	}
}
if(noncepage<1)
	noncepage=1;
%>			
<%
	String searchtype = request.getParameter("searchType");
    String filter=request.getParameter("rowpage2");
	if(filter == null) filter = "";
	try{
		filter = new String(filter.getBytes("ISO-8859-1"), "UTF-8").trim();
		System.out.println("-----------------页面中得到的filter="+filter);
	}catch(Exception ex){
		ex.printStackTrace();
	}

	
	int count=mpmo.Conut(qmail,filter,searchtype);
	if(count<0)count=0;
	int allpages=0;
	if(count%rowpage==0)
		allpages=count/rowpage;
	else
		allpages=count/rowpage+1;
	if(allpages<1)
		allpages=1;
	if(noncepage>allpages)
		noncepage=allpages;	
%>
	
<tr>
<td colspan="9">
<div id="a" class="divstyle" style="display:none">
<table width="100%" height="100%" border="0" cellpadding="0" align="left">
		<tr>
			<td colspan="5">&nbsp;	</td>
		<tr>
			<td align="left">搜索类别：</td>
			<td align="left">
				主题：<input type="radio" name = "searchType" id="searchType" value="Q_TITL" checked="checked"/>&nbsp;|&nbsp;
				提交用户：<input type="radio" name = "searchType" id="searchType" value="Q_MAIL"/>&nbsp;|&nbsp;
				问题系统：<input type="radio" name = "searchType" id="searchType" value="Q_SYSTEM"/>&nbsp;|&nbsp;
				系统分类：<input type="radio" name = "searchType" id="searchType" value="Q_TYPE"/>&nbsp;|&nbsp;
				紧急程度：<input type="radio" name = "searchType" id="searchType" value="Q_DEGREE"/>&nbsp;|&nbsp;
			</td>
			<td></td>
			<td></td>
		</tr>
	</table>
</div>
				</td>
			</tr>
</table>

<table border="0" cellspacing="0" cellpadding="0" width="100%">
<tr>
    <td width="350">
    <table border="0" cellspacing="0" cellpadding="0" width="100%">
       <tr>
   <td height="30" align="center" >&nbsp;</td>
   <td width="150" height="30" align="center" valign="middle" >
     <input name="rowpage2" id = "rowpage2" type="text" value="<%=filter%>"/>
   </td>
   <td height="30" >&nbsp;
   <a href="#" class="subm"><img src="images/search.png" title="搜索"></a>&nbsp;
   &nbsp;
    <a href="#"  onClick="auto();"><img src="images/zhankai.png" title="展开"></a>&nbsp;&nbsp;&nbsp;
    
    <a href="b/jsp/aq.jsp"  onClick="auto();"><img src="images/add.png" title="添加"></a>&nbsp;&nbsp;&nbsp;
    <a href="#"  id="delete"><img src="images/delete.png" title="删除"></a></td>
   </tr>
  </table>
								
	</td>
			<td align="right" valign="middle" style="border:0px;">&nbsp;&nbsp;
		共<font color="red"><%=count%></font>行
		/&nbsp;
		每页<input name="rowpage" id="rowpage" type="text" class="text_style30" value="<%=rowpage%>" size="3"/>
		行
		&nbsp;&nbsp;&nbsp;&nbsp;
		共<font color="red"><%=allpages%></font>页
		/&nbsp;
		当前第<input name="noncepage" id="noncepage" type="text" class="text_style30" value="<%=noncepage%>" size="5"/>
		页
	
<%
		if(count<rowpage){
%>
		&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;
<%
		}else if(noncepage==1){
%>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="#" class="next">下一页</a>
<%		
		}else if(noncepage==allpages){
%>
		<a href="#" class="prev">上一页</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
<%
		}else{
%>
		<a href="#" class="prev">上一页</a>
		<a href="#" class="next">下一页</a>
		
<%	
		}
%>	
		<a href="#" class="subm"><img src="images/refresh.png" style="margin-bottom:-4px;margin-right:5px;margin-left:5px;" title="刷新" ></a>
</td>
</tr>	
</table>
</body>
</html>