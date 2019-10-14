package com.zertones.service.sims;

import com.zertones.dao.sims.ParentDAO;
import com.zertones.model.sims.Parent;

public class ParentServiceImpl implements ParentService
{

	private ParentDAO parentDao;

	@Override
	public Parent parentSignUp(Parent parent)
	{
		// TODO Auto-generated method stub
		return parentDao.ParentDetails(parent);
	}

}
