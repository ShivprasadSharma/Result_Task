package com.zertones.model.DataTransferObjectModel;

public class CollegeCountDTO
{
	private long	totalcount;
	private long	presentcount;

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

	@Override
	public String toString()
	{
		return "CollegeCountDTO [totalcount=" + totalcount + ", presentcount=" + presentcount + "]";
	}

}
