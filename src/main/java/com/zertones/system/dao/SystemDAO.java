package com.zertones.system.dao;

import java.util.List;

import com.zertones.model.common.InstituteInfoMaster;
import com.zertones.model.master.ComListMaster;

/**
 * @author Abhijit
 * @Created Date : Sep 29, 2015
 */
public interface SystemDAO
{
	public List<InstituteInfoMaster> listInstituteInfoMasters();

	public List<ComListMaster> listCommonMasters();
}
