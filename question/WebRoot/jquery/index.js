encodeURI("utf-8");
$(function(){
	
	/*------------------------显示隐藏登录页面-----------------------*/
	var $showlogin = $("#showlogin");
	var $notShowlogin = $("#closelogin");
	//单击首页登录链接时，显示登录界面
	$showlogin.click(function(){
		var $light = $("#light");
		var $fade = $("#fade");
		$light[0].style.display = "block";
		$fade[0].style.display = "block";
		loginfocusthis();
	});
	//登录界面显示出来后，单击关闭链接隐藏该界面
	$notShowlogin.click(function(){
		var $light = $("#light");
		var $fade = $("#fade");
		
		$light[0].style.display = "none";
		$fade[0].style.display = "none";
		focusthis()
	});
});
/*----------------------------------------------------------------------*/
var isPostBack = "True";
function checkthis(){
	var usid = document.getElementById('usid').value;
	var pawd = document.getElementById('pawd').value;
	if(usid==""){
		document.getElementById('usid').focus();
		document.getElementById('usid').placeholder="邮箱账号不能为空！";
		return;
	}
	if(pawd==""){
		//alert("请输入密码！");
		document.getElementById('pawd').focus();
		document.getElementById('pawd').placeholder="开机密码不能为空！";
		return;
	}
	document.getElementById('form1').submit();
	isPostBack = "False";
}
function focusthis(){
	document.getElementById('search').value="";
	document.getElementById('search').focus();
}
function loginfocusthis(){
	document.getElementById('usid').value="";
	document.getElementById('usid').focus();
	onLoginLoaded();
}
function onLoginLoaded() 
{ 
if(isPostBack == "False") 
{ 
GetLastUser(); 
} 
} 
function GetLastUser() 
{ 
var id = "wentiyichangchulizhongxin"; 
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
var id = "wentiyichangchulizhongxin"; 
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
