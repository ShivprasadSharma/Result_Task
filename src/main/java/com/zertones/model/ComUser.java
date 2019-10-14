package com.zertones.model;

import java.io.Serializable;

public class ComUser implements Serializable
{

	private Integer	id;

	private String	oldCredential;

	private String	newCredential;

	private String	confirmCredential;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getNewCredential()
	{
		return newCredential;
	}

	public void setNewCredential(String newCredential)
	{
		this.newCredential = newCredential;
	}

	public String getConfirmCredential()
	{
		return confirmCredential;
	}

	public void setConfirmCredential(String confirmCredential)
	{
		this.confirmCredential = confirmCredential;
	}

	public String getOldCredential()
	{
		return oldCredential;
	}

	public void setOldCredential(String oldCredential)
	{
		this.oldCredential = oldCredential;
	}

}
