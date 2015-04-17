package com.gree.q.sys.mo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gree.q.sys.dao.Cbase000DAO;
import com.gree.q.sys.vo.Cbase000VO;
import com.gree.q.sys.vo.Cbase011VO;
import com.gree.q.util.db.DbConnection;

public class Cbase000MO extends BaseMO {
	private Cbase000MO() {
	}

	private static Cbase000MO cbase000mo = null;

	public static Cbase000MO newInstance() {
		if (cbase000mo == null) {
			cbase000mo = new Cbase000MO();
		}
		return cbase000mo;
	}

	public Cbase000VO findCBase000VO(String usid) {
		Cbase000VO result = null;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((Cbase000DAO) getDAO(Cbase000DAO.class))
					.queryCbase000ByKey(usid, conn);
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

	public List findCBase000ByKey(String usid) {
		List result = null;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((Cbase000DAO) getDAO(Cbase000DAO.class)).Cbase000ByKey(usid, conn);
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
	
	public List findusid(String filter) {
		List result = null;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((Cbase000DAO) getDAO(Cbase000DAO.class)).finduisd(filter, conn);
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
	

	public int insert(Cbase000VO cbase000vo) throws SQLException {
		int result = 0;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((Cbase000DAO) getDAO(Cbase000DAO.class)).insert(
					cbase000vo, conn);
		} catch (SQLException ex) {		
			ex.printStackTrace();
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

	public int updateIP(String usid, String ipid, String comm) throws SQLException {
		int result = 0;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((Cbase000DAO) getDAO(Cbase000DAO.class)).updateIP(usid, ipid, comm, conn);
		} catch (SQLException ex) {		
			ex.printStackTrace();
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
	
	public int updateYYNO(String yyno, int sta2, String usid) throws SQLException {
		int result = 0;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((Cbase000DAO) getDAO(Cbase000DAO.class)).updateyyno(yyno, sta2, usid, conn);
		} catch (SQLException ex) {		
			ex.printStackTrace();
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
	
	
	
	public int insert(Cbase011VO cbase011vo) throws SQLException {
		int result = 0;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((Cbase000DAO) getDAO(Cbase000DAO.class)).insert11(cbase011vo, conn);
		} catch (SQLException ex) {		
			ex.printStackTrace();
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

	public int deteles(String[] keys) {
		int result = 0;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			conn.setAutoCommit(false);
			for (int i = 0; i < keys.length; i++) {
				String usid = new String(keys[i]).toString();
				result += ((Cbase000DAO) getDAO(Cbase000DAO.class)).delete(
						usid, conn);
			}
			conn.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
			result = -1;
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public int udpate(Cbase000VO cbase000vo) throws SQLException {
		int result = 0;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((Cbase000DAO) getDAO(Cbase000DAO.class)).update(
					cbase000vo, conn);
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
	
	public int udpate_INFOR(Cbase000VO cbase000vo) throws SQLException {
		int result = 0;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((Cbase000DAO) getDAO(Cbase000DAO.class)).updateINFOR(cbase000vo, conn);
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

	public int Conut(String filter) {
		int result = 0;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((Cbase000DAO) getDAO(Cbase000DAO.class)).Conut(filter,
					conn);
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
	
	public List findCbase000s(String filter, int start, int end) {
		List result = null;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((Cbase000DAO) getDAO(Cbase000DAO.class))
					.queryCbase000toMapList(filter, start, end, conn);
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
	
	public List findCbase000s(String usid) {
		List result = null;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((Cbase000DAO) getDAO(Cbase000DAO.class))
					.fingCbase000(usid, conn);
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
	
	public boolean iF_usid_IN_Cbase000(String usid) {

		boolean result = true;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((Cbase000DAO) getDAO(Cbase000DAO.class))
					.iF_usid_IN_Cbase000(usid, conn);
		} catch (SQLException ex) {
			ex.printStackTrace();
			result = true;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	//�û�=��ɫ	
	public List findUserisRoid(String usid) {
		List result = null;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((Cbase000DAO) getDAO(Cbase000DAO.class))
					.findUserisRoid(usid, conn);
		} catch (SQLException ex) {
			ex.printStackTrace();
			result = null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	//�û�<>��ɫ
	public List findUserisNotRoid(String usid) {
		List result = null;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((Cbase000DAO) getDAO(Cbase000DAO.class))
					.findUserisNotRoid(usid, conn);
		} catch (SQLException ex) {
			ex.printStackTrace();
			result = null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public int Conutlog(String filter) {
		int result = 0;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((Cbase000DAO) getDAO(Cbase000DAO.class)).Conutlog(filter, conn);
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
	
	public List findCbase011s(String filter, int start, int end) {
		List result = null;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			result = ((Cbase000DAO) getDAO(Cbase000DAO.class))
					.queryCbase011toMapList(filter, start, end, conn);
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
	
}
