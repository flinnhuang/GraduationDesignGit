// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov Date: 2009-4-15
// 19:43:46
// Home Page : http://members.fortunecity.com/neshkov/dj.html - Check often for
// new version!
// Decompiler options: packimports(3)
// Source File Name: InvaidLoginFilter.java

package com.gree.q.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gree.q.sys.vo.Cbase000VO;

public class InvaidLoginFilter implements Filter {

	public InvaidLoginFilter() {
	}

	public void init(FilterConfig filterConfig) {

	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse response_, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) response_;
		HttpSession session = request.getSession();
		// ServletContext app = session.getServletContext();

		String a1 = request.getRequestURI();
		String a1s = a1.split("/")[1];
		// System.out.println("--------------\n:
		// "+request.getRequestURI()+"\n:"+request.getRequestURL());

		String uri = request.getRequestURI();
		if (uri.indexOf("login") == -1 && uri.indexOf("logout") == -1
				&& uri.indexOf("index") == -1 && uri.indexOf("401") == -1) {
			Cbase000VO user = (Cbase000VO) session.getAttribute("userBean");

			if (user == null) {
				response.sendRedirect("/q/401_.htm");
				return;
			}
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		}
		chain.doFilter(servletRequest, response);
	}

	public void destroy() {
	}
}