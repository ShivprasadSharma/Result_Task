package com.zertones.model.common;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "PLACEMENT")
public class Placement extends BaseModel implements Serializable
{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int							id;

	@Column(name = "NO_OF_STUDENT")
	private int							noOfStudentPlace;

	@Column(name = "palcement_year")
	private int							yr;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYER_ID")
	private EmployerNameForPlacement	employeer;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DEP_ID")
	private Department					branch;

	@Column(name = "PACKAGE")
	private float						pkg;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getYr()
	{
		return yr;
	}

	public Department getBranch()
	{
		return branch;
	}

	public void setBranch(Department branch)
	{
		this.branch = branch;
	}

	public void setYr(int yr)
	{
		this.yr = yr;
	}

	public float getPkg()
	{
		return pkg;
	}

	public void setPkg(float pkg)
	{
		this.pkg = pkg;
	}

	public int getNoOfStudentPlace()
	{
		return noOfStudentPlace;
	}

	public void setNoOfStudentPlace(int noOfStudentPlace)
	{
		this.noOfStudentPlace = noOfStudentPlace;
	}

	public EmployerNameForPlacement getEmployeer()
	{
		return employeer;
	}

	public void setEmployeer(EmployerNameForPlacement employeer)
	{
		this.employeer = employeer;
	}

	@Override
	public String toString()
	{
		return "Placement [id=" + id + ", noOfStudentPlace=" + noOfStudentPlace + ", yr=" + yr + ", employeer="
				+ employeer + ", branch=" + branch + ", pkg=" + pkg + "]";
	}

}
