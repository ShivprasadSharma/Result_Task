package com.zertones.model.DataTransferObjectModel;

public class PortFolioInfo
{

	private Integer	co_id;

	private String	eventName;

	private String	incharge_type;

	public Integer getCo_id()
	{
		return co_id;
	}

	public void setCo_id(Integer co_id)
	{
		this.co_id = co_id;
	}

	public String getEventName()
	{
		return eventName;
	}

	public void setEventName(String eventName)
	{
		this.eventName = eventName;
	}

	public String getIncharge_type()
	{
		return incharge_type;
	}

	public void setIncharge_type(String incharge_type)
	{
		this.incharge_type = incharge_type;
	}

	@Override
	public String toString()
	{
		return "PortFolioInfo [co_id=" + co_id + ", eventName=" + eventName + ", incharge_type=" + incharge_type + "]";
	}

}
