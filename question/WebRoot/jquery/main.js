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
		doAddTab("TIP2", "qm.jsp", "异常反馈");
	});
	
    $('#m2').click(function(){
		doAddTab("TIP3", "b.jsp", "异常处理");
	});
    
    $('#m3').click(function(){
		doAddTab("TIP4", "c.jsp", "统计查询");
	});
    
    $('#m4').click(function(){
		doAddTab("TIP5", "allCount.jsp", "全部异常");
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
});