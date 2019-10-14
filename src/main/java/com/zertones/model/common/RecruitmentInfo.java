package com.zertones.model.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zertones.model.BaseModel;
import com.zertones.system.utility.JsonDateSerializer;

@Entity
@Table(name = "RECRUITMENT_INFO")
public class RecruitmentInfo extends BaseModel implements Serializable
{
	@Id
	@Column(name = "REINFO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int		reInfoId;

	@Column(name = "COMPANY_NAME")
	private String	companyName;

	@Column(name = "JOB_TITLE")
	private String	jobtitle;

	@Column(name = "DEPARTMENT")
	private String	branch;

	@Column(name = "TYPE_OF_DRIVE")
	private String	drivetype;

	@Column(name = "DESCRIPTION")
	private String	jobDescription;

	@Column(name = "CROTERA")
	private String	criteria;

	@Column(name = "SALARY")
	private String	salary;

	@Column(name = "SEL_PROCESS")
	private String	slectionProcess;

	@Column(name = "TIME")
	private String	time;

	@Column(name = "VENU")
	private String	venue;

	@Column(name = "DATE_INFO")
	private Date	dateInfo;

	@Column(name = "LAST_DATE_APPLY")
	private Date	applydate;

	@Column(name = "LOGO_URL")
	private String	logoUrl;

	@Column(name = "YEAR")
	private Integer	year;

	@Column(name = "10TH_CRITERIA")
	private Integer	tenth;

	@Column(name = "12TH_DIP_CRITERIA")
	private Integer	twelveth;

	@Column(name = "DEGREE_CRITERIA")
	private Integer	degree;

	@Column(name = "BACKLOG_CRITERIA")
	private Integer	backlog;

	public int getReInfoId()
	{
		return reInfoId;
	}

	public void setReInfoId(int reInfoId)
	{
		this.reInfoId = reInfoId;
	}

	public String getCompanyName()
	{
		return companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	public String getJobDescription()
	{
		return jobDescription;
	}

	public void setJobDescription(String jobDescription)
	{
		this.jobDescription = jobDescription;
	}

	public String getCriteria()
	{
		return criteria;
	}

	public void setCriteria(String criteria)
	{
		this.criteria = criteria;
	}

	public String getSalary()
	{
		return salary;
	}

	public void setSalary(String salary)
	{
		this.salary = salary;
	}

	public String getSlectionProcess()
	{
		return slectionProcess;
	}

	public void setSlectionProcess(String slectionProcess)
	{
		this.slectionProcess = slectionProcess;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public String getVenue()
	{
		return venue;
	}

	public void setVenue(String venue)
	{
		this.venue = venue;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getDateInfo()
	{
		return dateInfo;
	}

	public void setDateInfo(Date dateInfo)
	{
		this.dateInfo = dateInfo;
	}

	public String getLogoUrl()
	{
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl)
	{
		this.logoUrl = logoUrl;
	}

	public String getJobtitle()
	{
		return jobtitle;
	}

	public void setJobtitle(String jobtitle)
	{
		this.jobtitle = jobtitle;
	}

	public String getBranch()
	{
		return branch;
	}

	public void setBranch(String branch)
	{
		this.branch = branch;
	}

	public String getDrivetype()
	{
		return drivetype;
	}

	public void setDrivetype(String drivetype)
	{
		this.drivetype = drivetype;
	}

	public Integer getYear()
	{
		return year;
	}

	public void setYear(Integer year)
	{
		this.year = year;
	}

	public Integer getTenth()
	{
		return tenth;
	}

	public void setTenth(Integer tenth)
	{
		this.tenth = tenth;
	}

	public Integer getTwelveth()
	{
		return twelveth;
	}

	public void setTwelveth(Integer twelveth)
	{
		this.twelveth = twelveth;
	}

	public Integer getDegree()
	{
		return degree;
	}

	public void setDegree(Integer degree)
	{
		this.degree = degree;
	}

	public Integer getBacklog()
	{
		return backlog;
	}

	public void setBacklog(Integer backlog)
	{
		this.backlog = backlog;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getApplydate()
	{
		return applydate;
	}

	public void setApplydate(Date applydate)
	{
		this.applydate = applydate;
	}

	@Override
	public String toString()
	{
		return "RecruitmentInfo [reInfoId=" + reInfoId + ", companyName=" + companyName + ", jobtitle=" + jobtitle
				+ ", branch=" + branch + ", drivetype=" + drivetype + ", jobDescription=" + jobDescription
				+ ", criteria=" + criteria + ", salary=" + salary + ", slectionProcess=" + slectionProcess + ", time="
				+ time + ", venue=" + venue + ", dateInfo=" + dateInfo + ", applydate=" + applydate + ", logoUrl="
				+ logoUrl + ", year=" + year + ", tenth=" + tenth + ", twelveth=" + twelveth + ", degree=" + degree
				+ ", backlog=" + backlog + "]";
	}

}
