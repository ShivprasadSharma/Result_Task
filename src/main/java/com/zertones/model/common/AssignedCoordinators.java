package com.zertones.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zertones.model.BaseModel;
import com.zertones.model.ComClientName;

@Entity
@Table(name = "COM_ASSINED_COORDINATOR")
public class AssignedCoordinators extends BaseModel implements Serializable
{

	@Id
	@Column(name = "CO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer			co_id;

	@Column(name = "CO_TYPE")
	private Integer			co_type;

	@Column(name = "DEPT_ID")
	private Integer			dep_id;

	@Column(name = "FOR_YR")
	private Integer			forYr;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID", nullable = false)
	private ComClientName	comClientName;

	public Integer getCo_id()
	{
		return co_id;
	}

	public void setCo_id(Integer co_id)
	{
		this.co_id = co_id;
	}

	public Integer getCo_type()
	{
		return co_type;
	}

	public void setCo_type(Integer co_type)
	{
		this.co_type = co_type;
	}

	public Integer getDep_id()
	{
		return dep_id;
	}

	public void setDep_id(Integer dep_id)
	{
		this.dep_id = dep_id;
	}

	public ComClientName getComClientName()
	{
		return comClientName;
	}

	public void setComClientName(ComClientName comClientName)
	{
		this.comClientName = comClientName;
	}

	public Integer getForYr()
	{
		return forYr;
	}

	public void setForYr(Integer forYr)
	{
		this.forYr = forYr;
	}

	@Override
	public String toString()
	{
		return "AssignedCoordinators [co_id=" + co_id + ", co_type=" + co_type + ", dep_id=" + dep_id + ", forYr="
				+ forYr + ", comClientName=" + comClientName + "]";
	}

}
