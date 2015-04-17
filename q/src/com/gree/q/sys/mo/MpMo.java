package com.gree.q.sys.mo;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.gree.q.mainpage.MpDaoImpl;
import com.gree.q.util.db.DbConnection;

public class MpMo extends BaseMO{
	private MpMo() {
	}
	private static MpMo mpmo = null;
	public static MpMo newInstance() {
		if (mpmo == null) {
			mpmo = new MpMo();
		}
		return mpmo;
	}
	public String getItem(String qid,String searna) {
		String result = "";
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((MpDaoImpl) getDAO(MpDaoImpl.class)).getItem(qid,searna,conn);
		} catch (SQLException ex) {
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
	//根据id取得该记录的数据
	public List getInfo(String qid) {
		List result = null;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((MpDaoImpl) getDAO(MpDaoImpl.class)).getInfo(qid,conn);
		} catch (SQLException ex) {
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
	//分页
	public int Conut(String qmail,String filter, String searchtype) {
		int result = 0;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((MpDaoImpl) getDAO(MpDaoImpl.class)).Conut(qmail,filter,searchtype,conn);
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
			
	public List findTexes002(String qmail,String filter, String searchtype, int start, int end) {
		List result = null;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((MpDaoImpl) getDAO(MpDaoImpl.class)).queryAqus001toMapList(qmail,filter, searchtype,start, end, conn);
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
	//更新
	public int UpdateTable(Map mpdaoimpl001){
		int result = -1;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((MpDaoImpl) getDAO(MpDaoImpl.class)).UpdateTable(mpdaoimpl001, conn);
		} catch (SQLException ex) {
			ex.printStackTrace();
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
	//用户反馈问题，问题已经且只是保存，e页面的提交“a”链接按钮，更新数据库
	public int qmSubmit(String qid) throws SQLException{
		int result = -1;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((MpDaoImpl) getDAO(MpDaoImpl.class)).qmSubmit(qid, conn);
		} catch (SQLException ex) {
			ex.printStackTrace();
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
	//用户删除其所反馈的问题
	public int qmDelete(String qid) throws SQLException{
		int result = -1;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((MpDaoImpl) getDAO(MpDaoImpl.class)).qmDelete(qid, conn);
		} catch (SQLException ex) {
			ex.printStackTrace();
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
	//系统负责人，撤销最近一次的回复
	public int bRepeal(String qid) throws SQLException{
		int result = -1;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((MpDaoImpl) getDAO(MpDaoImpl.class)).bRepeal(qid, conn);
		} catch (SQLException ex) {
			ex.printStackTrace();
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
}
