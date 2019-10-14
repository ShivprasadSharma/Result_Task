package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.zertones.model.common.LecturePracticalTime;
import com.zertones.model.common.LectureTheoryTime;

public class AttendanceDTO implements Serializable
{

	private List<SubjectsDTO>			subList					= new ArrayList();
	private List<LectureTheoryTime>		lectureTheoryTimes		= new ArrayList();
	private List<LecturePracticalTime>	lecturePracticalTimes	= new ArrayList();

	public List<SubjectsDTO> getSubList()
	{
		return subList;
	}

	public void setSubList(List<SubjectsDTO> subList)
	{
		this.subList = subList;
	}

	public List<LectureTheoryTime> getLectureTheoryTimes()
	{
		return lectureTheoryTimes;
	}

	public void setLectureTheoryTimes(List<LectureTheoryTime> lectureTheoryTimes)
	{
		this.lectureTheoryTimes = lectureTheoryTimes;
	}

	public List<LecturePracticalTime> getLecturePracticalTimes()
	{
		return lecturePracticalTimes;
	}

	public void setLecturePracticalTimes(List<LecturePracticalTime> lecturePracticalTimes)
	{
		this.lecturePracticalTimes = lecturePracticalTimes;
	}

}
