package com.zertones.controller.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
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
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.zertones.controller.RestURIConstants;
import com.zertones.controller.SIMSURIConstants;
import com.zertones.controller.WebURIConstants;
import com.zertones.core.SampleClass;
import com.zertones.core.WebException;
import com.zertones.model.ComClientName;
import com.zertones.model.DataTransferObjectModel.CommentDTO;
import com.zertones.model.DataTransferObjectModel.DepartmentDTO;
import com.zertones.model.DataTransferObjectModel.NotificationDTO;
import com.zertones.model.common.Comment;
import com.zertones.model.common.EventModel;
import com.zertones.model.common.Like;
import com.zertones.model.common.Notification;
import com.zertones.model.common.NotificationFiles;
import com.zertones.model.master.CollegeMenuList;
import com.zertones.model.sims.Staff;
import com.zertones.service.Constants;
import com.zertones.service.common.CommonService;
import com.zertones.service.common.NotificationService;
import com.zertones.service.common.PushNotificationService;
import com.zertones.service.sims.StaffService;

@Controller
public class NotificationController implements ServletContextAware
{
	private static final Logger		logger	= LoggerFactory.getLogger(NotificationController.class);

	private NotificationService		notificationService;

	private PushNotificationService	pushNotificationService;

	@Autowired
	private CommonService			commonService;

	@Autowired
	private StaffService			staffService;

	private ServletContext			servletContext;

	@Autowired(required = true)
	@Qualifier(value = "notificationService")
	public void setNotificationService(NotificationService ns)
	{
		notificationService = ns;
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

	// Web Calling
	@RequestMapping(value = WebURIConstants.ADD_NOTIFICATION, method = RequestMethod.POST)
	public String createWebNotification(@ModelAttribute("notification") Notification notification, BindingResult result,
			@RequestParam(value = "notificationFile", required = false) MultipartFile image) throws IOException
	{

		logger.info("Creating Notification.");
		NotificationFiles nf = new NotificationFiles();
		if (!image.isEmpty())
		{

			System.out.println("....file." + image);
			try
			{
				nf.setString1(notificationService.uploadImageOnCloud(image));
			} catch (Exception e)
			{
				e.getMessage();
			}
		}
		nf.setNotification(notification);
		nf.setDocument1Type(image.getContentType());
		Set<NotificationFiles> nfs = new HashSet<>();
		nfs.add(nf);
		notification.setNotificationFiles(nfs);
		Notification notice;
		try
		{

			System.out.println("notification........." + notification);

			notice = notificationService.addNotification(notification);
			if (!notice.getNotificatiosHeadline().isEmpty())
			{
				pushNotificationService.sendNotification(notice);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:" + WebURIConstants.LIST_NOTIFICATIONS;
		// return null;

	}

	@RequestMapping(value = WebURIConstants.GET_EVENT, method = RequestMethod.GET)
	public void getAllEvent(HttpServletResponse response)
	{
		logger.info("Receiving All Event.");
		List<NotificationDTO> list = notificationService.listEvent(4);
		ArrayList<EventModel> lsitevm = new ArrayList<>();

		for (NotificationDTO lst : list)
		{
			EventModel evm = new EventModel();
			evm.setTitle(lst.getNotificatiosHeadline());
			evm.setStart(lst.getNotificationFromDate());
			evm.setEnd(lst.getNotificationToDate());
			evm.setEventDtl(lst.getNotificationDetails());
			evm.setVenu(lst.getVenue());
			lsitevm.add(evm);
		}
		PrintWriter out;
		try
		{
			out = response.getWriter();
			out.write(new Gson().toJson(lsitevm));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@RequestMapping(value = WebURIConstants.UPDATE_NOTIFICATION, method = RequestMethod.POST)
	public String getWebUpdateNotification(@ModelAttribute("notification") Notification notification,
			BindingResult result, @RequestParam(value = "notificationFile", required = false) MultipartFile image)

	{
		logger.info("Update notification.");
		notificationService.updateNotification(notification, image);
		return "redirect:" + WebURIConstants.LIST_NOTIFICATIONS;
	}

	@RequestMapping(value = WebURIConstants.LIST_NOTIFICATIONS, method = RequestMethod.GET)
	public String getWebAllNotification(Model model, HttpSession session, @AuthenticationPrincipal Principal principal,
			HttpServletRequest request, @PathVariable Map<String, String> msg)

	{

		logger.info("Receiving All notification.");

		Staff user = (Staff) request.getSession().getAttribute("user");

		if (user == null)
		{
			Staff staff = commonService.userCheck(principal.getName());
			session.setAttribute("user", staff);
			session.setAttribute("loginInfo", commonService
					.getInstituteInfoMasterById(Integer.parseInt(staff.getComClientName().getInstituteId())));

			List<DepartmentDTO> departmentDTOs = notificationService.getDepartment();
			session.setAttribute("departments", departmentDTOs);

			List<CollegeMenuList> list = commonService.college_DynamicMenu();
			for (CollegeMenuList collegeMenuList : list)
			{
				session.setAttribute(collegeMenuList.getMainMenuList().getMenu_Name(),
						collegeMenuList.getMainMenuList().getMenu_Name());
			}

		}
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("depid", "0");
		pathVariables.put("type", "0");
		pathVariables.put("for", Constants.CURRENT_NOTICES);
		model.addAttribute("notificationList", notificationService.getNotificationByDepId(pathVariables));

		pathVariables.put("for", Constants.UPCOMING_NOTICE);
		model.addAttribute("upcomingNotice", notificationService.getNotificationByDepId(pathVariables));
		return "admin.noticelist";
	}

	@RequestMapping(value = WebURIConstants.LIST_NOTIFICATION_BY_DEP, method = RequestMethod.GET)
	public String getWebNotificationByDep(@PathVariable Map<String, String> pathVariables, ModelMap model)
	{
		logger.info("Getting departmental notification ");
		pathVariables.put("for", Constants.CURRENT_NOTICES);
		model.addAttribute("notificationList", notificationService.getNotificationByDepId(pathVariables));// notification);
		pathVariables.put("for", Constants.UPCOMING_NOTICE);
		model.addAttribute("upcomingNotice", notificationService.getNotificationByDepId(pathVariables));
		model.addAttribute("departments", notificationService.getDepartment());
		return "admin.noticelist";
	}

	@RequestMapping(value = WebURIConstants.GET_PAST_NOTIFICATIONS, method = RequestMethod.GET)
	public String getWebAllPastNotification(ModelMap model, @PathVariable Map<String, String> pathVariables)
	{

		logger.info("Receiving All notification.");
		pathVariables.put("for", Constants.PAST_NOTICES);
		model.addAttribute("notificationList", notificationService.getNotificationByDepId(pathVariables));
		model.addAttribute("departments", notificationService.getDepartment());
		return "admin.pastnotices";
	}

	@RequestMapping(value = WebURIConstants.GET_NOTIFICATION, method = RequestMethod.GET)
	public String getWebNotification(@PathVariable("id") Integer id, ModelMap model)
	{
		logger.info("Receiving Notification ");
		NotificationDTO notification = notificationService.getNotificationById(id);
		if (notification == null)
		{
			throw new WebException("Notice Not found by id " + id, 404,
					"redirect:" + WebURIConstants.LIST_NOTIFICATIONS);
		} else
		{
			model.addAttribute("noticeForm", notification);
		}
		return "admin.noticeForm";
	}

	/*
	 *
	 * Test method not use any where*
	 *
	 * @RequestMapping(value = "/test", method = RequestMethod.GET)
	 * public @ResponseBody List<NotificationDTO> test() { return
	 * notificationService.getNotificationByDepId(2, 1, Constants.CURRENT_NOTICES);
	 * }
	 */

	/*
	 * @RequestMapping(value = WebURIConstants.GET_PAST_NOTIFICATION_BY_DEP, method
	 * = RequestMethod.GET) public String
	 * getWebPastNotificationByDep(@PathVariable("depid") Integer
	 * id, @PathVariable("type") Integer typId, ModelMap model, HttpSession session)
	 * { logger.info("Receiving Notification For Department "); if (id == 0 && typId
	 * == 0) { getWebAllPastNotification(model); } else { Notification notice = new
	 * Notification(); List<Notification> notification = null;//
	 * notificationService.getNotificationByDepId(id, //
	 * typId,Constants.PAST_NOTICES); List<Department> listofdeprtment = null;//
	 * notificationService.getDepartment(); notice.setDepartment(id);
	 * notice.setNotificationType(typId); if (notification.isEmpty()) {
	 * notification.add(notice); } model.addAttribute("notificationList",
	 * notification); model.addAttribute("departments", listofdeprtment);
	 *
	 * } return "admin.pastnotices"; }
	 */

	@RequestMapping(value = WebURIConstants.DELETE_NOTIFICATION, method = RequestMethod.GET)
	public String deleteWebNotification(@PathVariable("id") Integer id)
	{
		// System.out.println("Deleting Notification");
		notificationService.removeNotification(id);
		return "redirect:" + WebURIConstants.BLANK_NOTICE_FORM;

	}

	/*
	 * @RequestMapping(value = WebURIConstants.PARENT_NOTIFICATIONS, method =
	 * RequestMethod.GET) public String getWebParentNotification(ModelMap model) {
	 * logger.info("Receiving All notification.");
	 * model.addAttribute("notificationList", null
	 * notificationService.listNotificationsforParent() ); return
	 * "admin.noticelist"; }
	 */
	@RequestMapping(value = WebURIConstants.GET_NOTICE, method = RequestMethod.GET)
	public String getWebNotice(@PathVariable("id") Integer id, ModelMap model, Map<String, Object> map)
	{
		logger.info("Receiving Notification for id ");
		Notification notification = notificationService.getNotification(id);
		// System.out.println("cgfgffg" + stafflst);
		model.addAttribute("departments", commonService.getDepartmentList());
		model.addAttribute("stafflist", staffService.getStaffList());
		model.addAttribute("noticeforupdate", notification);
		map.put("notification", notification);
		return "admin.updatenotice";
	}

	@RequestMapping(value = WebURIConstants.ADD_GROUP_NOTICE, method = RequestMethod.POST)
	public String AddGroupWebNotification(@ModelAttribute("notification") Notification notification,
			BindingResult result, @RequestParam(value = "notificationFile", required = false) MultipartFile image)
			throws IOException

	{

		logger.info("Creating Group Notification.");
		NotificationFiles nf = new NotificationFiles();
		if (!image.isEmpty())
		{
			nf.setString1(notificationService.uploadImageOnCloud(image));
		}
		nf.setNotification(notification);
		nf.setDocument1Type(image.getContentType());
		Set<NotificationFiles> nfs = new HashSet<>();
		nfs.add(nf);
		notification.setNotificationFiles(nfs);
		try
		{
			// Notification n =
			// notificationService.addGroupNotification(notification);
			notificationService.addNotification(notification);
			pushNotificationService.sendGroupNotification(notification);
			// model.addAttribute("mentor",
			// studentService.getStudentListByStaffId(id));
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		if (notification.getMentor() != null)
		{
			return "redirect:/web/taskforce/service/mentor/d/" + notification.getMentor();
		} else
		{
			return "redirect:/web/taskforce/groupdetails/" + notification.getGroups();
		}
	}

	@RequestMapping(value = WebURIConstants.GET_NOTIFICATION_BY_DATE, method = RequestMethod.GET)
	public @ResponseBody List<NotificationDTO> getNotificationByDate(@RequestParam("date") String date)
			throws ParseException
	{
		return notificationService.getNotificationByDate(date);
	}

	/*
	 * @Scheduled(fixedRate = 1000) public void NotificationScheduler() throws
	 * InterruptedException, IOException {
	 * System.out.println("Cron Schedular is running..."); DateFormat df = new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Date date = new Date();
	 * List<Notification> notifications =
	 * notificationService.getNotificationByDate(df.format(date)); for (Notification
	 * notification : notifications) {
	 * pushNotificationService.sendNotification(notification, null); }
	 * Thread.sleep(300); }
	 */

	@RequestMapping(value = WebURIConstants.UPLOAD_NOTICE_FILE, method = RequestMethod.POST)
	public String saveNoticeFile(@RequestParam("NoticeExcelFile") MultipartFile excelFile, Model model)

	{
		String message = "Please Contact to Support team for this error. ";
		try
		{
			message = notificationService.saveNoticesViaExcelFile(excelFile);
		} catch (Exception e)
		{
			message = message + e.getMessage();
		}
		model.addAttribute("message", message);
		return "redirect:" + WebURIConstants.NOTICE_FILE;
	}

	@RequestMapping(value = SIMSURIConstants.COMMENT_LIST, method = RequestMethod.POST)
	public @ResponseBody List<CommentDTO> getCommentList(@RequestParam(value = "noticeId") Integer noticeId)
	{

		// List<Comment> list =
		// notificationService.getCommentListByID(noticeId);
		return notificationService.getNotificationComment(noticeId);// list;
	}

	@RequestMapping(value = SIMSURIConstants.COMMENT_INSERT, method = RequestMethod.POST)
	public @ResponseBody Boolean commentInsert(@RequestParam Map<String, String> requestParam)
	{

		Comment comment = new Comment();
		ComClientName c = new ComClientName();
		comment.setComment(requestParam.get("comment").toString());
		comment.setNoticeId(Integer.parseInt(requestParam.get("noticeId")));
		c.setId(Integer.parseInt(requestParam.get("clientId")));
		comment.setComClientName(c);
		// String comment = notificationService.commentInsert(requestParam);
		return notificationService.addCooment(comment);
	}

	@RequestMapping(value = SIMSURIConstants.LIKE, method = RequestMethod.POST)
	public @ResponseBody Boolean likeIncriment(@RequestParam Map<String, String> requestParam)
	{
		Like like = new Like();
		like.setClient_Id(Integer.parseInt(requestParam.get("client_Id")));
		like.setLike_Status(Boolean.parseBoolean(requestParam.get("likeStatus")));
		like.setNotification_id(Integer.parseInt(requestParam.get("noticeId")));
		return notificationService.likeIncriment(like);
	}

	/********************* Rest Services *******************************/

	@RequestMapping(value = RestURIConstants.GET_NOTIFICATION_BY_TYPE, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getRestNotificationByType(@PathVariable Map<String, String> pathVariables)
	{
		pathVariables.put("for", Constants.CURRENT_NOTICES);
		return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "List of Notices by notification type",
				notificationService.getNotifications(pathVariables)), HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = RestURIConstants.NOTIFICATION_COUNT, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getNotificationCount(@PathVariable("id") Integer id)
	{

		return new ResponseEntity<>(
				new SampleClass(HttpStatus.OK, "Notice Count", notificationService.getNotificationCount(id)),
				HttpStatus.ACCEPTED);

	}

	@RequestMapping(value = RestURIConstants.ADD_NOTIFICATION, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> createNotification(@RequestBody Notification notification)
	{
		logger.info("Creating Notification...");
		Notification n = new Notification();
		Set<NotificationFiles> f = notification.getNotificationFiles();
		NotificationFiles nf = new NotificationFiles();
		for (NotificationFiles notificationFiles : f)
		{
			try
			{
				if (notificationFiles.getFile() != null)
				{

					System.out.println(".....file is......" + notificationFiles.getFile());
					try
					{
						nf.setString1(notificationService.uploadImageOnCloud(notificationFiles.getFile()));
					} catch (Exception e)
					{
						e.getMessage();
					}
				}
				nf.setNotification(notification);
				nf.setDocument1Type(notificationFiles.getDocument1Type());
				Set<NotificationFiles> nfs = new HashSet<>();
				nfs.add(nf);
				notification.setNotificationFiles(nfs);
				n = notificationService.addNotification(notification);
				if (n != null)
				{
					pushNotificationService.sendNotification(notification);
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		// pushNotificationService.sendNotification(notification);
		if (n != null)
		{
			return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "Notice Post Sucessfully...!", n),
					HttpStatus.ACCEPTED);
		} else
		{
			return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "Error Occured...!", n), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = RestURIConstants.REST_GET_GROUP_NOTICE, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getGroupNotices(@PathVariable("groupid") Integer groupid)
	{
		return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "List of group Notices",
				notificationService.getGroupNotification(groupid)), HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = RestURIConstants.LIKE, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> likeIncrement(@RequestBody Like like)
	{
		if (like.getLike_Status())
		{
			return new ResponseEntity<>(
					new SampleClass(HttpStatus.OK, "Liked..!!!!", notificationService.likeIncriment(like)),
					HttpStatus.ACCEPTED);
		} else
		{
			return new ResponseEntity<>(
					new SampleClass(HttpStatus.OK, "Unliked..!!!!", notificationService.likeIncriment(like)),
					HttpStatus.ACCEPTED);
		}
		// return notificationService.likeIncriment(like);
	}

	@RequestMapping(value = RestURIConstants.COMMENT_LIST, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getCommentListForMbl(@PathVariable(value = "noticeId") Integer noticeId)
	{

		List<CommentDTO> comments = notificationService.getNotificationComment(noticeId);
		if (!comments.isEmpty())
		{
			return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "Comments...", comments), HttpStatus.ACCEPTED);
		} else
		{
			return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "Comments not available...", comments),
					HttpStatus.ACCEPTED);
		}
	}

	@RequestMapping(value = RestURIConstants.COMMENT_INSERT, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> addComment(@RequestBody Comment comment)
	{

		if (notificationService.addCooment(comment))
		{
			return getCommentListForMbl(comment.getNoticeId());
		} else
		{
			return getCommentListForMbl(comment.getNoticeId());
		}
	}

	@RequestMapping(value = RestURIConstants.GET_EVENT_FOR_CALENDAR, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getAllEvents()
	{
		List<NotificationDTO> list = notificationService.listEvent(4);

		if (!list.isEmpty())
		{
			return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "Get Event Notification List", list),
					HttpStatus.ACCEPTED);

		} else
		{
			return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "Event's are not avialable", list),
					HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = RestURIConstants.GET_ACHIVEMENT_FOR_CALENDAR, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getAllAwardsAndAchivement()
	{
		List<NotificationDTO> list = notificationService.listEvent(2);

		if (!list.isEmpty())
		{
			return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "Get Event Notification List", list),
					HttpStatus.ACCEPTED);

		} else
		{
			return new ResponseEntity<>(new SampleClass(HttpStatus.OK, "Event's are not avialable", list),
					HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = WebURIConstants.noticeurl, method = RequestMethod.POST)
	public @ResponseBody List<Notification> getnotices(@PathVariable("id") Integer staff_id, ModelMap model)
			throws ParseException
	{
		System.out.println("iddd" + staff_id);
		return notificationService.getnotices(staff_id);

	}
}
