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
@Table(name = "HELPS_URL")
public class Help extends BaseModel implements Serializable
{

	@Id
	@Column(name = "URLID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	id;

	@Column(name = "ROLE_ID")
	private int		roleId;

	@Column(name = "MENU_ID")
	private int		menuId;

	@Column(name = "ORDER_ID")
	private Integer	orderID;

	@Column(name = "TITLE")
	private String	title;

	@Column(name = "URL")
	private String	url;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public int getMenuId()
	{
		return menuId;
	}

	public void setMenuId(int menuId)
	{
		this.menuId = menuId;
	}

	public Integer getOrderID()
	{
		return orderID;
	}

	public void setOrderID(Integer orderID)
	{
		this.orderID = orderID;
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

	public int getRoleId()
	{
		return roleId;
	}

	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}

	@Override
	public String toString()
	{
		return "Help [id=" + id + ", menuId=" + menuId + ", orderID=" + orderID + ", title=" + title + ", url=" + url
				+ "]";
	}

}
