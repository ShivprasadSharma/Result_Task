package com.zertones.controller;

public interface WebURIConstants
{
	// Master Service URL
	// String ADMIN_INDEX = "/";
	String	CHECK_ATHENTICATION					= "/web/taskforce/master/athe";
	String	MASTER_LIST							= "/web/taskforce/master";
	String	MASTER_GET							= "/web/taskforce/master/{masterid}";
	String	MASTER_GET_DETAILS					= "/web/taskforce/masterdtls/{masterid}";
	String	MASTER_DETAIL_GET					= "/web/taskforce/masterdtl/{masterdtlid}";
	String	ADD_MASTER							= "/web/taskforce/master/save";
	String	ADD_MASTER_DTL						= "/web/taskforce/masterdtl/save";
	String	BLANK_MASTER						= "/web/taskforce/master/blank";
	String	BLANK_MASTER_DTL					= "/web/taskforce/masterdtl/blank";

	// Time Table
	String	SET_TIME_TABLE						= "/web/taskforce/service/timeTable";
	String	GET_TIME_TABLE						= "/web/taskforce/service/timeTable/{year}";

	String	LOGOUT								= "/logout";
	String	BLANK_USER							= "/web/taskforce/user/blank/{id}";
	String	GET_SORTED_STAFF					= "/web/taskforce/user/blank/staff";
	String	GET_SORTED_STUD						= "/web/taskforce/user/blank/stud";
	String	BLANK_USER_DATA						= "/web/taskforce/user/group/d";
	String	USER_LIST							= "/web/taskforce/user";
	String	USER_SAVE							= "/web/taskforce/user/save";
	String	USER_GET							= "/web/taskforce/user/{userId}";
	String	REMOVE_USER							= "/web/taskforce/deluser/{userId}";

	// Notification Service URL
	String	BLANK_NOTICE_FORM					= "/web/taskforce/service/notification";
	String	ADD_NOTIFICATION					= "/web/taskforce/service/notification/add";
	String	GET_NOTIFICATION					= "/web/taskforce/service/notification/{id}";
	String	GET_NOTICE							= "/web/taskforce/service/notifications/update/{id}";
	String	GET_NOTIFICATION_BY_DATE			= "/web/taskforce/service/notification/calendar";

	// String GET_NOTIFICATION_BY_USER =
	// "/web/taskforce/service/notification/{user}";
	String	IDENTIFIRE							= "/web/taskforce/services";
	String	LIST_NOTIFICATIONS					= "/web/taskforce/service/notification/list";
	String	LIST_NOTIFICATION_BY_DEP			= "/web/taskforce/service/notification/{depid}/{type}";
	String	GET_PAST_NOTIFICATIONS				= "/web/taskforce/service/notification/past/{depid}/{type}";
	// String GET_PAST_NOTIFICATION_BY_DEP =
	// "/web/taskforce/service/pastnotice/notification/{depid}/{type}";
	String	DELETE_NOTIFICATION					= "/web/taskforce/service/notification/delete/{id}";
	String	UPDATE_NOTIFICATION					= "/web/taskforce/service/notification/update";
	// Parent NotificationService URI
	String	PARENT_NOTIFICATIONS				= "/web/taskforce/service/notification/Parentlist";

	// User Profile Setting
	String	USER_PROFIEL						= "/web/taskforce/user/profile";
	String	GET_STAFF_BY_TYPE					= "/web/taskforce/stafflist/{type}";

	// Calender
	String	GET_CALENDER						= "/web/taskforce/calender";
	// search Teacher List
	String	SEARCH_TEACHER						= "/web/taskforce/service/notice/{query}";

	// Chat URI
	String	CHAT_PAGE							= "/web/taskforce/chatList/{viewId}";
	String	CHAT_LIST							= "/web/taskforce/chat/{id}";
	String	VIEW_CHAT							= "/web/taskforce/chat/view";
	String	CHATLIST_BY_ID						= "/web/taskforce/chatList/getMsg/{fromId}/{uid}";
	String	SAVE_CHAT							= "/web/taskforce/chatList/save";

	// Attendance
	String	GET_ADD_USER						= "/web/taskforce/services/add";
	String	SAVE_ADD_USR_FILE					= "/web/taskforce/service/save";

	String	GET_MONTHLY_ATTENDANCE				= "/web/taskforce/student/monthly/attendance/{staff_sub_id}";
	String	SAVE_STUDENT_ATTENDANCE				= "/web/taskforce/student/attendance/save";

	String	NOTICE_FILE							= "/web/taskforce/serives/notice/uploadfile";
	String	UPLOAD_NOTICE_FILE					= "/web/taskforce/service/notice/fileupload";

	String	GET_EVENT							= "/web/taskforce/service/event/list";

	// String ALL_GROUP_LIST = "/web/taskforce/allgroups";

	String	CREATE_GROUP						= "/web/taskforce/group/create";
	String	CREATE_SUBJECT_GROUP				= "/web/taskforce/subjectgroup/create";
	String	CREATE_CLASS_GROUP					= "/web/taskforce/classgroup/create";

	String	GROUP_LIST							= "/web/taskforce/group";

	String	GROUP_DETAILS						= "/web/taskforce/groupdetails/{gid}";

	String	BLANK_NOTICE_FORM_FOR_GROUP			= "/web/taskforce/notice/blankGroupNotice/{id}";

	String	BLANK_NOTICE_MENTOR					= "/web/taskforce/notice/blankMentorNotice/{id}";

	String	ADD_GROUP_NOTICE					= "/web/taskforce/group/notice/save";

	String	GET_GROUP_MEMBER					= "/web/taskforce/group/memberlist/{id}";

	String	GET_MENTOR_MEMBER					= "/web/taskforce/mentor/students/{id}/{sid}";

	String	ADD_MENTOR_MEMBER					= "/web/taskforce/mentor/Member";

	String	ADD_GROUP_MEMBER					= "/web/taskforce/group/addGroupMember";

	String	GROUP_INFO							= "/web/taskforce/group/info/{id}";

	String	UPDATE_GROUP						= "/web/taskforce/group/update";

	String	DELETE_GROUP						= "/web/taskforce/group/delete/{id}";

	String	REMOVE_MEMBER_FORM					= "/web/taskforce/group/removeMember/{id}";

	String	REMOVE_MEMBER						= "/web/taskforce/group/removeGroupMember";

	String	SET_PASS							= "/web/taskforce/sims/staff/set";

	String	FORGOT_WEB_PASS						= "/identify";

	String	SET_PASS_FROM_MAIL					= "/identify/user/clg/{authToken}";

	String	SEND_WEB_RESET_PASS_LINK			= "/identify/vesvl=req";

	String	SET_WEB_PASS						= "/identify/u/clg/auth/0";

	String	PAGE_NOT_FOND						= "/user/*/**";

	String	SET_ATTENDANCE						= "/web/taskforce/student/set/attendance/{id}";

	String	INSERT_ATTENDANCE					= "/web/taskforce/student/insert/attendance";
	String	DAILY_ATTENDANCE					= "/web/taskforce/student/attendance/daily/{clientId}";
	String	GET_DAILY_ATTENDANCE_DATA			= "/web/taskforce/student/attendance/daily/data";
	String	DAILY_ATTENDANCE_REPORT				= "/web/taskforce/student/attendance/daily/report/{clientId}";
	String	DAILY_ATTENDANCE_REPORT_DOWNLOAD	= "/web/taskforce/student/attendance/daily/report/download";
	String	THEROY_PERIODTIME					= "/web/taskforce/service/attendance/theroy/Periods/time";
	String	PRAICAL_PERIODTIME					= "/web/taskforce/service/attendance/practical/Periods/time";

	String	GET_DIVISION						= "/web/taskforce/service/division";
	String	GET_DIVISION_FOR_REPORT				= "/web/taskforce/service/report/division";

	String	GET_PRACTICAL_BATCH					= "/web/taskforce/service/attendance/batchlist";
	String	GET_PRACTICAL_BATCH_FOR_REPORT		= "/web/taskforce/service/attendance/report/batchlist";
	String	GET_STUDENT_ATTENDANCE				= "/web/taskforce/student/attendance/{id}";

	String	GET_STUDENT_LIST_FOR_ATTENDANCE		= "/web/taskforce/student/set/attendance/getstudentlist";

	String	ATTENDANCE_BY_SUBJECT				= "/web/taskforce/service/attendance/subject/{staff_sub_id}";

	String	TIMETABLE_SUBJECT					= "web/taskforce/student/set/attendance/getInfo";

	String	GET_ACADEMIC_SUBJECT				= "/web/taskforce/service/attendance/getStaffSubject/{empno}";

	String	GmailLogin							= "/loginwithgmail";

	String	UPLOAD_RESULT_FILE					= "/web/taskforce/service/result/upload";

	String	SAVE_RESULT_FILE					= "/web/taskforce/service/result/save";
	String	GET_RESULT_SUMMARY					= "/web/taskforce/service/result/summary";
	String	GET_SUBJECT_RESULT					= "/web/taskforce/service/result/summary/sub";
	// menotor

	String	MENTOR_PROFILE						= "/web/taskforce/service/mentor/{client_id}";
	String	GET_STUDENT_LIST					= "/web/taskforce/service/student/list";
	String	GET_MENTOR_LIST						= "/web/taskforce/service/mentor/records";
	String	CREATE_PROFILE						= "/web/taskforce/service/mentor/create";
	String	MENTOR_CHAT_BOX						= "/web/taskforce/service/mentor/d/{id}";
	String	REMOVE_MENTOR_MEMBER				= "/web/taskforce/service/mentor/d/student/remove/{id}";

	String	DELETE_MENTOR_MEMBER				= "/web/taskforce/service/mentor/d/student/remove";
	String	TIME_TABLE_INSERT					= "/web/taskforce/timeTable/insert";
	String	TIME_TEACHER_LIST_TIMETABLE			= "/web/sims/timeTable/getsubjectTeacherlist";

	String	ADD_SUBJECT							= "/web/taskforce/serives/Add_Subject";
	String	INSERT_SUBJECT						= "/web/taskforce/serives/Insert_Subject";

	String	ASSIGN_SUBJECT						= "/web/taskforce/Teacher_Subject_List";
	String	INSEART_ASSIGN_SUBJECT				= "/web/taskforce/Adding_Teacher_Subject_list";
	String	GET_ASSIGN_SUBJECT					= "/web/taskforce/service/assign/subjectList";
	String	DELETE_ASSIGN_SUBJECT				= "/web/taskforce/service/delete/subject";
	String	SUBJECT_LIST_FOR_TIMETABLE			= "/web/taskforce/subject/getAcademicSubject";

	String	FEEDBACKLIST_BY_DEPT				= "/web/taskforce/feedback/feedbacklist/{staff_id}/{yrid}";
	String	FEEDBACK							= "/web/taskforce/feedback/feedback";
	String	FEEDBACK_DETAILS					= "/web/taskforce/feedback/feedback/{id}/{sem}";
	String	FEEDBACKCHART_DETAILS				= "/web/taskforce/feedback/feedbackchart/{id}/{sem}";

	// Student SignUp Verification By Admin
	String	STUDENT_ACTIVATION					= "/web/taskforce/activation/student";
	String	UPDATE_STUDENT_STATUS				= "/web/taskforce/activation/active_status/{id}";
	String	DELETE_STUDENT_STATUS				= "/web/taskforce/activation/deactive_status/{id}";

	String	ASSIGN_PRACTICAL_BATCH				= "/web/taskforce/practical_batch/{staffId}";
	String	GET_DIVISION_FOR_BATCH				= "/web/taskforce/batch/division";

	String	GET_PRACTICAL_INFO					= "/web/taskforce/getsubject";

	String	ADD_BATCH							= "/web/taskforce/serives/addBatch";
	String	CREATE_GRIEVANCE_COMMITTEE			= "/web/taskforce/serives/grievance/create/committee";
	String	ADD_COMMITTEE_MEMBERS				= "/web/taskforce/serives/grievance/add/members";
	String	GET_GC__MEMBERS						= "/web/taskforce/serives/grievance/GC_members/list";
	String	GRIVANCE_LIST						= "/web/taskforce/serives/grievance/list";
	String	GRIVANCE_ASSIGN_TOMEMBERS			= "/web/taskforce/serives/grievance/assign/toMember";
	String	GET_GRIEVANCE_ASSIGN_MEMBERS		= "/web/taskforce/serives/grievance/get/Member";
	String	GRIEVANCE_REPLAY					= "/web/taskforce/serives/grievance/replay";
	String	GET_GRIEVANCE_DOWNLOAD_PAGE			= "/web/taskforce/serives/grievance/download/page";
	String	GRIEVANCE_DOWNLOAD					= "/web/taskforce/serives/grievance/download";

	String	GET_POLL_PAGE						= "/web/taskforce/serives/poll/page";
	String	ADD_POLL							= "/web/taskforce/serives/poll/add";
	String	DELETE_POLL							= "/web/taskforce/serives/poll/delete/{id}";
	String	UPDATE_POLL							= "/web/taskforce/serives/poll/update";
	String	GET_POLL_GRAPH_RESULT				= "/web/taskforce/serives/poll/graph";

	// Adding Excel SubjectList
	String	SUBJECT_FILE						= "/web/taskforce/serives/uploadsubfile";
	String	ADD_XCEL_SUBJECT_LIST				= "/web/taskforce/serives/Addxcelsubjectdata";

	// Adding Excel FeedbackQue
	String	FEEDBACK_FILE						= "/web/taskforce/serives/uploadfeedbackfile";
	String	ADD_EXCEL_FEEDBACK_LIST				= "/web/taskforce/serives/Addxcelfeedbackdata";

	// click on menter stud,display mentee profile
	String	MENTER_STUD_PROFILE					= "/web/taskforce/service/result/menterstudprof/{id}";

	// display staff profile in mentor list
	String	STAFFPROFILE						= "/web/taskforce/service/staffprofile/{id}";

	String	STAFF_YEAR_INFO						= "/web/taskforce/service/staffprofile/info/{id}/{dt}/{option}";

	String	SUB_ATTENDANCE						= "/web/taskforce/service/attendbysubject";

	String	UPDATE_STUDENT_PROFILE_PAGE			= "/web/taskforce/service/student/profile/{Id}";
	String	student_info_update					= "/web/taskforce/serives/student/updateStudentProfile";

	String	UPDATE_STAFF_PROFILE				= "/web/taskforce/service/staff/profile/{Id}";
	String	SAVE_UPDATE_INFO					= "/web/taskforce/serives/staff/updateProfile";

	String	UPDATE_PARENT_PROFILE				= "/web/taskforce/service/student/parentprofile/{Id}";
	String	SAVE_PARENT_INFO					= "/web/taskforce/serives/staff/SaveParentProfile";

	// Add staff using form
	String	AdminDetails						= "/web/taskforce/service/Admin_details";
	String	Admin_Info							= "/web/taskforce/service/result/adminupdate";

	String	test								= "/web/test";

	String	MENTOR_GROUP_LIST					= "/web/taskforce/service/mentor/grplist/{id}";
	String	MENTOR_GROUP_DELETE					= "/web/taskforce/serives/mentor/group/delete/{grupId}/{staffid}";

	String	FEE_DETAILS							= "/web/taskforce/service/student/FeeDetails/{id}";
	String	SAVE_FEE_DETAILS					= "/web/taskforce/service/student/SaveFeeDetails";

	String	DELETE_STUDENT						= "web/taskforce/serives/student/delete/{id}/{depID}";
	String	DELETE_STAFF						= "web/taskforce/serives/staff/delete/{id}/{depID}";
	String	GET_STUDENTLIST_FOR_BATCH			= "/web/taskforce/batch/studentlist";

	String	STUDENTLIST_BATCH_ADD				= "/web/taskforce/student/batch/add";

	String	STUDENT_PUNCH						= "/web/taskforce/attendance/punch/page/{staffId}";

	String	STUDENT_COUNT						= "/web/taskforce/punch/student/count/{id}";

	String	HELP								= "/web/taskforce/help";
	String	HELP_URL							= "/web/help/geturl";

	String	PUNCH								= "/web/taskforce/punch/{id}";
	String	PUNCH_ADD							= "/web/punch/add";

	String	INSTITUTE_INFO						= "/web/institute/list";
	String	INSTITUTE_INFO_BY_ID				= "/web/institute/info/{id}";
	String	MAINMEMU_LIST_BY_ID					= "/web/menulist/collegeMenu";
	String	ADD_INSTITUTE_INFO					= "/web/institute/add";
	String	UPDATE_INSTITUTE_INFO				= "/web/institute/update";

	String	seubjecturl							= "/web/taskforce/service/staff/Subject/{id}";
	String	feedbackurl							= "/web/taskforce/service/feedback";

	String	noticeurl							= "/web/taskforce/service/notice/{id}";
	String	pollurl								= "/web/taskforce/service/poll/{clientid}";
	String	groupurl							= "/web/taskforce/service/group/{clientid}";
	String	resulturl							= "/web/taskforce/service/result";

	// result url
	String	resultpdf							= "/web/taskforce/service/result/report";
	String	resultStudent						= "/web/taskforce/service/result/report/student";
	String	Resultpage							= "/web/taskforce/service/resultpage/report";
	String	Resultstaffpage						= "/web/taskforce/service/resultpage/staffreport";
	String	Resultsubject						= "/web/taskforce/service/resultsubject";
	String	ResultSubjeclist					= "/web/taskforce/service/resultsubject/uploadfile";
	String	getsubjectlist						= "/web/taskforce/service/subject/list";

	String	GET_STUD_EDUCATIONAL_INFO_PAGE		= "/web/taskforce/service/student/placementPage/{id}";
	String	UPDATE_STUD_EDUCATIONAL_INFO		= "/web/taskforce/service/student/placementPage/update";
	String	REMOVE_STUD_EDUCATIONAL_INFO		= "/web/taskforce/service/student/placementPage/remove";

	// Tpo Data.............
	String	ASSIGN_COORDINATOR					= "/web/taskforce/service/assigncoordinator/{client_id}";
	String	ADD_ASSIGN_COORDINATOR				= "/web/taskforce/service/assigncoordinator/save";
	String	REMOVE_COORDINATOR					= "/web/taskforce/service/removecoordinator/{client_id}/{cid}";
	String	DEPT_TPO_LIST						= "/web/taskforce/service/depttpolist";
	String	TPO_HOME							= "/web/taskforce/service/tpohome";
	String	ADD_RECRUITMENT_INFO				= "/web/taskforce/service/add/recruitmentInfo";
	String	PAST_COMPANYS						= "/web/taskforce/service/pastcompany";
	String	GET_COMPNY_BY_ID					= "/web/taskforce/service/compny/{id}";
	String	GET_COMPNY_BY_ID1					= "/web/taskforce/service/compny/data/{id}";
	String	STUDENTLIST_TPO						= "/web/taskforce/service/tpo/getstudent";
	String	GET_STUDENT_LIST_FOR_TPO			= "/web/taskforce/service/tpo/getstudentlist";
	String	STUDENTLIST_DOWNLOAD				= "/web/taskforce/service/tpo/studentlist/download";
	String	GET_SELECTED_STUDENT				= "/web/taskforce/service/tpo/selectstud";

	String	GET_RESULT_BY_SUBJECT				= "/web/taskforce/service/resultbysubject";
	String	GET_DEPARTMENT						= "/web/department";

	String	GET_APPLYED_STUDENT_LIST			= "/web/taskforce/service/appyled/studlist/{reInfoId}/{id}";
	String	GET_PLACED_STUDENT_LIST				= "/web/taskforce/service/placed/studlist";
	String	ADD_OFFCAMPUS_PLACE_STUDENT			= "/web/taskforce/service/offcampus/addstud";
	String	SAVE_OFFCAMPUS_PALCE_STUDENT		= "/web/taskforce/serives/offcampusplace/stud";

	String	ADD_SELECTED_STUDENT_IN_ROUND		= "/web/taskforce/service/rounds/addstud";

	String	UPDATE_STUDENT_ACADAMIC_YEAR		= "/web/taskforce/service/student/year";
	String	PASSOUT_YD_STUDENT					= "/web/taskforce/service/backlog/passout";
	String	GET_PASSOUT_YD_STUDENT_LIST			= "/web/taskforce/service/passout/yd/student/list";
	String	UPDATE_PASSOUT_YD_STUDENT			= "/web/taskforce/update/passout/to/redular";

	String	EXAM_DASHBOARD						= "/web/taskforce/service/exam/dashboard";
	String	GET_SUBJECT_BY_STAFF				= "/web/service/taskforce/getsubjectbyid";
	String	SEND_OFFER_TO_STUDENT				= "/web/taskforce/service/rounds/sendoffer";
	String	GET_ROUND_LIST_PLSCEMENT			= "/web/taskforce/service/rounds/list/{id}";

	String	STUDENTDATA							= "/web/taskforce/serives/student/studentdata";
	String	STAFFDATA							= "/web/taskforce/serives/staff/staffdata";
	String	getdonloads							= "/web/taskforce/serives/student/download/page";

	// Add student using form
	String	Studentdetails						= "/web/taskforce/service/studentdetails";
	String	Studentupdate						= "/web/taskforce/service/result/studentupdate";

	// student and college count
	String	Studenttotalcount					= "/web/taskforce/service/count/studentcountpagess";
	String	totalstudcountdept					= "/web/taskforce/service/count/student";
	String	yeardepartment						= "/web/taskforce/service/student/yearcountpage";

	// Group Poll
	String	ADD_GROUP_POLL						= "/web/taskforce/serives/grouppoll/add";
	String	GET_GROUP_POLL_GRAPH_RESULT			= "/web/taskforce/serives/grouppoll/graph";
	String	UPDATE_GROUP_POLL					= "/web/taskforce/serives/grouppoll/update";
	String	DELETE_GROUP_POLL					= "/web/taskforce/serives/grouppoll/delete/{id}/{g_id}";

	String	GET_STUDENTLIST_FILTER				= "/web/taskforce/service/student/getfilterlist";
	String	GET_STUDENT_ATTENDACE_BY_ID			= "/web/taskforce/service/student/Attendance/{id}";

	// Student Internship
	String	GET_INTERNSHIP_STUDENT_LIST			= "/web/taskforce/service/studentinternship/list";

	String	FEE_DETAILS1						= "/web/taskforce/service/student/feedtl/{id}/{mid}";
	String	SAVE_FEE_DETAILS1					= "/web/taskforce/service/student/SaveFeedtl";

	// Fee Details
	String	Totalstudentfeedetails				= "/web/taskforce/service/fee/studentfeedetails";
	String	TotalDivStudentFee					= "/web/taskforce/service/fee/student";
	String	MENTORSTAFFPROFILE					= "/web/taskforce/service/mentor/mentstaffprofile";
	String	SAVE_PARENT_CALL_RECORD				= "/web/taskforce/service/student/parentcallrecord/{id}";
	// Registration Page
	String	REGISTRATION_PAGE					= "/web/taskforce/service/staff/registration";
	String	SAVE_REGISTER_DATA					= "/taskforce/service/staff/register/save";
	String	CHECKEMAILID						= "/taskforce/service/staff/test/check";
	String	MESSAGE								= "/taskforce/service/user/test/msg";
	String	SUCCESSPAGE							= "/taskforce/service/user/success";
	String	REPORTLIST							= "/taskforce/service/result/list";
	String	SADMINSUBREPORT						= "/taskforce/service/result/superadmin/report";

	String	COMMON_INSTITUDE_REPORT				= "/taskforce/service/result/commonreport";
	String	COMMONSUPERADMINREPORT				= "/taskforce/service/result/admin/report";

}
