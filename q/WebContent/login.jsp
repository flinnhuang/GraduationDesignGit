<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.gree.q.util.DateTime"%>
<%@ page import="com.gree.q.util.LdapUtil"%>
<%@ page import="java.util.*,java.security.MessageDigest,java.math.BigInteger,javax.servlet.http.Cookie,java.text.SimpleDateFormat"%>
<%
    String action="noaction";
    action=request.getParameter("action");
	System.out.println("login.jsp");
    if(action != null && action.equals("1")){
    	String uid = request.getParameter("usid").trim();
		String psw = request.getParameter("pawd").trim();
		System.out.println(uid+psw);
		Map map=LdapUtil.authenticate(uid, psw);
		
		if(map==null){
		//	response.sendRedirect("index.jsp");
			//out.println("<br/><br/><center>没有该用户！</center>");
			out.print("<script language='javascript' type='text/javascript'> "
					+"window.history.go(-1);"
					+"</script> ");
			response.getWriter().write("error");
			return;
		}else{
			Date nowdate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String nowtime = sdf.format(nowdate);//获取时间
			//设置cookie的名称、内容、路径、域、保存时间等
			String serverName = request.getServerName();
			System.out.println("serverName ="+serverName);
			Cookie cookie = new Cookie("greeProjectAutoLogin","000-000000000000");
			cookie.setPath("/"); 				//如果路径为/则为整个tomcat目录有用
			cookie.setDomain(serverName);	//设置对该网站的域名效
			int time = 1*60*60*14;//時間設置為14小時
			cookie.setMaxAge(time);//設置cookie保存時間
		//设置cookie结束
		//msg验证信息加密
			String msg = "greeProjectLoginSuccess"+uid+nowtime;
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(msg.getBytes());
			BigInteger sha = new BigInteger(md.digest());
			msg = sha.toString(32);
		//msg验证信息加密结束
		//添加cookie
			cookie.setValue(uid+"-"+msg);
			response.addCookie(cookie);
		//end
		
			response.sendRedirect("main.jsp");
			session.setAttribute("mail", map.get("mail"));
			session.setAttribute("department", map.get("department"));
			session.setAttribute("displayname", map.get("displayname"));
			session.setAttribute("company", map.get("company"));
			session.setMaxInactiveInterval(9000);//设置session失效时间，单位为秒，此为15分钟后失效
			response.getWriter().write("success");
		}
  } 
%>