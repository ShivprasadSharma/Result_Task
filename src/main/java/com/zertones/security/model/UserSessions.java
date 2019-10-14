package com.zertones.security.model;

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

import com.zertones.model.ComUserDetails;

@Entity
@Table(name = "USER_SESSIONS")
public class UserSessions
{
	@Id
	@Column(name = "USER_SESSIONS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer			notificationId;

	@Column(name = "SESSION_KEY")
	private String			sessionKey;

	@ManyToOne
	@JoinColumn(name = "COM_USER_ID", nullable = false)
	private ComUserDetails	comUserDetails;

	@Column(name = "CREATED_DT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date			createdDate		= new Date();

	@Column(name = "CREATED_BY")
	private String			createdBy;

	@Column(name = "UPDATED_DT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date			updatedDate;

	@Column(name = "UPDATED_BY")
	private String			updatedBy;

	@Column(name = "RECORD_STATUS")
	private String			recordStatus	= "A";

	public Integer getNotificationId()
	{
		return notificationId;
	}

	public void setNotificationId(Integer notificationId)
	{
		this.notificationId = notificationId;
	}

	public String getSessionKey()
	{
		return sessionKey;
	}

	public void setSessionKey(String sessionKey)
	{
		this.sessionKey = sessionKey;
	}

	public ComUserDetails getComUserDetails()
	{
		return comUserDetails;
	}

	public void setComUserDetails(ComUserDetails comUserDetails)
	{
		this.comUserDetails = comUserDetails;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public String getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate()
	{
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy()
	{
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	public String getRecordStatus()
	{
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus)
	{
		this.recordStatus = recordStatus;
	}

}
