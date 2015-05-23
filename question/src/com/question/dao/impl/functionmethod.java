package com.question.dao.impl;

import org.hibernate.SessionFactory;

import com.main.daoimpl.initMethod;
import com.model.QUESTION;

public class functionmethod {
	public functionmethod(){
		
	}
	initMethod initmethod;
	zsgcQuestionDaoImpl zsgcqdi;
	private SessionFactory sessionFactory;

	public boolean updatePointById(int qid, int sta){
		QUESTION qus = initmethod.initupdatequestion(qid, 1);//初始化,取得楼层为1的qu数据
		qus.setGRADE(sta);
		if(zsgcqdi.updateQuestion(qus)){
			return true;
		}else{
			return false;
		}
	}
	
	public void setInitmethod(initMethod initmethod) {
		this.initmethod = initmethod;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public void setZsgcqdi(zsgcQuestionDaoImpl zsgcqdi) {
		this.zsgcqdi = zsgcqdi;
	}
}
