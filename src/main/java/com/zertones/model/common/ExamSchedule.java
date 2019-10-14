package com.zertones.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zertones.model.BaseModel;
import com.zertones.model.master.CourseMaster;
import com.zertones.system.utility.JsonDateDeserializer;
import com.zertones.system.utility.JsonDateSerializer;

/**
 * @author Abhijit
 * @Created Date : Jan 16, 2016
 */

@Entity
@Table(name = "COM_EXAM_SCHEDULE")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "examScheduleId")
public class ExamSchedule extends BaseModel implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EXAM_SCHEDULE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer examScheduleId;

	@ManyToOne(optional = true)
	@JoinColumn(name = "COURSE_ID", nullable = false)
	// @JsonProperty("courseMaster") // used to map parent element
	@JsonBackReference
	//// @JsonManagedReference
	// @Fetch(FetchMode.JOIN)
	private CourseMaster courseMaster;

	@Column(name = "PERIOD_START_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date periodStartTime;

	@Column(name = "PERIOD_END_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date periodEndTime;

	@Column(name = "YEAR_VID")
	Integer yearVID;

	@Column(name = "SUBJECT_VID")
	Integer subjectVID;

	@Column(name = "BRANCH_VID")
	Integer branchVID;

	@Column(name = "SEMESTER_VID")
	Integer semesterVID;

	/*
	 * @Column(name = "CREATED_DT")
	 *
	 * @Temporal(TemporalType.TIMESTAMP) private Date createdDate;
	 *
	 * @Column(name = "CREATED_BY") private String createdBy;
	 *
	 * @Column(name = "UPDATED_DT")
	 *
	 * @Temporal(TemporalType.TIMESTAMP) private Date updatedDate;
	 *
	 * @Column(name = "UPDATED_BY") private String updatedBy;
	 *
	 * @Column(name = "RECORD_STATUS") private String recordStatus;
	 */

	public Integer getExamScheduleId()
	{
		return examScheduleId;
	}

	public void setExamScheduleId(Integer examScheduleId)
	{
		this.examScheduleId = examScheduleId;
	}

	public CourseMaster getCourseMaster()
	{
		return courseMaster;
	}

	public void setCourseMaster(CourseMaster courseMaster)
	{
		this.courseMaster = courseMaster;
	}

	public Date getPeriodStartTime()
	{
		return periodStartTime;
	}

	public void setPeriodStartTime(Date periodStartTime)
	{
		this.periodStartTime = periodStartTime;
	}

	public Date getPeriodEndTime()
	{
		return periodEndTime;
	}

	public void setPeriodEndTime(Date periodEndTime)
	{
		this.periodEndTime = periodEndTime;
	}

	public Integer getYearVID()
	{
		return yearVID;
	}

	public void setYearVID(Integer yearVID)
	{
		this.yearVID = yearVID;
	}

	public Integer getSubjectVID()
	{
		return subjectVID;
	}

	public void setSubjectVID(Integer subjectVID)
	{
		this.subjectVID = subjectVID;
	}

	public Integer getBranchVID()
	{
		return branchVID;
	}

	public void setBranchVID(Integer branchVID)
	{
		this.branchVID = branchVID;
	}

	public Integer getSemesterVID()
	{
		return semesterVID;
	}

	public void setSemesterVID(Integer semesterVID)
	{
		this.semesterVID = semesterVID;
	}

	/*
	 * @Override public Date getCreatedDate() { return createdDate; }
	 *
	 * @Override public void setCreatedDate(Date createdDate) { this.createdDate
	 * = createdDate; }
	 *
	 * @Override public String getCreatedBy() { return createdBy; }
	 *
	 * @Override public void setCreatedBy(String createdBy) { this.createdBy =
	 * createdBy; }
	 *
	 * @Override public Date getUpdatedDate() { return updatedDate; }
	 *
	 * @Override public void setUpdatedDate(Date updatedDate) { this.updatedDate
	 * = updatedDate; }
	 *
	 * @Override public String getUpdatedBy() { return updatedBy; }
	 *
	 * @Override public void setUpdatedBy(String updatedBy) { this.updatedBy =
	 * updatedBy; }
	 *
	 * @Override public String getRecordStatus() { return recordStatus; }
	 *
	 * @Override public void setRecordStatus(String recordStatus) {
	 * this.recordStatus = recordStatus; }
	 */

}
