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
	
	//多选框--全选框
	var $checkAll = $("#checkAll");
	$checkAll.click(function(){
		
		if($("#checkAll").attr('checked') == "checked"){
		//	alert($("#checkAll").attr('checked'));
			$('[name=chks]:checkbox').attr('checked',true);
		}else{
			$('[name=chks]:checkbox').attr('checked',false);
		}
	});
	//删除
	var $delete = $("#delete");
	$delete.click(function(){
		if(confirm('是否真的删除已选择的数据？')){
			var $checkedObj = $('input:checkbox[name="chks"]:checked');
			$checkedObj.each(function(){
				var checkedStr = this.value;	
				var divhtml = "<div id='dele' style='display:block;'></div>";
				$("body").append(divhtml);
				$("#dele").load("qmDelete.jsp?qid="+checkedStr+"");
				$("#dele").remove();
			});
			window.location.reload();
		}
	});
})
 /*TODO*/
    function exam(qqid){
	//	alert(qqid);
        $('#win_add_iframe2').attr('src', "b/subpage/aqForb.jsp?qqid="+qqid);
        $('#win_add2').window("open");
    }
    
    
    function closeadd(){ 
    	//alert("1111");
        $('#win_add2').window("close");
        
    }
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
    	case "Q_TYPE":
    		$searchname.html("系统分类：");
    		break;
    	case "DEGREE":
    		$searchname.html("紧急程度：");
    		break;
    	case "REPLYSTATE":
    		$searchname.html("已(未)回复：");
    		break;
    	case "SUBMITSTATE":
    		$searchname.html("已(未)提交：");
    		break;
    	default:
    		$searchname.html("主题：");
    		break;
    	}
    }
    //qm.jsp页面追加图标链接点击事件
    function exam1(qqid){
    	$('.panel-title').text("修改页面");
        $('#win_add_iframe2').attr('src', "b/subpage/questionUpdate.jsp?qid="+qqid);
        $('#win_add2').window("open");
    }
    function exam2(qqid){
		var st =  $("#stars2input").attr("value");
		window.location.href="point.jsp?qqid="+qqid+"&&st="+st;
    }
    function exam3(qqid){
    	$('.panel-title').text("问题追问");
        $('#win_add_iframe2').attr('src', "b/subpage/questionZhuijia.jsp?qqid="+qqid);
        $('#win_add2').window("open");
    }
    function exam4(){
        $('.panel-title').text("添加问题");
        $('#win_add_iframe2').attr('src', "b/subpage/addquestion.jsp");
        $('#win_add2').window("open");
    }
    function showStarPoint(qqid){
    	$("#info").load("star.jsp?qid="+qqid+"");
    	document.getElementById('light1').style.display='block';
    	document.getElementById('fade1').style.display='block';
    }
