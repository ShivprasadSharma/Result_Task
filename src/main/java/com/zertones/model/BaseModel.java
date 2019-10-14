package com.zertones.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zertones.dao.AuditListener;
import com.zertones.system.service.Auditable;

@MappedSuperclass
@EntityListeners(AuditListener.class)
@JsonInclude(Include.NON_NULL)
public class BaseModel implements Auditable
{
	@Column(name = "CREATED_DT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date	createdDate	= new Date();

	@Column(name = "CREATED_BY")
	private String	createdBy;

	@Column(name = "UPDATED_DT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date	updatedDate;

	@Column(name = "UPDATED_BY")
	private String	updatedBy;

	@Column(name = "RECORD_STATUS")
	private String	recordStatus;

	@Column(name = "INSTITUTE_ID")
	private String	instituteId;

	// @ManyToOne
	// @JoinColumn(name = "COM_USER_ID", nullable = false)
	// @JsonProperty("instituteProfile") // used to map parent element
	// @JsonBackReference
	// private InstituteProfile instituteProfile;

	@Override
	public Date getCreatedDate()
	{
		return createdDate;
	}

	@Override
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	@Override
	public String getCreatedBy()
	{
		return createdBy;
	}

	@Override
	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	@Override
	public Date getUpdatedDate()
	{
		return updatedDate;
	}

	@Override
	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}

	@Override
	public String getUpdatedBy()
	{
		return updatedBy;
	}

	@Override
	public void setUpdatedBy(String updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	@Override
	public String getRecordStatus()
	{
		return recordStatus;
	}

	@Override
	public void setRecordStatus(String recordStatus)
	{
		this.recordStatus = recordStatus;
	}

	public String getInstituteId()
	{
		return instituteId;
	}

	public void setInstituteId(String instituteId)
	{
		this.instituteId = instituteId;
	}

}
