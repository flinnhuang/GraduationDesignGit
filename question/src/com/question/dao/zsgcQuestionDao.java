package com.question.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.model.QUESTION;

public interface zsgcQuestionDao {
	//增
	public boolean addQuestion(QUESTION qu);
	//删
	public boolean deleteQuestion(int qid);
	//改
	public boolean updateQuestion(QUESTION qu);
	//查
	public List queryQuestionToMapList(String yhmc,String filter,String searchtype, int start, int end);
	
	public Map getQuestionInfoByQidLcnum(int qid, int lcnum);
}
