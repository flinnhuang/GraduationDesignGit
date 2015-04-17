<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.gree.q.util.LdapUtil"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gree.q.sys.mo.Cbase000MO"%>
<%@ page import="com.gree.q.sys.vo.SysType"%>
<%@ page import="com.gree.q.sys.mo.Aqus001MO"%>
<%@ page import="com.gree.q.sys.mo.MpMo"%>
<%@ page import="com.gree.q.util.MailUtil"%>
<%@ page import="com.gree.q.aq.aqDAOImpl"%>
<%
request.setCharacterEncoding("ISO-8859-1");
Aqus001MO aq001mo = Aqus001MO.newInstance();
MpMo mpmo = MpMo.newInstance();
String opt = request.getParameter("opt");
String opt1 = request.getParameter("opt1");
String isupdate = request.getParameter("isupdate");
String zj = request.getParameter("zj")!= null ? (String)request.getParameter("zj") : "";//追加？zhuijia
if(isupdate == null||isupdate == ""){
	isupdate = "";
}
String qid = request.getParameter("qqid");
if(qid == null||qid == ""){
	qid = System.currentTimeMillis()+"";
}
String mail = request.getParameter("mail");
String titl = request.getParameter("titl");
if(titl == null) titl = "";
titl = new String(titl.getBytes("ISO-8859-1"), "UTF-8");
String qsystem = new String(request.getParameter("systype").getBytes("ISO-8859-1"), "UTF-8");
String qtype = new String(request.getParameter("type").getBytes("ISO-8859-1"), "UTF-8");
String degree = request.getParameter("degree");
String content = request.getParameter("content1");
content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
if(zj.equals("zhuijia")){
	String htDa = mpmo.getItem(qid, "q_note");
	content = htDa+"<br/>追加问题：<br/>"+content;
}
System.out.println(">>>>>"+request.getParameter("content1"));
int result=0;
if("doSave".equals(opt)){
	System.out.println("doSave---"+opt);
	Map aqus001vo=new HashMap(); 
	aqus001vo.put("q_id",qid);	//流水号
	aqus001vo.put("q_mail",mail);//用户邮箱号
	aqus001vo.put("q_titl",titl);//主题
	aqus001vo.put("q_system",qsystem);//错误系统分类
	aqus001vo.put("q_type",qtype);//问题类型
	aqus001vo.put("q_note",content);//回复内容
	aqus001vo.put("q_degree",degree);//紧急程度
	aqus001vo.put("q_sta",0);//星级评分
	aqus001vo.put("q_accessories",0);//附件，0为无附件
	

//	System.out.println("aqUpdateMethor.jsp页面提交发送邮件功能输出测试------------------");
	int ii = 0;
	if("onlySave".equals(opt1)){
		aqus001vo.put("q_havesubmit",1);//未提交状态
		if(isupdate.equals("isupdate")){
			ii = aq001mo.update(aqus001vo);
		}else{
			ii = aq001mo.insert(aqus001vo);
		}
		
		if(ii > 0){
			out.print("<script language='javascript' type='text/javascript'> "
					+"window.close();"
					+"parent.location.reload();"
					+"</script> ");
		}else{
			out.print("<script language='javascript' type='text/javascript'> "
					+"alert('error！');"
					+"window.close();"
					+"</script> ");
		}	
	}//只保存，不提交
	else{
		aqus001vo.put("q_havesubmit",0);//提交状态
		if(isupdate.equals("isupdate")){
			ii = aq001mo.update(aqus001vo);
		}else{
			ii = aq001mo.insert(aqus001vo);
		}
			
		if(ii > 0){
			MailUtil mailutil = new MailUtil();
			aqDAOImpl qd = new aqDAOImpl();   
			List list = qd.getSysUser(qsystem);
			if(list.size()>0){
				String notes = "由用户"+mail+"发送";
				for(int i = 0;i<list.size();i++){
					System.out.println("list长度="+list.size());
					String sysUserId = (String)list.get(i);
					if(sysUserId != null){
						System.out.println("查找错误系统对应用户成功，返回值="+sysUserId);
						mailutil.sendmail(sysUserId, titl, notes, Integer.parseInt(degree));
					}
				}
				out.print("<script language='javascript' type='text/javascript'> "
						+"alert('提交成功，系统将发送邮件给相应负责人处理！请等待邮件回复！');"
						+"window.close();"
						+"parent.location.reload();"
						+"</script> ");
			}else{
				out.print("<script language='javascript' type='text/javascript'> "
						+"alert('error！');"
						+"window.close();"
						+"</script> ");
				}
		}else{
			out.print("<script language='javascript' type='text/javascript'> "
				+"alert('error！');"
				+"window.close();"
				+"</script> ");
		}
	}	
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加、修改，更新到数据库jsp方法页面</title>
</head>
<body>

</body>
</html>