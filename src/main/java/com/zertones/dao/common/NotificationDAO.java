package com.zertones.dao.common;

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

public interface NotificationDAO
{

	public Notification addNotification(Notification notification);

	public void updateNotification(Notification notification, MultipartFile image);

	public List<Notification> listNotifications();

	public Notification getNotification(Integer id);

	public NotificationDTO getNotificationById(Integer id);

	public List<NotificationDTO> getNotificationByDepId(Map<String, String> pathVariables);

	// public List<Notification> getNoticeByDepId(Integer id, Integer typId);

	public void removeNotification(Integer id);

	public List<Notification> listNotificationsforParent();

	public List<DepartmentDTO> getDepartment();

	public List<NotificationDTO> getEvent(Integer type);

	public List<NotificationDTO> getAchivement();

	public List<Notification> getAllUpcomingEvent();

	public List<NotificationDTO> getNoticesSendByTeacher();

	public List<NotificationDTO> getGroupNotifications(Integer id);

	public List<NotificationDTO> getChatNotification(Integer id);

	public List<Notification> getUpcomeNoticeByDepId(int id, int typId);

	public Notification addGroupNotification(Notification notification);

	public List<NotificationDTO> getNotificationByDate(String date);

	public List<Notification> getNoticeByDateForWeb();

	public List<Notification> getNoticesByDate();

	public String saveNoticesViaExcelFile(MultipartFile excelFile) throws Exception;

	public List<Notification> saveNotificationByDepnt(Integer id);

	public List<Notification> getNotificationByDepAndType(Integer id, Integer typId);

	public List<Notification> getNotificationWithLike(List<Notification> list);

	public List<CommentDTO> getNotificationComment(Integer noticeId);

	public String commentInsert(Map<String, String> requestParam);

	public Boolean likeIncriment(Like like);

	public Boolean addComment(Comment commnet);

	public List<NotificationCount> getNotificationCount(Integer id);

	public List<Notification> getnotices(Integer staffid);

}
