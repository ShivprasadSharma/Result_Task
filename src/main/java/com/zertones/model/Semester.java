package com.zertones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "SEMESTERS")
@JsonInclude(Include.NON_NULL)
public class Semester extends BaseModel
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEM_ID")
	private Integer	semid;

	@Column(name = "SEM_NAME")
	private String	sem_name;

	public Integer getSemid()
	{
		return semid;
	}

	public void setSemid(Integer semid)
	{
		this.semid = semid;
	}

	public String getSem_name()
	{
		return sem_name;
	}

	public void setSem_name(String sem_name)
	{
		this.sem_name = sem_name;
	}

	@Override
	public String toString()
	{
		return "Semester [semid=" + semid + ", sem_name=" + sem_name + "]";
	}

}
