package com.zertones.controller.service;

import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.zertones.controller.WebURIConstants;
import com.zertones.model.ComChatMessages;
import com.zertones.service.common.ChatingService;
import com.zertones.service.common.PushNotificationService;

@Controller
public class ChatController implements ServletContextAware
{
	private static final Logger		logger	= LoggerFactory.getLogger(ChatController.class);
	private ServletContext			servletContext;
	private PushNotificationService	pushNotificationService;

	private ChatingService			chatingService;

	@Autowired(required = true)
	@Qualifier(value = "pushNotificationService")
	public void setPushNotificationService(PushNotificationService pns)
	{
		pushNotificationService = pns;
	}

	@Autowired(required = true)
	public void setChatingService(ChatingService chatingService)
	{
		this.chatingService = chatingService;
	}

	@Override
	public void setServletContext(ServletContext servletContext)
	{
		this.servletContext = servletContext;
	}

	@RequestMapping(value = WebURIConstants.CHAT_PAGE, method = RequestMethod.GET)
	public String chatBox(@PathVariable("viewId") Integer viewId, ModelMap model)
	{
		model.addAttribute("chatList", chatingService.getChatingMember(viewId));
		return "user.chatBox";
	}

	@RequestMapping(value = WebURIConstants.CHATLIST_BY_ID, method = RequestMethod.GET)
	public @ResponseBody List<ComChatMessages> getChatMsgById(@PathVariable("fromId") Integer fromId,
			@PathVariable("uid") Integer uid)
	{
		logger.info("Get msg ");
		List<ComChatMessages> list = chatingService.getChat(fromId, uid);
		return list;
	}

	@RequestMapping(value = WebURIConstants.CHAT_LIST, method = RequestMethod.GET)
	@ResponseBody
	public List<ComChatMessages> getChatingList(@PathVariable("id") Integer id)
	{
		logger.info("Get Chaing Controller");
		List<ComChatMessages> list = chatingService.listChating(id);
		return list;
	}

	@RequestMapping(value = WebURIConstants.SAVE_CHAT, method = RequestMethod.POST)
	public @ResponseBody ComChatMessages saveChatMsg(@RequestBody ComChatMessages comChatMsg)
	{
		logger.info("Save Chat Msg ");
		chatingService.saveChatMsg(comChatMsg);
		pushNotificationService.sendChatMsg(comChatMsg);
		return comChatMsg;
	}

}
