package com.zertones.model.DataTransferObjectModel;

import java.util.List;

public class StudentAttendanceDto
{

	private Integer						id;

	private String						subName;

	private List<MonthAndAttendanceDto>	list;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getSubName()
	{
		return subName;
	}

	public void setSubName(String subName)
	{
		this.subName = subName;
	}

	public List<MonthAndAttendanceDto> getList()
	{
		return list;
	}

	public void setList(List<MonthAndAttendanceDto> list)
	{
		this.list = list;
	}

	@Override
	public String toString()
	{
		return "StudentAttendanceDto [id=" + id + ", subName=" + subName + ", list=" + list + "]";
	}

}
