package com.gree.q.util.db;

//import com.gree.ht.sys.mo.Qcom001MO;
//import com.gree.ht.sys.mo.SequencesMO;
////import com.gree.ht.sys.vo.Qcom001VO;
//import com.gree.ht.sys.vo.SequencesVO;
//
//public class Sequence {
//  private static SequencesMO sequencesmo = SequencesMO.newInstance();
//  public static String getNextId(String coutername) {
//    String key = "";
//    SequencesVO sqvo = sequencesmo.selectByKey(coutername);
//    if (!sqvo.isNull()) {
//      String head = sqvo.getHEAD();
//      long cval = sqvo.getCVAL();
//      int step = sqvo.getSTEP();
//      int l1 = head.length();
//      int l2 = (String.valueOf(cval)).length();
//      if (6 - l1 - l2 == 0) {
//        key = head + String.valueOf(cval);
//      }
//      else if (6 - l1 - l2 == 1) {
//        key = head + "0" + String.valueOf(cval);
//      }
//      else if (6 - l1 - l2 == 2) {
//        key = head + "00" + String.valueOf(cval);
//      }1313
//      else if (6 - l1 - l2 == 3) {
//        key = head + "000" + String.valueOf(cval);
//      }
//      else if (6 - l1 - l2 == 4) {
//        key = head + "0000" + String.valueOf(cval);
//      }
//      else if (6 - l1 - l2 == 5) {
//        key = head + "00000" + String.valueOf(cval);
//      }
//      cval += step;
//      if (cval >= sqvo.getMVAL()) {
//        cval = 1;
//        sqvo.setCVAL(cval);
//      }
//      else {
//        sqvo.setCVAL(cval);
//      }
//      sequencesmo.update(sqvo);
//    }
//    else {
//      key = "error!error!error!error!error!";
//    }
//    return key;
//  }
//
//  public static int getNextIdInt(String coutername) {
//    SequencesMO sequencesmo = SequencesMO.newInstance();
//    SequencesVO sqvo = sequencesmo.selectByKey(coutername);
//    long key = 0;
//    long cval = sqvo.getCVAL();
//
//    if (sqvo!=null && !sqvo.isNull()) {
//      key = cval;
//      cval++;
//      sqvo.setCVAL(cval);
//      sequencesmo.update(sqvo);
//    }
//    else {
//      key = -1;
//    }
//    return (int)key;
//  }
//  public static long getNextIdLong(String coutername) {
//    SequencesMO sequencesmo = SequencesMO.newInstance();
//    SequencesVO sqvo = sequencesmo.selectByKey(coutername);
//    long key = 0;
//    long cval = sqvo.getCVAL();
//
//    if (!sqvo.isNull()) {
//      key = cval;
//      cval++;
//      sqvo.setCVAL(cval);
//      sequencesmo.update(sqvo);
//    }
//    else {
//      key = -1;
//    }
//    return key;
//  }
//
////
////  public static String getORNO(String coutername) {
////    Qcom001MO qcom001mo = Qcom001MO.newInstance();
////    Qcom001VO qcom001vo = qcom001mo.selectByKey(coutername);
////    String head = qcom001vo.getSERI();
////    long next = qcom001vo.getMXNO();
////    int Rollback;
////    String key = "";
////    SequencesMO sequencesmo = SequencesMO.newInstance();
////    SequencesVO sequencesvo = sequencesmo.selectByKey("PTYP");
////
////    Rollback = sequencesvo.getRBAK();
////    if (next == sequencesvo.getMVAL()) {
////      qcom001vo.setMXNO(sequencesvo.getSVAL());
////      Rollback = sequencesvo.getRBAK() + 1;
////      sequencesvo.setRBAK(Rollback);
////    }
////    else {
////      qcom001vo.setMXNO(next + sequencesvo.getSTEP());
////      if (next < 10) {
////        key = head + "0000000" + Long.toString(next);
////      }
////      else if (next < 100) {
////        key = head + "000000" + Long.toString(next);
////      }
////      else if (next < 1000) {
////        key = head + "00000" + Long.toString(next);
////      }
////      else if (next < 10000) {
////        key = head + "0000" + Long.toString(next);
////      }
////      else if (next < 100000) {
////        key = head + "000" + Long.toString(next);
////      }
////      else if (next < 1000000) {
////        key = head + "00" + Long.toString(next);
////      }
////      else if (next < 10000000) {
////        key = head + "0" + Long.toString(next);
////      }
////      else if (next < 1000000000) {
////        key = head + Long.toString(next);
////      }
////      else {
////        key = "00000000";
////      }
////    }
////    qcom001mo.update(qcom001vo);
////    return key;
////  }
//}
