package com.gree.q.sys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gree.q.sys.vo.QuestionVO;
import com.gree.q.util.db.DbConnection;

public class QuestionDao {
	//取出所有数据
	public List<QuestionVO> getAllQuestionList(){
		List<QuestionVO> result = new ArrayList<QuestionVO>();
		Connection con = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try{
			con = DbConnection.getConnection();
			String sql = "select * from Q_QTMATTER";
			sta = con.prepareStatement(sql);
			rs = sta.executeQuery();
			System.out.println("-----------"+sql);
			while(rs.next()){
				QuestionVO q = new QuestionVO();
				q.setQ_id(rs.getString("q_id"));
				q.setQ_titl(rs.getString("q_titl"));
				//q.setNOTE(rs.getString("NOTE").toString());
				q.setQ_mail(rs.getString("q_mail"));
				q.setQ_submittime(rs.getDate("q_submittime"));
				q.setQ_type(rs.getString("q_type"));
				q.setQ_sta(rs.getInt("q_sta"));

				result.add(q);
			}
			System.out.println("-----------"+result.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	//取出所有不重复数据
	public List<QuestionVO> getAllDistinctQuestionList(){
		List<QuestionVO> result = new ArrayList<QuestionVO>();
		Connection con = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try{
			con = DbConnection.getConnection();
			String sql = "select distinct q_titl from q_qtmatter t";
			sta = con.prepareStatement(sql);
			rs = sta.executeQuery();
			
			while(rs.next()){
				QuestionVO q = new QuestionVO();
				//q.setQ_id(rs.getString("q_id"));
				q.setQ_titl(rs.getString("q_titl"));
				//q.setNOTE(rs.getString("NOTE").toString());
				//q.setQ_mail(rs.getString("q_mail"));
				//q.setQ_submittime(rs.getDate("q_submittime"));
				//q.setQ_type(rs.getString("q_type"));
				//q.setQ_sta(rs.getInt("q_sta"));

				result.add(q);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	//按关键字搜索
	public List<QuestionVO> getAllQuestionListByKeyWord(String keyWord,int start,int end){		
		List<QuestionVO> result1 = new ArrayList<QuestionVO>();
		Connection con1 = null;
		PreparedStatement sta1 = null;
		ResultSet rs1 = null;
		try{
			con1 = DbConnection.getConnection();
			System.out.println("keyword="+keyWord);
			String sql1 = "select * from (select tab.*,rownum rown_ from (select * from Q_QTMATTER where q_titl like"+"'%"+keyWord+"%')tab where rownum<=" + end
				+ ") where rown_>" + start;
			System.out.println("noncepage=================="+sql1);
			sta1 = con1.prepareStatement(sql1);
			rs1 = sta1.executeQuery();	

				while(rs1.next()){
					QuestionVO q1 = new QuestionVO();
					q1.setQ_id(rs1.getString("q_id"));
					q1.setQ_titl(rs1.getString("q_titl"));
					//q.setNOTE(rs.getString("NOTE").toString());
					q1.setQ_mail(rs1.getString("q_mail"));
					q1.setQ_submittime(rs1.getDate("q_submittime"));
					q1.setQ_type(rs1.getString("q_type"));
					q1.setQ_sta(rs1.getInt("q_sta"));
					result1.add(q1);
			}				
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result1;
	}
	
	//评分
	public boolean updatePointById(int point,String id){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = DbConnection.getConnection();
			String sql = "update Q_QTMATTER set q_sta="+point+"where q_id="+id;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return true;
	}
	
	//计算根据搜索关键词的问题数量
	public int Count(String keyWord) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = DbConnection.getConnection();
			String sql = "select count(*) from Q_QTMATTER where q_titl like"+"'%"+keyWord+"%'";
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
}
