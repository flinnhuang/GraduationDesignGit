<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.gree.q.util.DateTime"%>
<%@ page import="com.gree.q.util.LdapUtil"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gree.q.sys.mo.MpMo"%>
<%@ page import="com.gree.q.mainpage.mainDAOImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%


//用户验证 
	request.setCharacterEncoding("ISO-8859-1");
    String opt=request.getParameter("opt");
    String sys =request.getParameter("q_system");
    String u=(String)session.getAttribute("mail");
    int l = (u.indexOf("@")<=0)?u.length():u.indexOf("@");
	String qmail = u.substring(0, l);
	sys = new String(sys.getBytes("ISO-8859-1"), "UTF-8");
	mainDAOImpl mdi = new mainDAOImpl();
%>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>统计查询，详细项目列表</title>
	<link rel="stylesheet" type="text/css" href="css/css.css">
	<link rel="stylesheet" href="css/commons.css" type="text/css"></link>
	<script type="text/javascript" src="jquery/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="jquery/main.js"></script>
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
			<td colspan="3">当前位置：<a href="javascript:history.go(-1)">统计查询</a>-><%=sys %>-详细项目列表&nbsp;
				<a href="javascript:history.go(-1)"><img src="images/clear.gif" title="返回上一级"  width="13px;" height="13px;"/></a>
			</td>
			</tr>
			<tr><td>  </td></tr>
			<tr>
				<td nowrap class="table_td_head">| 主题</td>
				<td nowrap class="table_td_head">| 提交用户</td>
				<td nowrap class="table_td_head">| 提交时间</td>
				<%
				List list1 = mdi.showSystemDeteil(qmail,sys);
				System.out.println("hhh="+sys);
				%>
			</tr>			
				
				<% 
				if(list1!=null){
					int tri=0;
					for (Iterator iter = list1.iterator(); iter.hasNext();) {
			   			Map row = (Map) iter.next();
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
						  </td>
						  
						   <td nowrap class="t_td">
						   <%=row.get("q_mail")==null?"&nbsp;":row.get("q_mail").toString()%>
				         
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
	
</html>