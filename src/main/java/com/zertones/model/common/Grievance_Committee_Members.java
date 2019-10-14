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
@Table(name = "GRIEVANCE_COMMITTEE_MEMBERS")
public class Grievance_Committee_Members extends BaseModel implements Serializable
{

	@Id
	@Column(name = "GCM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int				id;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID", nullable = false)
	private ComClientName	comClientName;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
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
		return "Grievance_Committee_Members [id=" + id + ", comClientName=" + comClientName + "]";
	}

}
