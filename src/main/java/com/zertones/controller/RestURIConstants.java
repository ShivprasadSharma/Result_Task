package com.zertones.controller;

public interface RestURIConstants
{
	// Notification Service URL
	String						ADD_NOTIFICATION				= "/api/notification/add";
	String						REQUIRED_DATA_FOR_ADD_NOTICE	= "/api/notification/data/{cid}";
	String						DEPARTMENTAL_NOTIFICATIONS		= "/api/departmental/notifications/{id}";
	String						GET_NOTIFICATION_BY_TYPE		= "/api/departmental/notifications/{depid}/{type}";
	String						NOTIFICATION_COUNT				= "/api/departmental/notifications/count/{id}";
	String						GET_NOTIFICATION				= "/api/notification/{id}";
	String						DELETE_NOTIFICATION				= "/api/notification/delete/{id}";
	// Device Service URL
	String						ADD_DEVICE						= "/api/device/add";
	String						LIST_DEVICES					= "/api/device/list";
	String						GET_DEVICE						= "/api/device/{id}";
	String						DELETE_DEVICE					= "/api/device/delete/{id}";

	// login API
	String						USER_LOGIN						= "/user/login";
	String						LOGOUT							= "/user/logout";
	String						SET_PASS						= "/api/sims/student/set";
	String						FORGOT_PASSWORD					= "/api/identify/forgot";

	// Common Modoles
	String						CONTACT_US						= "/api/common/contactus/contact/{instId}";
	String						VIDEO_URL						= "/api/common/video/url/{instId}";

	// Common Service URL
	String						GET_APP_MENU					= "/api/common/menu";
	String						GET_APP_PERTICULAR_MENU_DTL		= "/api/common/menu/{id}/{menuid}";
	String						SIDERBAR_MENU_LIST				= "/api/common/menu/navbar/{instId}";

	String						GET_LIFE_OF_CAMPUS				= "/api/common/app/lifeOnCampus";
	String						GET_INSTITUTE_INFO				= "/api/common/getinstituteinfo";
	String						ADD_INSTITUTE_INFO_MST			= "/api/common/master/add";
	String						GET_INSTITUTE_INFO_MST			= "/api/common/master";
	String						APP_INSTITUTE_INFO				= "/api/common/app/master";
	String						APP_COURSE_MSTR					= "/api/common/app/courses";

	String						APP_ABOUT_US_INFO				= "/api/common/app/aboutus";
	String						GET_INSTITUTE_MST_DTL			= "/api/common/master/{masterid}";
	String						MASTER_GET_DETAILS				= "/api/common/masterdtl/{masterid}";
	String						SAVE_TIME_TABLE					= "/api/common/timetable/save";
	String						SAVE_EXAM_SCHEDULE				= "/api/common/examschedule/save";
	String						SAVE_COURSE_MASTER				= "/api/common/coursemaster/save";
	// String GET_COURSE_MASTER = "/api/common/coursemaster";
	String						SAVE_ACADEMIC_YEAR				= "/api/common/academicyear/save";
	// String GET_TIME_TABLE = "/api/common/timetable";
	String						GET_EXAM_SCHEDULE				= "/api/common/examschedule";
	String						GET_ACADEMIC_YEAR				= "/api/common/academicyear";
	String						GET_DIRECTORY					= "/api/common/directory";

	String						GET_DEPARTMENT					= "/signup/common/department";
	// implemented
	String						GET_TEACHR_PROFIE				= "/api/common/teacherprofile";
	String						GET_TEACHR_PROFIE_ById			= "/api/common/teacherprofileByid/{teacherId}";

	// Common Service URL
	String						GET_MENU_LIST_BY_ID				= "/api/app/master/{masterid}";
	String						GET_APP_MENU_LIST				= "/api/app/common/master/{listid}";
	String						GET_APP_MASTER_LIST				= "/api/app/master";

	String						SIGNUP_STUDENT					= "/signup/student";
	String						SIGNUP_STAFF					= "/signup/staff";

	String						GET_EVENT_FOR_CALENDAR			= "/api/common/service/event/list";
	String						GET_ACHIVEMENT_FOR_CALENDAR		= "/api/common/service/achivement/list";
	String						GET_UPCOMING_EVENT				= "/api/common/service/event/upcoming";
	String						GET_GROUP_LIST_BY_USER			= "/api/common/groups/listofgroupbyuser/{userclientid}";
	String						GET_GROUP_LIST_BY_TYPE			= "/api/common/groups/list/group/type/{type}/{cid}";
	String						GET_GROUP_DETAILS				= "/api/common/groups/getgroupdetail/{groupid}";
	String						REST_GET_GROUP_NOTICE			= "/api/notification/group/{groupid}";
	String						GET_ACHIVEMENT					= "/api/common/service/achivement/list";

	// group
	String						CREATE_NEW_GROUP				= "/api/common/groups/create";
	String						PUT_GROUP_DELETE				= "/api/common/groups/delete/{gid}";
	String						UPDATE_GROUP					= "/api/common/group/update/{gid}";
	String						ADD_GROUP_NOTICE				= "/api/common/group/notification/save";
	String						GET_GROUP_MEMBER				= "/api/common/group/member/list";
	String						ADD_GROUP_MEMBER				= "/api/common/group/member/add";
	String						GROUP_NOTICES					= "/api/common/group/notifiation/{gid}";

	String						LIKE							= "/api/common/notification/like";
	String						COMMENT_LIST					= "/api/common/notification/comment/{noticeId}";
	String						COMMENT_INSERT					= "/api/common/notification/comment/add";

	// Create Group On Mobile
	String						STAFFDATA_FOR_GROUP				= "/api/common/group/staffdata";
	String						STUD_CREATE_GROUP				= "/api/common/group/student/creategroup/{id}";
	String						JOIN_GROUP_LIST					= "/api/common/group/join/list/{id}";
	String						ADD_GROUP_STUDENT_MEMBER		= "/api/common/group/member/add/{id}";
	// String LIST_DEPARTMENT = "/api/common/deprtment/list";

	// public static final String LIST_DEPARTMENT =
	// "/api/common/deprtment/list";

	// Feedback
	String						GET_FEEDBACK_DATA_BY_STUDID		= "api/common/feedbackdata/{studId}";
	String						ADD_FEEDBACK_VOTE_BY_STUD		= "api/common/feedbackdata/vote";

	String						GRIEVANCE_lIST					= "/api/common/grievance/list/{clientId}";
	String						GRIEVANCE_ADD					= "/api/common/grievance/add";
	String						GRIEVANCE_REOPEN				= "/api/common/grievance/reopen/{reopenRP}/{grid}";

	// poll
	String						POLL_LIST						= "/api/common/poll/list/{clientId}";
	String						GET_POLL_OPTIONS				= "/api/common/poll/option/{pollId}";
	String						ADD_POLL_ANSWER					= "/api/common/poll/answer/{pollId}/{optionId}/{clientId}";
	String						GET_POLL_PERCENTAGE				= "/api/common/poll/percentage/{pollId}";
	String						REST_ADD_POLL					= "/api/taskforce/serives/poll/add";
	String						REST_POLL_LIST					= "/api/taskforce/serives/poll/list";
	String						REST_POLL_DELETE				= "/api/taskforce/serives/poll/delete/{pollId}";
	String						REST_POLL_UPDATE				= "/api/taskforce/serives/poll/update";

	String						GET_ACADEMIC_SUBJECT			= "/api/common/attendance/getSubject/{empno}";
	String						GET_ACADEMIC_STUDENT_LIST		= "/api/common/attendance/batch/student";
	String						ADD_ATTENDACE					= "/api/common/attendance/add";

	String						GET_BATCH						= "/api/common/attendance/batch/{subid}/{division}";
	String						ADD_PUNCH						= "/api/common/attendance/punch/add";
	String						CHECK_PUNCH						= "/api/common/attendance/punch/{clientID}";

	String						TERMS_AND_CONDITION				= "/api/common/terms/condition/{id}";

	String						APP_PLACEMENT					= "/api/common/app/placement";

	String						HTML_PAGE_MENU					= "/api/common/htmlpages/{id}/{menuid}";

	public static final String	GET_STUDENT_EDUCATION_PRE_INFO	= "/api/common/student/educationPreInfo/{cid}";
	public static final String	GET_STUDENT_EDUCATION_INFO		= "/api/common/student/education/{cid}";
	public static final String	REMOVE_STUDENT_EDUCATION_INFO	= "/api/common/student/education/remove/{rid}";

	public static final String	ADD_STUDENT_EDUCATION_INFO		= "/api/common/student/education/add";

	/* ..................TPO....................... */

	public static final String	UPCOMING_DRIVE					= "/api/tpo/drive/list/upcoming";
	public static final String	PAST_DRIVE						= "/api/tpo/drive/list/past";
	public static final String	DRIVE_DETAILS					= "/api/tpo/drive/detail/{id}";
	public static final String	COMPANY_CRITERIA_CHECK			= "/api/tpo/drive/company/check/criteria/{clientId}/{driveId}";
	public static final String	COMPANY_APPLAY					= "/api/tpo/drive/company/applay/{clientId}/{driveId}";

	public static final String	GET_CURRENT_ATTENDANCE			= "/api/common/student/current/attendance/{cid}";
	public static final String	SAVE_STUDENT_EXTRA_ACTIVITY		= "/api/common/student/createExtraActivity";
	public static final String	GET_STUDENT_EXTRA_ACTIVITY		= "/api/common/student/createExtraActivity/{cid}/{type}";
	public static final String	GET_OFFER_LATEER_TO_STUDENT		= "/api/common/student/sendofferlatter/{studId}/{driveID}";
	public static final String	ACEEPT_OFFER_LATTER_STUD		= "/api/common/student/acceptofferlatter/{studId}/{driveID}";

	public static final String	GET_STUDENT_INFO_FOR_PARENT		= "/api/common/student/infodtl/forparent/{cid}";

	public static final String	GET_INTERNSHIP_STUDENT_LIST		= "/api/taskforce/service/studentinternship/list";
	public static final String	GET_ONCAMPUS_PLACED_LIST		= "/api/taskforce/service/studentplaced/list";
	public static final String	GET_CHECK_FUTURE_INTEREST_LIST	= "/api/taskforce/service/{clientId}/{typeId}";
	public static final String	SAVE_FUTURE_INTEREST_LIST		= "/api/common/student/saveFutureList";

	// student and college count
	public static final String	TOTAL_COLLGE_COUNT				= "/api/common/student/service/collegecount";

	public static final String	TOTAL_DEPARTMENTC_COUNT			= "/api/common/student/service/deptcount";
	public static final String	GET_STUDNET_CARRIER_LIST		= "/api/taskforce/services/carrierlist/{eventId}/{typeId}";

	/*
	 * ......................................Group Poll ............................
	 */
	public static final String	POLL_GROUP_LIST					= "/api/common/grouppoll/list/{clientId}/{g_id}";
	public static final String	GET_GROUP_POLL_OPTIONS			= "/api/common/grouppoll/option/{pollId}";
	public static final String	ADD_GROUP_POLL_ANSWER			= "/api/common/grouppoll/answer/{pollId}/{optionId}/{clientId}";
	public static final String	GET_GROUP_POLL_PERCENTAGE		= "/api/common/grouppoll/percentage/{pollId}";
	public static final String	GROUP_POLL_LIST_TEACHER			= "/api/taskforce/serives/grppolltea/list/{clientId}/{g_id}";
	public static final String	ADD_REST_GROUP_POLL				= "/api/taskforce/serives/grouppoll/add";
	public static final String	REST_GROUP_POLL_DELETE			= "/api/taskforce/serives/grouppoll/delete/{pollId}";
	public static final String	REST_GROUP_POLL_UPDATE			= "/api/taskforce/serives/grouppoll/update";

	public static final String	GET_OFFCAMPUS_PLACED_LIST		= "/api/taskforce/service/offCampus/studentplaced/list";
	public static final String	COLLEGE_DEPT_FEE				= "/api/common/student/service/studentdept/feedtl";
	public static final String	COLLEGEFEEDETAILS				= "/api/common/student/service/collegefee";
	public static final String	GETSTUDENTREMARKLIST			= "/api/common/student/service/remarks/{cid}";

}
