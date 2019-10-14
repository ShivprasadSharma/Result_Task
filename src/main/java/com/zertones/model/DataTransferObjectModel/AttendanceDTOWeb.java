package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;
import java.util.ArrayList;

public class AttendanceDTOWeb implements Serializable
{

	private String					department;
	private String					class_name;
	private String					at_Date;
	private String					division;
	private String					day;
	private String					semister;
	private Integer					acadmic_year;
	private String					batch;

	ArrayList<String>				lectureList	= new ArrayList();
	ArrayList<String>				subList		= new ArrayList();
	ArrayList<AttendanceStudentDTO>	studentList	= new ArrayList();

	public String getBatch()
	{
		return batch;
	}

	public void setBatch(String batch)
	{
		this.batch = batch;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public String getClass_name()
	{
		return class_name;
	}

	public void setClass_name(String class_name)
	{
		this.class_name = class_name;
	}

	public String getAt_Date()
	{
		return at_Date;
	}

	public void setAt_Date(String at_Date)
	{
		this.at_Date = at_Date;
	}

	public String getDivision()
	{
		return division;
	}

	public void setDivision(String division)
	{
		this.division = division;
	}

	public String getDay()
	{
		return day;
	}

	public void setDay(String day)
	{
		this.day = day;
	}

	public String getSemister()
	{
		return semister;
	}

	public void setSemister(String semister)
	{
		this.semister = semister;
	}

	public Integer getAcadmic_year()
	{
		return acadmic_year;
	}

	public void setAcadmic_year(Integer acadmic_year)
	{
		this.acadmic_year = acadmic_year;
	}

	public ArrayList<String> getLectureList()
	{
		return lectureList;
	}

	public void setLectureList(ArrayList<String> lectureList)
	{
		this.lectureList = lectureList;
	}

	public ArrayList<String> getSubList()
	{
		return subList;
	}

	public void setSubList(ArrayList<String> subList)
	{
		this.subList = subList;
	}

	public ArrayList<AttendanceStudentDTO> getStudentList()
	{
		return studentList;
	}

	public void setStudentList(ArrayList<AttendanceStudentDTO> studentList)
	{
		this.studentList = studentList;
	}

	@Override
	public String toString()
	{
		return "AttendanceDTOWeb [department=" + department + ", class_name=" + class_name + ", at_Date=" + at_Date
				+ ", division=" + division + ", day=" + day + ", semister=" + semister + ", acadmic_year="
				+ acadmic_year + ", lectureList=" + lectureList + ", subList=" + subList + ", studentList="
				+ studentList + "]";
	}

}
