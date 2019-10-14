package com.zertones.model.DataTransferObjectModel;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class TimeTableDTO
{

	private Integer	timeTableId;
	private Integer	dep_id;
	private Integer	semesterId;
	private String	dep_name;
	@Temporal(TemporalType.TIMESTAMP)
	private Date	yearStartDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date	yearEndDate;
	private Integer	subject_id;
	private String	subject_name;
	private String	firstName;
	private String	middleName;
	private String	lastName;
	private Integer	day_VID;
	private String	division;
	private String	theoryorPractical;
	private Integer	batch_name;
	private String	periodStartTime;
	private String	periodEndTime;
	private Integer	academicYear;
	private Integer	devisionid;

	public Integer getDevisionid()
	{
		return devisionid;
	}

	public void setDevisionid(Integer devisionid)
	{
		this.devisionid = devisionid;
	}

	public Integer getAcademicYear()
	{
		return academicYear;
	}

	public void setAcademicYear(Integer academicYear)
	{
		this.academicYear = academicYear;
	}

	public Integer getSemesterId()
	{
		return semesterId;
	}

	public void setSemesterId(Integer semesterId)
	{
		this.semesterId = semesterId;
	}

	public Integer getDep_id()
	{
		return dep_id;
	}

	public void setDep_id(Integer dep_id)
	{
		this.dep_id = dep_id;
	}

	public Integer getTimeTableId()
	{
		return timeTableId;
	}

	public void setTimeTableId(Integer timeTableId)
	{
		this.timeTableId = timeTableId;
	}

	public String getDep_name()
	{
		return dep_name;
	}

	public void setDep_name(String dep_name)
	{
		this.dep_name = dep_name;
	}

	public Date getYearStartDate()
	{
		return yearStartDate;
	}

	public void setYearStartDate(Date yearStartDate)
	{
		this.yearStartDate = yearStartDate;
	}

	public Date getYearEndDate()
	{
		return yearEndDate;
	}

	public void setYearEndDate(Date yearEndDate)
	{
		this.yearEndDate = yearEndDate;
	}

	public String getSubject_name()
	{
		return subject_name;
	}

	public void setSubject_name(String subject_name)
	{
		this.subject_name = subject_name;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public Integer getDay_VID()
	{
		return day_VID;
	}

	public void setDay_VID(Integer day_VID)
	{
		this.day_VID = day_VID;
	}

	public String getDivision()
	{
		return division;
	}

	public void setDivision(String division)
	{
		this.division = division;
	}

	public Integer getBatch_name()
	{
		return batch_name;
	}

	public String getTheoryorPractical()
	{
		return theoryorPractical;
	}

	public void setTheoryorPractical(String theoryorPractical)
	{
		this.theoryorPractical = theoryorPractical;
	}

	public void setBatch_name(Integer batch_name)
	{
		this.batch_name = batch_name;
	}

	public String getPeriodStartTime()
	{
		return periodStartTime;
	}

	public void setPeriodStartTime(String periodStartTime)
	{
		this.periodStartTime = periodStartTime;
	}

	public String getPeriodEndTime()
	{
		return periodEndTime;
	}

	public void setPeriodEndTime(String periodEndTime)
	{
		this.periodEndTime = periodEndTime;
	}

	@Override
	public String toString()
	{
		return "TimeTableDTO [timeTableId=" + timeTableId + ", dep_id=" + dep_id + ", semesterId=" + semesterId
				+ ", dep_name=" + dep_name + ", yearStartDate=" + yearStartDate + ", yearEndDate=" + yearEndDate
				+ ", subject_name=" + subject_name + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", day_VID=" + day_VID + ", division=" + division + ", theoryorPractical="
				+ theoryorPractical + ", batch_name=" + batch_name + ", periodStartTime=" + periodStartTime
				+ ", periodEndTime=" + periodEndTime + "]";
	}

	public Integer getSubject_id()
	{
		return subject_id;
	}

	public void setSubject_id(Integer subject_id)
	{
		this.subject_id = subject_id;
	}

}
