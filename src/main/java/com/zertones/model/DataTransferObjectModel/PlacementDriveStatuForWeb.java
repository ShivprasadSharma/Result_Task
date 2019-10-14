package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;
import java.util.List;

import com.zertones.model.common.ApplyedStudentForCompany;
import com.zertones.model.common.CompanySelectionRounds;

public class PlacementDriveStatuForWeb implements Serializable
{

	private List<CompanySelectionRounds>	roundList;

	private ApplyedStudentForCompany		applyedStudentForCompany;

	public List<CompanySelectionRounds> getRoundList()
	{
		return roundList;
	}

	public void setRoundList(List<CompanySelectionRounds> roundList)
	{
		this.roundList = roundList;
	}

	public ApplyedStudentForCompany getApplyedStudentForCompany()
	{
		return applyedStudentForCompany;
	}

	public void setApplyedStudentForCompany(ApplyedStudentForCompany applyedStudentForCompany)
	{
		this.applyedStudentForCompany = applyedStudentForCompany;
	}

}
