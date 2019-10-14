package com.zertones.model.sims;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "REMARK")
public class Remarks extends BaseModel implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "remarkid")
	private Integer				remarkid;

	@Column(name = "STUDENTID")
	private Integer				studentid;

	@Column(name = "MENTORID")
	private String				mid;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "remarks", cascade = CascadeType.ALL)
	private Set<RemarkOption>	remarkOptions	= new HashSet<>();

	public Integer getRemarkid()
	{
		return remarkid;
	}

	public void setRemarkid(Integer remarkid)
	{
		this.remarkid = remarkid;
	}

	public Integer getStudentid()
	{
		return studentid;
	}

	public void setStudentid(Integer studentid)
	{
		this.studentid = studentid;
	}

	public String getMid()
	{
		return mid;
	}

	public void setMid(String mid)
	{
		this.mid = mid;
	}

	@Override
	public String toString()
	{
		return "Remarks [remarkid=" + remarkid + ", studentid=" + studentid + ", mid=" + mid + ", mentorStudent=" + "]";
	}

}
