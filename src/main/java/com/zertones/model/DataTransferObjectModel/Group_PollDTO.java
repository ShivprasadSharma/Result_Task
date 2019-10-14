package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;

public class Group_PollDTO implements Serializable
{

	private Integer	id;

	private long	clientId;

	private String	question;

	private String	toDateString;

	private Boolean	pollStatus;

	private Boolean	votedornot;

	public Boolean getPollStatus()
	{
		return pollStatus;
	}

	public void setPollStatus(Boolean pollStatus)
	{
		this.pollStatus = pollStatus;
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

	public String getToDateString()
	{
		return toDateString;
	}

	public void setToDateString(String toDateString)
	{
		this.toDateString = toDateString;
	}

	public long getClientId()
	{
		return clientId;
	}

	public void setClientId(long clientId)
	{
		this.clientId = clientId;
	}

	public Boolean getVotedornot()
	{
		return votedornot;
	}

	public void setVotedornot(Boolean votedornot)
	{
		this.votedornot = votedornot;
	}

}
