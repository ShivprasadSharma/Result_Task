package com.zertones.model.master;

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
@Table(name = "VALUE_ADDED_PROGRAM")
public class ValueAddedProgram extends BaseModel
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer			vap_id;

	@Column(name = "CLASS_OR_SEM")
	private String			class_or_sem;

	@Column(name = "NAME_COURCE")
	private String			name_cource;

	@Column(name = "DURATION_FROM")
	private String			duration_from;

	@Column(name = "DURATION_TO")
	private String			duration_to;

	@Column(name = "REMARK")
	private String			remark;

	@Column(name = "VPA_OR_INTERNSHIP")
	private Integer			vpa_or_intranship;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID", nullable = false)
	private ComClientName	comClientName;

	public Integer getVap_id()
	{
		return vap_id;
	}

	public void setVap_id(Integer vap_id)
	{
		this.vap_id = vap_id;
	}

	public String getClass_or_sem()
	{
		return class_or_sem;
	}

	public void setClass_or_sem(String class_or_sem)
	{
		this.class_or_sem = class_or_sem;
	}

	public String getName_cource()
	{
		return name_cource;
	}

	public void setName_cource(String name_cource)
	{
		this.name_cource = name_cource;
	}

	public String getDuration_from()
	{
		return duration_from;
	}

	public void setDuration_from(String duration_from)
	{
		this.duration_from = duration_from;
	}

	public String getDuration_to()
	{
		return duration_to;
	}

	public void setDuration_to(String duration_to)
	{
		this.duration_to = duration_to;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public ComClientName getComClientName()
	{
		return comClientName;
	}

	public void setComClientName(ComClientName comClientName)
	{
		this.comClientName = comClientName;
	}

	public Integer getVpa_or_intranship()
	{
		return vpa_or_intranship;
	}

	public void setVpa_or_intranship(Integer vpa_or_intranship)
	{
		this.vpa_or_intranship = vpa_or_intranship;
	}

	@Override
	public String toString()
	{
		return "ValueAddedProgram [vap_id=" + vap_id + ", class_or_sem=" + class_or_sem + ", name_cource=" + name_cource
				+ ", duration_from=" + duration_from + ", duration_to=" + duration_to + ", remark=" + remark
				+ ", vpa_or_intranship=" + vpa_or_intranship + ", comClientName=" + comClientName + "]";
	}

}
