package com.zertones.model.sims;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zertones.model.BaseModel;
import com.zertones.model.ComClientName;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "parentId")
@Table(name = "PARENT_DETAILS")
public class Parent extends BaseModel implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	@Id
	@Column(name = "PARENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				parentid;
	@Column(name = "PARENT_PROFESSION")
	private String				profesion;
	@Column(name = "PARENT_HASRELATION")
	private String				relation;

	@Column(name = "CONTACT_NO1")
	private String				contact_no1;

	@Column(name = "CONTACT_NO2")
	private String				contact_no2;

	@Column(name = "PARENT_EMAIL")
	private String				email;

	@Column(name = "STUD_ID")
	private Integer				Stud_id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "CLIENT_ID", nullable = false)
	private ComClientName		comClientName;

	public Integer getParentid()
	{
		return parentid;
	}

	public void setParentid(Integer parentid)
	{
		this.parentid = parentid;
	}

	public String getProfesion()
	{
		return profesion;
	}

	public void setProfesion(String profesion)
	{
		this.profesion = profesion;
	}

	public String getRelation()
	{
		return relation;
	}

	public void setRelation(String relation)
	{
		this.relation = relation;
	}

	public ComClientName getComClientName()
	{
		return comClientName;
	}

	public void setComClientName(ComClientName comClientName)
	{
		this.comClientName = comClientName;
	}

	public String getContact_no1()
	{
		return contact_no1;
	}

	public void setContact_no1(String contact_no1)
	{
		this.contact_no1 = contact_no1;
	}

	public String getContact_no2()
	{
		return contact_no2;
	}

	public void setContact_no2(String contact_no2)
	{
		this.contact_no2 = contact_no2;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Integer getStud_id()
	{
		return Stud_id;
	}

	public void setStud_id(Integer integer)
	{
		Stud_id = integer;
	}

	@Override
	public String toString()
	{
		return "Parent [parentid=" + parentid + ", profesion=" + profesion + ", relation=" + relation + ", contact_no1="
				+ contact_no1 + ", contact_no2=" + contact_no2 + ", email=" + email + ", Stud_id=" + Stud_id
				+ ", comClientName=" + comClientName + "]";
	}

}
