package com.zertones.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "COMPANY_REPRESENTATIVE")
public class CompanyRepresentative extends BaseModel implements Serializable
{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int				id;

	@Column(name = "CR_FNAME")
	private String			fname;

	@Column(name = "CR_LNAME")
	private String			lname;

	@Column(name = "EMAIL")
	private String			email;

	@Column(name = "MOBILE_NO")
	private String			mobno;

	@Column(name = "DESIGNATION")
	private String			designation;

	@ManyToOne
	@JoinColumn(name = "REINFO_ID", nullable = false)
	private RecruitmentInfo	recruitmentInfo;

	public String getFname()
	{
		return fname;
	}

	public void setFname(String fname)
	{
		this.fname = fname;
	}

	public String getLname()
	{
		return lname;
	}

	public void setLname(String lname)
	{
		this.lname = lname;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getMobno()
	{
		return mobno;
	}

	public void setMobno(String mobno)
	{
		this.mobno = mobno;
	}

	public String getDesignation()
	{
		return designation;
	}

	public void setDesignation(String designation)
	{
		this.designation = designation;
	}

	public RecruitmentInfo getRecruitmentInfo()
	{
		return recruitmentInfo;
	}

	public void setRecruitmentInfo(RecruitmentInfo recruitmentInfo)
	{
		this.recruitmentInfo = recruitmentInfo;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "CompanyRepresentative [id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email
				+ ", mobno=" + mobno + ", designation=" + designation + ", recruitmentInfo=" + recruitmentInfo + "]";
	}

}
