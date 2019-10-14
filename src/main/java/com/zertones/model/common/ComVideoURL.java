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
@Table(name = "VIDEO_URL")
public class ComVideoURL extends BaseModel implements Serializable
{

	@Id
	@Column(name = "VIDEO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	videoId;

	@Column(name = "TITLE")
	private String	title;

	@Column(name = "URL")
	private String	url;

	public Integer getVideoId()
	{
		return videoId;
	}

	public void setVideoId(Integer videoId)
	{
		this.videoId = videoId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	@Override
	public String toString()
	{
		return "ComVideoURL [videoId=" + videoId + ", title=" + title + ", url=" + url + "]";
	}

}
