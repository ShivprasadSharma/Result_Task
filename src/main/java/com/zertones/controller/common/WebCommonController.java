package com.zertones.controller.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.zertones.controller.RestURIConstants;
import com.zertones.controller.SIMSURIConstants;
import com.zertones.controller.WebURIConstants;
import com.zertones.controller.service.Helper;
import com.zertones.core.SampleClass;
import com.zertones.core.WebException;
import com.zertones.model.ComAttendance;
import com.zertones.model.ComClientName;
import com.zertones.model.ComUser;
import com.zertones.model.ComUserDetails;
import com.zertones.model.EducationDetails;
import com.zertones.model.ResultModelForPlacement;
import com.zertones.model.StudentForm;
import com.zertones.model.SubjectList;
import com.zertones.model.DataTransferObjectModel.AttendanceDTOWeb;
import com.zertones.model.DataTransferObjectModel.ComClientNameDTO;
import com.zertones.model.DataTransferObjectModel.DepartmentDTO;
import com.zertones.model.DataTransferObjectModel.NewUserDTO;
import com.zertones.model.DataTransferObjectModel.RecruitmentInfoDTO;
import com.zertones.model.DataTransferObjectModel.StudentAttendanceDto;
import com.zertones.model.DataTransferObjectModel.TimeTableDTO;
import com.zertones.model.common.AdminForm;
import com.zertones.model.common.ApplyedStudentForCompany;
import com.zertones.model.common.AttendancePunch;
import com.zertones.model.common.BatchList;
import com.zertones.model.common.CompanySelectionRounds;
import com.zertones.model.common.ExtraActivities;
import com.zertones.model.common.FeeDetails;
import com.zertones.model.common.Groups;
import com.zertones.model.common.Help;
import com.zertones.model.common.InstituteInfoDetails;
import com.zertones.model.common.InstituteInfoMaster;
import com.zertones.model.common.LecturePracticalTime;
import com.zertones.model.common.LectureTheoryTime;
import com.zertones.model.common.Notification;
import com.zertones.model.common.OffcampusPlaceStud;
import com.zertones.model.common.StudentFeeDtl;
import com.zertones.model.common.TimeTable;
import com.zertones.model.common.TotalStudentCount;
import com.zertones.model.common.UserToken;
import com.zertones.model.master.AcademicSemester;
import com.zertones.model.master.AcademicSubject;
import com.zertones.model.master.CollegeMenuList;
import com.zertones.model.master.ComStaffSubject;
import com.zertones.model.sims.Mentor;
import com.zertones.model.sims.Staff;
import com.zertones.model.sims.Student;
import com.zertones.security.model.UserRole;
import com.zertones.service.CommonValidationService;
import com.zertones.service.common.CommonService;
import com.zertones.service.common.FeedbackService;
import com.zertones.service.common.GrievanceService;
import com.zertones.service.common.JavaSendMailService;
import com.zertones.service.common.NotificationService;
import com.zertones.service.sims.StaffService;
import com.zertones.service.sims.StudentService;

/**
 * @author Zerton Team
 * @Created Date : Sep 17, 2015
 */
@Controller
public class WebCommonController
{
	private static final Logger		logger	= LoggerFactory.getLogger(WebCommonController.class);

	private CommonService			commonService;

	@Autowired
	private GrievanceService		grievanceService;

	@Autowired
	private Helper					helper;

	@Autowired
	private NotificationService		notificationService;

	@Autowired
	private FeedbackService			feedbackService;

	@Autowired
	private StudentService			studentService;

	@Autowired
	private JavaSendMailService		javaSendMailService;

	@Autowired
	private StaffService			staffService;

	@Autowired
	private CommonValidationService	commonValidation;

	@ModelAttribute("userEntity")
	public ComUser ComUser()
	{
		return new ComUser();
	}

	@Autowired(required = true)
	@Qualifier(value = "commonService")
	public void setCommonService(CommonService commonService)
	{
		this.commonService = commonService;
	}

	@RequestMapping(value =
	{ "/" }, method = RequestMethod.GET)
	public String welcomePage()
	{
		return "redirect:" + WebURIConstants.LIST_NOTIFICATIONS;
	}

	@RequestMapping(value =
	{ "/web/**" }, method = RequestMethod.GET)
	public ModelAndView getPageNotFound()
	{
		return new ModelAndView("user.pageNotFound");
	}

	// Spring Security see this :
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(String logout, HttpSession session)
	{

		return "login";

	}

	// Spring Security see this :
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response)
	{

		InstituteInfoMaster infoMaster = (InstituteInfoMaster) request.getSession().getAttribute("collegeInfo");

		HttpSession session = request.getSession(false);

		session = request.getSession(false);
		if (session != null)
		{
			session.invalidate();
		}
		for (Cookie cookie : request.getCookies())
		{
			cookie.setMaxAge(0);
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null)
		{
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		SecurityContextHolder.clearContext();
		session = request.getSession(true);
		session.setAttribute("collegeInfo", infoMaster);
		return "redirect:/login";

	}

	// Spring Security see this :
	@RequestMapping(value = "/college/{instId}", method = RequestMethod.GET)
	public String instLogin(@PathVariable("instId") int instId, HttpServletRequest request, HttpSession session)
	{
		try
		{

			Staff user = (Staff) request.getSession().getAttribute("user");
			InstituteInfoMaster infoMaster = (InstituteInfoMaster) request.getSession().getAttribute("collegeInfo");
			if (user != null)
			{
				if (infoMaster.getInstCode() != instId)
				{
					session.removeAttribute("collegeInfo");
					session.setAttribute("collegeInfo", commonService.getInstituteInfoMasterById(instId));
					return "redirect:/logout";
				} else
				{
					return "redirect:" + WebURIConstants.LIST_NOTIFICATIONS;

				}

			} else
			{
				InstituteInfoMaster instituteInfoMaster = new InstituteInfoMaster();
				if (infoMaster == null)
				{
					instituteInfoMaster = commonService.getInstituteInfoMasterById(instId);
					session.setAttribute("collegeInfo", instituteInfoMaster);
				} else
				{
					if (infoMaster.getInstCode() != instId)
					{
						session.removeAttribute("collegeInfo");
						instituteInfoMaster = commonService.getInstituteInfoMasterById(instId);
						session.setAttribute("collegeInfo", instituteInfoMaster);
					}

				}

				return "redirect:/login";
			}

		} catch (Exception e)
		{
			return "redirect:/logout";
		}

	}

	/*
	 * @RequestMapping(value = WebURIConstants.ADMIN_INDEX, method =
	 * RequestMethod.GET) public String adminHome(ModelMap model) {
	 * logger.debug("index() is executed!"); return "admin.homepage"; }
	 */

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = WebURIConstants.MASTER_LIST, method = RequestMethod.GET)
	public String masterList(ModelMap model)
	{
		logger.debug("calling master list");
		model.addAttribute("masterList", commonService.getInstituteInfoMatserList());
		model.addAttribute("masterForm", new InstituteInfoMaster());
		return "admin.masterlist";
	}

	@RequestMapping(value = WebURIConstants.MASTER_GET, method = RequestMethod.GET)
	public String getMasterById(@PathVariable("masterid") Integer masterId, Map<String, Object> map)
	{
		logger.debug("getting institute master");
		map.put("masterForm", commonService.getInstituteInfoMasterById(masterId));
		return "admin.masterForm";
	}

	@RequestMapping(value = WebURIConstants.MASTER_DETAIL_GET, method = RequestMethod.GET)
	public String getMasterDtlById(@PathVariable("masterdtlid") Integer masterId, Map<String, Object> map)
	{
		logger.debug("getting master details");
		map.put("masterDetailForm", commonService.getMasterDtlById(masterId));
		return "admin.masterDetailsForm";
	}

	@RequestMapping(value = WebURIConstants.BLANK_NOTICE_FORM, method = RequestMethod.GET)
	public String getNotificationForm(Map<String, Object> map, ModelMap model)
	{
		logger.debug("Notice is executed!");
		Notification notification = new Notification();

		model.addAttribute("departments", notificationService.getDepartment());
		model.addAttribute("stafflist", staffService.getStaffList());
		model.addAttribute("noticesSendByTecherList", notificationService.getNoticesSendByTeacher());
		model.addAttribute("group", null/* commonService.getGroupList() */);
		map.put("notification", notification);
		return "admin.blankNoticeForm";
	}

	@RequestMapping(value = WebURIConstants.BLANK_MASTER, method = RequestMethod.GET)
	public String getBlankMasterForm(Map<String, Object> map)
	{
		logger.debug("calling master blank form");
		InstituteInfoMaster master = new InstituteInfoMaster();
		map.put("masterForm", master);
		return "admin.masterForm";
	}

	@RequestMapping(value = WebURIConstants.GET_CALENDER, method = RequestMethod.GET)
	public String getcalender(ModelMap model)
	{
		logger.debug("calling calender");
		model.addAttribute("departments", notificationService.getDepartment());
		return "admin.calender";
	}

	@RequestMapping(value = WebURIConstants.NOTICE_FILE, method = RequestMethod.GET)
	public String getNoticeFileUploadPage(@RequestParam(value = "message", required = false) String message,
			ModelMap model)
	{
		logger.debug("calling notification form page");
		model.addAttribute("message", message);
		model.addAttribute("departments", notificationService.getDepartment());
		return "admin.noticefile";
	}

	@RequestMapping(value = WebURIConstants.BLANK_USER, method = RequestMethod.GET)
	public String getBlankUserForm(Map<String, Object> map, @PathVariable("id") Integer id, Model model)
	{

		logger.debug("calling user blank form");
		Groups group = new Groups();
		map.put("groupForm", group);
		model.addAttribute("stafflist", staffService.getStaffList());
		model.addAttribute("studentlist", studentService.getStudentList());
		model.addAttribute("subjectlist", commonService.getAssigendStaffSubject(id));
		model.addAttribute("DeptStfflist", staffService.getStaffList(id));
		return "admin.userForm";
	}

	@RequestMapping(value = WebURIConstants.GET_SORTED_STUD, method = RequestMethod.POST)
	public @ResponseBody List<ComClientNameDTO> getStudentListForGroup(@RequestParam("gt") Integer gt,
			@RequestParam("dept") Integer dept)
	{

		logger.info("Get Student list ");
		if (gt == 2 || gt == 4)
		{
			List<ComClientNameDTO> stdlist = studentService.getStudentListByDep(dept);
			return stdlist;
		} else
		{
			List<ComClientNameDTO> stdlist = studentService.getStudentlist();

			return stdlist;
		}
	}

	@RequestMapping(value = WebURIConstants.GET_SORTED_STAFF, method = RequestMethod.POST)
	public @ResponseBody List<ComClientNameDTO> getStaffListForGroup(@RequestParam("gt") Integer gt,
			@RequestParam("dept") Integer dept)
	{

		logger.info("Get staff list ");
		if (gt == 2 || gt == 4)
		{
			List<ComClientNameDTO> stdlist = staffService.getStaffListbydepts(dept);
			return stdlist;
		} else
		{
			List<ComClientNameDTO> stdlist = staffService.getStaffList();
			return stdlist;
		}
	}

	@RequestMapping(value = WebURIConstants.BLANK_USER_DATA, method = RequestMethod.POST)
	public @ResponseBody List<?> getRequiredDataForNewGroup(@RequestParam Map<String, String> d, Model model)
	{
		Integer stafforStudent = Integer.parseInt(d.get("forValue"));

		// System.out.println("staff id.........." + stafforStudent);
		Integer departmentalOrAll = Integer.parseInt(d.get("groupType"));
		int staffId = Integer.parseInt(d.get("StaffID"));
		if (stafforStudent == 1)
		{
			if (departmentalOrAll == 3)
			{
				return studentService.getStudentListByDepartment();// studentService.getStudentListByDep(departmentalOrAll);
			} else
			{
				return studentService.getStudentList();
			}
		} else
		{
			if (departmentalOrAll == 3)
			{
				return staffService.getStaffList(staffId);
			} else
			{
				return staffService.getStaffList();
			}
		}

	}

	@RequestMapping(value = WebURIConstants.BLANK_MASTER_DTL, method = RequestMethod.GET)
	public String getBlankMasterDtlForm(Map<String, Object> map)
	{
		logger.debug("calling master blank form");
		InstituteInfoDetails masterDetail = new InstituteInfoDetails();
		map.put("masterDetailForm", masterDetail);
		return "admin.masterDetailsForm";
	}

	@RequestMapping(value = WebURIConstants.ADD_MASTER, method = RequestMethod.POST)
	public String saveMaster(@ModelAttribute("masterForm") InstituteInfoMaster instituteInfoMaster,
			BindingResult result)
	{
		// commonService.addInstituteInfoMaster(instituteInfoMaster);
		return "redirect:" + WebURIConstants.MASTER_LIST;
	}

	@RequestMapping(value = WebURIConstants.MASTER_GET_DETAILS, method = RequestMethod.GET)
	public String getMasterDetailsByMasterId(@PathVariable("masterid") Integer masterId, Map<String, Object> map)
	{
		logger.debug("getting institute master by Id");
		map.put("MasterDetails", commonService.getInstituteInfoMasterById(masterId));
		return "admin.masterDetails";
	}

	@RequestMapping(value = WebURIConstants.ADD_MASTER_DTL, method = RequestMethod.POST)
	public String saveMasterDtl(@ModelAttribute("masterForm") InstituteInfoDetails instituteInfoDetails,
			BindingResult result)
	{
		commonService.addInstituteInfoDetails(instituteInfoDetails);
		return "redirect:" + WebURIConstants.MASTER_LIST;
	}

	// @Secured("ROLE_ADMIN")
	@RequestMapping(value = WebURIConstants.USER_LIST, method = RequestMethod.GET)
	public String userList(ModelMap model)
	{
		logger.debug("calling master list");
		model.addAttribute("departments", commonService.getUserInfoMatserList());
		model.addAttribute("userList", notificationService.getDepartment());
		return "admin.homepage";
	}

	@RequestMapping(value = WebURIConstants.USER_SAVE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String userSignUp(@ModelAttribute("userForm") ComClientName comClientName, BindingResult result)
	{
		ComUserDetails comUserDetails = new ComUserDetails();
		UserRole role = new UserRole();
		Set<UserRole> userRole = new HashSet<>();
		logger.info("Creating New User");
		if (comClientName.getComUserDetails().getUserName() != null
				&& !commonValidation.isUserExists(comClientName.getComUserDetails().getUserName()))
		{
			comUserDetails.setUserName(comClientName.getComUserDetails().getUserName());
			comUserDetails.setPassword(comClientName.getComUserDetails().getPassword());
			comUserDetails.setUserRole(comClientName.getComUserDetails().getUserRole());
			role.setRoleName(comClientName.getComUserDetails().getUserRole());
			userRole.add(role);
			comUserDetails.setUserRoles(userRole);
			comClientName.setComUserDetails(comUserDetails);

			ComClientName cn = commonService.saveUser(comClientName);

			return "admin.homepage";
		} else
		{
			return "admin.homepage";
		}

	}

	@RequestMapping(value = WebURIConstants.USER_GET, method = RequestMethod.GET)
	public String getGetById(@PathVariable("userId") Integer userId, Map<String, Object> map)
	{
		logger.debug("calling User Details");
		map.put("userForm", commonService.getUserDtlById(userId));
		return "admin.userForm";

	}

	@RequestMapping(value = WebURIConstants.REMOVE_USER, method = RequestMethod.GET)
	public String getRemoveUser(@PathVariable("userId") Integer userId)
	{
		logger.debug("calling User Details");
		commonService.getRemoveUser(userId);
		return "admin.homepage";

	}

	@RequestMapping(value = WebURIConstants.USER_PROFIEL, method = RequestMethod.GET)
	public String getUserProfile(@RequestParam(value = "errorMsg", required = false) String error,
			@RequestParam(value = "message", required = false) String message, Model model)
	{
		logger.debug("calling User Profile");
		model.addAttribute("errorMsg", error);
		model.addAttribute("message", message);
		return "user.UserProfile";

	}

	@RequestMapping(value = WebURIConstants.SEARCH_TEACHER, method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody List<ComClientName> getTechList(@RequestParam("term") String query)
	{
		return commonService.getTechList(query);
	}

	@RequestMapping(value = WebURIConstants.GET_STAFF_BY_TYPE, method = RequestMethod.GET)
	public String getuserList(@PathVariable("type") Integer id, ModelMap model)
	{
		model.addAttribute("departments", notificationService.getDepartment());
		model.addAttribute("stafflist", commonService.getStaffByType(id));

		System.out.println("commonService.getStaffByType(id)........" + commonService.getStaffByType(id));

		return "admin.userlist";
	}

	@RequestMapping(value = WebURIConstants.CREATE_GROUP, method = RequestMethod.POST)
	public String creategroup(@ModelAttribute("groupForm") Groups group, @RequestParam("groupFor") Integer forGroup,
			@RequestParam(value = "member", required = false) Integer[] member, Model model)
	{
		// System.out.println("Group for " + forGroup);
		group.setGroupfor(forGroup);
		commonService.saveGroup(group, member);
		return "redirect:" + WebURIConstants.GROUP_LIST;
	}

	@RequestMapping(value = WebURIConstants.CREATE_SUBJECT_GROUP, method = RequestMethod.POST)
	public String createsubgroup(@RequestParam("clientId") Integer clientID, @RequestParam("div") Integer div,
			@RequestParam("subjectId") Integer subjectId, Model model)
	{

		commonService.createSubjectGroup(subjectId, clientID, div);
		// return "redirect:" + WebURIConstants.GROUP_LIST;
		return "redirect:" + WebURIConstants.GROUP_LIST;
	}

	@RequestMapping(value = WebURIConstants.CREATE_CLASS_GROUP, method = RequestMethod.POST)
	public String createclassgroup(@RequestParam("yearId") Integer yearId, @RequestParam("div") Integer div,
			@RequestParam("staffId") Integer staffId, Model model)
	{
		commonService.createClassGroup(staffId, yearId, div);
		return "redirect:" + WebURIConstants.GROUP_LIST;

	}

	@RequestMapping(value = WebURIConstants.GROUP_LIST, method = RequestMethod.GET)
	public String getGroupList(Model model, HttpServletRequest request)
	{
		logger.debug("Group List");
		Staff user = (Staff) request.getSession().getAttribute("user");
		model.addAttribute("grouplist", commonService.getGroupList(user.getComClientName().getId()));
		return "admin.group";
	}

	@RequestMapping(value = WebURIConstants.GROUP_DETAILS, method = RequestMethod.GET)
	public String getGroupdetails(Model model, @PathVariable("gid") Integer groupId)
	{
		logger.debug(" Group Details");
		List<Notification> list = null;// notificationService.getNotificationByGroupId(groupId);
		model.addAttribute("notices", notificationService.getGroupNotification(groupId));
		model.addAttribute("groupInfo", commonService.getGroupDetails(groupId));
		model.addAttribute("PastGPollList", feedbackService.getPast_GroupPollList(groupId));
		model.addAttribute("currentGPollList", feedbackService.getcurrent_GroupPollList(groupId));

		return "admin.groupdetails";
	}

	@RequestMapping(value = RestURIConstants.GROUP_NOTICES, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getGroupNotices(@PathVariable("gid") Integer gId)
	{
		logger.debug(" Group Details");
		List<Notification> list = null;// notificationService.getNotificationByGroupId(gId);
		if (!list.isEmpty())
		{
			return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "Group Detail", list), HttpStatus.ACCEPTED);
		} else
		{
			return new ResponseEntity<>(new SampleClass(HttpStatus.BAD_REQUEST, "Group not found", list),
					HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = WebURIConstants.ADD_GROUP_MEMBER, method = RequestMethod.POST)
	public String addMember(@ModelAttribute("groupForm") Groups group,
			@RequestParam(value = "member", required = true) Integer[] member, Model model)
	{
		System.out.println("groupid" + group.getGroupId());
		Boolean result = commonService.UpdateGroup(group, member);

		if (result == true)
		{
			model.addAttribute("msg", "Group Create Successfully...!");
			return "redirect:/web/taskforce/groupdetails/" + group.getGroupId();
		} else
		{
			model.addAttribute("msg", "Error While Create a Group !");
			return "redirect:/web/taskforce/groupdetails/" + group.getGroupId();
		}
	}

	@RequestMapping(value = WebURIConstants.GET_GROUP_MEMBER, method = RequestMethod.GET)
	public String addGroupMemberForm(Map<String, Object> map, Model model, @PathVariable("id") Integer gid)
	{
		logger.debug("calling user blank form");
		// System.out.println("gid" + gid);
		Groups groupmember = new Groups();
		map.put("groupMember", groupmember);
		model.addAttribute("groupId", gid);
		model.addAttribute("studentlist", studentService.getStudentList(gid));
		return "admin.addGroupMember";
	}

	@RequestMapping(value = WebURIConstants.REMOVE_MEMBER, method = RequestMethod.POST)
	public String removeMembrs(@ModelAttribute("groupForm") Groups group,

			@RequestParam(value = "member", required = true) Integer[] member)
	{
		if (commonService.removeGroupMember(group, member) == true)
		{
			return "redirect:" + WebURIConstants.GROUP_LIST;
		} else
		{
			return "redirect:" + WebURIConstants.GROUP_LIST;
		}
	}

	@RequestMapping(value = RestURIConstants.GET_GROUP_MEMBER, method = RequestMethod.GET)
	public List<Student> getGroupMember(@PathVariable("id") Integer id)
	{
		logger.debug("calling user blank form");
		List<Student> studentlst = null;// studentService.getStudentList();
		List<Student> std = new ArrayList<>();
		for (Student student : studentlst)
		{
			int i = 0;
			/*
			 * for (Groups group : commonService.getGroupDetails(id)) { for (GroupMembers gm
			 * : group.getGroupMembers()) { i = gm.getComClientName().getId(); if
			 * (student.getComClientName().getId().equals(i)) { std.add(student); } } }
			 */
		}
		studentlst.removeAll(std);
		return studentlst;
	}

	@RequestMapping(value = WebURIConstants.BLANK_NOTICE_FORM_FOR_GROUP, method = RequestMethod.GET)
	public String getBlankNoticeForm(Map<String, Object> map, Model model, @PathVariable("id") Integer id)
	{
		logger.debug("calling user blank form");
		Notification n = new Notification();
		map.put("groupNotice", n);
		model.addAttribute("groupId", id);
		return "admin.groupNoticeForm";
	}

	@RequestMapping(value = WebURIConstants.BLANK_NOTICE_MENTOR, method = RequestMethod.GET)
	public String getBlankNoticeForms(Map<String, Object> map, Model model, @PathVariable("id") Integer id)
	{
		logger.debug("calling user blank form");
		Notification n = new Notification();
		map.put("groupNotice", n);
		model.addAttribute("mentorId", id);
		return "admin.mentorNoticeForm";
	}
	//
	// @RequestMapping(value = "/testapi", method = RequestMethod.GET)
	// public @ResponseBody List<ComClientNameDTO> getStudentList()
	// {
	// return studentService.getStudentList(25);
	// }

	@RequestMapping(value = WebURIConstants.GET_MENTOR_MEMBER, method = RequestMethod.GET)
	public String addMentorMemberForm(Map<String, Object> map, Model model, @PathVariable("id") Integer id,
			@PathVariable("sid") Integer stfId)
	{
		logger.debug("calling mentor blank form");
		Mentor mentor = new Mentor();
		map.put("member", mentor);
		model.addAttribute("id", id);
		model.addAttribute("stfid", stfId);
		model.addAttribute("studentlist", studentService.getStudentForMentor(id));
		System.out.println("studenlist" + studentService.getStudentForMentor(id));
		return "admin.addMentorMember";
	}

	@RequestMapping(value = WebURIConstants.ADD_MENTOR_MEMBER, method = RequestMethod.POST)
	public String addMenMember(@ModelAttribute("member") Mentor mentor,
			@RequestParam(value = "students", required = true) Integer[] member, @RequestParam("sid") Integer stfId)
	{
		System.out.println("controllerstudent" + mentor);

		studentService.updateMember(mentor, member);
		return "redirect:/web/taskforce/service/mentor/d/" + stfId;
	}

	@RequestMapping(value = WebURIConstants.REMOVE_MEMBER_FORM, method = RequestMethod.GET)
	public String getMembrsForRemove(@PathVariable("id") Integer id, Model model, Map<String, Object> map)
	{
		Groups gp = new Groups();
		map.put("groupMember", gp);
		model.addAttribute("members", commonService.getGroupDetails(id));
		return "admin.removeMember";

	}

	@RequestMapping(value = WebURIConstants.GROUP_INFO, method = RequestMethod.GET)
	public String editGroupInfo(Model model, @PathVariable("id") Integer groupId, Map<String, Object> map)
	{
		logger.debug(" Group Details" + groupId);
		Groups gp = new Groups();
		map.put("groupForm", gp);
		model.addAttribute("stafflist", staffService.getStaffList());
		model.addAttribute("groupinfo", commonService.getGroupDetails(groupId));
		return "admin.editGroupInfo";

	}

	@RequestMapping(value = WebURIConstants.UPDATE_GROUP, method = RequestMethod.POST)
	public String updateGroup(@ModelAttribute("groupForm") Groups group, Model model)
	{

		if (commonService.updateGroup(group) == true)
		{
			model.addAttribute("message", "Group Create Successfully...!");
			return "redirect:" + WebURIConstants.GROUP_LIST;
		} else
		{
			model.addAttribute("message", "Error While Create a Group !");
			return "redirect:" + WebURIConstants.GROUP_LIST;
		}
	}

	@RequestMapping(value = WebURIConstants.DELETE_GROUP, method = RequestMethod.GET)
	public String deleteGroup(@PathVariable("id") Integer id, Model model)
	{
		System.out.println("groupiiiiiiiiii idddd deletedd" + id);
		if (commonService.removeGroup(id) == true)
		{
			model.addAttribute("msg", "Group Delete Successfully...!");
			return "redirect:" + WebURIConstants.GROUP_LIST;
		} else
		{
			model.addAttribute("msg", "Error While Deleting a Group !");
			return "redirect:" + WebURIConstants.GROUP_LIST;
		}
	}

	@RequestMapping(value = WebURIConstants.FORGOT_WEB_PASS, method = RequestMethod.GET)
	public ModelAndView showGetEmailScreen()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user.getEmailId");
		return mv;
	}

	@RequestMapping(value = WebURIConstants.SEND_WEB_RESET_PASS_LINK, method = RequestMethod.POST)
	public ModelAndView sendVerificationEmail(@RequestParam("inputEmail") String inputEmailId, Model model)
	{
		UserToken userToken = new UserToken();
		ModelAndView mv = new ModelAndView("user.getEmailId");
		String token = UUID.randomUUID().toString().toUpperCase();
		ComClientName comClientName = commonService.findUserByEmail(inputEmailId);
		if (comClientName != null)
		{
			Date dt = new Date();
			userToken.setVerificationToken(token);
			userToken.setComClientName(comClientName);
			userToken.setTokenExpiration(dt);
			UserToken tkn = commonService.saveVerificationToken(userToken);
			if (tkn != null)
			{
				try
				{
					javaSendMailService.sendMail(comClientName.getEmailId(), token, comClientName.getFirstName());
					mv.addObject("msg", "Request Email Send Successfully..!!");
				} catch (Exception e)
				{
					e.printStackTrace();
					mv.addObject("error", "Error Occured while Request email Send ..!!");
				}
			} else
			{
				mv.addObject("error", "Invalid Email Address ...!!");
			}
		} else
		{
			mv.addObject("error", "Invalid Email Address ...!!");
		}
		return mv;
	}

	@RequestMapping(value = "/identify/user/clg/{authToken}", method = RequestMethod.GET)
	public ModelAndView getNewPassword(@PathVariable("authToken") String authToken, Model mdl)
	{
		System.out.println("Forgot Pass");
		ModelAndView model = new ModelAndView();
		model.setViewName("user.pageNotFound");
		if (!authToken.isEmpty())
		{
			UserToken user = commonService.getUserByToken(authToken);
			if (user != null)
			{
				if (user.getTokenId() != 0)
				{
					mdl.addAttribute("userInfo", user);
					model.setViewName("user.setNewPass");
				} else
				{
					mdl.addAttribute("msg", "This Link is already used...!!!");
				}
			}
		}
		return model;
	}

	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public ModelAndView setUserEntity(@ModelAttribute("userEntity") ComUser comUser, BindingResult result, Model model)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user.setNewPass");
		if (commonService.updateUserEntity(comUser) == true)
		{
			model.addAttribute("msg", "Password Update Successfully ...!!");
		} else
		{
			model.addAttribute("error", "Error while updating Password ...!!");
		}
		return mv;
	}

	@RequestMapping(value = WebURIConstants.SET_PASS, method = RequestMethod.POST)
	public ModelAndView setUserNewEntity(@ModelAttribute("userEntity") ComUser comUser, BindingResult result,
			Model model)
	{
		ModelAndView mv = new ModelAndView();
		boolean reslt = commonService.updateUserEntity(comUser);
		if (reslt == true)
		{
			model.addAttribute("msg", "Password Update Successfully ...!!");
		} else
		{
			model.addAttribute("error", "Old Password Not Matching ...!!");
		}
		mv.setViewName("user.UserProfile");
		return mv;
	}

	@RequestMapping(value = WebURIConstants.PAGE_NOT_FOND, method = RequestMethod.GET)
	public ModelAndView errorpage(Model model)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user.pageNotFound");
		return mv;
	}

	@RequestMapping(value = WebURIConstants.GET_ADD_USER, method = RequestMethod.GET)
	public ModelAndView getAddUser(@RequestParam(value = "message", required = false) String message, Model model)
	{
		logger.debug("calling add user form");
		ModelAndView mv = new ModelAndView("admin.addUser");
		model.addAttribute("message", message);
		return mv;
	}

	@RequestMapping(value = WebURIConstants.SAVE_ADD_USR_FILE, method = RequestMethod.POST)
	public String saveAttendance(@RequestParam("ExcelFile") MultipartFile excelFile,
			@RequestParam(value = "fileType[]") Integer[] fileType, Map<String, Object> map, ModelMap model)

	{

		String message = "Problem with file ...!";
		if (fileType[0] == 1)
		{
			try
			{
				model.addAttribute("message", staffService.saveStaffInfoViaExcelFile(excelFile));
			} catch (Exception e)
			{
				model.addAttribute("message", e.getMessage());
			}

		} else if (fileType[0] == 2)
		{
			try
			{
				model.addAttribute("message", studentService.saveStudetInfoViaExcelFile(excelFile));
			} catch (Exception e)
			{
				model.addAttribute("message", e.getMessage());
			}

		}
		return "redirect:" + WebURIConstants.GET_ADD_USER;
	}

	@RequestMapping(value = WebURIConstants.GET_STUDENT_ATTENDANCE, method = RequestMethod.GET)
	public String showAttendancePage(@PathVariable("id") Integer id, Model model)
	{
		logger.debug("Student Attendance");
		model.addAttribute("subject", commonService.getAcademicSubjectByStaff(id));
		return "student.attendance";
	}

	@RequestMapping(value = WebURIConstants.SET_ATTENDANCE, method = RequestMethod.GET)
	public String setAttendancePage(@PathVariable("id") Integer staffId, Model model)
	{
		logger.debug("Student Attendance");

		// System.out.println(staffId + "subject............" +
		// commonService.getAssigendStaffSubject(staffId));
		// model.addAttribute("division", commonService.getdivision(staffId));
		model.addAttribute("subjectlist", commonService.getAssigendStaffSubject(staffId));
		return "student.takeAttendance";
	}

	@RequestMapping(value = WebURIConstants.GET_MONTHLY_ATTENDANCE, method = RequestMethod.GET)
	public void getSemister(@PathVariable("staff_sub_id") Integer staffSub_id, HttpServletResponse response)
			throws IOException
	{
		PrintWriter out;
		List<Object> listOfMonth = new ArrayList<>();

		AcademicSemester semister = commonService.getYearSemester(3);
		String[] s = semister.getSemesterStartDate().toString().split("-");
		String[] s2 = semister.getSemesterEndDate().toString().split("-");
		LocalDate date1 = LocalDate.of(Integer.parseInt(s[0]), Integer.parseInt(s[1]), 01);
		LocalDate date2 = LocalDate.of(Integer.parseInt(s2[0]), Integer.parseInt(s2[1]), 20);

		while (date1.isBefore(date2))
		{
			listOfMonth.add(Month.of(date1.getMonthValue()).name());
			listOfMonth.add(commonService.getMonthlyAttenedance(date1, staffSub_id));
			date1 = date1.plus(Period.ofMonths(1));
		}
		out = response.getWriter();
		out.write(new Gson().toJson(listOfMonth.toString()));

	}

	@RequestMapping(value = WebURIConstants.ATTENDANCE_BY_SUBJECT, method = RequestMethod.GET)
	public void showAttendanceBySubject(@PathVariable("staff_sub_id") Integer staffSub_id, HttpServletResponse response)
			throws IOException
	{
		logger.debug("Student Attendance");
		PrintWriter out;
		List<ComAttendance> dbAttendance = commonService.getAttendanceBySubject(staffSub_id);
		out = response.getWriter();
		out.write(new Gson().toJson(dbAttendance.toString()));
	}

	@RequestMapping(value = WebURIConstants.TIMETABLE_SUBJECT, method = RequestMethod.POST)
	public void getTimeTableSubject(@RequestParam("date") String date, HttpServletResponse response) throws IOException
	{
		logger.debug("Time Table");
		PrintWriter out;
		List<TimeTableDTO> dbAttendance = commonService.getTimetableSubject(date);
		out = response.getWriter();
		out.write(new Gson().toJson(dbAttendance));
	}

	@RequestMapping(value = WebURIConstants.GET_ACADEMIC_SUBJECT, method = RequestMethod.GET)
	public @ResponseBody String getAcademicSubject(@PathVariable("empno") Integer staffEmpId) throws ParseException
	{
		return commonService.getAcademicSubjectByStaff(staffEmpId).toString();
	}

	@RequestMapping(value = WebURIConstants.GET_STUDENT_LIST_FOR_ATTENDANCE, method = RequestMethod.POST)
	public @ResponseBody List<ComClientNameDTO> getStudentListForAttendane(@RequestParam("SubjectId") Integer subjectId,
			@RequestParam("division") String devision, @RequestParam("attendanceid") int attendanceid,
			@RequestParam(value = "batchID", required = false) String batchID) throws ParseException
	{

		return commonService.getAcademicStdList(subjectId, devision, attendanceid, batchID);

	}

	/*
	 * @RequestMapping(value = WebURIConstants.GmailLogin, method =
	 * RequestMethod.POST) public String getAuth(HttpServletRequest req) { String
	 * idToken = req.getParameter("id_token"); String email =
	 * req.getParameter("email");
	 *
	 * return "redirect:/" + WebURIConstants.LIST_NOTIFICATIONS; }
	 */

	@RequestMapping(value = WebURIConstants.SAVE_STUDENT_ATTENDANCE, method = RequestMethod.POST)
	public String getOCR(@RequestParam("ExcelFile") MultipartFile file, @RequestParam("empid") Integer empid,
			HttpServletRequest servletRequest) throws Exception
	{

		String name = "AttendanceSheet";
		ModelAndView mv = new ModelAndView("student.attendance");
		byte[] bytes = file.getBytes();

		String msg = null;
		ProcessBuilder builder = null;
		Process p = null;
		File fileLocation = new File("/usr/share/tesseract-ocr/Attendance.jpg");
		Path path = Paths.get(fileLocation.getAbsolutePath());
		if (!fileLocation.exists())
		{
			fileLocation.createNewFile();
			Files.write(path, bytes);
		} else
		{
			Files.write(path, bytes);
		}

		try
		{
			builder = new ProcessBuilder("/bin/bash", "-c",
					"/bin/bash -c '/usr/bin/tesseract" + " " + fileLocation + " " + name + "'");
			builder.directory(new File("/lib"));
			builder.redirectErrorStream(true);
			p = builder.start();
			p.waitFor();

		} catch (Exception e)
		{
			msg = e.getLocalizedMessage();
		}
		// msg = saveStudentAttendance(name);
		mv.addObject("message", msg);
		return "redirect:" + empid;
	}

	@RequestMapping(value = WebURIConstants.MENTOR_PROFILE, method = RequestMethod.GET)
	public String mentorProfilePage(@PathVariable("client_id") Integer client_id, Map<String, Object> map, Model model)
	{
		Mentor mentor = new Mentor();
		map.put("mentorProfile", mentor);
		model.addAttribute("stafflist", staffService.getStaffListbydept(client_id));
		model.addAttribute("studentlist", studentService.getStudentList());
		return "admin.mentorprofile";
	}

	@RequestMapping(value = WebURIConstants.GET_STUDENT_LIST, method = RequestMethod.POST)
	public @ResponseBody List<Student> getStudentListForEvent(@RequestParam("yr") Integer yr,
			@RequestParam("div") Integer div, @RequestParam("client_id") Integer client_id)
	{

		logger.info("Get Student list ");
		List<Student> stdlist = studentService.getStudentbysortList(yr, div, client_id);
		return stdlist;
		// return null;
	}

	@RequestMapping(value = WebURIConstants.CREATE_PROFILE, method = RequestMethod.POST)
	public String mentorProfileSave(@ModelAttribute("mentorProfile") Mentor mentor, Integer[] students, Model model)
			throws WebException
	{
		System.out.println("mentorididdddddddddd" + mentor.getId());

		try
		{
			if (staffService.createMentorProfile(mentor, students))
			{
				// menotr data in group
				commonService.savegroup(mentor, students);
				model.addAttribute("message", "Profile Create Succesfully..!");
			} else
			{
				model.addAttribute("errorMsg", "Error occured while creating Profile...!");
			}
		} catch (Exception e)
		{
			throw new WebException(e.getMessage(), HttpStatus.BAD_REQUEST.value(), WebURIConstants.LIST_NOTIFICATIONS);
		}
		return "redirect:" + WebURIConstants.LIST_NOTIFICATIONS;
	}

	@RequestMapping(value = WebURIConstants.MENTOR_CHAT_BOX, method = RequestMethod.GET)
	public String getMentorChat(@PathVariable("id") Integer id, Model model)
	{
		model.addAttribute("notices", notificationService.getChatNotification(id));
		model.addAttribute("mentor", studentService.getStudentListByStaffId(id));
		return "admin.mentorchatbox";
	}

	@RequestMapping(value = WebURIConstants.REMOVE_MENTOR_MEMBER, method = RequestMethod.GET)
	public String getStudentRemove(@PathVariable("id") Integer id, Model model)
	{
		model.addAttribute("members", studentService.getStudentListbyid(id));
		return "admin.mentorstudentremove";

	}

	@RequestMapping(value = WebURIConstants.DELETE_MENTOR_MEMBER, method = RequestMethod.POST)
	public String removeFormMentor(@RequestParam(value = "member", required = true) Integer[] member, Model model)
	{
		System.out.println("mentoridddddd" + member);
		return "redirect:/web/taskforce/service/mentor/d/" + studentService.deleteMentorStudent(member);
	}

	@RequestMapping(value = WebURIConstants.UPLOAD_RESULT_FILE, method = RequestMethod.GET)
	public String uploadResultFile1(Model model)
	{
		model.addAttribute("departmets", commonService.getDepartmentList());
		model.addAttribute("academicYear", commonService.getAcademiYear());

		// model.addAttribute("subject",
		// commonService.getAcademicSubjectByStaff(id));
		return "college.result";

	}

	@RequestMapping(value = WebURIConstants.SAVE_RESULT_FILE, method = RequestMethod.POST)
	public String saveResultFile(@RequestParam("ResultPDFfile") MultipartFile file, HttpServletRequest request)
	{
		PdfReader reader;
		try
		{
			reader = new PdfReader(file.getBytes());

			for (int i = 1; i <= reader.getNumberOfPages(); i++)
			{
				// Page number is i
				String textFromPage = PdfTextExtractor.getTextFromPage(reader, i);
				int x = 0;
				for (String element : textFromPage.trim().replaceAll("( )+", " ").split("\\n"))
				{

					helper.newsave(element, x);
					x++;
				}
			}
			reader.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		Staff user = (Staff) request.getSession().getAttribute("user");

		/*
		 * return "redirect:" + "/web/taskforce/service/result/upload/" +
		 * user.getEmployeeNo();
		 */
		return "admin.resultStaffpage";
	}

	@RequestMapping(value = WebURIConstants.GET_RESULT_SUMMARY, method = RequestMethod.GET)
	public void getResultSummary(@RequestParam("yr") Integer yr, @RequestParam("sem") Integer sem,
			@RequestParam("depid") Integer depid, @RequestParam("sub") Integer sub, HttpServletResponse response)
			throws IOException
	{
		int fail = 0;
		int atkt = 0;
		List<Object[]> obj = commonService.getResultSummary(yr, sem, depid, sub);
		for (Object[] objects : obj)
		{
			System.out.println("resulkt.........." + objects);

			for (Object object : objects)
			{
				System.out.println("fail modal..............." + object);
			}

			Long count = (Long) objects[1];
			System.out.println("printing:::::::" + count);
			if (count <= 3)
			{
				atkt++;
			} else
			{
				fail++;
			}
		}
		PrintWriter out;
		List<Object> result = new ArrayList<>();

		result.add(commonService.getAllStudentListFromMarksheet(depid, sem, sub).size());
		result.add(fail);
		result.add(atkt);
		out = response.getWriter();
		out.write(new Gson().toJson(result.toString()));
	}

	@RequestMapping(value = WebURIConstants.GET_SUBJECT_RESULT, method = RequestMethod.GET)
	public void getSemister(@RequestParam("yr") Integer exam_yr, @RequestParam("sem") Integer sem,
			@RequestParam("depid") Integer depid, @RequestParam(value = "sub", required = false) Integer sub,
			HttpServletResponse response) throws Exception
	{
		PrintWriter out;
		List<Object> listOfSubject = new ArrayList<>();
		List<Object[]> o = commonService.getSubjectResult(sem, depid, exam_yr, sub);
		for (Object[] objects : o)
		{
			listOfSubject.add(objects[0]);
			listOfSubject.add(objects[1]);
		}
		out = response.getWriter();
		out.write(new Gson().toJson(listOfSubject.toString()));
	}

	// Time Table
	@RequestMapping(value = WebURIConstants.SET_TIME_TABLE, method = RequestMethod.GET)
	public String timeTableView(Map<String, Object> map)
	{
		TimeTable timeTable = new TimeTable();
		map.put("setTimeTable", timeTable);
		map.put("departments", notificationService.getDepartment());
		map.put("stafflist", staffService.getStaffList());
		map.put("daylist", staffService.getDayList());
		map.put("Semisterlist", staffService.getSemisterList());
		// map.put("yearList", staffService.getyearList());
		return "admin.timetable";
	}

	@RequestMapping(value = SIMSURIConstants.GET_SUB_LIST, method = RequestMethod.POST)
	public @ResponseBody List<SubjectList> getSubjectdata(@RequestParam("depId") Integer dep,
			@RequestParam("semId") Integer sem)
	{
		List<SubjectList> sublist = commonService.getSubjectdata(dep, sem);
		return sublist;
	}

	@RequestMapping(value = WebURIConstants.SUBJECT_LIST_FOR_TIMETABLE, method = RequestMethod.POST)
	public @ResponseBody List<AcademicSubject> getSubjectForTimeTable(@RequestParam("depId") Integer dep,
			@RequestParam("semId") Integer sem)
	{
		List<AcademicSubject> sublist = commonService.getAcademicSubData(dep, sem);
		return sublist;
	}

	@RequestMapping(value = WebURIConstants.TIME_TABLE_INSERT, method = RequestMethod.POST)
	public String InsertTimeTable(@ModelAttribute(value = "setTimeTable") TimeTable timeTable, Model model,
			Map<String, Object> map)
	{
		model.addAttribute("timeTableResponse", timeTable);
		/*
		 * TimeTable timeTable1 = new TimeTable(); map.put("setTimeTable", timeTable1);
		 * map.put("departments", notificationService.getDepartment());
		 * map.put("stafflist", staffService.getStaffList()); map.put("daylist",
		 * staffService.getDayList()); map.put("Semisterlist",
		 * staffService.getSemisterList()); map.put("yearList",
		 * staffService.getyearList()); return "admin.timetable";
		 */
		commonService.insertTimeTable(timeTable);
		// return "admin.timetable";
		return "redirect:" + WebURIConstants.SET_TIME_TABLE;
	}

	@RequestMapping(value = WebURIConstants.TIME_TEACHER_LIST_TIMETABLE, method = RequestMethod.POST)
	public @ResponseBody String getSubjectTecher(@RequestParam(value = "SubId") Integer subId)
	{
		String comStaffSubject = commonService.getSubjectTeacherdata(subId);
		return comStaffSubject;

	}

	@RequestMapping(value = WebURIConstants.ADD_SUBJECT, method = RequestMethod.GET)
	public String getSubject(ModelMap model)
	{
		model.addAttribute("departments", notificationService.getDepartment());
		return "admin.Add_Subject";
	}

	@RequestMapping(value = WebURIConstants.INSERT_SUBJECT, method = RequestMethod.POST)
	public String getInsertSubjectData(@RequestParam(value = "department") Integer depId,
			@RequestParam(value = "semister") Integer semId, @RequestParam(value = "subjectname") Integer[] subject)
	{

		commonService.addSubjectdata(depId, semId, subject);
		return "redirect:" + WebURIConstants.ADD_SUBJECT;
	}

	@RequestMapping(value = WebURIConstants.ASSIGN_SUBJECT, method = RequestMethod.GET)
	public String getteacherList(ModelMap model)
	{
		model.addAttribute("stafflist", staffService.getStaffList());
		model.addAttribute("subjectList", commonService.getSubjectToAssign());

		return "admin.SubjectAssign";
	}

	@RequestMapping(value = WebURIConstants.INSEART_ASSIGN_SUBJECT, method = RequestMethod.POST)
	public String getAddinglist(@RequestParam("subject_id") Integer[] subid,
			@RequestParam("client_id") Integer clientid, Model model)

	{

		commonService.insertTeacherSubject(subid, clientid);
		return "redirect:" + WebURIConstants.ASSIGN_SUBJECT;// "admin.SubjectAssign";
	}

	@RequestMapping(value = WebURIConstants.GET_TIME_TABLE, method = RequestMethod.GET)
	public String dispalyTimeTable(@PathVariable("year") Integer yearId, ModelMap model)
	{
		model.addAttribute("timeTable", commonService.getTimeTable(yearId));
		return "admin.displaytimetable";
	}

	@RequestMapping(value = WebURIConstants.STUDENT_ACTIVATION, method = RequestMethod.GET)
	public String getStudList(ModelMap model)
	{
		model.addAttribute("studlist", studentService.getVerifyingStud());
		model.addAttribute("dept", commonService.getDepartmentList());
		return "admin.StudentVerification";

	}

	@RequestMapping(value = WebURIConstants.INSERT_ATTENDANCE, method = RequestMethod.POST)
	public String addAttendance(@ModelAttribute(value = "comAttendance") ComAttendance comAttendance,
			@RequestParam(value = "studId", required = false) Integer[] studId,
			@RequestParam(value = "subjectId") Integer subjectId,
			@RequestParam(value = "attendanceid") Integer attendanceid,
			@RequestParam(value = "batch", required = false) String batch, ModelMap model)
	{
		int status = 0;
		if (attendanceid == 1)
		{
			status = commonService.addAttendance(comAttendance, studId, subjectId);

		} else
		{
			status = commonService.addPracticalAttendance(comAttendance, studId, subjectId, batch);
		}
		return "redirect:/web/taskforce/student/set/attendance/" + status;

	}

	@RequestMapping(value = WebURIConstants.DAILY_ATTENDANCE, method = RequestMethod.GET)
	public String addAttendance(@PathVariable("clientId") Integer staffid, ModelMap model)
	{
		model.addAttribute("subjectlist", commonService.getAssigendStaffSubject(staffid));
		// model.addAttribute("department",
		// commonService.getDepartmentListForAttendance(staffid));
		return "admin.dailyAttendance";
	}

	@Secured(
	{ "ROLE_HOD", "ROLE_ADMIN" })
	@RequestMapping(value = WebURIConstants.DAILY_ATTENDANCE_REPORT, method = RequestMethod.GET)
	public String dailyAttendanceReport(@PathVariable("clientId") Integer staffid, ModelMap model,
			@RequestParam(value = "status", required = false) Boolean status)
	{

		model.addAttribute("status", status);
		model.addAttribute("department", commonService.getDepartmentListForAttendance(staffid));

		return "admin.dailyAttendanceReport";
	}

	@RequestMapping(value = WebURIConstants.GET_DAILY_ATTENDANCE_DATA, method = RequestMethod.POST)
	public String getDailyAttendanceData(@ModelAttribute(value = "comAttendance") ComAttendance comAttendance,
			@RequestParam(value = "subjectId") int subjectId, @RequestParam(value = "Reportid") int id,
			@RequestParam(value = "batch", required = false) String batch, ModelMap model)
	{
		AttendanceDTOWeb comAttendances = null;
		System.out.println(batch + "batch......");
		if (id == 1)
		{
			comAttendances = commonService.getDailyAttendanceData(comAttendance, subjectId);

		} else
		{
			comAttendances = commonService.getPracticalDailyAttendanceData(comAttendance, subjectId, batch);

		}
		model.addAttribute("comAttendances", comAttendances);
		return "admin.dailyAttendanceDisplay";
	}

	@Secured(
	{ "ROLE_HOD", "ROLE_ADMIN" })
	@RequestMapping(value = WebURIConstants.DAILY_ATTENDANCE_REPORT_DOWNLOAD, method = RequestMethod.POST)
	public ModelAndView dailyAttendanceReportDownload(@RequestParam(value = "Reportid") int reportid,
			@RequestParam(value = "batch") String batch, @RequestParam(value = "theId") int theId,
			@RequestParam(value = "todate", required = false) String todate,
			@ModelAttribute(value = "comAttendance") ComAttendance comAttendance, ModelMap model,
			HttpServletRequest request)
	{

		System.out.println("msg");
		System.out.println(".........batch ...." + batch);
		Staff user = (Staff) request.getSession().getAttribute("user");
		Boolean status = false;
		ModelAndView model1 = new ModelAndView("admin.dailyAttendanceReport");
		if (theId == 1)
		{
			System.out.println("hii");
			status = commonService.dailyAttendanceReportDownload(comAttendance, reportid, todate);

		} else
		{
			status = commonService.daily_PracticalAttendanceReportDownload(comAttendance, reportid, todate, batch);
		}
		model1.addObject("status", status);
		model1.setViewName("redirect:/web/taskforce/student/attendance/daily/report/" + user.getStaffId());
		return model1;

	}

	@RequestMapping(value = WebURIConstants.UPDATE_STUDENT_STATUS, method = RequestMethod.GET)
	public String UpdateVerifyStudent(@PathVariable("id") Integer id, ModelMap model)
	{

		Boolean result = studentService.updateverifystud(id);
		if (result == true)
		{
			model.addAttribute("msg", "Student Verify Successfully...!");
			return "redirect:" + WebURIConstants.STUDENT_ACTIVATION;
		} else
		{
			model.addAttribute("msg", "Error...!");
			return "redirect:" + WebURIConstants.STUDENT_ACTIVATION;
		}
	}

	@RequestMapping(value = WebURIConstants.DELETE_STUDENT_STATUS, method = RequestMethod.GET)
	public String deleteVerifyStudent(@PathVariable("id") Integer id, ModelMap model)
	{

		Boolean result = studentService.deleteverifystud(id);
		if (result == true)
		{
			model.addAttribute("msg", "Student deleted Successfully...!");
			return "redirect:" + WebURIConstants.STUDENT_ACTIVATION;
		} else
		{
			model.addAttribute("msg", "Error...!");
			return "redirect:" + WebURIConstants.STUDENT_ACTIVATION;
		}
	}

	@RequestMapping(value = WebURIConstants.ASSIGN_PRACTICAL_BATCH, method = RequestMethod.GET)
	public String geting_practical_info(@PathVariable("staffId") Integer staffid, ModelMap model)
	{

		model.addAttribute("department", commonService.getDepartmentListForAttendance(staffid));

		return "admin.assignpractical";
	}

	@RequestMapping(value = WebURIConstants.GET_DIVISION_FOR_BATCH, method = RequestMethod.POST)
	public @ResponseBody List<String> getDivision_For_Batch(@RequestParam("depId") Integer depId,
			@RequestParam("classId") Integer classId, ModelMap model)
	{
		List<String> list = commonService.getDivision_For_Batch(depId, classId);

		return list;

	}

	@RequestMapping(value = WebURIConstants.GET_STUDENTLIST_FOR_BATCH, method = RequestMethod.POST)
	public @ResponseBody List<ComClientNameDTO> getStudentFor_Batch(@RequestParam("depId") Integer depId,
			@RequestParam("classId") Integer classId, @RequestParam("divId") String divId, ModelMap model)
	{
		List<ComClientNameDTO> list = commonService.getStudent_For_Batch(depId, classId, divId);
		return list;
	}

	@RequestMapping(value = WebURIConstants.STUDENTLIST_BATCH_ADD, method = RequestMethod.POST)
	public String add_Student_Batch(@ModelAttribute(value = "batchDetails") BatchList batchList,
			@RequestParam(value = "depaId") int depId, @RequestParam(value = "classId") int classId,
			@RequestParam(value = "divId") String divId, ModelMap map)
	{

		int id = commonService.add_Student_Batch(batchList, depId, classId, divId);

		return "redirect:/web/taskforce/practical_batch/" + id;
	}

	@RequestMapping(value = WebURIConstants.GET_PRACTICAL_INFO, method = RequestMethod.POST)
	public @ResponseBody Map<Integer, String> getSubListforbatch(@RequestParam("staffId") Integer staffId,
			@RequestParam("sem") Integer sem)
	{

		System.out.println(commonService.getpracticalSubdata(staffId, sem));
		return commonService.getpracticalSubdata(staffId, sem);

	}

	@RequestMapping(value = WebURIConstants.SUBJECT_FILE, method = RequestMethod.GET)
	public String getSubjectFileUploadPage(@RequestParam(value = "message", required = false) String message,
			ModelMap model)
	{
		logger.debug("calling notification form page");
		// model.addAttribute("message", message);
		// model.addAttribute("departments",
		// notificationService.getDepartment());
		return "admin.subjectfile";
	}

	@RequestMapping(value = WebURIConstants.ADD_XCEL_SUBJECT_LIST, method = RequestMethod.POST)
	public String saveNoticeFile(@RequestParam("SubjectExcelFile") MultipartFile excelFile, Model model)

	{

		String message = "Please Contact to Support team for this error. ";
		try
		{
			message = commonService.saveSubjectListViaExcelFile(excelFile);
		} catch (Exception e)
		{
			message = message + e.getMessage();
		}
		model.addAttribute("message", message);

		return "redirect:" + WebURIConstants.SUBJECT_FILE;
	}

	@RequestMapping(value = WebURIConstants.FEEDBACK_FILE, method = RequestMethod.GET)
	public String getfeedbackFileUploadPage(@RequestParam(value = "message", required = false) String message,
			ModelMap model)
	{
		logger.debug("calling feedback form page");
		// model.addAttribute("message", message);
		// model.addAttribute("departments",
		// notificationService.getDepartment());
		return "admin.feedbackquefile";
	}

	@RequestMapping(value = WebURIConstants.ADD_EXCEL_FEEDBACK_LIST, method = RequestMethod.POST)
	public String savefeedbackqueFile(@RequestParam("FeedbackExcelFile") MultipartFile excelFile, Model model)

	{

		String message = "Please Contact to Support team for this error. ";
		try
		{
			message = commonService.saveFeedbackListViaExcelFile(excelFile);
		} catch (Exception e)
		{
			message = message + e.getMessage();
		}
		model.addAttribute("message", message);

		return "redirect:" + WebURIConstants.FEEDBACK_FILE;
	}

	@RequestMapping(value = WebURIConstants.GET_DIVISION, method = RequestMethod.POST)
	public @ResponseBody List<String> getDivisionForAttendance(@RequestParam("subId") Integer subId,
			@RequestParam("clientID") Integer clientId)
	{

		List<String> list = commonService.getdivision(clientId, subId);
		return list;
	}

	@RequestMapping(value = WebURIConstants.GET_DIVISION_FOR_REPORT, method = RequestMethod.POST)
	public @ResponseBody List<String> getDivisionAtten_Report(@RequestParam("deptId") Integer deptId,
			@RequestParam("yearId") Integer yearId)
	{

		List<String> list = commonService.getDivisionAtten_Report(deptId, yearId);
		return list;
	}

	@RequestMapping(value = WebURIConstants.GET_PRACTICAL_BATCH, method = RequestMethod.POST)
	public @ResponseBody List<String> getPractical_Batch(@RequestParam("subId") Integer subId,
			@RequestParam("division") String division)
	{

		List<String> list = commonService.getPractical_Batch(subId, division);
		return list;
	}

	@RequestMapping(value = WebURIConstants.GET_PRACTICAL_BATCH_FOR_REPORT, method = RequestMethod.POST)
	public @ResponseBody List<String> getPractical_Batch_forAtt_Report(@RequestParam("deptId") Integer deptId,
			@RequestParam("yearId") Integer yearId, @RequestParam("divId") String divId)
	{

		List<String> list = commonService.getPractical_Batch_forAtt_Report(deptId, yearId, divId);
		return list;
	}

	@RequestMapping(value = WebURIConstants.MENTER_STUD_PROFILE, method = RequestMethod.GET)
	public String getmenterProfile(@PathVariable("id") Integer ClientId, ModelMap model)
	{

		// logger.debug("student profile update page");
		// Student stud = studentService.getStudentDetails(ClientId);
		// model.addAttribute("departments",
		// notificationService.getDepartment());
		// model.addAttribute("studprofile", stud);
		// model.addAttribute("parentprofile",
		// studentService.getParentDetails(ClientId));
		// model.addAttribute("feeDetails",
		// commonService.getFeeDetails(ClientId));
		// model.addAttribute("educationDtl",
		// commonService.getEducationDetails(ClientId));

		logger.debug("student profile update page");
		Student stud = studentService.getStudentDetails(ClientId);
		model.addAttribute("departments", notificationService.getDepartment());
		model.addAttribute("studprofile", stud);
		model.addAttribute("parentprofile", studentService.getParentDetails(ClientId));
		model.addAttribute("feeDetails", commonService.getFeeDetails(ClientId));
		model.addAttribute("educationDtl", commonService.getEducationDetails(ClientId));
		// model.addAttribute("vpa",
		// commonService.getValueAddedProgam(ClientId));
		model.addAttribute("GfmInfo", commonService.getMentorListByStudent(ClientId));
		// model.addAttribute("currentAttendance",
		// commonService.getStudentCurrentAttendance(ClientId));
		model.addAttribute("placmentstatus", commonService.getplacementroundDetails(ClientId));
		List<ExtraActivities> activities = commonService.getStudentExtraActivities(ClientId);

		List<ExtraActivities> departmetalProgram = activities.stream().filter(x -> x.getEventType() == 1)
				.collect(Collectors.toList());
		List<ExtraActivities> extraActivities = activities.stream().filter(x -> x.getEventType() == 2)
				.collect(Collectors.toList());
		List<ExtraActivities> sportActivity = activities.stream().filter(x -> x.getEventType() == 3)
				.collect(Collectors.toList());
		List<ExtraActivities> paperPresentaion = activities.stream().filter(x -> x.getEventType() == 4)
				.collect(Collectors.toList());
		List<ExtraActivities> semenars = activities.stream().filter(x -> x.getEventType() == 5)
				.collect(Collectors.toList());
		List<ExtraActivities> projects = activities.stream().filter(x -> x.getEventType() == 6)
				.collect(Collectors.toList());

		List<ExtraActivities> inranship = activities.stream().filter(x -> x.getEventType() == 8)
				.collect(Collectors.toList());

		List<ExtraActivities> vpa = activities.stream().filter(x -> x.getEventType() == 7).collect(Collectors.toList());

		model.addAttribute("inranship", inranship);
		model.addAttribute("vpa", vpa);// commonService.getValueAddedProgam(ClientId)
		model.addAttribute("departmetalProgram", departmetalProgram);
		model.addAttribute("extraActivities", extraActivities);
		model.addAttribute("sportActivity", sportActivity);
		model.addAttribute("paperPresentaion", paperPresentaion);
		model.addAttribute("semenars", semenars);
		model.addAttribute("projects", projects);

		return "admin.MenterProfile";
	}

	@RequestMapping(value = WebURIConstants.UPDATE_STUDENT_PROFILE_PAGE, method = RequestMethod.GET)
	public String UpdateStudentProfile(@PathVariable("Id") Integer ClientId, Model model)
	{
		logger.debug("student profile update page");

		ComClientName clientName = studentService.getStudentData(ClientId);
		model.addAttribute("student", clientName);
		Student student = studentService.getStudentDetails(ClientId);
		model.addAttribute("stud", student);
		model.addAttribute("dep", notificationService.getDepartment());
		model.addAttribute("parentprofile", studentService.getParentDetails(ClientId));

		return "admin.StudentProfile";
	}

	@RequestMapping(value = WebURIConstants.student_info_update, method = RequestMethod.POST)
	public String getsubmitstudentdata(@ModelAttribute("studentform") StudentForm stud)
	{
		logger.debug("update student profile");

		Integer s = studentService.updateStudentform(stud);
		return "redirect:/web/taskforce/service/result/menterstudprof/" + s;
	}

	@RequestMapping(value = WebURIConstants.UPDATE_STAFF_PROFILE, method = RequestMethod.GET)
	public String UpdateStaffProfile(@PathVariable("Id") Integer ClientId, Model model)
	{
		model.addAttribute("staffprofile", commonService.getstaffData(ClientId));
		model.addAttribute("dep", notificationService.getDepartment());
		return "admin.updateStaffProfile";
	}

	@RequestMapping(value = WebURIConstants.SAVE_UPDATE_INFO, method = RequestMethod.POST)
	public String getsubmitstaffdata(@RequestParam("staff_id") Integer clientId,
			@ModelAttribute("stafform") StudentForm staff, Model model)
	{
		model.addAttribute("staffprofile", staffService.updatestaffData(staff, clientId));

		return "redirect:/web/taskforce/service/staffprofile/" + clientId;
	}

	@RequestMapping(value = WebURIConstants.AdminDetails, method = RequestMethod.GET)
	public String getAdmindata(ModelMap model)
	{

		logger.debug("calling  page");
		ModelAndView mode = new ModelAndView();
		Staff admin = new Staff();

		System.out.println("print information details" + admin);
		mode.addObject("Admininfo", admin);

		model.addAttribute("dept", commonService.getDepartmentList());

		return "admin.Admindetails";
	}

	@RequestMapping(value = WebURIConstants.Admin_Info, method = RequestMethod.POST)
	public String getAdmininfodata(@ModelAttribute("adminform") AdminForm admin, ModelMap model)
	{

		logger.debug("calling notification form page");
		String s = staffService.updateAdminForm(admin);

		model.addAttribute("msg", s);
		return "redirect:" + WebURIConstants.AdminDetails;
	}

	@RequestMapping(value = WebURIConstants.MENTOR_GROUP_LIST, method = RequestMethod.GET)
	public String getMgroupList(@PathVariable("id") Integer staffId, Model model, HttpServletRequest request)
	{
		logger.debug("Group List");

		/*
		 * Staff user = (Staff) request.getSession().getAttribute("user");
		 * model.addAttribute("grouplist",
		 * commonService.getGroupList(user.getComClientName().getId()));
		 */
		model.addAttribute("grouplist", commonService.getMenterGroupList(staffId));
		return "admin.mentorgrplist";
	}

	@RequestMapping(value = WebURIConstants.MENTOR_GROUP_DELETE, method = RequestMethod.GET)
	public String deleteMenterGroup(@PathVariable("grupId") Integer groupid, Model model,
			@PathVariable("staffid") Integer staffId)
	{
		System.out.println("group menteriddd" + groupid);
		commonService.deleteMenterGroup(groupid);
		logger.debug("Group List");
		model.addAttribute("grouplist", commonService.getMenterGroupList(staffId));
		return "admin.mentorgrplist";
	}

	@RequestMapping(value = WebURIConstants.FEE_DETAILS, method = RequestMethod.GET)
	public String getFeeDetails(@PathVariable("id") Integer ClientId, Model model, HttpServletRequest request)
	{

		model.addAttribute("feeDetails", commonService.getFeeDetails(ClientId));
		model.addAttribute("ClientId", ClientId);
		return "admin.getfeedetails";
	}

	@RequestMapping(value = WebURIConstants.SAVE_FEE_DETAILS, method = RequestMethod.POST)
	public String saveFeeDetails(@RequestParam("stud_id") Integer studId,
			@ModelAttribute(value = "FeeDetails") FeeDetails feeDetails, ModelMap model, HttpServletRequest request)
	{

		Integer s = commonService.SaveStudFeeDetails(studId, feeDetails);
		return "redirect:/web/taskforce/service/result/menterstudprof/" + s;
	}

	@RequestMapping(value = WebURIConstants.GET_ASSIGN_SUBJECT, method = RequestMethod.POST)
	public @ResponseBody Map<Integer, String> getAssigned_Subject(@RequestParam("id") Integer clientID)
			throws ParseException
	{
		Map<Integer, String> subList = new HashMap<>();
		List<ComStaffSubject> list = commonService.getAssigendStaffSubject(clientID);
		if (!list.isEmpty())
		{
			for (ComStaffSubject comStaffSubject : list)
			{
				subList.put(comStaffSubject.getStaff_sub_id(), comStaffSubject.getAcademicSubject().getSubject_name());
			}
		}
		return subList;

	}

	@RequestMapping(value = WebURIConstants.DELETE_ASSIGN_SUBJECT, method = RequestMethod.POST)
	public String deleteAssingedSubject(@RequestParam("clientId") Integer clientID,
			@RequestParam("subjectId") Integer subjectId) throws ParseException
	{
		commonService.deleteAssingedSubject(clientID, subjectId);
		return "redirect:" + WebURIConstants.ASSIGN_SUBJECT;

	}

	@RequestMapping(value = WebURIConstants.DELETE_STUDENT, method = RequestMethod.GET)
	public String deleteStudent(@PathVariable(value = "id") int clientID, @PathVariable(value = "depID") int depID)
	{
		studentService.deleteStudent(clientID);
		return "redirect:/web/taskforce/cims/student/list/" + depID;
	}

	@RequestMapping(value = WebURIConstants.DELETE_STAFF, method = RequestMethod.GET)
	public String deleteStaff(@PathVariable(value = "id") int clientID, @PathVariable(value = "depID") int depID)
	{
		staffService.deletestaff(clientID);
		return "redirect:/web/taskforce/stafflist/2";
	}

	@RequestMapping(value = WebURIConstants.THEROY_PERIODTIME, method = RequestMethod.POST)
	public @ResponseBody List<LectureTheoryTime> getTheroyPeriodTime() throws ParseException
	{
		return commonService.getTheoryLectureTime();

	}

	@RequestMapping(value = WebURIConstants.PRAICAL_PERIODTIME, method = RequestMethod.POST)
	public @ResponseBody List<LecturePracticalTime> getPracticalPeriodTime() throws ParseException
	{
		return commonService.getPracticalLectureTime();

	}

	@RequestMapping(value = WebURIConstants.STUDENT_PUNCH, method = RequestMethod.GET)
	public String studentPunch(Model model, @PathVariable("staffId") int staffId)
	{
		model.addAttribute("department", commonService.getDepartmentListForAttendance(staffId));
		return "admin.punchStudent";
	}

	@RequestMapping(value = WebURIConstants.STUDENT_COUNT, method = RequestMethod.POST)
	public @ResponseBody AttendancePunch getStudentCount(@RequestBody AttendancePunch punch,
			@PathVariable(value = "id") int id)
	{
		AttendancePunch attendancePunch = commonService.getStudentPunchCount(punch, id);
		return attendancePunch;
	}

	@RequestMapping(value = WebURIConstants.HELP, method = RequestMethod.GET)
	public String helpPage(Model model) throws ParseException
	{
		return "admin.helps";

	}

	@RequestMapping(value = WebURIConstants.HELP_URL, method = RequestMethod.POST)
	public @ResponseBody List<Help> helpPage(@RequestParam(value = "id") int id,
			@RequestParam(value = "menuid") int menuid) throws ParseException
	{

		return commonService.getHelpUrl(id, menuid);

	}

	@RequestMapping(value = WebURIConstants.PUNCH, method = RequestMethod.GET)
	public String studentPunch(@PathVariable(value = "id") int id, Model model) throws ParseException
	{

		AttendancePunch punch = commonService.checkStudentPunch(id);
		punch.setStudId(id);
		model.addAttribute("attendancePunch", punch);
		return "admin.punch";
	}

	@RequestMapping(value = WebURIConstants.PUNCH_ADD, method = RequestMethod.POST)
	public @ResponseBody AttendancePunch studPunchAdd(@RequestParam(value = "punchId") int punchId,
			@RequestParam(value = "studId") int studId, Model model) throws ParseException
	{
		AttendancePunch punch = commonService.studPunchAdd(punchId, studId);
		return punch;
	}

	@RequestMapping(value = WebURIConstants.INSTITUTE_INFO, method = RequestMethod.GET)
	public String institutesList(Model model)
	{

		model.addAttribute("resultlist", commonService.getInstituteResult());
		model.addAttribute("departmets", commonService.getDepartmentList());
		model.addAttribute("semsterlist", commonService.getresultsemester());
		return "admin.instituteinfo";
	}

	@RequestMapping(value = WebURIConstants.MAINMEMU_LIST_BY_ID, method = RequestMethod.POST)
	public @ResponseBody List<CollegeMenuList> institutesMenuListByID(@RequestParam("id") Integer instid, Model model)
	{

		List<CollegeMenuList> list = commonService.getInstitutemenuInfoById(instid);
		return list;
	}

	@RequestMapping(value = WebURIConstants.ADD_INSTITUTE_INFO, method = RequestMethod.POST)
	public String instituteAdd(@ModelAttribute("institutes") InstituteInfoMaster instituteInfoMaster, Model model,
			@RequestParam(value = "menuid", required = false) int[] menuiId)

	{
		commonService.instituteDataAdd(instituteInfoMaster, menuiId);
		return "redirect:" + WebURIConstants.INSTITUTE_INFO;

	}

	@RequestMapping(value = WebURIConstants.UPDATE_INSTITUTE_INFO, method = RequestMethod.POST)
	public String updateInstitute(@ModelAttribute("institutes") InstituteInfoMaster instituteInfoMaster, Model model,
			@RequestParam(value = "menuid", required = false) int[] menuiId)

	{
		commonService.updateInstitute(instituteInfoMaster, menuiId);
		return "redirect:" + WebURIConstants.INSTITUTE_INFO;
	}

	@RequestMapping(value = WebURIConstants.seubjecturl, method = RequestMethod.POST)
	public @ResponseBody List<AcademicSubject> getsubjecturl(@PathVariable("id") Integer staff_id, ModelMap model)
			throws ParseException
	{
		model.addAttribute("subject", commonService.getAcademicSubjectByStaff(staff_id));
		return null;
	}

	@RequestMapping(value = WebURIConstants.groupurl, method = RequestMethod.POST)
	public @ResponseBody List<Groups> getgroup(@PathVariable("clientid") Integer clientid, ModelMap model)
			throws ParseException
	{
		return commonService.getgroup(clientid);
	}

	@RequestMapping(value = WebURIConstants.resultpdf, method = RequestMethod.POST)
	public String resultdownload(Model model, @RequestParam(value = "yearId") int aid,
			@RequestParam(value = "branchId") int bid, @RequestParam(value = "classId") int cid)
	{
		System.out.println("staffffffffff report" + aid + "branch" + bid + "classid" + cid);
		commonService.getResultreport(aid, bid, cid);
		return "admin.resultStaffpage";
	}

	@RequestMapping(value = WebURIConstants.resultStudent, method = RequestMethod.POST)
	public String resultReportStudent(Model model, @RequestParam(value = "yearId") int aid,
			@RequestParam(value = "branchId") int bid, @RequestParam(value = "classId") int cid)
	{
		commonService.getResultReportStudent(aid, bid, cid);
		return "admin.resultStaffpage";
	}

	@RequestMapping(value = WebURIConstants.Resultstaffpage, method = RequestMethod.GET)
	public String getStaffresultpage(Model model)
	{
		model.addAttribute("departmets", commonService.getDepartmentList());
		model.addAttribute("resultlist", commonService.getpdfresultlist());
		model.addAttribute("semsterlist", commonService.getresultsemester());
		return "admin.resultStaffpage";
	}

	@RequestMapping(value = WebURIConstants.Resultpage, method = RequestMethod.GET)
	public String uploadResultFile(Model model)
	{
		model.addAttribute("departmets", commonService.getDepartmentList());
		model.addAttribute("academicYear", commonService.getAcademiYear());
		return "admin.resultpdf";
	}

	@RequestMapping(value = WebURIConstants.Resultsubject, method = RequestMethod.GET)
	public String getSubjectFileUploadPage1(@RequestParam(value = "message", required = false) String message,
			ModelMap model)
	{
		logger.debug("calling form page");
		return "admin.ResultSubject";
	}

	@RequestMapping(value = WebURIConstants.ResultSubjeclist, method = RequestMethod.POST)
	public String getSubjectelist(@RequestParam("SubjectExcelFile") MultipartFile subjectfile, Model model)
	{

		logger.debug("calling notification form page");
		String message = "Please Contact to Support team for this error. ";
		try
		{
			message = commonService.getSubjectelist(subjectfile);
		} catch (Exception e)
		{
			message = message + e.getMessage();
		}
		model.addAttribute("message", message);

		return "redirect:" + WebURIConstants.Resultsubject;

	}

	@RequestMapping(value = WebURIConstants.getsubjectlist, method = RequestMethod.POST)
	public @ResponseBody List<AcademicSubject> getresultsubjectlist(Model model,
			@RequestParam(value = "deptid") int deptid, @RequestParam(value = "classid") int classid,
			@RequestParam(value = "semid") int semid)
	{
		System.out.println("come in controller");
		List<AcademicSubject> list = commonService.getresultsubjectlist(deptid, classid, semid);
		return list;
	}

	@RequestMapping(value = WebURIConstants.GET_STUD_EDUCATIONAL_INFO_PAGE, method = RequestMethod.GET)
	public String getStudentEducationalInfoPage(@PathVariable("id") Integer ClientId, ModelMap model,
			Map<String, Object> map)
	{

		logger.debug("student profile dispay for edit page");
		EducationDetails eduDtl = new EducationDetails();
		map.put("eduDtls", eduDtl);
		model.addAttribute("educationDtl", commonService.getEducationDetails(ClientId));
		model.addAttribute("resultType", commonService.getEducationResultModel());
		return "admin.editStudentEducationalDtl";
	}

	@RequestMapping(value = WebURIConstants.UPDATE_STUD_EDUCATIONAL_INFO, method = RequestMethod.POST)
	public String updateStudentEducationalDtl(@ModelAttribute("eduDtls") EducationDetails educatinalDtl,
			Map<String, Object> map)
	{

		logger.debug("updating student placement dtl..");
		if (educatinalDtl.getStandard().equalsIgnoreCase("SSC") || educatinalDtl.getStandard().equalsIgnoreCase("HSC")
				|| educatinalDtl.getStandard().equalsIgnoreCase("DIPLOMA"))
		{
			ResultModelForPlacement resultModel = new ResultModelForPlacement();
			resultModel.setResult_id(1);
			educatinalDtl.setResultModel(resultModel);
		}
		commonService.updateStudentEducationalDtls(educatinalDtl);

		return "redirect:/web/taskforce/service/result/menterstudprof/" + educatinalDtl.getClientName().getId();
	}

	@RequestMapping(value = WebURIConstants.REMOVE_STUD_EDUCATIONAL_INFO, method = RequestMethod.POST)
	public String removeStudentEducationalDtl(@ModelAttribute("eduDtls") EducationDetails educatinalDtl,
			Map<String, Object> map)
	{

		logger.debug("updating student placement dtl..");

		commonService.removeStudentEducationalDtls(educatinalDtl);

		return "redirect:/web/taskforce/service/result/menterstudprof/" + educatinalDtl.getClientName().getId();
	}

	// .....................................TPO......................................

	@RequestMapping(value = WebURIConstants.ASSIGN_COORDINATOR, method = RequestMethod.GET)
	public String getStaffForGfmco(@PathVariable("client_id") Integer client_id, ModelMap model)
	{
		model.addAttribute("stafflistbydept", staffService.getStaffListbydept(client_id));
		model.addAttribute("allstaff", staffService.getallStaffList());
		model.addAttribute("coordinatorList", commonService.getcoordinatorlist(client_id));
		return "admin.gfmcoordinator";
	}

	@RequestMapping(value = WebURIConstants.ADD_ASSIGN_COORDINATOR, method = RequestMethod.POST)
	public String saveAssignCoordinator(@RequestParam("co_type") Integer co_type,
			@RequestParam("client_id") Integer client_id, @RequestParam("dept_id") Integer dept_id, ModelMap model)
	{
		// System.out.println("both ids........" + co_type + client_id + ".." +
		// dept_id);
		commonService.saveAssingedCoordinator(client_id, co_type, dept_id);
		return "redirect:/web/taskforce/service/assigncoordinator/" + client_id;
	}

	@RequestMapping(value = WebURIConstants.REMOVE_COORDINATOR, method = RequestMethod.GET)
	public String Removecoordinator(@PathVariable("client_id") Integer client_id, @PathVariable("cid") Integer cid)
	{
		// System.out.println(":::::::::::::::::" + client_id + "---" + cid);
		commonService.deleteAssigncoordinator(client_id, cid);
		return "redirect:/web/taskforce/service/assigncoordinator/" + client_id;

	}

	@RequestMapping(value = WebURIConstants.DEPT_TPO_LIST, method = RequestMethod.GET)
	public String getdeptTpoList(ModelMap model)
	{
		model.addAttribute("departments", notificationService.getDepartment());
		model.addAttribute("deptcoordinatorList", commonService.getdepartmentTpoList());
		return "admin.dpttoplist";
	}

	@RequestMapping(value = WebURIConstants.TPO_HOME, method = RequestMethod.GET)
	public String displayhomepage(ModelMap model)
	{
		model.addAttribute("departments", notificationService.getDepartment());
		model.addAttribute("cmpnylogo", commonService.getRecuitmentcmpnylogo());
		model.addAttribute("pastcompny", commonService.getpastcompnydtl());
		return "admin.tpohome";
	}

	@RequestMapping(value = WebURIConstants.ADD_RECRUITMENT_INFO, method = RequestMethod.POST)
	public String add_Recruitment_Info(@ModelAttribute(value = "recruitment") RecruitmentInfoDTO recruitmentInfoDTO,
			@RequestParam(value = "logoimg") MultipartFile logo,
			@RequestParam(value = "SelectRound") String[] selectionRound,
			@RequestParam(value = "typeindustry") String[] typeindustry, @RequestParam(value = "dept") String[] dept)
	{

		commonService.add_Recruitment_Info(recruitmentInfoDTO, selectionRound, logo, typeindustry, dept);
		return "redirect:" + WebURIConstants.TPO_HOME;

	}

	@RequestMapping(value = WebURIConstants.PAST_COMPANYS, method = RequestMethod.GET)
	public String displaypastcmpnylist(ModelMap model)
	{

		model.addAttribute("pastcompny", commonService.getpastcompnydtl());
		return "admin.pastcompny";
	}

	@RequestMapping(value = WebURIConstants.GET_COMPNY_BY_ID, method = RequestMethod.GET)
	public String getCompnyInfoByIDfmco(@PathVariable("id") Integer id, ModelMap model)
	{
		model.addAttribute("count", commonService.getcountround(id));
		model.addAttribute("compny", commonService.getcompnybyid(id));
		return "admin.compydtl";
	}

	@RequestMapping(value = WebURIConstants.GET_COMPNY_BY_ID1, method = RequestMethod.GET)
	public @ResponseBody RecruitmentInfoDTO getCompnyInfoByID(@PathVariable("id") Integer id)
	{
		RecruitmentInfoDTO data = commonService.getcompnybyid(id);
		return data;
	}

	@RequestMapping(value = WebURIConstants.STUDENTLIST_TPO, method = RequestMethod.GET)
	public String displayStudentTpoList(ModelMap model)
	{
		model.addAttribute("departments", notificationService.getDepartment());
		return "admin.tpostudlist";
	}

	@RequestMapping(value = WebURIConstants.GET_STUDENT_LIST_FOR_TPO, method = RequestMethod.POST)
	public @ResponseBody List<ComClientNameDTO> getStudentlistforTPO(@RequestParam(value = "dept[]") Integer[] depts,
			@RequestParam("year") Integer year, @RequestParam("tencriteria") double tencriteria,
			@RequestParam("deplocriteria") double deplocriteria, @RequestParam("degreecriteria") double degreecriteria,
			@RequestParam("backlog") Integer backlog)
	{
		return commonService.getfiltereducationalDTlofStudent(year, depts, tencriteria, deplocriteria, degreecriteria,
				backlog);

	}

	@RequestMapping(value = WebURIConstants.STUDENTLIST_DOWNLOAD, method = RequestMethod.POST)
	public String studentListDownloadforTPO(@RequestParam("year") Integer year,
			@RequestParam(value = "dept") Integer[] depts, @RequestParam("tencriteria") double tencriteria,
			@RequestParam("deplocriteria") double deplocriteria, @RequestParam("degreecriteria") double degreecriteria,
			@RequestParam("backlog") Integer backlog, ModelMap model)
	{
		commonService.tpoStudentlistDownloadyear(year, depts, tencriteria, deplocriteria, degreecriteria, backlog);
		return "redirect:" + WebURIConstants.STUDENTLIST_TPO;
	}

	@RequestMapping(value = WebURIConstants.GET_RESULT_BY_SUBJECT, method = RequestMethod.POST)
	public void getResultBySubject(@RequestParam Map<String, String> parameters, HttpServletResponse response)
			throws IOException
	{

		PrintWriter out;
		out = response.getWriter();
		out.write(new Gson().toJson(commonService.getResultBySubject(parameters)));
		// return new
		// Gson().toJson(commonService.getResultBySubject(parameters));//
		// (commonService.getResultBySubject(parameters));

	}

	@RequestMapping(value = WebURIConstants.SUB_ATTENDANCE, method = RequestMethod.POST)
	public void getAttendanceBySubject(@RequestParam Map<String, String> parameters, HttpServletResponse response)
			throws IOException
	{

		commonService.getAttendanceBySubject(parameters);
		PrintWriter out;
		out = response.getWriter();
		out.write(new Gson().toJson(null));
		// return new
		// Gson().toJson(commonService.getResultBySubject(parameters));//
		// (commonService.getResultBySubject(parameters));

	}

	@RequestMapping(value = WebURIConstants.GET_DEPARTMENT, method = RequestMethod.GET)
	public @ResponseBody List<DepartmentDTO> getdepartment()
	{
		return notificationService.getDepartment();
	}

	@RequestMapping(value = WebURIConstants.STAFFPROFILE, method = RequestMethod.GET)
	public String staffProfile(@PathVariable("id") Integer staff_id, ModelMap model)
	{
		model.addAttribute("staffprofile", commonService.getstaffData(staff_id));
		model.addAttribute("studlist", commonService.getMentorStudentList(staff_id));
		model.addAttribute("dep", notificationService.getDepartment());
		model.addAttribute("academicyr", commonService.getAcademiYear());
		model.addAttribute("que", feedbackService.getque());

		return "admin.staffprofile";
	}

	@RequestMapping(value = WebURIConstants.STAFF_YEAR_INFO, method = RequestMethod.GET)
	public String staffProfileInfo(@PathVariable("id") Integer staff_id, @PathVariable("option") String option,
			@PathVariable("dt") String dt, ModelMap model)
	{
		model.addAttribute("staffprofile", commonService.getstaffData(staff_id));
		model.addAttribute("studlist", commonService.getMentorStudentList(staff_id));
		model.addAttribute("dep", notificationService.getDepartment());
		model.addAttribute("academicyr", commonService.getAcademiYear());
		model.addAttribute("que", feedbackService.getque());
		model.addAttribute("subjectlist", commonService.getAcademicSubjectByStaff(staff_id, dt, option));
		model.addAttribute("eventIncharge", commonService.getStaffPartipationlist(staff_id, dt, option));
		model.addAttribute("mentorMember", commonService.getMentorStudentList(staff_id));
		model.addAttribute("grevenceMember", grievanceService.getGC_Members_List(staff_id));

		return "admin.staffprofile";
	}

	@RequestMapping(value = WebURIConstants.GET_APPLYED_STUDENT_LIST, method = RequestMethod.GET)
	public String getApplyedStudentList(ModelMap model, @PathVariable("reInfoId") Integer reInfoId,
			@PathVariable("id") Integer id)
	{

		model.addAttribute("studlist", commonService.getapplyedStudentlist(reInfoId, id));
		model.addAttribute("roundID", id);
		model.addAttribute("reInfoId", reInfoId);
		String roundName = "";
		if (id == 0)
		{
			roundName = "Final Filter";
		} else
		{
			roundName = "Round 1r";
		}
		return "admin.applystud";
	}

	@RequestMapping(value = WebURIConstants.GET_PLACED_STUDENT_LIST, method = RequestMethod.GET)
	public String getplacedStudentList(ModelMap model)
	{

		model.addAttribute("studinternlist", studentService.getStudentinternshipbysortList());
		model.addAttribute("placestudlist", commonService.getplaceStudentList());
		model.addAttribute("futureinterestlist", studentService.getFutureInteresttList());
		model.addAttribute("offcampusplace", commonService.getoffcampusstudlist());
		return "admin.placedstud";
	}

	@RequestMapping(value = WebURIConstants.ADD_OFFCAMPUS_PLACE_STUDENT, method = RequestMethod.GET)
	public String getstudentoflistforaddoffcamous(ModelMap model)
	{
		model.addAttribute("studlist", studentService.getforthyrstudent());

		return "admin.offcampusstud";
	}

	@RequestMapping(value = WebURIConstants.SAVE_OFFCAMPUS_PALCE_STUDENT, method = RequestMethod.POST)
	public String addGroupPoll(@ModelAttribute(value = "offcampusplace") OffcampusPlaceStud data, Model model)
	{

		OffcampusPlaceStud offcampusPlaceStud = commonService.Saveoffcampusstudlist(data);
		return "admin.offcampusstud";
	}

	@RequestMapping(value = WebURIConstants.ADD_SELECTED_STUDENT_IN_ROUND, method = RequestMethod.POST)
	public String AddSelectedStudent(@RequestParam(value = "studId") Integer[] studId,
			@RequestParam("roundId") int roundId, @RequestParam("reInfoId") int reInfoId)
	{
		commonService.SaveAelectedStudNextRound(studId, roundId, reInfoId);
		return "redirect:/web/taskforce/service/compny/" + reInfoId;
	}

	@RequestMapping(value = WebURIConstants.UPDATE_STUDENT_ACADAMIC_YEAR, method = RequestMethod.POST)
	public String upadteStudentAcadmicYear(@RequestParam(value = "clientId") int clientId,
			@RequestParam("year") int year, @RequestParam("acYear") String acYear, @RequestParam("depId") String depId)
	{
		boolean status = commonService.upadteStudentAcadmicYear(clientId, year, acYear);
		return "redirect:/web/taskforce/cims/student/list/" + depId;
	}

	@RequestMapping(value = WebURIConstants.PASSOUT_YD_STUDENT, method = RequestMethod.GET)
	public String passoutBacklogStudent(ModelMap model)
	{
		// model.addAttribute("studlist", studentService.getforthyrstudent());
		return "admin.passOutBacklogStudent";
	}

	@RequestMapping(value = WebURIConstants.GET_PASSOUT_YD_STUDENT_LIST, method = RequestMethod.POST)
	public @ResponseBody List<ComClientNameDTO> getPassoutYearDownStudent(@RequestParam(value = "deptID") int deptID,
			@RequestParam("YearID") int YearID, @RequestParam("ACyear") String ACyear)
	{

		return commonService.getPassoutYearDownStudent(deptID, YearID, ACyear);
	}

	@RequestMapping(value = WebURIConstants.UPDATE_PASSOUT_YD_STUDENT, method = RequestMethod.POST)
	public @ResponseBody boolean updatePassoutStudent(@RequestParam(value = "clientID") int clientId,
			@RequestParam("YearID") int year, @RequestParam("ACyear") String acYear)
	{

		boolean status = commonService.upadteStudentAcadmicYear(clientId, year, acYear);

		if (status)
		{
			if ((year == 5 || year == 6))
			{
				return true;
			} else
			{
				return false;
			}
		} else
		{
			return false;
		}
	}

	@RequestMapping(value = WebURIConstants.GET_SELECTED_STUDENT, method = RequestMethod.POST)
	public @ResponseBody List<ApplyedStudentForCompany> getStaffSubject(@RequestParam("reInfoId") Integer reInfoId,
			@RequestParam("round") Integer round)
	{
		List<ApplyedStudentForCompany> studlist = commonService.getapplyedStudentlist(reInfoId, round);
		return studlist;
	}

	@RequestMapping(value = WebURIConstants.SEND_OFFER_TO_STUDENT, method = RequestMethod.POST)
	public String sendOfferStudent(@RequestParam(value = "studId") Integer[] studId,
			@RequestParam("reInfoId") Integer reInfoId)
	{
		commonService.sendOffertostudent(studId, reInfoId);
		return "redirect:/web/taskforce/service/compny/" + reInfoId;
	}

	@RequestMapping(value = WebURIConstants.GET_ROUND_LIST_PLSCEMENT, method = RequestMethod.GET)
	public @ResponseBody List<CompanySelectionRounds> getroundlist(@PathVariable("id") Integer id)
	{
		List<CompanySelectionRounds> roundlist = commonService.ggetroundlistofdrive(id);
		return roundlist;
	}

	@RequestMapping(value = WebURIConstants.STUDENTDATA, method = RequestMethod.POST)
	public List<Student> getstudentdata() throws IOException
	{
		System.out.println("come in controller");
		return commonService.getstudentdata();
	}

	@RequestMapping(value = WebURIConstants.STAFFDATA, method = RequestMethod.POST)
	public List<Staff> getstaffdata() throws IOException
	{
		System.out.println("come in controller");
		return commonService.getstaffdata();
	}

	@RequestMapping(value = WebURIConstants.getdonloads, method = RequestMethod.GET)
	public String getdwonLoad(Model model) throws IOException
	{
		return "admin.getdownload";
	}

	@RequestMapping(value = WebURIConstants.Studentdetails, method = RequestMethod.GET)
	public String getstdentdetails(ModelMap model)
	{

		logger.debug("studentdetails  page");
		ModelAndView mode = new ModelAndView();
		Student student = new Student();
		mode.addObject("studentinfo", student);
		model.addAttribute("dept", commonService.getDepartmentList());
		return "admin.studentdtl";
	}

	@RequestMapping(value = WebURIConstants.Studentupdate, method = RequestMethod.POST)
	public String getstudentupdatedata(@ModelAttribute("studentform") AdminForm student, ModelMap model)
	{

		logger.debug("calling notification form page");
		System.out.println("student");
		String s = commonService.updateStudentform(student);
		model.addAttribute("msg", s);
		return "redirect:" + WebURIConstants.Studentdetails;
	}

	@RequestMapping(value = WebURIConstants.UPDATE_PARENT_PROFILE, method = RequestMethod.GET)
	public String UpdatePArentProfile(@PathVariable("Id") Integer ClientId, Model model)
	{
		logger.debug("student profile update page");
		ComClientName clientName = studentService.getStudentData(ClientId);
		model.addAttribute("student", clientName);
		Student student = studentService.getStudentDetails(ClientId);
		model.addAttribute("stud", student);
		model.addAttribute("parentprofile", studentService.getParentDetails(ClientId));
		return "admin.ParentProfile";
	}

	@RequestMapping(value = WebURIConstants.SAVE_PARENT_INFO, method = RequestMethod.POST)
	public String saveparentdata(@ModelAttribute("studentform") StudentForm stud)
	{
		logger.debug("update student profile");
		Integer s = studentService.updateStudentform(stud);
		return "redirect:/web/taskforce/service/result/menterstudprof/" + s;
	}

	@RequestMapping(value = WebURIConstants.Studenttotalcount, method = RequestMethod.GET)
	public String getstudentcountpage(ModelMap model)
	{

		model.addAttribute("collegecount", commonService.gettotolcollegecount());
		model.addAttribute("studentcount", commonService.gettotaldepartmentcount());
		return "admin.studentcountpage";

	}

	@RequestMapping(value = WebURIConstants.totalstudcountdept, method = RequestMethod.POST)
	public @ResponseBody Map<String, List<TotalStudentCount>> getStudentCount(@RequestParam(value = "id") int id,
			ModelMap model)
	{
		return commonService.getsudenttotalcount(id);
	}

	@RequestMapping(value = WebURIConstants.GET_STUDENTLIST_FILTER, method = RequestMethod.POST)
	public @ResponseBody List<Student> getStudenfiltertlist(@RequestParam("dept") Integer depts,
			@RequestParam("year") Integer year, @RequestParam("div") Integer div)
	{

		return commonService.getfilterstudentlist(year, depts, div);

	}

	@RequestMapping(value = WebURIConstants.GET_STUDENT_ATTENDACE_BY_ID, method = RequestMethod.GET)
	public @ResponseBody List<StudentAttendanceDto> getStudAttendanceByID(@PathVariable("id") Integer id, Model model)
	{
		List<StudentAttendanceDto> data = commonService.getStudentCurrentAttendance(id);
		return data;
	}

	@RequestMapping(value = WebURIConstants.FEE_DETAILS1, method = RequestMethod.GET)
	public String getFeedtls(@PathVariable("id") Integer ClientId, @PathVariable("mid") Integer mid, Model model,
			HttpServletRequest request)
	{

		model.addAttribute("feeDetails", commonService.getFeeDetails(ClientId));
		model.addAttribute("ClientId", ClientId);
		model.addAttribute("mid", mid);
		return "admin.getfeedetails1";
	}

	@RequestMapping(value = WebURIConstants.SAVE_FEE_DETAILS1, method = RequestMethod.POST)
	public String saveFeedtl(@RequestParam("stud_id") Integer studId, @RequestParam("mid") Integer mid,
			@ModelAttribute(value = "FeeDetails") FeeDetails feeDetails, ModelMap model, HttpServletRequest request)
	{

		Integer s = commonService.SaveStudFeeDetails(studId, feeDetails);
		return "redirect:/web/taskforce/service/mentor/student/" + mid;
	}

	@RequestMapping(value = WebURIConstants.Totalstudentfeedetails, method = RequestMethod.GET)
	public String getstudentfeedetails(ModelMap model)
	{
		model.addAttribute("collegefee", commonService.getcollegetotalfee());
		model.addAttribute("feedeptcount", commonService.getfeedepartmentdtl());
		return "admin.StudentFeeDetail";
	}

	@RequestMapping(value = WebURIConstants.TotalDivStudentFee, method = RequestMethod.POST)
	public @ResponseBody Map<String, List<StudentFeeDtl>> gettotalstudentfee(@RequestParam(value = "id") int id,
			ModelMap model)
	{

		return commonService.gettotalstudentfee(id);
	}

	@RequestMapping(value = WebURIConstants.SAVE_PARENT_CALL_RECORD, method = RequestMethod.POST)
	public String saveparentcalldtl(@PathVariable("id") Integer mid, @RequestParam("studid") Integer studid,
			@RequestParam("date") Date date, @RequestParam("remark") String remark, Model model)
	{

		System.out.println(".......///....///...///" + mid + studid + date + remark);
		commonService.saveparentcall(studid, date, remark);
		return "redirect:/web/taskforce/service/mentor/student/" + mid;
	}

	@RequestMapping(value = WebURIConstants.MENTORSTAFFPROFILE, method = RequestMethod.GET)
	public String getmentorprofile(Model model)
	{
		model.addAttribute("mentprof", commonService.getmentorfprofile());
		System.out.println("mentorstudent" + model);
		return "admin.MentorStaffProfile";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(HttpSession session, Model model)
	{
		System.out.println("come in registrationpage");
		model.addAttribute("deptlist", commonService.getDepartmentList());
		return "user.register";
	}

	@RequestMapping(value = WebURIConstants.SAVE_REGISTER_DATA, method = RequestMethod.POST)
	public String getuserregistration(@ModelAttribute("saveuser") NewUserDTO user, Model model)
	{
		System.out.println("/////////////////////" + user.getEmailId() + "" + user.getContactNos());
		String m1 = "";
		UserToken userToken = new UserToken();
		ModelAndView mv = new ModelAndView("user.getEmailId()");

		String token = UUID.randomUUID().toString().toUpperCase();
		m1 = commonService.getuserregistration(user);
		model.addAttribute("m2", m1);

		System.out.println("messssssssssssssssssssss" + m1);

		ComClientName comClientName = commonService.findUserByEmail(user.getEmailId());

		if (m1.equalsIgnoreCase("emailid already exists"))
		{
			System.out.println("emailid already exists");
		} else
		{
			System.out.println("come in else loop" + m1);
			if (comClientName.getEmailId() != null)
			{
				Date dt = new Date();
				userToken.setVerificationToken(token);
				userToken.setComClientName(comClientName);
				userToken.setTokenExpiration(dt);

				UserToken tkn = commonService.saveVerificationToken(userToken);
				if (tkn != null)
				{
					try
					{
						javaSendMailService.sendnewuser(comClientName.getEmailId(), token,
								comClientName.getFirstName());
						mv.addObject("msg", "Request Email Send Successfully..!!");
					} catch (Exception e)
					{
						e.printStackTrace();
						mv.addObject("error", "Error Occured while Request email Send ..!!");
					}
				} else
				{
					mv.addObject("error", "Invalid Email Address ...!!");
				}
			} else
			{
				mv.addObject("error", "Email ID already exists ...!!");
			}
		}

		return "redirect:" + WebURIConstants.SUCCESSPAGE;
	}

	@RequestMapping(value = WebURIConstants.CHECKEMAILID, method = RequestMethod.POST)
	public @ResponseBody String getuseremailidcheck(@RequestParam(value = "email") String email, Model model)
	{
		System.out.println("CHECKEMAILID" + email);
		return commonService.getuseremailidcheck(email);
	}

	@RequestMapping(value = WebURIConstants.MESSAGE, method = RequestMethod.POST)
	public @ResponseBody String getusermessage(@RequestParam(value = "email") String email, Model model)
	{
		System.out.println("USERMESSAGE" + email);
		return commonService.getuseremailidcheck(email);
	}

	@RequestMapping(value = WebURIConstants.SUCCESSPAGE, method = RequestMethod.GET)
	public String getsucesspage(HttpSession session, Model model)
	{
		System.out.println("sucesspage");
		return "user.success";
	}

	@RequestMapping(value = WebURIConstants.REPORTLIST, method = RequestMethod.GET)
	public String getpdfresultlist(ModelMap model)
	{
		System.out.println("resultlistttttttttt");
		System.out.println("resultpdflist" + model);
		return "admin.resultStaffpage";
	}

	@RequestMapping(value = WebURIConstants.SADMINSUBREPORT, method = RequestMethod.POST)
	public String getsuperadminreport(Model model, @RequestParam(value = "yearId") int aid,
			@RequestParam(value = "branchId") int bid, @RequestParam(value = "classId") int cid,
			@RequestParam(value = "instituteid") int instid)
	{
		commonService.getsuperadminreport(aid, bid, cid, instid);
		return "null";
	}

	@RequestMapping(value = WebURIConstants.COMMON_INSTITUDE_REPORT, method = RequestMethod.GET)
	public String getinstitudereport(ModelMap model)
	{
		model.addAttribute("resultist", commonService.getcommoninstitudereport());
		model.addAttribute("departmets", commonService.getDepartmentList());
		model.addAttribute("semsterlist", commonService.getresultsemester());
		return "admin.Instituteresult";
	}

	@RequestMapping(value = WebURIConstants.COMMONSUPERADMINREPORT, method = RequestMethod.POST)
	public String getcommonsuperadmindeptreport(Model model, @RequestParam(value = "yearId") int aid,
			@RequestParam(value = "branchId") int bid, @RequestParam(value = "classId") String cid)
	{
		System.out.println("superadmin controller comingggggggg" + aid + "bracnh" + bid + "semseter" + cid);
		commonService.getcommonsuperadmindeptreport(aid, bid, cid);
		return "null";
	}

}
