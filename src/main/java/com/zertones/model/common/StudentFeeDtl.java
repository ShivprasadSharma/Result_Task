package com.zertones.model.common;

public class StudentFeeDtl
{
	private int		dept_id;
	private String	dept_name;
	private long	total_fee;
	private long	paid_fee;
	private String	div;

	public int getDept_id()
	{
		return dept_id;
	}

	public void setDept_id(int dept_id)
	{
		this.dept_id = dept_id;
	}

	public String getDept_name()
	{
		return dept_name;
	}

	public void setDept_name(String dept_name)
	{
		this.dept_name = dept_name;
	}

	public long getTotal_fee()
	{
		return total_fee;
	}

	public void setTotal_fee(long total_fee)
	{
		this.total_fee = total_fee;
	}

	public long getPaid_fee()
	{
		return paid_fee;
	}

	public void setPaid_fee(long paid_fee)
	{
		this.paid_fee = paid_fee;
	}

	public String getDiv()
	{
		return div;
	}

	public void setDiv(String div)
	{
		this.div = div;
	}

	@Override
	public String toString()
	{
		return "StudentFeeDtl [dept_id=" + dept_id + ", dept_name=" + dept_name + ", total_fee=" + total_fee
				+ ", paid_fee=" + paid_fee + ", div=" + div + "]";
	}

}
