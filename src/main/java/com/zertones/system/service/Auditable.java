package com.zertones.system.service;

import java.util.Date;

public interface Auditable
{
	public Date getCreatedDate();

	public void setCreatedDate(Date createdDate);

	public String getCreatedBy();

	public void setCreatedBy(String createdBy);

	public Date getUpdatedDate();

	public void setUpdatedDate(Date updatedDate);

	public String getUpdatedBy();

	public void setUpdatedBy(String updatedBy);

	public String getRecordStatus();

	public void setRecordStatus(String recordStatus);

}
