package com.gree.q.aq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.gree.q.sys.vo.SysType;
import com.gree.q.util.db.DbConnection;
import com.gree.q.util.db.preparedStatement;
import com.gree.q.mainpage.Main001Daoimpl;;

public class aqDAOImpl implements aqDao{

	public List<SysType> getAllTypeList(){
		List<SysType> result = new ArrayList<SysType>();
		Connection con = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try{
			con = DbConnection.getConnection();
			String sql = "select distinct s_system from q_sysuser";
			sta = con.prepareStatement(sql);
			rs = sta.executeQuery();
			
			while(rs.next()){
				SysType q = new SysType();
				q.setS_system(rs.getString("s_system"));
				result.add(q);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public List getSysUser(String s) throws SQLException {
		List list = new ArrayList();
		String result = null;
		Connection con = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try{
			con = DbConnection.getConnection();
			String sql = "select * from q_sysuser where s_system = '"+s+"' ";
			sta = con.prepareStatement(sql);
			rs = sta.executeQuery();
			
			while(rs.next()){
				result =  rs.getString("S_USERID");
				list.add(result);
			}
		//	System.out.println("s"+"系统对应用户:"+result);
		//	System.out.println("sys"+"系统---:"+s);
			sta.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;

	}
	//计算用户所反馈的所有问题的数量
	public int Conut(String qmail,String filter, String searchtype,Connection conn) throws SQLException {
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			String titltemp = "";
			
			if (!"".equals(filter)){//根据传入的搜索字符，添加相应sql查询语句
			//	System.out.println("传入方法中的filter="+filter);
				if(searchtype == "" ||searchtype ==null ){
					titltemp = "q_titl like '%" + filter + "%' and ";
				}
				else{
					if(searchtype.equals("Q_DEGREE")){
						if(filter.equals("特急")){
							filter = "1";
						}
						else if(filter.equals("急")){
							filter = "2";
						}
						else if(filter.equals("普通")){
							filter = "3";
						}else{
							return 0;
						}
					}
					if(searchtype.equals("Q_ISDO")){
						if(filter.equals("未回复")){
							filter = "1";
						}
						else if(filter.equals("已回复")){
							filter = "0";
						}else{
							return 0;
						}
					}
					titltemp = ""+searchtype+" like '%" + filter + "%' and";
				}
			}
			String sql = "select count(q_id) from Q_QTMATTER q " 
					+"where "+titltemp+" q_mail = '"+qmail+"'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				result += rs.getInt(1);
			}
		//	System.out.println("........取出问题的总数............"+result);
			//System.out.println("sys"+"系统---:"+s);
			rs.close();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public List queryAqus001toMapList(String umail,String filter,String searchtype, int start, int end,
			Connection conn) throws SQLException {
		Main001Daoimpl mdi = new Main001Daoimpl();
		String titltemp = "where q_mail='"+umail+"' ";
		
		if (!"".equals(filter)){//根据传入的搜索字符，添加相应sql查询语句
		//	System.out.println("传入方法中的filter="+filter);
			if(searchtype == "" ||searchtype ==null ){
				titltemp = "where q_titl like '%" + filter + "%' and q_mail='"+umail+"' ";
			}
			else{
				if(searchtype.equals("Q_DEGREE")){
					if(filter.equals("特急")){
						filter = "1";
					}
					else if(filter.equals("急")){
						filter = "2";
					}
					else if(filter.equals("普通")){
						filter = "3";
					}else{
						return null;
					}
				}
				if(searchtype.equals("Q_ISDO")){
					if(filter.equals("未回复")){
						filter = "1";
					}
					else if(filter.equals("已回复")){
						filter = "0";
					}else{
						return null;
					}
				}
				if(searchtype.equals("Q_HAVESUBMIT")){
					if(filter.equals("未提交")){
						filter = "1";
					}
					else if(filter.equals("已提交")){
						filter = "0";
					}else{
						return null;
					}
				}
				titltemp = "where "+searchtype+" like '%" + filter + "%' and q_mail='"+umail+"' ";
			}
		}
		String sql = "select * from (select tab.*,rownum rown_ from ("
				+"select t.*,to_char(q_submittime,'yyyy/mm/dd hh24:mi') submittime from q_qtmatter t "
				+ titltemp +" order by q_submittime desc, Q_DEGREE desc " + ") tab where rownum<=" + end
				+ ") where rown_>" + start;
	//	System.out.println("gg"+titltemp);
		preparedStatement ps = preparedStatement.newInstance(conn, sql);
	//	System.out.println("000000---"+ps.getQueryString());
		List result = new ArrayList();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Map row = new HashMap();
			
			row.put("q_id", rs.getObject("q_id"));
			row.put("q_titl", rs.getObject("q_titl"));
			row.put("q_note", rs.getObject("q_note"));
			row.put("q_mail", rs.getObject("q_mail"));

			row.put("q_system", rs.getObject("q_system"));
			row.put("q_type", rs.getObject("q_type"));
			row.put("q_degree", rs.getObject("q_degree"));
			row.put("q_submittime", rs.getObject("submittime"));
			row.put("q_isdo", rs.getObject("q_isdo"));
			row.put("q_havesubmit", rs.getObject("q_havesubmit"));
			row.put("q_sta", rs.getObject("q_sta"));
			row.put("q_accessories", rs.getObject("q_accessories"));
			//-----------------------------------
			String calcutime = mdi.calcuTime(rs.getObject("q_id").toString());
			row.put("calcutime", calcutime);
			result.add(row);
		}
		rs.close();
		ps.close();
		//System.out.println("==="+result);
		//System.out.println("33="+result.size());
		return result;
	}
//*********************************************************************
	//取出所有数据
		public List getAllList(String filter,String searchtype, int start, int end){
			List result = new ArrayList();
			Main001Daoimpl mdi = new Main001Daoimpl();
			Connection con = null;
			PreparedStatement sta = null;
			ResultSet rs = null;
			
			String titltemp = "";
			try{
				con = DbConnection.getConnection();
				if (!"".equals(filter)){//根据传入的搜索字符，添加相应sql查询语句
					System.out.println("传入方法中的filter="+filter);
					if(searchtype == "" ||searchtype ==null ){
						titltemp = "q_titl like '%" + filter + "%' and ";
					}
					else{
						if(searchtype.equals("Q_DEGREE")){
							if(filter.equals("特急")){
								filter = "1";
							}
							else if(filter.equals("急")){
								filter = "2";
							}
							else if(filter.equals("普通")){
								filter = "3";
							}else{
								return null;
							}
						}
						if(searchtype.equals("Q_ISDO")){
							if(filter.equals("未回复")){
								filter = "1";
							}
							else if(filter.equals("已回复")){
								filter = "0";
							}else{
								return null;
							}
						}
						titltemp = ""+searchtype+" like '%" + filter + "%' and ";
					}
				}
				String sql ="select * from (select tab.*,rownum rown_ from ("
						+ "select t.* from (select q.*,to_char(q_backtime,'yyyy/mm/dd hh24:mi:ss') backtime " +
						",to_char(q_submittime,'yyyy/mm/dd hh24:mi:ss') submittime from Q_QTMATTER q where " +
						titltemp+"Q_HAVESUBMIT = '0') t order by  Q_ISDO desc, Q_SUBMITTIME desc " + ") tab where rownum<=" 
						+ end + ") where rown_ >" + start;
				System.out.println("sql="+sql);
				sta = con.prepareStatement(sql);
				rs = sta.executeQuery();
				
				while(rs.next()){
					Map row = new HashMap();				
					row.put("q_id", rs.getObject("q_id"));
					row.put("q_titl", rs.getObject("q_titl"));
					row.put("q_note", rs.getObject("q_note"));
					row.put("q_mail", rs.getObject("q_mail"));
					row.put("q_system", rs.getObject("q_system"));
					row.put("q_type", rs.getObject("q_type"));
					row.put("q_degree", rs.getObject("q_degree"));
					row.put("q_submittime", rs.getObject("q_submittime"));
					row.put("q_isdo", rs.getObject("q_isdo"));
					row.put("q_havesubmit", rs.getObject("q_havesubmit"));
					row.put("q_sta", rs.getObject("q_sta"));
					row.put("q_accessories", rs.getObject("q_accessories"));
					//-----------------------------------
					String calcutime = mdi.calcuTime(rs.getObject("q_id").toString());
					row.put("calcutime", calcutime);
					result.add(row);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			return result;
		}
		
		//所有反馈数目
		public int Conut(String filter, String searchtype) throws SQLException {
			int result = 0;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection conn = null;
			try{
				String titltemp = "";
				conn = DbConnection.getConnection();
				if (!"".equals(filter)){//根据传入的搜索字符，添加相应sql查询语句
					System.out.println("传入方法中的filter="+filter);
					if(searchtype == "" ||searchtype ==null ){
						titltemp = "q_titl like '%" + filter + "%' and ";
					}
					else{
						if(searchtype.equals("Q_DEGREE")){
							if(filter.equals("特急")){
								filter = "1";
							}
							else if(filter.equals("急")){
								filter = "2";
							}
							else if(filter.equals("普通")){
								filter = "3";
							}else{
								return 0;
							}
						}
						if(searchtype.equals("Q_ISDO")){
							if(filter.equals("未回复")){
								filter = "1";
							}
							else if(filter.equals("已回复")){
								filter = "0";
							}else{
								return 0;
							}
						}
						titltemp = ""+searchtype+" like '%" + filter + "%' and";
					}
				}
				String sql = "select count(*) from Q_QTMATTER q " 
						+"where "+titltemp+" Q_HAVESUBMIT = '0'";
				System.out.println("countsql-"+sql);
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()){
					result += rs.getInt(1);
				}
				
				//System.out.println("sys"+"系统---:"+s);
				rs.close();
				ps.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public void setACCESSORIES(String qid ,String acce) {//改附件状态1(有附件),0(无附件)
			try{
				int result = 0;
				Connection conn = DbConnection.getConnection();
				preparedStatement ps = preparedStatement
				.newInstance(conn,
				"update Q_QTMATTER set q_accessories=? where q_id=? and q_accessories != ?");
				ps.setString(1, acce.trim());
				ps.setString(2, qid.trim());
				ps.setString(3, acce.trim());
				System.out.println("setACCESSORIES--sql="+ps.getQueryString());
				result = ps.executeUpdate();
				ps.close();
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}

}
