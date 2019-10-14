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
@Table(name = "LECTURES_PRA_TIME")
public class LecturePracticalTime extends BaseModel implements Serializable
{

	@Id
	@Column(name = "PRE_NO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	pre_no;

	@Column(name = "START_TIME")
	private String	start_time;

	@Column(name = "END_TIME")
	private String	end_time;

	public Integer getPre_no()
	{
		return pre_no;
	}

	public void setPre_no(Integer pre_no)
	{
		this.pre_no = pre_no;
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
