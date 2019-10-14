package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;

public class TpoApplayCriteria implements Serializable
{
	private String	standard;
	private String	companyCriteria;
	private double	mark;
	private boolean	status;

	public String getStandard()
	{
		return standard;
	}

	public void setStandard(String standard)
	{
		this.standard = standard;
	}

	public String getCompanyCriteria()
	{
		return companyCriteria;
	}

	public void setCompanyCriteria(String companyCriteria)
	{
		this.companyCriteria = companyCriteria;
	}

	public double getMark()
	{
		return mark;
	}

	public void setMark(double mark)
	{
		this.mark = mark;
	}

	public boolean isStatus()
	{
		return status;
	}

	public void setStatus(boolean status)
	{
		this.status = status;
	}

	@Override
	public String toString()
	{
		return "TpoApplayCriteria [standard=" + standard + ", companyCriteria=" + companyCriteria + ", mark=" + mark
				+ ", status=" + status + "]";
	}

}
