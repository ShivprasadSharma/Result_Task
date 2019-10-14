package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;

public class AttendanceBatch implements Serializable
{

	private int		subjectId;
	private String	div;
	private int		id;
	private String	batch;

	public int getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(int subjectId)
	{
		this.subjectId = subjectId;
	}

	public String getDiv()
	{
		return div;
	}

	public void setDiv(String div)
	{
		this.div = div;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getBatch()
	{
		return batch;
	}

	public void setBatch(String batch)
	{
		this.batch = batch;
	}

	@Override
	public String toString()
	{
		return "AttendanceBatch [subjectId=" + subjectId + ", div=" + div + ", id=" + id + ", batch=" + batch + "]";
	}

}
