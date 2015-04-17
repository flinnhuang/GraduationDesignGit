package com.gree.q.sys.vo;

import java.util.Date;

public class Cbase011VO extends ValueObject {
	/**
	 * ��־��
	 */
	private static final long serialVersionUID = 1L;

	private String usid = "";

	private String nama = "";
	
	private int COMP = 0;
	
	private Date idat;
	
	private Date gdat;
	
	private String ipid="";
	
	private int stat=0;
	
	private int tims=0;
	

	public int getCOMP() {
		return COMP;
	}

	public void setCOMP(int comp) {
		COMP = comp;
	}

	public Date getGdat() {
		return gdat;
	}

	public void setGdat(Date gdat) {
		this.gdat = gdat;
	}

	public Date getIdat() {
		return idat;
	}

	public void setIdat(Date idat) {
		this.idat = idat;
	}

	public String getIpid() {
		return ipid;
	}

	public void setIpid(String ipid) {
		this.ipid = ipid;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

	public int getTims() {
		return tims;
	}

	public void setTims(int tims) {
		this.tims = tims;
	}

	public String getUsid() {
		return usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	} 
	
	

	
	
 

}
