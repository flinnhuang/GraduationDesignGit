package com.reply.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.model.QANDRTIME;
import com.model.QUESTION;
import com.model.REPLY000;
import com.opensymphony.xwork2.ActionSupport;
import com.question.dao.impl.zsgcQuestionDaoImpl;
import com.reply.dao.impl.zsgcReplyDaoImpl;
import com.main.daoimpl.MainDaoimpl;
import com.main.daoimpl.initMethod;
import com.sendmail.daoimpl.sendemail;

public class ReplyAction extends ActionSupport{
	public ReplyAction(){
		
	}
	private String qid;
	private String yhmc;
	zsgcQuestionDaoImpl zsgcqdi;
	MainDaoimpl mdi;
	private REPLY000 re;
	initMethod initmethod;
	zsgcReplyDaoImpl zsgcrdi;
	sendemail semail = new sendemail();
	//回复问题
	public String replyQuestion(){
		String flag = "success";
		QANDRTIME qr = new QANDRTIME();
		int qqid = Integer.parseInt(qid);
		System.out.println("----qqid"+qqid);
		qr = mdi.getQANDRTIME(qqid);
		if(qr == null){
			return INPUT;
		}
		Date nowdate = new Date();
		qr.setREPLYTIME(nowdate);
		
		System.out.println("----------qr---------------qid"+qr.getQID());
		re.setREPLYTIME(nowdate);
		re.setLCNUM(re.getLCNUM()+1);
		re.setQandrtime(qr);
		String contenttext = re.getRCONTENT();
		String cont = contenttext.substring(contenttext.indexOf("<b>回复：</b>")+1, contenttext.length());
		System.out.println("rCONTENT~"+cont);
		
		re.setRCONTENT(cont);
		
		if(zsgcrdi.addReply(re)){
			QUESTION q = new QUESTION();
			System.out.println("----qqid"+qqid);
			q = initmethod.initupdatequestion(qqid, 1);
			System.out.println("----gengxing 问题表"+q.getTITLE());
			q.setREPLYSTATE(0);
			if(zsgcqdi.updateQuestion(q)){
				flag="input";
			}
			if(sendmailforreply(q)){
				flag="input";
			}
			flag = "success";
		}else{
			flag="input";
		}
		return flag;
	}
	//撤销问题
	public String rollbackreply(){
		int qqid = Integer.parseInt(qid);
		REPLY000 rep = zsgcrdi.getmaxlcnumReplyInfoByQid(qqid);
		if(zsgcrdi.deleteReply(rep)){
			QUESTION q = new QUESTION();
			q = initmethod.initupdatequestion(qqid, 1);
			q.setREPLYSTATE(1);
			zsgcqdi.updateQuestion(q);
			return SUCCESS;
		}else{
			return INPUT;
		}
	}

	//发送邮件
	public boolean sendmailforreply(QUESTION q){
		String notes = "系统负责人"+yhmc+"已处理完成";
		String mailcontent = initmethod.initemailcontent(q.getTITLE(), notes);//邮件内容设置
		List maillist = mdi.getEmail(yhmc.trim());
		if(maillist != null){
			semail.sendMail(maillist,mailcontent,q.getDEGREE());
		}else{
			System.out.print("邮箱查找错误！");
			return false;
		}
		return true;
	}
	
	public REPLY000 getRe() {
		return re;
	}
	public void setRe(REPLY000 re) {
		this.re = re;
	}
	public zsgcReplyDaoImpl getZsgcrdi() {
		return zsgcrdi;
	}
	public void setZsgcrdi(zsgcReplyDaoImpl zsgcrdi) {
		this.zsgcrdi = zsgcrdi;
	}
	public MainDaoimpl getMdi() {
		return mdi;
	}
	public void setMdi(MainDaoimpl mdi) {
		this.mdi = mdi;
	}
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public void setInitmethod(initMethod initmethod) {
		this.initmethod = initmethod;
	}
	public void setZsgcqdi(zsgcQuestionDaoImpl zsgcqdi) {
		this.zsgcqdi = zsgcqdi;
	}
	public String getYhmc() {
		return yhmc;
	}
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

}
