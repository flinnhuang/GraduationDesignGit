package com.gree.q.sys.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gree.q.sys.vo.Cbase000VO;
import com.gree.q.sys.vo.Cbase011VO;
import com.gree.q.util.Constants;
import com.gree.q.util.db.preparedStatement;

public class Cbase000DAOImpl implements Cbase000DAO {

	public int insert(Cbase000VO cbase000vo, Connection conn)
			throws SQLException {
		int result = 0;

		preparedStatement ps = preparedStatement.newInstance(conn,
				"insert into cbase000(USID,DSCA,PAWD,GROU,DEPT,FLAG,COMP,CPID,MAIL,JWWJ,STA1,TOID,IPID,COMM,STA2)"
						+ "values(?,?,?,?,?,?,?,?,?,?,0,0,0,0,0)");

		ps.setString(1, cbase000vo.getUSID());
		ps.setString(2, cbase000vo.getDSCA());
		ps.setString(3, cbase000vo.getPAWD());
		ps.setString(4, cbase000vo.getGROU());
		ps.setString(5, cbase000vo.getDEPT());
		ps.setInt(6, cbase000vo.getFLAG());
		ps.setInt(7, cbase000vo.getCOMP());
		ps.setString(8, cbase000vo.getCPID());
		ps.setString(9, cbase000vo.getMAIL());
		ps.setString(10, cbase000vo.getJWWJ());
		ps.setString(9, cbase000vo.getIPID());
		ps.setString(10, cbase000vo.getCOMM());
		
		Constants.out(ps.getQueryString());
		result = ps.executeUpdate();

		ps.close();
		return result;
	}
	
	public int insert11(Cbase011VO cbase011vo, Connection conn)
	throws SQLException {
	int result = 0;
	
	preparedStatement ps = preparedStatement.newInstance(conn,
			"insert into cbase011(usid, nama, comp, idat, gdat, ipid, stat, tims)"
					+ "values(?,?,?,sysdate,?,?,?,?)");
	
	ps.setString(1, cbase011vo.getUsid());
	ps.setString(2, cbase011vo.getNama());
	ps.setInt(3, cbase011vo.getCOMP());
	ps.setDate(4,cbase011vo.getGdat());
	ps.setString(5, cbase011vo.getIpid());
	ps.setInt(6, cbase011vo.getStat());
	ps.setInt(7, cbase011vo.getTims());
	
	Constants.out(ps.getQueryString());
	result = ps.executeUpdate();
	
	ps.close();
	return result;
}

	public int update(Cbase000VO cbase000vo, Connection conn)
			throws SQLException {
		int result = 0;
		preparedStatement ps = preparedStatement
				.newInstance(
						conn,
						"update cbase000 set DSCA=?,PAWD=?,GROU=?,DEPT=?,FLAG=?,COMP=?,CPID=?,MAIL=? where USID=?");

		ps.setString(1, cbase000vo.getDSCA());
		ps.setString(2, cbase000vo.getPAWD());
		ps.setString(3, cbase000vo.getGROU());
		ps.setString(4, cbase000vo.getDEPT());
		ps.setInt(5, cbase000vo.getFLAG());
		ps.setInt(6, cbase000vo.getCOMP());
		ps.setString(7, cbase000vo.getCPID());
		ps.setString(8, cbase000vo.getMAIL());	
		ps.setString(9, cbase000vo.getUSID());
		
		Constants.out(ps.getQueryString());
		
		result = ps.executeUpdate();

		ps.close();
		return result;
	}
	
	public int updateIP(String usid, String ipid, String comm, Connection conn) throws SQLException {
	int result = 0;
	preparedStatement ps = preparedStatement
			.newInstance(
					conn,
					"update cbase000 set ipid=?,comm=? where USID=?");
	
	ps.setString(1, ipid);
	ps.setString(2, comm);
	ps.setString(3, usid);
	
	Constants.out(ps.getQueryString());
	
	result = ps.executeUpdate();
	
	ps.close();
	return result;
	}
	
	
	public int updateyyno(String usid,int sta2,String yyno, Connection conn) throws SQLException {
		int result = 0;
		preparedStatement ps = preparedStatement
				.newInstance(
						conn,
						"update cbase000 set yyno=?,sta2=? where USID=?");
		
		ps.setString(1, yyno);
		ps.setInt(2, sta2);
		ps.setString(3, usid);		
		Constants.out(ps.getQueryString());
		
		result = ps.executeUpdate();
		
		ps.close();
		return result;
		}
	
	public int updateINFOR(Cbase000VO cbase000vo, Connection conn)
		throws SQLException {
	int result = 0;
	preparedStatement ps = preparedStatement
			.newInstance(
					conn,
					"update cbase000 set DSCA=?,PAWD=?,GROU=?,DEPT=?,FLAG=?,COMP=?,CPID=?,MAIL=?,ACCo=?,BANK=?,TEL1=?,JWWJ=? where USID=?");
	
	ps.setString(1, cbase000vo.getDSCA());
	ps.setString(2, cbase000vo.getPAWD());
	ps.setString(3, cbase000vo.getGROU());
	ps.setString(4, cbase000vo.getDEPT());
	ps.setInt(5, cbase000vo.getFLAG());
	ps.setInt(6, cbase000vo.getCOMP());
	ps.setString(7, cbase000vo.getCPID());
	ps.setString(8, cbase000vo.getMAIL());	
	ps.setString(9, cbase000vo.getACCO());	
	ps.setString(10, cbase000vo.getBANK());	
	ps.setString(11, cbase000vo.getTEL1());	
	ps.setString(12, cbase000vo.getJWWJ());	
	
	ps.setString(13, cbase000vo.getUSID());
	
	Constants.out(ps.getQueryString());
	
	result = ps.executeUpdate();
	
	ps.close();
	return result;
	}

	public int delete(String usid, Connection conn) throws SQLException {
		int result = 0;
		preparedStatement ps = preparedStatement.newInstance(conn,
				"delete from cbase000 where USID=?");
		ps.setString(1, usid);

		System.out.println(ps.getQueryString());
		result = ps.executeUpdate();
		ps.close();
		return result;
	}

	public Cbase000VO queryCbase000ByKey(String usid, Connection conn)
			throws SQLException {
		String sql = "select cb0.usid,cb0.dsca,cb0.pawd,cb0.grou,cb6.dsca detpdsca," +
				"cb1.dsca compdsac,cb0.flag,cb0.dept,cb0.cpid,cb0.comp,cb0.mail,cb0.acco,cb0.bank,cb0.tel1,cb0.remk,cb0.jwwj,cb0.sta1,cb0.toid "
				+ "from cbase000 cb0,cbase001 cb1,cbase006 cb6 "
				+ "where cb0.comp=cb1.comp and cb6.dept=cb0.dept and cb0.usid=? ";
		preparedStatement ps = preparedStatement.newInstance(conn, sql);
		ps.setString(1, usid);
		Constants.out(ps.getQueryString());
		ResultSet rs = ps.executeQuery();
		Cbase000VO result = null;
		if (rs.next()) {
			result = new Cbase000VO();
			result.setUSID(rs.getString(1));
			result.setDSCA(rs.getString(2));
			result.setPAWD(rs.getString(3));
			result.setGROU(rs.getString(4));
			result.setDEPTDSCA(rs.getString(5));
			result.setCOMPDSCA(rs.getString(6));
			result.setFLAG(rs.getInt(7));
			result.setDEPT(rs.getString(8));
			result.setCPID(rs.getString(9));
			result.setCOMP(rs.getInt(10));
			result.setMAIL(rs.getString(11));
			result.setACCO(rs.getString(12));
			result.setBANK(rs.getString(13));
			result.setTEL1(rs.getString(14));
			result.setREMK(rs.getString(15));
			result.setJWWJ(rs.getString(16));
			result.setSTA1(rs.getInt(17));
			result.setTOID(rs.getString(18));
		}
		rs.close();
		ps.close();
		return result;
	}

	public List queryCbase000toMapList(String filter, int start, int end,
			Connection conn) throws SQLException {
		String sql = "select * from (select tab.*,rownum rown_ from ("
				+ "select cb0.usid,cb0.dsca,cb0.pawd,cb0.grou,cb6.dsca dscb,"
				+ "cb1.dsca dscc,cb0.comp,cb0.flag,cb0.cpid,cb0.mail,cb0.jwwj,cb0.sta1,cb0.toid,cb0.ipid,cb0.comm,cb0.jdep,cb0.sta2,cb0.yyno  from cbase000 cb0,cbase001 cb1,"
				+ "cbase006 cb6 where cb0.comp=cb1.comp and cb6.dept=cb0.dept"
				+ filter + " order by cb0.usid" + ") tab where rownum<=" + end
				+ ") where rown_>" + start;
		preparedStatement ps = preparedStatement.newInstance(conn, sql);
		Constants.out(ps.getQueryString());
		List result = new ArrayList();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Map row = new HashMap();
			
			row.put("usid", rs.getObject("usid"));
			row.put("dsca", rs.getObject("dsca"));
			row.put("pawd", rs.getObject("pawd"));
			row.put("grou", rs.getObject("grou"));
			row.put("dscb", rs.getObject("dscb"));
			row.put("dscc", rs.getObject("dscc"));
			row.put("comp", rs.getObject("comp"));
			
			row.put("flag", rs.getObject("flag"));
			row.put("cpid", rs.getObject("cpid"));
			
			row.put("mail", rs.getObject("mail"));
			row.put("jwwj", rs.getObject("jwwj"));
			
			row.put("sta1", rs.getObject("sta1"));
			row.put("toid", rs.getObject("toid"));
			
			row.put("ipid", rs.getObject("ipid"));
			row.put("comm", rs.getObject("comm"));
			
			row.put("jdep", rs.getObject("jdep"));
			row.put("sta2", rs.getObject("sta2"));
			row.put("yyno", rs.getObject("yyno"));
			
			result.add(row);
			
		}
		rs.close();
		ps.close();

		return result;
	}

	public int Conut(String filter, Connection conn) throws SQLException {
		String sql = " select count(*) "
				+ "from cbase000 cb0,cbase001 cb1,cbase006 cb6 "
				+ "where cb0.comp=cb1.comp and cb6.dept=cb0.dept" + filter;

		preparedStatement ps = preparedStatement.newInstance(conn, sql);
		Constants.out(ps.getQueryString());
		ResultSet rs = ps.executeQuery();
		rs.next();
		int result = rs.getInt(1);
		rs.close();
		ps.close();
		return result;
	}

	public boolean iF_usid_IN_Cbase000(String usid, Connection conn)
			throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select count(*) from Cbase000 where usid=?";
		preparedStatement ps = preparedStatement.newInstance(conn, sql);
		ps.setString(1, usid);

		Constants.out(ps.getQueryString());
		ResultSet rs = ps.executeQuery();
		rs.next();
		int result = rs.getInt(1);
		rs.close();
		ps.close();
		return result > 0;
	}

	public List fingCbase000(String usid, Connection conn) throws SQLException {

		String sql = "select cb0.usid,cb0.dsca,cb0.pawd,cb0.grou,cb6.dsca dscb,"
				+ "cb1.dsca dscc,cb0.flag,cb0.cpid,cb0.dept,cb0.mail,cb0.jwwj,cb0.sta1,cb0.toid,cb0.bank,cb0.acco,cb0.ipid,cb0.comm from cbase000 cb0,cbase001 cb1,"
				+ "cbase006 cb6 where cb0.comp=cb1.comp and cb6.dept=cb0.dept ";

		preparedStatement ps = preparedStatement.newInstance(conn, sql);
		Constants.out(ps.getQueryString());
		List result = new ArrayList();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Map row = new HashMap();

			row.put("usid", rs.getObject("usid"));
			row.put("dsca", rs.getObject("dsca"));
			row.put("pawd", rs.getObject("pawd"));
			
			row.put("grou", rs.getObject("grou"));
			row.put("dscb", rs.getObject("dscb"));			
			row.put("dscc", rs.getObject("dscc"));
			
			row.put("flag", rs.getObject("flag"));		
			row.put("cpid", rs.getObject("cpid"));
			row.put("dept", rs.getObject("dept"));
			
			row.put("mail", rs.getObject("mail"));
			row.put("jwwj", rs.getObject("jwwj"));		
			row.put("bank", rs.getObject("bank"));
			
			row.put("acco", rs.getObject("acco"));			
			row.put("sta1", rs.getObject("sta1"));
			row.put("toid", rs.getObject("toid"));
			
			row.put("ipid", rs.getObject("ipid"));
			row.put("comm", rs.getObject("comm"));
			
			result.add(row);

		}
		rs.close();
		ps.close();
		return result;
	}

	public List Cbase000ByKey(String usid, Connection conn) throws SQLException {
		String sql = "select cb0.usid,cb0.dsca,cb0.pawd,cb0.grou,cb6.dsca dscb,"
				+ "cb1.dsca dscc,cb0.flag,cb0.cpid,cb0.dept,cb0.mail,cb0.jwwj,cb0.sta1,cb0.toid,cb0.bank,cb0.acco,cb0.tel1 from cbase000 cb0,cbase001 cb1,"
				+ "cbase006 cb6 where cb0.comp=cb1.comp and cb6.dept=cb0.dept and usid=?";

		preparedStatement ps = preparedStatement.newInstance(conn, sql);
		Constants.out(ps.getQueryString());
		ps.setString(1, usid);

		List result = new ArrayList();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Map row = new HashMap();

			row.put("usid", rs.getObject("usid"));
			row.put("dsca", rs.getObject("dsca"));
			row.put("pawd", rs.getObject("pawd"));			
			row.put("grou", rs.getObject("grou"));			
			row.put("dscb", rs.getObject("dscb"));		
			row.put("dscc", rs.getObject("dscc"));
			row.put("flag", rs.getObject("flag"));
			row.put("cpid", rs.getObject("cpid"));			
			row.put("dept", rs.getObject("dept"));
			row.put("mail", rs.getObject("mail"));
			row.put("jwwj", rs.getObject("jwwj"));			
			row.put("bank", rs.getObject("bank"));
			row.put("tel1", rs.getObject("tel1"));			
			row.put("acco", rs.getObject("acco"));			
			row.put("sta1", rs.getObject("sta1"));
			row.put("toid", rs.getObject("toid"));
			
			result.add(row);

		}
		rs.close();
		ps.close();
		return result;
	}
	
	
	public List finduisd(String filter, Connection conn) throws SQLException {
		String sql = "select cb0.usid,cb0.dsca," +
				"(select dsca from cbase006 where dept=cb0.dept) deptdsac from cbase000 cb0 where 0=0 "+filter;

		preparedStatement ps = preparedStatement.newInstance(conn, sql);
		Constants.out(ps.getQueryString());
		List result = new ArrayList();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Map row = new HashMap();

			row.put("usid", rs.getObject("usid"));
			row.put("dsca", rs.getObject("dsca"));
			row.put("deptdsac", rs.getObject("deptdsac"));				
			result.add(row);

		}
		rs.close();
		ps.close();
		return result;
	}

	// ������ɫ = usid
	public List findUserisRoid(String usid, Connection conn)
			throws SQLException {

		String sql = " select distinct cb7.roid,cb7.dsca from" +
		" cbase005 cb5,cbase007 cb7 where cb5.roid in (select cb7.roid from cbase007) and cb5.usid=? ";

		preparedStatement ps = preparedStatement.newInstance(conn, sql);
		Constants.out(ps.getQueryString());
		ps.setString(1, usid);

		List result = new ArrayList();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Map row = new HashMap();

			row.put("roid", rs.getObject("roid"));
			row.put("dsca", rs.getObject("dsca"));
			result.add(row);

		}
		rs.close();
		ps.close();
		return result;
	}
	
	// ������ɫ <> usid
	public List findUserisNotRoid(String usid, Connection conn)
			throws SQLException {
		
		String sql = " select distinct cb7.roid,cb7.dsca from " +
				" cbase005 cb5,cbase007 cb7 where cb5.roid not in (select cb7.roid from cbase007) and cb5.usid=? ";

		preparedStatement ps = preparedStatement.newInstance(conn, sql);
		Constants.out(ps.getQueryString());
		ps.setString(1, usid);

		List result = new ArrayList();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Map row = new HashMap();

			row.put("roid", rs.getObject("roid"));
			row.put("dsca", rs.getObject("dsca"));
			
			result.add(row);

		}
		rs.close();
		ps.close();
		return result;
		
	}

	//��¼��־
	public List queryCbase011toMapList(String filter, int start, int end,
			Connection conn) throws SQLException {
		String sql = "select * from (select tab.*,rownum rown_ from ("
				+ "select usid, nama, comp, idat, gdat, ipid, stat, tims from cbase011 where "
				+ filter + " order by idat desc " + ") tab where rownum<=" + end
				+ ") where rown_>" + start;
		preparedStatement ps = preparedStatement.newInstance(conn, sql);
		Constants.out(ps.getQueryString());
		List result = new ArrayList();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Map row = new HashMap();
			
			row.put("usid", rs.getObject("usid"));
			row.put("nama", rs.getObject("nama"));
			row.put("comp", rs.getObject("comp"));
			row.put("idat", rs.getTimestamp("idat"));
			row.put("gdat", rs.getTimestamp("gdat"));
			row.put("ipid", rs.getObject("ipid"));
			row.put("stat", rs.getObject("stat"));
			row.put("tims", rs.getObject("tims"));
	
			result.add(row);
			
		}
		rs.close();
		ps.close();

		return result;
	}

	public int Conutlog(String filter, Connection conn) throws SQLException {
		String sql = " select count(*) "
				+ "from cbase011 "
				+ "where " + filter;

		preparedStatement ps = preparedStatement.newInstance(conn, sql);
		Constants.out(ps.getQueryString());
		ResultSet rs = ps.executeQuery();
		rs.next();
		int result = rs.getInt(1);
		rs.close();
		ps.close();
		return result;
	}


	
}
