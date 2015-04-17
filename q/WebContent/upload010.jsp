<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.io.File"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.gree.q.util.Constants"%>
<%@ page import="com.gree.q.util.UploadBean"%>
<%@ page import="com.gree.q.util.db.DbConnection"%>
<%@ page import="com.gree.q.sys.vo.Cbase000VO"%>
<%@ page import="com.gree.q.aq.aqDAOImpl"%>
<%
//设置No-Cache
response.setHeader("pragma", "no-cache");
response.setHeader("Cache-Control", "no-cache, must-revalidate");
response.setHeader("expires", "-1");
		
String user=(String)session.getAttribute("mail");
if(user==null){
	out.println("<br/><br/><center>会话时间已经过期！</center>");
	return;
}
aqDAOImpl adi = new aqDAOImpl();
//通用对象
//业务对象与处理程序
		String opt=request.getParameter("opt");
		String djid=request.getParameter("djid");
		String duta=request.getParameter("duta");
		int dele=Integer.parseInt(request.getParameter("dele")) ;//判断决定删除标签是否能执行删除操作
		
		//opt = "doUpload";
		//djid = "1418625058598";
	Connection conn=null;
    PreparedStatement ps=null;

try{

	String sql="";
	
	if("doUpload".equals(opt)){

	    conn=DbConnection.getConnection();
		String dirname = "";
		UploadBean up =null;
		try{
		up = new UploadBean(Constants.UPLOAD_PATH+dirname);
		up.setSourcefile(request);
		//System.out.println(Constants.UPLOAD_PATH);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		String fs[]=up.getFileNames();
		String fs_[]=up.getSourcefile();
		File f[]=up.getFiles();
		
		sql="insert into tpros010(djid, duta, ffil, fsiz, dire) values(?,?,?,?,?)";
		ps=conn.prepareStatement(sql);

		for(int i=0;i<fs.length && fs_[i]!=null && f[i]!=null && !"".equals(fs_[i].trim());i++){		
		    ps.setLong(1,new Long(djid).longValue());   
			ps.setString(2,fs[i]);
			ps.setString(3,fs_[i]);
			ps.setLong(4,f[i].length());
			ps.setString(5,dirname);
			//System.out.println("fs["+i+"]="+fs[i]);	
			ps.executeUpdate();
		}
		
		adi.setACCESSORIES(djid,"1");
		up=null;
		ps.close();
		conn.close();
		
	}else if("doDelete".equals(opt)){
		//System.out.println("删除===="+duta);
	   conn=DbConnection.getConnection();
		sql="select duta,ffil, fsiz, dire from tpros010 where djid=? ";
		ps=conn.prepareStatement(sql);
		
		ps.setLong(1,new Long(djid).longValue());
        ResultSet rs=ps.executeQuery();
		while(rs.next()){
			File f=new File(Constants.UPLOAD_PATH+rs.getString("duta"));
			f.delete();
		}
		rs.close();
		ps.close();
		
		sql="delete tpros010 where djid=? and duta=? ";
		ps=conn.prepareStatement(sql);
		
	    ps.setLong(1,new Long(djid).longValue());
		ps.setString(2,duta);
		
		ps.executeUpdate();
		rs.close();
		ps.close();
		conn.close();
	}
%>
<html>
<head>
<link href="css/css.css" rel="stylesheet" type="text/css">
</head>
<body>
<form name="tpform" id="tpform" action="upload010.jsp?djid=<%=djid%>" method="post" 
enctype="multipart/form-data" onSubmit="return false;">
<input type="hidden" name="opt" id="opt"/>
<table width="100%" border="1" class="table">
<tr>
<td nowrap align="center">
	<%
	if(dele==1){
		%>
		<input type="file" name="ufile" class="textfile" />
	&nbsp;&nbsp;
		<input type="button" name="btnUpload" value="上传" style="cursor:pointer;"
	onclick="if(ufile.value!=''){this.onclick='';document.all('tpform').action=document.all('tpform').action+'&opt=doUpload&dele=<%=dele%>';submit();}"/>
	</a>
		<%
	}else{
		%>
		<input type="file" name="ufile" class="textfile" disabled="disabled"/>
	&nbsp;&nbsp;
		<input type="button" name="btnUpload" class="bu" value="上传"/>
		<%
	}
%>
	
</td>
</tr>
</table>
<table width="100%" border="1" class="table">
<tr>
<td nowrap class="table_td_head">文件名称</td>
<td nowrap class="table_td_head">文件大小</td>
<td nowrap class="table_td_head">下载</td>
<td nowrap class="table_td_head">删除</td>
</tr>
<%
	conn=DbConnection.getConnection();
	sql="select djid, duta, ffil, fsiz, dire from tpros010 where djid=? ";
	ps=conn.prepareStatement(sql);
	ps.setLong(1,new Long(djid).longValue());
	ResultSet rs=ps.executeQuery();
	if(rs == null){
		adi.setACCESSORIES(djid,"0");
	}
	DecimalFormat df = new DecimalFormat("#,###,###0.00");
	double size=0;
	while(rs.next()){
		
%>
<tr>
<td nowrap><%=rs.getString("ffil")%></td>
<td nowrap>
<%
		size = rs.getDouble("fsiz");
		if (size >= Math.pow(1024, 2)) {
			size /= Math.pow(1024, 2);
%>
	<%=df.format(size)%>MB
<%			
		}else if (size >= 1024) {
			size /= 1024;
%>
	<%=df.format(size)%>KB
<%	
		}else{
%>
	<%=df.format(size)%>B
<%	
		}
%>
</td>
<td nowrap>
	<a style="cursor:pointer;" onMouseOver="this.style.textDecoration='underline';" onMouseOut="this.style.textDecoration='none';" onClick="doDownload('<%=rs.getString("duta")%>','<%=rs.getString("dire")%>','<%=rs.getString("ffil")%>',document.all('tpform'));">
		下载
	</a>
</td>
<td nowrap>
<%
	if(dele==1){
		%>
		<a style="cursor:pointer;" onClick="this.onclick='';document.all('tpform').action=document.all('tpform').action+'&opt=doDelete&duta=<%=rs.getString("duta")%>&dele=<%=dele %>';submit();">
		删除
	</a>
		<%
	}else{
		%>
		<a>删除</a>
		<%
	}
%>
</td>
</tr>
<%
	}
	rs.close();
    ps.close();
    conn.close();
%>
</table>
</form>
</body>
<script language="javascript">
<!--
history.forward(1);
document.oncontextmenu=function(){ 
	return false;
}

function doDownload(file,dire,filename,forms){
	alert(forms.action);
	var act=forms.action;
	forms.action="download.do?file="+file+"&dire="+dire+"&filename="+filename;
	forms.target=true;
	forms.submit();
	forms.target="";
	forms.action=act;
}

//-->
</script>
</html>
<%
}
catch(Exception e){
	e.printStackTrace();
}
%>