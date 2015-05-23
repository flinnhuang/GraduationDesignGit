package com.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class REPLY000 {
	private int ID;//数据库自增长id
	private QANDRTIME qandrtime;//问题序列号QID，外键，异常处理中心生成
	private String RCONTENT;//回复内容
	private Date REPLYTIME;//问题回复时间
	private int LCNUM;//问题标注（帖子楼层）

	public REPLY000(){
		
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public QANDRTIME getQandrtime() {
		return qandrtime;
	}

	public void setQandrtime(QANDRTIME qandrtime) {
		this.qandrtime = qandrtime;
	}

	public String getRCONTENT() {
		return RCONTENT;
	}

	public void setRCONTENT(String rCONTENT) {
		RCONTENT = rCONTENT;
	}

	public Date getREPLYTIME() {
		return REPLYTIME;
	}

	public void setREPLYTIME(Date rEPLYTIME) {
		REPLYTIME = rEPLYTIME;
	}
	
	public String getReplytime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String cd = sdf.format(REPLYTIME);
		return cd;
	}
	
	public int getLCNUM() {
		return LCNUM;
	}

	public void setLCNUM(int lCNUM) {
		LCNUM = lCNUM;
	}

}
