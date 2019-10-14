package com.zertones.model.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.zertones.model.BaseModel;
import com.zertones.model.DataTransferObjectModel.TimeTableDTO;

@Entity
@Table(name = "CALENDAR_DAYS")
public class Days extends BaseModel implements Serializable
{

	@Id
	@Column(name = "DAY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				day_vid;

	@Column(name = "DAY_NAME")
	private String				day_name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "days", cascade = CascadeType.ALL)
	private List<TimeTable>		timeTable		= new ArrayList<>();

	@Transient
	private List<TimeTableDTO>	timeTableDTO	= new ArrayList<>();

	public List<TimeTableDTO> getTimeTableDTO()
	{
		return timeTableDTO;
	}

	public void setTimeTableDTO(List<TimeTableDTO> timeTableDTO)
	{
		this.timeTableDTO = timeTableDTO;
	}

	public Integer getDay_vid()
	{
		return day_vid;
	}

	public void setDay_vid(Integer day_vid)
	{
		this.day_vid = day_vid;
	}

	public String getDay_name()
	{
		return day_name;
	}

	public void setDay_name(String day_name)
	{
		this.day_name = day_name;
	}

	public List<TimeTable> getTimeTable()
	{
		return timeTable;
	}

	public void setTimeTable(List<TimeTable> timeTable)
	{
		this.timeTable = timeTable;
	}

	@Override
	public String toString()
	{
		return "Days [day_vid=" + day_vid + ", day_name=" + day_name + ", timeTableDTO=" + timeTableDTO + "]";
	}

}
