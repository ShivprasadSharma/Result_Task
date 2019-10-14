package com.zertones.model.common;

import java.io.Serializable;
import java.util.List;

import com.zertones.model.BaseModel;
import com.zertones.model.master.CourseMaster;
import com.zertones.model.master.ResultFile;

public class InstituteInfoModel extends BaseModel implements Serializable
{
	private List<AwardsAndAchievementModel>	awardsAndAchievementModel;
	private List<InstituteInfoMaster>		instituteInfoMasters;
	private List<Placement>					placement;
	private List<CourseMaster>				courses;
	private List<EventModel>				eventModel;
	private List<ResultFile>				results;
	private List<LifeOfCampus>				lifeOfCampuse;

	public List<InstituteInfoMaster> getInstituteInfoMasters()
	{
		return instituteInfoMasters;
	}

	public void setInstituteInfoMasters(List<InstituteInfoMaster> instituteInfoMasters)
	{
		this.instituteInfoMasters = instituteInfoMasters;
	}

	public List<Placement> getPlacement()
	{
		return placement;
	}

	public void setPlacement(List<Placement> placement)
	{
		this.placement = placement;
	}

	public List<CourseMaster> getCourses()
	{
		return courses;
	}

	public void setCourses(List<CourseMaster> courses)
	{
		this.courses = courses;
	}

	public List<ResultFile> getResults()
	{
		return results;
	}

	public void setResults(List<ResultFile> results)
	{
		this.results = results;
	}

	public List<LifeOfCampus> getLifeOfCampuse()
	{
		return lifeOfCampuse;
	}

	public void setLifeOfCampuse(List<LifeOfCampus> lifeOfCampuse)
	{
		this.lifeOfCampuse = lifeOfCampuse;
	}

	public List<AwardsAndAchievementModel> getAwardsAndAchievementModel()
	{
		return awardsAndAchievementModel;
	}

	public void setAwardsAndAchievementModel(List<AwardsAndAchievementModel> awardsAndAchievementModel)
	{
		this.awardsAndAchievementModel = awardsAndAchievementModel;
	}

	public List<InstituteInfoMaster> getInstituteInfoMatser()
	{
		return instituteInfoMasters;
	}

	public void setInstituteInfoMatser(List<InstituteInfoMaster> instituteInfoMasters)
	{
		this.instituteInfoMasters = instituteInfoMasters;
	}

	public List<EventModel> getEventModel()
	{
		return eventModel;
	}

	public void setEventModel(List<EventModel> eventModel)
	{
		this.eventModel = eventModel;
	}

	@Override
	public String toString()
	{
		return "InstituteInfoModel [awardsAndAchievementModel=" + awardsAndAchievementModel + ", instituteInfoMasters="
				+ instituteInfoMasters + ", placement=" + placement + ", courses=" + courses + ", eventModel="
				+ eventModel + ", results=" + results + ", lifeOfCampuse=" + lifeOfCampuse + "]";
	}

}
