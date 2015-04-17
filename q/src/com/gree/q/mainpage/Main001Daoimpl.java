package com.gree.q.mainpage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gree.q.util.db.DbConnection;

public class Main001Daoimpl {
	public String calcuTime(String qid){
		String result = "";
		String str = "处理花费时间：";
		String str2 = "";
		double num = 0.0;
		Connection con = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try{
			con = DbConnection.getConnection();
			String sql = "select to_char(t.q_backtime-t.q_submittime) ti from q_qtmatter t " +
					"where q_id = '"+qid+"'";
			//System.out.println("...........sql......"+sql);
			sta = con.prepareStatement(sql);
			rs = sta.executeQuery();
			while(rs.next()){
				result =  rs.getString("ti");
			}
			if(result != null){
				num = Double.parseDouble(result)*24;
			}
			 
			if(num<=0){
				str = "";
				str2 = "未处理";
				String sql2 = "select to_char(sysdate-t.q_submittime) ti from q_qtmatter t " +
						"where q_id = '"+qid+"'";
				//System.out.println("...........sql2......"+sql2);
				sta = con.prepareStatement(sql2);
				rs = sta.executeQuery();
				while(rs.next()){
					result =  rs.getString("ti");
				}
				if(result != null){
					num = Double.parseDouble(result)*24;
				}
			}
			//System.out.println("......................"+result);
			int day = (int) (num/24);//天
			double h1 =  (num%24);
			int hour = (int) (num%24);//时
			int min=(int) ((h1-hour)*60);//分
			//System.out.println("......num="+num+"...day="+day+"...hour="+hour+"...minute="+min+"..");
			if(day==0){
				result = str+hour+"小时"+min+"分钟"+str2;
			}
			if(day==0 && hour==0){
				result = str+min+"分钟"+str2;
			}
			if(day!=0&&hour!=0 || day!=0&&hour==0){
				result = str+day+"天"+hour+"小时"+min+"分钟"+str2;
			}

			rs.close();
			sta.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
