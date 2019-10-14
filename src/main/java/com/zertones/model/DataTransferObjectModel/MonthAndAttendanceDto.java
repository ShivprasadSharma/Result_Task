package com.zertones.model.DataTransferObjectModel;

public class MonthAndAttendanceDto
{

	private Integer	id;

	private String	monthName;

	private double	attendance;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getMonthName()
	{
		return monthName;
	}

	public void setMonthName(String monthName)
	{
		this.monthName = monthName;
	}

	public double getAttendance()
	{
		return attendance;
	}

	public void setAttendance(double attendance)
	{
		this.attendance = attendance;
	}

	@Override
	public String toString()
	{
		return "MonthAndAttendanceDto [id=" + id + ", monthName=" + monthName + ", attendance=" + attendance + "]";
	}

}
