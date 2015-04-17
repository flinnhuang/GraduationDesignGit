package com.gree.q.mainpage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.gree.q.util.db.DbConnection;
import com.gree.q.util.db.preparedStatement;

public class MpDaoImpl {
	//------------------------分页方法，根据登录用户、搜索字符、搜索类型，取出其所有记录数
		public int Conut(String qmail,String filter, String searchtype,Connection conn) throws SQLException {
			int result = 0;
			List list = new ArrayList();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try{
				String titltemp = "";
				
				String sql1 = "select distinct s_system from q_sysuser where S_USERID ='"+qmail+"'";
//				System.out.println("--"+sql1);
				ps = conn.prepareStatement(sql1);
				rs = ps.executeQuery();
				while(rs.next()){
					String r = null;
					r =  rs.getString("s_system");
					list.add(r);
				}
//				System.out.println("...list长度="+list.size());
				
				if (!"".equals(filter)){//根据传入的搜索字符，添加相应sql查询语句
//					System.out.println("传入方法中的filter="+filter);
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
						titltemp = " and "+searchtype+" like '%" + filter + "%'";
					}
				}
				
				for(int i =0;i<list.size();i++){
					String qsystem = (String) list.get(i);
					String sql2 = "select count(q_id) from Q_QTMATTER q " 
							+"where q.q_system = '"+qsystem+"'"+titltemp+"and Q_HAVESUBMIT = '0'";
//					System.out.println(".."+i+"..sql2="+sql2);
					ps = conn.prepareStatement(sql2);
					rs = ps.executeQuery();
					while(rs.next()){
						result += rs.getInt(1);
					}
				}
//				System.out.println("........取出问题的总数............"+result);
				//System.out.println("sys"+"系统---:"+s);
				rs.close();
				ps.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}
		//分页方法------------------搜索主题、开始行数、结束行数，取出中间数据保存到map用list返回
		public List queryAqus001toMapList(String qmail,String filter, String searchtype,int start, int end,
				Connection conn) throws SQLException {
			List result = new ArrayList();
			System.out.println("0.0-----------mpdi---query---");
			//开始查找用户所负责系统
			List list = new ArrayList();
			String sql = "select distinct s_system from q_sysuser where S_USERID ='"+qmail+"'";
//			System.out.println("0.0-----------mpdi---query---"+sql);
			preparedStatement ps = preparedStatement.newInstance(conn, sql);
			ResultSet rs = ps.executeQuery();
			
			String temp = "where ";
			String titltemp = "";
			
			while(rs.next()){
				String q = null;
				q = rs.getString("s_system");
				System.out.println("-----------mpdi-- q---"+q);
				list.add(q);
			}
//			System.out.println("-----mpdi--list.size()---"+list.size());
			
			//开始根据查找到的系统，取出符合要求的数据
			for(int i = 0; i<list.size();i++){
				String q = (String) list.get(i);
//				System.out.println("-----mpdi--for循环---"+q);
				temp = temp + "Q_SYSTEM = '"+q+"'";
				if(i<list.size()-1){
					temp += " or ";
				}
			}
			if (!"".equals(filter)){//根据传入的搜索字符，添加相应sql查询语句
//				System.out.println("传入方法中的filter="+filter);
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
			String sql1 = "select * from (select tab.*,rownum rown_ from ("
					+ "select t.* from (select q.*,to_char(q_backtime,'yyyy/mm/dd hh24:mi:ss') backtime " +
					",to_char(q_submittime,'yyyy/mm/dd hh24:mi:ss') submittime from Q_QTMATTER q where " +
					titltemp+"Q_HAVESUBMIT = '0') t "+ temp 
					+ " order by  q_submittime desc " + ") tab where rownum<=" 
					+ end + ") where rown_ >=" + start;
//			System.out.println("-------mpdi-- sql1-----"+sql1);
			ps = preparedStatement.newInstance(conn, sql1);
			rs = ps.executeQuery();
			while (rs.next()) {
				Map row = new HashMap();
				row.put("q_id", rs.getObject("Q_ID"));
				row.put("q_titl", rs.getObject("Q_TITL"));
				row.put("q_mail", rs.getObject("Q_MAIL"));
				row.put("q_system", rs.getObject("Q_SYSTEM"));

				row.put("q_type", rs.getObject("Q_TYPE"));
				row.put("q_degree", rs.getObject("Q_DEGREE"));
				row.put("q_note", rs.getObject("Q_NOTE"));
				row.put("q_submittime", rs.getObject("submittime"));
				row.put("q_backtime", rs.getObject("backtime"));
//				System.out.println("----"+rs.getObject("backtime"));
				row.put("q_sta", rs.getObject("Q_STA"));

				row.put("q_accessories", rs.getObject("Q_ACCESSORIES"));
				row.put("q_isdo", rs.getObject("Q_ISDO"));

				row.put("q_havesubmit", rs.getObject("Q_HAVESUBMIT"));

				result.add(row);
			}
			
			
			rs.close();
			ps.close();
			return result;
		}
		//根据id、表字段名,取得所要查询的项目的数据
		public String getItem(String qid,String searna ,Connection conn) throws SQLException {
			String result = "";
			List list = new ArrayList();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try{
				String sql1 = "select "+searna+" from Q_QTMATTER where Q_ID ='"+qid+"'";
//				System.out.println("--"+sql1);
				ps = conn.prepareStatement(sql1);
				rs = ps.executeQuery();
				while(rs.next()){//-----------------------------------------------------
					if(searna.equals("q_note")||searna.equals("Q_NOTE")){
						InputStream is = null;
						is = rs.getBinaryStream(searna);
						if(is != null){
							result = convertStreamToString(is);
						}else{
							result = "";
						}
					}else{
						result = rs.getString(searna);
					}
				}
			//	result = rs.getString(1);
				rs.close();
				ps.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}
		//根据id取得该记录的数据
		public List getInfo(String qid,Connection conn) throws SQLException {
			List result = new ArrayList();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try{
				String sql1 = "select q.*,to_char(q.q_submittime,'yyyy/mm/dd hh24:mi:ss') qsubmittime " 
						+",to_char(q.q_backtime,'yyyy/mm/dd hh24:mi:ss') qbacktime "
						+"from Q_QTMATTER q where Q_ID ="+qid;
//				System.out.println("--"+sql1);
				ps = conn.prepareStatement(sql1);
				rs = ps.executeQuery();
				while (rs.next()) {
					Map row = new HashMap();
					InputStream is = null;
			
					row.put("q_id", rs.getObject("Q_ID"));
					row.put("q_titl", rs.getObject("Q_TITL"));
					row.put("q_mail", rs.getObject("Q_MAIL"));
					row.put("q_system", rs.getObject("Q_SYSTEM"));
					row.put("q_type", rs.getObject("Q_TYPE"));
					row.put("q_degree", rs.getObject("Q_DEGREE"));
					
					is = rs.getBinaryStream("Q_NOTE");
					String st = null;
					if(is != null){
						st = convertStreamToString(is);
					}else{
						st = "";
					}
					
//					System.out.println("----+++++++++----mpdaoimpl中取得note="+st);
					row.put("q_note", st);
					row.put("q_submittime", rs.getObject("qsubmittime"));
					row.put("q_backtime", rs.getObject("qbacktime"));
					row.put("q_sta", rs.getObject("Q_STA"));

					row.put("q_accessories", rs.getObject("Q_ACCESSORIES"));
					row.put("q_isdo", rs.getObject("Q_ISDO"));

					row.put("q_havesubmit", rs.getObject("Q_HAVESUBMIT"));

					result.add(row);
				}
				System.out.println("---result---"+result.size());
				rs.close();
				ps.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}
		//负责人提交回复，更新数据库
		public int UpdateTable(Map mpdaoimpl001, Connection conn) throws SQLException{
//			System.out.println("UpdateTable....");
			int result = -1;
			String nowtime = mpdaoimpl001.get("q_backtime").toString();
			preparedStatement ps = preparedStatement
					.newInstance(
							conn,
							"update Q_QTMATTER set Q_NOTE =? , Q_BACKTIME=to_date(?,'yyyy/mm/dd hh24:mi:ss') ," +
							"Q_ACCESSORIES=?,Q_ISDO=0,Q_STA=0 where Q_ID = '"+mpdaoimpl001.get("q_id").toString()+"'");
			try {
				ps.setBytes(1, mpdaoimpl001.get("q_note").toString().getBytes("GBK"));
				ps.setString(2, nowtime);
				ps.setString(3, mpdaoimpl001.get("q_accessories").toString());
//				System.out.println("---mpdaoimpl sql="+ps.getQueryString());
				result = ps.executeUpdate();
				ps.close();
				
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
			return result;				
		}
		//用户反馈问题，问题已经且只是保存，e页面的提交“a”链接按钮，更新数据库
		public int qmSubmit(String qid, Connection conn) throws SQLException {
			System.out.println("SubmitTable....");
			int result = -1;
			preparedStatement ps = preparedStatement
					.newInstance(
							conn,
							"update Q_QTMATTER set Q_SUBMITTIME=sysdate ," +
							"Q_HAVESUBMIT=0,Q_ISDO=1 where Q_ID = "+qid);

//				System.out.println("---mpdaoimpl eSubmit sql="+ps.getQueryString());
				result = ps.executeUpdate();
				ps.close();
			return result;				
		}
		//用户删除其所反馈的问题
		public int qmDelete(String qid, Connection conn) throws SQLException {
			System.out.println("DeleteTable....");
			int result = -1;
			preparedStatement ps = preparedStatement
					.newInstance(
							conn,
							"delete from Q_QTMATTER where Q_ID ='"+qid+"'");

//				System.out.println("---mpdaoimpl qmDelete sql="+ps.getQueryString());
				result = ps.executeUpdate();
				ps.close();
			return result;	
		}
		//系统负责人，撤销最近一次的回复
		public int bRepeal(String qid, Connection conn) throws SQLException {
			System.out.println("bRepeal....");
			int result = -1;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String backtime = "";
			String sql1 = "select Q_NOTE,to_char(q_backtime,'yyyy/mm/dd hh24:mi:ss') backtime " +
					"from Q_QTMATTER where Q_ID ='"+qid+"'";
//			System.out.println("-11-"+sql1);
			ps = conn.prepareStatement(sql1);
			rs = ps.executeQuery();
			while (rs.next()) {
				try {
				InputStream is = null;
				is = rs.getBinaryStream("Q_NOTE");
				
				System.out.println("----+++iss="+is);
				String st = null;
				if(is != null){
					st = convertStreamToString(is);
				}else{
					st = "";
				}
//				System.out.println("----+++qnote="+st);
				backtime = rs.getString("backtime");
				int index = st.indexOf("A("+backtime+")");
				if(index>0){
					st=st.substring(0, index);
					System.out.println("----+++删除A("+backtime+")之后的qnote="+st);
				}
				preparedStatement ps2 = preparedStatement
						.newInstance(
								conn,
								"update Q_QTMATTER set Q_NOTE =? , Q_BACKTIME=null ," +
										"Q_ISDO=1 where Q_ID = '"+qid+"'");
				ps2.setBytes(1, st.getBytes("GBK"));
//				System.out.println("---mpdaoimpl sql="+ps2.getQueryString());
				result = ps2.executeUpdate();
				ps2.close();
				is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			rs.close();
			ps.close();
			return result;	
		}
	
		//inputStream流转成String
		public String convertStreamToString(InputStream is){
				System.out.println("---进入转换方法---"+is);
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				StringBuilder sb = new StringBuilder();
				String str = "";
				try{
					while((str = reader.readLine()) != null){
//						System.out.println("---str---"+str);
						if(str==null||str.equals("")){
							str = "";
						}
						sb.append(str);
					}
				}catch(IOException e){
					e.printStackTrace();
				}finally{
					try{
						is.close();
					}catch(IOException e){
						e.printStackTrace();
					}
				}
//				System.out.println("---转换方法得到String---"+sb.toString());
				return sb.toString();
			}
}
