package com.zertones.model.DataTransferObjectModel;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class ResultInstituteList
{
	private String	branch;

	private Integer	semister;

	private Integer	year;

	private String	academiyr;

	private String	collegename;

	private String	instituteId;

	public String getBranch()
	{
		return branch;
	}

	public void setBranch(String branch)
	{
		this.branch = branch;
	}

	public Integer getSemister()
	{
		return semister;
	}

	public void setSemister(Integer semister)
	{
		this.semister = semister;
	}

	public Integer getYear()
	{
		return year;
	}

	public void setYear(Integer year)
	{
		this.year = year;
	}

	public String getAcademiyr()
	{
		return academiyr;
	}

	public void setAcademiyr(String academiyr)
	{
		this.academiyr = academiyr;
	}

	public String getCollegename()
	{
		return collegename;
	}

	public void setCollegename(String collegename)
	{
		this.collegename = collegename;
	}

	public String getInstituteId()
	{
		return instituteId;
	}

	public void setInstituteId(String instituteId)
	{
		this.instituteId = instituteId;
	}

	@Override
	public String toString()
	{
		return "ResultInstituteList [branch=" + branch + ", semister=" + semister + ", year=" + year + ", academiyr="
				+ academiyr + ", collegename=" + collegename + ", instituteId=" + instituteId + "]";
	}

}
