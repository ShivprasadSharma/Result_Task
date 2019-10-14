package com.zertones.model.master;

import java.util.HashSet;
import java.util.Set;

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
@Table(name = "COM_LIST_MST")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "listId")
public class ComListMaster extends BaseModel implements java.io.Serializable
{
	private static final long	serialVersionUID	= 5408136749548491686L;

	@Id
	@Column(name = "LIST_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				listId;

	@Column(name = "LIST_DESC")
	private String				description;

	@Column(name = "LIST_VALUE")
	private String				value;

	@Column(name = "URL_IMG")
	private String				url;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "comListMaster")
	private Set<ComListDetails>	comListDetails		= new HashSet<>();

	public Integer getListId()
	{
		return listId;
	}

	public void setListId(Integer listId)
	{
		this.listId = listId;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public Set<ComListDetails> getComListDetails()
	{
		return comListDetails;
	}

	public void setComClientAddresses(Set<ComListDetails> comListDetails)
	{
		this.comListDetails = comListDetails;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public void setComListDetails(Set<ComListDetails> comListDetails)
	{
		this.comListDetails = comListDetails;
	}

	@Override
	public String toString()
	{
		return "ComListMaster [listId=" + listId + ", description=" + description + ", value=" + value + ", url=" + url
				+ "]";
	}

}
