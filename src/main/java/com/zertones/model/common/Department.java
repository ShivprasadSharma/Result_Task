package com.zertones.model.common;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zertones.model.master.AcademicSubject;
import com.zertones.model.master.CourseMaster;

@Entity
@Table(name = "COM_DEPARTMENT")
public class Department
{

	@Id
	@Column(name = "DEP_ID")
	private int						dep_id;

	@Column(name = "DEP_NAME")
	private String					dep_name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = CascadeType.ALL)
	private Set<AcademicSubject>	academicSubject	= new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "COURSE_ID", nullable = false)
	@JsonProperty("courseMaster") // used to map parent element
	@JsonBackReference
	private CourseMaster			courseMaster;

	public Set<AcademicSubject> getAcademicSubject()
	{
		return academicSubject;
	}

	public void setAcademicSubject(Set<AcademicSubject> academicSubject)
	{
		this.academicSubject = academicSubject;
	}

	public void setCourseMaster(CourseMaster courseMaster)
	{
		this.courseMaster = courseMaster;
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

	@Override
	public String toString()
	{
		return " [dep_id=" + dep_id + ", dep_name=" + dep_name + ", academicSubject=" + academicSubject + "]";
	}

}
