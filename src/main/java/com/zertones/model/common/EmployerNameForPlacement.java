package com.zertones.model.common;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "EMPLOYER")
public class EmployerNameForPlacement extends BaseModel implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYER_ID")
	private int				employerId;

	@Column(name = "EMPLOYER_NAME")
	private String			employerName;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employeer")
	private Set<Placement>	placement	= new HashSet<>();

	public int getEmployerId()
	{
		return employerId;
	}

	public void setEmployerId(int employerId)
	{
		this.employerId = employerId;
	}

	public String getEmployerName()
	{
		return employerName;
	}

	public void setEmployerName(String employerName)
	{
		this.employerName = employerName;
	}

	public void setPlacement(Set<Placement> placement)
	{
		this.placement = placement;
	}

	@Override
	public String toString()
	{
		return "EmployerNameForPlacement [employerId=" + employerId + ", employerName=" + employerName + "]";
	}

}
