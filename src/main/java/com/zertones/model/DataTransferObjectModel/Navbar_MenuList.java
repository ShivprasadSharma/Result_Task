package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;

public class Navbar_MenuList implements Serializable
{

	private String	menuName;
	private boolean	staus;

	public String getMenuName()
	{
		return menuName;
	}

	public void setMenuName(String menuName)
	{
		this.menuName = menuName;
	}

	public boolean isStaus()
	{
		return staus;
	}

	public void setStaus(boolean staus)
	{
		this.staus = staus;
	}

}
