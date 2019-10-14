package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;

public class GroupPollOptionPercentageDTO implements Serializable
{

	private String	optionname;
	private Float	percentage;

	public String getOptionname()
	{
		return optionname;
	}

	public void setOptionname(String optionname)
	{
		this.optionname = optionname;
	}

	public Float getPercentage()
	{
		return percentage;
	}

	public void setPercentage(Float percentage)
	{
		this.percentage = percentage;
	}

}

