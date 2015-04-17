package com.gree.q.sys.mo;

import java.util.HashMap;
import java.util.Map;

import com.gree.q.mainpage.MpDaoImpl;
import com.gree.q.sys.dao.Cbase000DAO;
import com.gree.q.sys.dao.Cbase000DAOImpl;

import com.gree.q.sys.dao.Aqus001DAO;
import com.gree.q.sys.dao.Aqus001DAOImpl;

import com.gree.q.aq.aqDao;
import com.gree.q.aq.aqDAOImpl;

public class BaseMO {
	public BaseMO() {
		if (dao == null) {
			dao = new HashMap();
			dao.put(Cbase000DAO.class, new Cbase000DAOImpl());
			dao.put(Aqus001DAO.class, new Aqus001DAOImpl());
			dao.put(aqDao.class, new aqDAOImpl());	
			dao.put(MpDaoImpl.class, new MpDaoImpl());
			System.out.println("BaseMO");
		}
	}

	private static Map dao = null;

	protected final static Object getDAO(Class _class) {
		return dao.get(_class);
	}
}
