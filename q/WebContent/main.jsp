<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,java.security.MessageDigest,java.math.BigInteger,javax.servlet.http.Cookie,java.text.SimpleDateFormat"%>
<%@ page import="com.gree.q.sys.vo.Cbase000VO"%>
<%@ page import="com.gree.q.sys.vo.Cbase011VO"%>
<%@ page import="com.gree.q.sys.mo.Cbase000MO"%>
<%@ page import="com.gree.q.util.db.DbConnection"%>
<%@ page import="com.gree.q.util.DateTime"%>
<%
	Date nowdate = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	String nowtime = sdf.format(nowdate);//获取时间
  String mail=(String)session.getAttribute("mail");
if(mail==null){
	//----------------------------------------搜索判断cookie中保存的登录状态信息
	boolean isauto = false;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie ck : cookies) {
			if (ck.getName().equals("greeProjectAutoLogin")){
				// 取得名称是greeAutoLogin的cookie
				isauto = true;
				String val = ck.getValue();
				String[] uidval = val.split("-");
				String msg = val.substring(val.indexOf("-")+1,val.length());//取得用户id及登录状态信息msg
				//如果登录成功，msg经过加密后应该为：
				String msg2 = "greeProjectLoginSuccess"+uidval[0]+nowtime;
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(msg2.getBytes());
				BigInteger sha2 = new BigInteger(md.digest());
				msg2 = sha2.toString(32);//这个就是登录成功的msg经过加密后的String
				
				System.err.println("自動登錄，id="+uidval[0]);
				System.err.println("自動登錄，msg="+msg+"  msg2="+msg2);
				
				if(msg==null||!msg.equals(msg2)){//验证msg
					out.println("<br/><center><img alt='loginError' src='images/loginError.PNG'/></center>"+
								"<br/><center>会话时间已过期！</center>");
					return;
				}else{
					session.setAttribute("mail", uidval[0]+"@gree.com.cn");
					session.setMaxInactiveInterval(9000);//设置session失效时间，单位为秒，此为15分钟后失效
					response.sendRedirect("main.jsp");
				}
				return;
				//break;
			}
			System.err.println("cookie.getName()="+ck.getName());
			System.err.println("cookies ="+cookies);
		}
	}
	if(isauto == false){
		out.println("<br/><center><img alt='loginError' src='images/loginError.PNG'/></center>"+
					"<br/><center>会话时间已过期！</center>");
		return;
	}
	return;
}
//---------------------------------------------------------end

  String department=(String)session.getAttribute("department");
  String displayname=(String)session.getAttribute("displayname");
  String company=(String)session.getAttribute("company");
%>
<%
	int l = (mail.indexOf("@")<=0)?mail.length():mail.indexOf("@");
	String umail = mail.substring(0, l);
%>
<HTML>
<HEAD>
<TITLE>问题异常处理中心>>当前帐号：<%=mail%></TITLE>
<link rel="stylesheet" href="css/commons.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="easyui132/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="easyui132/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="easyui132/themes/gray/easyui.css" type="text/css"></link>
<style type="text/css">
.tab_iframe{width:100%;height:100%;border:0;}
.home_menu {
	width: 100%;
	float: left;
	padding-left: 0px;
	padding-top: 0px;
}
.home_banner {
}
.home_banner span{
	font-size: 14px;
	color:navy;
}
</style>
<script type="text/javascript">
function doAddTab(id, url, text){
    var index = 0;
    var exists = false;
    var tt = $("#tabs").tabs('getTab', index);
    while(tt != null){
       if($(tt).attr('id') == id){
           exists = true;
           break;
       }
       index++;
       tt = $("#tabs").tabs('getTab', index);
    }
    
    if(!exists){
       $('#tabs').tabs('add',{
           id: id,
            title: text,
            content: '<iframe id="if_'+id+'" frameborder="false" class="tab_iframe" src="'+url+'"></iframe>',
            closable: true,
            fit: true,
            style: 'overflow:auto;',
            tools:[{
                iconCls: 'icon-mini-refresh',
                handler:function(){
                  $('#if_'+id).attr('src', url);
                }
            }]
        });
    }else{
       $('#tabs').tabs('select',index);
    }
}

$(document).ready(function(){
	
	$(".menubtn").click(function(){
		var url = "/q/" + $(this).attr('url');
		var text = $(this).text();
		var id = $(this).attr('id');
		
		doAddTab(id, url, text);
		
	});
	
	$('#tabs').tabs('add',{
		id: "TIP1",
        title: "系统信息",
        content: '<iframe id="if_TIP1" frameborder="false" class="tab_iframe" src="e.jsp"></iframe>',  
        closable: false,
		tools:[{
			iconCls: 'icon-mini-refresh',
			handler:function(){
				$('#if_TIP1').attr('src', 'e.jsp');
			}
		}]
    });
	
	$('#m1').click(function(){
		doAddTab("TIP2", "qm.jsp", "异常反馈");
	});
	
    $('#m2').click(function(){
		doAddTab("TIP3", "b.jsp", "异常处理");
	});
    
    $('#m3').click(function(){
		doAddTab("TIP4", "c.jsp", "统计查询");
	});
    
    $('#m4').click(function(){
		doAddTab("TIP5", "allCount.jsp", "全部异常");
	});
	
	$('#btnLogout').click(function(){
		$.messager.confirm('系统信息', '您真的要退出系统吗?', function(r){
		   if (r){
			   window.location.href="logout.jsp";
		   }
		});
	});
});

</script>
</HEAD>
<BODY >
<form id="menuForm" action="main.jsp" name="frm" method="post" >
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
        	<a href="javascript:void(0);" id="m1" ><img src="images/m11.jpg" ></a>
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
</BODY>
</HTML>
