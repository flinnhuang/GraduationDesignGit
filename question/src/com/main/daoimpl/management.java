package com.main.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.QANDRTIME; 
import com.model.QUESTION;
import com.model.SYSTEMS0;
import com.model.SYSUSER0;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class management {
	public management(){
		
	}
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	//根据条件，得出数据总数。分页方法
	public int getCountformanagement(String table, String filter, String searchtype) {
		int result = 0;
		Session session = null;
		try{
			session=sessionFactory.openSession();
			String titltemp = "";
			
			if (!"".equals(filter)){//根据传入的搜索字符，添加相应sql查询语句
//				System.out.println("传入方法中的filter="+filter);
				if(searchtype == "" ||searchtype ==null ){
					titltemp = " where SYSNAME like '%" + filter + "%' ";
				}
				else{
					titltemp = " where "+searchtype+" like '%" + filter + "%' ";
				}
			}
			String sql2 = "";
			if(table.equals("SYSUSER0")){//系统负责人表
				sql2 = "select count(ID) from "+table+" su"+titltemp;
			}
			if(table.equals("SYSTEMS0")){//系统[SYSTEMS0]表
				sql2 = "select count(ID) from "+table+" s"+titltemp;
			}
			
			Query queryObject2=session.createSQLQuery(sql2);
			List list2=queryObject2.list();
			if(list2.size()<1 || list2.get(0) == null){
				return -1;
			}else{
				result = Integer.parseInt(list2.get(0).toString());
			}
			System.out.println("........取出总数............"+result);

			
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally{
			session.close();
		}
		return result;
	}
	//回复页面，查找问题数据，条件：系统负责人负责系统，搜索条件，分页查询
	public List queryinfoformanagement(String table, String filter,String searchtype, int start, int end) {
		int rowpage = end-start;
		List result = new ArrayList();
		MainDaoimpl mdi = new MainDaoimpl();
		Session session = sessionFactory.openSession();
		String titltemp = "";
		if (!"".equals(filter)){//根据传入的搜索字符，添加相应sql查询语句
//			System.out.println("传入方法中的filter="+filter);
			if(searchtype == "" ||searchtype ==null ){
				titltemp = " where SYSNAME like '%" + filter + "%' ";
			}
			else{
				titltemp = " where "+searchtype+" like '%" + filter + "%' ";
			}
		}
		
		if(table.equals("SYSUSER0")){//系统负责人表
			String sql2 = "from "+table+" su"+titltemp;
			Query queryObject2=session.createQuery(sql2);
			List list2=queryObject2.list();
			queryObject2.setFirstResult(start);
			queryObject2.setMaxResults(rowpage);
			List<SYSUSER0> list3 =queryObject2.list();
			int lenth = list3.size();
			for(int i = 0; i < lenth ;i++){
				Map row = new HashMap();
				SYSUSER0 su = list3.get(i);
				if(su != null){
					row.put("iid", su.getID());
					row.put("a", i+1);
					row.put("b", su.getYHMC());
					row.put("c", su.getSYSNAME());
					result.add(row);
				}	
			}
		}
		if(table.equals("SYSTEMS0")){//系统[SYSTEMS0]表
			String sql2 = "from "+table+" s"+titltemp;
			Query queryObject2=session.createQuery(sql2);
			List list2=queryObject2.list();
			queryObject2.setFirstResult(start);
			queryObject2.setMaxResults(rowpage);
			List<SYSTEMS0> list3 =queryObject2.list();
			int lenth = list3.size();
			for(int i = 0; i < lenth ;i++){
				Map row = new HashMap();
				SYSTEMS0 s = list3.get(i);
				if(s != null){
					row.put("iid", s.getID());
					row.put("a", i+1);
					row.put("b", s.getID());
					row.put("c", s.getSYSNAME());
					result.add(row);
				}	
			}
		}
		session.close();
		if(result.size()>0){
			return result;
		}
		else{
			return null;
		}
	}
	public boolean adddateformanagement(String yhmc,String sysname) {
		boolean flag = false;
		Session session=null;
		Transaction transaction = null;
		try{
			session=sessionFactory.openSession();
			String sql1 = "from SYSTEMS0 where SYSNAME = '"+sysname+"'";
			Query queryObject1=session.createQuery(sql1);
			List<SYSTEMS0> list1=queryObject1.list();
			SYSTEMS0 sys = new SYSTEMS0();
			sys = list1.get(0);
			
			SYSUSER0 suser = new SYSUSER0();
			suser.setSYSNAME(sysname);
			suser.setSystems0(sys);
			suser.setYHMC(yhmc);
			
			transaction = session.beginTransaction();
			session.save(suser);//在缓存中保存数据，受影响行数
			transaction.commit();//写入数据库表
			System.out.println("---添加问题--");
			flag = true;//修改成功
		}catch(Exception e){
			e.printStackTrace();
			return flag;
		}finally{
			session.close();
		}
		// TODO Auto-generated method stub
		return flag;
	}
	public boolean adddateformanagement(String sysname) {
		boolean flag = false;
		Session session=null;
		Transaction transaction = null;
		try{
			session=sessionFactory.openSession();

			SYSTEMS0 s = new SYSTEMS0();
			s.setSYSNAME(sysname);
			
			transaction = session.beginTransaction();
			session.save(s);//在缓存中保存数据，受影响行数
			transaction.commit();//写入数据库表
			System.out.println("---添加问题--");
			flag = true;//修改成功
		}catch(Exception e){
			e.printStackTrace();
			return flag;
		}finally{
			session.close();
		}
		// TODO Auto-generated method stub
		return flag;
	}
	public boolean deletedateformanagement(String table,int id) {
		Session session=null;
		try{
			session = sessionFactory.openSession();
			String sql = "delete from "+table+" where ID = "+id;
			Query queryObject=session.createSQLQuery(sql);
			int r =queryObject.executeUpdate();
			if(r<=0){
				return false;
			}
			System.out.println("---删除"+table+"---"+id+"--");
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}
		// TODO Auto-generated method stub
		return true;
	}
	//判断在表中有没有相应的值,在员工表中权限不为员工
	public boolean isintable(String table,String ttype,String tdate) {
		Session session=null;
		try{
			session = sessionFactory.openSession();
			String sql = "select * from "+table+" where "+ttype+" = '"+tdate+"'";
			if(table.equals("USER0000")){
				sql = sql+" and YHLX != 'staff'";
			}
			Query queryObject2=session.createSQLQuery(sql);
			List list2=queryObject2.list();
			if(list2.size()<1 || list2.get(0) == null){
				return false;
			}
			System.out.println("---"+table+"---"+ttype+"--"+tdate);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}
		// TODO Auto-generated method stub
		return true;
	}
}
