package com.gree.q.sys.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.List;

public interface Aqus001DAO {
	public int insert(Map aqus001vo, Connection conn) throws SQLException;
	public int update(Map aqus001vo, Connection conn) throws SQLException;
	public int DeleteById(Connection conn,String id)throws SQLException;
}
