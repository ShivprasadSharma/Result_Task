package com.zertones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RESULT_ACDYEAR")
public class ResultAcadyear
{

	@Id
	@Column(name = "AID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	aid;

	@Column(name = "ACDEMICYEAR")
	private String	academicyear;

	public Integer getAid()
	{
		return aid;
	}

	public void setAid(Integer aid)
	{
		this.aid = aid;
	}

	public String getAcademicyear()
	{
		return academicyear;
	}

	public void setAcademicyear(String academicyear)
	{
		this.academicyear = academicyear;
	}

	@Override
	public String toString()
	{
		return "ResultAcadyear [aid=" + aid + ", academicyear=" + academicyear + "]";
	}

}
