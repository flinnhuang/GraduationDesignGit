encodeURI("utf-8");
$(function(){
	
	var str = $("#radiochecked").attr("value");
	if(str == null||str == ""){
		str="su.YHMC";
	}
	$(":radio").val([str]);//单选按钮初始选择
	changeText(str);//更改搜索框input前的提示搜索信息
	
	if(str == "s.SYSNAME"){
		$("#thd").append("<td nowrap class='table_td_head'>"
				+"| 序号"
			+"</td>"
			+"<td nowrap class='table_td_head'>"
			+"| ID"
			+"</td>"
				+"<td nowrap class='table_td_head'>"
				+"| 问题系统"
				+"</td>"
				+"<td nowrap class='table_td_head'>"
				+"|操作"
				+"</td>");
		//$(".SYSUSER0").remove();
	}else{
		$("#thd").append("<td nowrap class='table_td_head'>"
				+"| 序号"
			+"</td>"
			+"<td nowrap class='table_td_head'>"
			+"| 账号"
			+"</td>"
				+"<td nowrap class='table_td_head'>"
				+"| 问题系统"
				+"</td>"
				+"<td nowrap class='table_td_head'>"
				+"|操作"
				+"</td>");
		
		//$(".SYSUSER0")[0].style.display = "block";
	}
	
	$("[name=searchType]:radio").click(function(){
		var str2 = $("[name=searchType]:radio:checked").attr("value");
		changeText(str2);
	})
	$frm = $("#frm");

})
    
    //更改搜索框input前的提示搜索信息
    function changeText(str){
    	var $searchname = $("#searchname");
    	switch (str) {
    	case "su.YHMC":
    		$searchname.html("系统负责人管理    账号：");
    		break;
    	case "su.SYSNAME":
    		$searchname.html("系统负责人管理    问题系统：");
    		break;
    	case "s.SYSNAME":
    		$searchname.html("系统列表管理    问题系统：");
    		break;
    	default:
    		$searchname.html("系统负责人管理    账号：");
    		break;
    	}
    }

