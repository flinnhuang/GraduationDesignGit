package com.gree.q.mainpage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gree.q.util.db.DbConnection;

public class mainDAOImpl {
		//取出已处理0（未处理1）的数据、统计取出系统已处理数all=true的数据
		public List getDeteil(String qmail,int isdo,boolean all) throws SQLException {
			List list = new ArrayList();
			List list2 = new ArrayList();
			Connection con = null;
			PreparedStatement sta = null;
			ResultSet rs = null;
			try{
				con = DbConnection.getConnection();
				
				if(!all){//all为false，则取已处理（未处理）的总数
					String sql1 = "select t.s_system from q_sysuser t where s_userid = '"+qmail+"'";
					sta = con.prepareStatement(sql1);
					rs = sta.executeQuery();
					//if(rs.first()){
						while(rs.next()){
							String r = null;
							r =  rs.getString("s_system");
							list2.add(r);
						}

						for(int i =0;i<list2.size();i++){
							String qsystem = (String) list2.get(i);
							
							String sql2 = "select * from Q_QTMATTER q " 
									+"where q.q_system = '"+qsystem+"' and q.q_isdo = '"+isdo
									+"' and q.q_mail not like '"
									+qmail+"'"+"order by q_degree asc";
							
							sta = con.prepareStatement(sql2);
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
								row.put("q_sta", rs.getObject("q_sta"));
								list.add(row);
							}
//							System.out.println(list.size());
					//	}
					}
					String sql3 = "select * from Q_QTMATTER q where q.q_mail = '"+qmail+"' " +
							"and q.q_isdo = '"+isdo+"'"+"order by q_degree asc";
					sta = con.prepareStatement(sql3);
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
						row.put("q_sta", rs.getObject("q_sta"));
						list.add(row);
					}
					//System.out.println(list.size());
				}else{//统计已处理数  all为true
					String sql4 = "select * from Q_QTMATTER q where q.q_isdo = '"+isdo+"'"+"order by q_degree asc";
					sta = con.prepareStatement(sql4);
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
						row.put("q_sta", rs.getObject("q_sta"));
						list.add(row);
					}
					//System.out.println(list.size());
				}
				//System.out.println("........取出已处理（未处理or统计）的总数............"+result1);
				//System.out.println("sys"+"系统---:"+s);
				rs.close();
				sta.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			return list;
		}
		
		
		//显示系统平台
		public List showSystem(String qmail) throws SQLException{
			List list1 = new ArrayList();
			Connection con = null;
			PreparedStatement sta = null;
			ResultSet rs = null;
			try{
				con = DbConnection.getConnection();
				//根据邮箱取出所有的系统平台
					String sql1 = "select distinct q_system from q_qtmatter t where q_mail = '"+qmail+"'";
					sta = con.prepareStatement(sql1);
					rs = sta.executeQuery();
						while(rs.next()){
							String system = null;
							system =  rs.getString("q_system");
							list1.add(system);
						}
					//	System.out.println("list1长度="+list1.size());
				rs.close();
				sta.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			return list1;
		}
		
		//显示系统平台详细信息
		public List showSystemDeteil(String qmail,String sys) throws SQLException{
			List list0 = new ArrayList();
			Connection con = null;
			PreparedStatement sta = null;
			ResultSet rs = null;
			try{
				con = DbConnection.getConnection();
				//根据邮箱取出所有的系统平台
					String sql1 = "select * from q_qtmatter t where q_mail = '"+qmail+"'"+"and q_system='"+sys+"'"+"order by q_submittime desc";
				//	System.out.println(sql1);
					sta = con.prepareStatement(sql1);
					rs = sta.executeQuery();
						while(rs.next()){
							Map row = new HashMap();			
							row.put("q_titl", rs.getObject("q_titl"));
							row.put("q_note", rs.getObject("q_note"));
							row.put("q_mail", rs.getObject("q_mail"));
							row.put("q_system", rs.getObject("q_system"));
							row.put("q_type", rs.getObject("q_type"));
							row.put("q_degree", rs.getObject("q_degree"));
							row.put("q_submittime", rs.getObject("q_submittime"));

							list0.add(row);
							
						}
					//	System.out.println("list1长度="+list0.size());
				rs.close();
				sta.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			return list0;
		}
	
		//显示类型
		public List showType() throws SQLException{
			List list2 = new ArrayList();
			Connection con = null;
			PreparedStatement sta = null;
			ResultSet rs = null;
			try{
				con = DbConnection.getConnection();
				//取出搜有的类别
				String sql2 = "select distinct qtype from q_type t";
				sta = con.prepareStatement(sql2);
				rs = sta.executeQuery();
					while(rs.next()){
						String type = null;
						type =  rs.getString("qtype");
						list2.add(type);
					}
				rs.close();
				sta.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("2222222222"+list2.size()) ;
			return list2;
		}
		
		//显示严重级别
		public List showDegree() throws SQLException{
			List list3 = new ArrayList();
//			Connection con = null;
//			PreparedStatement sta = null;
//			ResultSet rs = null;
//			try{
//				con = DbConnection.getConnection();
//				//取出严重级别
//				String sql3 = "select distinct q_degree from q_qtmatter t";
//				sta = con.prepareStatement(sql3);
//				rs = sta.executeQuery();
//					while(rs.next()){
//						String degree = null;
//						degree =  rs.getString("q_degree");
//						list3.add(degree);
//					}
//				rs.close();
//				sta.close();
//			}catch(Exception e){
//				e.printStackTrace();
//			}
			list3.add("1");
			list3.add("2");
			list3.add("3");
			
			return list3;
		}
		
		//显示统计数据
		public List showCountDeteil(String qmail) throws SQLException {
			List list1 = new ArrayList();
			List list2 = new ArrayList();
			List list3 = new ArrayList();

			List result = new ArrayList();
			int r = 0;
			
			Connection con = null;
			PreparedStatement sta = null;
			ResultSet rs = null;
			try{
				con = DbConnection.getConnection();				
				//根据邮箱取出所有的系统平台
					String sql1 = "select distinct q_system from q_qtmatter t where q_mail = '"+qmail+"'";
					sta = con.prepareStatement(sql1);
					rs = sta.executeQuery();
						while(rs.next()){
							String system = null;
							system =  rs.getString("q_system");
							list1.add(system);
						}
//						System.out.println("list1长度="+list1.size());
						
						//取出搜有的类别
						String sql2 = "select distinct qtype from q_type t";
						sta = con.prepareStatement(sql2);
						rs = sta.executeQuery();
							while(rs.next()){
								String type = null;
								type =  rs.getString("qtype");
								list2.add(type);
							}
//						System.out.println("list2长度="+list2.size());
//						//取出严重级别
//						String sql3 = "select distinct q_degree from q_qtmatter t order by q_degree asc";
//						sta = con.prepareStatement(sql3);
//						rs = sta.executeQuery();
//							while(rs.next()){
//								String degree = null;
//								degree =  rs.getString("q_degree");
//								list3.add(degree);
//						}
//							System.out.println("list3长度="+list3.size());	
							list3.add("1");
							list3.add("2");
							list3.add("3");
							System.out.println("list3长度="+list3.size());
							
							
						for(int i = 0;i < list1.size();i++){
							
							String qsystem = (String) list1.get(i);
//							System.out.println("qsystem="+qsystem);
							for(int j =0;j<list2.size();j++){
								
								String qtype = (String) list2.get(j);
//								System.out.println("qtype="+qtype);
								for(int k =0;k<list3.size();k++){									
									String qdegree = (String) list3.get(k);
//									System.out.println("qdegree="+qdegree);
									String sql4 = "select count(*) from q_qtmatter t where q_mail = '"+qmail+"'"+"and q_system='"+qsystem+"'"+"and q_type='"+qtype+"'"+"and q_degree='"+qdegree+"'";
									System.out.println("sql4="+sql4);
							    	sta = con.prepareStatement(sql4);
							    	rs = sta.executeQuery();
							    	while(rs.next()){																								
							    		r = rs.getInt(1);									    		
							    	}	
							    	result.add(r);
//							    	System.out.println("r1="+result);
						    										    	
								}
							}
						}
				rs.close();
				sta.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("size="+result.size());
			return result;
	}
	
//----------------------------------------------------------------------------------------------
//e.jsp调用方法      取出已处理0（未处理1）的总数、统计已处理数true
	public int getCount(String qmail,int isdo,boolean all) throws SQLException {
		int result = 0;
		Connection con = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		String sql = null;
		if(all){
			sql = "select count(q_id) from Q_QTMATTER q " 
					+"where q.q_mail = '"+qmail+"'"
					+" and q.Q_HAVESUBMIT = '0'";
		}else{
			sql = "select count(q_id) from Q_QTMATTER q " 
					+"where q.q_isdo = '"+isdo
					+"' and q.q_mail = '"+qmail+"'"
					+" and q.Q_HAVESUBMIT = '0'";
		}
//		System.out.println(".getCount...sql="+sql);
		try{
			con = DbConnection.getConnection();
			sta = con.prepareStatement(sql);
			rs = sta.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			rs.close();
			sta.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public int getCountByAdmin(String qmail,int isdo) throws SQLException {
		int result = 0;
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		String sql = null;
		
		sql = "select t.s_system from q_sysuser t where s_userid = '"+qmail+"'";
		try{
			con = DbConnection.getConnection();
			sta = con.prepareStatement(sql);
			rs = sta.executeQuery();
			while(rs.next()){
				String r = null;
				r =  rs.getObject("s_system").toString();
				list.add(r);
//				System.out.println("...r="+rs.getObject("s_system").toString());
			}
//			System.out.println(".getCountByAdmin..sql="+sql);
//			System.out.println("...list长度="+list.size());
			for(int i =0;i<list.size();i++){
				String qsystem = (String) list.get(i);
				String sql2 = "select count(q_id) from Q_QTMATTER q " 
					+"where q.q_system = '"+qsystem+"' and q.q_isdo = '"+isdo
					+"' and q.Q_HAVESUBMIT = '0'";
//				System.out.println("....sql2="+sql2);
				sta = con.prepareStatement(sql2);
				rs = sta.executeQuery();
				while(rs.next()){
					result += rs.getInt(1);
				}
			}
		rs.close();
		sta.close();
	}catch(Exception e){
		e.printStackTrace();
	}
		return result;
	}
	//根据系统显示相应负责人邮箱号
			public List showSysMail(String sys) throws SQLException{
				List list1 = new ArrayList();
				Connection con = null;
				PreparedStatement sta = null;
				ResultSet rs = null;
				try{
					con = DbConnection.getConnection();
					//根据系统取出所有的邮箱
						String sql1 = "select S_USERID from Q_SYSUSER where S_SYSTEM = '"+sys+"'";
						sta = con.prepareStatement(sql1);
						rs = sta.executeQuery();
							while(rs.next()){
								String sysid = null;
								sysid =  rs.getString("S_USERID");
								list1.add(sysid);
							}
						//	System.out.println("list1长度="+list1.size());
					rs.close();
					sta.close();
				}catch(Exception e){
					e.printStackTrace();
				}
				return list1;
			}
}