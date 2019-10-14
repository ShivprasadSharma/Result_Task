package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class GroupDTO implements Serializable
{

	private Integer	groupId;

	private String	groupName;

	private String	groupDescription;

	private Integer	groupIncharge_1;

	private Integer	groupIncharge_2;

	private String	groupIncharge_3;

	private String	groupIncharge_4;

	private Integer	department;

	private Integer	groupType;

	private Integer	members;

	private Integer	groupfor;
	private Integer	gfm_id;

	public Integer getGfm_id()
	{
		return gfm_id;
	}

	public void setGfm_id(Integer gfm_id)
	{
		this.gfm_id = gfm_id;
	}

	public Integer getGroupfor()
	{
		return groupfor;
	}

	public void setGroupfor(Integer groupfor)
	{
		this.groupfor = groupfor;
	}

	List<ComClientNameDTO> groupMembers;

	public Integer getMembers()
	{
		return members;
	}

	public void setMembers(Integer members)
	{
		this.members = members;
	}

	public Integer getGroupId()
	{
		return groupId;
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

	public Integer getDepartment()
	{
		return department;
	}

	public void setDepartment(Integer department)
	{
		this.department = department;
	}

	public Integer getGroupType()
	{
		return groupType;
	}

	public void setGroupType(Integer groupType)
	{
		this.groupType = groupType;
	}

	public List<ComClientNameDTO> getGroupMembers()
	{
		return groupMembers;
	}

	public void setGroupMembers(List<ComClientNameDTO> groupMembers)
	{
		this.groupMembers = groupMembers;
	}

	@Override
	public String toString()
	{
		return "GroupDTO [groupId=" + groupId + ", groupName=" + groupName + ", groupDescription=" + groupDescription
				+ ", groupIncharge_1=" + groupIncharge_1 + ", groupIncharge_2=" + groupIncharge_2 + ", groupIncharge_3="
				+ groupIncharge_3 + ", groupIncharge_4=" + groupIncharge_4 + ", department=" + department
				+ ", groupType=" + groupType + ", groupMembers=" + groupMembers + ", groupfor=" + groupfor + "]";
	}

}
