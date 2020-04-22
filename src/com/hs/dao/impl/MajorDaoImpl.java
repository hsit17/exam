package com.hs.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hs.dao.MajorDao;
import com.hs.model.Major;
import com.hs.utils.C3P0Utils;

public class MajorDaoImpl implements MajorDao{

	private QueryRunner db = new QueryRunner(C3P0Utils.getDataSource());
	
	/**
	 * 查询所有专业列表
	 */
	@Override
	public List<Major> getMajorList() throws SQLException {
		String sql = "select * from major where del_flag=0 order by id desc";
		return db.query(sql, new BeanListHandler<Major>(Major.class));
	}
}
