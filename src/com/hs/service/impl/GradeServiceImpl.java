package com.hs.service.impl;

import java.util.List;

import com.hs.dao.GradeDao;
import com.hs.dao.impl.GradeDaoImpl;
import com.hs.model.Grade;
import com.hs.service.GradeService;
import com.hs.utils.Page;

public class GradeServiceImpl implements GradeService{

	private GradeDao gradeDao = new GradeDaoImpl();
	
	/**
	 * 根据年级名称查询列表
	 */
	@Override
	public Page<Grade> getGradesByName(String name,Integer curPage) {
		Page page = new Page();
		try {
			//查询当前页面要显示的记录列表
			List<Grade> list = gradeDao.getGradesByName(name,page,curPage);
			//查询总记录数
			int rowsCount = gradeDao.getGradesCount(name);
			//将查询结果封装到page对象中
			page.setParam(list, curPage, rowsCount);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
	/**
	 * 保存年级信息
	 */
	@Override
	public String saveGrade(String name) {
		String result = null;
		try {
			//1、判断该年级名称是否已经被添加
			Grade grade = gradeDao.queryByName(name);
			if(grade!=null) {
				//已存在
				result = "exist";
			}else {
				//不存在,调用添加方法
				int rows = gradeDao.saveGrade(name);
				if(rows==1) {
					result = "ok";
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 查询所有年级列表
	 */
	@Override
	public List<Grade> getGradeList() {
		List<Grade> list = null;
		try {
			list = gradeDao.getGradeList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
