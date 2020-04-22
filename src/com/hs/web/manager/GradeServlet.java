package com.hs.web.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.hs.service.GradeService;
import com.hs.service.impl.GradeServiceImpl;
import com.hs.utils.Page;

/**
 * Servlet implementation class GradeServlet
 */
@WebServlet("/manager/grade")
public class GradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeServlet() {
        super();
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
		//获取查询关键字
		String name = request.getParameter("name");
		//查询年级列表
		GradeService gradeService = new GradeServiceImpl();
		Page page = gradeService.getGradesByName(name,Integer.parseInt(curPage));
		//将查询结果返回页面
		request.setAttribute("page", page);
		//关键字回填
		request.setAttribute("name", name);
		request.getRequestDispatcher("/WEB-INF/page/manager/grade_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
