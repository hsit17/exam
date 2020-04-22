package com.hs.model;

public class Clazz {
    private Integer id;

    private Integer cno;

    private Integer fk_grade;

    private Integer fk_major;

    private Byte del_flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

	public Integer getFk_grade() {
		return fk_grade;
	}

	public void setFk_grade(Integer fk_grade) {
		this.fk_grade = fk_grade;
	}

	public Integer getFk_major() {
		return fk_major;
	}

	public void setFk_major(Integer fk_major) {
		this.fk_major = fk_major;
	}

	public Byte getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(Byte del_flag) {
		this.del_flag = del_flag;
	}

    
}