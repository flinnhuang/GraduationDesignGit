package com.gree.q.sys.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gree.q.sys.vo.Cbase000VO;
import com.gree.q.sys.vo.Cbase011VO;

public interface Cbase000DAO {

	public int insert(Cbase000VO cbase000vo, Connection conn)
			throws SQLException;
	
	
	//������־
	public int insert11(Cbase011VO cbase011vo, Connection conn)
	throws SQLException;

	public int update(Cbase000VO cbase000vo, Connection conn)
			throws SQLException;
	
	
	public int updateIP(String usid,String ipid,String comm, Connection conn)
	throws SQLException;
	
	
	public int updateyyno(String usid,int sta2,String yyno, Connection conn)
	throws SQLException;
	
	public int updateINFOR(Cbase000VO cbase000vo, Connection conn)
	throws SQLException;

	public int delete(String usid, Connection conn) throws SQLException;

	public Cbase000VO queryCbase000ByKey(String usid, Connection conn)
			throws SQLException;
	
	public List Cbase000ByKey(String usid, Connection conn)
	throws SQLException;
	
	
	public List finduisd(String filter, Connection conn)
	throws SQLException;
	
	public List fingCbase000(String usid, Connection conn)
	throws SQLException;

	public int Conut(String filter, Connection conn) throws SQLException;

	public List queryCbase000toMapList(String filter, int start, int end,
			Connection conn) throws SQLException;
	
	
	
	//�����¼��־
	public int Conutlog(String filter, Connection conn) throws SQLException;

	public List queryCbase011toMapList(String filter, int start, int end,
			Connection conn) throws SQLException;
	
	public boolean iF_usid_IN_Cbase000(String usid, Connection conn)
	throws SQLException;
	
	//�û��ҽ�ɫ=
	public List findUserisRoid(String usid, Connection conn)
	throws SQLException;
	
   //�û��ҽ�ɫ<>
	public List findUserisNotRoid(String usid, Connection conn)
	throws SQLException;
}
