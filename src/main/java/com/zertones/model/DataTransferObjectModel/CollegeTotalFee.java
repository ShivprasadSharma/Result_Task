package com.zertones.model.DataTransferObjectModel;

public class CollegeTotalFee
{
	private long	collegestudentfee;
	private long	collgestudentppiadfee;

	public long getCollegestudentfee()
	{
		return collegestudentfee;
	}

	public void setCollegestudentfee(long collegestudentfee)
	{
		this.collegestudentfee = collegestudentfee;
	}

	public long getCollgestudentppiadfee()
	{
		return collgestudentppiadfee;
	}

	public void setCollgestudentppiadfee(long collgestudentppiadfee)
	{
		this.collgestudentppiadfee = collgestudentppiadfee;
	}

	@Override
	public String toString()
	{
		return "CollegeTotalFee [collegestudentfee=" + collegestudentfee + ", collgestudentppiadfee="
				+ collgestudentppiadfee + "]";
	}

}
