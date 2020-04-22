package com.hs.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 被增强的对象
		HttpServletRequest req = (HttpServletRequest) request;
		// 增强对象
		req.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 在传递request之前对request的getParameter方法进行增强
		EnhanceRequest enhanceRequest = new EnhanceRequest(req);
		chain.doFilter(enhanceRequest, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

	//包装类
	class EnhanceRequest extends HttpServletRequestWrapper {
		private HttpServletRequest request;
		public EnhanceRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		// 对getParameter增强
		@Override
		public String getParameter(String name) {
			String parameter = request.getParameter(name);// 乱码
			if(parameter!=null) {
				if ("get".equalsIgnoreCase(super.getMethod())) {
					try {
						parameter = new String(parameter.getBytes("utf-8"), "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
			return parameter;
		}
	}
}
