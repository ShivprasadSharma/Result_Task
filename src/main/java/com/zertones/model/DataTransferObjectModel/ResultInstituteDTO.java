package com.zertones.model.DataTransferObjectModel;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class ResultInstituteDTO
{
	private String	collegename;

	private Integer	instituteid;

	private Integer	semister;

	private Integer	year;

	private String	academiyr;

	private String	branch;

	public String getcollegename()
	{
		return collegename;
	}

	public void setInstitutename(String collegename)
	{
		this.collegename = collegename;
	}

	public Integer getInstituteid()
	{
		return instituteid;
	}

	public void setInstituteid(Integer instituteid)
	{
		this.instituteid = instituteid;
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

	public String getBranch()
	{
		return branch;
	}

	public void setBranch(String branch)
	{
		this.branch = branch;
	}

	@Override
	public String toString()
	{
		return "ResultInstituteDTO [collegename=" + collegename + ", instituteid=" + instituteid + ", semister="
				+ semister + ", year=" + year + ", academiyr=" + academiyr + ", branch=" + branch + "]";
	}

}
