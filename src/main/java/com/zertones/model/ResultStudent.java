package com.zertones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COM_STUDENT_RESULT")
public class ResultStudent extends BaseModel
{

	@Id
	@Column(name = "STUD_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	studId;

	@Column(name = "FIRST_NAME")
	private String	firstName;

	@Column(name = "MIDDLE_NAME")
	private String	middleName;

	@Column(name = "LAST_NAME")
	private String	lastName;

	@Column(name = "SEAT_NO")
	private String	seatno;

	@Column(name = "PRN_NO")
	private String	prnumber;

	@Column(name = "STANDARD", length = 5)
	private Integer	standard;

	@Column(name = "BRANCH", length = 10)
	private Integer	branch;

	@Column(name = "SEMISTER")
	private Integer	semister;

	@Column(name = "Exam_Year")
	private Integer	year;

	@Column(name = "COLLEGENAME")
	private String	studcollegename;

	public Integer getYear()
	{
		return year;
	}

	public void setYear(Integer year)
	{
		this.year = year;
	}

	@Column(name = "Aca_Year")
	private String academiyr;

	public Integer getSemister()
	{
		return semister;
	}

	public void setSemister(Integer semister)
	{
		this.semister = semister;
	}

	public String getAcademiyr()
	{
		return academiyr;
	}

	public void setAcademiyr(String academiyr)
	{
		this.academiyr = academiyr;
	}

	public Integer getStudId()
	{
		return studId;
	}

	public void setStudId(Integer studId)
	{
		this.studId = studId;
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

	public String getSeatno()
	{
		return seatno;
	}

	public void setSeatno(String seatno)
	{
		this.seatno = seatno;
	}

	public String getPrnumber()
	{
		return prnumber;
	}

	public void setPrnumber(String prnumber)
	{
		this.prnumber = prnumber;
	}

	public Integer getStandard()
	{
		return standard;
	}

	public void setStandard(Integer standard)
	{
		this.standard = standard;
	}

	public Integer getBranch()
	{
		return branch;
	}

	public void setBranch(Integer branch)
	{
		this.branch = branch;
	}

	public String getStudcollegename()
	{
		return studcollegename;
	}

	public void setStudcollegename(String studcollegename)
	{
		this.studcollegename = studcollegename;
	}

	@Override
	public String toString()
	{
		return "ResultStudent [studId=" + studId + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", seatno=" + seatno + ", prnumber=" + prnumber + ", standard=" + standard
				+ ", branch=" + branch + ", semister=" + semister + ", year=" + year + ", studcollegename="
				+ studcollegename + ", academiyr=" + academiyr + "]";
	}

}
