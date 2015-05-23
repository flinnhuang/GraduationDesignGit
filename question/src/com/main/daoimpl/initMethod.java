package com.main.daoimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.model.QANDRTIME;
import com.model.QUESTION;

public class initMethod {
	public initMethod(){
		
	}
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public QUESTION initfirstsavequestion(QUESTION qus){//添加问题时，初始化问题数据
		System.out.println("添加问题，初始化!");
		QANDRTIME qr = new QANDRTIME();
		Date nowdate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String nowtime = sdf.format(nowdate);//获取提交时间
		int qid= (int)System.currentTimeMillis();
		qr.setQID(qid);
		try {
			qr.setCREATETIME(sdf.parse(nowtime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("日期转换错误");
			return null;
		}
		System.out.println("String nowtime="+nowtime);
		System.out.println("Date nowdate="+nowdate);
		qus.setQandrtime(qr);
		qus.setSUBMITSTATE(1);//未提交
		qus.setREPLYSTATE(1);//未回复
		qus.setCREATETIME(nowdate);
		qus.setACCESSORY(0);//没有附件
		qus.setGRADE(0);//没有评分
		qus.setLCNUM(1);//初始为1楼
		
		return qus;
	}
	
	public QUESTION initupdatequestion(int qid,int lcnum){//取得楼层数？的问题数据
		QUESTION qus = new QUESTION();
		Session session = null;
		try{
			session=sessionFactory.openSession();
			String queryString="from QUESTION where QID=? and LCNUM=?";
			Query queryObject=session.createQuery(queryString);
			queryObject.setParameter(0, qid);
			queryObject.setParameter(1, lcnum);
			System.out.println("取得楼层数"+lcnum+"的问题数据");
			List list=queryObject.list();
			if(list.size()==0){
				qus=null;
			}
			qus = (QUESTION) list.get(0);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
		return qus;
	}
	public String initemailcontent(String titl, String notes){
		return "<HTML><HEAD><TITLE>格力异常处理系统管理</TITLE>"
		+ "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"
		+ "<STYLE type=text/css>BODY {FONT-SIZE: 9pt; MARGIN: 0px; OVERFLOW: auto; FONT-FAMILY: Verdana, Geneva, Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #ffffff}"
		+ "BODY {FONT-SIZE: 12px; COLOR: #0377C1; FONT-FAMILY: arial}"
		+ "TABLE {FONT-SIZE: 12px; COLOR: #0377C1; FONT-FAMILY: arial}"
		+ "DIV#default {BORDER-RIGHT: black 0px solid; PADDING-RIGHT: 10px; BORDER-TOP: black 0px solid; PADDING-LEFT: 10px; BACKGROUND: #fff; PADDING-BOTTOM: 20px; MARGIN: 10pt auto 20pt 20pt; BORDER-LEFT: black 0px solid; WIDTH: 700px; PADDING-TOP: 10px; BORDER-BOTTOM: black 0px solid; TEXT-ALIGN: left}"
		+ "</STYLE>"
		+ "<BODY>"
		+ "<DIV id=default><B>格力异常处理系统邮件提示:</B><BR>"
		+ "<TABLE width=\"100%\">"
		+ "  <TBODY>"
		+ "  <TR>"
		+ "    <TD colSpan=2>"
		+ " <LI>您好，系统异常: <FONT color=red><U>"
		+ "("+ titl+ ") ;"
		+ notes
		+ "</U></FONT> 。 <FONT color=green>注：请及时<A HREF='http://localhost:8080/question/'>"+"登录"+"</A>"+"系统处理。</FONT></LI></TD></TR></TR>"
		+ "<TR>"
		+ "<TD colSpan=2>"
		+ "<LI><FONT color=red><B>注:</B></FONT> "
		+ "此邮件为系统信息,请不要作回复操作。</LI></TD></TR></TBODY></TABLE></DIV></BODY></HTML>";

	}

}
