package com.zertones.model.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "POLLS")
public class Polls extends BaseModel implements Serializable
{
	@Id
	@Column(name = "POLL_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	id;

	@Column(name = "QUESTION")
	private String	question;

	@Column(name = "DEPARTMENT")
	private Integer	depID;

	@Column(name = "YEAR")
	private Integer	year;

	@Column(name = "TO_DATE")
	private Date	to_Date;

	@Column(name = "TO_DATE_STRING")
	private String	toDateString;

	@Column(name = "CLIENT_ID")
	private long	clientId;

	public String getToDateString()
	{
		return toDateString;
	}

	public void setToDateString(String toDateString)
	{
		this.toDateString = toDateString;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public Integer getDepID()
	{
		return depID;
	}

	public void setDepID(Integer depID)
	{
		this.depID = depID;
	}

	public Integer getYear()
	{
		return year;
	}

	public void setYear(Integer year)
	{
		this.year = year;
	}

	public Date getTo_Date()
	{
		return to_Date;
	}

	public void setTo_Date(Date to_Date)
	{
		this.to_Date = to_Date;
	}

	public long getClientId()
	{
		return clientId;
	}

	public void setClientId(long clientId)
	{
		this.clientId = clientId;
	}

	@Override
	public String toString()
	{
		return "Polls [id=" + id + ", question=" + question + ", depID=" + depID + ", year=" + year + ", to_Date="
				+ to_Date + "]";
	}

}
