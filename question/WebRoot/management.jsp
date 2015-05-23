<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,java.security.MessageDigest,java.math.BigInteger,javax.servlet.http.Cookie,java.text.SimpleDateFormat"%>
<%@ page import="com.model.QUESTION"%>
<%@ page import="com.main.daoimpl.management"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());    
management mg = (management)wac.getBean("management");

//用户验证 
	request.setCharacterEncoding("UTF-8");
    String opt=request.getParameter("opt");
    String dis=request.getParameter("dis");
    if(dis==null)dis="";
    
   	String u=(String)session.getAttribute("YHMC");
	if(u==null){
		out.println("<br/><center><img alt='loginError' src='images/loginError.PNG'/></center>"+
		"<br/><center>会话时间已过期！</center>");
		return ;
	}
	String qx = (String)session.getAttribute("qx");
	if(!qx.equals("admin")){
		out.println("<br/><center><img alt='loginError' src='images/loginError.PNG'/></center>"+
		"<br/><center>权限不足！您不能进入此页面！</center>");
		return ;
	}
	String radiochecked=request.getParameter("radiochecked");
	if(radiochecked == null ||radiochecked.equals("")){
		radiochecked = "su.YHMC";
	}
	String table = "";
	if(radiochecked.equals("s.SYSNAME")){
		table = "SYSTEMS0";
	}else{
		table = "SYSUSER0";
	}
	System.out.println("----------radiochecked"+radiochecked);
	System.out.println("----------table"+table);
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>系统管理</title>
		<link rel="stylesheet" type="text/css" href="css/css.css">
		<link rel="stylesheet" href="css/commons.css" type="text/css"></link>
		<script type="text/javascript" src="jquery/jquery-1.8.2.js"></script>
		<script type="text/javascript" src="jquery/main.js"></script>
		<script type="text/javascript" src="jquery/management.js"></script>
		<script type="text/javascript" src="easyui132/jquery.easyui.min.js"></script>
		<link rel="stylesheet" href="easyui132/themes/icon.css"
			type="text/css"></link>
		<link rel="stylesheet" href="easyui132/themes/gray/easyui.css"
			type="text/css"></link>

		<style>
.navPoint {
	COLOR: #FF0000;
	CURSOR: hand;
	FONT-FAMILY: Webdings;
	FONT-SIZE: 9pt
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
		<form action="management.jsp" method="post" name="frm" id="frm">
			<input type="hidden" name="dis" value="<%=dis%>" />
			<input type="hidden" name="opt" value="" />
			<table border="0" width="100%" class="table" cellspacing="0"
				cellpadding="0">

				<%
//分页
int rowpage=0;//每页显示行数
if(request.getParameter("rowpage")==null){
	rowpage=5;
}else{
	try{
		rowpage=new Integer(request.getParameter("rowpage")).intValue();
	}catch(Exception ex){
		rowpage=15;
	}
}
if(rowpage<5)//每页最少显示5行
	rowpage=5;

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
	System.out.println("-------页面中得到的filter="+filter);
	
	int count=mg.getCountformanagement(table, filter, searchtype);//-----------------------
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
						<div id="a" class="divstyle" style="display: none">
							<table width="100%" height="100%" border="0" cellpadding="0"
								align="left">
								<tr>
									<td colspan="5">
										&nbsp;
									</td>
								<tr>
									<td width="60" align="left">
										类别：
									</td>
									<td>
										系统负责人管理：&nbsp;&nbsp;账号
										<input type="radio" name="searchType" id="searchType"
											value="su.YHMC" checked="checked" style="border: 0;" />
										&nbsp;|&nbsp; 问题系统
										<input type="radio" name="searchType" id="searchType"
											value="su.SYSNAME" checked="checked" style="border: 0;" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;||
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系统列表管理：&nbsp;&nbsp; 问题系统
										<input type="radio" name="searchType" id="searchType"
											value="s.SYSNAME" style="border: 0;" />
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
					<td width="430">
						<table border="0" cellspacing="0" cellpadding="0" width="100%">
							<tr>
								<td height="30" align="center"  width="200">
									<span id="searchname">搜索类别:</span>
									<input type="hidden" name="radiochecked" id="radiochecked"
										value="<%=radiochecked%>" />
								</td>
								<td width="140" height="30" align="center" valign="middle">
									<input name="rowpage2" id="rowpage2" type="text"
										value="<%=filter%>" />
								</td>
								<td height="30">
									&nbsp;&nbsp;	
									<a href="#" class="search"><img src="images/search2.png"
											title="搜索" style="vertical-align: top;">
									</a>&nbsp; &nbsp;
									<a href="#" onClick="auto();"><img src="images/zhankai.png"
											title="展开" style="vertical-align: top;">
									</a>&nbsp;&nbsp;&nbsp;
								</td>
							</tr>
						</table>

					</td>
					<td align="right" valign="middle" style="border: 0px;">
						&nbsp;&nbsp; 共
						<font color="red"><%=count%></font>行 /&nbsp; 每页
						<input name="rowpage" id="rowpage" type="text"
							class="text_style30" value="<%=rowpage%>" size="3" />
						行 &nbsp;&nbsp;&nbsp;&nbsp; 共
						<font color="red"><%=allpages%></font>页 /&nbsp; 当前第
						<input name="noncepage" id="noncepage" type="text"
							class="text_style30" value="<%=noncepage%>" size="5" />
						页

						<%
		if(count<rowpage){
%>
						&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
						<%
		}else if(noncepage==1){
%>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#" class="next">下一页</a>
						<%		
		}else if(noncepage==allpages){
%>
						<a href="#" class="prev">上一页</a> &nbsp;&nbsp;&nbsp;&nbsp;
						<%
		}else{
%>
						<a href="#" class="prev">上一页</a>
						<a href="#" class="next">下一页</a>
						<%	
		}
%>
						<a href="#" class="subm"><img src="images/refresh.png"
								style="margin-bottom: -4px; margin-right: 5px; margin-left: 5px;"
								title="刷新">
						</a>
					</td>
				</tr>
			</table>
		</form>
			<input type="hidden" name="qid" id="qid" value="" />
			<table width="100%" height="27" cellpadding="0" cellspacing="0">
				<tr id="thd">
					
				</tr>

				<%
	List list=mg.queryinfoformanagement(table,filter,searchtype,((noncepage-1)*rowpage),(noncepage*rowpage));
	if(list!=null){
		int tri=0;
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			tri=(tri+1)%2;
   			Map row = (Map) iter.next();
   			int iid =  Integer.parseInt(row.get("iid").toString());
   			String a  = row.get("a").toString();
   			String b = row.get("b").toString();
   			String c = row.get("c").toString();
		  if(tri == 0){
          		%>
          		<tr onMouseMove="style.backgroundColor='#B4DBFE'"
				onMouseOut="style.backgroundColor='#F3F3F3'" style="background-color: #F3F3F3;">
          		<%
          	}else{
   				%>
   				<tr onMouseMove="style.backgroundColor='#B4DBFE'"
				onMouseOut="style.backgroundColor='#FFFFFF'">
                 <%}%>
				<td nowrap class='t_td'><%=a %></td>
				<td nowrap class='t_td'><%=b %></td>
				<td nowrap class='t_td'><%=c %></td>
				<td nowrap class='t_td'>
				<a href="deletedateAction.action?tid=<%=iid %>" id="delete" onclick="confirm('警告！', '确定删除该数据?'){return true;}"><img src="images/delitem.gif" title="删除"></a>
				</td>
					<%
		          }
	}
		          %>
		       </tr>
		       <form action="adddateAction.action" method="post" name="frm2" id="frm2">
		       <input type="hidden" name="table1" value="<%=table %>" />
		       <tr>
		       <td nowrap class='t_td'>添加：</td>
		       <td nowrap class='t_td'>
		       <%if(table.equals("SYSUSER0")){
		   		%>
				<input name="yhmc" id="yhmc" type="text" value="" placeholder="请输入系统负责人账号"/>
				<%}else{
					%>
					保存时ID自动生成
					<%
				}
				%></td>
				<td nowrap class='t_td'><input name="sysname" id="sysname" type="text" value="" placeholder="请输入系统名称"/></td>
				<td nowrap class='t_td'>
				<a href="javascript:document.frm2.submit();" id="add"><img src="images/add.gif" title="添加"></a>
		       </tr>
		       </form>
			</table>
		<script language="javascript">
	function auto(){
		var div=document.getElementById("a");
		if(div.style.display=='none'){
			div.style.display = "";
		}else{
			div.style.display = "none";
		}
	}
	</script>

	</body>

</html>