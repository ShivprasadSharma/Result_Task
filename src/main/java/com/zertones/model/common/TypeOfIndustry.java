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
@Table(name = "TYPE_OF_INDUSTRY_DRIVE")
public class TypeOfIndustry extends BaseModel implements Serializable
{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int				id;

	@Column(name = "INDUSTRY_NAME")
	private String			industryname;

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

	public String getIndustryname()
	{
		return industryname;
	}

	public void setIndustryname(String industryname)
	{
		this.industryname = industryname;
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
		return "Type_Of_Industry [id=" + id + ", industryname=" + industryname + ", recruitmentInfo=" + recruitmentInfo
				+ "]";
	}

}
