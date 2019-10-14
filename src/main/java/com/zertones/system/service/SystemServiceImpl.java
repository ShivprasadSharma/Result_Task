package com.zertones.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zertones.model.ComUserDetails;
import com.zertones.model.common.InstituteInfoMaster;
import com.zertones.model.master.ComListMaster;
import com.zertones.system.dao.SystemDAO;

/**
 * @author Abhijit
 * @Created Date : Sep 29, 2015
 */
@Service("systemService")
public class SystemServiceImpl implements SystemService
{
	@Autowired
	private SystemDAO systemDAO;

	@Override
	@Transactional
	public List<InstituteInfoMaster> listInstituteInfoMasters()
	{
		return systemDAO.listInstituteInfoMasters();
	}

	@Override
	@Transactional
	public List<ComListMaster> listCommonMasters()
	{
		return systemDAO.listCommonMasters();
	}

	@Override
	public ComUserDetails getUserDetails()
	{
		return null;
	}

}
