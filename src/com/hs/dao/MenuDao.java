package com.hs.dao;

import java.sql.SQLException;
import java.util.List;

import com.hs.model.Menu;

public interface MenuDao {

	/**
	 * 根据角色ID查询菜单列表
	 * @param roleId
	 * @return
	 */
	public List<Menu> getMenusByRoleId(Integer roleId) throws SQLException;
}
