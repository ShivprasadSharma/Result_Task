package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;

public class DepartmentDTO implements Serializable
{

	private Integer	dep_id;
	private String	dep_name;

	public Integer getDep_id()
	{
		return dep_id;
	}

	public void setDep_id(Integer dep_id)
	{
		this.dep_id = dep_id;
	}

	public String getDep_name()
	{
		return dep_name;
	}

	public void setDep_name(String dep_name)
	{
		this.dep_name = dep_name;
	}

	@Override
	public String toString()
	{
		return "DepartmentDTO [dep_id=" + dep_id + ", dep_name=" + dep_name + "]";
	}

}
