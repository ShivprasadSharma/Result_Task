package com.zertones.service.common;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.zertones.model.DataTransferObjectModel.CommentDTO;
import com.zertones.model.DataTransferObjectModel.DepartmentDTO;
import com.zertones.model.DataTransferObjectModel.NotificationCount;
import com.zertones.model.DataTransferObjectModel.NotificationDTO;
import com.zertones.model.common.Comment;
import com.zertones.model.common.Like;
import com.zertones.model.common.Notification;

public interface NotificationService
{
	public String uploadImageOnCloud(MultipartFile image) throws IOException;

	public String uploadImageOnCloud(byte[] image) throws IOException;

	public Notification addNotification(Notification n) throws Exception;

	public void updateNotification(Notification n, MultipartFile image);

	public void removeNotification(int id);

	public List<DepartmentDTO> getDepartment();

	public Notification addGroupNotification(Notification n) throws Exception;

	public String saveNoticesViaExcelFile(MultipartFile excelFile) throws Exception;

	public String commentInsert(Map<String, String> requestParam);

	public Boolean likeIncriment(Like like);

	public Notification getNotification(Integer id);

	public List<NotificationDTO> getNotificationByDepId(Map<String, String> pathVariables);

	public List<NotificationDTO> getNotifications(Map<String, String> pathVariables);

	public List<NotificationDTO> listEvent(Integer type);

	public List<NotificationDTO> listAchivement();

	public List<NotificationDTO> getNotificationByDate(String date);

	public List<NotificationDTO> getGroupNotification(Integer id);

	public List<NotificationDTO> getChatNotification(Integer id);

	public List<CommentDTO> getNotificationComment(Integer noticeId);

	public Boolean addCooment(Comment comment);

	// public List<Notification> listNotifications();

	public NotificationDTO getNotificationById(int id);

	//// public List<Notification> getNoticeByDepId(int id, int typId);

	// public List<Notification> getUpNoticeByDepId(int id, int typId);

	// public List<Notification> listNotificationsforParent();

	// public List<EventModel> listOfUpcomingEvent();

	public List<NotificationDTO> getNoticesSendByTeacher();

	// public List<Notification> getNoticeByDateForWeb();

	// public List<Notification> getNoticesByDate();

	// public List<Notification> getNotificationByDepnt(Integer id);

	// public List<Notification> getNotificationByDepAndType(int id, int typId);

	// public List<Notification> getNotificationWithLike(List<Notification> list);

	public List<NotificationCount> getNotificationCount(Integer id);

	public List<Notification> getnotices(Integer staffid);

}
