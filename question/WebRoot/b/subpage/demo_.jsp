<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
request.setCharacterEncoding("UTF-8");
String hta = (String)request.getAttribute("cont");
String htmlData = request.getAttribute("cont2") != null ? (String)request.getAttribute("cont2") : "";

String replytime = request.getAttribute("replytime") != null ? (String)request.getAttribute("replytime") : "";
request.removeAttribute("cont2");//移除销毁保存到attribute里的相应数据
request.removeAttribute("cont");
request.removeAttribute("replytime");
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
				uploadJson : 'upload_json.jsp',
				fileManagerJson : 'file_manager_json.jsp',
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
				},
				afterBlur: function(){this.sync();}
			});
			prettyPrint();
		});
	</script>
</head>
<body>
	<form name="example" method="post" action="demo_.jsp">
		<table width="1000px" border="0" cellpadding="0">
	 <tr>
	     <td >
	     <%
	     if(hta!=null){
	     %>
	     <p><b>最近回复：（<%=replytime %>）</b><p/>
	     <%=htmlspecialchars(hta)%>
	     <br/><br/><b>追问：</b>
	     <%
	     }
	      %>
	      </td>
	   </tr>
	  <tr>
		<td>
		<textarea id="content1" name="content1" cols="100" rows="8" 
			style="width:1000px;height:335px;visibility:hidden;">
		<%=htmlspecialchars(htmlData)%>
		</textarea>
		<br/>
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