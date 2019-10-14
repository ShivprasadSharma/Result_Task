package com.zertones.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zertones.dao.common.OnlineExamDAO;

@Service("onlineExamService")
public class OnlineExamServiceImpl implements OnlineExamService
{

	@Autowired
	private OnlineExamDAO onlineExamDAO;

}
