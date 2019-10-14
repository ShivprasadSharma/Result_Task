package com.zertones.model.common;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zertones.model.BaseModel;

/**
 * @author Abhijit
 * @Created Date : Sep 17, 2015
 */

@Entity
@Table(name = "COM_MENU_MST")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "menuId")
public class MenuList extends BaseModel implements java.io.Serializable
{
	private static final long	serialVersionUID	= -2500891343639419535L;

	@Id
	@Column(name = "MENU_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				menuId;

	@Column(name = "VALUE")
	private String				value;

	@Column(name = "URL")
	private String				url;

	@Column(name = "PARENT_ID")
	private Integer				parentId;

	@ManyToOne(cascade =
	{ CascadeType.ALL })
	@JoinColumn(name = "PARENT_ID", insertable = false, updatable = false)
	private MenuList			menuList;

	@Column(name = "DESCRIPTION")
	private String				description;

	/*
	 * @Column(name = "CREATED_DT")
	 *
	 * @Temporal(TemporalType.TIMESTAMP) private Date createdDate = new Date();
	 *
	 * @Column(name = "CREATED_BY") private String createdBy;
	 *
	 * @Column(name = "UPDATED_DT")
	 *
	 * @Temporal(TemporalType.TIMESTAMP) private Date updatedDate;
	 *
	 * @Column(name = "UPDATED_BY") private String updatedBy;
	 *
	 * @Column(name = "RECORD_STATUS") private String recordStatus = "A";
	 */

	public Integer getMenuId()
	{
		return menuId;
	}

	public void setMenuId(Integer menuId)
	{
		this.menuId = menuId;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public Integer getParentId()
	{
		return parentId;
	}

	public void setParentId(Integer parentId)
	{
		this.parentId = parentId;
	}

	public MenuList getMenuList()
	{
		return menuList;
	}

	public void setMenuList(MenuList menuList)
	{
		this.menuList = menuList;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@Override
	public String toString()
	{
		return "MenuList [menuId=" + menuId + ", value=" + value + ", url=" + url + ", parentId=" + parentId
				+ ", menuList=" + menuList + ", description=" + description + "]";
	}

	/*
	 * @Override public Date getCreatedDate() { return createdDate; }
	 *
	 * @Override public void setCreatedDate(Date createdDate) { this.createdDate =
	 * createdDate; }
	 *
	 * @Override public String getCreatedBy() { return createdBy; }
	 *
	 * @Override public void setCreatedBy(String createdBy) { this.createdBy =
	 * createdBy; }
	 *
	 * @Override public Date getUpdatedDate() { return updatedDate; }
	 *
	 * @Override public void setUpdatedDate(Date updatedDate) { this.updatedDate =
	 * updatedDate; }
	 *
	 * @Override public String getUpdatedBy() { return updatedBy; }
	 *
	 * @Override public void setUpdatedBy(String updatedBy) { this.updatedBy =
	 * updatedBy; }
	 *
	 * @Override public String getRecordStatus() { return recordStatus; }
	 *
	 * @Override public void setRecordStatus(String recordStatus) {
	 * this.recordStatus = recordStatus; }
	 */

}
