package com.gree.q.util;

import org.apache.log4j.Logger;

public class daoFactory {
  final static private Logger log = Logger.getLogger(daoFactory.class);

  //���ﶨ���������ݿ����ͱ���Sqlsrv , Oracle , mysql
  static public String dbtype = "Sqlsrv";
  private daoFactory() {
  }

  public static Object getInstance(java.lang.Class cls) {

    String clss = cls.toString() + "Impl";
    //ͨ����ݿ�daoʵ����
    String clsname_comm = clss.split("interface ")[1].toString();
    //������ݿ�daoʵ����
    String clsname_dbtype = clsname_comm + dbtype;

    Object daobj = null;
    try {
      daobj = (Object) cls.getClassLoader().loadClass(clsname_dbtype).
          newInstance();
    }
    catch (InstantiationException ex1) {
      log.error(ex1.getMessage());
    }
    catch (IllegalAccessException ex1) {
      log.error(ex1.getMessage());
    }
    catch (ClassNotFoundException ex1) {
      try {
        daobj = (Object) cls.getClassLoader().loadClass(clsname_comm).
            newInstance();
      }
      catch (InstantiationException ex) {
        log.error(ex.getMessage());
      }
      catch (IllegalAccessException ex) {
        log.error(ex.getMessage());
      }
      catch (ClassNotFoundException ex) {
        log.error(ex.getMessage());
      }
      //log.debug("Comm DAO Implement, No " + clsname_dbtype + " Dao Implement. " );
    }

    return daobj;
  }

}
