<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%
System.out.println("清空session,退出登录");
session.invalidate();
response.sendRedirect("index.jsp");
%>