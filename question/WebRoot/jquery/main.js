encodeURI("utf-8");
$(function(){
	$('#tabs').tabs('add',{
		id: "TIP1",
        title: "系统信息",
        content: '<iframe id="if_TIP1" frameborder="false" class="tab_iframe" src="sysinfo.jsp"></iframe>',  
        closable: false,
		tools:[{
			iconCls: 'icon-mini-refresh',
			handler:function(){
				$('#if_TIP1').attr('src', 'sysinfo.jsp');
			}
		}]
    });
	$('#m1').click(function(){
		doAddTab("TIP2", "abnormal_feedback.jsp", "异常反馈");
	});
	
    $('#m2').click(function(){
		doAddTab("TIP3", "abnormal_handling.jsp", "异常处理");
	});
    
    $('#m3').click(function(){
		doAddTab("TIP4", "statistical_query.jsp", "统计查询");
	});
    
    $('#m4').click(function(){
		doAddTab("TIP5", "management.jsp", "系统管理");
	});
	
	$('#btnLogout').click(function(){
		$.messager.confirm('系统信息', '您真的要退出系统吗?', function(r){
		   if (r){
				window.location.href="index.html";
		   }
		});
	});
	function doAddTab(id, url, text){
	    var index = 0;
	    var exists = false;
	    var tt = $("#tabs").tabs('getTab', index);
	    while(tt != null){
	       if($(tt).attr('id') == id){
	           exists = true;
	           break;
	       }
	       index++;
	       tt = $("#tabs").tabs('getTab', index);
	    }
	    
	    if(!exists){
	       $('#tabs').tabs('add',{
	           id: id,
	            title: text,
	            content: '<iframe id="if_'+id+'" frameborder="false" class="tab_iframe" src="'+url+'"></iframe>',
	            closable: true,
	            fit: true,
	            style: 'overflow:auto;',
	            tools:[{
	                iconCls: 'icon-mini-refresh',
	                handler:function(){
	                  $('#if_'+id).attr('src', url);
	                }
	            }]
	        });
	    }else{
	       $('#tabs').tabs('select',index);
	    }
	}
	//下一页
	var $next = $(".next");
	$next.click(function(){
		var $noncepage = $("#noncepage");
		var numpage = $noncepage.attr("value")-0+1;
		$noncepage.attr("value",numpage);
		$frm.submit();
	});
	//上一页
	var $prev = $(".prev");
	$prev.click(function(){
		var $noncepage = $("#noncepage");
		var numpage = $noncepage.attr("value")-0-1;
		$noncepage.attr("value",numpage);
		$frm.submit();
	});
	//刷新、跳页
	var $subm = $(".subm");
	$subm.click(function(){
		var str = $(":radio:checked").val();
		if(str == null||str == ""){
			str="TITLE";
		}
		$("#radiochecked").attr("value",str);
		$frm.submit();
	});
	//当前页
	var $current = $(".current");
	$current.click(function(){
		var $noncepage = $("#noncepage");
		var numpage = $(this).attr("value");
		$noncepage.attr("value",numpage);
		$frm.submit();
		$(this).css({
			"border":10+"px"
		});
	});
	//首页
	var $first = $(".first");
	$first.click(function(){
		var $noncepage = $("#noncepage");
		var numpage = 1;
		$noncepage.attr("value",numpage);	
		$frm.submit();
	});
	//尾页
	var $last = $(".last");
	$last.click(function(){
		var $allpages = $("#allpages");
		var $noncepage = $("#noncepage");
		var numpage = $allpages.attr("value")-0;
		alert(numpage);
		$noncepage.attr("value",numpage);
		$frm.submit();
	});
	//搜索
	var $search = $(".search");
	$search.click(function(){
		var str = $(":radio:checked").val();
		if(str == null||str == ""){
			str="TITLE";
		}
		$("#radiochecked").attr("value",str);
		var $noncepage = $("#noncepage");
		$noncepage.attr("value","1");
		$frm.submit();	
	});
});