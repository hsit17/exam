package com.hs.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hs.dao.MenuDao;
import com.hs.model.Menu;
import com.hs.utils.C3P0Utils;

public class MenuDaoImpl implements MenuDao{

	//获取数据库操作对象
	private QueryRunner db = new QueryRunner(C3P0Utils.getDataSource());
	
	/**
	 * 根据角色ID查询菜单列表
	 */
	@Override
	public List<Menu> getMenusByRoleId(Integer roleId) throws SQLException{
		String sql = "select t1.* from menu t1 left join role_menu t2 on t1.id=t2.menu_id where t2.role_id="+roleId;
		List<Menu> list = db.query(sql, new BeanListHandler<Menu>(Menu.class));
		return list;
	}
}
