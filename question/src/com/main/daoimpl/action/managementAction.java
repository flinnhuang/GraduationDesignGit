package com.main.daoimpl.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.model.QANDRTIME;
import com.model.QUESTION;
import com.opensymphony.xwork2.ActionSupport;
import com.question.dao.impl.zsgcQuestionDaoImpl;
import com.main.daoimpl.MainDaoimpl;
import com.main.daoimpl.initMethod;
import com.main.daoimpl.management;
import com.sendmail.daoimpl.sendemail;

public class managementAction extends ActionSupport{
	public managementAction(){
		
	}
	private String sysname;
	private String yhmc;
	private String table1;
	private String tid;
	
	private management mg;
	//only提交问题
	public String adddate(){
		String returnstr = "input";
		boolean flag1 = true;
		boolean flag2 = true;
		if(table1.equals("SYSUSER0")){//说明是保存系统负责人[SYSUSER0]表
			if(yhmc!=null && !yhmc.equals("")){
				flag1 = mg.isintable("USER0000", "YHMC", yhmc);//在用户表中验证该用户账号是否存在
			}
			if(sysname!=null && !sysname.equals("")){
				flag2 = mg.isintable("SYSTEMS0", "SYSNAME", sysname);//在系统表中验证该系统是否存在
			}
			
			if(flag1 && flag2){//两验证同时通过才能保存数据
				System.out.print("-----保存系统负责人表--"+yhmc+"-------"+sysname);
				if(mg.adddateformanagement(yhmc, sysname)){
					returnstr = "success";
				}
			}
		}else{//说明保存的是系统表
			if(flag1 && flag2){//两验证同时通过才能保存数据
				if(mg.adddateformanagement(sysname)){//调用多态方法
					returnstr = "success";
				}
			}
		}
		return returnstr;
	}
	public String deletedate(){
		String returnstr = "input";
		if(mg.deletedateformanagement(table1, Integer.parseInt(tid))){//调用多态方法
			returnstr = "success";
		}
		return returnstr;
	}
	
	public String getSysname() {
		return sysname;
	}
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	public String getYhmc() {
		return yhmc;
	}
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	public String getTable1() {
		return table1;
	}
	public void setTable1(String table1) {
		this.table1 = table1;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public management getMg() {
		return mg;
	}
	public void setMg(management mg) {
		this.mg = mg;
	}
	

}
