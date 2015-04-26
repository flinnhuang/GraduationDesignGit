package com.user0000.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.user0000.dao.impl.USER0000DaoImpl;
import com.user0000.model.USER0000;

public class LoginAction extends ActionSupport{
	private USER0000 us;
	USER0000DaoImpl udi;
	public LoginAction(){
		
	}
	public String execute() throws Exception{
		System.out.println("login--execute-"+us.getYHMC()+"---"+us.getYHMM());	
	    Map sesseion = ActionContext.getContext().getSession();
		sesseion.put("YHMC", us.getYHMC());
		return SUCCESS;	
	}
	public void validate(){
		System.out.println("validate mathod login:"+us.getYHMC()+"---"+us.getYHMM());
		//HttpServletRequest request = ServletActionContext.getRequest();//get repuest 
		if(!udi.userLogin(us)){
			this.addFieldError("us.YHMM","user name error!");	
			System.out.println("validate login error.");
	    }
	}
	public USER0000 getUs() {
		return us;
	}
	public void setUs(USER0000 us) {
		this.us = us;
	}
	public USER0000DaoImpl getUdi() {
		return udi;
	}
	public void setUdi(USER0000DaoImpl udi) {
		this.udi = udi;
	}

}
