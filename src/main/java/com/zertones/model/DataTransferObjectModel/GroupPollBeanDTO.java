package com.zertones.model.DataTransferObjectModel;

import java.util.List;

public class GroupPollBeanDTO
{

	private Integer	id;

	private long	clientId;

	private String	question;

	private Integer	g_id;

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

	public Integer getG_id()
	{
		return g_id;
	}

	public void setG_id(Integer g_id)
	{
		this.g_id = g_id;
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
		return "GroupPollBeanDTO [id=" + id + ", clientId=" + clientId + ", question=" + question + ", g_id=" + g_id
				+ ", toDateString=" + toDateString + ", options=" + options + "]";
	}

}
