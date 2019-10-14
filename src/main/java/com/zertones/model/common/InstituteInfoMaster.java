package com.zertones.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zertones.model.BaseModel;

@Entity
@Table(name = "INSTITUTE_LIST_MST")
public class InstituteInfoMaster extends BaseModel implements java.io.Serializable
{
	private static final long	serialVersionUID	= 1117935444961302329L;

	@Id
	@Column(name = "INST_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				instId;

	@Column(name = "NAME")
	private String				name;

	@Column(name = "Address")
	private String				address;

	@Column(name = "LOGO_URL")
	private String				logoUrl;

	@Column(name = "BGIMG_URL")
	private String				bgImgUrl;

	@Column(name = "CONTACT_NO")
	private String				contactNo;

	@Column(name = "CONTEXT")
	private String				context;

	@Column(name = "SHORT_CONTEXT")
	private String				short_context;

	@Column(name = "INST_CODE")
	private int					instCode;

	@Column(name = "EMAIL_ID")
	private String				email;

	public int getInstCode()
	{
		return instCode;
	}

	public void setInstCode(int instCode)
	{
		this.instCode = instCode;
	}

	public Integer getInstId()
	{
		return instId;
	}

	public void setInstId(Integer instId)
	{
		this.instId = instId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getLogoUrl()
	{
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl)
	{
		this.logoUrl = logoUrl;
	}

	public String getBgImgUrl()
	{
		return bgImgUrl;
	}

	public void setBgImgUrl(String bgImgUrl)
	{
		this.bgImgUrl = bgImgUrl;
	}

	public String getContactNo()
	{
		return contactNo;
	}

	public void setContactNo(String contactNo)
	{
		this.contactNo = contactNo;
	}

	public String getContext()
	{
		return context;
	}

	public void setContext(String context)
	{
		this.context = context;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getShort_context()
	{
		return short_context;
	}

	public void setShort_context(String short_context)
	{
		this.short_context = short_context;
	}

	@Override
	public String toString()
	{
		return "InstituteInfoMaster [instId=" + instId + ", name=" + name + ", address=" + address + ", logoUrl="
				+ logoUrl + ", bgImgUrl=" + bgImgUrl + ", contactNo=" + contactNo + ", context=" + context + "]";
	}

}
