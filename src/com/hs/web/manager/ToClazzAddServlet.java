package com.hs.web.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hs.model.Grade;
import com.hs.model.Major;
import com.hs.service.GradeService;
import com.hs.service.MajorService;
import com.hs.service.impl.GradeServiceImpl;
import com.hs.service.impl.MajorServiceImpl;

/**
 * Servlet implementation class ToClazzAddServlet
 */
@WebServlet("/manager/ToClazzAddServlet")
public class ToClazzAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToClazzAddServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取年级和专业列表
		GradeService gradeService = new GradeServiceImpl();
		MajorService majorService = new MajorServiceImpl();
		List<Grade> gradeList = gradeService.getGradeList();
		List<Major> majorList = majorService.getMajorList();
		//将查询到的列表集合放入request域中传回页面
		request.setAttribute("gradeList", gradeList);
		request.setAttribute("majorList", majorList);
		request.getRequestDispatcher("/WEB-INF/page/manager/clazz_add.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
