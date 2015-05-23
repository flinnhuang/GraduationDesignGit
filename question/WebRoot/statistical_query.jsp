<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page import="com.sysinfo.dao.impl.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());    
statistical stst = (statistical)wac.getBean("statistical");
//用户验证 
	response.setContentType("text/html");
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    String opt=request.getParameter("opt");
    String dis=request.getParameter("dis");
   // String doSubmit=request.getParameter("doSubmit");
    if(dis==null)dis="";  
   // if(doSubmit==null)doSubmit="";
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
%>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>统计查询</title>
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
		<table width="100%" height="100" cellpadding="0"
			cellspacing="0" border="1">
			<tr>
				<td nowrap class="table_td_head">| 系统平台</td>
				<td nowrap class="table_td_head">| 类别</td>
				<td nowrap class="table_td_head">| 紧急程度</td>
				<%
				List list1 = stst.showSystem();
				List list2 = stst.showType();
				List list3 = stst.showDegree();				
				List list=stst.showCountDeteil();
				int n = 0;
				int h = 0;
				int num = -3;
				int m = 0;
				System.out.println(list1.size()) ;
				%>
			</tr>			
				
				<% 
				if(list1!=null){
					for(int i = 0;i<list1.size();i++){
						m=0;
						for(int x=0;x<list2.size()*list3.size();x++){
							m+=Integer.parseInt(list.get(n).toString());
							n++;
						}
						List lis = stst.getSysuser0Yhmc(list1.get(i).toString());
						%>
							<tr>
							<td nowrap class="t_td"  rowspan="3"><a href="questiondeteil.jsp?q_system=<%=list1.get(i) %>"><%=list1.get(i)%></a> [<%=m %>]
							&nbsp;系统负责人：<% for(int kk = 0;kk<lis.size();kk++){%>
							&nbsp;<%=lis.get(kk) %>
							<%
							}
							%>
							</td>
						<% 						
						for(int j = 0;j<list2.size();j++){							
							num+=3;
							m=0;
							for(int y=0;y<list3.size();y++){
								m+=Integer.parseInt(list.get(h).toString());
								h++;
							}
							
							%>
							   <td nowrap class="t_td"><%=list2.get(j) %>[<%=m%>]</td>
							   <td nowrap class="t_td">特急[<%=list.get(num) %>]条&nbsp;&nbsp;&nbsp;&nbsp;  急[<%=list.get(num+1) %>]条 &nbsp;&nbsp;&nbsp;&nbsp;  普通[<%=list.get(num+2) %>]条</td>							  
							   
							     </tr>
							<%
						}			
					}
				}
				%>
				
	</table>
	</div>

	</body>
	<!--#ffc b5e7ff  deedf3-->
</html>