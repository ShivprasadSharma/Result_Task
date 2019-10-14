package com.zertones.model.master;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.zertones.model.BaseModel;

/**
 * @author Abhijit
 * @Created Date : Oct 5, 2015
 */
@Entity
@Table(name = "COM_ACADEMIC_YEAR_MST")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "yearId")
public class AcademicYear extends BaseModel implements java.io.Serializable
{
	private static final long	serialVersionUID	= -8195008327271076910L;

	@Id
	@Column(name = "YEAR_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				yearId;

	@Column(name = "YEAR_START_DT")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateSerializer.class)
	@JsonDeserialize(using = DateDeserializer.class)
	private Date				yearStartDate;

	@Column(name = "YEAR_END_DT")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateSerializer.class)
	@JsonDeserialize(using = DateDeserializer.class)
	private Date				yearEndDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "academicYear", cascade = CascadeType.ALL)
	// @JsonManagedReference
	// @JsonIgnore
	Set<CourseMaster>			courseMaster		= new HashSet<>();

	@Column(name = "YEAR_STATUS")
	Integer						yearStatus;

	/*
	 * @Column(name = "CREATED_DT")
	 *
	 * @Temporal(TemporalType.TIMESTAMP) private Date createdDate;
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

	public Integer getYearId()
	{
		return yearId;
	}

	public void setYearId(Integer yearId)
	{
		this.yearId = yearId;
	}

	public Date getYearStartDate()
	{
		return yearStartDate;
	}

	public void setYearStartDate(Date yearStartDate)
	{
		this.yearStartDate = yearStartDate;
	}

	public Date getYearEndDate()
	{
		return yearEndDate;
	}

	public void setYearEndDate(Date yearEndDate)
	{
		this.yearEndDate = yearEndDate;
	}

	public Integer getYearStatus()
	{
		return yearStatus;
	}

	public void setYearStatus(Integer yearStatus)
	{
		this.yearStatus = yearStatus;
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

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	@Override
	public String toString()
	{
		return "AcademicYear [yearId=" + yearId + ", yearStartDate=" + yearStartDate + ", yearEndDate=" + yearEndDate
				+ ", courseMaster=" + courseMaster + ", yearStatus=" + yearStatus + "]";
	}

	public Set<CourseMaster> getCourseMaster()
	{
		return courseMaster;
	}

	public void setCourseMaster(Set<CourseMaster> courseMaster)
	{
		courseMaster.forEach((course) ->
		{
			course.setAcademicYear(this);
		});
		this.courseMaster = courseMaster;
	}

}
