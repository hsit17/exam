package com.hs.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hs.dao.LoginDao;
import com.hs.dao.impl.LoginDaoImpl;
import com.hs.model.Manager;
import com.hs.model.Student;
import com.hs.model.Teacher;
import com.hs.service.LoginService;
import com.hs.utils.MD5Util;

public class LoginServiceImpl implements LoginService{

	private LoginDao loginDao = new LoginDaoImpl();
	
	/**
	 * 登录请求
	 */
	@Override
	public String login(Integer roleId, String username, String password,HttpServletRequest request) {
		//定义返回值变量
		String result = null;
		try {
			Object obj = loginDao.login(roleId, username, MD5Util.getMD5(password));
			if(obj==null) {//登录失败
				result="no";
			}else {//登录成功
				result="yes";
				if(roleId==1) {
					request.getSession().setAttribute("roleId", roleId);
					request.getSession().setAttribute("user", (Student)obj);
				}else if(roleId==2) {
					request.getSession().setAttribute("roleId", roleId);
					request.getSession().setAttribute("user", (Teacher)obj);
				}else if(roleId==3) {
					request.getSession().setAttribute("roleId", roleId);
					request.getSession().setAttribute("user", (Manager)obj);
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			result="error";
		}
		
		return result;
	}
	
	/**
	 * 修改密码
	 */
	@Override
	public String updatePassword(HttpServletRequest request) {
		String result = null;
		
		//获取页面参数
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		String rePwd = request.getParameter("rePwd");
		//判断旧密码是否正确
		HttpSession session = request.getSession();
		Integer roleId = (Integer)session.getAttribute("roleId");
		Object ouser = session.getAttribute("user");
		String username = null;
		Integer userId = null;
		//根据角色类型ID将session中获取到的用户对象转换为对应的类型并获取username和id
		if(roleId==1) {//学生
			username = ((Student)ouser).getUsername();
			userId = ((Student)ouser).getId();
		}else if(roleId==2) {//老师
			username = ((Teacher)ouser).getUsername();
			userId = ((Teacher)ouser).getId();
		}else if(roleId==3) {//管理员
			username = ((Manager)ouser).getUsername();
			userId = ((Manager)ouser).getId();
		}
		try {
			Object user = loginDao.login(roleId, username, MD5Util.getMD5(oldPwd));
			//如果user为Null表示原密码错误
			if(user==null) {
				result = "wrong";
			}else {
				//判断两次输入的密码是否一致
				if(newPwd!=null && newPwd.equals(rePwd)) {
					int rows = loginDao.updatePassword(roleId, MD5Util.getMD5(newPwd), userId);
					if(rows==1) {
						result = "ok";
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
