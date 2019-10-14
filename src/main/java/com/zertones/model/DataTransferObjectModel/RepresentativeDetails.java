package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;

public class RepresentativeDetails implements Serializable
{

	private String	firstName;
	private String	lastName;
	private String	mobNo;
	private String	email;
	private String	designation;

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

	public String getMobNo()
	{
		return mobNo;
	}

	public void setMobNo(String mobNo)
	{
		this.mobNo = mobNo;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getDesignation()
	{
		return designation;
	}

	public void setDesignation(String designation)
	{
		this.designation = designation;
	}

	@Override
	public String toString()
	{
		return "RepresentativeDetails [firstName=" + firstName + ", lastName=" + lastName + ", mobNo=" + mobNo
				+ ", email=" + email + ", designation=" + designation + "]";
	}

}
