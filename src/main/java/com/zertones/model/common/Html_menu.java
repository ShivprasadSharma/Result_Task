package com.zertones.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zertones.model.BaseModel;

@Entity
@Table(name = "HTML_MENU")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Html_menu extends BaseModel implements Serializable
{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	id;

	@Column(name = "MENU_NAME")
	private String	menu_name;

	@Column(name = "HTML_PAGE", columnDefinition = "TEXT")
	private String	htmlstring;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getMenu_name()
	{
		return menu_name;
	}

	public void setMenu_name(String menu_name)
	{
		this.menu_name = menu_name;
	}

	public String getHtmlstring()
	{
		return htmlstring;
	}

	public void setHtmlstring(String htmlstring)
	{
		this.htmlstring = htmlstring;
	}

	@Override
	public String toString()
	{
		return "Html_menu [id=" + id + ", menu_name=" + menu_name + ", htmlstring=" + htmlstring + "]";
	}

}
