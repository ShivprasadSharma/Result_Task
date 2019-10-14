package com.zertones.dao.common;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.zertones.dao.BaseDAOImpl;
import com.zertones.model.ComClientName;
import com.zertones.model.ComUserDetails;
import com.zertones.model.DataTransferObjectModel.CommentDTO;
import com.zertones.model.DataTransferObjectModel.DepartmentDTO;
import com.zertones.model.DataTransferObjectModel.NotificationCount;
import com.zertones.model.DataTransferObjectModel.NotificationDTO;
import com.zertones.model.DataTransferObjectModel.NotificationFilesDTO;
import com.zertones.model.common.Comment;
import com.zertones.model.common.Department;
import com.zertones.model.common.Like;
import com.zertones.model.common.Notification;
import com.zertones.model.common.NotificationFiles;
import com.zertones.model.sims.Staff;
import com.zertones.model.sims.Student;
import com.zertones.service.Constants;

@Repository
public class NotificationDAOImpl extends BaseDAOImpl implements NotificationDAO
{
	private static final Logger	logger	= LoggerFactory.getLogger(NotificationDAOImpl.class);
	NotificationFiles			nf;

	@Autowired
	protected SessionFactory	sessionFactory;

	@Override
	public void setSessionFactory(SessionFactory sf)
	{
		this.sessionFactory = sf;
	}

	@Override
	public Notification addNotification(Notification n)
	{
		// n.setLike_Status("false");
		// n.setLike_count(0);
		// n.setComment_count(0);
		ComUserDetails user = getUserDetails();
		n.setPostBy(user.getComClientName().getId());
		Set<NotificationFiles> file = n.getNotificationFiles();
		for (NotificationFiles notificationFiles : file)
		{
			notificationFiles.setInstituteId(user.getInstituteId());
		}
		n.setNotificationFiles(file);
		return (Notification) save(n);
	}

	@Override
	@Transactional
	public Notification getNotification(Integer id)
	{
		Criteria c = getCriteriaForSelect(Notification.class);
		c.add(Restrictions.eq("notificationId", id));
		return (Notification) c.list().get(0);
	}

	@Override
	@Transactional
	public List<Notification> listNotifications()
	{
		logger.info("Listing Notification");
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Notification.class);
		c.setProjection(Constants.NOTIFICATION_PROJECTION_LIST);
		c.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		c.add(Restrictions.isNull("groups"));
		c.add(Restrictions.lt("notificationToDate", getCurrentDate()));
		c.setMaxResults(10);
		c.addOrder(Order.desc("notificationToDate"));
		c.setResultTransformer(Transformers.aliasToBean(Notification.class));
		List<NotificationDTO> list = c.list();
		for (NotificationDTO notification : list)
		{
			notification.setNotificationFilesDTO(getNotificationFile(notification.getNotificationId()));
			notification.setPostNotice(getComClientName(notification.getPostBy()));
		}
		return null;// list;

		/*
		 * Session session = this.sessionFactory.getCurrentSession(); Query q =
		 * session.createQuery(
		 * "From Notification where notificationToDate < CURDATE()  ORDER BY notificationToDate desc"
		 * ); return q.list();
		 */

	}

	@Override
	@Transactional
	public NotificationDTO getNotificationById(Integer id)
	{
		logger.info("Notification loaded successfully");
		// return (Notification) get(Notification.class, id);
		Criteria c = getCriteriaForSelect(Notification.class);
		c.setProjection(Constants.NOTIFICATION_PROJECTION_LIST);
		c.add(Restrictions.eq("notificationId", id));
		c.setResultTransformer(Transformers.aliasToBean(NotificationDTO.class));
		List<NotificationDTO> list = c.list();
		for (NotificationDTO notification : list)
		{
			notification.setLike_count(getLikeCount(notification.getNotificationId()));
			notification.setLike_Status(getNotificationLikeStatus(notification.getNotificationId()));
			notification.setNotificationFilesDTO(getNotificationFile(notification.getNotificationId()));
			notification.setPostNotice(getComClientName(notification.getPostBy()));
		}
		return list.get(0);
	}

	@Override
	@Transactional
	public List<NotificationDTO> getNotificationByDepId(Map<String, String> pathVariables)
	{
		logger.info("List of Notifications");
		/*
		 * Criteria c = getCriteriaForSelect(Notification.class);
		 * c.setProjection(Constants.NOTIFICATION_PROJECTION_LIST); if
		 * (Integer.parseInt(pathVariables.get("type")) != 0) {
		 * c.add(Restrictions.eq("notificationType",
		 * Integer.parseInt(pathVariables.get("type")))); } if
		 * (Integer.parseInt(pathVariables.get("type")) != 0) {
		 * c.add(Restrictions.eq("notificationType",
		 * Integer.parseInt(pathVariables.get("type"))));
		 *
		 * } c.add(Restrictions.isNull("groups")); if
		 * (pathVariables.get("for").equals(Constants.PAST_NOTICES)) {
		 * c.add(Restrictions.le("notificationToDate", getNextDate(1)));
		 *
		 * } else if (pathVariables.get("for").equals(Constants.CURRENT_NOTICES)) {
		 * c.add(Restrictions.between("notificationToDate", getCurrentDate(),
		 * getNextDate(28))); c.add(Restrictions.le("notificationFromDate",
		 * getNextDate(1))); } else if
		 * (pathVariables.get("for").equals(Constants.UPCOMING_NOTICE)) {
		 * c.add(Restrictions.gt("notificationFromDate", getNextDate(1))); } //
		 * c.setMaxResults(10); // c.addOrder(Order.desc("notificationFromDate"));
		 * c.addOrder(Order.desc("notificationId"));
		 * c.setResultTransformer(Transformers.aliasToBean(NotificationDTO.class));
		 * List<NotificationDTO> list = c.list(); for (NotificationDTO notification :
		 * list) {
		 * notification.setLike_count(getLikeCount(notification.getNotificationId()));
		 * notification.setLike_Status(getNotificationLikeStatus(notification.
		 * getNotificationId()));
		 * notification.setNotificationFilesDTO(getNotificationFile(notification.
		 * getNotificationId()));
		 * notification.setPostNotice(getComClientName(notification.getPostBy())); }
		 */ return null;
	}

	public Integer getLikeCount(Integer noticeId)
	{

		Criteria c = getCriteriaForSelect(Like.class);
		c.add(Restrictions.eq("notification_id", noticeId));
		c.add(Restrictions.eq("like_Status", true));
		return c.list().size();
	}

	public Boolean getNotificationLikeStatus(Integer noticeId)
	{
		ComUserDetails user = getUserDetails();
		Boolean result = false;
		// ComUserDetails user = getUserDetails();
		Criteria c = getCriteriaForSelect(Like.class);
		c.add(Restrictions.eq("notification_id", noticeId));
		c.add(Restrictions.eq("client_Id", user.getComClientName().getId()));
		List<Like> like = c.list();
		if (!like.isEmpty())
		{
			Like l = like.get(0);
			result = l.getLike_Status();
		}
		return result;
	}

	// @Override
	// @Transactional
	// public List<Notification> getNoticeByDepId(Integer id, Integer typId)
	// {
	// Session session = this.sessionFactory.getCurrentSession();
	// Query q = session.createQuery("From Notification where department =" + id
	// + "
	// and notificationType =" + typId
	// + " and (notificationFromDate between CURDATE() - 1 and CURDATE() - 8)
	// ORDER
	// BY notificationFromDate asc");
	// logger.info("Notification loaded successfully, Notification details");
	// return q.list();
	// }

	@Override
	@Transactional
	public void removeNotification(Integer id)
	{
		Notification n = (Notification) get(Notification.class, id);
		NotificationFiles nf = n.getNotificationFiles().iterator().next();
		if (delete(nf) != null)
		{
			delete(n);
		}

		logger.info("Notification deleted successfully");
	}

	@Override
	@Transactional
	public List<Notification> listNotificationsforParent()
	{
		logger.info("Listing Notification");
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Notification.class);
		Criterion groups = Restrictions.like("groups", "Parent");
		Criterion notificationType = Restrictions.eq("notificationType", 1);
		LogicalExpression orExp = Restrictions.or(groups, notificationType);
		cr.add(orExp);
		List<Notification> notifications = cr.list();
		session.flush();
		session.close();
		return notifications;
	}

	@Override
	@Transactional
	public List<DepartmentDTO> getDepartment()
	{
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Department.class);
		cr.setProjection(Projections.projectionList().add(Property.forName("dep_id"), "dep_id")
				.add(Property.forName("dep_name"), "dep_name"));
		cr.setResultTransformer(Transformers.aliasToBean(DepartmentDTO.class));
		List<DepartmentDTO> departmentDTOs = cr.list();
		return departmentDTOs;
	}

	@Override
	@Transactional
	public List<NotificationDTO> getEvent(Integer type)
	{
		logger.info("Listing Events");

		Criteria c = getCriteriaForSelect(Notification.class);
		c.setProjection(Projections.projectionList().add(Property.forName("notificationId"), "notificationId")
				.add(Property.forName("notificatiosHeadline"), "notificatiosHeadline")
				.add(Property.forName("notificationDetails"), "notificationDetails")
				.add(Property.forName("notificationFromDate"), "notificationFromDate")
				.add(Property.forName("notificationToDate"), "notificationToDate")
				.add(Property.forName("venue"), "venue"));
		c.add(Restrictions.eq("notificationType", type));
		c.addOrder(Order.desc("notificationId"));
		c.setResultTransformer(Transformers.aliasToBean(NotificationDTO.class));
		List<NotificationDTO> l = c.list();
		for (NotificationDTO notificationDTO : l)
		{
			Set<NotificationFilesDTO> n = getNotificationFile(notificationDTO.getNotificationId());
			for (NotificationFilesDTO notificationFilesDTO : n)
			{
				notificationDTO.setImgUrl(notificationFilesDTO.getString1());
			}
		}
		return l;
	}

	@Override
	@Transactional
	public List<NotificationDTO> getAchivement()
	{
		// TODO Auto-generated method stub
		logger.info("Listing achivements");

		Criteria c = getCriteriaForSelect(Notification.class);
		c.setProjection(
				Projections.projectionList().add(Property.forName("notificatiosHeadline"), "notificatiosHeadline")
						.add(Property.forName("notificationDetails"), "notificationDetails")
						.add(Property.forName("notificationFromDate"), "notificationFromDate")
						.add(Property.forName("notificationToDate"), "notificationToDate"));
		// .add(Property.forName("venue"), "venue"));
		c.add(Restrictions.eq("notificationType", 2));
		c.addOrder(Order.desc("notificationId"));
		c.setResultTransformer(Transformers.aliasToBean(NotificationDTO.class));
		return c.list();

	}

	@Override
	@Transactional
	public List<Notification> getAllUpcomingEvent()
	{
		logger.info("Listing Upcoming Events");
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Notification where " + Constants.RECORD_STATUS + " ='"
				+ Constants.ACTIVE_RECORD_STATUS
				+ "' and notificationType = 4 and (notificationFromDate >= CURDATE()) ORDER BY notificationFromDate asc");
		List<Notification> notifications = query.list();
		session.flush();
		session.close();
		return notifications;
	}

	@Override
	@Transactional
	public List<NotificationDTO> getNoticesSendByTeacher()
	{
		logger.info("get notification send by teacher :");
		return getNoticeByTeacher(Notification.class);
	}

	@Override
	@Transactional
	public void updateNotification(Notification n, MultipartFile image)
	{
		// Session session = sessionFactory.getCurrentSession();
		// Query q = session.createQuery("from NotificationFiles n where
		// n.notification.notificationId = :id ");
		// q.setParameter("id", n.getNotificationId());
		ComUserDetails user = getUserDetails();
		n.setPostBy(user.getComClientName().getId());
		Criteria c = getCriteriaForSelect(NotificationFiles.class);
		c.add(Restrictions.eq("notification.notificationId", n.getNotificationId()));
		NotificationFiles nf = (NotificationFiles) c.list().get(0);
		nf.setNotification(n);
		if (image != null)
		{
			if (!image.isEmpty())
			{
				try
				{
					nf.setString1(uploadImgOnCloud(image));
					nf.setUpdatedDate(new Date());
					nf.setUpdatedBy(user.getUserName());
					nf.setInstituteId(user.getInstituteId());
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		} else
		{
			nf.setUpdatedDate(new Date());
			nf.setUpdatedBy(user.getUserName());
			nf.setInstituteId(user.getInstituteId());
		}
		// session.flush();
		// session.close();
		Update(n);
	}

	@Override
	@Transactional
	public List<NotificationDTO> getGroupNotifications(Integer id)
	{
		// System.out.println("Group Id :" + id);
		Criteria c = getCriteriaForSelect(Notification.class);
		c.setProjection(Constants.NOTIFICATION_PROJECTION_LIST);
		c.add(Restrictions.eq("groups", id));
		c.addOrder(Order.desc("notificationId"));
		c.setMaxResults(10);
		c.addOrder(Order.desc("notificationToDate"));
		c.setResultTransformer(Transformers.aliasToBean(NotificationDTO.class));
		List<NotificationDTO> groupNotices = c.list();
		for (NotificationDTO notification : groupNotices)
		{
			notification.setLike_count(getLikeCount(notification.getNotificationId()));
			notification.setComment_count(getNotificationComment(notification.getNotificationId()).size());
			notification.setLike_Status(getNotificationLikeStatus(notification.getNotificationId()));
			notification.setNotificationFilesDTO(getNotificationFile(notification.getNotificationId()));
			notification.setPostNotice(getComClientName(notification.getPostBy()));
		}
		// System.out.println("Notification :" + groupNotices);
		return groupNotices;
	}

	@Override
	public Notification addGroupNotification(Notification n)
	{
		return (Notification) save(n);
	}

	@Override
	@Transactional
	public List<NotificationDTO> getNotificationByDate(String date)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date fromDate = null;
		Date toDate = null;
		try
		{
			fromDate = df.parse(date + " 00:00:00");
			toDate = df.parse(date + " 23:59:59");

		} catch (Exception e)
		{
			System.out.println("Exception In get notification " + e.getLocalizedMessage());
		}
		// List<Notification> list =
		return getNotificationByDate(fromDate, toDate);

	}

	@Override
	@Transactional
	public List<Notification> getNoticeByDateForWeb()
	{

		Criteria c = getCriteriaForSelect(Notification.class);
		c.setProjection(Constants.NOTIFICATION_PROJECTION_LIST);
		// c.add(Restrictions.eq(Constants.RECORD_STATUS,
		// Constants.ACTIVE_RECORD_STATUS));
		c.add(Restrictions.isNull("groups"));
		c.add(Restrictions.between("notificationToDate", getCurrentDate(), getNextDate(2)));
		c.addOrder(Order.desc("notificationFromDate"));
		c.setResultTransformer(Transformers.aliasToBean(Notification.class));
		List<Notification> list = c.list();
		for (Notification notification : list)
		{
			// notification.setNotificationFiles(getNotificationFile(notification.getNotificationId()));
			// notification.setPostNotice(getComClientName(notification.getPostBy()));
		}
		return list;

	}

	public Set<NotificationFilesDTO> getNotificationFile(Integer id)
	{
		// System.out.println("Test :" + id);
		Criteria c = getCriteriaForSelect(NotificationFiles.class);
		c.setProjection(Projections.projectionList().add(Property.forName("document1Type"), "document1Type")
				.add(Property.forName("string1"), "string1"));
		// c.add(Restrictions.eq(Constants.RECORD_STATUS,
		// Constants.ACTIVE_RECORD_STATUS));
		c.add(Restrictions.eq("notification.notificationId", id));
		c.setResultTransformer(Transformers.aliasToBean(NotificationFilesDTO.class));
		Set<NotificationFilesDTO> setl = new HashSet<>();
		setl.addAll(c.list());
		return setl;
	}

	@Override
	@Transactional
	public List<Notification> getNoticesByDate()
	{
		Criteria c = getCriteriaForSelect(Notification.class);
		c.setProjection(Constants.NOTIFICATION_PROJECTION_LIST);
		// c.add(Restrictions.eq(Constants.RECORD_STATUS,
		// Constants.ACTIVE_RECORD_STATUS));
		c.add(Restrictions.isNull("groups"));
		c.add(Restrictions.between("notificationToDate", getNextDate(1), getNextDate(3)));
		c.addOrder(Order.asc("notificationFromDate"));
		c.setResultTransformer(Transformers.aliasToBean(Notification.class));
		List<Notification> list = c.list();
		for (Notification notification : list)
		{
			// notification.setNotificationFiles(getNotificationFile(notification.getNotificationId()));
			// notification.setPostNotice(getComClientName(notification.getPostBy()));
		}
		return list;
	}

	@Override
	@Transactional
	public String saveNoticesViaExcelFile(MultipartFile excelFile) throws Exception
	{
		String msg = "error while rading file. ";
		Notification notification = null;
		XSSFWorkbook wb;

		wb = new XSSFWorkbook(excelFile.getInputStream());
		XSSFSheet sheet;
		Row row;
		sheet = wb.getSheetAt(2);
		if (sheet.getLastRowNum() > 0)
		{
			for (int i = 1; i <= sheet.getLastRowNum(); i++)
			{
				row = sheet.getRow(i);
				notification = new Notification();

				for (int j = 1; j < row.getLastCellNum(); j++)
				{
					notification.setNotificationFromDate(row.getCell(1).getDateCellValue());
					notification.setNotificationToDate(row.getCell(2).getDateCellValue());
					notification.setNotificatiosHeadline(row.getCell(3).toString());
					notification.setNotificationDetails(row.getCell(4).toString());
					notification.setDepartment((int) row.getCell(5).getNumericCellValue());
					notification.setNotificationType((int) row.getCell(6).getNumericCellValue());
					notification.setDivision((int) row.getCell(7).getNumericCellValue());
					Cell c = row.getCell(8);
					if (c != null)
					{
						notification.setVenue(row.getCell(8).toString());
					}
				}
				save(notification);
				msg = "Congratulation..! File Upload Successfully... ";
			}
		} else
		{
			msg = "Sorry..! Your file is empty can't process.";
		}
		wb.close();
		return msg;
	}

	@Override
	@Transactional
	public List<Notification> saveNotificationByDepnt(Integer id)
	{
		logger.info("Listing Notification");
		Session session = this.sessionFactory.getCurrentSession();
		ComUserDetails user = getUserDetails();
		Query q = null;
		if (user.getUserRole().equals("ROLE_STUDENT"))
		{
			Criteria cr = session.createCriteria(Student.class);
			cr.add(Restrictions.eq("comClientName.id", id));
			cr.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
			Student s = (Student) cr.uniqueResult();
			q = session.createQuery("From Notification where department = 0 or department = " + s.getBranch()
					+ " and notificationType = 0 OR notificationType = 1 and " + Constants.RECORD_STATUS + "= '"
					+ Constants.ACTIVE_RECORD_STATUS + "'");
		} else if (user.getUserRole().equals("ROLE_TEACHER") || user.getUserRole().equals("ROLE_HOD")
				|| user.getUserRole().equals("ROLE_ADMIN"))
		{
			Criteria cr = session.createCriteria(Staff.class);
			cr.add(Restrictions.eq("comClientName.id", id));
			cr.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
			Staff staff = (Staff) cr.uniqueResult();
			q = session.createQuery("From Notification where department = 0 or department = " + staff.getDepartment()
					+ " and notificationType = 0 OR notificationType = 1 and " + Constants.RECORD_STATUS + "= '"
					+ Constants.ACTIVE_RECORD_STATUS + "'");
		}
		session.flush();
		session.close();
		return q.list();
	}

	@Override
	@Transactional
	public List<Notification> getNotificationByDepAndType(Integer id, Integer typId)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Notification.class);
		if (id != 0)
		{
			cr.add(Restrictions.eq("department", id));
		}
		cr.add(Restrictions.eq("notificationType", typId));
		// cr.add(Restrictions.eq("notificationType", 1));
		cr.add(Restrictions.eq("recordStatus", "A"));
		logger.info("Notification loaded successfully, Notification details");
		List<Notification> notifications = cr.list();
		session.flush();
		session.close();
		return notifications;
	}

	@Override
	@Transactional
	public List<NotificationDTO> getChatNotification(Integer id)
	{
		Criteria c = getCriteriaForSelect(Notification.class);
		c.setProjection(Constants.NOTIFICATION_PROJECTION_LIST);
		c.add(Restrictions.eq("mentor", id));
		c.setResultTransformer(Transformers.aliasToBean(NotificationDTO.class));
		List<NotificationDTO> l = c.list();
		for (NotificationDTO notification : l)
		{
			System.out.println("File  " + getNotificationFile(notification.getNotificationId()));
			notification.setLike_count(getLikeCount(notification.getNotificationId()));
			notification.setComment_count(getNotificationComment(notification.getNotificationId()).size());
			notification.setLike_Status(getNotificationLikeStatus(notification.getNotificationId()));
			notification.setNotificationFilesDTO(getNotificationFile(notification.getNotificationId()));
			notification.setPostNotice(getComClientName(notification.getPostBy()));
		}
		return l;
	}

	@Override
	@Transactional
	public List<Notification> getUpcomeNoticeByDepId(int id, int typId)
	{
		logger.info("Listing Notification");
		Criteria c = getCriteriaForSelect(Notification.class);
		c.setProjection(Constants.NOTIFICATION_PROJECTION_LIST);
		// c.add(Restrictions.eq(Constants.RECORD_STATUS,
		// Constants.ACTIVE_RECORD_STATUS));
		c.add(Restrictions.eq("notificationType", typId));
		c.add(Restrictions.eq("department", id));
		c.add(Restrictions.isNull("groups"));
		c.add(Restrictions.between("notificationToDate", getCurrentDate(), getNextDate(2)));
		c.setMaxResults(10);
		c.addOrder(Order.desc("notificationToDate"));
		c.setResultTransformer(Transformers.aliasToBean(Notification.class));
		List<Notification> list = c.list();
		for (Notification notification : list)
		{
			// notification.setNotificationFiles(getNotificationFile(notification.getNotificationId()));
			// notification.setPostNotice(getComClientName(notification.getPostBy()));
		}
		return list;
	}

	@Override
	@Transactional
	public List<Notification> getNotificationWithLike(List<Notification> list)
	{
		Session session = this.sessionFactory.getCurrentSession();
		for (Notification notification : list)
		{
			System.out.println("notification idd::" + notification.getNotificationId());
			Query q = session.createQuery("From Like where client_Id=" + getUserDetails().getComClientName().getId()
					+ " and notification_id=" + notification.getNotificationId());

			List<Like> likes = q.list();
			if (!likes.isEmpty())
			{
				for (Like like : likes)
				{
					// notification.setLike_Status(like.getLike_Status());
					System.out.println("notification bean::");
				}
			}
			session.flush();
			session.close();
		}

		return list;
	}

	@Override
	@Transactional
	public List<CommentDTO> getNotificationComment(Integer noticeId)
	{
		Criteria c = getCriteriaForSelect(Comment.class);
		c.createAlias("comClientName", "comClientName");
		c.setProjection(Projections.projectionList().add(Property.forName("comClientName.firstName"), "firstName")
				.add(Property.forName("comClientName.lastName"), "lastName")
				.add(Property.forName("comClientName.imgUrl"), "imgUrl").add(Property.forName("comment"), "comment")
				.add(Property.forName("noticeId"), "noticeId"));
		c.add(Restrictions.eq("noticeId", noticeId));
		c.addOrder(Order.desc("comment_id"));
		c.setResultTransformer(Transformers.aliasToBean(CommentDTO.class));
		return c.list();
		// return getListById("From Comment", noticeId, "noticeId");
	}

	@Override
	@Transactional
	public String commentInsert(Map<String, String> requestParam)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Comment comment = new Comment();
		comment.setComClientName(session.get(ComClientName.class, Integer.parseInt(requestParam.get("clientId"))));
		comment.setComment(requestParam.get("comment"));
		comment.setNoticeId(Integer.parseInt(requestParam.get("noticeId")));
		save(comment);

		String query = "UPDATE COM_NOTIFICATIONS SET comment_count = "
				+ Integer.parseInt(requestParam.get("CommentCount")) + " WHERE NOTIFICATION_ID = "
				+ Integer.parseInt(requestParam.get("noticeId"));

		// String update = "UPDATE COM_NOTIFICATIONS set comment_count =
		// comment_count +
		// 1 WHERE NOTIFICATION_ID="+ noticeId;
		SQLQuery sqlQuery = session.createSQLQuery(query);
		sqlQuery.executeUpdate();
		session.flush();
		session.close();
		return "done";
	}

	@Override
	@Transactional
	public Boolean addComment(Comment commnet)
	{
		Boolean b = false;
		ComClientName clientName = (ComClientName) get(ComClientName.class, commnet.getComClientName().getId());
		commnet.setComClientName(clientName);
		if (save(commnet) != null)
		{
			b = true;
		}
		return b;
	}

	@Override
	@Transactional
	public Boolean likeIncriment(Like like)
	{

		Criteria c = getCriteriaForSelect(Like.class);
		c.add(Restrictions.eq("notification_id", like.getNotification_id()));
		c.add(Restrictions.eq("client_Id", like.getClient_Id()));
		Like l = (Like) c.uniqueResult();
		if (l != null)
		{
			l.setLike_Status(like.getLike_Status());
			update(l);
		} else
		{
			save(like);
		}
		return like.getLike_Status();
	}

	@Override
	@Transactional
	public List<NotificationCount> getNotificationCount(Integer id)
	{

		List<NotificationCount> notificationCount = new ArrayList<>();
		NotificationCount count = new NotificationCount();
		count.setAcad(getNotificationCount(id, 1));
		count.setAchive(getNotificationCount(0, 2));
		count.setAnnoun(getNotificationCount(0, 3));
		count.setClg(getNotificationCount(0, 0));
		count.setEvet(getNotificationCount(0, 4));
		notificationCount.add(count);
		return notificationCount;
	}

	public Integer getNotificationCount(Integer id, Integer type)
	{
		Criteria cr = getCriteriaForSelect(Notification.class);
		cr.add(Restrictions.eq("department", id));
		cr.add(Restrictions.eq("notificationType", type));
		cr.add(Restrictions.between("notificationToDate", getCurrentDate(), getNextDate(28)));
		cr.add(Restrictions.le("notificationFromDate", getNextDate(1)));
		return cr.list().size();
	}

	@Override
	@Transactional
	public List<Notification> getnotices(Integer staffid)
	{

		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Notification.class);

		cr.add(Restrictions.eq("postBy", staffid));

		cr.add(Restrictions.eq("recordStatus", "A"));

		List<Notification> notifications = cr.list();

		System.out.println("notificationslist" + notifications);
		return notifications;

	}

}
