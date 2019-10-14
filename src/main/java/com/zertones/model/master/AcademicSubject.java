package com.zertones.model.master;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zertones.model.common.Department;

@Entity
@Table(name = "COURSE_SUBJECT")
public class AcademicSubject
{

	@Id
	@Column(name = "SUB_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer					sub_id;

	@Column(name = "SUBJECT_CODE")
	private String					subject_code;

	@Column(name = "SUBJECT_NAME")
	private String					subject_name;

	@Column(name = "SUBJECT_STATUS")
	private String					subject_status;

	@Column(name = "SEMISTER")
	private String					semister;

	@Column(name = "SEMISTER_YEAR")
	private Integer					sem_year;

	@ManyToOne
	@JoinColumn(name = "DEP_ID", nullable = false)
	private Department				department;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "academicSubject", cascade = CascadeType.REFRESH)
	private Set<ComStaffSubject>	comStaffSubject	= new HashSet<>();

	@OneToMany(mappedBy = "subject")
	private Set<MarkSheet>			sheet;

	public String getSubject_name()
	{
		return subject_name;
	}

	public void setSubject_name(String subject_name)
	{
		this.subject_name = subject_name;
	}

	public Integer getSub_id()
	{
		return sub_id;
	}

	public void setSub_id(Integer sub_id)
	{
		this.sub_id = sub_id;
	}

	public String getSubject_code()
	{
		return subject_code;
	}

	public void setSubject_code(String subject_code)
	{
		this.subject_code = subject_code;
	}

	public String getSubject_status()
	{
		return subject_status;
	}

	public void setSubject_status(String subject_status)
	{
		this.subject_status = subject_status;
	}

	public String getSemister()
	{
		return semister;
	}

	public void setSemister(String semister)
	{
		this.semister = semister;
	}

	public Integer getSem_year()
	{
		return sem_year;
	}

	public void setSem_year(Integer sem_year)
	{
		this.sem_year = sem_year;
	}

	public Set<ComStaffSubject> getComStaffSubject()
	{
		return comStaffSubject;
	}

	public void setComStaffSubject(Set<ComStaffSubject> comStaffSubject)
	{
		this.comStaffSubject = comStaffSubject;
	}

	public Department getDepartment()
	{
		return department;
	}

	public void setDepartment(Department department)
	{
		this.department = department;
	}

}
