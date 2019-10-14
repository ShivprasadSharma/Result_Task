package com.zertones.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "LECTURES_THEORY_TIME")
public class LectureTheoryTime extends BaseModel implements Serializable
{
	@Id
	@Column(name = "THE_NO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	the_no;

	@Column(name = "START_TIME")
	private String	start_time;

	@Column(name = "END_TIME")
	private String	end_time;

	public Integer getThe_no()
	{
		return the_no;
	}

	public void setThe_no(Integer the_no)
	{
		this.the_no = the_no;
	}

	public String getStart_time()
	{
		return start_time;
	}

	public void setStart_time(String start_time)
	{
		this.start_time = start_time;
	}

	public String getEnd_time()
	{
		return end_time;
	}

	public void setEnd_time(String end_time)
	{
		this.end_time = end_time;
	}

}
