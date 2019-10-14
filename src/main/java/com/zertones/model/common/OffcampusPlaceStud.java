package com.zertones.model.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "OFFCAMPUS_PLACED_STUDENT")
public class OffcampusPlaceStud extends BaseModel implements Serializable
{

	@Id
	@Column(name = "OFFCAMPUSID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	offcampusplce;

	@Column(name = "STUD_NAME")
	private String	studname;

	@Column(name = "COMPANY_NAME")
	private String	cmpnyname;

	@Column(name = "PLACE_DATE")
	private Date	placdate;

	@Column(name = "SALARY_OFFERING")
	private String	salary;

	@Column(name = "TO_DATE_STRING")
	private String	toDateString;

	public Integer getOffcampusplce()
	{
		return offcampusplce;
	}

	public void setOffcampusplce(Integer offcampusplce)
	{
		this.offcampusplce = offcampusplce;
	}

	public String getStudname()
	{
		return studname;
	}

	public void setStudname(String studname)
	{
		this.studname = studname;
	}

	public String getCmpnyname()
	{
		return cmpnyname;
	}

	public void setCmpnyname(String cmpnyname)
	{
		this.cmpnyname = cmpnyname;
	}

	public Date getPlacdate()
	{
		return placdate;
	}

	public void setPlacdate(Date placdate)
	{
		this.placdate = placdate;
	}

	public String getSalary()
	{
		return salary;
	}

	public void setSalary(String salary)
	{
		this.salary = salary;
	}

	public String getToDateString()
	{
		return toDateString;
	}

	public void setToDateString(String toDateString)
	{
		this.toDateString = toDateString;
	}

	@Override
	public String toString()
	{
		return "OffcampusPlaceStud [offcampusplce=" + offcampusplce + ", studname=" + studname + ", cmpnyname="
				+ cmpnyname + ", placdate=" + placdate + ", salary=" + salary + ", toDateString=" + toDateString + "]";
	}

}
