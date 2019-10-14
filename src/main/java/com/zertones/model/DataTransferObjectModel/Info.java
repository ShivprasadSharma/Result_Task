package com.zertones.model.DataTransferObjectModel;

public class Info
{

	private Integer	dep;

	private String	deptName;

	private String	year;

	private String	divs;

	private String	rollNo;

	private String	clas;

	private String	designation;

	public String getDesignation()
	{
		return designation;
	}

	public void setDesignation(String designation)
	{
		this.designation = designation;
	}

	public Integer getDep()
	{
		return dep;
	}

	public void setDep(Integer dep)
	{
		this.dep = dep;
	}

	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}

	public String getYear()
	{
		return year;
	}

	public void setYear(String year)
	{
		this.year = year;
	}

	public String getDivs()
	{
		return divs;
	}

	public void setDivs(String divs)
	{
		this.divs = divs;
	}

	public String getRollNo()
	{
		return rollNo;
	}

	public void setRollNo(String rollNo)
	{
		this.rollNo = rollNo;
	}

	public String getClas()
	{
		return clas;
	}

	public void setClas(String clas)
	{
		this.clas = clas;
	}

	@Override
	public String toString()
	{
		return "Info [dep=" + dep + ", deptName=" + deptName + ", year=" + year + ", divs=" + divs + ", rollNo="
				+ rollNo + ", clas=" + clas + ", designation=" + designation + "]";
	}

}
