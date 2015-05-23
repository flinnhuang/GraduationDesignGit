<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page import="com.sysinfo.dao.impl.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());    
statistical stst = (statistical)wac.getBean("statistical");
//用户验证 
    request.setCharacterEncoding("utf-8");
     response.setCharacterEncoding("utf-8");
    String dis=request.getParameter("dis");
    String sys =request.getParameter("q_system");
    
    String u=(String)session.getAttribute("YHMC");
	if(u==null){
		out.println("<br/><center><img alt='loginError' src='images/loginError.PNG'/></center>"+
		"<br/><center>会话时间已过期！</center>");
		return ;
	}
	
		sys = new String(sys.getBytes("ISO-8859-1"), "UTF-8");

	
	String radiochecked=request.getParameter("radiochecked");
	if(radiochecked == null ||radiochecked == ""){
		radiochecked = "TITLE";
	}
%>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>统计查询，详细项目列表</title>
	<link rel="stylesheet" type="text/css" href="css/css.css">
	<link rel="stylesheet" href="css/commons.css" type="text/css"></link>
	<link rel="stylesheet" type="text/css" href="css/demoOnlyinfo.css">
	<script type="text/javascript" src="jquery/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="jquery/main.js"></script>
	<script type="text/javascript" src="jquery/abnormal_handing.js"></script>
	<script type="text/javascript" src="easyui132/jquery.easyui.min.js"></script>
	<link rel="stylesheet" href="easyui132/themes/icon.css" type="text/css"></link>
	<link rel="stylesheet" href="easyui132/themes/gray/easyui.css" type="text/css"></link>
	</head>
	<body>
	<div style="direction:inherit; overflow:yes;">
		<table width="100%" height="27" cellpadding="0"
			cellspacing="0" border="1">
			<tr></tr>
			<tr>
			<td colspan="3">当前位置：<a href="javascript:window.location.href='statistical_query.jsp'">统计查询</a>-><%=sys %>-详细项目列表&nbsp;
				<a href="javascript:window.location.href='statistical_query.jsp'"><img src="images/clear.gif" title="返回上一级"  width="13px;" height="13px;"/></a>
			</td>
			</tr>
			</table>
			<form action="questiondeteil.jsp?q_system=<%=sys %>" method="post" name="frm" id="frm">
			<input type="hidden" name="dis" value="<%=dis%>" />
			<input type="hidden" name="opt" value="" />
			
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
	System.out.println("--abnormal_handing.jsp-------页面中得到的filter="+filter);
	
	int count=stst.getCountforquestiondeteil(sys, filter, searchtype);//-----------------------
	
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
									<td width="80" align="left">
										搜索类别：
									</td>
									<td>
										主题：
										<input type="radio" name="searchType" id="searchType"
											value="TITLE" checked="checked" style="border: 0;" />
										&nbsp;|&nbsp; 提交用户：
										<input type="radio" name="searchType" id="searchType"
											value="YHMC" style="border: 0;" />
										&nbsp;|&nbsp; 问题分类：
										<input type="radio" name="searchType" id="searchType"
											value="QTYPE" style="border: 0;" />
										&nbsp;|&nbsp; 紧急程度：
										<input type="radio" name="searchType" id="searchType"
											value="DEGREE" style="border: 0;" />
										&nbsp;|&nbsp;
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
								<td height="30" align="center">
									<span id="searchname">搜索类别:</span>
									<input type="hidden" name="radiochecked" id="radiochecked"
										value="<%=radiochecked%>" />
								</td>
								<td width="150" height="30" align="center" valign="middle">
									<input name="rowpage2" id="rowpage2" type="text"
										value="<%=filter%>" />
								</td>
								<td height="30">
									&nbsp;
									<a href="#" class="search"><img src="images/search2.png"
											title="搜索">
									</a>&nbsp; &nbsp;
									<a href="#" onClick="auto();"><img src="images/zhankai.png"
											title="展开">
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
		<table border="0" width="100%" class="table" cellspacing="0"
				cellpadding="0">
			<tr>
				<td nowrap class="table_td_head">| 主题</td>
				<td nowrap class="table_td_head">| 提交用户</td>
				<td nowrap class="table_td_head">| 问题分类</td>
				<td nowrap class="table_td_head">| 紧急程度</td>
				<td nowrap class="table_td_head">| 提交时间</td>
				<%
				List list1 = stst.showDeteilbySystem(sys,filter,searchtype,((noncepage-1)*rowpage),(noncepage*rowpage));
				System.out.println("hhh="+sys);
				%>
			</tr>			
				
				<% 
				if(list1!=null){
					int tri=0;
					for (Iterator iter = list1.iterator(); iter.hasNext();) {
			   			Map row = (Map) iter.next();
			   			int qqid = Integer.parseInt(row.get("qqid").toString());
			   			System.out.print("deteil页面中显示qid为："+qqid);
			   			int degree = Integer.parseInt(row.get("q_degree").toString());
		          		String degreeStr = "";
		          		switch(degree){
		          			case 1: degreeStr = "特急";
		          					break;
		          			case 2: degreeStr = "急";
		          					break;
		          			case 3: degreeStr = "普通";
		          		}
			   			tri=(tri+1)%2;
					      if(tri == 0){
          		%>
          		<tr style="background-color: #F3F3F3;">
          		<%
          	}else{
   				%>
   				<tr>
                 <%}%>
				          <td nowrap class="t_td">
				          <%=row.get("q_titl")==null?"&nbsp;":row.get("q_titl").toString()%>
				          <a href="#" onClick="show(<%=qqid%>);">(查看明细)</a>
						  </td>
						  
						   <td nowrap class="t_td">
						   <%=row.get("YHMC")==null?"&nbsp;":row.get("YHMC").toString()%>
						  </td>
						  
						  <td nowrap class="t_td">
						   <%=row.get("q_type")==null?"&nbsp;":row.get("q_type").toString()%>
						  </td>
						  
						  <td nowrap class="t_td t_td<%=degree %>">
						   <%=degreeStr%>
						  </td>
						  
						  <td nowrap class="t_td">
				          <%=row.get("q_submittime")==null?"&nbsp;":row.get("q_submittime").toString()%>
						  </td>
						  </tr>
							<% 
							
					}
				}
				%>
	</table>
	</div>
	</body>
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
</html>