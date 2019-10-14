package com.zertones.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zertones.model.BaseModel;

@Table(name = "COM_CLASS_YEAR")
public class ComClassYear extends BaseModel implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLASS_YR_ID")
	private Integer	classYrId;

	@Column(name = "YEAR_NAME")
	private String	year_name;

	public Integer getClassYrId()
	{
		return classYrId;
	}

	public void setClassYrId(Integer classYrId)
	{
		this.classYrId = classYrId;
	}

	public String getYear_name()
	{
		return year_name;
	}

	public void setYear_name(String year_name)
	{
		this.year_name = year_name;
	}

}
