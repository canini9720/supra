package com.supra.xss;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XSSFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    	
    }

    

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {
			System.out.println("filter invoked="+request.getContentType());
			String type=request.getContentType();
			if(type!=null && type.contains("multipart/form-data")){
				//XSSMultipartHttpServletRequest xssrequ=new XSSMultipartHttpServletRequest((HttpServletRequest) request);
				//chain.doFilter(xssrequ, response);
				MultipartRequestWrapper xssrequ=new MultipartRequestWrapper((HttpServletRequest) request);
				chain.doFilter(xssrequ, response);
			}else{
				NormalRequestWrapper normarlWrapper= new NormalRequestWrapper((HttpServletRequest) request);
				chain.doFilter(normarlWrapper, response);
			}
	
	    }

}