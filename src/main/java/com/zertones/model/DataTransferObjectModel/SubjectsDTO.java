package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class SubjectsDTO implements Serializable
{

	private Integer	sub_id;
	private String	sub_name;
	private String	div_name;
	private String	date;
	private int		periodsTime;
	private int		id;
	private String	batch;
	private int		clientId;

	List<String>	str	= new ArrayList();

	public Integer getSub_id()
	{
		return sub_id;
	}

	public void setSub_id(Integer sub_id)
	{
		this.sub_id = sub_id;
	}

	public String getSub_name()
	{
		return sub_name;
	}

	public void setSub_name(String sub_name)
	{
		this.sub_name = sub_name;
	}

	public String getDiv_name()
	{
		return div_name;
	}

	public void setDiv_name(String div_name)
	{
		this.div_name = div_name;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getBatch()
	{
		return batch;
	}

	public void setBatch(String batch)
	{
		this.batch = batch;
	}

	public int getClientId()
	{
		return clientId;
	}

	public void setClientId(int clientId)
	{
		this.clientId = clientId;
	}

	public List<String> getStr()
	{
		return str;
	}

	public void setStr(List<String> str)
	{
		this.str = str;
	}

	public int getPeriodsTime()
	{
		return periodsTime;
	}

	public void setPeriodsTime(int periodsTime)
	{
		this.periodsTime = periodsTime;
	}

}
