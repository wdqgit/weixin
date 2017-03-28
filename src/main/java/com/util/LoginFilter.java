package com.util;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.po.People;

/**
 * Servlet Filter implementation class loginFilter
 */
@WebFilter("/loginFilter")
public class LoginFilter implements Filter {
	public void destroy() {}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		if(uri.contains("people")){
			chain.doFilter(req, resp);
			return;
		}
		if(req.getSession().getAttribute("people") != null){
			chain.doFilter(req, resp);
			return;
		}
		if(req.getSession().getAttribute("admin") != null){
			chain.doFilter(req, resp);
			return;
		}
		String path = req.getRequestURI() + (req.getQueryString() == null ? "" : "?"+ req.getQueryString());
		System.out.println(path);
		resp.sendRedirect(req.getContextPath() + "/people/loginUI.do?path=" + URLEncoder.encode(path));
	}
	public void init(FilterConfig fConfig) throws ServletException {}

}
