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
@Table(name = "GRIEVANCE_LIST")
public class Grievance_List extends BaseModel implements Serializable
{

	@Id
	@Column(name = "GRIEVANCE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	id;

	@Column(name = "TYPE")
	private String	type;

	@Column(name = "TITLE")
	private String	title;

	@Column(name = "DESCRIPTION", length = 100000)
	private String	description;

	@Column(name = "GRIEVANCE_DATE")
	private String	grievance_Date;

	@Column(name = "GRIEVANCE_STATUS")
	private Boolean	grievance_Status;

	@Column(name = "PROCESS_STATUS")
	private Integer	process_Status;

	@Column(name = "ASSIGN_TO_MEMBERS")
	private Boolean	assignToMembers;

	@Column(name = "Replay", length = 100000)
	private String	replay;

	@Column(name = "REPLAY_DATE")
	private String	replay_Date;

	@Column(name = "REOPEN")
	private Boolean	reOpen;

	@Column(name = "ONETIIME_REOPEN")
	private Boolean	oneTime_reOpen;

	@Column(name = "REOPEN_REPLAY", length = 100000)
	private String	reOpen_replay;

	@Column(name = "REOPEN_REPLAY_DATE")
	private String	reopen_replay_Date;

	@Column(name = "CLIENT_ID")
	private Integer	clientID;

	public String getReopen_replay_Date()
	{
		return reopen_replay_Date;
	}

	public void setReopen_replay_Date(String reopen_replay_Date)
	{
		this.reopen_replay_Date = reopen_replay_Date;
	}

	public Boolean getReOpen()
	{
		return reOpen;
	}

	public void setReOpen(Boolean reOpen)
	{
		this.reOpen = reOpen;
	}

	public Boolean getOneTime_reOpen()
	{
		return oneTime_reOpen;
	}

	public void setOneTime_reOpen(Boolean oneTime_reOpen)
	{
		this.oneTime_reOpen = oneTime_reOpen;
	}

	public String getReOpen_replay()
	{
		return reOpen_replay;
	}

	public void setReOpen_replay(String reOpen_replay)
	{
		this.reOpen_replay = reOpen_replay;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getReplay()
	{
		return replay;
	}

	public void setReplay(String replay)
	{
		this.replay = replay;
	}

	public Integer getId()
	{
		return id;
	}

	public String getGrievance_Date()
	{
		return grievance_Date;
	}

	public void setGrievance_Date(String grievance_Date)
	{
		this.grievance_Date = grievance_Date;
	}

	public String getReplay_Date()
	{
		return replay_Date;
	}

	public void setReplay_Date(String replay_Date)
	{
		this.replay_Date = replay_Date;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Boolean getGrievance_Status()
	{
		return grievance_Status;
	}

	public void setGrievance_Status(Boolean grievance_Status)
	{
		this.grievance_Status = grievance_Status;
	}

	public Integer getProcess_Status()
	{
		return process_Status;
	}

	public void setProcess_Status(Integer process_Status)
	{
		this.process_Status = process_Status;
	}

	public Integer getClientID()
	{
		return clientID;
	}

	public void setClientID(Integer clientID)
	{
		this.clientID = clientID;
	}

	public Boolean getAssignToMembers()
	{
		return assignToMembers;
	}

	public void setAssignToMembers(Boolean assignToMembers)
	{
		this.assignToMembers = assignToMembers;
	}

	@Override
	public String toString()
	{
		return "Grievance_List [id=" + id + ", type=" + type + ", title=" + title + ", description=" + description
				+ ", grievance_Date=" + grievance_Date + ", grievance_Status=" + grievance_Status + ", process_Status="
				+ process_Status + ", assignToMembers=" + assignToMembers + ", replay=" + replay + ", replay_Date="
				+ replay_Date + ", reOpen=" + reOpen + ", oneTime_reOpen=" + oneTime_reOpen + ", reOpen_replay="
				+ reOpen_replay + ", clientID=" + clientID + "]";
	}

}
