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
@Table(name = "COM_LIKE")
public class Like extends BaseModel implements Serializable
{

	@Id
	@Column(name = "LIKE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	like_Id;

	@Column(name = "NOTIFICATION_ID")
	private Integer	notification_id;

	@Column(name = "CLIENT_ID")
	private Integer	client_Id;

	@Column(name = "LIKE_STATUS")
	private Boolean	like_Status;

	public Integer getLike_Id()
	{
		return like_Id;
	}

	public void setLike_Id(Integer like_Id)
	{
		this.like_Id = like_Id;
	}

	public Integer getNotification_id()
	{
		return notification_id;
	}

	public void setNotification_id(Integer notification_id)
	{
		this.notification_id = notification_id;
	}

	public Integer getClient_Id()
	{
		return client_Id;
	}

	public void setClient_Id(Integer client_Id)
	{
		this.client_Id = client_Id;
	}

	@Override
	public String toString()
	{
		return "Like [like_Id=" + like_Id + ", notification_id=" + notification_id + ", client_Id=" + client_Id
				+ ", like_Status=" + like_Status + "]";
	}

	public Boolean getLike_Status()
	{
		return like_Status;
	}

	public void setLike_Status(Boolean like_Status)
	{
		this.like_Status = like_Status;
	}

}
