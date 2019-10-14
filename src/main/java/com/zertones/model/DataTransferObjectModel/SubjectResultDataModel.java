package com.zertones.model.DataTransferObjectModel;

public class SubjectResultDataModel
{

	private Long	y;

	private String	label;

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public Long getY()
	{
		return y;
	}

	public void setY(Long y)
	{
		this.y = y;
	}

	@Override
	public String toString()
	{
		return "[y=" + y + ", label=" + label + "]";
	}

}
