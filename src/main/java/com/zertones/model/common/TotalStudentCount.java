package com.zertones.model.common;

public class TotalStudentCount implements java.io.Serializable
{

	private String	div;
	private int		dept_id;
	private long	totalcount;
	private long	presentcount;
	private String	deptname;
	private long	totaldepartmentcount;
	private long	totalpresentcount;

	public int getDept_id()
	{
		return dept_id;
	}

	public void setDept_id(int dept_id)
	{
		this.dept_id = dept_id;
	}

	public String getDiv()
	{
		return div;
	}

	public String setDiv(String div)
	{
		return this.div = div;
	}

	public long getTotalcount()
	{
		return totalcount;
	}

	public void setTotalcount(long totalcount)
	{
		this.totalcount = totalcount;
	}

	public long getPresentcount()
	{
		return presentcount;
	}

	public void setPresentcount(long presentcount)
	{
		this.presentcount = presentcount;
	}

	public String getDeptname()
	{
		return deptname;
	}

	public void setDeptname(String deptname)
	{
		this.deptname = deptname;
	}

	public long getTotaldepartmentcount()
	{
		return totaldepartmentcount;
	}

	public void setTotaldepartmentcount(long totaldepartmentcount)
	{
		this.totaldepartmentcount = totaldepartmentcount;
	}

	public long getTotalpresentcount()
	{
		return totalpresentcount;
	}

	public void setTotalpresentcount(long totalpresentcount)
	{
		this.totalpresentcount = totalpresentcount;
	}

	@Override
	public String toString()
	{
		return "TotalStudentCount [div=" + div + ", dept_id=" + dept_id + ", totalcount=" + totalcount
				+ ", presentcount=" + presentcount + ", deptname=" + deptname + ", totaldepartmentcount="
				+ totaldepartmentcount + ", totalpresentcount=" + totalpresentcount + "]";
	}

}
