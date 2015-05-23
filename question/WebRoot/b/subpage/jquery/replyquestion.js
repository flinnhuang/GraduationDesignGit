$(function(){
	var isIE = $.browser.msie;
	//---------------------回复页面回复确认提交按钮
	$("#huifusubmit").click(function(){//保存并提交
		if(confirm("是否确认回复？")== true){
			var contenttext1 = $("#content1").val();//取出编辑器textarea里的值
			$content = $("#RCONTENT");
			$content.attr("value",contenttext1);
			if(isIE){//ie兼容性处理
				var editorText = $(window.frames['example'].document).find('iframe').contents().find("body");
				var content = editorText.html();//取出编辑器textarea里的值
				$content = $("#content1");
				$content.attr("value",content);
			}
			document.example.submit();
		}
	});
});

