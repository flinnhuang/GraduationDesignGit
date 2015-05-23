package com.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QANDRTIME {
	private int QID;//问题序列号，异常处理中心生成
	private Date CREATETIME;//问题创建时间
	private Date REPLYTIME;//问题回复时间

	public QANDRTIME(){
		
	}

	public int getQID() {
		return QID;
	}

	public void setQID(int qID) {
		QID = qID;
	}

	public Date getREPLYTIME() {
		return REPLYTIME;
	}

	public void setREPLYTIME(Date rEPLYTIME) {
		REPLYTIME = rEPLYTIME;
	}

	public Date getCREATETIME() {
		return CREATETIME;
	}

	public void setCREATETIME(Date cREATETIME) {
		CREATETIME = cREATETIME;
	}
	public String getCreatetime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String cd = sdf.format(CREATETIME);
		return cd;
	}
	public String getReplytime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String cd = sdf.format(REPLYTIME);
		return cd;
	}
}
