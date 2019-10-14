package com.zertones.model.DataTransferObjectModel;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class NotificationFilesDTO
{

	private String	document1Type;
	private String	string1;

	public String getDocument1Type()
	{
		return document1Type;
	}

	public void setDocument1Type(String document1Type)
	{
		this.document1Type = document1Type;
	}

	public String getString1()
	{
		return string1;
	}

	public void setString1(String string1)
	{
		this.string1 = string1;
	}

	@Override
	public String toString()
	{
		return "NotificationFilesDTO [document1Type=" + document1Type + ", string1=" + string1 + "]";
	}

}
