package com.zertones.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zertones.dao.common.ChatingDao;
import com.zertones.model.ComChatMessages;
import com.zertones.model.sims.Student;

@Service("chatingService")
public class ChatingServiceImpl implements ChatingService
{

	@Autowired
	private ChatingDao chatingDao;

	public void setChatingDao(ChatingDao chatingDao)
	{
		this.chatingDao = chatingDao;
	}

	@Override
	@Transactional
	public List<ComChatMessages> listChating(Integer id)
	{
        return chatingDao.getListOfChat(id);
	}

	@Override
	@Transactional
	public List<Student> getChatingMember(Integer viewId)
	{

		return chatingDao.getMemberList(viewId);
	}

	@Override
	@Transactional
	public List<ComChatMessages> getChat(Integer fromId, Integer uid)
	{

		return chatingDao.getChat(fromId, uid);
	}

	@Override
	@Transactional
	public void saveChatMsg(ComChatMessages comChatMessages)
	{
		chatingDao.saveChtMsg(comChatMessages);
	}

}
