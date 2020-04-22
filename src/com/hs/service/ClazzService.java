package com.hs.service;

import com.hs.model.Clazz;
import com.hs.utils.Page;

public interface ClazzService {

	public Page<Clazz> getClazzPage(String gradeName,String majorName,int curPage);
	
	public String saveClazz(String gradeId,String majorId,String cno);
	
	public String delClazz(String clazzId);
}
