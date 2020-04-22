package com.hs.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.hs.dao.MenuDao;
import com.hs.dao.impl.MenuDaoImpl;
import com.hs.model.Menu;
import com.hs.service.MenuService;

public class MenuServiceImpl implements MenuService{

	private MenuDao menuDao = new MenuDaoImpl();
	
	/**
	 * 根据角色ID查询菜单列表
	 */
	@Override
	public List<Menu> getMenusByRoleId(Integer roleId) {
		List<Menu> list = null;
		try {
			list = menuDao.getMenusByRoleId(roleId);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
