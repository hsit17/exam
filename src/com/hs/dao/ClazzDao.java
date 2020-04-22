package com.hs.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.hs.model.Clazz;
import com.hs.utils.Page;

public interface ClazzDao {

	public List<Map<String,Object>> queryClazzList(String gradeName,String majorName,int curPage,Page page) throws SQLException;
	public int queryClazzCount(String gradeName,String majorName) throws SQLException;
	
	public int saveClazz(String gradeId,String majorId,String cno) throws SQLException;
	public Clazz getClazzByGidAndMidAndCno(String gradeId,String majorId,String cno) throws SQLException;
	public int delClazz(String clazzId) throws SQLException;
}
