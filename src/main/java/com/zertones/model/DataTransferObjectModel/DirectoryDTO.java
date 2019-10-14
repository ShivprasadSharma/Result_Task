package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class DirectoryDTO implements Serializable
{

	String				DepName;
	List<StaffInfoDto>	staffinfo	= new ArrayList<>();

	public String getDepName()
	{
		return DepName;
	}

	public void setDepName(String depName)
	{
		DepName = depName;
	}

	public List<StaffInfoDto> getStaffinfo()
	{
		return staffinfo;
	}

	public void setStaffinfo(List<StaffInfoDto> staffinfo)
	{
		this.staffinfo = staffinfo;
	}

}
