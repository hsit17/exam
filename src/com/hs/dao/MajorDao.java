package com.hs.dao;

import java.sql.SQLException;
import java.util.List;

import com.hs.model.Major;

public interface MajorDao {

	public List<Major> getMajorList() throws SQLException;
}
