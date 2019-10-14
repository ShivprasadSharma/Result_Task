package com.zertones.model.sims;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "REMARK_OPTION")
public class RemarkOption extends BaseModel implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "remarkoptid")
	private Integer		remarkoptid;

	@Column(name = "SKILLSTYPE")
	private String		skilltype;

	@Column(name = "SKILLSID")
	private Integer		skillsid;

	@Column(name = "STUDENTID")
	private Integer		studentid;

	@Column(name = "SKILLSTYPESLIST")
	private String[]	skillstypeslist;

	@Column(name = "SKILLTYPEID")
	private String		skillstypeid1;

	private String		mid;

	@ManyToOne
	private Remarks		remarks;

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

	public Integer getSkillsid()
	{
		return skillsid;
	}

	public void setSkillsid(Integer skillsid)
	{
		this.skillsid = skillsid;
	}

	public Integer getStudentid()
	{
		return studentid;
	}

	public void setStudentid(Integer studentid)
	{
		this.studentid = studentid;
	}

	public String[] getSkillstypeslist()
	{
		return skillstypeslist;
	}

	public void setSkillstypeslist(String[] skillstypeslist)
	{
		this.skillstypeslist = skillstypeslist;
	}

	public String getSkillstypeid1()
	{
		return skillstypeid1;
	}

	public void setSkillstypeid1(String skillstypeid1)
	{
		this.skillstypeid1 = skillstypeid1;
	}

	public String getMid()
	{
		return mid;
	}

	public void setMid(String mid)
	{
		this.mid = mid;
	}

	public Remarks getRemarks()
	{
		return remarks;
	}

	public void setRemarks(Remarks remarks)
	{
		this.remarks = remarks;
	}

	@Override
	public String toString()
	{
		return "RemarkOption [remarkoptid=" + remarkoptid + ", skilltype=" + skilltype + ", skillsid=" + skillsid
				+ ", studentid=" + studentid + ", skillstypeslist=" + Arrays.toString(skillstypeslist)
				+ ", skillstypeid1=" + skillstypeid1 + ", mid=" + mid + ", remarks=" + remarks + "]";
	}

}
