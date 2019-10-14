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
@Table(name = "COMPANY_SELECTION_ROUND")
public class CompanySelectionRounds extends BaseModel implements Serializable
{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int				id;

	@Column(name = "roundName")
	private String			roundName;

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

	public String getRoundName()
	{
		return roundName;
	}

	public void setRoundName(String roundName)
	{
		this.roundName = roundName;
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
		return "CompanySelectionRounds [id=" + id + ", roundName=" + roundName + ", recruitmentInfo=" + recruitmentInfo
				+ "]";
	}

}
