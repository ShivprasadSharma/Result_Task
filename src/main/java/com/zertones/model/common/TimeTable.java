package com.zertones.model.common;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zertones.model.BaseModel;
import com.zertones.model.ComClientName;
import com.zertones.model.master.AcademicSemester;
import com.zertones.model.master.AcademicSubject;
import com.zertones.model.master.CourseMaster;

/**
 * @author Abhijit
 * @Created Date : Jan 16, 2016
 */
@Entity
@Table(name = "COM_TIME_TABLE_MST")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "timeTableId")
public class TimeTable extends BaseModel implements java.io.Serializable
{
	private static final long	serialVersionUID	= -1061260518687616241L;

	@Id
	@Column(name = "TIME_TABLE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				timeTableId;

	// @Column(name = "PERIOD_START_TIME")
	// private String periodStartTime;
	//
	// @Column(name = "PERIOD_END_TIME")
	// private String periodEndTime;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "COURSE_ID", nullable = true)
	@JsonProperty("courseMaster") // used to map parent element
	@JsonBackReference
	private CourseMaster		courseMaster;

	// @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	// @JoinColumn(name = "YEAR_ID")
	// private AcademicYear academicYear;

	@Column(name = "ACADEMIC_YEAR_ID")
	private Integer				academicYear;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "SUB_ID", nullable = true)
	private AcademicSubject		academicSubject;

	// @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	// @JoinColumn(name = "DAY_VID")
	// private DayTable dayTable;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "DAY_VID")
	private Days				days;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "DEP_ID")
	private Department			department;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "SEMESTER_VID")
	private AcademicSemester	academicSemester;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "CLIENT_ID")
	private ComClientName		comClientName;

	@Column(name = "DIVISION")
	private String				division;

	@Column(name = "THEORYorPRACTICAL")
	private String				TheoryorPractical;

	@Column(name = "BATCH")
	private Integer				batch_name;

	public Integer getTimeTableId()
	{
		return timeTableId;
	}

	public void setTimeTableId(Integer timeTableId)
	{
		this.timeTableId = timeTableId;
	}

	/*
	 * public Date getPeriodStartTime() { return periodStartTime; }
	 *
	 * public void setPeriodStartTime(Date periodStartTime) { this.periodStartTime =
	 * periodStartTime; }
	 *
	 * public Date getPeriodEndTime() { return periodEndTime; }
	 *
	 * public void setPeriodEndTime(Date periodEndTime) { this.periodEndTime =
	 * periodEndTime; }
	 */

	public CourseMaster getCourseMaster()
	{
		return courseMaster;
	}
	/*
	 * public Lectures getLectures() { return lectures; }
	 * 
	 * public void setLectures(Lectures lectures) { this.lectures = lectures; }
	 */

	public Days getDays()
	{
		return days;
	}

	public void setDays(Days days)
	{
		this.days = days;
	}

	public void setCourseMaster(CourseMaster courseMaster)
	{
		this.courseMaster = courseMaster;
	}

	public AcademicSubject getAcademicSubject()
	{
		return academicSubject;
	}

	public Integer getAcademicYear()
	{
		return academicYear;
	}

	public void setAcademicYear(Integer academicYear)
	{
		this.academicYear = academicYear;
	}

	public void setAcademicSubject(AcademicSubject academicSubject)
	{
		this.academicSubject = academicSubject;
	}

	public Department getDepartment()
	{
		return department;
	}

	public void setDepartment(Department department)
	{
		this.department = department;
	}

	public AcademicSemester getAcademicSemester()
	{
		return academicSemester;
	}

	public void setAcademicSemester(AcademicSemester academicSemester)
	{
		this.academicSemester = academicSemester;
	}

	public ComClientName getComClientName()
	{
		return comClientName;
	}

	public void setComClientName(ComClientName comClientName)
	{
		this.comClientName = comClientName;
	}

	public String getDivision()
	{
		return division;
	}

	public void setDivision(String division)
	{
		this.division = division;
	}

	public String getTheoryorPractical()
	{
		return TheoryorPractical;
	}

	public void setTheoryorPractical(String theoryorPractical)
	{
		TheoryorPractical = theoryorPractical;
	}

	public Integer getBatch_name()
	{
		return batch_name;
	}

	public void setBatch_name(Integer batch_name)
	{
		this.batch_name = batch_name;
	}

}
