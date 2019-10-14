package com.zertones.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zertones.model.BaseModel;
import com.zertones.model.ComClientName;

@Entity
@Table(name = "GROUP_MEMBERS")
public class GroupMembers extends BaseModel
{
	@Id
	@Column(name = "MEMBER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer			membserId;

	@ManyToOne
	@JoinColumn(name = "GROUP_ID", nullable = false)
	private Groups			groups;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private ComClientName	comClientName;

	public void setMembserId(Integer membserId)
	{
		this.membserId = membserId;
	}

	public void setGroups(Groups groups)
	{
		this.groups = groups;
	}

	public ComClientName getComClientName()
	{
		return comClientName;
	}

	public void setComClientName(ComClientName comClientName)
	{
		this.comClientName = comClientName;
	}

	public Integer getMembserId()
	{
		return membserId;
	}

	public Groups getGroups()
	{
		return groups;
	}

	@Override
	public String toString()
	{
		return "GroupMembers [membserId=" + membserId + ", groups=" + groups + ", comClientName=" + comClientName + "]";
	}

}
