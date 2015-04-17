<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gree.q.sys.mo.MpMo"%>
<%
MpMo mpmo = MpMo.newInstance();
String qid = request.getParameter("qqid");//反馈问题id
System.out.println("qmdelete.jsp");
if(qid =="" ||qid == null){
	System.out.println("qmdelete.jsp-----"+qid);
	out.print("<script language='javascript' type='text/javascript'> "
			+"alert('错误！！问题id为空！');"
			+"</script> ");
}else{
	System.out.println("qmdelete.jsp------"+qid);
	if(mpmo.qmDelete(qid) <= 0){//数据库更新不成功
		out.print("<script language='javascript' type='text/javascript'> "
				+"alert('Delete error！');"
				+"</script> ");
	}else{
		out.print("<script language='javascript' type='text/javascript'> "
				+"alert('Delete success！');"
				+"</script> ");
	}
}
%>