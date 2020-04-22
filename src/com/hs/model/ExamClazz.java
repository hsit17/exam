package com.hs.model;

public class ExamClazz {
    private Integer id;

    private Integer fk_exam;

    private Integer fk_clazz;

    private Byte del_flag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFk_exam() {
		return fk_exam;
	}

	public void setFk_exam(Integer fk_exam) {
		this.fk_exam = fk_exam;
	}

	public Integer getFk_clazz() {
		return fk_clazz;
	}

	public void setFk_clazz(Integer fk_clazz) {
		this.fk_clazz = fk_clazz;
	}

	public Byte getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(Byte del_flag) {
		this.del_flag = del_flag;
	}

    
}