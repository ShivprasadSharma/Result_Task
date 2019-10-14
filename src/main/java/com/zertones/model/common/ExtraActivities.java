package com.zertones.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zertones.model.BaseModel;
import com.zertones.model.ComClientName;

@Entity
@Table(name = "EXTRA_CURICULAM_ACTIVITIES")
@JsonInclude(Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class ExtraActivities extends BaseModel implements Serializable
{

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACTIVITY_ID")
	private Integer				activity_id;

	@Column(name = "EVENT_NAME")
	private String				eventname;

	@Column(name = "DATE_OF_EVENT")
	private String				dateOfEvent;

	@Column(name = "TO_DATE")
	private String				toDate;

	@Column(name = "EVENT_TYPE")
	private Integer				eventType;

	@Column(name = "EVENT_LEVEL")
	private String				levelOfEvent;

	@Column(name = "EVENT_ORGANIZER")
	private String				organizer;

	@Column(name = "CLASS_NAME")
	private String				className;

	@Column(name = "DURATION")
	private String				duration;

	@Column(name = "EVENT_VENUE")
	private String				venue;

	@Column(name = "PRIZE_WON")
	private String				prizewon;

	@Column(name = "COORDINATE_NAME")
	private String				coordinateName;

	@Column(name = "SPONSOR")
	private String				sponsor;

	@Column(name = "REMARK")
	private String				remark;

	@Column(name = "PLACEMENT")
	private boolean				placement;

	@Column(name = "HIGHER_STUDY")
	private boolean				higherstudy;

	@Column(name = "BUSSINESS")
	private boolean				bussiness;

	@Column(name = "CHCEK_STATUS")
	private boolean				status;

	@Column(name = "DEPARTMENT")
	private String				dept;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID", nullable = false)
	private ComClientName		comClientName;

	public Integer getActivity_id()
	{
		return activity_id;
	}

	public void setActivity_id(Integer activity_id)
	{
		this.activity_id = activity_id;
	}

	public String getEventname()
	{
		return eventname;
	}

	public void setEventname(String eventname)
	{
		this.eventname = eventname;
	}

	public String getDateOfEvent()
	{
		return dateOfEvent;
	}

	public void setDateOfEvent(String dateOfEvent)
	{
		this.dateOfEvent = dateOfEvent;
	}

	public String getToDate()
	{
		return toDate;
	}

	public void setToDate(String toDate)
	{
		this.toDate = toDate;
	}

	public Integer getEventType()
	{
		return eventType;
	}

	public void setEventType(Integer eventType)
	{
		this.eventType = eventType;
	}

	public String getLevelOfEvent()
	{
		return levelOfEvent;
	}

	public void setLevelOfEvent(String levelOfEvent)
	{
		this.levelOfEvent = levelOfEvent;
	}

	public String getOrganizer()
	{
		return organizer;
	}

	public void setOrganizer(String organizer)
	{
		this.organizer = organizer;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public String getDuration()
	{
		return duration;
	}

	public void setDuration(String duration)
	{
		this.duration = duration;
	}

	public String getVenue()
	{
		return venue;
	}

	public void setVenue(String venue)
	{
		this.venue = venue;
	}

	public String getPrizewon()
	{
		return prizewon;
	}

	public void setPrizewon(String prizewon)
	{
		this.prizewon = prizewon;
	}

	public String getCoordinateName()
	{
		return coordinateName;
	}

	public void setCoordinateName(String coordinateName)
	{
		this.coordinateName = coordinateName;
	}

	public String getSponsor()
	{
		return sponsor;
	}

	public void setSponsor(String sponsor)
	{
		this.sponsor = sponsor;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public boolean isPlacement()
	{
		return placement;
	}

	public void setPlacement(boolean placement)
	{
		this.placement = placement;
	}

	public boolean isHigherstudy()
	{
		return higherstudy;
	}

	public void setHigherstudy(boolean higherstudy)
	{
		this.higherstudy = higherstudy;
	}

	public boolean isBussiness()
	{
		return bussiness;
	}

	public void setBussiness(boolean bussiness)
	{
		this.bussiness = bussiness;
	}

	public boolean isStatus()
	{
		return status;
	}

	public void setStatus(boolean status)
	{
		this.status = status;
	}

	public String getDept()
	{
		return dept;
	}

	public void setDept(String dept)
	{
		this.dept = dept;
	}

	public ComClientName getComClientName()
	{
		return comClientName;
	}

	public void setComClientName(ComClientName comClientName)
	{
		this.comClientName = comClientName;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	@Override
	public String toString()
	{
		return "ExtraActivities [activity_id=" + activity_id + ", eventname=" + eventname + ", dateOfEvent="
				+ dateOfEvent + ", toDate=" + toDate + ", eventType=" + eventType + ", levelOfEvent=" + levelOfEvent
				+ ", organizer=" + organizer + ", className=" + className + ", duration=" + duration + ", venue="
				+ venue + ", prizewon=" + prizewon + ", coordinateName=" + coordinateName + ", sponsor=" + sponsor
				+ ", remark=" + remark + ", placement=" + placement + ", higherstudy=" + higherstudy + ", bussiness="
				+ bussiness + ", status=" + status + ", dept=" + dept + ", comClientName=" + comClientName
				+ ", getActivity_id()=" + getActivity_id() + ", getEventname()=" + getEventname()
				+ ", getDateOfEvent()=" + getDateOfEvent() + ", getToDate()=" + getToDate() + ", getEventType()="
				+ getEventType() + ", getLevelOfEvent()=" + getLevelOfEvent() + ", getOrganizer()=" + getOrganizer()
				+ ", getClassName()=" + getClassName() + ", getDuration()=" + getDuration() + ", getVenue()="
				+ getVenue() + ", getPrizewon()=" + getPrizewon() + ", getCoordinateName()=" + getCoordinateName()
				+ ", getSponsor()=" + getSponsor() + ", getRemark()=" + getRemark() + ", isPlacement()=" + isPlacement()
				+ ", isHigherstudy()=" + isHigherstudy() + ", isBussiness()=" + isBussiness() + ", isStatus()="
				+ isStatus() + ", getDept()=" + getDept() + ", getComClientName()=" + getComClientName()
				+ ", getCreatedDate()=" + getCreatedDate() + ", getCreatedBy()=" + getCreatedBy()
				+ ", getUpdatedDate()=" + getUpdatedDate() + ", getUpdatedBy()=" + getUpdatedBy()
				+ ", getRecordStatus()=" + getRecordStatus() + ", getInstituteId()=" + getInstituteId()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
