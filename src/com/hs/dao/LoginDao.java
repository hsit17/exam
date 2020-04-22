package com.hs.dao;

import java.sql.SQLException;

public interface LoginDao {

	public Object login(Integer roleId,String username,String password) throws SQLException;
	
	public int updatePassword(Integer roleId,String newPwd,Integer userId)throws SQLException;
}
