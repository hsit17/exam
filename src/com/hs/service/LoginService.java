package com.hs.service;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

	public String login(Integer roleId,String username,String password,HttpServletRequest request);
	
	public String updatePassword(HttpServletRequest request);
	
}
