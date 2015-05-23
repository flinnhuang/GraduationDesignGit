package com.main.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.QANDRTIME; 
import com.model.SYSTEMS0;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MainDaoimpl {
	public MainDaoimpl(){
		
	}
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	//计算处理花费时间
	public String calcuTime(int qid,Session session){
		int SECOND_DAY = 86400;   //60*60*24
		int SECOND_HOUR = 3600;   //60*60
		int SECOND_MIN = 60;   //60
		String result = "";
		String str = "处理花费时间：";
		String str2 = "";
		int second = 0;
		try{
			String sql = "select datediff(second, CREATETIME, REPLYTIME) ti from QANDRTIME " +
					"where QID = "+qid;
			//System.out.println("...........sql......"+sql);
			Query queryObject=session.createSQLQuery(sql);
			List list =queryObject.list();
			if(list.get(0) == null){
				result = "0";
			}else{
				result = list.get(0).toString();
			}
			second = Integer.parseInt(result);
			 
			if(second<=0){
				str = "";
				str2 = "未处理";
				String sql2 = "select datediff(second, CREATETIME, getdate()) ti from QANDRTIME " +
						"where QID = "+qid;
				//System.out.println("...........sql2......"+sql2);
				Query queryObject2=session.createSQLQuery(sql2);
				List list2=queryObject2.list();
				result = list2.get(0).toString();
				if(list2.get(0) == null){
					result = "0";
				}else{
					result = list2.get(0).toString();
				}
				second = Integer.parseInt(result);
			}
			//System.out.println("......................"+result);
			int day = second / SECOND_DAY;;//天
			int leftSeconds = second % SECOND_DAY;
			int hour = leftSeconds / SECOND_HOUR;//时
			int min=(leftSeconds % SECOND_HOUR) / SECOND_MIN;//分
			//System.out.println("......num="+num+"...day="+day+"...hour="+hour+"...minute="+min+"..");
			if(day==0){
				result = str+hour+"小时"+min+"分钟"+str2;
			}
			if(day==0 && hour==0){
				result = str+min+"分钟"+str2;
			}
			if(day!=0&&hour!=0 || day!=0&&hour==0){
				result = str+day+"天"+hour+"小时"+min+"分钟"+str2;
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	//取得问题系统数据
	public List<SYSTEMS0> getSyslist(){
		List<SYSTEMS0> list = new ArrayList();
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String queryString = "from SYSTEMS0";
			Query queryObject=session.createQuery(queryString);
			list=queryObject.list();
			if(list.size()==0){
				list = null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
		return list;
	}
	//根据问题系统取得负责人邮箱
	public List getSysuser0Email(String sysname){
		List list = new ArrayList();
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String queryString = "select su.YHMC from SYSUSER0 su where su.SYSID=" +
					"(select st.ID from SYSTEMS0 st where st.SYSNAME='"+sysname+"')";
			Query queryObject=session.createSQLQuery(queryString);
			List list1=queryObject.list();
			System.out.println("取得问题系统管理员数"+list1.size());
			if(list1.size()==0){
				list = null;
			}else{
				for(int i=0;i<list1.size();i++){
					String yhmc = list1.get(i).toString();
					System.out.println("系统管理员"+yhmc);
					if(yhmc != null){
						String sql = "select u.SYSEMAIL from USER0000 u where u.YHMC ='"+yhmc+"'";
						Query queryObject2=session.createSQLQuery(sql);
						List list2=queryObject2.list();
						String mail = list2.get(0).toString();
						list.add(mail);
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
		return list;
	}
	//根据yhmc取得邮箱
	public List getEmail(String yhmc){
		List list = new ArrayList();
		String email = "";
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String sql = "select u.SYSEMAIL from USER0000 u where u.YHMC ='"+yhmc+"'";
			Query queryObject=session.createSQLQuery(sql);
			List list1=queryObject.list();
			System.out.println("取得邮箱数"+list1.size());
			if(list1.size()==0){
				list = null;
			}else{
				email = list1.get(0).toString();
				list.add(email);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return list;
		}finally{
			session.close();
		}
		return list;
	}
	//根据问题qid取得问题及回复内容
	public String getqrallinfo(int qid){
		Map row = new HashMap();
		String str = "";
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String queryString1 = "select QCONTENT,LCNUM,CONVERT(varchar(100), CREATETIME, 120) ctime from QUESTION su where su.QID="+qid+"";
			Query queryObject=session.createSQLQuery(queryString1);
			List list1=queryObject.list();
			
			String queryString2 = "select RCONTENT,LCNUM,CONVERT(varchar(100), REPLYTIME, 120) rtime from REPLY000 re where re.QID="+qid+"";
			Query queryObject2=session.createSQLQuery(queryString2);
			List list2=queryObject2.list();
			System.out.println("取得QUESTION内容数"+list1.size());
			System.out.println("取得REPLY000内容数"+list2.size());
			
			for(int i=0;i<list1.size();i++){
				Object obj[] = (Object[])list1.get(i);
				if(obj != null){
					String QCONTENT = obj[0].toString();
					String LCNUM = obj[1].toString();
					String ctime = obj[2].toString();
					row.put(""+LCNUM, "<p><b>"+LCNUM+"楼</b>("+ctime+")</p><hr/><b>问题：</b><br/>"+QCONTENT);
				}	
			}
			for(int i=0;i<list2.size();i++){
				Object obj[] = (Object[])list2.get(i);
				if(obj != null){
					String RCONTENT = obj[0].toString();
					String LCNUM = obj[1].toString();
					String rtime = obj[2].toString();
					row.put(""+LCNUM, "<p><b>"+LCNUM+"楼</b>("+rtime+")</p><hr/><b>回复：</b><br/>"+RCONTENT);
				}
			}
			System.out.println("row内容数"+row.size());
			for(int i=1;i<=row.size();i++){
				str = str + row.get(""+i)+"<p><br/></p>";
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
		
		return str;
	}
	//取得问题创建回复时间[QANDRTIME]表数据
	public QANDRTIME getQANDRTIME(int qid){
		Session session = null;
		QANDRTIME qr = new QANDRTIME();
		try{
			session = sessionFactory.openSession();
			String queryString = "from QANDRTIME where QID = "+qid+"";
			Query queryObject=session.createQuery(queryString);
			List<QANDRTIME> list=queryObject.list();
			if(list.size()==0){
				qr = null;
			}else{
				qr = list.get(0);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
		return qr;
	}
	
}
