package com.zertones.model.DataTransferObjectModel;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zertones.system.utility.JsonDateDeserializer;
import com.zertones.system.utility.JsonDateSerializer;

@JsonInclude(Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class NotificationDTO implements Serializable
{
	private Integer	notificationId;

	private Integer	department;

	private String	notificatiosHeadline;

	private Integer	notificatioStatus;

	private String	notificationDetails;

	private Integer	notificationType;

	private Integer	division;

	private Integer	studentIncharge;

	private Integer	facultyIncharge;

	private Integer	reminder;

	private String	venue;

	private Integer	totalStudent;

	private Integer	totalSendNotices;

	private Integer	groups;

	private Integer	mentor;

	private Integer	postBy;

	@Transient
	private String	postNotice;

	private Integer	comment_count;

	private Integer	like_count;

	private Boolean	like_Status;

	private String	ImgUrl;

	public String getImgUrl()
	{
		return ImgUrl;
	}

	public void setImgUrl(String imgUrl)
	{
		ImgUrl = imgUrl;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date						notificationFromDate	= new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date						notificationToDate;

	private Set<NotificationFilesDTO>	notificationFilesDTO	= new HashSet<>();

	public Integer getNotificationId()
	{
		return notificationId;
	}

	public void setNotificationId(Integer notificationId)
	{
		this.notificationId = notificationId;
	}

	public Integer getDepartment()
	{
		return department;
	}

	public void setDepartment(Integer department)
	{
		this.department = department;
	}

	public String getNotificatiosHeadline()
	{
		return notificatiosHeadline;
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

	public String getNotificationDetails()
	{
		return notificationDetails;
	}

	public void setNotificationDetails(String notificationDetails)
	{
		this.notificationDetails = notificationDetails;
	}

	public Integer getNotificationType()
	{
		return notificationType;
	}

	public void setNotificationType(Integer notificationType)
	{
		this.notificationType = notificationType;
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

	public Integer getGroups()
	{
		return groups;
	}

	public void setGroups(Integer groups)
	{
		this.groups = groups;
	}

	public Integer getMentor()
	{
		return mentor;
	}

	public void setMentor(Integer mentor)
	{
		this.mentor = mentor;
	}

	public Integer getPostBy()
	{
		return postBy;
	}

	public void setPostBy(Integer postBy)
	{
		this.postBy = postBy;
	}

	public String getPostNotice()
	{
		return postNotice;
	}

	public void setPostNotice(String postNotice)
	{
		this.postNotice = postNotice;
	}

	public Integer getComment_count()
	{
		return comment_count;
	}

	public void setComment_count(Integer comment_count)
	{
		this.comment_count = comment_count;
	}

	public Integer getLike_count()
	{
		return like_count;
	}

	public void setLike_count(Integer like_count)
	{
		this.like_count = like_count;
	}

	public Date getNotificationFromDate()
	{
		return notificationFromDate;
	}

	public Boolean getLike_Status()
	{
		return like_Status;
	}

	public void setLike_Status(Boolean like_Status)
	{
		this.like_Status = like_Status;
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

	public Set<NotificationFilesDTO> getNotificationFilesDTO()
	{
		return notificationFilesDTO;
	}

	public void setNotificationFilesDTO(Set<NotificationFilesDTO> notificationFilesDTO)
	{
		this.notificationFilesDTO = notificationFilesDTO;
	}

	@Override
	public String toString()
	{
		return "NotificationDTO [notificationId=" + notificationId + ", department=" + department
				+ ", notificatiosHeadline=" + notificatiosHeadline + ", notificatioStatus=" + notificatioStatus
				+ ", notificationDetails=" + notificationDetails + ", notificationType=" + notificationType
				+ ", division=" + division + ", studentIncharge=" + studentIncharge + ", facultyIncharge="
				+ facultyIncharge + ", reminder=" + reminder + ", venue=" + venue + ", totalStudent=" + totalStudent
				+ ", totalSendNotices=" + totalSendNotices + ", groups=" + groups + ", mentor=" + mentor + ", postBy="
				+ postBy + ", postNotice=" + postNotice + ", comment_count=" + comment_count + ", like_count="
				+ like_count + ", like_Status=" + like_Status + ", notificationFromDate=" + notificationFromDate
				+ ", notificationToDate=" + notificationToDate + ", notificationFilesDTO=" + notificationFilesDTO + "]";
	}

}
