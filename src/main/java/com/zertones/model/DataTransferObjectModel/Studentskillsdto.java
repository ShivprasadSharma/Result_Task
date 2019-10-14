package com.zertones.model.DataTransferObjectModel;

public class Studentskillsdto
{

	private Integer	studentid;
	private Integer	mid;

	private String	techFields;

	public String getTechFields()
	{
		return techFields;
	}

	public void setTechFields(String techFields)
	{
		this.techFields = techFields;
	}

	public Integer getStudentid()
	{
		return studentid;
	}

	public void setStudentid(Integer studentid)
	{
		this.studentid = studentid;
	}

	public Integer getMid()
	{
		return mid;
	}

	public void setMid(Integer mid)
	{
		this.mid = mid;
	}

	@Override
	public String toString()
	{
		return "Studentskillsdto [studentid=" + studentid + ", mid=" + mid + ", techFields=" + techFields + "]";
	}

}
