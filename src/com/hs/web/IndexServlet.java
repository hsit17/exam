package com.hs.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hs.model.Menu;
import com.hs.service.LoginService;
import com.hs.service.MenuService;
import com.hs.service.impl.LoginServiceImpl;
import com.hs.service.impl.MenuServiceImpl;

/**
 * 首页
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//判断用户是否登录
		Object obj = request.getSession().getAttribute("user");
		if(obj!=null) {
			//根据角色获取菜单
			Integer roleId = (Integer)request.getSession().getAttribute("roleId");//获取角色ID，在登录成功时存入session中
			//调用service层的方法
			MenuService menuService = new MenuServiceImpl();
			List<Menu> menus = menuService.getMenusByRoleId(roleId);
			//将查询结果保存到request域中，传给页面
			request.setAttribute("menus", menus);
			//跳转到首页
			request.getRequestDispatcher("/WEB-INF/page/index.jsp").forward(request, response);
		}else {
			//判断用户是否选了自动登录
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie:cookies) {
				if("exam_user_info".equals(cookie.getName())) {
					String userInfo = cookie.getValue();
					String[] infoArray = userInfo.split("<>");
					String username = infoArray[0];
					String password = infoArray[1];
					Integer roleId = Integer.parseInt(infoArray[2]);
					//执行登录
					LoginService loginService = new LoginServiceImpl();
					String result = loginService.login(roleId, username, password,  request);
					if(result.equals("yes")) {
						//调用service层的方法
						MenuService menuService = new MenuServiceImpl();
						List<Menu> menus = menuService.getMenusByRoleId(roleId);
						//将查询结果保存到request域中，传给页面
						request.setAttribute("menus", menus);
						request.getRequestDispatcher("/WEB-INF/page/index.jsp").forward(request, response);
						return;
					}else {
						//重定向到登录页面
						response.sendRedirect("login");
						return;
					}
				}
			}
			response.sendRedirect("login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
