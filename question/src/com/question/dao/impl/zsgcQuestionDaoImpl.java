package com.question.dao.impl;

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
import com.question.dao.zsgcQuestionDao;
import com.reply.dao.impl.zsgcReplyDaoImpl;
import com.user.model.USER0000;

public class zsgcQuestionDaoImpl implements zsgcQuestionDao{
	public zsgcQuestionDaoImpl(){
		
	}
	initMethod initmethod;
	zsgcReplyDaoImpl zsgcrdi;
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public void setInitmethod(initMethod initmethod) {
		this.initmethod = initmethod;
	}
	public void setZsgcrdi(zsgcReplyDaoImpl zsgcrdi) {
		this.zsgcrdi = zsgcrdi;
	}
	@Override
	public boolean addQuestion(QUESTION qu) {
		boolean flag = false;
		Session session=null;
		Transaction transaction = null;
		try{
			session=sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(qu);//在缓存中保存数据，受影响行数
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

	@Override
	public boolean deleteQuestion(int qid) {
		Session session=null;
		try{
			session = sessionFactory.openSession();
			String sql = "delete from QANDRTIME where QID = "+qid;
			Query queryObject=session.createSQLQuery(sql);
			int r =queryObject.executeUpdate();
			if(r<=0){
				return false;
			}
			System.out.println("---删除"+qid+"--问题表回复表的qid外键为qr表主键，会级联删除相应数据r="+r+"。");
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}
		// TODO Auto-generated method stub
		return true;
	}
	public boolean deleteQuestion(QUESTION qu) {
		boolean flag = false;
		Session session=null;
		Transaction transaction = null;
		try{
			session=sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.delete(qu);//在缓存中保存数据，受影响行数
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
	@Override
	public boolean updateQuestion(QUESTION qu) {
		boolean flag = false;
		Session session=null;
		Transaction transaction = null;
		try{
			session=sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(qu);//在缓存中保存数据，受影响行数
			transaction.commit();//写入数据库表
			System.out.println("---更新--");
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

	@Override
	public List queryQuestionToMapList(String yhmc, String filter,String searchtype, int start, int end) {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mi:ss");
		int rowpage = end-start;
		List result = new ArrayList();
		MainDaoimpl mdi = new MainDaoimpl();
		Session session = sessionFactory.openSession();
		String titltemp = "where q.YHMC='"+yhmc+"' ";
		
		if (!"".equals(filter)){//根据传入的搜索字符，添加相应sql查询语句
		//	System.out.println("传入方法中的filter="+filter);
			if(searchtype == "" ||searchtype ==null ){
				titltemp = "where q.TITLE like '%" + filter + "%' and q.YHMC='"+yhmc+"' ";
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
				if(searchtype.equals("REPLYSTATE")){
					if(filter.equals("未回复")){
						filter = "1";
					}
					else if(filter.equals("已回复")){
						filter = "0";
					}else{
						return null;
					}
				}
				if(searchtype.equals("SUBMITSTATE")){
					if(filter.equals("未提交")){
						filter = "1";
					}
					else if(filter.equals("已提交")){
						filter = "0";
					}else{
						return null;
					}
				}
				titltemp = "where q."+searchtype+" like '%" + filter + "%' and q.YHMC='"+yhmc+"' ";
			}
		}
//		String sql = "select * from (select top "+rowpage+" tab.* from ("
//				+"select top "+end+" ID, QID, TITLE, SYSNAME, QTYPE, DEGREE, SUBMITSTATE, REPLYSTATE , "
//				+"GRADE, ACCESSORY, CONVERT(varchar(100), CREATETIME, 102) CREATETIME, LCNUM, YHMC," 
//				+" QCONTENT  from QUESTION "+ titltemp +"and LCNUM = 1 order by SUBMITSTATE desc,"
//				+" QID asc, DEGREE asc " + ") tab order by tab.QID desc) tab2 order by tab2.QID asc";
	//	System.out.println("gg"+titltemp);
		String sql = "from QUESTION q "+ titltemp +"and q.LCNUM = 1 order by q.SUBMITSTATE desc,"
		+" q.CREATETIME desc, q.DEGREE asc ";
		Query queryObject=session.createQuery(sql);
		queryObject.setFirstResult(start);
		queryObject.setMaxResults(rowpage);
		//Query queryObject=session.createSQLQuery(sql);
		List<QUESTION> list =queryObject.list();
		int lenth = list.size();
		for(int i = 0; i < lenth ;i++){
			Map row = new HashMap();
			QUESTION qu = list.get(i);
//			System.out.println("查询创建时间：");
//			String sql2 = "select CONVERT(varchar(100), CREATETIME, 120) CT from QUESTION q "
//				+"where q.QID = "+qu.getID()+" and q.LCNUM = 1";
//			Query queryObject2=session.createSQLQuery(sql2);
//			List list2 =queryObject2.list();
		//	try {
		//		qu.setCREATETIME(sdf.parse(list2.get(0).toString()));
		//	} catch (ParseException e) {
		//		// TODO Auto-generated catch block
		//		e.printStackTrace();
		//		System.out.println("日期转换错误");
			//	return null;
		//	}
			row.put("ID", qu.getID());
			row.put("QID", qu.getQandrtime().getQID());
			row.put("TITLE", qu.getTITLE());
			row.put("SYSNAME", qu.getSYSNAME());

			row.put("QTYPE", qu.getQTYPE());
			row.put("DEGREE", qu.getDEGREE());
			row.put("SUBMITSTATE", qu.getSUBMITSTATE());
			
			row.put("REPLYSTATE", qu.getREPLYSTATE());
			row.put("GRADE", qu.getGRADE());
			row.put("ACCESSORY", qu.getACCESSORY());
			row.put("CREATETIME", qu.getCreatetime());
			
			row.put("LCNUM", qu.getLCNUM());
			row.put("YHMC", qu.getYHMC());
			//-----------------------------------
			String calcutime = mdi.calcuTime(qu.getQandrtime().getQID(), session);
			row.put("calcutime", calcutime);
			result.add(row);
		}
		session.close();
		//System.out.println("==="+result);
		//System.out.println("33="+result.size());
		return result;
	}
	
	//回复页面，查找问题数据，条件：系统负责人负责系统，搜索条件，分页查询
	public List queryQuestionToMapListforabnormal_handingPage(String yhmc, String filter,String searchtype, int start, int end) {

		int rowpage = end-start;
		List result = new ArrayList();
		MainDaoimpl mdi = new MainDaoimpl();
		Session session = sessionFactory.openSession();
		String titltemp = "q.LCNUM = 1 ";
		System.out.print("-------------"+searchtype);
		String sql1 = "select distinct SYSNAME from SYSUSER0 where YHMC ='"+yhmc+"'";
		Query queryObject1=session.createSQLQuery(sql1);
		List list=queryObject1.list();
		if(list.size()<1 || list.get(0) == null){
			return null;
		}
		System.out.println("...系统名称,list长度="+list.size());
		
		if (!"".equals(filter)){//根据传入的搜索字符，添加相应sql查询语句
		//	System.out.println("传入方法中的filter="+filter);
			if(searchtype == "" ||searchtype ==null ){
				titltemp = titltemp+"and q.TITLE like '%" + filter + "%' and q.YHMC='"+yhmc+"' ";
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
				if(searchtype.equals("REPLYSTATE")){
					if(filter.equals("未回复")){
						filter = "1";
					}
					else if(filter.equals("已回复")){
						filter = "0";
					}else{
						return null;
					}
				}
				titltemp = titltemp+"and q."+searchtype+" like '%" + filter + "%' and q.YHMC='"+yhmc+"' ";
			}
		}
		String sql = "";

		if(searchtype != null && searchtype != "" && searchtype.equals("SYSNAME")   ){
			for(int w = 0;w<list.size();w++){
				if(list.get(w).toString().contains(filter)){
					sql = "from QUESTION q where "+ titltemp +"and q.SUBMITSTATE = 0"
					+" order by q.CREATETIME desc, q.DEGREE asc ";
				}
			}
			if(!sql.equals("")){
				Query queryObject=session.createQuery(sql);
				queryObject.setFirstResult(start);
				queryObject.setMaxResults(rowpage);
				List<QUESTION> list2 =queryObject.list();
				int lenth = list2.size();
				for(int i = 0; i < lenth ;i++){
					Map row = new HashMap();
					QUESTION qu = list2.get(i);
					if(qu != null){
						row.put("ID", qu.getID());
						row.put("QID", qu.getQandrtime().getQID());
						row.put("TITLE", qu.getTITLE());
						row.put("SYSNAME", qu.getSYSNAME());

						row.put("QTYPE", qu.getQTYPE());
						row.put("DEGREE", qu.getDEGREE());
						row.put("SUBMITSTATE", qu.getSUBMITSTATE());
						
						row.put("REPLYSTATE", qu.getREPLYSTATE());
						row.put("GRADE", qu.getGRADE());
						row.put("ACCESSORY", qu.getACCESSORY());
						row.put("CREATETIME", zsgcrdi.getlastsubmittime(qu.getQandrtime().getQID()));
						
						row.put("LCNUM", qu.getLCNUM());
						row.put("YHMC", qu.getYHMC());
						//-----------------------------------
						String calcutime = mdi.calcuTime(qu.getQandrtime().getQID(), session);
						row.put("calcutime", calcutime);
						result.add(row);
					}
					
				}
			}
		}else{
			for(int i =0;i<list.size();i++){
				String qsystem = (String) list.get(i);
				String sql2 = "from QUESTION q where "+ titltemp +"and q.SYSNAME = '"+qsystem
					+"' and q.SUBMITSTATE = 0 order by q.CREATETIME desc, q.DEGREE asc ";
				Query queryObject2=session.createQuery(sql2);
				queryObject2.setFirstResult(start);
				queryObject2.setMaxResults(rowpage);
				List<QUESTION> list3 =queryObject2.list();
				int lenth = list3.size();
				for(int e = 0; e < lenth ;e++){
					Map row = new HashMap();
					QUESTION qu = list3.get(e);
					if(qu != null){
						row.put("ID", qu.getID());
						row.put("QID", qu.getQandrtime().getQID());
						row.put("TITLE", qu.getTITLE());
						row.put("SYSNAME", qu.getSYSNAME());

						row.put("QTYPE", qu.getQTYPE());
						row.put("DEGREE", qu.getDEGREE());
						row.put("SUBMITSTATE", qu.getSUBMITSTATE());
						
						row.put("REPLYSTATE", qu.getREPLYSTATE());
						row.put("GRADE", qu.getGRADE());
						row.put("ACCESSORY", qu.getACCESSORY());
						row.put("CREATETIME", zsgcrdi.getlastsubmittime(qu.getQandrtime().getQID()));
						
						row.put("LCNUM", qu.getLCNUM());
						row.put("YHMC", qu.getYHMC());
						//-----------------------------------
						String calcutime = mdi.calcuTime(qu.getQandrtime().getQID(), session);
						row.put("calcutime", calcutime);
						result.add(row);
					}
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
	
	public Map getQuestionInfoByQidLcnum(int qid, int lcnum){
		Map row = new HashMap();
		System.out.println("getQuestionInfoByQidLcnum---"+qid);
		Session session = sessionFactory.openSession();
		String sql = "from QUESTION q where QID = ? and LCNUM =  ?";
		Query queryObject=session.createQuery(sql);
		queryObject.setInteger(0, qid);
		queryObject.setInteger(1, lcnum);
		List<QUESTION> list =queryObject.list();
		int lenth = list.size();
		for(int i = 0; i < lenth ;i++){
			QUESTION qu = list.get(i);
			if(qu == null){
				row.put("error", "系统出错，问题获取出错。");
			}else{
				row.put("ID", qu.getID());
				row.put("QID", qu.getQandrtime().getQID());
				row.put("TITLE", qu.getTITLE());
				row.put("SYSNAME", qu.getSYSNAME());

				row.put("QTYPE", qu.getQTYPE());
				row.put("DEGREE", qu.getDEGREE());
				row.put("QCONTENT", qu.getQCONTENT());
				row.put("SUBMITSTATE", qu.getSUBMITSTATE());
				
				row.put("REPLYSTATE", qu.getREPLYSTATE());
				row.put("GRADE", qu.getGRADE());
				row.put("ACCESSORY", qu.getACCESSORY());
				row.put("CREATETIME", qu.getCreatetime());
				
				row.put("LCNUM", qu.getLCNUM());
				row.put("YHMC", qu.getYHMC());
			}
			//-----------------------------------
		}
		session.close();
		return row;
	}
	//问题追加，返回给页面显示的值
	public Map getInfoforZhuijia(int qid){
		Map row = new HashMap();
		QUESTION qu = initmethod.initupdatequestion(qid, 1);//初始化,取得楼层为1的qu数据
		REPLY000 re = zsgcrdi.getmaxlcnumReplyInfoByQid(qid);
		if(qu == null || re == null){
			row.put("error", "系统出错，可能该问题尚未回复或回复已撤销。");
			return row;
		}else{
			row.put("YHMC", qu.getYHMC());
			row.put("QID", qu.getQandrtime().getQID());
			row.put("TITLE", qu.getTITLE());
			row.put("SYSNAME", qu.getSYSNAME());

			row.put("QTYPE", qu.getQTYPE());
			row.put("DEGREE", qu.getDEGREE());
			row.put("RCONTENT", re.getRCONTENT());
			row.put("RLCNUM", re.getLCNUM());
			row.put("REPLYTIME", re.getReplytime());
			
			return row;
		}
	}
}
