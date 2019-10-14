package com.zertones.controller.sims;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.zertones.controller.RestURIConstants;
import com.zertones.controller.SIMSURIConstants;
import com.zertones.controller.WebURIConstants;
import com.zertones.core.SampleClass;
import com.zertones.core.WebException;
import com.zertones.model.ComClientName;
import com.zertones.model.ComUser;
import com.zertones.model.SubjectList;
import com.zertones.model.DataTransferObjectModel.Studentskillsdto;
import com.zertones.model.common.BatchTable;
import com.zertones.model.common.Department;
import com.zertones.model.sims.Parent;
import com.zertones.model.sims.RemarkOption;
import com.zertones.model.sims.Staff;
import com.zertones.model.sims.Student;
import com.zertones.model.sims.StudentRemark;
import com.zertones.model.sims.StudentRemarkOption;
import com.zertones.service.CommonValidationService;
import com.zertones.service.common.CommonService;
import com.zertones.service.common.NotificationService;
import com.zertones.service.sims.ParentService;
import com.zertones.service.sims.StaffService;
import com.zertones.service.sims.StudentService;

/**
 * @author Zerton Teams
 * @Created Date : Oct 6, 2015
 */

@Controller
public class SIMSRESTController
{
	private static final Logger		logger	= LoggerFactory.getLogger(SIMSRESTController.class);
	private StudentService			studentService;
	private StaffService			staffService;
	private ParentService			parentService;
	private NotificationService		notificationService;
	private CommonService			commonService;

	private CommonValidationService	commonValidationService;

	@Autowired(required = true)
	@Qualifier(value = "studentService")
	public void setCommonService(StudentService studentService)
	{
		this.studentService = studentService;
	}

	@Autowired(required = true)
	@Qualifier(value = "commonService")
	public void setCommonService(CommonService commonService)
	{
		this.commonService = commonService;
	}

	@Autowired(required = true)
	@Qualifier(value = "staffService")
	public void setStaffService(StaffService staffService)
	{
		this.staffService = staffService;
	}

	@Autowired(required = true)
	@Qualifier(value = "commonValidationService")
	public void setCommonValidationService(CommonValidationService commonValidationService)
	{
		this.commonValidationService = commonValidationService;
	}

	// @Secured("ROLE_ADMIN")
	@RequestMapping(value = SIMSURIConstants.SIGNUP_STUDENT, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> studentSignUp(@Valid @RequestBody Student student)
	{
		logger.info("Creating New Student");
		// if (student.getComClientName() != null &&
		// student.getComClientName().getComUserDetails() != null
		// && student.getComClientName().getComUserDetails().getUserName() != null
		// && student.getComClientName().getComUserDetails().getUserName().trim() != ""
		// &&
		// !commonValidationService.isUserExists(student.getComClientName().getComUserDetails().getUserName()))
		// {
		student = studentService.studentSignUp(student);
		return new ResponseEntity<>(student, HttpStatus.OK);
		// } else
		// {
		// return new ResponseEntity<>("User Already Exists", HttpStatus.BAD_REQUEST);
		// }
	}

	// @Secured("ROLE_ADMIN")
	@RequestMapping(value = SIMSURIConstants.SIGNUP_STAFF, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> staffSignUp(@Valid @RequestBody Staff staff)
	{
		// System.out.println("Swapnil ");
		logger.info("Creating New Staff");
		if (staff.getComClientName() != null && staff.getComClientName().getComUserDetails() != null
				&& staff.getComClientName().getComUserDetails().getUserName() != null
				&& staff.getComClientName().getComUserDetails().getUserName().trim() != ""
				&& !commonValidationService.isUserExists(staff.getComClientName().getComUserDetails().getUserName()))
		{
			staff = staffService.staffSignUp(staff);
			return new ResponseEntity<>(staff, HttpStatus.OK);
		} else
		{
			return new ResponseEntity<>("Staff Already Exists", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = SIMSURIConstants.REST_GET_ALL_STUDENT_RECORD, method = RequestMethod.GET)
	public @ResponseBody String getAllStudentList()
	{
		logger.info("Get All Student List");
		List<Student> AllStudentlist = studentService.getStudentDetails();
		return AllStudentlist.toString();
	}

	@RequestMapping(value = SIMSURIConstants.REST_GET_STUDENT_RECORD_ById, method = RequestMethod.GET)
	public @ResponseBody List<Student> getStudentRecordsById(@PathVariable("StudId") Integer StudId)
	{
		logger.info("Get All Student List");
		List<Student> StudentlistById = studentService.getStudentDetailsById(StudId);
		return StudentlistById;
	}

	@RequestMapping(value = SIMSURIConstants.SIGNUP_PARENT, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> parentSignUp(@RequestBody Parent parent, BindingResult result)
	{
		if (parent.getComClientName() != null && parent.getComClientName().getComUserDetails() != null
				&& parent.getComClientName().getComUserDetails().getUserName() != null
				&& parent.getComClientName().getComUserDetails().getUserName().trim() != ""
				&& !commonValidationService.isUserExists(parent.getComClientName().getComUserDetails().getUserName()))
		{
			parent = parentService.parentSignUp(parent);
			return new ResponseEntity<>(new SampleClass(HttpStatus.BAD_REQUEST, "User Not Found ", null),
					HttpStatus.NOT_FOUND);
		} else
		{
			return new ResponseEntity<>("Parent Already Exists", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = SIMSURIConstants.SAVE_STAFF_PROFIL, method = RequestMethod.POST)
	public ModelAndView updateProfile(@ModelAttribute("profileForm") Staff staff, BindingResult result,
			@RequestParam(value = "profilefile", required = false) MultipartFile image, HttpSession session)
	{
		logger.info("In Update staff Profile");
		ModelAndView mv = new ModelAndView("user.UserProfile");
		Staff stf = new Staff();
		try
		{

			stf = staffService.saveStaff(staff, image);
		} catch (Exception e)
		{
			// TODO: handle exception
			mv.addObject("message", "Update your profile  error...!!");
		}

		if (stf == null)
		{
			throw new WebException("Error Occured while updating profile", 404,
					"redirect:" + WebURIConstants.USER_PROFIEL);
		} else
		{
			mv.addObject("message", "Update your profile successfully...!!");
		}
		session.setAttribute("user", stf);

		mv.setViewName("redirect:" + WebURIConstants.USER_PROFIEL);
		return mv;
	}

	@RequestMapping(value = SIMSURIConstants.GET_LIST_STUDENT, method = RequestMethod.POST)
	public @ResponseBody List<Student> getStudentListForEvent(@RequestParam("dep") Integer dep,
			@RequestParam("cls") Integer cls)
	{
		logger.info("Get Student list ");
		List<Student> stdlist = studentService.getStudentList(dep, cls);
		return stdlist;
	}

	@RequestMapping(value = SIMSURIConstants.GET_STUDENT_LIST_BY_DEPARTMENT, method = RequestMethod.GET)
	public String getStudentListByDep(@PathVariable("depId") Integer dep, ModelMap model)
	{
		logger.info("Get Student list ");
		List<Student> stdlist = studentService.getStudentListByDept(dep);
		List<Department> listofdeprtment = studentService.getDepartment();
		model.addAttribute("studentList", stdlist);
		model.addAttribute("departments", listofdeprtment);

		return "admin.studentlist";
	}

	@RequestMapping(value = SIMSURIConstants.UPDATE_STUDENT_PROFIL, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> updateStudentProfile(@RequestBody ComClientName studentClient)
	{
		logger.info("In update student profile");
		ComClientName stu = new ComClientName();
		if (studentClient.getProfileImage() != null)
		{
			studentClient.setImgUrl(studentService.uploadImageOnCloud(studentClient.getProfileImage()));
		}
		stu = studentService.updateStudentProfile(studentClient);
		if (stu != null)
		{
			return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "Profile update successfully", stu),
					HttpStatus.ACCEPTED);
		} else
		{
			return new ResponseEntity<>(
					new SampleClass(HttpStatus.BAD_REQUEST, "Error occured while updating profile", studentClient),
					HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = RestURIConstants.SET_PASS, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> setStudentEntity(@RequestBody ComUser comUser)
	{

		ComClientName client = studentService.setCredential(comUser);
		if (client != null)
		{
			return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "Password update successfully", client),
					HttpStatus.OK);
		} else
		{
			return new ResponseEntity<>(
					new SampleClass(HttpStatus.BAD_REQUEST, "Password Not Match with old password", client),
					HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@RequestMapping(value = WebURIConstants.GET_MENTOR_LIST, method = RequestMethod.GET)
	public String getMentorlist(Model model)
	{
		logger.info("Get Mentor Profesor List");
		model.addAttribute("mentor", staffService.getMentorProfile());
		return "admin.mentorlist";
	}

	@RequestMapping(value = SIMSURIConstants.GET_STUDENTS, method = RequestMethod.GET)
	public String getMentorStudentList(@PathVariable("id") Integer id, Model model)
	{
		// System.out.println("......." + id);
		model.addAttribute("mentor", studentService.getStudentListByStaffId(id));
		model.addAttribute("fees", studentService.getfeestructureofstudent(id));
		model.addAttribute("parentdtl", studentService.getparentdtlofstudent(id));
		return "admin.mentorlist";
	}

	@RequestMapping(value = SIMSURIConstants.TIME_BATCH_TIMETABLE, method = RequestMethod.POST)
	public @ResponseBody List<BatchTable> getBatchLitst(@RequestParam("SubId") Integer SubId)
	{
		logger.info("Get Student list ");
		// System.out.println("subject list controller::");
		List<BatchTable> list = studentService.getBatchList(SubId);
		// System.out.println("list" + list);
		return list;
	}

	@RequestMapping(value = SIMSURIConstants.GET_SUBJEC_INFO, method = RequestMethod.POST)
	public @ResponseBody List<SubjectList> getSubjectList(@RequestParam("dep") Integer dep,
			@RequestParam("sem") Integer sem)
	{
		List<SubjectList> sublist = commonService.getSubjectdata(dep, sem);
		return sublist;
	}

	@RequestMapping(value = SIMSURIConstants.GFM_STUD_REMARK, method = RequestMethod.POST)
	public String getgfmrstudemark(@ModelAttribute("gfmremark") Studentskillsdto gfmremark,
			@RequestParam("mid") Integer mid, @RequestParam("studentid") Integer studentid,
			@RequestParam("techFields") String techFields, Model model)
	{
		commonService.getgfmrstudemark(gfmremark);
		return "redirect:/web/taskforce/service/mentor/student/" + mid;
	}

	@RequestMapping(value = SIMSURIConstants.REMARKLIST, method = RequestMethod.GET)
	public @ResponseBody List<RemarkOption> getliststudentremarks(@RequestParam(value = "id") int id,
			@RequestParam(value = "mid") int mentorid, Model model)
	{
		System.out.println("comes in controller remark" + id + "midddd" + mentorid);
		List<RemarkOption> list = commonService.getliststudentremarks(id, mentorid);
		return list;
	}

	@RequestMapping(value = SIMSURIConstants.SKILLIST, method = RequestMethod.GET)
	public @ResponseBody HashMap<String, List<String>> getskilllist(@RequestParam(value = "studid") int studid,
			@RequestParam(value = "mentid") int mentid, Model model)
	{
		System.out.println("studid" + studid + "mentorid" + mentid);
		HashMap<String, List<String>> map = commonService.getskilllist(studid, mentid);
		System.out.println("moddddddddddddddddddddddeeeeeeeeeeeellllll" + map);
		return map;
	}

	@RequestMapping(value = SIMSURIConstants.STUDENTREMARK, method = RequestMethod.POST)
	public String getstudentremark(@ModelAttribute("studentremark") StudentRemark studentremark,
			@RequestParam("mid") Integer mid, @RequestParam("studentid") Integer studentid, Model model)
	{
		System.out.println("mendotrid" + mid);
		commonService.getstudentremark(studentremark);
		return "redirect:/web/taskforce/service/mentor/student/" + mid;
	}

	@RequestMapping(value = SIMSURIConstants.STUDENTRMKCHECK, method = RequestMethod.GET)
	public @ResponseBody List<StudentRemarkOption> getcheckremarkliststud(@RequestParam(value = "stuid") int stuid,
			@RequestParam(value = "mid") int mentorid, Model model)
	{
		System.out.println("comes in controller remark" + stuid + "midddd" + mentorid);
		List<StudentRemarkOption> list = commonService.getcheckremarkliststud(stuid, mentorid);
		return list;
	}

	@RequestMapping(value = SIMSURIConstants.STUDENTREMARKVIEW, method = RequestMethod.GET)
	public @ResponseBody List<StudentRemarkOption> getstudentremarkview(@RequestParam(value = "stuid") int stuid,
			@RequestParam(value = "mid") int mentorid, Model model)
	{
		System.out.println("comes in controller remark" + stuid + "midddd" + mentorid);
		List<StudentRemarkOption> studentrmklist = commonService.getstudentremarkview(stuid, mentorid);
		return studentrmklist;
	}

}
