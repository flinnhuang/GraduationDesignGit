<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.gree.q.util.DateTime"%>
<%@ page import="com.gree.q.util.LdapUtil"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gree.q.sys.dao.*"%>
<%@ page import="com.gree.q.sys.vo.*"%>
<%@ page import="com.gree.q.sys.mo.Cbase000MO"%>
<%@ page import="com.gree.q.sys.mo.Aqus001MO"%>
<%
Aqus001MO aq001mo = Aqus001MO.newInstance();
QuestionDao qd = new QuestionDao();

//用户验证
	request.setCharacterEncoding("ISO-8859-1");
    String opt=request.getParameter("opt");
    String dis=request.getParameter("dis");
    if(dis==null)dis="";

	String radiochecked=request.getParameter("radiochecked");
	if(radiochecked == null ||radiochecked == ""){
		radiochecked = "Q_TITL";
	}
%>
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<link rel="stylesheet" type="text/css" href="css/css.css">	
		<link rel="stylesheet" href="css/commons.css" type="text/css"></link>
		<link rel="stylesheet" type="text/css" href="css/demoOnlyinfo.css">
		<script type="text/javascript" src="js/jquery-1.8.2.js"></script>		
		<script type="text/javascript" src="jquery/main.js"></script>
        <script type="text/javascript" src="easyui132/jquery.easyui.min.js"></script>
        <link rel="stylesheet" href="easyui132/themes/icon.css" type="text/css"></link>
        <link rel="stylesheet" href="easyui132/themes/gray/easyui.css" type="text/css"></link>	
	</head>
	
<SCRIPT LANGUAGE="JavaScript">
function checkthis(){
	var usid = document.getElementById('usid').value;
	var pawd = document.getElementById('pawd').value;
	if(usid==""){
		document.getElementById('usid').focus();
		return;
	}
	if(pawd==""){
		document.getElementById('pawd').focus();
		return;
	}
	document.getElementById('form4').submit();
}
function focusthis(){
	document.getElementById('usid').value="";
	document.getElementById('usid').focus();
	onLoginLoaded();
}
</SCRIPT>
<script language="javascript" type="text/javascript"> 
function onLoginLoaded() 
{ 
if(isPostBack == "False") 
{ 
GetLastUser(); 
} 
} 
function GetLastUser() 
{ 
var id = "49BAC005-7D5B-4231-8CEA-16939BEACD67"; 
var usr = GetCookie(id); 
if(usr != null) 
{ 
document.getElementById('usid').value = usr; 
} 
else 
{ 
document.getElementById('usid').value = "001"; 
} 
GetPwdAndChk(); 
} 
//点击登录时触发客户端事件 
function SetPwdAndChk() 
{ 
//取用户名 
var usr = document.getElementById('usid').value; 
//alert(usr); 
//将最后一个用户信息写入到Cookie 
SetLastUser(usr); 
//如果记住密码选项被选中 
if(document.getElementById('chkRememberPwd').checked == true) 
{ 
//取密码值 
var pwd = document.getElementById('pawd').value; 
//alert(pwd); 
var expdate = new Date(); 
expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000)); 
//将用户名和密码写入到Cookie 
SetCookie(usr,pwd, expdate); 
} 
else 
{ 
//如果没有选中记住密码,则立即过期 
ResetCookie(); 
} 
} 
function SetLastUser(usr) 
{ 
var id = "49BAC005-7D5B-4231-8CEA-16939BEACD67"; 
var expdate = new Date(); 
//当前时间加上两周的时间 
expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000)); 
SetCookie(id, usr, expdate); 
} 
//用户名失去焦点时调用该方法 
function GetPwdAndChk() 
{ 
var usr = document.getElementById('usid').value; 
var pwd = GetCookie(usr); 
if(pwd != null) 
{ 
document.getElementById('chkRememberPwd').checked = true; 
document.getElementById('pawd').value = pwd; 
} 
else 
{ 
document.getElementById('chkRememberPwd').checked = false; 
document.getElementById('pawd').value = ""; 
} 
} 
//取Cookie的值 
function GetCookie (name) 
{ 
var arg = name + "="; 
var alen = arg.length; 
var clen = document.cookie.length; 
var i = 0; 
while (i < clen) 
{ 
var j = i + alen; 
//alert(j); 
if (document.cookie.substring(i, j) == arg) 
return getCookieVal (j); 
i = document.cookie.indexOf(" ", i) + 1; 
if (i == 0) break; 
} 
return null; 
} 
function getCookieVal (offset) 
{ 
var endstr = document.cookie.indexOf (";", offset); 
if (endstr == -1) 
endstr = document.cookie.length; 
return unescape(document.cookie.substring(offset, endstr)); 
} 
//写入到Cookie 
function SetCookie(name, value, expires) 
{ 
var argv = SetCookie.arguments; 
//本例中length = 3 
var argc = SetCookie.arguments.length; 
var expires = (argc > 2) ? argv[2] : null; 
var path = (argc > 3) ? argv[3] : null; 
var domain = (argc > 4) ? argv[4] : null; 
var secure = (argc > 5) ? argv[5] : false; 
document.cookie = name + "=" + escape (value) + 
((expires == null) ? "" : ("; expires=" + expires.toGMTString())) + 
((path == null) ? "" : ("; path=" + path)) + 
((domain == null) ? "" : ("; domain=" + domain)) + 
((secure == true) ? "; secure" : ""); 
} 
function ResetCookie() 
{ 
var usr = document.getElementById('usid').value; 
var expdate = new Date(); 
SetCookie(usr, null, expdate); 
} 

function searchCheck() 
{ 
var sc = document.getElementById('text_style400').value; 
if(sc == ""){
	alert("亲，请输入要搜索的问题哦！");
	return;
	
}else{
	document.getElementById('form2').submit();
}
 
} 
</script> 
<body scroll=no style="MARGIN: 0px">
<form  action="index2.jsp" method="post" name="frm" id="frm">
<%	
request.setCharacterEncoding("ISO-8859-1");
String key = request.getParameter("keyWord");
if(key == null) key = "";
key = new String(key.getBytes("ISO-8859-1"), "UTF-8");


 %>
<%
//分页
int rowpage=0;
if(request.getParameter("rowpage")==null){
	rowpage=5;
}else{
	try{
		rowpage=new Integer(request.getParameter("rowpage")).intValue();
	}catch(Exception ex){
		rowpage=5;
	}
}
if(rowpage<5)
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
int allpages=0;

if(request.getParameter("allpages")==null){
	allpages=1;
}else{
	try{
		allpages=new Integer(request.getParameter("allpages")).intValue();
	}catch(Exception ex){
		allpages=1;
	}
}


if(noncepage<1)
	noncepage=1;

	int count=qd.Count(key);
	if(count<0)count=0;

	if(count%rowpage==0)
		allpages=count/rowpage;
	else
		allpages=count/rowpage+1;
	if(allpages<1)
		allpages=1;
	if(noncepage>allpages)
		noncepage=allpages;
%>


<%

List list1 = qd.getAllQuestionListByKeyWord(key,((noncepage-1)*rowpage),(noncepage*rowpage));
List list2 = qd.getAllDistinctQuestionList();
List list = new ArrayList();
	
    for(int i = 0;i< list2.size();i++){
    	QuestionVO qvo = (QuestionVO)list2.get(i);
    	String str ="'"+qvo.getQ_titl()+"'";
    	list.add(str);
    }
	if(list1.size() == 0){
		%>			
			<h1 style="padding:30%;">亲！系统暂时还找您想要的问题，快到<a href="index.jsp">首页</a>登录反馈吧！</h1>
		<%
	}
	if(list1.size() > 0){
		
		%>
		<div id="search_top">
		    <a href="index.jsp">首页</a>
		    <a href="javascript:void(0)" onclick = "document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">登录</a>
		</div>
		<div id="search_answer">
		
		        			<a href="index.jsp"><img src="images/q1.jpg" width="70" height="70" style="margin-bottom:-20px;margin-right:10px;"/> </a>  
          					<input name="keyWord" type="text" id="search" class="text_style401" onkeyup="autoComplete.start(event)" autocomplete="off" value="<%=key%>"/>
          					<div class="auto_hidden" id="auto"><!--自动完成 DIV--></div>
          					<script>
          					 var l =<%=list%>;
   							 var autoComplete=new AutoComplete('search','auto',l);
							</script>
          					<input name="button" type="submit" class="text_style60" id="button" value="搜  索" onclick="searchCheck();">    
       				
       	
       	<h3 style="margin-top:30px;">亲，为您找到<font color=red><%=count%></font>条相关问题</h3>
       	</div>
		<% 
		
		for(int i = 0;i<list1.size();i++){
		QuestionVO qvo = (QuestionVO)list1.get(i);
		double percent = qvo.getQ_sta()/5*100;
		String goodPercent = "";
		if(percent == 0){
			goodPercent = "未评分！";
		}else{
			goodPercent = "好评率:"+ percent+"%";
		}
		%>
		
			<ul style="width:50%;margin-left:40px;">
				<li style="font-size: 20px;"> <a href="answer.jsp?titl=<%=qvo.getQ_titl()%>"><%=qvo.getQ_titl()%></a></li>
				<li style="font-size: 15px;"> <%=qvo.getQ_titl()%></li>
				<li style="font-size: 8px;"> <%=qvo.getQ_submittime()%><%=goodPercent%></li><hr/>
			</ul>
		<% 
	}

%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td align="left"  style="padding-left:200px;padding-top:100px;">
	
	<% 
		if(noncepage!=1){
	%>	
		<a href="#" class="prev"><input name="ggg" type="text"  value="&lt上一页" style="width:70px;height:35px;text-align: center;font-size: 15px;font-weight:bold;background-color:#3BC9B1;color:#fff;" readonly="readonly"/></a>
	<% 
		}
	%>
	<% 
		for(int i = 1;i<=allpages;i++){
			if(noncepage == i){
				%>
				<input type="text" value="<%=i%>" style="width:35px;height:35px;text-align: center;font-size: 15px;border: 1px solid #3BC9B1;color:red;" readonly="readonly" />
				<%
			}else{
			%>
				<a href="#"><input id ="current" class="current" name="current" type="text" value="<%=i%>" style="width:35px;color:#fff;height:35px;background-color:#3BC9B1;  text-align: center;font-weight:bold;font-size: 15px" readonly="readonly" /></a>
			<% 
			}
		}
	
	%>
	
	<% if(noncepage!=allpages){
	%>
	<a href="#" class="next"><input name="ggg" type="text"  value="下一页&gt" style="width:70px;height:35px;text-align: center;font-size: 15px;font-weight:bold;background-color:#3BC9B1;color:#fff;" readonly="readonly"/></a>	
	<% 
	}
	%>
	<input name="noncepage" id="noncepage" type="text"  value="<%=noncepage%>" style="width:40px;height:30px;text-align: center;display: none;"/>
	&nbsp;&nbsp;&nbsp;<input name="rowpage" id="rowpage" type="text" style="width:40px;height:30px;text-align: center;display: none;" value="<%=rowpage%>" size="3"/>
   <a href="#" onclick="document.getElementById('frm').submit();"></a>
	</td>
</tr>
</table>
</form>
<%
	}
	 %>
<form method="post" id="form4" name="form4"  action="login.jsp?action=1" onSubmit="return false;">
  		<div id="light" class="white_content">
  			<p align="right" class="imgtop"> 
  				<a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">
  					<img src="images/close.png" width="30" height="30" title="关闭" border="0"/>
  				</a>
  			</p>
  			<p align="center" style="margin-bottom:30px;padding-top:20px;">
  				<img src="images/qu.jpg" width="140" height="30"/>
  			</p>
  		    			
  		    <div id="login_content">
  				邮箱号码<input id="usid" name="usid" type="text"   class="text_style300" onBlur="GetPwdAndChk();"><br><br>
  			
  			
  				开机密码<input id="pawd" name="pawd" type="password"  class="text_style300" value=""><br><br>
  			
  			
  				记住密码<input name="checkbox" type="checkbox" id="chkRememberPwd" style="margin-left:10px;border:0px;"/><br><br>
 			
 			 		 
 			  		  <input type=image src="images/login.jpg" width="345" height="35" onClick="SetPwdAndChk();checkthis();"><br><br><br><br><br><br><br>
	   		</div>
 		</div> 
 		<div id="fade" class="black_overlay"></div> 
	</form>
</body>
</html>