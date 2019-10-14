package com.zertones.model.common;

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
import com.zertones.model.BaseModel;
import com.zertones.system.utility.JsonDateDeserializer;
import com.zertones.system.utility.JsonDateSerializer;

@Entity
@Table(name = "COM_NOTIFICATIONS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "notificationId")
public class Notification extends BaseModel implements java.io.Serializable
{
	private static final long	serialVersionUID	= 1085165961751982130L;

	@Id
	@Column(name = "NOTIFICATION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				notificationId;

	@Column(name = "DEPTMNT")
	private Integer				department;

	@Column(name = "NOTIFICATION_HEADLINE")
	private String				notificatiosHeadline;

	@Column(name = "STATUS_VID")
	private Integer				notificatioStatus;

	@Column(name = "NOTIFICATION_DETAILS")
	private String				notificationDetails;

	@Column(name = "NOTIFICATION_TYPE_VID")
	private Integer				notificationType;

	@Column(name = "NOTIFICATION_FOR_CLASS")
	private Integer				division;

	@Column(name = "EVENT_STU_INCHR")
	private Integer				studentIncharge;

	@Column(name = "EVENT_FACU_INCR")
	private Integer				facultyIncharge;

	@Column(name = "NOTIFICATION_REMINDER")
	private Integer				reminder;

	@Column(name = "VENUE_DTL")
	private String				venue;

	@Column(name = "TOTAL_STUDENT_INVITE")
	private Integer				totalStudent;

	@Column(name = "TOTAL_SUUCESS_NOTICE")
	private Integer				totalSendNotices;

	@Column(name = "GROUP_ID")
	private Integer				groups;

	@Column(name = "MENTOR_ID")
	private Integer				mentor;

	@Column(name = "POST_BY")
	private Integer				postBy;

	// @Transient
	// private String postNotice;

	public Integer getPostBy()
	{
		return postBy;
	}

	public void setPostBy(Integer postBy)
	{
		this.postBy = postBy;
	}

	public Integer getMentor()
	{
		return mentor;
	}

	public void setMentor(Integer mentor)
	{
		this.mentor = mentor;
	}

	public Integer getTotalStudent()
	{
		return totalStudent;
	}

	public void setTotalStudent(Integer totalStudent)
	{
		this.totalStudent = totalStudent;
	}

	public Integer getTotalSendNotices()
	{
		return totalSendNotices;
	}

	public void setTotalSendNotices(Integer totalSendNotices)
	{
		this.totalSendNotices = totalSendNotices;
	}

	public Integer getReminder()
	{
		return reminder;
	}

	public void setReminder(Integer reminder)
	{
		this.reminder = reminder;
	}

	public String getVenue()
	{
		return venue;
	}

	public void setVenue(String venue)
	{
		this.venue = venue;
	}

	@Column(name = "NOTIFICATION_FROM_DT")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date					notificationFromDate;

	@Column(name = "NOTIFICATION_TO_DT")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date					notificationToDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "notification", cascade = CascadeType.ALL)
	private Set<NotificationFiles>	notificationFiles	= new HashSet<>();

	public Integer getNotificationId()
	{
		return notificationId;
	}

	public void setNotificationId(Integer notificationId)
	{
		this.notificationId = notificationId;
	}

	public String getNotificatiosHeadline()
	{
		return notificatiosHeadline;
	}

	public String getNotificationDetails()
	{
		return notificationDetails;
	}

	public void setNotificationDetails(String notificationDetails)
	{
		this.notificationDetails = notificationDetails;
	}

	public void setNotificatiosHeadline(String notificatiosHeadline)
	{
		this.notificatiosHeadline = notificatiosHeadline;
	}

	public Integer getNotificatioStatus()
	{
		return notificatioStatus;
	}

	public void setNotificatioStatus(Integer notificatioStatus)
	{
		this.notificatioStatus = notificatioStatus;
	}

	public Date getNotificationFromDate()
	{
		return notificationFromDate;
	}

	public void setNotificationFromDate(Date notificationFromDate)
	{
		this.notificationFromDate = notificationFromDate;
	}

	public Date getNotificationToDate()
	{
		return notificationToDate;
	}

	public void setNotificationToDate(Date notificationToDate)
	{
		this.notificationToDate = notificationToDate;
	}

	public Set<NotificationFiles> getNotificationFiles()
	{
		return notificationFiles;
	}

	public void setNotificationFiles(Set<NotificationFiles> notificationFiles)
	{
		this.notificationFiles = notificationFiles;
	}

	public Integer getNotificationType()
	{
		return notificationType;
	}

	public void setNotificationType(Integer notificationType)
	{
		this.notificationType = notificationType;
	}

	public Integer getDepartment()
	{
		return department;
	}

	public void setDepartment(Integer department)
	{
		this.department = department;
	}

	public Integer getDivision()
	{
		return division;
	}

	public void setDivision(Integer division)
	{
		this.division = division;
	}

	public Integer getStudentIncharge()
	{
		return studentIncharge;
	}

	public void setStudentIncharge(Integer studentIncharge)
	{
		this.studentIncharge = studentIncharge;
	}

	public Integer getFacultyIncharge()
	{
		return facultyIncharge;
	}

	public void setFacultyIncharge(Integer facultyIncharge)
	{
		this.facultyIncharge = facultyIncharge;
	}

	public Integer getGroups()
	{
		return groups;
	}

	public void setGroups(Integer groups)
	{
		this.groups = groups;
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
	 * public String getPostNotice() { return postNotice; }
	 *
	 * public void setPostNotice(String postNotice) { this.postNotice = postNotice;
	 * }
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
	@Override
	public String toString()
	{
		return "Notification [notificationId=" + notificationId + ", notificatiosHeadline=" + notificatiosHeadline
				+ ",  notificatioStatus=" + notificatioStatus + ", notificationType=" + notificationType + ", groups="
				+ groups + ", notificationFromDate=" + notificationFromDate + ", notificationToDate="
				+ notificationToDate + ",department=" + department + ",notificationDetails=" + notificationDetails
				+ ", studentIncharge=" + studentIncharge + ", facultyIncharge=" + facultyIncharge
				+ ", totalSendNotices=" + totalSendNotices + ", totalStudent=" + totalStudent + ", notificationFiles="
				+ notificationFiles + "]";
	}
}
