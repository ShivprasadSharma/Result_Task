package com.zertones.model.common;

import java.io.Serializable;

public class AboutUsModel implements Serializable
{

	String	heading;
	String	description;
	// Image url if any
	String	url;

	public String getHeading()
	{
		return heading;
	}

	public void setHeading(String heading)
	{
		this.heading = heading;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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
		return "AboutUsModel [heading=" + heading + ", description=" + description + ", url=" + url + "]";
	}

}
