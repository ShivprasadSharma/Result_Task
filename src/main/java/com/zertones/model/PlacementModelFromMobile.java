package com.zertones.model;

import java.util.List;

public class PlacementModelFromMobile
{

	private List<Semester>					semlist;

	private List<ResultModelForPlacement>	resultModel;

	private List<PlacementYears>			placementYear;

	public List<PlacementYears> getPlacementYear()
	{
		return placementYear;
	}

	public void setPlacementYear(List<PlacementYears> placementYear)
	{
		this.placementYear = placementYear;
	}

	public List<Semester> getSemlist()
	{
		return semlist;
	}

	public void setSemlist(List<Semester> semlist)
	{
		this.semlist = semlist;
	}

	public List<ResultModelForPlacement> getResultModel()
	{
		return resultModel;
	}

	public void setResultModel(List<ResultModelForPlacement> resultModel)
	{
		this.resultModel = resultModel;
	}

	// private Double sscMarks;
	//
	// private Double hscMarks;
	//
	// private Double diplomaMarks;
	//
	// private Double sem1;
	//
	// private Double sem2;
	//
	// private Double sem3;
	//
	// private Double sem4;
	//
	// private Double sem5;
	//
	// private Double sem6;
	//
	// private Double sem7;
	//
	// private Double sem8;
	//
	// private Integer feResult;
	//
	// private Integer seResult;
	//
	// private Integer teResult;
	//
	// private Integer beResult;

}
