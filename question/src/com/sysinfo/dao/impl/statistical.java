package com.sysinfo.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.model.QUESTION;
import com.reply.dao.impl.zsgcReplyDaoImpl;

public class statistical {
	public statistical(){
		
	}
	private QUESTION qu;
	zsgcReplyDaoImpl zsgcrdi;
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	//显示系统平台
	public List showSystem() throws SQLException{
		List list1 = new ArrayList();
		Session session=null;
		try{
			session=sessionFactory.openSession();
			//取出所有的系统平台
			String sql = "select distinct SYSNAME from QUESTION";
			Query queryObject=session.createSQLQuery(sql);
			List list=queryObject.list();
			if(list.size()<0){
				list1=null;
			}
			for(int i=0;i<list.size();i++){
				String system = null;
				system = list.get(i).toString();
				list1.add(system);
			}
		}catch(Exception e){
			e.printStackTrace();
			list1=null;
		}finally{
			session.close();
		}
		return list1;
	}
	//显示类型
	public List showType() throws SQLException{
		List list1 = new ArrayList();
		Session session=null;
		try{
			session=sessionFactory.openSession();
			//取出所有的问题类型
			String sql = "select distinct QTYPE from QUESTION";
			Query queryObject=session.createSQLQuery(sql);
			List list=queryObject.list();
			if(list.size()<0){
				list1=null;
			}
			for(int i=0;i<list.size();i++){
				String QTYPE = null;
				QTYPE = list.get(i).toString();
				list1.add(QTYPE);
			}
		}catch(Exception e){
			e.printStackTrace();
			list1=null;
		}finally{
			session.close();
		}
		return list1;
	}
	//显示严重级别
	public List showDegree() throws SQLException{
		List list3 = new ArrayList();
		list3.add("1");
		list3.add("2");
		list3.add("3");
		return list3;
	}
	//显示统计数据
	public List showCountDeteil() throws SQLException {
		List list1 = showSystem();
		List list2 = showType();
		List list3 = showDegree();
		Session session=null;
		List result = new ArrayList();
		int r = 0;
		
		try{		
			session=sessionFactory.openSession();
			for(int i = 0;i < list1.size();i++){
			String qsystem = (String) list1.get(i);
//			System.out.println("qsystem="+qsystem);
				for(int j =0;j<list2.size();j++){
					
					String qtype = (String) list2.get(j);
//					System.out.println("qtype="+qtype);
					for(int k =0;k<list3.size();k++){									
						String qdegree = (String) list3.get(k);
//						System.out.println("qdegree="+qdegree);
						String sql = "select count(distinct QID) from QUESTION t where SYSNAME='"+qsystem+"'"+"and QTYPE='"+qtype+"'"+"and DEGREE='"+qdegree+"'";
						Query queryObject=session.createSQLQuery(sql);
						List list=queryObject.list();
						System.out.println("sql="+sql);		
						if(list.size()>0){
							r = Integer.parseInt(list.get(0).toString());
						}
			    			result.add(r);									    	
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	System.out.println("size="+result.size());
	return result;
	}
	//根据问题系统取得负责人账号名称
	public List getSysuser0Yhmc(String sysname){
		List list = new ArrayList();
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String queryString = "select YHMC from SYSUSER0 where SYSNAME='"+sysname+"'";
			Query queryObject=session.createSQLQuery(queryString);
			list=queryObject.list();
			System.out.println("根据问题系统取得负责人账号名称数"+list.size());
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
	//根据问题系统及条件查询取出问题数据
	public List showDeteilbySystem(String sys, String filter,String searchtype, int start, int end) throws SQLException{
		List list = new ArrayList();
		Session session = null;
		String titltemp = "LCNUM = 1 ";
		if (!"".equals(filter)){//根据传入的搜索字符，添加相应sql查询语句
			//	System.out.println("传入方法中的filter="+filter);
				if(searchtype == "" ||searchtype ==null ){
					
				}
				else{
					if(searchtype.equals("DEGREE")){
						if(filter.equals("特急")){
							filter = "1";
						}
						else if(filter.equals("急")){
							filter = "2";
						}
						else if(filter.equals("普通")){
							filter = "3";
						}else{
							return null;
						}
					}
					titltemp = titltemp+"and "+searchtype+" like '%" + filter + "%' ";
				}
			}
		try{
			session = sessionFactory.openSession();
			String queryString = "from QUESTION where "+ titltemp +"and SYSNAME='"+sys+"'";
			Query queryObject=session.createQuery(queryString);
			List<QUESTION> list1=queryObject.list();
			System.out.println("根据问题系统取得所有问题数据"+list1.size());
			int lenth = list1.size();
			if(lenth==0){
				list = null;
			}
			for(int i = 0; i < lenth ;i++){
				QUESTION qu = list1.get(i);
				if(qu != null){
					Map row = new HashMap();			
					row.put("q_titl", qu.getTITLE());
					row.put("qqid", qu.getQandrtime().getQID());
					System.out.println("------------问题的qid---"+qu.getQandrtime().getQID());
					row.put("YHMC", qu.getYHMC());
					row.put("q_system", qu.getSYSNAME());
					row.put("q_type", qu.getQTYPE());
					row.put("q_degree", qu.getDEGREE());
					row.put("q_submittime", qu.getCreatetime());
					list.add(row);
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
	//根据系统,搜索条件，得出问题的总数。分页方法
	public int getCountforquestiondeteil(String sys, String filter, String searchtype) {
		int result = 0;
		Session session = null;
		try{
			session=sessionFactory.openSession();
			String titltemp = "";
			
			if (!"".equals(filter)){//根据传入的搜索字符，添加相应sql查询语句
//				System.out.println("传入方法中的filter="+filter);
				if(searchtype == "" ||searchtype ==null ){
					titltemp = " and TITLE like '%" + filter + "%' ";
				}
				else{
					if(searchtype.equals("DEGREE")){
						if(filter.equals("特急")){
							filter = "1";
						}
						else if(filter.equals("急")){
							filter = "2";
						}
						else if(filter.equals("普通")){
							filter = "3";
						}else{
							return 0;
						}
					}
					titltemp = " and "+searchtype+" like '%" + filter + "%' ";
				}
			}
			
			String sql2 = "select count(distinct QID) from QUESTION q " 
					+"where q.SYSNAME = '"+sys+"'"+titltemp+"and q.LCNUM = 1";
			Query queryObject2=session.createSQLQuery(sql2);
			List list2=queryObject2.list();
			if(list2.size()<1 || list2.get(0) == null){
				return -1;
			}else{
				result = Integer.parseInt(list2.get(0).toString());
			}
			System.out.println("........取出问题的总数............"+result);

			
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally{
			session.close();
		}
		return result;
	}
	
	public QUESTION getQu() {
		return qu;
	}
	public void setQu(QUESTION qu) {
		this.qu = qu;
	}
	public zsgcReplyDaoImpl getZsgcrdi() {
		return zsgcrdi;
	}
	public void setZsgcrdi(zsgcReplyDaoImpl zsgcrdi) {
		this.zsgcrdi = zsgcrdi;
	}
	
}
