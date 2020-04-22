package com.hs.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.hs.dao.ClazzDao;
import com.hs.model.Clazz;
import com.hs.utils.C3P0Utils;
import com.hs.utils.Page;

public class ClazzDaoImpl implements ClazzDao{
	
	private QueryRunner db = new QueryRunner(C3P0Utils.getDataSource());
	
	/**
	 * 查询班级列表
	 */
	@Override
	public List<Map<String,Object>> queryClazzList(String gradeName, String majorName, int curPage,Page page)
			throws SQLException {
		//多表关联查询：以clazz为主表，关键到年级和专业表，获取年级和专业名称
		String sql = "select t1.id,t1.cno,t2.name as gradeName,t3.name as majorName "
				+ " from clazz t1 "
				+ " left join grade t2 on t1.fk_grade=t2.id "
				+ " left join major t3 on t1.fk_major=t3.id where t1.del_flag=0 ";
		//判断查询关键字是否为空
		if(StringUtils.isNotBlank(gradeName)) {
			sql += " and t2.name like '%"+gradeName+"%'";
			page.appendParam("gradeName", gradeName);
		}
		if(StringUtils.isNotBlank(majorName)) {
			sql += " and t3.name like '%"+majorName+"%'";
			page.appendParam("majorName", majorName);
		}
		sql += " order by t1.id desc limit "+(curPage-1)*page.getPageSize()+","+page.getPageSize();
		return db.query(sql, new MapListHandler());
	}
	
	/**
	 * 查询班级总数
	 */
	public int queryClazzCount(String gradeName, String majorName) throws SQLException {
		String sql = "select count(*) from clazz t1 "
				+ "left join grade t2 on t1.fk_grade=t2.id "
				+ "left join major t3 on t1.fk_major=t3.id where t1.del_flag=0 ";
		//判断查询关键字是否为空
		if(StringUtils.isNotBlank(gradeName)) {
			sql += " and t2.name like '%"+gradeName+"%'";
		}
		if(StringUtils.isNotBlank(majorName)) {
			sql += " and t3.name like '%"+majorName+"%'";
		}
		Long rowsCount = db.query(sql, new ScalarHandler<>());
		return rowsCount.intValue();
	}
	
	/**
	 * 保存班级信息
	 */
	@Override
	public int saveClazz(String gradeId, String majorId, String cno) throws SQLException {
		String sql = "insert into clazz(cno,fk_grade,fk_major) values("+cno+","+gradeId+","+majorId+")";
		return db.update(sql);
	}
	
	/**
	 * 根据条件查询班级信息
	 */
	@Override
	public Clazz getClazzByGidAndMidAndCno(String gradeId, String majorId, String cno) throws SQLException {
		String sql = "select * from clazz where fk_grade="+gradeId+" and fk_major="+majorId+" and cno="+cno+" and del_flag=0";
		return db.query(sql, new BeanHandler<Clazz>(Clazz.class));
	}
	
	/**
	 * 删除班级（软删除）
	 */
	@Override
	public int delClazz(String clazzId) throws SQLException{
		String sql = "update clazz set del_flag=1 where id="+clazzId;
		return db.update(sql);
	}
}
