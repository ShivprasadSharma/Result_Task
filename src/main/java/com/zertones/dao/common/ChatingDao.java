package com.zertones.dao.common;

import java.util.List;

import com.zertones.model.ComChatMessages;
import com.zertones.model.sims.Student;

public interface ChatingDao
{

	public List<ComChatMessages> getListOfChat(Integer id);

	public List<ComChatMessages> getChat(Integer fromId, Integer uid);

	public List<Student> getMemberList(Integer viewId);

	public void saveChtMsg(ComChatMessages comChatMessages);
}
