package com.model;

import java.text.SimpleDateFormat;
import java.util.Date;


public class QUESTION {
	private int ID;//数据库自增长id
	private QANDRTIME qandrtime;//问题序列号QID，外键，异常处理中心生成
	private String TITLE;//主题
	private String SYSNAME;//问题系统分类
	private String QTYPE;//问题类型
	private int DEGREE;//紧急程度
	private String QCONTENT;//内容
	private int SUBMITSTATE;//提交状态
	private int REPLYSTATE;//回复状态
	private int GRADE;//评分
	private int ACCESSORY;//是否有附件1:0
	private Date CREATETIME;//问题提问时间
	private int LCNUM;//问题标注（帖子楼层）
	private String YHMC;//提交问题用户名称
	
	public QUESTION(){
		
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

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}

	public String getSYSNAME() {
		return SYSNAME;
	}

	public void setSYSNAME(String sYSNAME) {
		SYSNAME = sYSNAME;
	}

	public String getQTYPE() {
		return QTYPE;
	}

	public void setQTYPE(String qTYPE) {
		QTYPE = qTYPE;
	}

	public int getDEGREE() {
		return DEGREE;
	}

	public void setDEGREE(int dEGREE) {
		DEGREE = dEGREE;
	}


	public String getQCONTENT() {
		return QCONTENT;
	}

	public void setQCONTENT(String qCONTENT) {
		QCONTENT = qCONTENT;
	}

	public int getSUBMITSTATE() {
		return SUBMITSTATE;
	}

	public void setSUBMITSTATE(int sUBMITSTATE) {
		SUBMITSTATE = sUBMITSTATE;
	}

	public int getREPLYSTATE() {
		return REPLYSTATE;
	}

	public void setREPLYSTATE(int rEPLYSTATE) {
		REPLYSTATE = rEPLYSTATE;
	}

	public int getGRADE() {
		return GRADE;
	}

	public void setGRADE(int gRADE) {
		GRADE = gRADE;
	}

	public int getACCESSORY() {
		return ACCESSORY;
	}

	public void setACCESSORY(int aCCESSORY) {
		ACCESSORY = aCCESSORY;
	}

	public Date getCREATETIME() {
		return CREATETIME;
	}
	public String getCreatetime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String cd = sdf.format(CREATETIME);
		return cd;
	}

	public void setCREATETIME(Date cREATETIME) {
		CREATETIME = cREATETIME;
	}

	public int getLCNUM() {
		return LCNUM;
	}

//	public String getCREATETIME() {
//		return CREATETIME;
//	}
//
//	public void setCREATETIME(String cREATETIME) {
//		CREATETIME = cREATETIME;
//	}

	public void setLCNUM(int lCNUM) {
		LCNUM = lCNUM;
	}

	public String getYHMC() {
		return YHMC;
	}

	public void setYHMC(String yHMC) {
		YHMC = yHMC;
	}
	
}
