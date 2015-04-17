// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2006-3-4 10:55:47
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SetCharacterEncodingFilter.java

package com.gree.q.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class SetCharacterEncodingFilter implements Filter {

	protected String encoding;

	protected FilterConfig filterConfig;

	public SetCharacterEncodingFilter() {
		encoding = null;
		filterConfig = null;
	}

	public void destroy() {
		encoding = null;
		filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (!req.getRequestURI().endsWith("q/download.do")) {
			//System.out.println("--------------------------- "
			//		+ req.getRequestURI());
			String encoding = selectEncoding(req);
			if (encoding != null)
				req.setCharacterEncoding(encoding);
			chain.doFilter(req, response);
		}else{
			req.setCharacterEncoding("GBK");
			chain.doFilter(req, response);
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		encoding = filterConfig.getInitParameter("encoding");
	}

	protected String selectEncoding(ServletRequest request) {
		return encoding;
	}

}