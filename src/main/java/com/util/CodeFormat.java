package com.util;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CodeFormat implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest)arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		req.setCharacterEncoding("UTF-8");
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;utf-8");
		if("GET".equals(req.getMethod())){
			HttpServletRequest proxy = (HttpServletRequest)Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(),
					new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							if("getParameter".equals(method.getName())){
								Enumeration<String> names = req.getParameterNames();
								while (names.hasMoreElements()) {
									String name = names.nextElement();
									System.out.println("name:" + name);
									String[] values = req.getParameterValues(name);
									for(int i = 0; i < values.length; i++){
										values[i] = new String(values[i].getBytes("ISO-8859-1"), "UTF-8");
										System.out.print("value:" + values[i]);
									}
									req.setAttribute(name, values);
								}
							}
							return method.invoke(req, args);
							
						}
					});
			chain.doFilter(proxy, resp);
		}else{
			chain.doFilter(req, resp);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	

}
