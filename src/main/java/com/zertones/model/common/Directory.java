package com.zertones.model.common;

import java.io.Serializable;

public class Directory implements Serializable
{

	String	name;
	String	contact;
	String	designation;
	Integer	department;

	public String getDesignation()
	{
		return designation;
	}

	public void setDesignation(String designation)
	{
		this.designation = designation;
	}

	public Integer getDepartment()
	{
		return department;
	}

	public void setDepartment(Integer department)
	{
		this.department = department;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getContact()
	{
		return contact;
	}

	public void setContact(String contact)
	{
		this.contact = contact;
	}

	@Override
	public String toString()
	{
		return "Directory [name=" + name + ", contact=" + contact + ", designation=" + designation + ", department="
				+ department + "]";
	}

}
