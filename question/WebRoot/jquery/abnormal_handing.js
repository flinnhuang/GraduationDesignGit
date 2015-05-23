encodeURI("utf-8");
$(function(){
	
	var str = $("#radiochecked").attr("value");
	if(str == null||str == ""){
		str="TITLE";
	}
	$(":radio").val([str]);//单选按钮初始选择
	changeText(str);//更改搜索框input前的提示搜索信息
	
	$("[name=searchType]:radio").click(function(){
		var str2 = $("[name=searchType]:radio:checked").attr("value");
		changeText(str2);
	})
	$frm = $("#frm");

})

   function show(qqid){
    	//alert("5111315151");
    	var divhtml = "<div id='light' class='white_content2'>"+
    	"<p align='right' class='imgtop'> "+
    	"<a href = 'javascript:void(0)' id='close'>"+
    	"<img src='images/close.png' title='关闭'/>"+
    	" </a></p><div id='qinfo'>"+
    	"</div></div> "+
    	"<div id='fade' class='black_overlay'></div> ";
    	//alert(divhtml);
    	$("body").append(divhtml);
    	$("#qinfo").load("b/subpage/demoOnlyinfo.jsp?qqid="+qqid+"");
    	document.getElementById('light').style.display='block';
    	document.getElementById('fade').style.display='block';
    	
    	$("#close").click(function(){
    		document.getElementById('light').style.display='none';
        	document.getElementById('fade').style.display='none';
    	});
	}
    
    //更改搜索框input前的提示搜索信息
    function changeText(str){
    	var $searchname = $("#searchname");
    	switch (str) {
    	case "YHMC":
    		$searchname.html("提交用户：");
    		break;
    	case "SYSNAME":
    		$searchname.html("问题系统：");
    		break;
    	case "QTYPE":
    		$searchname.html("系统分类：");
    		break;
    	case "DEGREE":
    		$searchname.html("紧急程度：");
    		break;
    	case "REPLYSTATE":
    		$searchname.html("已(未)回复：");
    		break;
    	default:
    		$searchname.html("主题：");
    		break;
    	}
    }
    //回复页面图标链接点击事件
    function exam(qqid){
    	//	alert(qqid);
            $('#win_add_iframe2').attr('src', "b/subpage/replyquestion.jsp?qqid="+qqid);
            $('#win_add2').window("open");
        }
