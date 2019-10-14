package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class ComClientNameDTO implements Serializable
{

	private Integer	membserId;

	private Integer	id;

	private String	firstName;

	private String	lastName;

	private String	middleName;

	private String	emailId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date	dateOfBirth;

	private Integer	gender;

	private String	contactNos;

	private byte[]	profileImage;

	private String	imgUrl;

	private Integer	depId;

	private String	depName;

	private Integer	year_id;

	private String	year;

	private String	rollNo;

	private int		rollNoInt;

	public int getRollNoInt()
	{
		return rollNoInt;
	}

	public void setRollNoInt(int rollNoInt)
	{
		this.rollNoInt = rollNoInt;
	}

	public String getRollNo()
	{
		return rollNo;
	}

	public void setRollNo(String rollNo)
	{
		this.rollNo = rollNo;
	}

	public Integer getYear_id()
	{
		return year_id;
	}

	public void setYear_id(Integer year_id)
	{
		this.year_id = year_id;
	}

	public String getYear()
	{
		return year;
	}

	public void setYear(String year)
	{
		this.year = year;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getDepName()
	{
		return depName;
	}

	public Integer getDepId()
	{
		return depId;
	}

	public void setDepId(Integer depId)
	{
		this.depId = depId;
	}

	public void setDepName(String depName)
	{
		this.depName = depName;
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

	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
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

	public byte[] getProfileImage()
	{
		return profileImage;
	}

	public void setProfileImage(byte[] profileImage)
	{
		this.profileImage = profileImage;
	}

	public String getImgUrl()
	{
		return imgUrl;
	}

	public void setImgUrl(String imgUrl)
	{
		this.imgUrl = imgUrl;
	}

	public Integer getMembserId()
	{
		return membserId;
	}

	public void setMembserId(Integer membserId)
	{
		this.membserId = membserId;
	}

	@Override
	public String toString()
	{
		return "ComClientNameDTO [membserId=" + membserId + ", id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", middleName=" + middleName + ", emailId=" + emailId + ", dateOfBirth=" + dateOfBirth
				+ ", gender=" + gender + ", contactNos=" + contactNos + ", profileImage="
				+ Arrays.toString(profileImage) + ", imgUrl=" + imgUrl + ", depId=" + depId + ", depName=" + depName
				+ ", year_id=" + year_id + ", year=" + year + "]";
	}

}
