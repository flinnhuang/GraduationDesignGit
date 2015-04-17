package com.gree.q.util;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.gree.q.sys.vo.Cbase000VO;


//public class MyListener implements HttpSessionListener,ServletRequestListener,HttpSessionAttributeListener,ServletContextListener{
public class MyListener implements HttpSessionListener,HttpSessionAttributeListener,ServletContextListener,HttpSessionBindingListener{
private static Logger log=Logger.getLogger(MyListener.class);
public MyListener() {}
//private static HashMap onlineUsers=new HashMap();
//private ServletContext app;
/**
 * sessionCreated
 *
 * @param httpSessionEvent HttpSessionEvent
 */
public void sessionCreated(HttpSessionEvent event) {
  //HttpSession session=event.getSession();
}

/**
 * sessionDestroyed
 *
 * @param httpSessionEvent HttpSessionEvent
 */
public void sessionDestroyed(HttpSessionEvent event) {
  HttpSession session=event.getSession();

  Cbase000VO user=(Cbase000VO) session.getAttribute("Userinfo");
  if(user==null)return;

  ServletContext app=session.getServletContext();
  HashMap onlineUsers=(HashMap) app.getAttribute("onlineUsers");

  onlineUsers.remove(user.getUSID());
  System.out.println("[系统信息]:注销用户( "+user.getUSID()+user.getDSCA()+") "+user.getDEPTDSCA()+".");
}

/**
 * contextInitialized
 *
 * @param servletContextEvent ServletContextEvent
 */
public void contextInitialized(ServletContextEvent event) {
  HashMap onlineUsers=new HashMap();
  ServletContext app=event.getServletContext();
  app.setAttribute("onlineUsers",onlineUsers);

  System.out.println("\n[系统信息]:q....starting....");

}

/**
 * contextDestroyed
 *
 * @param servletContextEvent ServletContextEvent
 */
public void contextDestroyed(ServletContextEvent event) {
  System.out.println("\n[系统信息]:q....ending....");
}

/**
 * attributeAdded
 *
 * @param httpSessionBindingEvent HttpSessionBindingEvent
 */
public void attributeAdded(HttpSessionBindingEvent event) {
  HttpSession session=event.getSession();
  ServletContext app=session.getServletContext();
  HashMap onlineUsers=(HashMap) app.getAttribute("onlineUsers");

  String name=event.getName();
  if("Userinfo".equals(name)){
  	Cbase000VO user=(Cbase000VO)event.getValue();
    onlineUsers.put(user.getUSID(),user);
    System.out.println("[系统信息]:用户( "+user.getUSID()+user.getDSCA()+") "+user.getDEPTDSCA()+" 登录. ");
  }
}

/**
 * attributeRemoved
 *
 * @param httpSessionBindingEvent HttpSessionBindingEvent
 */
public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
  //System.out.println("**********attributeRemoved***********");
}

/**
 * attributeReplaced
 *
 * @param httpSessionBindingEvent HttpSessionBindingEvent
 */
public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

}

/**
 * valueBound
 *
 * @param httpSessionBindingEvent HttpSessionBindingEvent
 */
public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
}

/**
 * valueUnbound
 *
 * @param httpSessionBindingEvent HttpSessionBindingEvent
 */
public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {

}

/**
 * requestDestroyed
 *
 * @param servletRequestEvent ServletRequestEvent

public void requestDestroyed(ServletRequestEvent event) {
  HttpServletRequest request=(HttpServletRequest) event.getServletRequest();
  ServletContext app=event.getServletContext();
  HttpSession session=request.getSession();
  Qcom005VO user=(Qcom005VO) session.getAttribute("Userinfo");
  if(user==null)return;
  //user.setPAGE(request.getServletPath());
  HashMap onlineUsers=(HashMap) app.getAttribute("onlineUsers");
  Qcom005VO uu=(Qcom005VO) onlineUsers.get(user.getIP());

  uu.setPAGE(request.getServletPath());
  System.out.println("****模块跳转****");
}*/

/**
 * requestInitialized
 *
 * @param servletRequestEvent ServletRequestEvent
 */
//public void requestInitialized(ServletRequestEvent event) { }
}