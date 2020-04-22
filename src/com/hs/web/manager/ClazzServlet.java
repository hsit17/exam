package com.hs.web.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.hs.service.ClazzService;
import com.hs.service.impl.ClazzServiceImpl;
import com.hs.utils.Page;

/**
 * Servlet implementation class ClazzServlet
 */
@WebServlet("/manager/clazz")
public class ClazzServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClazzServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取当前要查询的页码
		String curPage = request.getParameter("curPage");
		if(StringUtils.isBlank(curPage)) {
			curPage="1";
		}
		//获取请求参数
		String gradeName = request.getParameter("gradeName");
		String majorName = request.getParameter("majorName");
		//调用service层方法进行分页查询
		ClazzService clazzService = new ClazzServiceImpl();
		Page page = clazzService.getClazzPage(gradeName, majorName, Integer.parseInt(curPage));
		request.setAttribute("page", page);
		//关键字回填
		request.setAttribute("gradeName", gradeName);
		request.setAttribute("majorName", majorName);
		request.getRequestDispatcher("/WEB-INF/page/manager/clazz_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
