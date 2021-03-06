package com.zertones.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zertones.security.model.UserRole;

@Entity
@Table(name = "COM_USER_DTL")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ComUserDetails extends BaseModel implements Serializable
{

	@Id
	@Column(name = "COM_USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				id;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENT_ID", nullable = false)
	private ComClientName		comClientName;

	@OneToMany(fetch = FetchType.LAZY, cascade =
	{ CascadeType.ALL }, mappedBy = "comUserDetails")
	@JsonManagedReference
	@ElementCollection(fetch = FetchType.LAZY)
	private Set<DeviceDetails>	deviceDetails	= new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, cascade =
	{ CascadeType.ALL }, mappedBy = "comUserDetails")
	@JsonManagedReference
	@ElementCollection(fetch = FetchType.LAZY)
	private Set<UserRole>		userRoles		= new HashSet<>();

	/*
	 * @OneToMany(fetch = FetchType.EAGER, mappedBy = "comUserDetails") private
	 * Set<UserSessions> userSessions = new HashSet<UserSessions>();
	 */
	@Column(name = "USER_NAME")
	private String				userName;

	@Column(name = "USER_PWD")
	@Size(min = 4, max = 13)
	private String				password;

	@Column(name = "IS_ACTIVE", length = 5)
	private Integer				isActive;

	@Column(name = "USER_ROLE")
	private String				userRole;

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

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public ComClientName getComClientName()
	{
		return comClientName;
	}

	public void setComClientName(ComClientName comClientName)
	{
		this.comClientName = comClientName;
	}

	public Set<DeviceDetails> getDeviceDetails()
	{
		return deviceDetails;
	}

	public void setDeviceDetails(Set<DeviceDetails> deviceDetails)
	{
		deviceDetails.forEach((device) ->
		{
			device.setComUserDetails(this);
		});
		this.deviceDetails = deviceDetails;
	}

	public Integer getIsActive()
	{
		return isActive;
	}

	public void setIsActive(Integer isActive)
	{
		this.isActive = isActive;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getUserRole()
	{
		return userRole;
	}

	public void setUserRole(String userRole)
	{
		this.userRole = userRole;
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

	public Set<UserRole> getUserRoles()
	{
		return userRoles;
	}

	/*
	 * public void setUserRoles(Set<UserRole> userRoles) { this.userRoles =
	 * userRoles; }
	 */

	public void setUserRoles(Set<UserRole> userRoles)
	{
		userRoles.forEach((role) ->
		{
			role.setComUserDetails(this);
		});
		this.userRoles = userRoles;
	}

}
