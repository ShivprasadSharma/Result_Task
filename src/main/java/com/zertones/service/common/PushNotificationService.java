package com.zertones.service.common;

import java.io.IOException;

import com.zertones.model.ComChatMessages;
import com.zertones.model.common.Grievance_List;
import com.zertones.model.common.Group_Poll;
import com.zertones.model.common.Groups;
import com.zertones.model.common.Notification;
import com.zertones.model.common.Polls;
import com.zertones.model.common.RecruitmentInfo;

public interface PushNotificationService
{
	public void sendNotification(Notification notification) throws IOException;

	public void sendChatMsg(ComChatMessages comChatMessages);

	public void sendGroupNotification(Notification notification) throws IOException;

	public void sendGrievanceReplay(Grievance_List grievance);

	public void sendPoll(Polls polls1);

	public void sendgrouprequestNotification(Groups group);

	public void recruitmentInfoNotification(RecruitmentInfo recruitmentInfo);

	public void sendOfferLaterNotification(RecruitmentInfo recruitmentInfo, Integer[] studId);

	public void sendGroupPoll(Group_Poll gpolls1);

}
