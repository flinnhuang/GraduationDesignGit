package com.gree.q.sys.vo;

import java.util.Date;

public class QuestionVO extends ValueObject{
	private static final long serialVersionUID = 1L;
	
	private String q_id;
	private String q_titl;
	private String q_mail;
	private String q_system;
	private String q_type;
	private String q_degree;
	private long q_note;
	private Date q_submittime;
	private Date q_backtime;
	private int q_sta;
	private String q_accessdries;
	private int q_isdo;
	private int q_havesubmit;
	public String getQ_id() {
		return q_id;
	}
	public void setQ_id(String q_id) {
		this.q_id = q_id;
	}
	public String getQ_titl() {
		return q_titl;
	}
	public void setQ_titl(String q_titl) {
		this.q_titl = q_titl;
	}
	public String getQ_mail() {
		return q_mail;
	}
	public void setQ_mail(String q_mail) {
		this.q_mail = q_mail;
	}
	public String getQ_system() {
		return q_system;
	}
	public void setQ_system(String q_system) {
		this.q_system = q_system;
	}
	public String getQ_type() {
		return q_type;
	}
	public void setQ_type(String q_type) {
		this.q_type = q_type;
	}
	public String getQ_degree() {
		return q_degree;
	}
	public void setQ_degree(String q_degree) {
		this.q_degree = q_degree;
	}
	public long getQ_note() {
		return q_note;
	}
	public void setQ_note(long q_note) {
		this.q_note = q_note;
	}
	public Date getQ_submittime() {
		return q_submittime;
	}
	public void setQ_submittime(Date q_submittime) {
		this.q_submittime = q_submittime;
	}
	public Date getQ_backtime() {
		return q_backtime;
	}
	public void setQ_backtime(Date q_backtime) {
		this.q_backtime = q_backtime;
	}
	public int getQ_sta() {
		return q_sta;
	}
	public void setQ_sta(int q_sta) {
		this.q_sta = q_sta;
	}
	public String getQ_accessdries() {
		return q_accessdries;
	}
	public void setQ_accessdries(String q_accessdries) {
		this.q_accessdries = q_accessdries;
	}
	public int getQ_isdo() {
		return q_isdo;
	}
	public void setQ_isdo(int q_isdo) {
		this.q_isdo = q_isdo;
	}
	public int getQ_havesubmit() {
		return q_havesubmit;
	}
	public void setQ_havesubmit(int q_havesubmit) {
		this.q_havesubmit = q_havesubmit;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
