package com.zertones.model.sims;

import java.util.Arrays;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.crypto.codec.Base64;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zertones.model.BaseModel;
import com.zertones.model.ComClientName;
import com.zertones.model.master.MarkSheet;

@Entity
// @Audited
@Table(name = "SIMS_STUDENT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "studentId")
public class Student extends BaseModel
{

	@Id
	@Column(name = "STUDENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer			studentId;

	@Column(name = "REGISTRATION_NO")
	private String			registrationNo;

	@Column(name = "ROLL_NO")
	private String			rollNo;

	@Column(name = "UNIVERSITY_ENROLL_NO")
	private String			universityEnrollNo;

	@Column(name = "BRANCH", length = 5)
	private Integer			branch;

	@Column(name = "YEAR", length = 5)
	private Integer			year;

	@Column(name = "GRADE", length = 5)
	private Integer			grade;

	@Column(name = "STANDARD", length = 5)
	private Integer			standard;

	@Column(name = "COURSETYPE")
	private String			CourseType;

	@Column(name = "HSC_DIP_INSTITUTE")
	private String			hscinstituteName;

	@Column(name = "HSC_DIP_BOARD")
	private String			hscboard;

	@Column(name = "HSC_Aggregate")
	private String			hscaggregate;

	@Column(name = "SSC_BOARD")
	private String			sscboard;

	@Column(name = "SSC_AGGREGATE")
	private String			sscaggregate;

	@Column(name = "SSC_INSTITUTE")
	private String			sscschoolName;

	@Column(name = "PROFILE_PIC", length = 1000000)
	private byte[]			profileImage;

	@Column(name = "BATCH")
	private String			batch;

	@Column(name = "PASSOUT_YEAR")
	private String			passoutYear;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "CLIENT_ID", nullable = false)
	private ComClientName	comClientName;

	@OneToMany(mappedBy = "student")
	private Set<MarkSheet>	sheet;

	public String getPassoutYear()
	{
		return passoutYear;
	}

	public void setPassoutYear(String passoutYear)
	{
		this.passoutYear = passoutYear;
	}

	public String getBatch()
	{
		return batch;
	}

	public void setBatch(String batch)
	{
		this.batch = batch;
	}

	public Set<MarkSheet> getSheet()
	{
		return sheet;
	}

	public void setSheet(Set<MarkSheet> sheet)
	{
		this.sheet = sheet;
	}

	public Integer getStudentId()
	{
		return studentId;
	}

	public void setStudentId(Integer studentId)
	{
		this.studentId = studentId;
	}

	public String getRegistrationNo()
	{
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo)
	{
		this.registrationNo = registrationNo;
	}

	public String getRollNo()
	{
		return rollNo;
	}

	public void setRollNo(String rollNo)
	{
		this.rollNo = rollNo;
	}

	public String getUniversityEnrollNo()
	{
		return universityEnrollNo;
	}

	public void setUniversityEnrollNo(String universityEnrollNo)
	{
		this.universityEnrollNo = universityEnrollNo;
	}

	public Integer getGrade()
	{
		return grade;
	}

	public Integer getBranch()
	{
		return branch;
	}

	public void setBranch(Integer branch)
	{
		this.branch = branch;
	}

	public Integer getYear()
	{
		return year;
	}

	public void setYear(Integer year)
	{
		this.year = year;
	}

	public void setGrade(Integer grade)
	{
		this.grade = grade;
	}

	public Integer getStandard()
	{
		return standard;
	}

	public void setStandard(Integer standard)
	{
		this.standard = standard;
	}

	public ComClientName getComClientName()
	{
		return comClientName;
	}

	public void setComClientName(ComClientName comClientName)
	{
		this.comClientName = comClientName;
	}

	public byte[] getProfileImage()
	{
		return profileImage;
	}

	public void setProfileImage(byte[] profileImage)
	{
		this.profileImage = profileImage;
	}

	public String getCourseType()
	{
		return CourseType;
	}

	public void setCourseType(String courseType)
	{
		CourseType = courseType;
	}

	public String getHscinstituteName()
	{
		return hscinstituteName;
	}

	public void setHscinstituteName(String hscinstituteName)
	{
		this.hscinstituteName = hscinstituteName;
	}

	public String getHscboard()
	{
		return hscboard;
	}

	public void setHscboard(String hscboard)
	{
		this.hscboard = hscboard;
	}

	public String getHscaggregate()
	{
		return hscaggregate;
	}

	public void setHscaggregate(String hscaggregate)
	{
		this.hscaggregate = hscaggregate;
	}

	public String getSscboard()
	{
		return sscboard;
	}

	public void setSscboard(String sscboard)
	{
		this.sscboard = sscboard;
	}

	public String getSscaggregate()
	{
		return sscaggregate;
	}

	public void setSscaggregate(String sscaggregate)
	{
		this.sscaggregate = sscaggregate;
	}

	public String getSscschoolName()
	{
		return sscschoolName;
	}

	public void setSscschoolName(String sscschoolName)
	{
		this.sscschoolName = sscschoolName;
	}

	@Override
	public String toString()
	{
		return "Student [studentId=" + studentId + ", registrationNo=" + registrationNo + ", rollNo=" + rollNo
				+ ", universityEnrollNo=" + universityEnrollNo + ", branch=" + branch + ", year=" + year + ", grade="
				+ grade + ", standard=" + standard + ", CourseType=" + CourseType + ", hscinstituteName="
				+ hscinstituteName + ", hscboard=" + hscboard + ", hscaggregate=" + hscaggregate + ", sscboard="
				+ sscboard + ", sscaggregate=" + sscaggregate + ", sscschoolName=" + sscschoolName + ", profileImage="
				+ Arrays.toString(profileImage) + ", comClientName=" + comClientName + ", sheet=" + sheet + "]";
	}

	public String getByteArrayString()
	{
		if (this.profileImage != null)
		{
			return new String(Base64.encode(this.profileImage));
		} else
		{
			return "";
		}
	}

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

}
