package com.zertones.service.common;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.zertones.dao.common.NotificationDAO;
import com.zertones.model.DataTransferObjectModel.CommentDTO;
import com.zertones.model.DataTransferObjectModel.DepartmentDTO;
import com.zertones.model.DataTransferObjectModel.NotificationCount;
import com.zertones.model.DataTransferObjectModel.NotificationDTO;
import com.zertones.model.common.Comment;
import com.zertones.model.common.Like;
import com.zertones.model.common.Notification;

@Service("notificationService")
@Scope("prototype")
public class NotificationServiceImpl implements NotificationService
{
	@Autowired
	private NotificationDAO notificationDAO;

	public void setNotificationDAO(NotificationDAO notificationDAO)
	{
		this.notificationDAO = notificationDAO;
	}

	@Override
	public String uploadImageOnCloud(MultipartFile file) throws IOException
	{
		Map config = ObjectUtils.asMap("cloud_name", "dcptr5ivh", "api_key", "297869565358947", "api_secret",
				"Lca6u4EW3lo-weSppOf1MwGz6v0");
		Cloudinary cloudinary = new Cloudinary(config);
		Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
		return uploadResult.get("url").toString();
	}

	@Override
	public String uploadImageOnCloud(byte[] file) throws IOException
	{
		Map config = ObjectUtils.asMap("cloud_name", "dcptr5ivh", "api_key", "297869565358947", "api_secret",
				"Lca6u4EW3lo-weSppOf1MwGz6v0");
		Cloudinary cloudinary = new Cloudinary(config);
		Map uploadResult;
		String url = "";
		try
		{
			uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
			url = uploadResult.get("url").toString();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return url;
	}

	@Override
	public Notification getNotification(Integer id)
	{
		return notificationDAO.getNotification(id);
	}

	@Override
	@Transactional
	public Notification addNotification(Notification n) throws Exception
	{
		return notificationDAO.addNotification(n);
	}

	@Override
	@Transactional
	public void updateNotification(Notification n, MultipartFile image)
	{
		notificationDAO.updateNotification(n, image);
	}

	/*
	 * @Override
	 *
	 * @Transactional public List<Notification> listNotifications() { return
	 * notificationDAO.listNotifications(); }
	 */

	@Override
	@Transactional
	public NotificationDTO getNotificationById(int id)
	{
		return notificationDAO.getNotificationById(id);

	}

	@Override
	@Transactional
	public List<NotificationDTO> getNotificationByDepId(Map<String, String> pathVariables)
	{
		/*
		 * List<NotificationDTO> notification =
		 * notificationDAO.getNotificationByDepId(pathVariables); NotificationDTO notice
		 * = new NotificationDTO();
		 * 
		 * notice.setDepartment(Integer.parseInt(pathVariables.get("depid")));
		 * notice.setNotificationType(Integer.parseInt(pathVariables.get("type"))); if
		 * (notification.isEmpty()) { notification.add(notice); }
		 */
		return null;

	}

	/*****************************
	 * Rest PAI
	 **************************************/

	// Extra Method Create for mobile it is in use
	@Override
	@Transactional
	public List<NotificationDTO> getNotifications(Map<String, String> pathVariables)
	{
		return notificationDAO.getNotificationByDepId(pathVariables);
	}

	/****************************
	 * Rest PAI
	 **************************************/
	// @Override
	// @Transactional
	// public List<Notification> getNoticeByDepId(int id, int typId)
	// {
	// List<Notification> n = notificationDAO.getNoticeByDepId(id, typId);
	//
	// return null;
	// }

	@Override
	@Transactional
	public void removeNotification(int id)
	{
		notificationDAO.removeNotification(id);
	}

	/*
	 * @Override public List<Notification> listNotificationsforParent() { return
	 * notificationDAO.listNotificationsforParent(); }
	 */

	@Override
	public List<DepartmentDTO> getDepartment()
	{
		return notificationDAO.getDepartment();

	}

	@Override
	public List<NotificationDTO> listEvent(Integer type)
	{
		return notificationDAO.getEvent(type);

	}

	@Override
	public List<NotificationDTO> listAchivement()
	{
		return notificationDAO.getAchivement();

	}

	/*
	 * @Override public List<EventModel> listOfUpcomingEvent() { List<Notification>
	 * list = notificationDAO.getAllUpcomingEvent(); ArrayList<EventModel> lsitevm =
	 * new ArrayList<>();
	 *
	 * for (Notification lst : list) { EventModel evm = new EventModel();
	 * evm.setTitle(lst.getNotificatiosHeadline());
	 * evm.setStart(lst.getNotificationFromDate());
	 * evm.setEnd(lst.getNotificationToDate());
	 * evm.setEventDtl(lst.getNotificationDetails()); evm.setVenu(lst.getVenue());
	 * lsitevm.add(evm); } return lsitevm; }
	 */

	@Override
	public List<NotificationDTO> getNoticesSendByTeacher()
	{
		return notificationDAO.getNoticesSendByTeacher();
	}

	@Override
	public List<NotificationDTO> getGroupNotification(Integer id)
	{
		return notificationDAO.getGroupNotifications(id);
	}

	@Override
	@Transactional
	public Notification addGroupNotification(Notification n) throws Exception
	{
		return notificationDAO.addGroupNotification(n);
	}

	@Override
	public List<NotificationDTO> getNotificationByDate(String date)
	{
		return notificationDAO.getNotificationByDate(date);
	}

	/*
	 * @Override public List<Notification> getNoticeByDateForWeb() { return
	 * notificationDAO.getNoticeByDateForWeb(); }
	 */

	/*
	 * @Override public List<Notification> getNoticesByDate() { return
	 * notificationDAO.getNoticesByDate(); }
	 */

	@Override
	public String saveNoticesViaExcelFile(MultipartFile excelFile) throws Exception
	{
		return notificationDAO.saveNoticesViaExcelFile(excelFile);
	}

	/*
	 * @Override public List<Notification> getNotificationByDepnt(Integer id) {
	 * return notificationDAO.saveNotificationByDepnt(id);
	 *
	 * }
	 */

	/*
	 * @Override public List<Notification> getNotificationByDepAndType(int id, int
	 * typId) {
	 *
	 * return notificationDAO.getNotificationByDepAndType(id, typId);
	 *
	 * }
	 */

	@Override
	public List<NotificationDTO> getChatNotification(Integer id)
	{
		return notificationDAO.getChatNotification(id);
	}

	/*
	 * @Override public List<Notification> getUpNoticeByDepId(int id, int typId) {
	 *
	 * return notificationDAO.getUpcomeNoticeByDepId(id, typId); }
	 *
	 * @Override public List<Notification>
	 * getNotificationWithLike(List<Notification> list) { // TODO Auto-generated
	 * method stub return notificationDAO.getNotificationWithLike(list); }
	 */
	@Override
	public List<CommentDTO> getNotificationComment(Integer noticeId)
	{
		return notificationDAO.getNotificationComment(noticeId);
	}

	@Override
	public String commentInsert(Map<String, String> requestParam)
	{
		// TODO Auto-generated method stub
		return notificationDAO.commentInsert(requestParam);
	}

	@Override
	public Boolean likeIncriment(Like like)
	{
		// TODO Auto-generated method stub
		return notificationDAO.likeIncriment(like);
	}

	@Override
	public Boolean addCooment(Comment comment)
	{
		// TODO Auto-generated method stub
		return notificationDAO.addComment(comment);
	}

	@Override
	public List<NotificationCount> getNotificationCount(Integer id)
	{
		// TODO Auto-generated method stub
		return notificationDAO.getNotificationCount(id);

	}

	@Override
	public List<Notification> getnotices(Integer staffid)
	{
		return notificationDAO.getnotices(staffid);

	}

}
