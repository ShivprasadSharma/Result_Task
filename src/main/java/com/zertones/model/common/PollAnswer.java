package com.zertones.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "POLL_ANSWER")
public class PollAnswer extends BaseModel implements Serializable
{

	@Id
	@Column(name = "ANS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	id;

	@Column(name = "OPTION_ID")
	private Integer	optionID;

	@Column(name = "POLL_ID")
	private Integer	pollId;

	@Column(name = "CLIENT_ID")
	private Integer	clientID;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getOptionID()
	{
		return optionID;
	}

	public void setOptionID(Integer optionID)
	{
		this.optionID = optionID;
	}

	public Integer getPollId()
	{
		return pollId;
	}

	public void setPollId(Integer pollId)
	{
		this.pollId = pollId;
	}

	public Integer getClientID()
	{
		return clientID;
	}

	public void setClientID(Integer clientID)
	{
		this.clientID = clientID;
	}

}
