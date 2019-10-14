package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;
import java.util.List;

import com.zertones.model.common.CompanySelectionRounds;

public class PlacementDriveStatus implements Serializable
{

	private List<CompanySelectionRounds>	roundList;
	private boolean							Round1;
	private boolean							Round2;
	private boolean							Round3;
	private boolean							Round4;
	private boolean							Round5;
	private boolean							Round6;
	private boolean							selectstud;
	private boolean							Accepted;

	/*
	 * private ApplyedStudentForCompany applyedStudentForCompany;
	 */
	public List<CompanySelectionRounds> getRoundList()
	{
		return roundList;
	}

	public void setRoundList(List<CompanySelectionRounds> roundList)
	{
		this.roundList = roundList;
	}

	public boolean isRound1()
	{
		return Round1;
	}

	public void setRound1(boolean round1)
	{
		Round1 = round1;
	}

	public boolean isRound2()
	{
		return Round2;
	}

	public void setRound2(boolean round2)
	{
		Round2 = round2;
	}

	public boolean isRound3()
	{
		return Round3;
	}

	public void setRound3(boolean round3)
	{
		Round3 = round3;
	}

	public boolean isRound4()
	{
		return Round4;
	}

	public void setRound4(boolean round4)
	{
		Round4 = round4;
	}

	public boolean isRound5()
	{
		return Round5;
	}

	public void setRound5(boolean round5)
	{
		Round5 = round5;
	}

	public boolean isRound6()
	{
		return Round6;
	}

	public void setRound6(boolean round6)
	{
		Round6 = round6;
	}

	public boolean isSelectstud()
	{
		return selectstud;
	}

	public void setSelectstud(boolean selectstud)
	{
		this.selectstud = selectstud;
	}

	public boolean isAccepted()
	{
		return Accepted;
	}

	public void setAccepted(boolean accepted)
	{
		Accepted = accepted;
	}

}
