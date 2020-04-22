package com.hs.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.hs.utils.Page;

/**
 * 分页标签类
 * @author snake
 *
 */
public class PageTag extends SimpleTagSupport{
	
	private String action;//标签参数

	@Override
	public void doTag() throws JspException, IOException {
		//获取jsp内置对象pageContext，通过pageContext可以获取到其他内置对象，比如request
		PageContext pageContext = (PageContext)this.getJspContext();
		Page page = (Page)pageContext.getRequest().getAttribute("page");
		//获取项目绝对路径
		String path = pageContext.getRequest().getServletContext().getContextPath();
		String basePath = pageContext.getRequest().getScheme()+"://"
				+pageContext.getRequest().getServerName()+":"
				+pageContext.getRequest().getServerPort()+path+"/";
		//获取分页工具条的html字符串
		String pageBar = page.getPageBar(action, basePath);
		pageContext.getOut().println(pageBar);
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
