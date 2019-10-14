package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zertones.model.common.CompanyRepresentative;
import com.zertones.model.common.CompanySelectionRounds;
import com.zertones.model.common.Depatmentlistofdrive;
import com.zertones.model.common.TypeOfIndustry;
import com.zertones.system.utility.JsonDateSerializer;

public class RecruitmentInfoDTO implements Serializable
{

	private int						reInfoId;
	private String					companyName;
	private String					jobtitle;
	private String					branch;
	private String					drivetype;
	private String					jobDescription;
	private String					criteria;
	private String					salary;
	private String					slectionProcess;
	private String					time;
	private String					venue;
	private Date					dateInfo;
	private Date					applydate;
	private String					logoUrl;
	private String					roundName;
	private Integer					year;
	private Integer					tenth;
	private Integer					twelveth;
	private Integer					degree;
	private Integer					backlog;

	List<CompanyRepresentative>		list;
	List<CompanySelectionRounds>	rounds;
	List<TypeOfIndustry>			industrytype;
	List<Depatmentlistofdrive>		deptlist;

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

	public List<CompanyRepresentative> getList()
	{
		return list;
	}

	public void setList(List<CompanyRepresentative> list)
	{
		this.list = list;
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

	public int getReInfoId()
	{
		return reInfoId;
	}

	public void setReInfoId(int reInfoId)
	{
		this.reInfoId = reInfoId;
	}

	public String getRoundName()
	{
		return roundName;
	}

	public void setRoundName(String roundName)
	{
		this.roundName = roundName;
	}

	public List<CompanySelectionRounds> getRounds()
	{
		return rounds;
	}

	public void setRounds(List<CompanySelectionRounds> rounds)
	{
		this.rounds = rounds;
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

	public List<TypeOfIndustry> getIndustrytype()
	{
		return industrytype;
	}

	public void setIndustrytype(List<TypeOfIndustry> industrytype)
	{
		this.industrytype = industrytype;
	}

	public Integer getYear()
	{
		return year;
	}

	public void setYear(Integer year)
	{
		this.year = year;
	}

	public List<Depatmentlistofdrive> getDeptlist()
	{
		return deptlist;
	}

	public void setDeptlist(List<Depatmentlistofdrive> deptlist)
	{
		this.deptlist = deptlist;
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

	@Override
	public String toString()
	{
		return "RecruitmentInfoDTO [reInfoId=" + reInfoId + ", companyName=" + companyName + ", jobtitle=" + jobtitle
				+ ", branch=" + branch + ", drivetype=" + drivetype + ", jobDescription=" + jobDescription
				+ ", criteria=" + criteria + ", salary=" + salary + ", slectionProcess=" + slectionProcess + ", time="
				+ time + ", venue=" + venue + ", dateInfo=" + dateInfo + ", applydate=" + applydate + ", logoUrl="
				+ logoUrl + ", roundName=" + roundName + ", year=" + year + ", tenth=" + tenth + ", twelveth="
				+ twelveth + ", degree=" + degree + ", backlog=" + backlog + ", list=" + list + ", rounds=" + rounds
				+ ", industrytype=" + industrytype + ", deptlist=" + deptlist + "]";
	}

}
