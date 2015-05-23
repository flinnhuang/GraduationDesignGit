$(function(){
	var str = $("#radiochecked").attr("value");
	if(str == null||str == ""){
		str="3";
	}
	$(":radio").val([str]);
	//--------------------------------------显示下拉框、选择填写效果------------------------------
	var isIE = $.browser.msie;
	var $selectlist = $(".selectlist");
	$selectlist.mouseenter(function(){
		var $input = $(this).children(":input");
		//var $weizhi = $(this).prev(".location001");
		var width = $input.width();
		var top = GetTop($input);
		var left = GetLeft($input);
		//alert("top:"+top+"---left:"+left+"---width="+width+"--isIE:"+isIE);
		if(isIE){
			$(this).children(".option1").css({
				"top":(top+61)+"px",
				"left":(left+8)+"px",
				"width":(width+4)+"px"
			}).show("fast").children("ul").css({
				"margin-left":0+"px"
			});
		}else{
			$(this).children(".option1").css({
				"top":(top+18)+"px",
				"left":left+"px",
				"width":(width-4)+"px"
			}).show("fast");
		}
		//显示出选择框后，点击td则取出该值，赋值到input的value中
		$(".td001").click(function(){
			var str = $.trim($(this).text());
			//alert(str);
			$(this).parent().parent().prev("input").attr("value",str);	
		});
	}).mouseleave(function(){
		$(this).children(".option1").hide();
	});
	
	
	/**********************************************************************/
	function onlysave(){
		
	}
	$("#onlysave").click(function(){//只保存不提交
		//if(confirm("确认保存?")== true){
		
		var $titl = $("#titl");
			//$titl.parent().find(".msg").remove();
			var titl = $.trim($titl.attr("value"));
			$titl.attr("value",titl);
			if(titl.equel("")||titl == null){
				alert("主题不能为空！");
				$titl.focus();
				$titl.attr("value","");
			}else{
				$("#opt").attr("value","doSave");
				$("#opt1").attr("value","onlySave");
				if(isIE){//ie兼容性处理
					var editorText = $(window.frames['example'].document).find('iframe').contents().find("body");
					var content = editorText.html();//取出编辑器textarea里的值
					$content = $("#content1");
					$content.attr("value",content);
					//alert($content.attr("value"));
					document.example.submit();
					//$("#example").submit();
				}
			}
	});
	
	$("#entersubmit").click(function(){//保存并提交
		if(confirm("提交后将发送邮件到相关负责人处，是否确认提交？")== true){
			var $titl = $("#titl");
			//$titl.parent().find(".msg").remove();
			var titl = $.trim($titl.attr("value"));
			$titl.attr("value",titl);
			if(titl == ""||titl == null){
				//var msg = "<span class='msg'><img src='../../images/red-.png' width='13' " +
				//		"height='14'/>请输入主题！</span>";
				//$titl.parent().append(msg);
				$titl.focus();
				//$titl.attr("value","");
			}else{
				$("#opt").attr("value","doSave");
				$("#opt1").attr("value","");
				if(isIE){//ie兼容性处理
					var editorText = $(window.frames['example'].document).find('iframe').contents().find("body");
					var content = editorText.html();//取出编辑器textarea里的值
					$content = $("#content1");
					$content.attr("value",content);
					//alert($content.attr("value"));
					//$("#example").submit();
					document.example.submit();
				}
			}
		}else{
			$("#opt").attr("value","");
			$("#opt1").attr("value","");
		}
	});
	//---------------------回复页面回复确认提交按钮
	$("#huifusubmit").click(function(){//保存并提交
		if(confirm("是否确认回复？")== true){
			$("#opt").attr("value","doSave");
			if(isIE){//ie兼容性处理
				var editorText = $(window.frames['example'].document).find('iframe').contents().find("body");
				var content = editorText.html();//取出编辑器textarea里的值
				$content = $("#content1");
				$content.attr("value",content);
				document.example.submit();
			}
		}else{
			$("#opt").attr("value","");
			$("#opt1").attr("value","");
		}
	});
	/**********************************************************************/
	//判断isIE，判断是否ie浏览器，调用不同方法返回页面元素真实定位值
	function GetTop(obj){
		if(isIE){
			return obj[0].offsetTop;
		}else{
			return obj.offset().top;
		}
	}
	function GetLeft(obj){
		if(isIE){
			return obj[0].offsetLeft;
		}else{
			return obj.offset().left;
		}
	}
	/***************************************************************/
	//主题填写验证
	$("#titl").blur(function(){
		//alert( $(this).attr);
		$(this).parent().find(".msg").remove();
		var titl = $.trim($(this).attr("value"));
		if(titl == ""||titl == null){
			var msg = "<span class='msg'><img src='../../images/red-.png' width='13' height='14'/>请输入主题！</span>";
			$(this).parent().append(msg);
			$(this).focus();
			$(this).attr("value","");
		}else{
			msg = "<span class='msg'><img src='../../images/greendui.png' width='15' height='14'/></span>";
			 $(this).parent().append(msg);
		}
	});
})
