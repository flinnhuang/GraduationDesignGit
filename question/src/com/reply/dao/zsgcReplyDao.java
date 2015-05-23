package com.reply.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.model.REPLY000;

public interface zsgcReplyDao {
	//增
	public boolean addReply(REPLY000 re);
	//删
	public boolean deleteReply(REPLY000 re);//只能删除未追加提问的回复，且时间不能超过24小时。
	//查
	public REPLY000 getmaxlcnumReplyInfoByQid(int qid);
}
