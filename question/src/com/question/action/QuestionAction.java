package com.question.action;

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
import com.sendmail.daoimpl.sendemail;

public class QuestionAction extends ActionSupport{
	public QuestionAction(){
		
	}
	private int qid;
	private String update;
	private String titl;
	private String sysname;
	private String typename;
	private String degree;
	private String contenttext;
	private String rlcnum;
	
	private QUESTION qu;
	zsgcQuestionDaoImpl zsgcqdi;
	MainDaoimpl mdi;
	initMethod initmethod;
	sendemail semail = new sendemail();
	//仅保存问题
	public String addQuestion(){
		if(update.equals("update") == false){
			qu = initmethod.initfirstsavequestion(qu);//初始化
		}else{
			System.out.print("update！");
			qu = initupdate();
		}
		if(qu == null){
			System.out.print("问题获取错误！");
			return INPUT;
		}
		if(qu == null){
			return INPUT;
		}
		if(update.equals("update") == false){
			if(zsgcqdi.addQuestion(qu)){
				return SUCCESS;
			}else{
				return INPUT;
			}
		}else{
			if(zsgcqdi.updateQuestion(qu)){
				return SUCCESS;
			}else{
				return INPUT;
			}
		}
		
	}
	//保存并提交问题
	public String addandsubmitquestion(){
		if(update.equals("update") == false){
			qu = initmethod.initfirstsavequestion(qu);//初始化
		}else{
			System.out.print("update！");
			qu = initupdate();
		}
		if(qu == null){
			System.out.print("问题获取错误！");
			return INPUT;
		}
		qu.setSUBMITSTATE(0);//已提交
		String notes = "由用户"+qu.getYHMC()+"发送";
		String mailcontent = initmethod.initemailcontent(qu.getTITLE(), notes);//邮件内容设置
		List maillist = mdi.getSysuser0Email(qu.getSYSNAME().trim());
		if(maillist != null){
			semail.sendMail(maillist,mailcontent,qu.getDEGREE());
		}else{
			System.out.print("邮箱查找错误！");
			return INPUT;
		}
		
		if(update.equals("update") == false){
			if(zsgcqdi.addQuestion(qu)){
				return SUCCESS;
			}else{
				return INPUT;
			}
		}else{
			if(zsgcqdi.updateQuestion(qu)){
				return SUCCESS;
			}else{
				return INPUT;
			}
		}
	}
	//only提交问题
	public String onlysubmitquestion(){
		qu = initmethod.initupdatequestion(qid, 1);//初始化,取得楼层为1的qu数据
		System.out.print("only提交问题qid="+qid);
		if(qu == null){
			System.out.print("问题初始化错误！");
			return INPUT;
		}
		qu.setSUBMITSTATE(0);//已提交
		String notes = "由用户"+qu.getYHMC()+"发送";
		String mailcontent = initmethod.initemailcontent(qu.getTITLE(), notes);//邮件内容设置
		List maillist = mdi.getSysuser0Email(qu.getSYSNAME().trim());
		if(maillist != null){
			semail.sendMail(maillist,mailcontent,qu.getDEGREE());
		}else{
			System.out.print("邮箱查找错误！");
			return INPUT;
		}
		
		if(zsgcqdi.updateQuestion(qu)){
			return SUCCESS;
		}else{
			System.out.print("问题更新错误！");
			return INPUT;
		}
		
	}
	
	public String zhuijiaquestion(){
		Date nowdate = new Date();
		System.out.println("追加问题~");
		qu = initmethod.initupdatequestion(qid, 1);//初始化,取得楼层为1的qu数据
		QUESTION question = new QUESTION();

		question.setQandrtime(qu.getQandrtime());
		QANDRTIME qr = new QANDRTIME();
		qr.setQID(question.getQandrtime().getQID());
		qr.setCREATETIME(nowdate);
		
		question.setQandrtime(qr);
		question.setTITLE(qu.getTITLE());
		question.setSYSNAME(qu.getSYSNAME());
		question.setQTYPE(qu.getQTYPE());
		question.setDEGREE(qu.getDEGREE());
		question.setCREATETIME(nowdate);
		
		String cont = contenttext.substring(contenttext.indexOf("<b>追问：</b>")+1, contenttext.length());
		System.out.println("QCONTENT~"+cont);
		question.setQCONTENT(cont);
		
		int lnum = Integer.parseInt(rlcnum)+1;
		System.out.println("lnum~"+lnum);
		question.setLCNUM(lnum);
		
		question.setYHMC(qu.getYHMC());
		
		qu.setREPLYSTATE(1);
		
		if(zsgcqdi.updateQuestion(qu)){
			if(zsgcqdi.addQuestion(question)){
				return SUCCESS;
			}else{
				qu.setREPLYSTATE(0);
				zsgcqdi.updateQuestion(qu);
				return INPUT;
			}	
		}else{
			return INPUT;
		}
	}
	
	//修改问题时，初始化问题。注：只能修改没有提交过的问题数据。如果提交过了将没有修改功能。
	public QUESTION initupdate(){
		QUESTION qus = initmethod.initupdatequestion(qid, 1);//初始化,取得楼层为1的qu数据
		System.out.print("更新问题qid="+qus.getQandrtime().getQID());
		Date nowdate = new Date();
		System.out.print("nowdate="+nowdate);
		QANDRTIME qr = new QANDRTIME();
		qr.setQID(qus.getQandrtime().getQID());
		qr.setCREATETIME(nowdate);
		qus.setQandrtime(qr);
		qus.setTITLE(titl);
		qus.setSYSNAME(sysname);
		qus.setQTYPE(typename);
		qus.setDEGREE(Integer.parseInt(degree));
		qus.setQCONTENT(contenttext);
		qus.setCREATETIME(nowdate);
		
		return qus;
	}
	
	
	public QUESTION getQu() {
		return qu;
	}
	public void setQu(QUESTION qu) {
		this.qu = qu;
	}
	public zsgcQuestionDaoImpl getZsgcqdi() {
		return zsgcqdi;
	}
	public void setZsgcqdi(zsgcQuestionDaoImpl zsgcqdi) {
		this.zsgcqdi = zsgcqdi;
	}
	public MainDaoimpl getMdi() {
		return mdi;
	}
	public void setMdi(MainDaoimpl mdi) {
		this.mdi = mdi;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public initMethod getInitmethod() {
		return initmethod;
	}
	public void setInitmethod(initMethod initmethod) {
		this.initmethod = initmethod;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getTitl() {
		return titl;
	}
	public void setTitl(String titl) {
		this.titl = titl;
	}
	public String getSysname() {
		return sysname;
	}
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getContenttext() {
		return contenttext;
	}
	public void setContenttext(String contenttext) {
		this.contenttext = contenttext;
	}
	public void setRlcnum(String rlcnum) {
		this.rlcnum = rlcnum;
	}
}
