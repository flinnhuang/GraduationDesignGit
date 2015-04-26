package com.user0000.model;

public class USER0000 {
	private int YHSY;//用户索引，数据库自增长user index,id
	private String YHMC;//名称 user name
	private String YHMM;//密码 user password
	private String YHQX;//用户权限 user limits
	private String YHLX;//user type
	public USER0000(){
		
	}
	public int getYHSY() {
		return YHSY;
	}
	public void setYHSY(int yHSY) {
		YHSY = yHSY;
	}
	public String getYHMC() {
		return YHMC;
	}
	public void setYHMC(String yHMC) {
		YHMC = yHMC;
	}
	public String getYHMM() {
		return YHMM;
	}
	public void setYHMM(String yHMM) {
		YHMM = yHMM;
	}
	public String getYHQX() {
		return YHQX;
	}
	public void setYHQX(String yHQX) {
		YHQX = yHQX;
	}
	public String getYHLX() {
		return YHLX;
	}
	public void setYHLX(String yHLX) {
		YHLX = yHLX;
	}
	
}
