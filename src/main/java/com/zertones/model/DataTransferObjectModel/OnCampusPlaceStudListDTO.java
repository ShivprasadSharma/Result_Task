package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;

public class OnCampusPlaceStudListDTO implements Serializable
{

	private String	companyName;
	private String	jobtitle;
	private String	salary;
	private String	firstName;
	private String	lastName;
	private String	middleName;

	public String getCompanyName()
	{
		return companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	public String getJobtitle()
	{
		return jobtitle;
	}

	public void setJobtitle(String jobtitle)
	{
		this.jobtitle = jobtitle;
	}

	public String getSalary()
	{
		return salary;
	}

	public void setSalary(String salary)
	{
		this.salary = salary;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	@Override
	public String toString()
	{
		return "OnCampusPlaceStudListDTO [companyName=" + companyName + ", jobtitle=" + jobtitle + ", salary=" + salary
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName + "]";
	}

}
