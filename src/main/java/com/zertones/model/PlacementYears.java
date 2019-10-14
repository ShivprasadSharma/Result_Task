package com.zertones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "PLACEMENT_YR")
@JsonInclude(Include.NON_NULL)
public class PlacementYears extends BaseModel
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	yid;

	@Column(name = "YEAR_NAME")
	private String	year_name;

	public Integer getYid()
	{
		return yid;
	}

	public void setYid(Integer yid)
	{
		this.yid = yid;
	}

	public String getYear_name()
	{
		return year_name;
	}

	public void setYear_name(String year_name)
	{
		this.year_name = year_name;
	}

	@Override
	public String toString()
	{
		return "PlacementYears [yid=" + yid + ", year_name=" + year_name + "]";
	}

}
