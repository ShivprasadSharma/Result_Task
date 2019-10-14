package com.zertones.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "BATCH_TABLE")
public class BatchTable extends BaseModel implements Serializable
{

	@Id
	@Column(name = "BATCH_TABLE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	batchId;

	@Column(name = "BATCH_ID")
	private String	batch_name;

	@Column(name = "DEPID")
	private int		depId;

	@Column(name = "CLASSID")
	private int		classId;

	@Column(name = "DIVISION")
	private String	division;

	public Integer getBatchId()
	{
		return batchId;
	}

	public void setBatchId(Integer batchId)
	{
		this.batchId = batchId;
	}

	public String getBatch_name()
	{
		return batch_name;
	}

	public void setBatch_name(String batch_name)
	{
		this.batch_name = batch_name;
	}

	public int getDepId()
	{
		return depId;
	}

	public void setDepId(int depId)
	{
		this.depId = depId;
	}

	public int getClassId()
	{
		return classId;
	}

	public void setClassId(int classId)
	{
		this.classId = classId;
	}

	public String getDivision()
	{
		return division;
	}

	public void setDivision(String division)
	{
		this.division = division;
	}

	@Override
	public String toString()
	{
		return "BatchTable [batchId=" + batchId + ", batch_name=" + batch_name + ", depId=" + depId + ", classId="
				+ classId + ", division=" + division + "]";
	}

}
