<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gree.q.sys.mo.MpMo"%>
<%
MpMo mpmo = MpMo.newInstance();
String qid = request.getParameter("qid");//问题id
System.out.println("bRepeal.jsp-----"+qid);
if(qid =="" ||qid == null){
	out.print("<script language='javascript' type='text/javascript'> "
			+"alert('错误！！问题id为空！');"
			+"history(-1);"
			+"</script> ");
}else{
	System.out.println(""+qid);
	if(mpmo.bRepeal(qid) <= 0){//数据库更新不成功
		out.print("<script language='javascript' type='text/javascript'> "
				+"alert('撤销失败 error！');"
				+"window.location.href='b.jsp';"
				+"</script> ");
	}else{
		out.print("<script language='javascript' type='text/javascript'> "
			+"alert('Repeal success！');"
			+"window.location.href='b.jsp';"
			+"</script> ");
	}
}
%>