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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "MENTOR")
public class Mentor extends BaseModel implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer				id;

	@Column(name = "sem")
	private Integer				sem;

	@Column(name = "M_GROUP_NAME")
	private String				mgroupname;

	@Column(name = "ACDEMIC_YR")
	private Integer				ac_yr;

	@ManyToOne
	@JoinColumn(name = "STAFF_ID")
	private Staff				staff;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mentor", cascade = CascadeType.ALL)
	private Set<MentorStudent>	mentorStudent	= new HashSet<>();

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getSem()
	{
		return sem;
	}

	public void setSem(Integer sem)
	{
		this.sem = sem;
	}

	public String getMgroupname()
	{
		return mgroupname;
	}

	public void setMgroupname(String mgroupname)
	{
		this.mgroupname = mgroupname;
	}

	public Integer getAc_yr()
	{
		return ac_yr;
	}

	public void setAc_yr(Integer ac_yr)
	{
		this.ac_yr = ac_yr;
	}

	public Staff getStaff()
	{
		return staff;
	}

	public void setStaff(Staff staff)
	{
		this.staff = staff;
	}

	public Set<MentorStudent> getMentorStudent()
	{
		return mentorStudent;
	}

	public void setMentorStudent(Set<MentorStudent> mentorStudent)
	{
		this.mentorStudent = mentorStudent;
	}

	@Override
	public String toString()
	{
		return "Mentor [id=" + id + ", sem=" + sem + ", mgroupname=" + mgroupname + ", ac_yr=" + ac_yr + ", staff="
				+ staff + ", mentorStudent=" + mentorStudent + "]";
	}

}
