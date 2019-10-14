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
 * @author Abhijit
 * @Created Date : Oct 5, 2015
 */
@Entity
@Table(name = "COM_ACADEMIC_TRIMESTER_MST")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "trimesterId")

public class AcademicTrimester extends BaseModel implements java.io.Serializable
{
	private static final long serialVersionUID = 2227184483736622806L;

	@Id
	@Column(name = "TRIMESTER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer trimesterId;

	@Column(name = "TRIMESTER_START_DT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date trimesterStartDate = new Date();

	@Column(name = "TRIMESTER_END_DT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date trimesterEndDate = new Date();

	@Column(name = "TRIMESTER_STATUS")
	Integer trimesterStatus;

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

	public Integer getTrimesterId()
	{
		return trimesterId;
	}

	public void setTrimesterId(Integer trimesterId)
	{
		this.trimesterId = trimesterId;
	}

	public Date getTrimesterStartDate()
	{
		return trimesterStartDate;
	}

	public void setTrimesterStartDate(Date trimesterStartDate)
	{
		this.trimesterStartDate = trimesterStartDate;
	}

	public Date getTrimesterEndDate()
	{
		return trimesterEndDate;
	}

	public void setTrimesterEndDate(Date trimesterEndDate)
	{
		this.trimesterEndDate = trimesterEndDate;
	}

	public Integer getTrimesterStatus()
	{
		return trimesterStatus;
	}

	public void setTrimesterStatus(Integer trimesterStatus)
	{
		this.trimesterStatus = trimesterStatus;
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

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	@Override
	public String toString()
	{
		return "AcademicTrimester [trimesterId=" + trimesterId + ", trimesterStartDate=" + trimesterStartDate
				+ ", trimesterEndDate=" + trimesterEndDate + ", trimesterStatus=" + trimesterStatus + "]";
	}

}
