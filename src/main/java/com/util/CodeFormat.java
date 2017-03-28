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
		final HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse res = (HttpServletResponse) arg1;
		res.setContentType("text/html;charset=UTF-8");  
		HttpServletRequest proxy1 = (HttpServletRequest) Proxy.newProxyInstance(this.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
				
				@Override
				public Object invoke(Object proxy, Method method, Object[] args)
						throws Throwable {
					Object result = null;
					if(req.getMethod().equals("GET")){
						
						if(method.getName().equals("getParameter") || method.getName().equals("getParameterValues")){
							System.out.println("value -- args");
							System.out.println(args[0]);
							String[] value = req.getParameterValues(args[0].toString());
							
							System.out.println(value);
						
							if(value != null && value.length > 0){
								String[] results = new String[value.length];
								for (int i = 0; i < value.length; i++) {
									results[i] = new String( value[i].getBytes("ISO-8859-1"),"UTF-8");
									System.out.println(results[i]);
								}
								return results;
							}
							else{
								result = method.invoke(req, args);
							}
						}else{
							result = method.invoke(req, args);
						}
					}else{
						req.setCharacterEncoding("UTF-8");
					
						 result = method.invoke(req, args);
					}
					
					return result;
				}
			});
		
		chain.doFilter(proxy1, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	

}
