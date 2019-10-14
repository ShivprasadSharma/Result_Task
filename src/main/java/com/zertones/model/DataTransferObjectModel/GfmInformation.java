package com.zertones.model.DataTransferObjectModel;

public class GfmInformation
{

	private Integer	staffid;

	private String	firstName;

	private String	lastName;

	private String	middleName;

	private Integer	sem;

	private Integer	yr;

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

	public Integer getSem()
	{
		return sem;
	}

	public void setSem(Integer sem)
	{
		this.sem = sem;
	}

	public Integer getYr()
	{
		return yr;
	}

	public void setYr(Integer yr)
	{
		this.yr = yr;
	}

	public Integer getStaffid()
	{
		return staffid;
	}

	public void setStaffid(Integer staffid)
	{
		this.staffid = staffid;
	}

	@Override
	public String toString()
	{
		return "GfmInformation [staffid=" + staffid + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", middleName=" + middleName + ", sem=" + sem + ", yr=" + yr + "]";
	}

}
