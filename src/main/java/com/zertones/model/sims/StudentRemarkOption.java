package com.zertones.model.sims;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "STUDENT_REMARK_OPTION")
public class StudentRemarkOption extends BaseModel implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "remarkoptid")
	private Integer			remarkoptid;

	@Column(name = "REMARKSTYPE")
	private String			skilltype;

	@Column(name = "REMARKS")
	private String			remarkname;

	@Column(name = "STUDENTID")
	private Integer			studentid;

	@ManyToOne
	private StudentRemark	remarks;

	@Column(name = "STATUS")
	private Boolean			Status;

	public Integer getStudentid()
	{
		return studentid;
	}

	public void setStudentid(Integer studentid)
	{
		this.studentid = studentid;
	}

	public Integer getRemarkoptid()
	{
		return remarkoptid;
	}

	public void setRemarkoptid(Integer remarkoptid)
	{
		this.remarkoptid = remarkoptid;
	}

	public String getSkilltype()
	{
		return skilltype;
	}

	public void setSkilltype(String skilltype)
	{
		this.skilltype = skilltype;
	}

	public String getRemarkname()
	{
		return remarkname;
	}

	public void setRemarkname(String remarkname)
	{
		this.remarkname = remarkname;
	}

	public StudentRemark getRemarks()
	{
		return remarks;
	}

	public void setRemarks(StudentRemark remarks)
	{
		this.remarks = remarks;
	}

	public Boolean getStatus()
	{
		return Status;
	}

	public void setStatus(Boolean status)
	{
		Status = status;
	}

	@Override
	public String toString()
	{
		return "StudentRemarkOption [remarkoptid=" + remarkoptid + ", skilltype=" + skilltype + ", remarkname="
				+ remarkname + ", studentid=" + studentid + ", remarks=" + remarks + ", Status=" + Status + "]";
	}

}
