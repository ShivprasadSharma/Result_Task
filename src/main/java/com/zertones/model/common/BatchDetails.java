package com.zertones.model.common;

import java.io.Serializable;

public class BatchDetails implements Serializable
{

	private int		studId;
	private String	batch;

	public int getStudId()
	{
		return studId;
	}

	public void setStudId(int studId)
	{
		this.studId = studId;
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
		return "BatchDetails [studId=" + studId + ", batch=" + batch + "]";
	}

}
