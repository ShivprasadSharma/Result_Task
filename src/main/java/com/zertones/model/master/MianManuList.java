package com.zertones.model.master;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "MAIN_MENU_LIST")
public class MianManuList extends BaseModel implements Serializable
{

	@Id
	@Column(name = "MANU_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	id;

	@Column(name = "MENU_NAME")
	private String	menu_Name;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getMenu_Name()
	{
		return menu_Name;
	}

	public void setMemu_Name(String menu_Name)
	{
		this.menu_Name = menu_Name;
	}

	@Override
	public String toString()
	{
		return "MianManuList [id=" + id + ", menu_Name=" + menu_Name + "]";
	}

}
