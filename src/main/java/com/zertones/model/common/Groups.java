package com.zertones.model.common;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zertones.model.BaseModel;

@Entity
@Table(name = "COM_GROUPS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "groupId")
public class Groups extends BaseModel
{

	@Id
	@Column(name = "GROUP_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	groupId;

	@Column(name = "GROUP_NAME")
	private String	groupName;

	@Column(name = "GROUP_DESCRIPTION")
	private String	groupDescription;

	@Column(name = "GROUP_INCHARGE_1")
	private Integer	groupIncharge_1;

	@Column(name = "GROUP_INCHARGE_2")
	private Integer	groupIncharge_2;

	@Column(name = "GROUP_INCHARGE_3")
	private String	groupIncharge_3;

	@Column(name = "GROUP_INCHARGE_4")
	private String	groupIncharge_4;

	@Column(name = "GROUP_DEPARTMENT")
	private Integer	department;

	@Column(name = "GROUP_FOR")
	private Integer	groupfor;

	@Column(name = "GROUP_TYPE")
	private Integer	groupType;

	@Column(name = "GFM_ID")
	private Integer	gfm_id;

	@Column(name = "STUD_CREATED")
	private boolean	studCreated;

	public Integer getGfm_id()
	{
		return gfm_id;
	}

	public void setGfm_id(Integer gfm_id)
	{
		this.gfm_id = gfm_id;
	}

	public Integer getGroupType()
	{
		return groupType;
	}

	public void setGroupType(Integer groupType)
	{
		this.groupType = groupType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "groups", cascade = CascadeType.ALL)
	private Set<GroupMembers> groupMembers = new HashSet<>();

	public Integer getGroupId()
	{
		return groupId;
	}

	public Integer getGroupfor()
	{
		return groupfor;
	}

	public void setGroupfor(Integer groupfor)
	{
		this.groupfor = groupfor;
	}

	public void setGroupId(Integer groupId)
	{
		this.groupId = groupId;
	}

	public String getGroupName()
	{
		return groupName;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	public String getGroupDescription()
	{
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription)
	{
		this.groupDescription = groupDescription;
	}

	public Integer getGroupIncharge_1()
	{
		return groupIncharge_1;
	}

	public void setGroupIncharge_1(Integer groupIncharge_1)
	{
		this.groupIncharge_1 = groupIncharge_1;
	}

	public Integer getGroupIncharge_2()
	{
		return groupIncharge_2;
	}

	public void setGroupIncharge_2(Integer groupIncharge_2)
	{
		this.groupIncharge_2 = groupIncharge_2;
	}

	public String getGroupIncharge_3()
	{
		return groupIncharge_3;
	}

	public void setGroupIncharge_3(String groupIncharge_3)
	{
		this.groupIncharge_3 = groupIncharge_3;
	}

	public String getGroupIncharge_4()
	{
		return groupIncharge_4;
	}

	public void setGroupIncharge_4(String groupIncharge_4)
	{
		this.groupIncharge_4 = groupIncharge_4;
	}

	public Set<GroupMembers> getGroupMembers()
	{
		return groupMembers;
	}

	public void setGroupMembers(Set<GroupMembers> groupMembers)
	{
		this.groupMembers = groupMembers;
	}

	public Integer getDepartment()
	{
		return department;
	}

	public void setDepartment(Integer department)
	{
		this.department = department;
	}

	public boolean isStudCreated()
	{
		return studCreated;
	}

	public void setStudCreated(boolean studCreated)
	{
		this.studCreated = studCreated;
	}

}
