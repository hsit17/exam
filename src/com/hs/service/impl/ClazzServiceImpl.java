package com.hs.service.impl;

import java.util.List;
import java.util.Map;

import com.hs.dao.ClazzDao;
import com.hs.dao.impl.ClazzDaoImpl;
import com.hs.model.Clazz;
import com.hs.service.ClazzService;
import com.hs.utils.Page;

public class ClazzServiceImpl implements ClazzService{

	private ClazzDao clazzDao = new ClazzDaoImpl();
	
	/**
	 * 分页查询班级列表
	 */
	@Override
	public Page<Clazz> getClazzPage(String gradeName, String majorName,int curPage) {
		Page page = new Page();
		try {
			List<Map<String,Object>> list = clazzDao.queryClazzList(gradeName, majorName, curPage, page);
			int rowsCount = clazzDao.queryClazzCount(gradeName, majorName);
			page.setParam(list, curPage, rowsCount);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
	/**
	 * 保存班级信息
	 */
	@Override
	public String saveClazz(String gradeId, String majorId, String cno) {
		String result = null;
		try {
			//判断该班级是否已经被添加
			Clazz clazz = clazzDao.getClazzByGidAndMidAndCno(gradeId, majorId, cno);
			if(clazz!=null) {//已存在
				result="exist";
			}else {//不存在
				int rows = clazzDao.saveClazz(gradeId, majorId, cno);
				if(rows==1) {
					result="ok";
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 删除班级
	 */
	@Override
	public String delClazz(String clazzId) {
		String result = null;
		try {
			int rows = clazzDao.delClazz(clazzId);
			if(rows==1) {
				result="ok";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
