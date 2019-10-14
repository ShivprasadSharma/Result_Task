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
@Table(name = "COM_FEEDBACKS")
public class FeedbackVote extends BaseModel implements Serializable
{

	@Id
	@Column(name = "FEEDBACK_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer			f_id;

	@Column(name = "SUB_ID")
	private Integer			sub_id;

	@Column(name = "SUB_NAME")
	private String			subject_name;

	@Column(name = "DEPT_ID")
	private int				dep_id;

	@Column(name = "DEPT_NAME")
	private String			dep_name;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID", nullable = false)
	private ComClientName	comClientName;

	@Column(name = "SEMESTER")
	private String			semister;

	public Integer getF_id()
	{
		return f_id;
	}

	public void setF_id(Integer f_id)
	{
		this.f_id = f_id;
	}

	public Integer getSub_id()
	{
		return sub_id;
	}

	public void setSub_id(Integer sub_id)
	{
		this.sub_id = sub_id;
	}

	public String getSubject_name()
	{
		return subject_name;
	}

	public void setSubject_name(String subject_name)
	{
		this.subject_name = subject_name;
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

	public ComClientName getComClientName()
	{
		return comClientName;
	}

	public void setComClientName(ComClientName comClientName)
	{
		this.comClientName = comClientName;
	}

	public String getSemister()
	{
		return semister;
	}

	public void setSemister(String semister)
	{
		this.semister = semister;
	}

	@Override
	public String toString()
	{
		return "FeedbackVote [f_id=" + f_id + ", sub_id=" + sub_id + ", subject_name=" + subject_name + ", dep_id="
				+ dep_id + ", dep_name=" + dep_name + ", comClientName=" + comClientName + ", semister=" + semister
				+ "]";
	}

}
