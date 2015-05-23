<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,java.security.MessageDigest,java.math.BigInteger,javax.servlet.http.Cookie,java.text.SimpleDateFormat"%>
<%@ page import="com.model.QUESTION"%>
<%@ page import="com.sysinfo.dao.impl.sysinfoDaoImpl"%>
<%@ page import="com.question.dao.impl.zsgcQuestionDaoImpl"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());    
sysinfoDaoImpl sdi = (sysinfoDaoImpl)wac.getBean("sysInfoDaoImpl");
zsgcQuestionDaoImpl zsgcqdi = (zsgcQuestionDaoImpl)wac.getBean("zsgcQuestionDaoImpl");

Date nowdate = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
String nowtime = sdf.format(nowdate);//获取时间
//用户验证 
	request.setCharacterEncoding("ISO-8859-1");
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
	if(qx.equals("staff")){
		out.println("<br/><center><img alt='loginError' src='images/loginError.PNG'/></center>"+
		"<br/><center>权限不足！您不能进入此页面！</center>");
		return ;
	}
	String radiochecked=request.getParameter("radiochecked");
	if(radiochecked == null ||radiochecked.equals("")){
		radiochecked = "TITLE";
	}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>异常处理页面</title>
		<link rel="stylesheet" type="text/css" href="css/css.css">
		<link rel="stylesheet" type="text/css" href="css/demoOnlyinfo.css">
		<link rel="stylesheet" href="css/commons.css" type="text/css"></link>
		<script type="text/javascript" src="jquery/jquery-1.8.2.js"></script>
		<script type="text/javascript" src="jquery/main.js"></script>
		<script type="text/javascript" src="jquery/abnormal_handing.js"></script>
		<script type="text/javascript" src="easyui132/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="jquery/uploadfile.js"></script>
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
		<form action="abnormal_handling.jsp" method="post" name="frm" id="frm">
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
	
	int count=sdi.getCountforabnormal_handing(u, filter, searchtype);//-----------------------
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
										&nbsp;|&nbsp; 问题系统：
										<input type="radio" name="searchType" id="searchType"
											value="SYSNAME" style="border: 0;" />
										&nbsp;|&nbsp; 系统分类：
										<input type="radio" name="searchType" id="searchType"
											value="QTYPE" style="border: 0;" />
										&nbsp;|&nbsp; 紧急程度：
										<input type="radio" name="searchType" id="searchType"
											value="DEGREE" style="border: 0;" />
										&nbsp;|&nbsp; 回复状态：
										<input type="radio" name="searchType" id="searchType"
											value="REPLYSTATE" style="border: 0;" />
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
			<input type="hidden" name="qid" id="qid" value="" />
			<table width="100%" height="27" cellpadding="0" cellspacing="0">
				<tr>

					<td nowrap class="table_td_head">
						| 回复
					</td>
					<td nowrap class="table_td_head">
						| 撤销
					</td>
					<td nowrap class="table_td_head">
						| 主题
					</td>
					<td nowrap class="table_td_head">
						| 提交用户
					</td>
					<td nowrap class="table_td_head">
						|问题系统
					</td>
					<td nowrap class="table_td_head">
						|系统分类
					</td>
					<td nowrap class="table_td_head">
						| 紧急程度
					</td>
					<td nowrap class="table_td_head">
						| 创建时间
					</td>
					<td nowrap class="table_td_head">
						| 处理情况
					</td>
				
					<td nowrap class="table_td_head">
						| 状态
					</td>
					<td nowrap class="table_td_head">
						| 用户评分
					</td>
				</tr>
				<%
	List list=zsgcqdi.queryQuestionToMapListforabnormal_handingPage(u,filter,searchtype,((noncepage-1)*rowpage),(noncepage*rowpage));
	if(list!=null){
		int tri=0;
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			tri=(tri+1)%2;
   			Map row = (Map) iter.next();
   			String qqid  = row.get("QID").toString();
   			//String stri = mpmo.getItem(qqid, "q_note");
   			int sta = Integer.parseInt(row.get("GRADE").toString());
   			int havesubmit = Integer.parseInt(row.get("SUBMITSTATE").toString());
          	int degree = Integer.parseInt(row.get("DEGREE").toString());
          	String degreeStr = "";
          	switch(degree){
          		case 1: degreeStr = "特急";
          				break;
          		case 2: degreeStr = "急";
          				break;
          		case 3: degreeStr = "普通";
          	}
          	
          	int isdo = Integer.parseInt(row.get("REPLYSTATE").toString());
          	if(tri == 0){
          		%>
				<tr onMouseMove="style.backgroundColor='#B4DBFE'"
					onMouseOut="style.backgroundColor='#F3F3F3'"
					style="background-color: #F3F3F3;">
					<%
          	}else{
   				%>
				
				<tr onMouseMove="style.backgroundColor='#B4DBFE'"
					onMouseOut="style.backgroundColor='#FFFFFF'">
					<%}
		      	int acc = 0;
		      	 try{
		      		acc = Integer.parseInt(row.get("ACCESSORY").toString());
		      	}catch(Exception ex){
		    		ex.printStackTrace();
		    	}
		          if(isdo == 0){
		        	  %>
					<td nowrap class='t_td td<%=degree %>'>
						<img src="images/unanswer.png" title="已回复" />
					</td>
					<td nowrap class='t_td td<%=degree %>'>
						<a href="#"
							onclick="if(confirm('确定撤销上次回复？')){window.location.href='rollbackreply.action?qid=<%=qqid%>';}else{return false;}"><img
								src="images/repeal.png" title="撤销" />
						</a>
					</td>
					<%
		          }if(isdo == 1){
		        	  %>
					<td nowrap class='t_td td<%=degree %>'>
						<a href="#" class="returnBtn" onClick="exam(<%=qqid%>);"><img
								src="images/answer.png" title="回复" />
						</a>
					</td>
					<td nowrap class='t_td td<%=degree %>'>
						<img src="images/hadrepeal.png" title="已撤销" />
					</td>
					<%
		          }
		          %>

					<td nowrap class="t_td td<%=degree %>">
						<%=row.get("TITLE")==null?"&nbsp;":row.get("TITLE").toString()%>
						<a href="#" onClick="show(<%=qqid%>);">(查看明细)</a>
					</td>
					<td nowrap class="t_td td<%=degree %>">
						<%=row.get("YHMC")==null?"&nbsp;":row.get("YHMC").toString()%>
					</td>
					<td nowrap class="t_td td<%=degree %>">
						<%=row.get("SYSNAME")==null?"&nbsp;":row.get("SYSNAME").toString()%>
					</td>
					<td nowrap class="t_td td<%=degree %>">
						<%=row.get("QTYPE")==null?"&nbsp;":row.get("QTYPE").toString()%>
					</td>
					<td nowrap class="t_td td<%=degree %>">
						<%=degreeStr%>
					</td>
					<td nowrap class="t_td td<%=degree %>">
						<%=row.get("CREATETIME")==null?"&nbsp;":row.get("CREATETIME").toString()%>
					</td>
					<td nowrap class="t_td td<%=degree %>">
						<%=row.get("calcutime")==null?"&nbsp;":row.get("calcutime").toString()%>
					</td>
					
					<%
		          if(isdo == 0){
		          %>
					<td nowrap class="t_td td<%=degree %>">
						<img src="images/isdo.png" title="已处理" />
					</td>
					<%
		          }else{%>
					<td nowrap class="t_td td<%=degree %>">
						<img src="images/notdo2.png" title="未处理" />
					</td>
					<%
		           }
					%>

					<td nowrap class="t_td td<%=degree %>">
						<%
		           if(sta == 0){
		        	   for(int i = 1;i<=5;i++){
		        		%>
						<img src="images/nostar.png" title="未评分" width="17px;" />
						<%
		        		}
		           }else{
		        	   if(sta<=2){
		        		   for(int i = 1;i<=2;i++){
		        		   %>
						<img src="images/star2.png" title="<%=sta%>分" width="17px;" />
						<%
		        		   }
		        	   }else{
		        		   for(int i = 1;i<=5;i++){
			        		   %>
						<img src="images/star11.png" title="<%=sta%>分" width="17px;" />
						<%
			        		   }
		        	   }
		           }
		           %>
					</td>
				</tr>

				<%
}
}
   
%>
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

		<div id="win_add2" class="easyui-window" title="待处理问题"
			data-options="modal:true,closed:true,collapsible:false,minimizable:false,maximizable:true,maximized:true,iconCls:'icon-tips'"
			style="width: 1100px; height: 800px; top: 50px;">
			<iframe id="win_add_iframe2" frameborder="0"
				style="border: 0; width: 100%; height: 100%;"></iframe>
		</div>

	</body>

</html>