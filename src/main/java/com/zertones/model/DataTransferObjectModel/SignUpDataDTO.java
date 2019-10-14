package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class SignUpDataDTO implements Serializable
{

	private List<DepartmentDTO>	departmentDto;
	private List<emailData>		email;

	public List<DepartmentDTO> getDepartmentDto()
	{
		return departmentDto;
	}

	public void setDepartmentDto(List<DepartmentDTO> departmentDto)
	{
		this.departmentDto = departmentDto;
	}

	public List<emailData> getEmail()
	{
		return email;
	}

	public void setEmail(List<emailData> email)
	{
		this.email = email;
	}

}
