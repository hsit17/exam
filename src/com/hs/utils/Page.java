package com.hs.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.map.HashedMap;

/**
 * 分页工具类
 * @author snake
 *
 * @param <T>
 */
public class Page<T> {

	//当前页面显示的数据集合
	private List<T> data = null;
	
	//当前页码
	private int curPage;
	
	//每页显示的记录数量
	private int pageSize=4;
	
	//总记录数量
	private int rowsCount;
	
	//总页数
	private int pageCount;
	
	//用于存放条件查询的查询关键字
	private Map<String,Object> params;

	public Page() {
		super();
	}
	
	public Page(List<T> data,int curPage,int rowsCount) {
		this.data = data;
		this.curPage = curPage;
		this.rowsCount = rowsCount;
		//Math.ceil()向上舍入
		this.pageCount = ((int)Math.ceil((double)rowsCount/pageSize))==0 ? 1 : ((int)Math.ceil((double)rowsCount/pageSize));
	}
	
	public void setParam(List<T> data,int curPage,int rowsCount) {
		this.data = data;
		this.curPage = curPage;
		this.rowsCount = rowsCount;
		this.pageCount = ((int)Math.ceil((double)rowsCount/pageSize))==0 ? 1 : ((int)Math.ceil((double)rowsCount/pageSize));
	}
	
	/**
	 * 添加查询关键字到map中
	 * @param name
	 * @param value
	 */
	public void appendParam(String name,Object value) {
		if(params==null) {
			params = new HashedMap();
		}
		//参数名称不为空且当前map中不包含该key
		if(name!=null && !name.trim().equals("") && params.containsKey(name)==false) {
			params.put(name, value);
		}
	}
	
	/**
	 * 生成分页工具条
	 * @param action
	 * @param basePath
	 * @return
	 */
	public String getPageBar(String action,String basePath) {
		StringBuffer sb = new StringBuffer();
		//当总页数大于1页的时候才需要分页工具条
		if(pageCount>1) {
			sb.append("<form method=\"post\" id=\"pageForm\" name=\"pageForm\" style=\"margin-bottom:0;height:35px\">");
			//将条件查询的参数设置的form的隐藏域中
			if(params!=null && !params.isEmpty()) {
				Iterator<Entry<String,Object>> ite = params.entrySet().iterator();
				while(ite.hasNext()) {
					Map.Entry<String,Object> et = (Map.Entry<String,Object>)ite.next();
					String key = (String)et.getKey();
					Object value = (Object)et.getValue();
					sb.append("<input type='hidden' name='"+key+"' value='"+(value!=null?value:"")+"'>");
				}
			}
			
			sb.append("<div class=\"row\">");
			sb.append("<div class=\"col-sm-12 col-md-12\">");
			sb.append("<div class=\"pull-right\">");
			sb.append("<ul class=\"pagination\">");
			//首页和上一页按钮
			if(curPage==1) {
				//当前页为1时，首页和上一页为disabled，无法点击
				sb.append("<li class=\"paginate_button page-item previous disabled\"><a href=\"#\" class=\"page-link\">首页</a></li>");
				sb.append("<li class=\"paginate_button page-item previous disabled\"><a href=\"#\" class=\"page-link\">上一页</a></li>");
			}else {
				//	否则可以使用
				sb.append("<li class=\"paginate_button page-item previous \"><a href=\"javascript:pageForm.action='"+basePath+action+"?curPage=1';pageForm.submit();\" class=\"page-link\">首页</a></li>");
				sb.append("<li class=\"paginate_button page-item previous \"><a href=\"javascript:pageForm.action='"+basePath+action+"?curPage="+(curPage-1)+"';pageForm.submit();\" class=\"page-link\">上一页</a></li>");
			}
			//中间的数字页码
			for(int i=1;i<=pageCount;i++) {
				if(i==curPage) {
					sb.append("<li class=\"paginate_button page-item active\"><a href=\"javascript:pageForm.action='"+basePath+action+"?curPage="+i+"';pageForm.submit();\" class=\"page-link\">"+i+"</a></li>");
				}else {
					sb.append("<li class=\"paginate_button page-item\"><a href=\"javascript:pageForm.action='"+basePath+action+"?curPage="+i+"';pageForm.submit();\" class=\"page-link\">"+i+"</a></li>");
				}
			}
			//结尾的下一页和最后一页按钮
			if(curPage<pageCount) {
				sb.append("<li class=\"paginate_button page-item\"><a href=\"javascript:pageForm.action='"+basePath+action+"?curPage="+(curPage+1)+"';pageForm.submit();\" class=\"page-link\">下一页</a></li>");
				sb.append("<li class=\"paginate_button page-item\"><a href=\"javascript:pageForm.action='"+basePath+action+"?curPage="+pageCount+"';pageForm.submit();\" class=\"page-link\">最后一页</a></li>");
			}else {
				sb.append("<li class=\"paginate_button page-item disabled\"><a href=\"#\" class=\"page-link\">下一页</a></li>");
				sb.append("<li class=\"paginate_button page-item disabled\"><a href=\"#\" class=\"page-link\">最后一页</a></li>");
			}
			sb.append("</ul>");
			sb.append("</div>");
			sb.append("</div>");
			sb.append("</div>");
			sb.append("</form>");
		}
		return sb.toString();
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRowsCount() {
		return rowsCount;
	}

	public void setRowsCount(int rowsCount) {
		this.rowsCount = rowsCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	
}
