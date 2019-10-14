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
@Table(name = "CONTACT_US")
public class ContactUsModel extends BaseModel implements Serializable
{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int		id;

	@Column(name = "NAME")
	private String	name;

	@Column(name = "EMAIL_ADDRESS")
	private String	email;

	@Column(name = "MESSAGE")
	private String	message;

	@Column(name = "CONTACT_NUMBER")
	private String	contactno;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getContactno()
	{
		return contactno;
	}

	public void setContactno(String contactno)
	{
		this.contactno = contactno;
	}

	@Override
	public String toString()
	{
		return "ContactUsModel [id=" + id + ", name=" + name + ", email=" + email + ", message=" + message
				+ ", contactno=" + contactno + "]";
	}

}
