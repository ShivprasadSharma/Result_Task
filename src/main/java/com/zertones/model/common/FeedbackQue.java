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
@Table(name = "FEEDBACK_QUESTIONS")
public class FeedbackQue extends BaseModel implements Serializable
{

	@Id
	@Column(name = "QUE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	que_id;

	private boolean	isChecked	= false;

	// @Column(name = "VOTE")
	private Integer	vote;

	@Column(name = "QUE_NO")
	private Integer	que_no;

	@Column(name = "QUESTIONS")
	private String	questions;

	public Integer getQue_id()
	{
		return que_id;
	}

	public void setQub_id(Integer que_id)
	{
		this.que_id = que_id;
	}

	public String getQuestions()
	{
		return questions;
	}

	public void setQuestions(String questions)
	{
		this.questions = questions;
	}

	public boolean isChecked()
	{
		return isChecked;
	}

	public void setChecked(boolean isChecked)
	{
		this.isChecked = isChecked;
	}

	public Integer getVote()
	{
		return vote;
	}

	public void setVote(Integer vote)
	{
		this.vote = vote;
	}

	public Integer getQue_no()
	{
		return que_no;
	}

	public void setQue_no(Integer que_no)
	{
		this.que_no = que_no;
	}

	@Override
	public String toString()
	{
		return "FeedbackQue [que_id=" + que_id + ", isChecked=" + isChecked + ", vote=" + vote + ", que_no=" + que_no
				+ ", questions=" + questions + "]";
	}

}
