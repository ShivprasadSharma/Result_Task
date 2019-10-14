package com.zertones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RESULT_SUBJEC_LIST")
public class ResulSubjectlist extends BaseModel
{
	@Id
	@Column(name = "SUBLIST_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	sub_id;

	@Column(name = "SUBJECT_NAME")
	private String	subjectname;

	@Column(name = "SUBJECT_CODE")
	private String	subjectcode;

	@Column(name = "DEPT_ID")
	private Integer	dep_id;

	@Column(name = "SEMISTER")
	private Integer	semester;

	public Integer getSub_id()
	{
		return sub_id;
	}

	public String getSubjectname()
	{
		return subjectname;
	}

	public String getSubjectcode()
	{
		return subjectcode;
	}

	public void setSub_id(Integer sub_id)
	{
		this.sub_id = sub_id;
	}

	public void setSubjectname(String subjectname)
	{
		this.subjectname = subjectname;
	}

	public void setSubjectcode(String subjectcode)
	{
		this.subjectcode = subjectcode;
	}

	public Integer getSemister()
	{
		return semester;
	}

	public void setSemister(Integer semister)
	{
		this.semester = semister;
	}

	public Integer getDep_id()
	{
		return dep_id;
	}

	public Integer getSemester()
	{
		return semester;
	}

	public void setDep_id(Integer dep_id)
	{
		this.dep_id = dep_id;
	}

	public void setSemester(Integer semester)
	{
		this.semester = semester;
	}

	@Override
	public String toString()
	{
		return "SubjectList [sub_id=" + sub_id + ", subjectname=" + subjectname + ", subjectcode=" + subjectcode
				+ ", dep_id=" + dep_id + ", semester=" + semester + "]";
	}

}
