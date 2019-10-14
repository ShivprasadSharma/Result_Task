package com.zertones.model.master;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zertones.model.BaseModel;
import com.zertones.model.common.ExamSchedule;
import com.zertones.model.common.TimeTable;

/**
 * @author Abhijit
 * @Created Date : Jan 16, 2016
 */
@Entity
@Table(name = "COM_COURSE_MST")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "courseId")
public class CourseMaster extends BaseModel implements java.io.Serializable
{
	private static final long	serialVersionUID	= -7573402062509664825L;

	@Id
	@Column(name = "COURSE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				courseId;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACADEMIC_YEAR_ID")
	@JsonProperty("academicYear") // used to map parent element
	@JsonBackReference
	// @JsonIgnore
	private AcademicYear		academicYear;

	@Column(name = "COURSE_NAME")
	private String				courseName;

	@Column(name = "COURSE_DESC")
	private String				courseDescription;

	@Column(name = "PATTERN_VID")
	Integer						patternVID;

	@Column(name = "BRANCH_VID")
	Integer						branchVID;

	@Column(name = "FE")
	Integer						FE;

	@Column(name = "SE")
	Integer						SE;

	@Column(name = "TE")
	Integer						TE;

	@Column(name = "BE")
	Integer						BE;

	@OneToMany(mappedBy = "courseMaster", cascade = CascadeType.ALL)
	// @JsonManagedReference
	// @JsonIgnore
	private Set<ExamSchedule>	examSchedule		= new HashSet<>();

	@OneToMany(mappedBy = "courseMaster", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// @JsonIgnore
	// @JsonManagedReference
	private Set<TimeTable>		timeTable			= new HashSet<>();

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
	 * @Column(name = "RECORD_STATUS") private String recordStatus;
	 */

	public Integer getCourseId()
	{
		return courseId;
	}

	public void setCourseId(Integer courseId)
	{
		this.courseId = courseId;
	}

	public AcademicYear getAcademicYear()
	{
		return academicYear;
	}

	public void setAcademicYear(AcademicYear academicYear)
	{
		this.academicYear = academicYear;
	}

	public String getCourseName()
	{
		return courseName;
	}

	public void setCourseName(String courseName)
	{
		this.courseName = courseName;
	}

	public String getCourseDescription()
	{
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription)
	{
		this.courseDescription = courseDescription;
	}

	public Integer getPatternVID()
	{
		return patternVID;
	}

	public void setPatternVID(Integer patternVID)
	{
		this.patternVID = patternVID;
	}

	public Integer getBranchVID()
	{
		return branchVID;
	}

	public void setBranchVID(Integer branchVID)
	{
		this.branchVID = branchVID;
	}

	public Set<ExamSchedule> getExamSchedule()
	{
		return examSchedule;
	}

	public void setExamSchedule(Set<ExamSchedule> examSchedule)
	{
		examSchedule.forEach((exam) ->
		{
			exam.setCourseMaster(this);
		});
		this.examSchedule = examSchedule;
	}

	public Set<TimeTable> getTimeTable()
	{
		return timeTable;
	}

	public void setTimeTable(Set<TimeTable> timeTable)
	{
		timeTable.forEach((time) ->
		{
			time.setCourseMaster(this);
		});
		this.timeTable = timeTable;
	}

	public Integer getFE()
	{
		return FE;
	}

	public void setFE(Integer fE)
	{
		FE = fE;
	}

	public Integer getSE()
	{
		return SE;
	}

	public void setSE(Integer sE)
	{
		SE = sE;
	}

	public Integer getTE()
	{
		return TE;
	}

	public void setTE(Integer tE)
	{
		TE = tE;
	}

	public Integer getBE()
	{
		return BE;
	}

	public void setBE(Integer bE)
	{
		BE = bE;
	}

	@Override
	public String toString()
	{
		return "CourseMaster [courseId=" + courseId + ", courseName=" + courseName + ", courseDescription="
				+ courseDescription + ", patternVID=" + patternVID + "]";
	}

}
