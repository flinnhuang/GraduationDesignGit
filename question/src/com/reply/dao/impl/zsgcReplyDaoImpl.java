package com.reply.dao.impl;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.QANDRTIME;
import com.model.QUESTION;
import com.model.REPLY000;
import com.main.daoimpl.MainDaoimpl;
import com.main.daoimpl.initMethod;
import com.question.dao.impl.zsgcQuestionDaoImpl;
import com.reply.dao.zsgcReplyDao;
import com.user.model.USER0000;

public class zsgcReplyDaoImpl implements zsgcReplyDao{
	public zsgcReplyDaoImpl(){
		
	}
	private SessionFactory sessionFactory;
	MainDaoimpl mdi;
	initMethod initmethod;
	zsgcQuestionDaoImpl zsgcqdi;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setZsgcqdi(zsgcQuestionDaoImpl zsgcqdi) {
		this.zsgcqdi = zsgcqdi;
	}

	public void setInitmethod(initMethod initmethod) {
		this.initmethod = initmethod;
	}

	public REPLY000 getmaxlcnumReplyInfoByQid(int qid){
		Map row = new HashMap();
		System.out.println("getmaxlcnumReplyInfoByQid---"+qid);
		Session session = sessionFactory.openSession();
		String sql = "from REPLY000 r where r.qandrtime.QID = ? and LCNUM =  " +
				"(select max(LCNUM) from REPLY000 rr where rr.qandrtime.QID = ?)";
		Query queryObject=session.createQuery(sql);
		queryObject.setInteger(0, qid);
		queryObject.setInteger(1, qid);
		List<REPLY000> list =queryObject.list();
		REPLY000 r = new REPLY000();
		System.out.println("g00--"+list.size());
		if(list.size()>0){
			r = list.get(0);
			System.out.println("getmaxlcnumReplyInfoByQid-REPLY000--"+r.getRCONTENT());
		}else{
			r= null;
		}	
		session.close();

		return r;
	}

	public String getlastsubmittime(int qid){
		Map row = new HashMap();
		System.out.println("getlastsubmittimeByQid---"+qid);
		Session session = sessionFactory.openSession();
		String sql = "from QUESTION r where r.qandrtime.QID = ? and LCNUM =  " +
				"(select max(LCNUM) from QUESTION rr where rr.qandrtime.QID = ?)";
		Query queryObject=session.createQuery(sql);
		queryObject.setInteger(0, qid);
		queryObject.setInteger(1, qid);
		List<QUESTION> list =queryObject.list();
		QUESTION r = list.get(0);
		System.out.println("getlastsubmittimeByQid-r.getCreatetime()--"+r.getCreatetime());
		session.close();
		if(r != null){
			return r.getCreatetime();
		}	
		return "";
	}
	
	//问题回复，返回给页面显示的值
	public Map getInfoforhuifu(int qid){
		Map row = new HashMap();
		int lcnum = 1;
		QUESTION qu = new QUESTION();
		//QUESTION qu = initmethod.initupdatequestion(qid, 1);//初始化,取得楼层为1的qu数据
		REPLY000 re = getmaxlcnumReplyInfoByQid(qid);
		
		if(re!=null){
			System.out.println("回复表楼层="+re.getLCNUM());
			qu = initmethod.initupdatequestion(qid, re.getLCNUM()+1);//初始化,取得最大楼层的qu数据
		}else{
			qu = initmethod.initupdatequestion(qid, 1);//re取得空，则说明问题第一个添加，不是追问，则取得楼层为1的qu数据
		}
		
		if(qu == null && re == null){
			row.put("error", "系统出错，可能该问题尚未添加或问题未提交。");
			return row;
		}else{
			row.put("YHMC", qu.getYHMC());
			row.put("QID", qu.getQandrtime().getQID());
			row.put("TITLE", qu.getTITLE());
			row.put("SYSNAME", qu.getSYSNAME());

			row.put("QTYPE", qu.getQTYPE());
			row.put("DEGREE", qu.getDEGREE());
			row.put("QCONTENT", qu.getQCONTENT());
			row.put("QLCNUM", qu.getLCNUM());
			row.put("SUBMITTIME", qu.getCreatetime());
			
			return row;
		}
	}

	@Override
	public boolean addReply(REPLY000 re) {
		boolean flag = false;
		Session session=null;
		Transaction transaction = null;
		try{
			session=sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(re);//在缓存中保存数据，受影响行数
			transaction.commit();//写入数据库表
			System.out.println("---添加回复--");
			flag = true;//添加成功
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
	public boolean deleteReply(REPLY000 re) {
		boolean flag = false;
		Session session=null;
		Transaction transaction = null;
		try{
			session=sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			QANDRTIME qr = new QANDRTIME();
			int qqid = re.getQandrtime().getQID();
			qr = mdi.getQANDRTIME(qqid);
			if(qr == null){
				return false;
			}
			qr.setREPLYTIME(null);
			
			session.update(qr);
			session.delete(re);//在缓存中保存数据，受影响行数
			transaction.commit();//写入数据库表
			System.out.println("---删除--");
			flag = true;//删除成功
		}catch(Exception e){
			e.printStackTrace();
			return flag;
		}finally{
			session.close();
		}
		// TODO Auto-generated method stub
		return flag;
	}

	public void setMdi(MainDaoimpl mdi) {
		this.mdi = mdi;
	}


}
