package com.zertones.model.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "PUNCH")
@Component
public class AttendancePunch extends BaseModel implements Serializable
{

	@Id
	@Column(name = "PID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	pid;

	@Column(name = "YEAR")
	private int		year;

	@Column(name = "DIVISION")
	private String	div;

	@Column(name = "DEP")
	private int		dept;

	@Column(name = "TDATE")
	private Date	tdate;

	@Column(name = "STUDID")
	private int		studId;

	@Column(name = "CHECK_IN")
	private boolean	checkIn;

	@Column(name = "CHECK_OUT")
	private boolean	checkOut;

	public Integer getPid()
	{
		return pid;
	}

	public void setPid(Integer pid)
	{
		this.pid = pid;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public String getDiv()
	{
		return div;
	}

	public void setDiv(String div)
	{
		this.div = div;
	}

	public Date getTdate()
	{
		return tdate;
	}

	public void setTdate(Date tdate)
	{
		this.tdate = tdate;
	}

	public int getStudId()
	{
		return studId;
	}

	public void setStudId(int studId)
	{
		this.studId = studId;
	}

	public boolean isCheckIn()
	{
		return checkIn;
	}

	public void setCheckIn(boolean checkIn)
	{
		this.checkIn = checkIn;
	}

	public boolean isCheckOut()
	{
		return checkOut;
	}

	public void setCheckOut(boolean checkOut)
	{
		this.checkOut = checkOut;
	}

	public int getDept()
	{
		return dept;
	}

	public void setDept(int dept)
	{
		this.dept = dept;
	}

	@Override
	public String toString()
	{
		return "AttendancePunch [pid=" + pid + ", year=" + year + ", div=" + div + ", dept=" + dept + ", tdate=" + tdate
				+ ", studId=" + studId + ", checkIn=" + checkIn + ", checkOut=" + checkOut + "]";
	}

}
