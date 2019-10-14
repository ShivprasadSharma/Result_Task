package com.zertones.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zertones.controller.WebURIConstants;
import com.zertones.model.master.ComStaffSubject;
import com.zertones.service.common.CommonService;
import com.zertones.service.common.OnlineExamService;

@Controller
public class OnlineExamController
{

	@Autowired
	private OnlineExamService	onlineExamService;

	@Autowired
	private CommonService		commonService;

	@RequestMapping(value = WebURIConstants.EXAM_DASHBOARD, method = RequestMethod.GET)
	public String DisplayExamDashboard(ModelMap model)
	{
		return "admin.examdashboard";
	}

	@RequestMapping(value = WebURIConstants.GET_SUBJECT_BY_STAFF, method = RequestMethod.POST)
	public @ResponseBody List<ComStaffSubject> getStaffSubject(@RequestParam("id") Integer id)
	{
		System.out.println("....data....." + id);
		List<ComStaffSubject> subjectlist = commonService.getAssigendStaffSubject(id);
		return null;
	}

}
