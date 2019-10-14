package com.zertones.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zertones.model.ComUserDetails;
import com.zertones.service.common.CommonService;

@Service("commonValidationService")
public class CommonValidationServiceImpl implements CommonValidationService
{
	private static final Logger	logger	= LoggerFactory.getLogger(CommonValidationServiceImpl.class);
	private CommonService		commonService;

	@Autowired(required = true)
	@Qualifier(value = "commonService")
	public void setCommonService(CommonService commonService)
	{
		this.commonService = commonService;
	}

	@Override
	public boolean isUserExists(String UserName)
	{
		// System.out.println(UserName);
		List<ComUserDetails> users = commonService.searchUsser(UserName);
		if (users != null && users.size() > 0)
		{
			return true;
		} else
		{
			return false;
		}
	}

}
