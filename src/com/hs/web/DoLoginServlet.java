package com.hs.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hs.service.LoginService;
import com.hs.service.impl.LoginServiceImpl;
import com.hs.utils.MD5Util;

/**
 * Servlet implementation class DoLoginServlet
 */
@WebServlet("/doLogin")
public class DoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoLoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取登录的用户名和密码,以及角色ID
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String roleId = request.getParameter("role");
		String auto = request.getParameter("auto");
		//调用service层方法
		LoginService loginService = new LoginServiceImpl();
		String result = loginService.login(Integer.parseInt(roleId), username, password,request);
		//如果选了自动登录，需要将登录信息保存到cookie中
		if("true".equals(auto)) {
			String info = username+"<>"+password+"<>"+roleId;
			Cookie cookie = new Cookie("exam_user_info",info);
			cookie.setMaxAge(30*24*60*60);//保存30天
			response.addCookie(cookie);
		}else {
			//如果没有选择自动登录，从cookie中移除登录信息
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("exam_user_info")) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		response.getWriter().write(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
