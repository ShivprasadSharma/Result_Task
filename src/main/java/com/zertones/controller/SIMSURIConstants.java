package com.zertones.controller;

/**
 * @author Zerton Team
 * @Created Date : Oct 6, 2015
 */
public interface SIMSURIConstants
{
	// Web URI
	String	SAVE_STUDENT					= "/web/taskforce/sims/student/save";
	String	GET_STUDENT_LIST_BY_DEPARTMENT	= "/web/taskforce/cims/student/list/{depId}";
	String	GET_STUDENTS					= "/web/taskforce/service/mentor/student/{id}";
	String	SAVE_STAFF_PROFIL				= "/web/taskforce/sims/staff/update";
	String	GET_LIST_STUDENT				= "/web/sims/student/listforevent";

	// REST URI
	String	REST_SAVE_STUDENT				= "/api/sims/student/save";
	String	REST_SAVE_STAFF					= "/api/sims/staff/save";
	String	UPDATE_STUDENT_PROFIL			= "/api/sims/student/updateprofile";

	// SIM URI
	String	REST_GET_ALL_STUDENT_RECORD		= "/api/sims/student/getstudentallrecords";
	String	REST_GET_STUDENT_RECORD_ById	= "/api/sims/student/getstudrecbyid/{StudId}";

	String	SIGNUP_STUDENT					= "/student/signup";
	String	SIGNUP_STAFF					= "/staff/signup";
	String	SIGNUP_PARENT					= "/parent/signup";

	String	COMMENT_INSERT					= "/web/sims/comment/insert";
	String	COMMENT_LIST					= "/web/sims/comment/CommentList";
	String	LIKE							= "/web/sims/like/incriment";

	String	TIME_BATCH_TIMETABLE			= "/web/sims/timeTable/getBatchlist";
	String	GET_SUB_LIST					= "/web/sims/sublect/getSubjectlist";

	String	GET_SUBJEC_INFO					= "/web/sims/subject/getsubject";

	String	GET_STUDENT_LIST_BY_FILTER		= "/web/taskforce/cims/student/list";

	// Skill and Remark
	String	GFM_STUD_REMARK					= "/web/taskforce/service/mentor/gfmremark";

	String	REMARKLIST						= "/web/taskforce/service/mentorstudent/remarklist";
	String	SKILLIST						= "/web/taskforce/service/studentskill/skillist";

	String	STUDENTREMARK					= "/web/taskforce/service/studentremark/remarks";

	String	STUDENTRMKCHECK					= "/web/taskforce/service/student/studcheckrmark";

	String	STUDENTREMARKVIEW				= "/web/taskforce/service/student/studremarkview";

}
