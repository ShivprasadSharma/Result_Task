package com.zertones.model.master;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "COLLEGE_MENU_LIST")
public class CollegeMenuList extends BaseModel implements Serializable
{

	@Id
	@Column(name = "CLG_MENU_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer			id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "MANU_ID")
	private MianManuList	mainMenuList;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public MianManuList getMainMenuList()
	{
		return mainMenuList;
	}

	public void setMainMenuList(MianManuList mainMenuList)
	{
		this.mainMenuList = mainMenuList;
	}

	@Override
	public String toString()
	{
		return "CollegeMenuList [id=" + id + ", mainMenuList=" + mainMenuList + "]";
	}

}
