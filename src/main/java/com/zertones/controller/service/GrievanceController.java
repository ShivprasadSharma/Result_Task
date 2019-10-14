package com.zertones.controller.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zertones.controller.WebURIConstants;
import com.zertones.model.DataTransferObjectModel.ComClientNameDTO;
import com.zertones.model.common.GrievanceAssign_MemberList;
import com.zertones.model.common.Grievance_List;
import com.zertones.service.common.GrievanceService;
import com.zertones.service.common.PushNotificationService;
import com.zertones.service.sims.StaffService;

@Controller
public class GrievanceController
{
	private static final Logger		logger	= LoggerFactory.getLogger(GrievanceController.class);

	@Autowired
	private GrievanceService		grievanceService;

	@Autowired
	private StaffService			staffService;

	private PushNotificationService	pushNotificationService;

	@Autowired(required = true)
	@Qualifier(value = "pushNotificationService")
	public void setPushNotificationService(PushNotificationService pns)
	{
		pushNotificationService = pns;
	}

	@RequestMapping(value = WebURIConstants.CREATE_GRIEVANCE_COMMITTEE, method = RequestMethod.GET)
	public String getCreateCommittee(Model model) throws IOException
	{
		logger.debug("calling create grievance committee page");

		List<ComClientNameDTO> list = grievanceService.getGrievanceMembers();
		model.addAttribute("stafflist", list);
		return "admin.createGrievanceCommittee";
	}

	@RequestMapping(value = WebURIConstants.GET_GC__MEMBERS, method = RequestMethod.GET)
	public String getGC_Member_List(Model model) throws IOException
	{
		logger.debug("calling create grievance member list page");

		model.addAttribute("GCMemberList", grievanceService.getGC_Members_List());
		return "admin.GCMemberList";
	}

	@RequestMapping(value = WebURIConstants.ADD_COMMITTEE_MEMBERS, method = RequestMethod.POST)
	public String addCommitteeMembers(@RequestParam(value = "userId[]") int[] clientId, Model model) throws IOException
	{
		logger.debug("calling create grievance committee page");

		grievanceService.addGCMembers(clientId);
		return "redirect:" + WebURIConstants.GET_GC__MEMBERS;
	}

	@RequestMapping(value = WebURIConstants.GRIVANCE_LIST, method = RequestMethod.GET)
	public String grievanceList(Model model) throws IOException
	{
		logger.debug("calling create grievance member list page");
		model.addAttribute("stafflist", grievanceService.getGC_Members_List());
		model.addAttribute("grievanceList", grievanceService.getgrievanceList());
		return "admin.grievanceList";
	}

	@RequestMapping(value = WebURIConstants.GRIVANCE_ASSIGN_TOMEMBERS, method = RequestMethod.POST)
	public String grievance_Assign_to_Members(@RequestParam(value = "userId[]") int[] clientId,
			@RequestParam(value = "grievanceId") Integer girvanceId, Model model) throws IOException
	{
		grievanceService.grievance_Assign_to_Members(clientId, girvanceId);

		return "redirect:" + WebURIConstants.GRIVANCE_LIST;
	}

	@RequestMapping(value = WebURIConstants.GET_GRIEVANCE_ASSIGN_MEMBERS, method = RequestMethod.POST)
	public @ResponseBody List<GrievanceAssign_MemberList> getGrievance_AssignMembers(
			@RequestParam(value = "grievanceID") Integer girvanceId, Model model) throws IOException
	{

		List<GrievanceAssign_MemberList> list = grievanceService.getGrievance_AssignMembers(girvanceId);

		System.out.println("list:::" + list);

		return list;

	}

	@RequestMapping(value = WebURIConstants.GRIEVANCE_REPLAY, method = RequestMethod.POST)
	public String addGrievance_Replay(@RequestParam(value = "Replay") String replayText,
			@RequestParam(value = "grievanceId") Integer girvanceId, @RequestParam(value = "checkReopen") Integer id,
			Model model) throws IOException
	{

		Grievance_List grievance = grievanceService.addGrievance_Replay(replayText, girvanceId, id);
		if (grievance != null)
		{
			pushNotificationService.sendGrievanceReplay(grievance);
		}
		return "redirect:" + WebURIConstants.GRIVANCE_LIST;
	}

	@RequestMapping(value = WebURIConstants.GET_GRIEVANCE_DOWNLOAD_PAGE, method = RequestMethod.GET)
	public String getDownloadpage(Model model, @RequestParam(value = "status", required = false) Boolean status)
			throws IOException
	{
		model.addAttribute("status", status);
		return "admin.downloadGrievance";
	}

	@RequestMapping(value = WebURIConstants.GRIEVANCE_DOWNLOAD, method = RequestMethod.POST)
	public ModelAndView grievanceDownload(@RequestParam(value = "startDate") Date startDate,
			@RequestParam(value = "endDate") Date endDate, Model modelm, HttpServletResponse response)
			throws IOException
	{

		ModelAndView model1 = new ModelAndView("admin.downloadGrievance");
		Boolean status = grievanceService.grievanceDownload(startDate, endDate, response);
		model1.addObject("status", status);
		model1.setViewName("redirect:" + WebURIConstants.GET_GRIEVANCE_DOWNLOAD_PAGE);

		return model1;
	}

}
