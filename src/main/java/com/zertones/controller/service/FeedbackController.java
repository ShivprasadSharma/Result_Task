package com.zertones.controller.service;

import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.zertones.controller.RestURIConstants;
import com.zertones.controller.WebURIConstants;
import com.zertones.core.SampleClass;
import com.zertones.model.DataTransferObjectModel.GroupPollBeanDTO;
import com.zertones.model.DataTransferObjectModel.GroupPollOptionPercentageDTO;
import com.zertones.model.DataTransferObjectModel.Group_PollDTO;
import com.zertones.model.DataTransferObjectModel.PollBeanDTO;
import com.zertones.model.DataTransferObjectModel.PollOptionPercentageDTO;
import com.zertones.model.DataTransferObjectModel.PollsDTO;
import com.zertones.model.common.FeedbackProfile;
import com.zertones.model.common.Group_Poll;
import com.zertones.model.common.Polls;
import com.zertones.service.DeviceService;
import com.zertones.service.common.CommonService;
import com.zertones.service.common.FeedbackService;
import com.zertones.service.common.JavaSendMailService;
import com.zertones.service.common.NotificationService;
import com.zertones.service.common.PushNotificationService;
import com.zertones.service.sims.StaffService;
import com.zertones.service.sims.StudentService;
import com.zertones.utility.Responses;

@Controller
public class FeedbackController implements ServletContextAware
{
	private static final Logger		logger	= LoggerFactory.getLogger(FeedbackController.class);

	@Autowired
	private CommonService			commonService;

	@Autowired
	private NotificationService		notificationService;

	@Autowired
	private StaffService			staffService;

	private ServletContext			servletContext;

	@Autowired
	private StudentService			studentService;

	@Autowired
	private JavaSendMailService		javaSendMailService;

	private FeedbackService			feedbackService;

	private PushNotificationService	pushNotificationService;
	private DeviceService			deviceService;

	@Autowired(required = true)
	@Qualifier(value = "deviceService")
	public void setDeviceService(DeviceService deviceService)
	{
		this.deviceService = deviceService;
	}

	@Autowired(required = true)
	@Qualifier(value = "pushNotificationService")
	public void setPushNotificationService(PushNotificationService pns)
	{
		pushNotificationService = pns;
	}

	@Override
	public void setServletContext(ServletContext servletContext)
	{
		this.servletContext = servletContext;
	}

	@Autowired(required = true)
	@Qualifier(value = "feedbackService")
	public void setCommonService(FeedbackService feedbackService)
	{
		this.feedbackService = feedbackService;
	}

	// Web Calling
	@RequestMapping(value = WebURIConstants.FEEDBACK, method = RequestMethod.GET)
	public String feedbackYearList()
	{
		return "admin.FeedbackYear";
	}

	@RequestMapping(value = WebURIConstants.FEEDBACKLIST_BY_DEPT, method = RequestMethod.GET)
	public String getfeedbackList(@PathVariable("staff_id") Integer staff_id, @PathVariable("yrid") Integer yrid,
			ModelMap model)
	{
		feedbackService.getfeedbacklist(staff_id, yrid);
		model.addAttribute("feedbacklist", feedbackService.getfeedbacklist(staff_id, yrid));
		return "admin.Feedback";
	}

	@RequestMapping(value = WebURIConstants.FEEDBACK_DETAILS, method = RequestMethod.GET)
	public String getFeedbackDetails(@PathVariable("id") Integer id, @PathVariable("sem") Integer sem, ModelMap model)
	{
		feedbackService.getfeedbackdetail(id, sem);
		model.addAttribute("feedbackdetails", feedbackService.getfeedbackdetail(id, sem));
		model.addAttribute("que", feedbackService.getque());
		return "admin.FeedbackProfile";
	}

	@RequestMapping(value = WebURIConstants.FEEDBACKCHART_DETAILS, method = RequestMethod.GET)
	public String getFeedbackchartDetails(@PathVariable("id") Integer id, @PathVariable("sem") Integer sem,
			ModelMap model)
	{
		feedbackService.getfeedbackdetail(id, sem);
		model.addAttribute("feedbackdetails", feedbackService.getfeedbackdetail(id, sem));
		model.addAttribute("que", feedbackService.getque());
		return "admin.FeedbackChart";
	}

	@RequestMapping(value = WebURIConstants.GET_POLL_PAGE, method = RequestMethod.GET)
	public String getPollPage(Model model)
	{
		logger.debug("calling notification form page");
		model.addAttribute("departments", notificationService.getDepartment());
		model.addAttribute("currentPollList", feedbackService.getPoll_List());
		model.addAttribute("PastPollList", feedbackService.getPast_PollList());
		return "admin.polls";
	}

	@RequestMapping(value = WebURIConstants.ADD_POLL, method = RequestMethod.POST)
	public String addPoll(@ModelAttribute(value = "poll") Polls polls, @RequestParam("option") String[] option,
			Model model)
	{
		logger.debug("add poll");
		System.out.println("polll.........." + option.length);
		Polls polls1 = feedbackService.addPollQuestion(polls, option);
		if (polls1 != null)
		{
			pushNotificationService.sendPoll(polls1);
		}
		return "redirect:" + WebURIConstants.GET_POLL_PAGE;
	}

	@RequestMapping(value = WebURIConstants.DELETE_POLL, method = RequestMethod.GET)
	public String delete_Poll(@PathVariable(value = "id") int id, Model model)
	{
		System.out.println("id.........." + id);
		boolean sta = feedbackService.deletePoll(id);
		return "redirect:" + WebURIConstants.GET_POLL_PAGE;
	}

	@RequestMapping(value = WebURIConstants.UPDATE_POLL, method = RequestMethod.POST)
	public String udatePoll(@ModelAttribute(value = "polldata") Polls polls, Model model)
	{
		logger.debug("uddate poll");

		Polls polls1 = feedbackService.udatePoll(polls);

		return "redirect:" + WebURIConstants.GET_POLL_PAGE;
	}

	@RequestMapping(value = WebURIConstants.GET_POLL_GRAPH_RESULT, method = RequestMethod.POST)
	public @ResponseBody List<PollOptionPercentageDTO> getGraphResult(@RequestParam(value = "pollid") Integer poollId,
			Model model)
	{
		logger.debug("uddate poll");
		List<PollOptionPercentageDTO> list = feedbackService.getGraphResult(poollId);
		return list;
	}

	@RequestMapping(value = RestURIConstants.REST_ADD_POLL, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> addPoll(@RequestBody PollBeanDTO pollBeanDTO)
	{

		System.out.println("bean..." + pollBeanDTO);
		Polls polls1 = feedbackService.addRestPoll(pollBeanDTO);
		if (polls1 != null)
		{
			pushNotificationService.sendPoll(polls1);
			return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "Poll Added Sucessfully", true),
					HttpStatus.ACCEPTED);
		} else
		{
			return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "Somthing Went Wrong", false),
					HttpStatus.FAILED_DEPENDENCY);
		}

	}

	@RequestMapping(value = RestURIConstants.REST_POLL_LIST, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> pollListStaff()
	{
		List<PollsDTO> list = feedbackService.pollListStaff();
		return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "Poll List", list), HttpStatus.ACCEPTED);

	}

	@RequestMapping(value = RestURIConstants.REST_POLL_DELETE, method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<?> pollDeleteRest(@PathVariable(value = "pollId") int pollId)
	{
		boolean statu = feedbackService.deletePoll(pollId);

		if (statu)
		{
			return Responses.booleanSucessfullyResponse("Poll Deleted Successfully", statu);
		} else
		{
			return Responses.booleanFailureyResponse("Poll not Deleted", statu);

		}
	}

	@RequestMapping(value = RestURIConstants.REST_POLL_UPDATE, method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<?> pollUpdateRest(@RequestBody PollBeanDTO pollBeanDTO)
	{

		Polls polls = feedbackService.pollUpdateRest(pollBeanDTO);
		return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "updated sucessfully", polls), HttpStatus.ACCEPTED);

	}

	@RequestMapping(value = WebURIConstants.feedbackurl, method = RequestMethod.POST)
	public @ResponseBody FeedbackProfile getfeedbackdata(@RequestParam("staffid") Integer staffid,
			@RequestParam("semId") Integer semId, ModelMap model)
	{
		System.out.println(".............." + staffid + semId);
		System.out.println("feedackdetail" + feedbackService.getfeedbackdetail(staffid, semId));
		return feedbackService.getfeedbackdetail(staffid, semId);
	}

	@RequestMapping(value = WebURIConstants.pollurl, method = RequestMethod.POST)
	public @ResponseBody List<Polls> getpoll(@PathVariable("clientid") Integer clientid, ModelMap model)
	{
		System.out.println("polllllll controller.........." + clientid);
		return feedbackService.getpoll(clientid);

	}

	// .......................................Group // // Poll
	// .........................................//

	@RequestMapping(value = WebURIConstants.ADD_GROUP_POLL, method = RequestMethod.POST)
	public String addGroupPoll(@ModelAttribute(value = "gpoll") Group_Poll gpolls,
			@RequestParam("option") String[] option, @RequestParam("groupid") Integer gropid, Model model)
	{
		logger.debug("add poll");
		Group_Poll gpolls1 = feedbackService.addGroupPollQuestion(gpolls, option, gropid);
		if (gpolls1 != null)
		{
			pushNotificationService.sendGroupPoll(gpolls1);
		}
		return "redirect:/web/taskforce/groupdetails/" + gropid;
	}

	@RequestMapping(value = WebURIConstants.GET_GROUP_POLL_GRAPH_RESULT, method = RequestMethod.POST)
	public @ResponseBody List<GroupPollOptionPercentageDTO> getgrpollGraphResult(
			@RequestParam(value = "pollid") Integer poollId, Model model)
	{
		logger.debug("uddate poll");
		List<GroupPollOptionPercentageDTO> list = feedbackService.getgrpollGraphResult(poollId);
		return list;
	}

	@RequestMapping(value = RestURIConstants.GROUP_POLL_LIST_TEACHER, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> grouppollListStaff(@PathVariable("clientId") Integer id,
			@PathVariable("g_id") Integer g_id, Model model)
	{
		List<Group_PollDTO> list = feedbackService.grouppollListStaff(id, g_id);
		return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "Poll List", list), HttpStatus.ACCEPTED);

	}

	@RequestMapping(value = RestURIConstants.ADD_REST_GROUP_POLL, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> addGroupPoll(@RequestBody GroupPollBeanDTO grouppollBeanDTO)
	{

		System.out.println("bean..." + grouppollBeanDTO);

		Group_Poll polls1 = feedbackService.addRestgroupPoll(grouppollBeanDTO);

		if (polls1 != null)
		{
			pushNotificationService.sendGroupPoll(polls1);
			return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "Poll Added Sucessfully", true),
					HttpStatus.ACCEPTED);
		} else
		{
			return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "Somthing Went Wrong", false),
					HttpStatus.FAILED_DEPENDENCY);
		}

	}

	@RequestMapping(value = WebURIConstants.UPDATE_GROUP_POLL, method = RequestMethod.POST)
	public String udategroupPoll(@ModelAttribute(value = "polldata") Group_Poll gpolls,
			@RequestParam("groupid") Integer gropid, Model model)
	{
		logger.debug("uddate poll");

		System.out.println("//.//././././././." + gropid + gpolls);
		Group_Poll polls1 = feedbackService.udategroupPoll(gpolls);

		return "redirect:/web/taskforce/groupdetails/" + gropid;
	}

	@RequestMapping(value = WebURIConstants.DELETE_GROUP_POLL, method = RequestMethod.GET)
	public String delete_Group_Poll(@PathVariable(value = "id") int id, @PathVariable(value = "g_id") int g_id,
			Model model)
	{
		System.out.println("id.........." + id + g_id);
		boolean staa = feedbackService.deletegroupPoll(id);
		return "redirect:/web/taskforce/groupdetails/" + g_id;
	}

	@RequestMapping(value = RestURIConstants.REST_GROUP_POLL_UPDATE, method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<?> grouppollUpdateRest(@RequestBody GroupPollBeanDTO grouppollBeanDTO)
	{

		Group_Poll polls = feedbackService.grouppollUpdateRest(grouppollBeanDTO);
		return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "updated sucessfully", polls), HttpStatus.ACCEPTED);

	}

	@RequestMapping(value = RestURIConstants.REST_GROUP_POLL_DELETE, method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<?> grouppollDeleteRest(@PathVariable(value = "pollId") int pollId)
	{
		boolean statu = feedbackService.deletegroupPoll(pollId);

		if (statu)
		{
			return Responses.booleanSucessfullyResponse("Poll Deleted Successfully", statu);
		} else
		{
			return Responses.booleanFailureyResponse("Poll not Deleted", statu);

		}
	}

}
