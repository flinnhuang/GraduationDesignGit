package com.gree.q.util;

import com.jasson.im.api.APIClient;
import common.Logger;

import java.util.*;
import java.text.*;

public class SMSUtil {

	private static Logger log = Logger.getLogger(SMSUtil.class);

	private APIClient handler = new APIClient();
	private String host = "10.1.18.250";
	//private String host = "192.13.183.250";
	private String dbName = "mas";
	private String apiId = "fywz";
	private String name = "fywz";
	private String pwd = "fywz";

	public SMSUtil() {
		init();
	}

	public void init() {
		int connectRe = handler.init(host, name, pwd, apiId, dbName);
		if (connectRe == APIClient.IMAPI_SUCC) {
			log.debug("��ʼ���ɹ�");
		} else if (connectRe == APIClient.IMAPI_CONN_ERR) {
			log.debug("���ӳ���");
		} else if (connectRe == APIClient.IMAPI_API_ERR) {
			log.debug("api����");
		}
		if (connectRe != APIClient.IMAPI_SUCC) {

		}
	}

	public int SendSM(String[] al, String tmpContent, int smId) {
		int result = 0;
		String mobiles = "";
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf1.format(new java.util.Date());
		// for(int i=0;i<list.size();i++){
		// mobiles=list.get(i).toString();
		result = handler.sendSM(al, tmpContent, date, smId, 10);

		// }
		return result;
	}

	public static void main(String args[]) {
		SMSUtil sm = new SMSUtil();
		// ArrayList al=new ArrayList();
		// al.add("180024");
		String[] al = new String[3];
		al[1] = "15919236470";
		//al[0] = "13824151995";
		//al[2] = "13928020911";
		// al[2]="13824160355";
		int result = 0;
		result = sm.SendSM(al, "��ͬ����ϵͳ���ŷ��͹��ܲ��ԣ�--��Ԫ��", 10);
		if (result == APIClient.IMAPI_SUCC) {
			// ��¼��־
			
			log.debug("���ͳɹ�");
		} else if (result == APIClient.IMAPI_INIT_ERR)
			log.debug("δ��ʼ��");
		else if (result == APIClient.IMAPI_CONN_ERR)
			log.debug("��ݿ�����ʧ��");
		else if (result == APIClient.IMAPI_DATA_ERR)
			log.debug("�������");
		else if (result == APIClient.IMAPI_DATA_TOOLONG)
			log.debug("��Ϣ����̫��");
		else if (result == APIClient.IMAPI_INS_ERR)
			log.debug("��ݿ�������");
		else if (result == APIClient.IMAPI_IFSTATUS_INVALID)
			log.debug("�ӿڴ�����ͣ��ʧЧ״̬");
		else if (result == APIClient.IMAPI_GATEWAY_CONN_ERR)
			log.debug("�������δ����");
		else
			log.debug("�����������");

	}
}
