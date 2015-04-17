<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
request.setCharacterEncoding("gb2312");
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
String htDa = request.getAttribute("cont2") != null ? (String)request.getAttribute("cont2") : "";
htmlData = new String(htmlData.getBytes("ISO-8859-1"), "UTF-8");
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>KindEditor JSP</title>
	<link rel="stylesheet" href="../themes/default/default.css" />
	<link rel="stylesheet" href="../plugins/code/prettify.css" />
	<script charset="utf-8" src="../kindeditor.js"></script>
	<script charset="utf-8" src="../lang/zh_CN.js"></script>
	<script charset="utf-8" src="../plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content1"]', {
				cssPath : '../plugins/code/prettify.css',
				uploadJson : '../jsp/upload_json.jsp',
				fileManagerJson : '../jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
</head>
<body>
	<form name="example" method="post" action="demo.jsp">
		<%=htDa%>
		<table width="1000px" border="0" cellpadding="0">
	 <tr>
	     <td >
	      </td>
	   </tr>
	  <tr>
		<td>
		<textarea id="content1" name="content1" cols="100" rows="8" style="width:1000px;height:400px;visibility:hidden;">
		<%=htmlspecialchars(htmlData)%>
		</textarea>
		<br />
		</td>
		</tr>
		</table>
</form>
</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>