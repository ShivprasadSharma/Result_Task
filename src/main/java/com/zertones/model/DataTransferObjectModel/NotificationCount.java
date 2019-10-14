package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;

public class NotificationCount implements Serializable
{

	private Integer	acad;
	private Integer	achive;
	private Integer	clg;
	private Integer	evet;
	private Integer	announ;

	public Integer getClg()
	{
		return clg;
	}

	public void setClg(Integer clg)
	{
		this.clg = clg;
	}

	public Integer getAchive()
	{
		return achive;
	}

	public Integer getAcad()
	{
		return acad;
	}

	public void setAcad(Integer acad)
	{
		this.acad = acad;
	}

	public void setAchive(Integer achive)
	{
		this.achive = achive;
	}

	public Integer getEvet()
	{
		return evet;
	}

	public void setEvet(Integer evet)
	{
		this.evet = evet;
	}

	public Integer getAnnoun()
	{
		return announ;
	}

	public void setAnnoun(Integer announ)
	{
		this.announ = announ;
	}

	@Override
	public String toString()
	{
		return "NotificationCount [acad=" + acad + ", achive=" + achive + ", clg=" + clg + ", evet=" + evet
				+ ", announ=" + announ + "]";
	}

}
