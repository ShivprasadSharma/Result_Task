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
@Table(name = "POLL_OPTIONS")
public class PollOptions extends BaseModel implements Serializable
{

	@Id
	@Column(name = "OPTION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	option_Id;

	@Column(name = "OPTIONs")
	private String	option;

	@ManyToOne
	@JoinColumn(name = "POLL_ID", nullable = false)
	private Polls	polls;

	public Integer getOption_Id()
	{
		return option_Id;
	}

	public void setOption_Id(Integer option_Id)
	{
		this.option_Id = option_Id;
	}

	public String getOption()
	{
		return option;
	}

	public void setOption(String option)
	{
		this.option = option;
	}

	public Polls getPolls()
	{
		return polls;
	}

	public void setPolls(Polls polls)
	{
		this.polls = polls;
	}

	@Override
	public String toString()
	{
		return "PollOptions [option_Id=" + option_Id + ", option=" + option + ", polls=" + polls + "]";
	}

}
