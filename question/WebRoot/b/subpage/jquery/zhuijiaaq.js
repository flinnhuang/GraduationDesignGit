$(function(){
	var str = $("#radiochecked").attr("value");
	if(str == null||str == ""){
		str="3";
	}
	$(":radio").val([str]);
	var isIE = $.browser.msie;
	/***************************************************************/
	$("#entersubmit").click(function(){//保存并提交
		if(confirm("提交后将发送邮件到相关负责人处，是否确认提交？")== true){
			var contenttext1 = $("#content1").val();//取出编辑器textarea里的值
			$content = $("#QCONTENT");
			$content.attr("value",contenttext1);
			var $titl = $("#titl");
			var titl = $.trim($titl.attr("value"));
			$titl.attr("value",titl);
			if(titl == ""||titl == null){
				alert("请输入主题！");
				$titl.focus();
				$titl.attr("value","");
			}else{
				if(isIE){//ie兼容性处理
					var editorText = $(window.frames['example'].document).find('iframe').contents().find("body");
					var content = editorText.html();//取出编辑器textarea里的值
					$content = $("#qu.QCONTENT");
					$content.attr("value",content);
				}
				document.example.submit();
			}
		}
	});

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
});

