<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    String sta = request.getParameter("stars2input");
    String qid = request.getParameter("qid");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>星级评分</title>
</head>
<body>
<style type="text/css">

body {background:#fff;text-align:center;margin:0 auto;padding: 0;font:12px Arial;color:#000;line-height:1.2em}
div,form,img,ul,ol,li,dl,dt,dd {margin: 0; padding: 0; border: 0;}
h1,h2,h3,h4,h5,h6 { margin:0; padding:0;}
table,td,tr,th{font-size:12px;}

#centers{
	margin-left:100px;
	margin-right:auto;
	margin-top:20px;
	margin-bottom:80px;
	width:400px;
	font-size: 15px;
	
	
}

/* 星级评分 */
.shop-rating {
    height: 25px;
    overflow: hidden;
    zoom: 1;
    padding: 2px 0px;
    position: relative;
    z-index: 999;
}

.shop-rating span {
    height: 23px;
    display: block;
    line-height: 23px;
    float: left;
}

.shop-rating span.title {
    width: 200px;
    text-align: center;
    margin-right: 5px;
}

.shop-rating ul {
    float: left;
	margin:0;padding:0
}

.shop-rating .result {
    margin-left: 20px;
    padding-top: 2px;
}

.shop-rating .result span {
	color: #ff6d02;
}
.shop-rating .result em {
    color: #f60;
    font-family: arial;
    font-weight: bold;
}
.shop-rating .result strong {
	color: #666666;
	font-weight: normal;
}
.rating-level,
.rating-level a {
    background: url(images/star.png) no-repeat scroll 1000px 1000px;
}
.rating-level {
    background-position: 0px 0px;
    width: 120px;
    height: 23px;
    position: relative;
    z-index: 1000;
}
.rating-level li {
    display: inline;
}

.rating-level a {
    line-height: 23px;
    height: 23px;
    position: absolute;
    top: 0px;
    left: 0px;
    text-indent: -999em;
    *zoom: 1;
    outline: none;
}


.rating-level a.one-star {
    width: 20%;
    z-index: 6;
}

.rating-level a.two-stars {
width: 40%;
z-index: 5;
}

.rating-level a.three-stars {
    width: 60%;
    z-index: 4;
}

.rating-level a.four-stars {
    width: 80%;
    z-index: 3;
}

.rating-level a.five-stars {
    width: 100%;
    z-index: 2;
}

.rating-level .current-rating,.rating-level a:hover{background-position:0 -28px;}
.rating-level a.one-star:hover,.rating-level a.two-stars:hover,.rating-level a.one-star.current-rating,.rating-level a.two-stars.current-rating{background-position:0 -116px;}
.rating-level .three-stars .current-rating,.rating-level .four-stars .current-rating,.rating-level .five-stars .current-rating{background-position:0 -28px;}


</style>
</head>

 <body>
<form method="post" id="formstar" name="formstar"  action="">
<div id="centers" class="shop-rating">
	<span class="title">如果满意就给五星好评吧!</span><p>
	<ul class="rating-level" id="stars2">
		<li><a class="one-star" star:value="1" href="#">1</a></li>
		<li><a class="two-stars" star:value="2" href="#">2</a></li>
		<li><a class="three-stars" star:value="3" href="#">3</a></li>
		<li><a class="four-stars" star:value="4" href="#">4</a></li>
		<li><a class="five-stars" star:value="5" href="#">5</a></li>
	</ul>
	<span class="result" id="stars2-tips"></span>
	<input type="hidden" id="stars2input" name="stars2input" value=""/>
</div>
<!-- END 星级评分 -->
<a href = "#"  onclick="exam2(<%=qid%>);"><img src="images/submit.png" width="125" height="35"/></a>
</form>
<script type="text/javascript">
var Class = {
	create: function() {
		return function() { this.initialize.apply(this, arguments); }
	}
}
var Extend = function(destination, source) {
	for (var property in source) {
		destination[property] = source[property];
	}
}
function stopDefault( e ) {
	 if ( e && e.preventDefault ){
		e.preventDefault();
	}else{
		window.event.returnValue = false;
	}
	return false;
} 
/**
 * 星星打分组件
 */
var Stars = Class.create();
Stars.prototype = {
	initialize: function(star,options) {
		this.SetOptions(options); //默认属性
		var flag = 999; //定义全局指针
		var isIE = (document.all) ? true : false; //IE?
		var starlist = document.getElementById(star).getElementsByTagName('a'); //星星列表
		var input = document.getElementById(this.options.Input) || document.getElementById(star+"input"); // 输出结果
		var tips = document.getElementById(this.options.Tips) || document.getElementById(star+"-tips"); // 打印提示
		var nowClass = " " + this.options.nowClass; // 定义选中星星样式名
		var tipsTxt = this.options.tipsTxt; // 定义提示文案
		var len = starlist.length; //星星数量
		

		for(i=0;i<len;i++){ // 绑定事件 点击 鼠标滑过
			starlist[i].value = i;
			starlist[i].onclick = function(e){
				stopDefault(e);
				this.className = this.className + nowClass;
				flag = this.value;
				input.value = this.getAttribute("star:value");
				tips.innerHTML = tipsTxt[this.value];
			}
			starlist[i].onmouseover = function(){
				if (flag< 999){
					var reg = RegExp(nowClass,"g");
					starlist[flag].className = starlist[flag].className.replace(reg,"");
				}
			}
			starlist[i].onmouseout = function(){
				if (flag< 999){
					starlist[flag].className = starlist[flag].className + nowClass;
				}
			}
		};
		if (isIE){ //FIX IE下样式错误
			var li = document.getElementById(star).getElementsByTagName('li');
			for (var i = 0, len = li.length; i < len; i++) {
				var c = li[i];
				if (c) {
					c.className = c.getElementsByTagName('a')[0].className;
				}
			}
		}
	},
	//设置默认属性
	SetOptions: function(options) {
		this.options = {//默认值
			Input:			"",//设置触保存分数的INPUT
			Tips:			"",//设置提示文案容器
			nowClass:	"current-rating",//选中的样式名
			tipsTxt:		["1","2","3","4","5"]//提示文案
		};
		Extend(this.options, options || {});
	}
}

var Stars2 = new Stars("stars2")
</script>

</body>
</html>