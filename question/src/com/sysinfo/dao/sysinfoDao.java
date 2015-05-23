package com.sysinfo.dao;

public interface sysinfoDao {
//根据用户，以条件：状态1未提交（未回复）or状态0已提交（已回复），统计类别（全部、提交、回复），得出统计数。
	public int getCount(String YHMC, int STATE, String type);
}
