package com.zertones.model.sims;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.crypto.codec.Base64;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zertones.model.BaseModel;
import com.zertones.model.ComClientName;

@Entity
@Table(name = "SIMS_STAFF")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "staffId")
public class Staff extends BaseModel implements java.io.Serializable
{
	private static final long	serialVersionUID	= -8654064518552445737L;

	@Id
	@Column(name = "STAFF_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				staffId;

	@Column(name = "REGISTRATION_NO")
	private String				registrationNo;

	@Column(name = "EMPLOYEE_NO")
	private String				employeeNo;

	@Column(name = "DESIGNATION")
	private String				designation;

	@Column(name = "STAFF_TYPE")
	private Integer				staffType;

	@Column(name = "DEPARTMENT")
	private Integer				department;

	@Column(name = "UNIVERSITY_ENROLL_NO")
	private String				universityEnrollNo;

	@Column(name = "PROFIEL_IMG", length = 20971520)
	private byte[]				profileImg;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "CLIENT_ID", nullable = false)
	private ComClientName		comClientName;

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
	 * @Column(name = "RECORD_STATUS") private String recordStatus;
	 */

	public Integer getStaffId()
	{
		return staffId;
	}

	public byte[] getProfileImg()
	{
		return profileImg;
	}

	public void setProfileImg(byte[] profileImg)
	{
		this.profileImg = profileImg;
	}

	public void setStaffId(Integer staffId)
	{
		this.staffId = staffId;
	}

	public String getRegistrationNo()
	{
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo)
	{
		this.registrationNo = registrationNo;
	}

	public String getEmployeeNo()
	{
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo)
	{
		this.employeeNo = employeeNo;
	}

	public String getUniversityEnrollNo()
	{
		return universityEnrollNo;
	}

	public void setUniversityEnrollNo(String universityEnrollNo)
	{
		this.universityEnrollNo = universityEnrollNo;
	}

	public ComClientName getComClientName()
	{
		return comClientName;
	}

	public void setComClientName(ComClientName comClientName)
	{
		this.comClientName = comClientName;
	}

	public String getByteArrayString()
	{
		if (this.profileImg != null)
		{
			return new String(Base64.encode(this.profileImg));
		} else
		{
			return "";
		}
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
	 *
	 * @Override public void setRecordStatus(String recordStatus) {
	 * this.recordStatus = recordStatus; }
	 */

	public String getDesignation()
	{
		return designation;
	}

	public void setDesignation(String designation)
	{
		this.designation = designation;
	}

	public Integer getStaffType()
	{
		return staffType;
	}

	public void setStaffType(Integer staffType)
	{
		this.staffType = staffType;
	}

	public Integer getDepartment()
	{
		return department;
	}

	public void setDepartment(Integer department)
	{
		this.department = department;
	}

	@Override
	public String toString()
	{
		return "Staff [staffId=" + staffId + ", registrationNo=" + registrationNo + ", employeeNo=" + employeeNo
				+ ", designation=" + designation + ", staffType=" + staffType + ", department=" + department
				+ ", universityEnrollNo=" + universityEnrollNo + ", profileImg=" + Arrays.toString(profileImg)
				+ ", comClientName=" + comClientName + "]";
	}

	/*
	 * @Override
	 *
	 * @PrePersist public void onCreate() { System.out.println(
	 * "Calling on pre persist2"); Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); String name =
	 * auth.getName(); this.setCreatedDate(new Date()); this.setCreatedBy(name); }
	 */

}
