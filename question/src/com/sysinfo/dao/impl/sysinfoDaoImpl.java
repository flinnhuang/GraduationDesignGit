package com.sysinfo.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sysinfo.dao.sysinfoDao;
import com.model.QUESTION;
public class sysinfoDaoImpl implements sysinfoDao{
	public sysinfoDaoImpl(){
	
	}
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
//根据用户，以条件：状态1未提交（未回复）or状态0已提交（已回复），统计类别（全部、提交、回复），得出统计数。
	public int getCount(String yhmc, int state, String type) {
		int num = 0;
		Session session = null;
		String queryString = "";
		try{
			session=sessionFactory.openSession();
			if(type.equals("all")){
				queryString = "select count(distinct QID) from QUESTION " 
								+"where YHMC = '"+yhmc+"' and LCNUM = 1";
				System.out.print("取出所有问题记录数--");
			}else if(type.equals("submit")){
				queryString = "select count(distinct QID) from QUESTION " 
					+"where YHMC = '"+yhmc+"' and LCNUM = 1 and SUBMITSTATE = " + state;
				System.out.print("取出提交"+state+"的问题记录数--");
			}else if(type.equals("reply")){
				queryString = "select count(distinct QID) from QUESTION " 
					+"where YHMC = '"+yhmc+"' and LCNUM = 1 and REPLYSTATE = " + state
					+" and SUBMITSTATE = 0";
				System.out.print("取出回复"+state+"的问题的记录数--");
			}else{
				return -2;
			}

			//Query queryObject=session.createQuery(queryString);
			Query queryObject=session.createSQLQuery(queryString);
			List list=queryObject.list();
			//System.out.print("----------"+list.get(0));
			if(list.size()==0){
				num = 0;
			}else{
				num = Integer.parseInt(list.get(0).toString());
			}
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally{
			session.close();
		}
		// TODO Auto-generated method stub
		return num;
	}
	//根据用户,搜索条件，得出其保存提交的问题总数。分页方法
	public int getCountforabnormal_feedback(String yhmc, String filter, String searchtype) {
		int result = 0;
		Session session = null;
		try{
			session=sessionFactory.openSession();
			String titltemp = "";
			
			if (!"".equals(filter)){//根据传入的搜索字符，添加相应sql查询语句
//				System.out.println("传入方法中的filter="+filter);
				if(searchtype == "" ||searchtype ==null ){
					titltemp = "TITLE like '%" + filter + "%' and ";
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
					if(searchtype.equals("REPLYSTATE")){
						if(filter.equals("未回复")){
							filter = "1";
						}
						else if(filter.equals("已回复")){
							filter = "0";
						}else{
							return 0;
						}
					}
					if(searchtype.equals("SUBMITSTATE")){
						if(filter.equals("未提交")){
							filter = "1";
						}
						else if(filter.equals("已提交")){
							filter = "0";
						}else{
							return 0;
						}
					}
					titltemp = " and "+searchtype+" like '%" + filter + "%'";
				}
			}
			

			String sql = "select count(distinct QID) from QUESTION q " 
						+"where q.LCNUM = 1"+titltemp;
			Query queryObject=session.createSQLQuery(sql);
			List list=queryObject.list();
			if(list.size()<1 || list.get(0) == null){
				return -1;
			}else{
				result = Integer.parseInt(list.get(0).toString());
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
	//根据系统负责用户,搜索条件，得出负责问题的总数。分页方法
	public int getCountforabnormal_handing(String yhmc, String filter, String searchtype) {
		int result = 0;
		Session session = null;
		try{
			session=sessionFactory.openSession();
			String titltemp = "";
			String sql1 = "select distinct SYSNAME from SYSUSER0 where YHMC ='"+yhmc+"'";
			Query queryObject=session.createSQLQuery(sql1);
			List list=queryObject.list();
			if(list.size()<1 || list.get(0) == null){
				return -1;
			}
			System.out.println("...list长度="+list.size());
			
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
					if(searchtype.equals("REPLYSTATE")){
						if(filter.equals("未回复")){
							filter = "1";
						}
						else if(filter.equals("已回复")){
							filter = "0";
						}else{
							return 0;
						}
					}
					titltemp = " and "+searchtype+" like '%" + filter + "%'";
				}
			}
			
			for(int i =0;i<list.size();i++){
				String qsystem = (String) list.get(i);
				String sql2 = "select count(distinct QID) from QUESTION q " 
						+"where q.SYSNAME = '"+qsystem+"'"+titltemp+"and q.SUBMITSTATE = '0'" +
								"and q.LCNUM = 1";
//				System.out.println(".."+i+"..sql2="+sql2);
				Query queryObject2=session.createSQLQuery(sql2);
				List list2=queryObject2.list();
				if(list2.size()<1 || list2.get(0) == null){
					return -1;
				}else{
					result += Integer.parseInt(list2.get(0).toString());
				}
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
	
}
