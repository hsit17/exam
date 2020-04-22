package com.hs.service;

import java.util.List;

import com.hs.model.Grade;
import com.hs.utils.Page;

public interface GradeService {

	public Page<Grade> getGradesByName(String name,Integer curPage);
	public String saveGrade(String name);
	public List<Grade> getGradeList();
}
