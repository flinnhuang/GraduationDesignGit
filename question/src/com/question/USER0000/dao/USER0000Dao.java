package com.question.USER0000.dao;

import java.util.List;

import com.question.USER0000.model.USER0000;

public interface USER0000Dao {
	//登陆验证
	public boolean userLogin(USER0000 user0000);
	//更改密码
	public int userChangePwd(String username,String oldpwd,String newpwd);
	//获取用户信息
	public List getUserInfo(String username);
}
