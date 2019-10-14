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
import com.zertones.model.ComClientName;

@Entity
@Table(name = "APPLY_STUDENT_FORCOMPANY")
public class ApplyedStudentForCompany extends BaseModel implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int				id;

	@ManyToOne
	@JoinColumn(name = "REINFO_ID", nullable = false)

	private RecruitmentInfo	recruitmentInfo;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID", nullable = false)
	private ComClientName	comClientName;

	@Column(name = "FINAl_FILTER")
	private boolean			finalfilter;

	@Column(name = "ROUND1")
	private boolean			Round1;

	@Column(name = "ROUND2")
	private boolean			Round2;

	@Column(name = "ROUND3")
	private boolean			Round3;

	@Column(name = "ROUND4")
	private boolean			Round4;

	@Column(name = "ROUND5")
	private boolean			Round5;

	@Column(name = "ROUND6")
	private boolean			Round6;

	@Column(name = "SELECTED_STUD")
	private boolean			selectstud;

	@Column(name = "OFFER_ACCEPTED")
	private boolean			offeraccepted;

	@Column(name = "OFFER_ACCEPTED_OR_NOT")
	private boolean			Accepted;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public boolean isFinalfilter()
	{
		return finalfilter;
	}

	public void setFinalfilter(boolean finalfilter)
	{
		this.finalfilter = finalfilter;
	}

	public boolean isRound1()
	{
		return Round1;
	}

	public void setRound1(boolean round1)
	{
		Round1 = round1;
	}

	public boolean isRound2()
	{
		return Round2;
	}

	public void setRound2(boolean round2)
	{
		Round2 = round2;
	}

	public boolean isRound3()
	{
		return Round3;
	}

	public void setRound3(boolean round3)
	{
		Round3 = round3;
	}

	public boolean isRound4()
	{
		return Round4;
	}

	public void setRound4(boolean round4)
	{
		Round4 = round4;
	}

	public boolean isRound5()
	{
		return Round5;
	}

	public void setRound5(boolean round5)
	{
		Round5 = round5;
	}

	public boolean isRound6()
	{
		return Round6;
	}

	public void setRound6(boolean round6)
	{
		Round6 = round6;
	}

	public boolean isSelectstud()
	{
		return selectstud;
	}

	public void setSelectstud(boolean selectstud)
	{
		this.selectstud = selectstud;
	}

	public boolean isOfferaccepted()
	{
		return offeraccepted;
	}

	public void setOfferaccepted(boolean offeraccepted)
	{
		this.offeraccepted = offeraccepted;
	}

	public ComClientName getComClientName()
	{
		return comClientName;
	}

	public void setComClientName(ComClientName comClientName)
	{
		this.comClientName = comClientName;
	}

	public RecruitmentInfo getRecruitmentInfo()
	{
		return recruitmentInfo;
	}

	public void setRecruitmentInfo(RecruitmentInfo recruitmentInfo)
	{
		this.recruitmentInfo = recruitmentInfo;
	}

	public boolean isAccepted()
	{
		return Accepted;
	}

	public void setAccepted(boolean accepted)
	{
		Accepted = accepted;
	}

	@Override
	public String toString()
	{
		return "ApplyedStudentForCompany [id=" + id + ", recruitmentInfo=" + recruitmentInfo + ", comClientName="
				+ comClientName + ", finalfilter=" + finalfilter + ", Round1=" + Round1 + ", Round2=" + Round2
				+ ", Round3=" + Round3 + ", Round4=" + Round4 + ", Round5=" + Round5 + ", Round6=" + Round6
				+ ", selectstud=" + selectstud + ", offeraccepted=" + offeraccepted + ", Accepted=" + Accepted + "]";
	}

}
