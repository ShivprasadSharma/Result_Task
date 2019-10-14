package com.zertones.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "COM_STUDENT_FEEDBACKS")
public class FeedbackStudVote extends BaseModel implements Serializable
{

	@Id
	@Column(name = "VOTE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer			vote_id;

	@Column(name = "QUE_ID")
	private Integer			que_id;

	@Column(name = "VOTE")
	private Integer			vote;

	@Column(name = "STUD_CID")
	private Integer			stud_id;

	@Column(name = "SUB_Id")
	private Integer			sub_id;

	@ManyToOne
	@JoinColumn(name = "FEEDBACK_ID", nullable = false)
	private FeedbackVote	feedbackVote;

	public Integer getVote_id()
	{
		return vote_id;
	}

	public void setVote_id(Integer vote_id)
	{
		this.vote_id = vote_id;
	}

	public Integer getQue_id()
	{
		return que_id;
	}

	public void setQue_id(Integer que_id)
	{
		this.que_id = que_id;
	}

	public Integer getVote()
	{
		return vote;
	}

	public void setVote(Integer vote)
	{
		this.vote = vote;
	}

	public Integer getStud_id()
	{
		return stud_id;
	}

	public void setStud_id(Integer stud_id)
	{
		this.stud_id = stud_id;
	}

	public FeedbackVote getFeedbackVote()
	{
		return feedbackVote;
	}

	public void setFeedbackVote(FeedbackVote feedbackVote)
	{
		this.feedbackVote = feedbackVote;
	}

	public Integer getSub_id()
	{
		return sub_id;
	}

	public void setSub_id(Integer sub_id)
	{
		this.sub_id = sub_id;
	}

	@Override
	public String toString()
	{
		return "FeedbackStudVote [vote_id=" + vote_id + ", que_id=" + que_id + ", vote=" + vote + ", stud_id=" + stud_id
				+ ", sub_id=" + sub_id + ", feedbackVote=" + feedbackVote + "]";
	}

}
