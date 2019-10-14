package com.zertones.model.master;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zertones.model.BaseModel;

/**
 * @author Zerton Team
 * @Created Date : Oct 5, 2015
 */
@Entity
@Table(name = "COM_ACADEMIC_SEMESTER_MST")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "semesterId")

public class AcademicSemester extends BaseModel implements java.io.Serializable
{
	private static final long	serialVersionUID	= 2224355923265442549L;

	@Id
	@Column(name = "SEMESTER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				semesterId;

	@Column(name = "SEMESTER_START_DT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				semesterStartDate	= new Date();

	@Column(name = "SEMESTER_END_DT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				semesterEndDate		= new Date();

	@Column(name = "SEMESTER_STATUS")
	Integer						semesterStatus;

	/*
	 * @Column(name = "CREATED_DT")
	 *
	 * @Temporal(TemporalType.TIMESTAMP) private Date createdDate = new Date();
	 *
	 * @Column(name = "CREATED_BY") private String createdBy;
	 *
	 * @Column(name = "UPDATED_DT")
	 *
	 * @Temporal(TemporalType.TIMESTAMP) private Date updatedDate;
	 *
	 * @Column(name = "UPDATED_BY") private String updatedBy;
	 *
	 * @Column(name = "RECORD_STATUS") private String recordStatus = "A";
	 */

	public Integer getSemesterId()
	{
		return semesterId;
	}

	public void setSemesterId(Integer semesterId)
	{
		this.semesterId = semesterId;
	}

	public Date getSemesterStartDate()
	{
		return semesterStartDate;
	}

	public void setSemesterStartDate(Date semesterStartDate)
	{
		this.semesterStartDate = semesterStartDate;
	}

	public Date getSemesterEndDate()
	{
		return semesterEndDate;
	}

	public void setSemesterEndDate(Date semesterEndDate)
	{
		this.semesterEndDate = semesterEndDate;
	}

	public Integer getSemesterStatus()
	{
		return semesterStatus;
	}

	public void setSemesterStatus(Integer semesterStatus)
	{
		this.semesterStatus = semesterStatus;
	}

	/*
	 * @Override public Date getCreatedDate() { return createdDate; }
	 *
	 * @Override public void setCreatedDate(Date createdDate) { this.createdDate =
	 * createdDate; }
	 *
	 * @Override public String getCreatedBy() { return createdBy; }
	 *
	 * @Override public void setCreatedBy(String createdBy) { this.createdBy =
	 * createdBy; }
	 *
	 * @Override public Date getUpdatedDate() { return updatedDate; }
	 *
	 * @Override public void setUpdatedDate(Date updatedDate) { this.updatedDate =
	 * updatedDate; }
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

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	@Override
	public String toString()
	{
		return "AcademicSemester [semesterId=" + semesterId + ", semesterStartDate=" + semesterStartDate
				+ ", semesterEndDate=" + semesterEndDate + ", semesterStatus=" + semesterStatus + "]";
	}

}
