package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class ExtraActivityDTO implements Serializable
{

	private String	firstName;

	private String	lastName;

	private String	middleName;

	private String	emailId;

	private String	dateOfEvent;

	private String	toDate;

	private String	organizer;

	private String	coordinateName;

	private String	className;

	private String	dept;

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

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public String getDateOfEvent()
	{
		return dateOfEvent;
	}

	public void setDateOfEvent(String dateOfEvent)
	{
		this.dateOfEvent = dateOfEvent;
	}

	public String getToDate()
	{
		return toDate;
	}

	public void setToDate(String toDate)
	{
		this.toDate = toDate;
	}

	public String getOrganizer()
	{
		return organizer;
	}

	public void setOrganizer(String organizer)
	{
		this.organizer = organizer;
	}

	public String getCoordinateName()
	{
		return coordinateName;
	}

	public void setCoordinateName(String coordinateName)
	{
		this.coordinateName = coordinateName;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public String getDept()
	{
		return dept;
	}

	public void setDept(String dept)
	{
		this.dept = dept;
	}

}
