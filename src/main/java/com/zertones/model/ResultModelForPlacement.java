package com.zertones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "RESULT_MODEL_FOR_PLACEMENT")
@JsonInclude(Include.NON_NULL)
public class ResultModelForPlacement extends BaseModel
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RES_ID")
	private Integer	result_id;

	@Column(name = "RESULT_TYPE")
	private String	result_type;

	public Integer getResult_id()
	{
		return result_id;
	}

	public void setResult_id(Integer result_id)
	{
		this.result_id = result_id;
	}

	public String getResult_type()
	{
		return result_type;
	}

	public void setResult_type(String result_type)
	{
		this.result_type = result_type;
	}

	@Override
	public String toString()
	{
		return "ResultModelForPlacement [result_id=" + result_id + ", result_type=" + result_type + "]";
	}

}
