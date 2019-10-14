package com.zertones.model.common;

import java.io.Serializable;
import java.util.List;

public class FeedbackData implements Serializable
{

	private Integer				sub_id;
	private String				subject_name;
	private long				stud_cid;
	private int					dep_id;
	private String				dep_name;
	private String				staff_firstName;
	private String				staff_lastName;
	private Integer				staffclient_id;
	private String				semister;
	private List<FeedbackQue>	list;

	public Integer getSub_id()
	{
		return sub_id;
	}

	public void setSub_id(Integer sub_id)
	{
		this.sub_id = sub_id;
	}

	public String getSubject_name()
	{
		return subject_name;
	}

	public void setSubject_name(String subject_name)
	{
		this.subject_name = subject_name;
	}

	public int getDep_id()
	{
		return dep_id;
	}

	public void setDep_id(int dep_id)
	{
		this.dep_id = dep_id;
	}

	public String getDep_name()
	{
		return dep_name;
	}

	public void setDep_name(String dep_name)
	{
		this.dep_name = dep_name;
	}

	public String getStaff_firstName()
	{
		return staff_firstName;
	}

	public void setStaff_firstName(String staff_firstName)
	{
		this.staff_firstName = staff_firstName;
	}

	public String getStaff_lastName()
	{
		return staff_lastName;
	}

	public void setStaff_lastName(String staff_lastName)
	{
		this.staff_lastName = staff_lastName;
	}

	public Integer getStaffclient_id()
	{
		return staffclient_id;
	}

	public void setStaffclient_id(Integer staffclient_id)
	{
		this.staffclient_id = staffclient_id;
	}

	public List<FeedbackQue> getList()
	{
		return list;
	}

	public void setList(List<FeedbackQue> list)
	{
		this.list = list;
	}

	public long getStud_cid()
	{
		return stud_cid;
	}

	public void setStud_cid(Integer stud_cid)
	{
		this.stud_cid = stud_cid;
	}

	public String getSemister()
	{
		return semister;
	}

	public void setSemister(String semister)
	{
		this.semister = semister;
	}

	@Override
	public String toString()
	{
		return "FeedbackData [sub_id=" + sub_id + ", subject_name=" + subject_name + ", stud_cid=" + stud_cid
				+ ", dep_id=" + dep_id + ", dep_name=" + dep_name + ", staff_firstName=" + staff_firstName
				+ ", staff_lastName=" + staff_lastName + ", staffclient_id=" + staffclient_id + ", semister=" + semister
				+ ", list=" + list + "]";
	}

}
