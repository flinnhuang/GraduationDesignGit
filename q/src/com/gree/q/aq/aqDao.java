package com.gree.q.aq;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface aqDao {
	public List getSysUser(String s)throws SQLException;

    public int Conut(String qmail,String filter, String searchtype, Connection conn) throws SQLException;
	
	public List queryAqus001toMapList(String umail,String filter,String searchtype, int start, int end,
			Connection conn) throws SQLException;
	public int Conut(String filter, String searchtype) throws SQLException;
	public void setACCESSORIES(String qid,String acce);
	
}
