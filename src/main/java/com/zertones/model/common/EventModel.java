package com.zertones.model.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zertones.system.utility.JsonDateDeserializer;
import com.zertones.system.utility.JsonDateSerializer;

public class EventModel implements Serializable
{

	private String	title;
	private String	eventDtl;
	private String	venu;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date	start	= new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date	end;

	public Date getStart()
	{
		return start;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setStart(Date start)
	{
		this.start = start;
	}

	public Date getEnd()
	{
		return end;
	}

	public void setEnd(Date end)
	{
		this.end = end;
	}

	public String getEventDtl()
	{
		return eventDtl;
	}

	public void setEventDtl(String eventDtl)
	{
		this.eventDtl = eventDtl;
	}

	public String getVenu()
	{
		return venu;
	}

	public void setVenu(String venu)
	{
		this.venu = venu;
	}

	@Override
	public String toString()
	{
		return "[title=" + title + ",  start=" + start + ", end=" + end + ", eventDtl=" + eventDtl + ", venu=" + venu
				+ "]";
	}

}
