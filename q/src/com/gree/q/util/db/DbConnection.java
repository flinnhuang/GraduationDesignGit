package com.gree.q.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * ϵͳ���ӳأ���������Ե�ʱ�������jdbc_odbc ��Ŀ��ʽ���е�ʱ��һ��Ҫ��ϵͳ���ӳ�
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class DbConnection {
	private final static String jdbcdriver = "oracle.jdbc.driver.OracleDriver";

    private final static String url = "jdbc:oracle:thin:@10.1.18.83:1521:greepro";
    //private final static String url = "jdbc:oracle:thin:@192.13.183.83:1521:greepro";
	
	private final static String user = "question";

	private final static String pass = "question2014"; 

	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		boolean onof = false;
		try {
			conn = getConnectionByDBPool();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.out.println("连不上");
			onof = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("连不上...");
			onof = true;
		}

		if (onof) {
			System.out.println("连不上...");
			conn = getConnectionDirect();
		}
		return conn;
	}

	private static Connection getConnectionDirect() throws SQLException {
		try {
			Class.forName(jdbcdriver);
		} catch (ClassNotFoundException ex) {
			throw new SQLException(ex.getMessage());
		}
		Connection conn = DriverManager.getConnection(url, user, pass);
		return conn;
	}

	private static Connection getConnectionByDBPool() throws NamingException,
			SQLException {
		Context initCtx = new InitialContext();
		Context ctx = (Context) initCtx.lookup("java:comp/env");
		Object obj = (Object) ctx.lookup("gree.q");
		DataSource ds = (javax.sql.DataSource) obj;
		Connection conn = ds.getConnection();
		return conn;
	}
}
