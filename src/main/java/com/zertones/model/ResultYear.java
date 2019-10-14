package com.zertones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RESULT_YEAR")
public class ResultYear
{

	@Id
	@Column(name = "YEAR_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	yearId;

	@Column(name = "Year")
	private Integer	year;

	public Integer getYearId()
	{
		return yearId;
	}

	public void setYearId(Integer yearId)
	{
		this.yearId = yearId;
	}

	public Integer getYear()
	{
		return year;
	}

	public void setYear(Integer year)
	{
		this.year = year;
	}

	@Override
	public String toString()
	{
		return "ResultYear [yearId=" + yearId + ", year=" + year + "]";
	}

}
