package com.zertones.model.DataTransferObjectModel;

public class NewUserDTO
{

	private String	firstName;

	private String	lastName;

	private String	middleName;

	private String	emailId;

	private Integer	gender;

	private String	contactNos;

	private Integer	deptid;

	private Integer	instituteid;

	private String	designations;

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

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public Integer getGender()
	{
		return gender;
	}

	public void setGender(Integer gender)
	{
		this.gender = gender;
	}

	public String getContactNos()
	{
		return contactNos;
	}

	public void setContactNos(String contactNos)
	{
		this.contactNos = contactNos;
	}

	public Integer getDeptid()
	{
		return deptid;
	}

	public void setDeptid(Integer deptid)
	{
		this.deptid = deptid;
	}

	public Integer getInstituteid()
	{
		return instituteid;
	}

	public void setInstituteid(Integer instituteid)
	{
		this.instituteid = instituteid;
	}

	public String getDesignations()
	{
		return designations;
	}

	public void setDesignations(String designations)
	{
		this.designations = designations;
	}

	@Override
	public String toString()
	{
		return "NewUserDTO [firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName
				+ ", emailId=" + emailId + ", gender=" + gender + ", contactNos=" + contactNos + ", deptid=" + deptid
				+ ", instituteid=" + instituteid + ", designations=" + designations + "]";
	}

}
