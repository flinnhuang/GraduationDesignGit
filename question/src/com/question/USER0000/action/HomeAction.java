package com.question.USER0000.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.question.USER0000.dao.impl.USER0000DaoImpl;
import com.question.USER0000.model.USER0000;

public class HomeAction extends ActionSupport{
	public HomeAction(){
		
	}
	private USER0000 us;
	USER0000DaoImpl udi;
	public String execute() throws Exception{
//		System.out.println("login--execute-"+us.getYHMC()+"---"+us.getYHMM());	
//	    Map sesseion = ActionContext.getContext().getSession();
//		sesseion.put("YHmc", us.getYHMC());
		return SUCCESS;	
	}
	public void validate(){
//		System.out.println("validate方法，login"+us.getYHMC()+"---"+us.getYHMM());
//		HttpServletRequest request = ServletActionContext.getRequest();//取得repuest对象
//		if(!udi.userLogin(us)){
//			this.addFieldError("us.YHMM","账号或密码不对！");	
//			System.out.println("validate方法，登陆失败");
//	    }
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
