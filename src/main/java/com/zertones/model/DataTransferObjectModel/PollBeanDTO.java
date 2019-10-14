package com.zertones.model.DataTransferObjectModel;

import java.util.List;

public class PollBeanDTO
{

	private Integer	id;

	private long	clientId;

	private String	question;

	private Integer	depID;

	private Integer	year;

	private String	toDateString;

	List<String>	options;

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

	public String getToDateString()
	{
		return toDateString;
	}

	public void setToDateString(String toDateString)
	{
		this.toDateString = toDateString;
	}

	public List<String> getOptions()
	{
		return options;
	}

	public void setOptions(List<String> options)
	{
		this.options = options;
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
		return "PollBeanDTO [id=" + id + ", question=" + question + ", depID=" + depID + ", year=" + year
				+ ", toDateString=" + toDateString + ", options=" + options + "]";
	}

}
