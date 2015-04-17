package com.gree.q.sys.dao;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.gree.q.util.db.preparedStatement;


public class Aqus001DAOImpl implements Aqus001DAO {

	private static final Logger log = Logger.getLogger(Aqus001DAO.class);
	public int insert(Map aqus001, Connection conn) throws SQLException {
		int result = 0;
		
		System.out.println("insert....");
		log.debug("begin update setting...");
		
		preparedStatement ps = preparedStatement
				.newInstance(
						conn,
						"insert into q_qtmatter(q_id, q_titl, q_mail, q_system, q_type, q_degree, " +
						"q_note, q_submitTime, q_backTime, q_sta, q_accessories, q_isdo,q_havesubmit) " +
						"values(?,?,?,?,?,?,?,sysdate,null,?,?,'1',?)");
		

		                ps.setLong(1, Long.parseLong(aqus001.get("q_id").toString()));
		                ps.setString(2, aqus001.get("q_titl").toString());
						ps.setString(3, aqus001.get("q_mail").toString());
						ps.setString(4, aqus001.get("q_system").toString());
						//ps.setString(5, aqus001.get("q_userid").toString());
						ps.setString(5, aqus001.get("q_type").toString());
						ps.setString(6, aqus001.get("q_degree").toString());
						
						try {
							//ps.setString(8, aqus001.get("q_note").toString());
							ps.setBytes(7, aqus001.get("q_note").toString().getBytes("GBK"));
							System.out.println("note为："+aqus001.get("q_note").toString());
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						
						ps.setString(8, aqus001.get("q_sta").toString());
						ps.setString(9, aqus001.get("q_accessories").toString());
						ps.setString(10, aqus001.get("q_havesubmit").toString());
						System.out.println("daoimp"+ps.getQueryString());
						log.debug("update setting end...");
						
						result = ps.executeUpdate();
						ps.close();
						return result;
	}
	
	//================修改
	public int update(Map aqus001, Connection conn) throws SQLException {
		int result = 0;
		
		System.out.println("update....");
		log.debug("begin update setting...");
		
		preparedStatement ps = preparedStatement
			.newInstance(
				conn,
				"update Q_QTMATTER set q_titl=?, q_mail=?, q_system=?, q_type=?, q_degree=?, " +
				"q_note=?, q_submitTime=sysdate, q_backTime=null, q_sta=?, q_accessories=?, q_isdo='1',q_havesubmit=? " +
				"where q_id=?");
		        ps.setString(1, aqus001.get("q_titl").toString());
				ps.setString(2, aqus001.get("q_mail").toString());
				ps.setString(3, aqus001.get("q_system").toString());
				ps.setString(4, aqus001.get("q_type").toString());
				ps.setString(5, aqus001.get("q_degree").toString());
				try {
					ps.setBytes(6, aqus001.get("q_note").toString().getBytes("GBK"));
					System.out.println("note为："+aqus001.get("q_note").toString());
				} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ps.setString(7, aqus001.get("q_sta").toString());
				ps.setString(8, aqus001.get("q_accessories").toString());
				ps.setString(9, aqus001.get("q_havesubmit").toString());
				ps.setLong(10, Long.parseLong(aqus001.get("q_id").toString()));
				System.out.println("daoimp"+ps.getQueryString());
				log.debug("update setting end...");
						
				result = ps.executeUpdate();
				ps.close();
				return result;
	}
	@Override
	public int DeleteById(Connection conn,String id) throws SQLException {

		int result = 0;
		
		System.out.println("delete....");
		
		preparedStatement ps = preparedStatement
				.newInstance(
						conn,
						"delete from q_qtmatter where q_id ="+ id);
						
						try {
                             result = 1;
						} catch (Exception e) {

						}
						
						result = ps.executeUpdate();
						ps.close();
						return result;
		
	}	
}
