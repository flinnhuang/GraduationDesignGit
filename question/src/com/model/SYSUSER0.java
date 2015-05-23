package com.model;

public class SYSUSER0 {
	private int ID;//数据库自增长id
	private String YHMC;//用户名称
	private String SYSNAME;//系统名称
	private SYSTEMS0 systems0;//在系统表里的id，外键

	public SYSUSER0(){
		
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getYHMC() {
		return YHMC;
	}

	public void setYHMC(String yHMC) {
		YHMC = yHMC;
	}

	public String getSYSNAME() {
		return SYSNAME;
	}

	public void setSYSNAME(String sYSNAME) {
		SYSNAME = sYSNAME;
	}

	public SYSTEMS0 getSystems0() {
		return systems0;
	}

	public void setSystems0(SYSTEMS0 systems0) {
		this.systems0 = systems0;
	}

}
