package com.hs.dao;

import java.sql.SQLException;
import java.util.List;

import com.hs.model.Grade;
import com.hs.utils.Page;

public interface GradeDao {

	public List<Grade> getGradesByName(String name ,Page page,Integer curPage) throws SQLException;
	public int getGradesCount(String name) throws SQLException;
	public int saveGrade(String name) throws SQLException;
	public Grade queryByName(String name) throws SQLException;
	public List<Grade> getGradeList() throws SQLException;
}
