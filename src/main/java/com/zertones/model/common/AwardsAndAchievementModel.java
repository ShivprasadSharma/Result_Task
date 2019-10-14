package com.zertones.model.common;

import java.io.Serializable;

public class AwardsAndAchievementModel implements Serializable
{
	String	header;
	String	news;

	public String getHeader()
	{
		return header;
	}

	public void setHeader(String header)
	{
		this.header = header;
	}

	public String getNews()
	{
		return news;
	}

	public void setNews(String news)
	{
		this.news = news;
	}

	@Override
	public String toString()
	{
		return "AwardsAndAchievementModel [header=" + header + ", news=" + news + "]";
	}

}
