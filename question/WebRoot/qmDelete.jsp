<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.question.dao.impl.zsgcQuestionDaoImpl"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
zsgcQuestionDaoImpl zsgcqdi = (zsgcQuestionDaoImpl)wac.getBean("zsgcQuestionDaoImpl");
String qqid = request.getParameter("qid");//反馈问题id
System.out.println("qmdelete.jsp");
if(qqid =="" ||qqid == null){
	System.out.println("qmdelete.jsp-----"+qqid);
	out.print("<script language='javascript' type='text/javascript'> "
			+"alert('错误！！问题id为空！');"
			+"</script> ");
}else{
	int qid = Integer.parseInt(qqid);
	System.out.println("qmdelete.jsp------"+qid);
	if(zsgcqdi.deleteQuestion(qid) == false){//数据库更新不成功
		out.print("<script language='javascript' type='text/javascript'> "
				+"alert('Delete error！qid="+qid+");"
				+"</script> ");
	}
}
%>