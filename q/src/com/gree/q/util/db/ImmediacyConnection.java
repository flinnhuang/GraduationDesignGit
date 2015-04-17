package com.gree.q.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class ImmediacyConnection implements HttpSessionBindingListener {

	public ImmediacyConnection() {
		conn = null;
		jdbcdriver = "";
		url = "";
		user = "";
		pass = "";
	}

	public Connection ImmediacyConn() {
		jdbcdriver = "oracle.jdbc.driver.OracleDriver";
		//url = "jdbc:oracle:thin:@192.13.183.83:1521:greepro";
		user = "question";
		pass = "question2014";
		
		//jdbcdriver = "oracle.jdbc.driver.OracleDriver";
		url = "jdbc:oracle:thin:@10.1.18.83:1521:greepro";
		//user = "question";
		//pass = "question2014";
		try {
			Class.forName(jdbcdriver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException ex) {
			System.out.println("error:" + ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println("error:" + ex.getMessage());
		} catch (IllegalAccessException ex) {
			System.out.println(ex.getMessage());
		} catch (InstantiationException ex) {
			System.out.println(ex.getMessage());
		}
		return conn;
	}
	
	public void close() {
		try {
			conn.close();
			conn = null;
		} catch (SQLException sex) {
			System.out.println(sex.toString());
		}
	}

	public void valueBound(HttpSessionBindingEvent httpsessionbindingevent) {
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		if (conn != null)
			close();
	}

	private Connection conn;

	private String jdbcdriver = "oracle.jdbc.driver.OracleDriver";

	//private String url = "jdbc:oracle:thin:@192.13.183.83:1521:greepro";
	
	private String url = "jdbc:oracle:thin:@10.1.18.83:1521:greepro";

	private String user = "question";

	private String pass = "question2014";

	public static void main(String args[]) {
		try {

			String str = "' '";

			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
