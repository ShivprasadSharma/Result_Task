package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;

public class DriveStudentRoundCount implements Serializable
{
	private Integer	totalapply;
	private Integer	ffilter;
	private Integer	round2;
	private Integer	round3;
	private Integer	round4;
	private Integer	round5;
	private Integer	round1;
	private Integer	round6;

	public Integer getFfilter()
	{
		return ffilter;
	}

	public void setFfilter(Integer ffilter)
	{
		this.ffilter = ffilter;
	}

	public Integer getRound2()
	{
		return round2;
	}

	public void setRound2(Integer round2)
	{
		this.round2 = round2;
	}

	public Integer getRound3()
	{
		return round3;
	}

	public void setRound3(Integer round3)
	{
		this.round3 = round3;
	}

	public Integer getRound4()
	{
		return round4;
	}

	public void setRound4(Integer round4)
	{
		this.round4 = round4;
	}

	public Integer getRound5()
	{
		return round5;
	}

	public void setRound5(Integer round5)
	{
		this.round5 = round5;
	}

	public Integer getRound1()
	{
		return round1;
	}

	public void setRound1(Integer round1)
	{
		this.round1 = round1;
	}

	public Integer getTotalapply()
	{
		return totalapply;
	}

	public void setTotalapply(Integer totalapply)
	{
		this.totalapply = totalapply;
	}

	public Integer getRound6()
	{
		return round6;
	}

	public void setRound6(Integer round6)
	{
		this.round6 = round6;
	}

	@Override
	public String toString()
	{
		return "DriveStudentRoundCount [totalapply=" + totalapply + ", ffilter=" + ffilter + ", round2=" + round2
				+ ", round3=" + round3 + ", round4=" + round4 + ", round5=" + round5 + ", round1=" + round1
				+ ", round6=" + round6 + "]";
	}

}
