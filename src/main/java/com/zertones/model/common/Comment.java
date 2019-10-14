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
import com.zertones.model.ComClientName;

@Entity
@Table(name = "COM_COMMENT")
public class Comment extends BaseModel implements Serializable
{

	@Id
	@Column(name = "COMMENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer			comment_id;

	private String			comment;

	private Integer			noticeId;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private ComClientName	comClientName;

	public int getComment_id()
	{
		return comment_id;
	}

	public void setComment_id(int comment_id)
	{
		this.comment_id = comment_id;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public Integer getNoticeId()
	{
		return noticeId;
	}

	public void setNoticeId(Integer noticeId)
	{
		this.noticeId = noticeId;
	}

	public ComClientName getComClientName()
	{
		return comClientName;
	}

	public void setComClientName(ComClientName comClientName)
	{
		this.comClientName = comClientName;
	}

	@Override
	public String toString()
	{
		return "Comment [comment_id=" + comment_id + ", comment=" + comment + ", noticeId=" + noticeId
				+ ", comClientName=" + comClientName + "]";
	}

}
