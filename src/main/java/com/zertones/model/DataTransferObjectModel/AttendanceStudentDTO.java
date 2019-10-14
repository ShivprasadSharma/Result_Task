package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;
import java.util.ArrayList;

public class AttendanceStudentDTO implements Serializable
{

	private Integer		id;

	private String		roll_No;

	private String		firstName;

	private String		lastName;

	private String		middleName;

	ArrayList<String>	studAttendance	= new ArrayList<>();

	public String getRoll_No()
	{
		return roll_No;
	}

	public void setRoll_No(String roll_No)
	{
		this.roll_No = roll_No;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	public ArrayList<String> getStudAttendance()
	{
		return studAttendance;
	}

	public void setStudAttendance(ArrayList<String> studAttendance)
	{
		this.studAttendance = studAttendance;
	}

	@Override
	public String toString()
	{
		return "AttendanceStudentDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", middleName=" + middleName + ", studAttendance=" + studAttendance + "]";
	}

}
