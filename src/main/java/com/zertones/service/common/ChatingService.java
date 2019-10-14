package com.zertones.service.common;

import java.util.List;

import com.zertones.model.ComChatMessages;
import com.zertones.model.sims.Student;

public interface ChatingService
{

	public List<ComChatMessages> listChating(Integer id);

	public List<ComChatMessages> getChat(Integer fromId, Integer uid);

	public List<Student> getChatingMember(Integer viewId);

	public void saveChatMsg(ComChatMessages comChatMessages);
}
