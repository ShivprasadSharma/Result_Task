package com.zertones.service;

import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

public class Constants
{
	public static final String		RECORD_STATUS					= "recordStatus";
	public static final String		ACTIVE_RECORD_STATUS			= "A";
	public static final String		DELETED_RECORD_STATUS			= "D";
	public static final String		CURRENT_NOTICES					= "0";
	public static final String		PAST_NOTICES					= "1";
	public static final String		UPCOMING_NOTICE					= "2";

	public static final String		INSTITUTE_VARIABLE_NAME			= "instituteId";

	public static final Projection	NOTIFICATION_PROJECTION_LIST	= Projections.projectionList()
			.add(Property.forName("notificationId"), "notificationId")
			.add(Property.forName("notificatiosHeadline"), "notificatiosHeadline")
			.add(Property.forName("notificationDetails"), "notificationDetails")
			.add(Property.forName("department"), "department")
			.add(Property.forName("notificationType"), "notificationType").add(Property.forName("division"), "division")
			.add(Property.forName("notificationFromDate"), "notificationFromDate")
			.add(Property.forName("notificationToDate"), "notificationToDate")
			.add(Property.forName("studentIncharge"), "studentIncharge")
			.add(Property.forName("totalStudent"), "totalStudent")
			.add(Property.forName("totalSendNotices"), "totalSendNotices")
			.add(Property.forName("facultyIncharge"), "facultyIncharge").add(Property.forName("venue"), "venue")
			.add(Property.forName("totalStudent"), "totalStudent")
			.add(Property.forName("totalSendNotices"), "totalSendNotices").add(Property.forName("groups"), "groups")
			.add(Property.forName("mentor"), "mentor").add(Property.forName("postBy"), "postBy");

	public static final Projection	ComClientName_POJECTION			= Projections.projectionList()
			.add(Projections.property("ClientName.id"), "id")
			.add(Projections.property("ClientName.firstName"), "firstName")
			.add(Projections.property("ClientName.lastName"), "lastName")
			.add(Projections.property("ClientName.emailId"), "emailId")
			.add(Projections.property("ClientName.contactNos"), "contactNos")
			.add(Projections.property("ClientName.imgUrl"), "imgUrl");
	public static final Projection	BATCHPROJECTION					= Projections.projectionList()
			.add(Property.forName("studId1"), "STUD_ID1").add(Property.forName("studId2"), "STUD_ID2")
			.add(Property.forName("studId3"), "STUD_ID3").add(Property.forName("studId4"), "STUD_ID4")
			.add(Property.forName("studId5"), "STUD_ID5").add(Property.forName("studId6"), "STUD_ID6")
			.add(Property.forName("studId7"), "STUD_ID7").add(Property.forName("studId8"), "STUD_ID8")
			.add(Property.forName("studId9"), "STUD_ID9").add(Property.forName("studId10"), "STUD_ID10")
			.add(Property.forName("studId11"), "STUD_ID11").add(Property.forName("studId12"), "STUD_ID12")
			.add(Property.forName("studId13"), "STUD_ID13").add(Property.forName("studId14"), "STUD_ID14")
			.add(Property.forName("studId15"), "STUD_ID15").add(Property.forName("studId16"), "STUD_ID16")
			.add(Property.forName("studId17"), "STUD_ID17").add(Property.forName("studId18"), "STUD_ID18")
			.add(Property.forName("studId19"), "STUD_ID19").add(Property.forName("studId20"), "STUD_ID20")
			.add(Property.forName("studId21"), "STUD_ID21").add(Property.forName("studId22"), "STUD_ID22")
			.add(Property.forName("studId23"), "STUD_ID23").add(Property.forName("studId24"), "STUD_ID24")
			.add(Property.forName("studId25"), "STUD_ID25").add(Property.forName("studId26"), "STUD_ID26");

}
