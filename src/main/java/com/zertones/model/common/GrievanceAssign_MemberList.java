package com.zertones.model.common;

import java.io.Serializable;

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
@Table(name = "GRIEVANCE_ASSIGN_MEMBER_LIST")
public class GrievanceAssign_MemberList extends BaseModel implements Serializable
{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer			id;

	@Column(name = "GRIEVANCE_ID")
	private Integer			grievanceId;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID", nullable = false)
	private ComClientName	comClientName;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getGrievanceId()
	{
		return grievanceId;
	}

	public void setGrievanceId(Integer grievanceId)
	{
		this.grievanceId = grievanceId;
	}

	public ComClientName getComClientName()
	{
		return comClientName;
	}

	public void setComClientName(ComClientName comClientName)
	{
		this.comClientName = comClientName;
	}

	@Override
	public String toString()
	{
		return "GrievanceAssign_MemberList [id=" + id + ", grievanceId=" + grievanceId + ", comClientName="
				+ comClientName + "]";
	}

}
