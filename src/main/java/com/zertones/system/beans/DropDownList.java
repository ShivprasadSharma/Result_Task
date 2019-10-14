package com.zertones.system.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.zertones.model.common.InstituteInfoMaster;
import com.zertones.model.master.ComListDetails;
import com.zertones.model.master.ComListMaster;
import com.zertones.system.service.SystemService;

/**
 * @author Abhijit
 * @Created Date : Sep 27, 2015
 */
@Component("listService")
@Eager
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class DropDownList implements InitializingBean
{
	private static final Logger					logger		= LoggerFactory.getLogger(DropDownList.class);
	public Map<String, Map<Integer, String>>	listMap		= new HashMap<>();
	@Autowired
	private SystemService						systemService;
	private final String						SELECT_TEXT	= "---Select---";

	@Autowired(required = true)
	@Qualifier(value = "systemService")
	public void setSystemService(SystemService systemService)
	{
		this.systemService = systemService;
	}

	// @PostConstruct
	public Map<String, Map<Integer, String>> populateMasterList()
	{

		listMap.put("infoList", populateInstituteMasterList());
		populateCommonMasters();
		logger.debug("Created List Map : " + listMap);
		return listMap;
	}

	public Map<Integer, String> populateInstituteMasterList()
	{
		logger.debug("Calling Institute Info Masters");
		List<InstituteInfoMaster> masterList = systemService.listInstituteInfoMasters();
		Map<Integer, String> masterMap = new HashMap<>();
		masterMap.put(0, SELECT_TEXT);
		masterList.forEach((master) ->
		{
			/*
			 * masterMap.put(master.getListId(), master.getValue());
			 */ });
		logger.debug("Created Map for List Masters");
		return masterMap;
	}

	public void populateCommonMasters()
	{
		logger.debug("Calling common Masters");
		List<ComListMaster> masterList = systemService.listCommonMasters();
		masterList.forEach((listmaster) ->
		{
			Map<Integer, String> masterMap = new HashMap<>();
			Set<ComListDetails> listDetails = listmaster.getComListDetails();
			listDetails.forEach((listDetail) ->
			{
				masterMap.put(listDetail.getListDtlId(), listDetail.getValue());
			});
			listMap.put(listmaster.getValue(), masterMap);
		});
	}

	public Map<String, Map<Integer, String>> getListMap()
	{
		return listMap;
	}

	public void setListMap()
	{
		listMap = populateMasterList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception
	{
		// populateMasterList();

	}

	/*
	 * public Map<Integer, String> getMasterList() { return masterList; }
	 *
	 * public void setMasterList() { this.masterList = populateMasterList(); }
	 */
}
