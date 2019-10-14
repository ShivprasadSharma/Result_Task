package com.zertones.model.DataTransferObjectModel;

public class GrievanceDTO
{

	private Integer	id;

	private String	type;
	private String	title;

	private String	description;

	private String	grievance_Date;

	private Boolean	grievance_Status;

	private Integer	process_Status;

	private Boolean	assignToMembers;

	private String	replay;

	private String	replay_Date;

	private Integer	clientID;

	private Boolean	reOpen;

	private Boolean	oneTime_reOpen;

	private String	reOpen_replay;

	private String	reopen_replay_Date;

	public String getReopen_replay_Date()
	{
		return reopen_replay_Date;
	}

	public void setReopen_replay_Date(String reopen_replay_Date)
	{
		this.reopen_replay_Date = reopen_replay_Date;
	}

	public String getReOpen_replay()
	{
		return reOpen_replay;
	}

	public void setReOpen_replay(String reOpen_replay)
	{
		this.reOpen_replay = reOpen_replay;
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

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
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

	public String getGrievance_Date()
	{
		return grievance_Date;
	}

	public void setGrievance_Date(String grievance_Date)
	{
		this.grievance_Date = grievance_Date;
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

	public Boolean getAssignToMembers()
	{
		return assignToMembers;
	}

	public void setAssignToMembers(Boolean assignToMembers)
	{
		this.assignToMembers = assignToMembers;
	}

	public String getReplay()
	{
		return replay;
	}

	public void setReplay(String replay)
	{
		this.replay = replay;
	}

	public String getReplay_Date()
	{
		return replay_Date;
	}

	public void setReplay_Date(String replay_Date)
	{
		this.replay_Date = replay_Date;
	}

	public Integer getClientID()
	{
		return clientID;
	}

	public void setClientID(Integer clientID)
	{
		this.clientID = clientID;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "GrievanceDTO [id=" + id + ", type=" + type + ", title=" + title + ", description=" + description
				+ ", grievance_Date=" + grievance_Date + ", grievance_Status=" + grievance_Status + ", process_Status="
				+ process_Status + ", assignToMembers=" + assignToMembers + ", replay=" + replay + ", replay_Date="
				+ replay_Date + ", clientID=" + clientID + ", reOpen=" + reOpen + ", oneTime_reOpen=" + oneTime_reOpen
				+ "]";
	}

}
