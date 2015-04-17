<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
<%@ page import="com.gree.q.sys.dao.*"%>
<%@ page import="com.gree.q.sys.vo.*"%>
<% 

String in = request.getParameter("in");
String opt = request.getParameter("opt");
request.setCharacterEncoding("ISO-8859-1");
String key = request.getParameter("keyWord");
if(key == null) key = "";
key = new String(key.getBytes("ISO-8859-1"), "UTF-8");
//if("doSave".equals(opt)){System.out.println("key="+key);}
    QuestionDao qd = new QuestionDao(); 
	List list2 = qd.getAllDistinctQuestionList();
	List list = new ArrayList();
    for(int i = 0;i<list2.size();i++){
    	QuestionVO qvo = (QuestionVO)list2.get(i);
    	String str ="'"+qvo.getQ_titl()+"'";
    	list.add(str);
    }

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>Q</title>
<link href="css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="jquery/jquery-1.11.1.min.js"></script>

</head>

<script language="javascript" type="text/javascript">
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

var $ = function (id) {
    return "string" == typeof id ? document.getElementById(id) : id;
}
var Bind = function(object, fun) {
    return function() {
        return fun.apply(object, arguments);
    }
}
function AutoComplete(obj,autoObj,arr){
    this.obj=$(obj);        //输入框
    this.autoObj=$(autoObj);//DIV的根节点
    this.value_arr=arr;        //不要包含重复值
    this.index=-1;          //当前选中的DIV的索引
    this.search_value="";   //保存当前搜索的字符
}
AutoComplete.prototype={
    //初始化DIV的位置
    init: function(){
        this.autoObj.style.left = this.obj.offsetLeft + "px";
        this.autoObj.style.top  = this.obj.offsetTop + this.obj.offsetHeight+"px";
        this.autoObj.style.width= this.obj.offsetWidth -5 + "px";//减去边框的长度5px   
    },
    //删除自动完成需要的所有DIV
    deleteDIV: function(){
        while(this.autoObj.hasChildNodes()){
            this.autoObj.removeChild(this.autoObj.firstChild);
        }
        this.autoObj.className="auto_hidden";
    },
    //设置值
    setValue: function(_this){
        return function(){
            _this.obj.value=this.seq;
            _this.autoObj.className="auto_hidden";
        }       
    },
    //模拟鼠标移动至DIV时，DIV高亮
    autoOnmouseover: function(_this,_div_index){
        return function(){
            _this.index=_div_index;
            var length = _this.autoObj.children.length;
            for(var j=0;j<length;j++){ 
                if(j!=_this.index ){       
                    _this.autoObj.childNodes[j].className='auto_onmouseout';
                }else{
                    _this.autoObj.childNodes[j].className='auto_onmouseover';
                }
            }
        }
    },
    //更改classname
    changeClassname: function(length){
        for(var i=0;i<length;i++){ 
            if(i!=this.index ){       
                this.autoObj.childNodes[i].className='auto_onmouseout';
            }else{
                this.autoObj.childNodes[i].className='auto_onmouseover';
                this.obj.value=this.autoObj.childNodes[i].seq;
            }
        }
    }
    ,
    //响应键盘
    pressKey: function(event){
        var length = this.autoObj.children.length;
        //光标键"↓"
        if(event.keyCode==40){
            ++this.index;
            if(this.index>length){ 
                this.index=0; 
            }else if(this.index==length){
                this.obj.value=this.search_value;
            }
            this.changeClassname(length);
        }
        //光标键"↑"
        else if(event.keyCode==38){
            this.index--;
            if(this.index<-1){
                this.index=length - 1;
            }else if(this.index==-1){
                this.obj.value=this.search_value;
            }
            this.changeClassname(length);
        }
        //回车键
        else if(event.keyCode==13){
            this.autoObj.className="auto_hidden";
            this.index=-1;
        }else{
            this.index=-1;
        }
    },
    //程序入口
    start: function(event){
        if(event.keyCode!=13&&event.keyCode!=38&&event.keyCode!=40){
            this.init();
            this.deleteDIV();
            this.search_value=this.obj.value;
            var valueArr=this.value_arr;
            valueArr.sort();
            if(this.obj.value.replace(/(^\s*)|(\s*$)/g,'')==""){ return; }//值为空，退出
            try{ var reg = new RegExp("(" + this.obj.value + ")","i");}
            catch (e){ return; }
            var div_index=0;//记录创建的DIV的索引
            for(var i=0;i<valueArr.length;i++){
                if(reg.test(valueArr[i])){
                    var div = document.createElement("div");
                    div.className="auto_onmouseout";
                    div.seq=valueArr[i];
                    div.onclick=this.setValue(this);
                    div.onmouseover=this.autoOnmouseover(this,div_index);
                    div.innerHTML=valueArr[i].replace(reg,"<strong>$1</strong>");//搜索到的字符粗体显示
                    this.autoObj.appendChild(div);
                    this.autoObj.className="auto_show";
                    div_index++;
                }
            }
        }
        this.pressKey(event);
        window.onresize=Bind(this,function(){this.init();});
    }
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
var sc = document.getElementById('search').value; 
if(sc == ""){
	alert("亲，请输入要搜索的问题哦！");
	return;
	
}else{
	document.getElementById('form2').submit();
}
 
} 
</script> 

<body onLoad="focusthis()">
<form method="post" id="form2" name="form2"  action="index2.jsp" onSubmit="return false;">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
  		<tr>
    		<td height="47" align="right" valign="middle" class="bot_border" style="padding-right:50px;" background="images/qlogo_bg.jpg">
    			<font color="#FFFFFF">
    				<a href = "javascript:void(0)" style="color:#FFF" 
    				onclick = "document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block';loginfocusthis();">登录</a> |
    			</font>
    		</td>
    	</tr>
    	<tr>
    		<td align="center" style="padding-top:20px;padding-bottom:50px;">
    			<img src="images/q1.jpg" width="146" height="138"/>
    		</td>
    	</tr>
    </table>
        			<div id="search_center">   
        				 
          					<input name="keyWord" type="text" id="search" class="text_style400" onkeyup="autoComplete.start(event)" autocomplete="off"  placeholder="搜一搜，也许会帮您解决问题！" value="<%=key%>"/>
          					<input name="button" type="submit" class="text_style60" id="button" value="搜  索" onclick="searchCheck();">    
          				
          					<div class="auto_hidden" id="auto"><!--自动完成 DIV--></div>
          					<script>
          					 var l =<%=list%>;
   							 var autoComplete=new AutoComplete('search','auto',l);
							</script>
          					
       				</div>
			
    	
     
</form>
<form method="post" id="form1" name="form1"  action="login.jsp?action=1" onSubmit="return false;">
  		<div id="light" class="white_content">
  			<p align="right" class="imgtop"> 
  				<a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">
  					<img src="images/close.png" width="30" height="30" title="关闭" border="0"/>
  				</a>
  			</p>
  			<p align="center">
  				<img src="images/qu.jpg" width="140" height="30"/>
  			</p>
  		    <div id="login_content">
  				邮箱号码<input tabindex="1" id="usid" name="usid" type="text"  class="text_style300" onBlur="GetPwdAndChk();" placeholder="请输入账号"><br><br/>
  			
  				开机密码<input tabindex="2" id="pawd" name="pawd" type="password" class="text_style300" value="" placeholder="请输入密码"><br><br>
  			  			
  				记住密码<input tabindex="3" name="checkbox" type="checkbox" id="chkRememberPwd" style="margin-left:10px;border:0px;"/><br><br>
 				
 			 		 
 			  		  <input type=image src="images/login.jpg" width="345" height="35" onClick="SetPwdAndChk();checkthis();" tabindex="4"><br><br><br><br><br><br><br>
	   		</div>
 		</div> 
 		<div id="fade" class="black_overlay"></div> 
	</form>
</body>
</html>
