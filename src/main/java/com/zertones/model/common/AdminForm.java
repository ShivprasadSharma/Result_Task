package com.zertones.model.common;

import java.io.Serializable;

import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class AdminForm implements Serializable
{
	private String	firstName;

	private String	middleName;

	private String	lastName;

	private String	emailId;

	private String	contactNos;

	private String	designation;

	private Integer	department;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String	dateofbirth;

	private Integer	gender;
	private Integer	standard;
	private Integer	year;
	private String	rollNo;

	public String getDateofbirth()
	{
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth)
	{
		this.dateofbirth = dateofbirth;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public String getContactNos()
	{
		return contactNos;
	}

	public void setContactNos(String contactNos)
	{
		this.contactNos = contactNos;
	}

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

	public Integer getGender()
	{
		return gender;
	}

	public void setGender(Integer gender)
	{
		this.gender = gender;
	}

	public Integer getStandard()
	{
		return standard;
	}

	public void setStandard(Integer standard)
	{
		this.standard = standard;
	}

	public String getRollNo()
	{
		return rollNo;
	}

	public void setRollNo(String rollNo)
	{
		this.rollNo = rollNo;
	}

	public Integer getYear()
	{
		return year;
	}

	public void setYear(Integer year)
	{
		this.year = year;
	}

	@Override
	public String toString()
	{
		return "AdminForm [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", emailId=" + emailId + ", contactNos=" + contactNos + ", designation=" + designation
				+ ", department=" + department + ", dateofbirth=" + dateofbirth + ", gender=" + gender + ", standard="
				+ standard + ", year=" + year + ", rollNo=" + rollNo + "]";
	}

}