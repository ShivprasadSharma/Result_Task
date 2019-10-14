package com.zertones.model;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.codec.Base64;

public class StudentForm implements Serializable
{
	// comClientbean

	private String	firstName;
	private String	lastName;
	private String	middleName;
	private Double	weight;
	private Double	height;
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private String	dateOfBirth;
	private Integer	gender;
	private String	contactNos;
	private Integer	isHandicaped;
	private String	emailId;
	private String	city;
	private String	address1;
	private String	state;
	private String	country;
	private String	postalCode;
	private Integer	studentId;
	private String	registrationNo;
	private String	rollNo;
	private String	universityEnrollNo;
	private Integer	branch;
	private Integer	year;
	private Integer	grade;
	private Integer	standard;
	private byte[]	profileImage;
	private String	motherName;
	private String	contactNo2;
	private String	courseType;
	private String	hscinstituteName;
	private String	hscboard;
	private String	hscaggregate;
	private String	sscschoolName;
	private String	sscboard;
	private String	sscaggregate;
	private String	parentContactNo1;
	private String	parentContactNo2;
	private String	parentEmail;
	private String	profesion;
	private String	relation;
	private Integer	client_id;
	private String	designation;
	private String	employeeNo;
	private String	batch;

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public Double getWeight()
	{
		return weight;
	}

	public Double getHeight()
	{
		return height;
	}

	public String getDateOfBirth()
	{
		return dateOfBirth;
	}

	public Integer getGender()
	{
		return gender;
	}

	public String getContactNos()
	{
		return contactNos;
	}

	public Integer getIsHandicaped()
	{
		return isHandicaped;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public String getCity()
	{
		return city;
	}

	public String getAddress1()
	{
		return address1;
	}

	public String getState()
	{
		return state;
	}

	public String getCountry()
	{
		return country;
	}

	public String getPostalCode()
	{
		return postalCode;
	}

	public Integer getStudentId()
	{
		return studentId;
	}

	public String getRegistrationNo()
	{
		return registrationNo;
	}

	public String getRollNo()
	{
		return rollNo;
	}

	public String getUniversityEnrollNo()
	{
		return universityEnrollNo;
	}

	public Integer getBranch()
	{
		return branch;
	}

	public Integer getYear()
	{
		return year;
	}

	public Integer getGrade()
	{
		return grade;
	}

	public Integer getStandard()
	{
		return standard;
	}

	public byte[] getProfileImage()
	{
		return profileImage;
	}

	public String getMotherName()
	{
		return motherName;
	}

	public String getContactNo2()
	{
		return contactNo2;
	}

	public String getCourseType()
	{
		return courseType;
	}

	public String getHscinstituteName()
	{
		return hscinstituteName;
	}

	public String getHscboard()
	{
		return hscboard;
	}

	public String getHscaggregate()
	{
		return hscaggregate;
	}

	public String getSscschoolName()
	{
		return sscschoolName;
	}

	public String getSscboard()
	{
		return sscboard;
	}

	public String getSscaggregate()
	{
		return sscaggregate;
	}

	public String getParentContactNo1()
	{
		return parentContactNo1;
	}

	public String getParentContactNo2()
	{
		return parentContactNo2;
	}

	public String getParentEmail()
	{
		return parentEmail;
	}

	public Integer getClient_id()
	{
		return client_id;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	public void setWeight(Double weight)
	{
		this.weight = weight;
	}

	public void setHeight(Double height)
	{
		this.height = height;
	}

	public void setDateOfBirth(String dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public void setGender(Integer gender)
	{
		this.gender = gender;
	}

	public void setContactNos(String contactNos)
	{
		this.contactNos = contactNos;
	}

	public void setIsHandicaped(Integer isHandicaped)
	{
		this.isHandicaped = isHandicaped;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public void setAddress1(String address1)
	{
		this.address1 = address1;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}

	public void setStudentId(Integer studentId)
	{
		this.studentId = studentId;
	}

	public void setRegistrationNo(String registrationNo)
	{
		this.registrationNo = registrationNo;
	}

	public void setRollNo(String rollNo)
	{
		this.rollNo = rollNo;
	}

	public void setUniversityEnrollNo(String universityEnrollNo)
	{
		this.universityEnrollNo = universityEnrollNo;
	}

	public void setBranch(Integer branch)
	{
		this.branch = branch;
	}

	public void setYear(Integer year)
	{
		this.year = year;
	}

	public void setGrade(Integer grade)
	{
		this.grade = grade;
	}

	public void setStandard(Integer standard)
	{
		this.standard = standard;
	}

	public void setProfileImage(byte[] profileImage)
	{
		this.profileImage = profileImage;
	}

	public void setMotherName(String motherName)
	{
		this.motherName = motherName;
	}

	public void setContactNo2(String contactNo2)
	{
		this.contactNo2 = contactNo2;
	}

	public void setCourseType(String courseType)
	{
		this.courseType = courseType;
	}

	public void setHscinstituteName(String hscinstituteName)
	{
		this.hscinstituteName = hscinstituteName;
	}

	public void setHscboard(String hscboard)
	{
		this.hscboard = hscboard;
	}

	public void setHscaggregate(String hscaggregate)
	{
		this.hscaggregate = hscaggregate;
	}

	public void setSscschoolName(String sscschoolName)
	{
		this.sscschoolName = sscschoolName;
	}

	public void setSscboard(String sscboard)
	{
		this.sscboard = sscboard;
	}

	public void setSscaggregate(String sscaggregate)
	{
		this.sscaggregate = sscaggregate;
	}

	public void setParentContactNo1(String parentContactNo1)
	{
		this.parentContactNo1 = parentContactNo1;
	}

	public void setParentContactNo2(String parentContactNo2)
	{
		this.parentContactNo2 = parentContactNo2;
	}

	public void setParentEmail(String parentEmail)
	{
		this.parentEmail = parentEmail;
	}

	public void setClient_id(Integer client_id)
	{
		this.client_id = client_id;
	}

	public String getDesignation()
	{
		return designation;
	}

	public void setDesignation(String designation)
	{
		this.designation = designation;
	}

	public String getEmployeeNo()
	{
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo)
	{
		this.employeeNo = employeeNo;
	}

	public String getBatch()
	{
		return batch;
	}

	public void setBatch(String batch)
	{
		this.batch = batch;
	}

	public String getProfesion()
	{
		return profesion;
	}

	public void setProfesion(String profesion)
	{
		this.profesion = profesion;
	}

	public String getRelation()
	{
		return relation;
	}

	public void setRelation(String relation)
	{
		this.relation = relation;
	}

	@Override
	public String toString()
	{
		return "StudentForm [firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName
				+ ", weight=" + weight + ", height=" + height + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender
				+ ", contactNos=" + contactNos + ", isHandicaped=" + isHandicaped + ", emailId=" + emailId + ", city="
				+ city + ", address1=" + address1 + ", state=" + state + ", country=" + country + ", postalCode="
				+ postalCode + ", studentId=" + studentId + ", registrationNo=" + registrationNo + ", rollNo=" + rollNo
				+ ", universityEnrollNo=" + universityEnrollNo + ", branch=" + branch + ", year=" + year + ", grade="
				+ grade + ", standard=" + standard + ", profileImage=" + Arrays.toString(profileImage) + ", motherName="
				+ motherName + ", contactNo2=" + contactNo2 + ", courseType=" + courseType + ", hscinstituteName="
				+ hscinstituteName + ", hscboard=" + hscboard + ", hscaggregate=" + hscaggregate + ", sscschoolName="
				+ sscschoolName + ", sscboard=" + sscboard + ", sscaggregate=" + sscaggregate + ", parentContactNo1="
				+ parentContactNo1 + ", parentContactNo2=" + parentContactNo2 + ", parentEmail=" + parentEmail
				+ ", profesion=" + profesion + ", relation=" + relation + ", client_id=" + client_id + ", designation="
				+ designation + ", employeeNo=" + employeeNo + ", batch=" + batch + "]";
	}

	public String getByteArrayString()
	{
		if (this.profileImage != null)
		{
			return new String(Base64.encode(this.profileImage));
		} else
		{
			return "";
		}
	}

}