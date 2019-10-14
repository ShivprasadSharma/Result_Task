package com.zertones.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "DRIVE_DEPARTMENTS")
public class Depatmentlistofdrive extends BaseModel implements Serializable
{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int				id;

	@Column(name = "DEPT_NAME")
	private String			deptname;

	@ManyToOne
	@JoinColumn(name = "REINFO_ID", nullable = false)
	private RecruitmentInfo	recruitmentInfo;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getDeptname()
	{
		return deptname;
	}

	public void setDeptname(String deptname)
	{
		this.deptname = deptname;
	}

	public RecruitmentInfo getRecruitmentInfo()
	{
		return recruitmentInfo;
	}

	public void setRecruitmentInfo(RecruitmentInfo recruitmentInfo)
	{
		this.recruitmentInfo = recruitmentInfo;
	}

	@Override
	public String toString()
	{
		return "depatmentlistofdrive [id=" + id + ", deptname=" + deptname + ", recruitmentInfo=" + recruitmentInfo
				+ "]";
	}

}
