package com.gree.q.sys.mo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.gree.q.sys.mo.Aqus001MO;
import com.gree.q.sys.dao.Aqus001DAO;
import com.gree.q.aq.aqDao;
import com.gree.q.util.db.DbConnection;

public class Aqus001MO extends BaseMO {
	private Aqus001MO() {
	}

	private static Aqus001MO aqus001mo = null;

	public static Aqus001MO newInstance() {
		if (aqus001mo == null) {
			aqus001mo = new Aqus001MO();
		}
		return aqus001mo;
	}


	public int insert(Map aqus001vo) throws SQLException {
		int result = 0;
		System.out.println("aqus001 mo....");
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((Aqus001DAO) getDAO(Aqus001DAO.class)).insert(aqus001vo, conn);
			System.out.println("insert..mo..");
		} catch (SQLException ex) {
			result = -1;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}
	
	public int update(Map aqus001vo) throws SQLException {
		int result = 0;
		System.out.println("aqus001 mo....");
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((Aqus001DAO) getDAO(Aqus001DAO.class)).update(aqus001vo, conn);
			System.out.println("update..mo..");
		} catch (SQLException ex) {
			result = -1;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}

	//分页
		public int Conut(String qmail,String filter, String searchtype) {
			int result = 0;
			Connection conn = null;
			try {
				conn = DbConnection.getConnection();
				result = ((aqDao) getDAO(aqDao.class)).Conut(qmail,filter,searchtype,conn);
			} catch (SQLException ex) {
				result = -1;
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}
			return result;
		}
		
		public List findTexes002(String umail,String filter, String searchtype,int start, int end) {
			List result = null;
			Connection conn = null;
			try {
				conn = DbConnection.getConnection();
				result = ((aqDao) getDAO(aqDao.class)).queryAqus001toMapList(umail,filter,searchtype, start, end, conn);
			} catch (SQLException ex) {
				ex.printStackTrace();
				result = null;
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}
			return result;
		}
	
}
