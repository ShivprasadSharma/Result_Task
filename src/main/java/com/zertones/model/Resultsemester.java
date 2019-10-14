package com.zertones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RESULT_SEMESTER")
public class Resultsemester
{

	@Id
	@Column(name = "SEMID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	semid;

	@Column(name = "SEMESTER")
	private Integer	semester;

	public Integer getSemid()
	{
		return semid;
	}

	public void setSemid(Integer semid)
	{
		this.semid = semid;
	}

	public Integer getSemester()
	{
		return semester;
	}

	public void setSemester(Integer semester)
	{
		this.semester = semester;
	}

	@Override
	public String toString()
	{
		return "ResultSemester [semid=" + semid + ", semester=" + semester + "]";
	}

}
