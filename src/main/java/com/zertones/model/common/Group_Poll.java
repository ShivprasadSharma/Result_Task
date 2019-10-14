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
@Table(name = "GROUP_POLLS")
public class Group_Poll extends BaseModel implements Serializable
{
	@Id
	@Column(name = "G_POLL_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	id;

	@Column(name = "QUESTION")
	private String	question;

	@Column(name = "TO_DATE")
	private Date	to_Date;

	@Column(name = "TO_DATE_STRING")
	private String	toDateString;

	@Column(name = "CLIENT_ID")
	private long	clientId;

	@Column(name = "GROUP_ID")
	private Integer	g_id;

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

	public Integer getG_id()
	{
		return g_id;
	}

	public void setG_id(Integer g_id)
	{
		this.g_id = g_id;
	}

	@Override
	public String toString()
	{
		return "Group_Poll [id=" + id + ", question=" + question + ", to_Date=" + to_Date + ", toDateString="
				+ toDateString + ", clientId=" + clientId + ", g_id=" + g_id + "]";
	}

}
