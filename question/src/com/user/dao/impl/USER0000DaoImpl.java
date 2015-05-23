package com.user.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.user.dao.USER0000Dao;
import com.user.model.USER0000;

public class USER0000DaoImpl implements USER0000Dao{
	private SessionFactory sessionFactory;
	public USER0000DaoImpl(){
		
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public boolean userLogin(USER0000 user0000) {//登陆验证方法
		boolean flag = true;
		Session session = null;
		try{
			session=sessionFactory.openSession();
			String queryString="from USER0000 where YHMC=? and YHMM=?";
			Query queryObject=session.createQuery(queryString);
			queryObject.setParameter(0, user0000.getYHMC());
			queryObject.setParameter(1, user0000.getYHMM());
			System.out.println("登录验证方法daoimpl--");
			List list=queryObject.list();
			if(list.size()==0){
				flag=false;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}
		// TODO Auto-generated method stub
		return flag;
	}
	//-------------------------------------*************************--------------------
	@Override
	public int userChangePwd(String username,String oldpwd,String newpwd) {//修改密码方法
		int flag = 0;
		Session session=null;
		Transaction transaction = null;
		try{
			session=sessionFactory.openSession();
			transaction = session.beginTransaction();
			String queryString="from USER0000 where YHMC=? and YHMM=?";
			Query queryObject=session.createQuery(queryString);
			queryObject.setParameter(0, username);
			queryObject.setParameter(1, oldpwd);
			List<USER0000> list=queryObject.list();
			System.out.println("daoimpl方法-修改密码-第一步数据库查询其信息-");
			if(list.size()==1){
				USER0000 us=list.get(0);
				us.setYHMM(newpwd);
				session.update(us);//在缓存中保存数据，受影响行数
				transaction.commit();//写入数据库表
				System.out.println("---修改的数据--"+newpwd+"-查询完毕!");
				flag = 1;//修改成功
			}else{
				flag = 0;//原密码错误
			}
		}catch(Exception e){
			e.printStackTrace();
			return flag;
		}finally{
			session.close();
		}
		// TODO Auto-generated method stub
		return flag;
	}
	@Override
	public USER0000 getUserInfo(String username) {//获取用户信息
		List<USER0000> list = new ArrayList();
		USER0000 us = new USER0000();
		Session session = null;
		try{
			session=sessionFactory.openSession();
			String queryString="from USER0000 where YHMC=?";
			Query queryObject=session.createQuery(queryString);
			queryObject.setParameter(0, username);
			list = queryObject.list();
			
			if(list.size()<=0){
				us = null;
			}else{
				us = list.get(0);
			}
			System.out.println("获取个人的信息------");
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
		// TODO Auto-generated method stub
		return us;
	}

}
