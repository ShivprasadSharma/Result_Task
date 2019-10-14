package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;

public class CommentDTO implements Serializable
{

	private String	comment;

	private Integer	noticeId;

	private String	firstName;

	private String	lastName;

	private String	imgUrl;

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

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getImgUrl()
	{
		return imgUrl;
	}

	public void setImgUrl(String imgUrl)
	{
		this.imgUrl = imgUrl;
	}

}
