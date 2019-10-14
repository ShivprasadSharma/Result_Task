package com.zertones.model.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zertones.model.ComClientName;
import com.zertones.system.utility.JsonDateDeserializer;
import com.zertones.system.utility.JsonDateSerializer;

@Entity
@Table(name = "COM_USER_TOKEN")
public class UserToken implements Serializable
{

	@Id
	@Column(name = "TOKEN_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long			tokenId;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CLIENT_ID", nullable = false)
	private ComClientName	comClientName;

	@Column(name = "VERIFICATION_TOKEN")
	private String			verificationToken;

	@Column(name = "VERIFICATION_ATTEMPT")
	private Integer			verificatioAttempt;

	@Column(name = "SET_PASS_STATUS")
	private Integer			recordStatus;

	@Column(name = "TOKEN_EXPIRATION")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date			tokenExpiration	= new Date();

	public long getTokenId()
	{
		return tokenId;
	}

	public void setTokenId(long tokenId)
	{
		this.tokenId = tokenId;
	}

	public ComClientName getComClientName()
	{
		return comClientName;
	}

	public void setComClientName(ComClientName comClientName)
	{
		this.comClientName = comClientName;
	}

	public String getVerificationToken()
	{
		return verificationToken;
	}

	public void setVerificationToken(String verificationToken)
	{
		this.verificationToken = verificationToken;
	}

	public Date getTokenExpiration()
	{
		return tokenExpiration;
	}

	public void setTokenExpiration(Date tokenExpiration)
	{
		this.tokenExpiration = tokenExpiration;
	}

	public Integer getVerificatioAttempt()
	{
		return verificatioAttempt;
	}

	public void setVerificatioAttempt(Integer verificatioAttempt)
	{
		this.verificatioAttempt = verificatioAttempt;
	}

	public Integer getRecordStatus()
	{
		return recordStatus;
	}

	public void setRecordStatus(Integer recordStatus)
	{
		this.recordStatus = recordStatus;
	}

	@Override
	public String toString()
	{
		return "UserToken [tokenId=" + tokenId + ", verificationToken=" + verificationToken + ", tokenExpiration="
				+ tokenExpiration + ", comClientName=" + comClientName + ",verificatioAttempt =" + verificatioAttempt
				+ "]";
	}
}
