<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,java.security.MessageDigest,java.math.BigInteger,javax.servlet.http.Cookie,java.text.SimpleDateFormat"%>
<html>
  <% 
	request.setCharacterEncoding("GBK");
    String dis=request.getParameter("dis");
    if(dis==null)dis="";
	String u=(String)session.getAttribute("YHMC");
	%>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>问题异常处理中心>>当前帐号：<%=u %></title>
	
	<link rel="stylesheet" href="css/commons.css" type="text/css"></link>
    <link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" href="easyui132/themes/icon.css" type="text/css"></link>
	<link rel="stylesheet" href="easyui132/themes/gray/easyui.css" type="text/css"></link>

    <script type="text/javascript" src="jquery/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="easyui132/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery/main.js"></script>
    
  </head>

  <body>
	<form id="menuForm" action="#" name="frm" method="post" >
		<div id="main_layout" class="easyui-layout" style="width:100%;height:100%;">  
			<div data-options="region:'north',title:''" style="padding:0px;height:75px; overflow:hidden;">
<!--  -->
				<div class="home_banner" style="width:100%; margin-right:auto;height:75px">
	  				<table width="100%" border="0" cellpadding="0" cellspacing="0">
  					<tr>
    				<td width="327" height="6"  bgcolor="#F3F3F3"><img src="images/qlo1.jpg" width="304" height="74"></td>
    				<td bgcolor="#F3F3F3"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      					<tr>
        				<td align="right"><a id="btnLogout" href="javascript:void(0);"><img src="images/logot.jpg" width="35" height="34"></a> </td>
       					</tr>
      					<tr>
        				<td height="30" align="center">
        				<a href="javascript:void(0);" id="m1" ><img src="images/m1.jpg" ></a>
        				<a href="javascript:void(0);" id="m2" ><img src="images/m2.jpg" ></a> 
        				<a href="javascript:void(0);" id="m3" ><img src="images/m3.jpg" ></a>
        				<a href="javascript:void(0);" id="m4" ><img src="images/m4.jpg" ></a></td>
        				</tr>
    					</table></td>
  					</tr>
					</table>
				</div>
			</div>  
			<div data-options="region:'center',title:''"  style="padding:0px;background:#F3F3F3;">
				<div id="tabs" class="easyui-tabs" data-options="border:false,fit:true">
				</div>
			</div>
		</div><!-- end layout -->
	</form>
  </body>
</html>
