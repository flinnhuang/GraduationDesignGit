<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gree.q.util.MailUtil"%>
<%@ page import="com.gree.q.aq.aqDAOImpl"%>
<%@ page import="com.gree.q.sys.mo.MpMo"%>
<%
MailUtil mailutil = new MailUtil();
MpMo mpmo = MpMo.newInstance();
String u=(String)session.getAttribute("mail");//当前登录用户邮箱id
int l = (u.indexOf("@")<=0)?u.length():u.indexOf("@");
String qmail = u.substring(0, l);
String qid = request.getParameter("qid");//反馈问题id

if(qid =="" ||qid == null){
	out.print("<script language='javascript' type='text/javascript'> "
			+"alert('错误！！问题id为空！');"
			+"window.location.href='qm.jsp';"
			+"</script> ");
}else{
	if(mpmo.qmSubmit(qid) <= 0){//数据库更新不成功
		out.print("<script language='javascript' type='text/javascript'> "
				+"alert('Submit error！');"
				+"window.location.href='qm.jsp';"
				+"</script> ");
	}
	
	String titl = mpmo.getItem(qid,"Q_TITL");
	int de = Integer.parseInt(mpmo.getItem(qid,"Q_DEGREE"));
	String systype = mpmo.getItem(qid, "Q_SYSTEM");
	System.out.println("+++++++++++++++++++++++++qid="+qid+"-- titl="+titl+"-- qmail="+qmail);

	aqDAOImpl qd = new aqDAOImpl();   
	List list = qd.getSysUser(systype);
	if(list.size()>0){
		String notes = "由用户"+qmail+"发送";
		for(int i = 0;i<list.size();i++){
			System.out.println("list长度="+list.size());
			String sysUserId = (String)list.get(i);
			if(sysUserId != null){
				System.out.println("查找错误系统对应用户成功，返回值="+sysUserId);
				mailutil.sendmail(sysUserId, titl, notes, de);
			}
		}
		out.print("<script language='javascript' type='text/javascript'> "
				+"alert('提交成功，系统将发送邮件给相应负责人处理！请等待邮件回复！');"
				+"window.location.href='qm.jsp';"
				+"</script> ");
	}
}


%>