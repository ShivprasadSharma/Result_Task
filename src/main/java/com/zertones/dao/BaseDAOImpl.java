package com.zertones.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.zertones.model.BaseModel;
import com.zertones.model.ComAttendance;
import com.zertones.model.ComChatMessages;
import com.zertones.model.ComClientName;
import com.zertones.model.ComPracticalAttendance;
import com.zertones.model.ComUserDetails;
import com.zertones.model.DeviceDetails;
import com.zertones.model.DataTransferObjectModel.AttendanceDTOWeb;
import com.zertones.model.DataTransferObjectModel.AttendanceStudentDTO;
import com.zertones.model.DataTransferObjectModel.ComClientNameDTO;
import com.zertones.model.DataTransferObjectModel.DepartmentDTO;
import com.zertones.model.DataTransferObjectModel.Info;
import com.zertones.model.DataTransferObjectModel.NotificationDTO;
import com.zertones.model.common.ApplyedStudentForCompany;
import com.zertones.model.common.Department;
import com.zertones.model.common.GroupMembers;
import com.zertones.model.common.Groups;
import com.zertones.model.common.InstituteInfoMaster;
import com.zertones.model.common.Notification;
import com.zertones.model.common.UserToken;
import com.zertones.model.master.AcademicSubject;
import com.zertones.model.master.ComStaffSubject;
import com.zertones.model.sims.Mentor;
import com.zertones.model.sims.MentorStudent;
import com.zertones.model.sims.Staff;
import com.zertones.model.sims.Student;
import com.zertones.service.Constants;
import com.zertones.service.UserDetailsService;
import com.zertones.system.service.AuditInterceptor;
import com.zertones.system.utility.ComClientNameDTORollNoComparator;
import com.zertones.system.utility.ComClientNameDtoChainedCompareter;

@Repository
public class BaseDAOImpl implements BaseDAO
{
	@Autowired
	protected SessionFactory	sessionFactory;

	@Autowired
	protected UserDetailsDAO	userDao;

	@Override
	public void setSessionFactory(SessionFactory sf)
	{
		this.sessionFactory = sf;
	}

	private UserDetailsService userDetailsService;

	@Autowired(required = true)
	@Qualifier(value = "userDetailsService")
	protected void setUserDetailsService(UserDetailsService userDetailsService)
	{
		this.userDetailsService = userDetailsService;
	}

	protected ComUserDetails getUserDetails()
	{

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userDetailsService.getUserByUsername(authentication.getName());

	}

	protected Criteria getCriteriaForSelect(Class modelClass)
	{

		ComUserDetails user = getUserDetails();
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(modelClass);
		criteria.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		criteria.add(Restrictions.eq(Constants.INSTITUTE_VARIABLE_NAME, user.getInstituteId()));
		return criteria;
	}

	@SuppressWarnings(
	{ "unchecked", "rawtypes" })
	protected Object get(Class modelClass, Integer id)
	{
		Session session = getHibernateSession();
		Object obj = session.get(modelClass, id);
		session.flush();
		session.close();
		return obj;
	}

	protected Date getCurrentDate()
	{
		// 2018-08-21
		// Session session = this.sessionFactory.getCurrentSession();
		// Query d = session.createSQLQuery("SELECT CURDATE() ");
		// return (Date) d.uniqueResult();
		String newstring = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Date date = new Date();
		try
		{
			date = new SimpleDateFormat("yyyy-MM-dd").parse(newstring);

		} catch (ParseException e)
		{
			e.printStackTrace();
		}

		return date;
	}

	protected Date getNextDate(Integer Interval)
	{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date nxtDate = new Date();
		try
		{
			// formater.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_YEAR, Interval);
			nxtDate = formater.parse(formater.format(calendar.getTime()));

		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return nxtDate;
	}

	protected Date getPreviousDate(Integer interval)
	{
		/*
		 * Session session = this.sessionFactory.getCurrentSession(); Query d =
		 * session.createSQLQuery("select DATE( DATE_SUB(NOW(), INTERVAL 2 DAY))");
		 * return (Date) d.uniqueResult();
		 */

		LocalDate date = LocalDate.now();
		date = date.minusDays(interval);
		String nextDt = date.toString();
		Date previousDay = new Date();

		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

		try
		{
			previousDay = formater.parse(nextDt);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return previousDay;
	}

	protected String getComClientName(Integer id)
	{
		ComClientName name = (ComClientName) get(ComClientName.class, id);

		return name.getFirstName() + " " + name.getLastName();
	}

	protected List get(String hql)
	{
		ComUserDetails user = getUserDetails();
		Session session = getHibernateSession();
		hql = hql + " where instituteId = " + user.getInstituteId() + " and " + Constants.RECORD_STATUS + " = " + "'"
				+ Constants.ACTIVE_RECORD_STATUS + "'";
		Query query = session.createQuery(hql);
		List list = query.list();
		session.flush();
		session.close();
		return list;
	}

	protected List getNoticeByTeacher(Class modelClass)
	{
		ComUserDetails user = getUserDetails();
		Criteria cr = getCriteriaForSelect(modelClass);
		cr.setProjection(Constants.NOTIFICATION_PROJECTION_LIST);
		cr.setResultTransformer(Transformers.aliasToBean(NotificationDTO.class));
		cr.add(Restrictions.eq("postBy", user.getComClientName().getId()));
		List<NotificationDTO> dto = cr.list();
		for (NotificationDTO notificationDTO : dto)
		{
			notificationDTO.setPostNotice(user.getComClientName().getFirstName());
		}
		return dto;
	}

	protected List getInfo(String hql)
	{
		ComUserDetails user = getUserDetails();
		Session session = getHibernateSession();
		hql = hql + "and instituteId = " + user.getInstituteId() + " and " + Constants.RECORD_STATUS + " = " + "'"
				+ Constants.ACTIVE_RECORD_STATUS + "'";
		Query query = session.createQuery(hql);
		List list = query.list();
		session.flush();
		session.close();
		return list;
	}

	protected List getList(String hql)
	{
		ComUserDetails user = getUserDetails();
		Session session = getHibernateSession();
		hql = hql + " where instituteId = " + user.getInstituteId() + " and " + Constants.RECORD_STATUS + " = " + "'"
				+ Constants.ACTIVE_RECORD_STATUS + "'";
		Query query = session.createQuery(hql);
		List list = query.list();
		session.flush();
		session.close();
		return list;
	}

	protected List getListById(String hql, Integer id, String parameter)
	{

		/*
		 * ComUserDetails user = getUserDetails(); Session session =
		 * getHibernateSession(); hql = hql + " where instituteId = " +
		 * user.getInstituteId() + " and " + parameter + "=" + id + " and " +
		 * Constants.RECORD_STATUS + " = " + "'" + Constants.ACTIVE_RECORD_STATUS + "'";
		 * Query query = session.createQuery(hql); List list = query.list();
		 * session.flush(); session.close(); return list;
		 */

		Session session = getHibernateSession();
		Criteria c = session.createCriteria(AcademicSubject.class);
		c.add(Restrictions.eq("sub_id", id));
		List list = c.list();
		session.flush();
		session.close();
		return list;

	}

	protected Object getObjectById(String hql, Integer id, String parameter)
	{

		ComUserDetails user = getUserDetails();
		Session session = getHibernateSession();
		hql = hql + " where instituteId = " + user.getInstituteId() + " and " + parameter + "=" + id + " and "
				+ Constants.RECORD_STATUS + " = " + "'" + Constants.ACTIVE_RECORD_STATUS + "'";
		Query query = session.createQuery(hql);
		Object obj = query.uniqueResult();
		session.flush();
		session.close();
		return obj;
	}

	public List getListForEvent(Integer depId, Integer divId)
	{
		Session session = getHibernateSession();
		String hql = "from Student where year=" + divId + " and Branch=" + depId + " and " + Constants.RECORD_STATUS
				+ " = " + "'" + Constants.ACTIVE_RECORD_STATUS + "'";
		Query query = session.createQuery(hql);
		// System.out.println("List Of Student For Event :" + query.list());
		List list = query.list();
		session.flush();
		session.close();
		return list;
	}

	protected Criteria getCriteria(Class modelClass)
	{
		Session session = this.sessionFactory.getCurrentSession();
		return session.createCriteria(modelClass);
	}

	protected Object save(BaseModel baseModel)
	{
		ComUserDetails user = getUserDetails();
		Session session = getHibernateSession();
		baseModel.setInstituteId(user.getInstituteId());
		session.saveOrUpdate(baseModel);
		session.flush();
		session.close();
		return baseModel;
	}

	protected Object signup(BaseModel baseModel)
	{
		ComUserDetails user = getUserDetails();
		Session session = getHibernateSession();
		baseModel.setRecordStatus("D");
		baseModel.setInstituteId(user.getInstituteId());
		session.saveOrUpdate(baseModel);
		session.flush();
		session.close();
		return baseModel;
	}

	protected Object update(BaseModel baseModel)
	{
		ComUserDetails user = getUserDetails();
		Session session = getHibernateSession();
		baseModel.setUpdatedBy(user.getUserName());
		baseModel.setUpdatedDate(user.getUpdatedDate());
		baseModel.setInstituteId(user.getInstituteId());
		baseModel.setRecordStatus(Constants.ACTIVE_RECORD_STATUS);
		session.update(baseModel);
		session.flush();
		session.close();
		return baseModel;
	}

	protected Object delete(BaseModel baseModel)
	{
		Session session = getHibernateSession();
		baseModel.setRecordStatus(Constants.DELETED_RECORD_STATUS);
		session.update(baseModel);
		session.flush();
		session.close();
		return baseModel;
	}

	protected Integer remove(Integer id)
	{
		Criteria criteria = getCriteriaForSelect(Groups.class);
		criteria.add(Restrictions.eq("groupId", id));
		Groups groups = (Groups) criteria.uniqueResult();
		if (groups.getGfm_id() != null)
		{

			Session session1 = this.sessionFactory.getCurrentSession();
			Mentor mentor = session1.get(Mentor.class, groups.getGfm_id());
			mentor.setRecordStatus(Constants.DELETED_RECORD_STATUS);
		}

		int result = 0;
		Session session = getHibernateSession();
		Groups g = (Groups) get(Groups.class, id);
		Groups gp = (Groups) delete(g);
		if (gp.getRecordStatus().equals(Constants.DELETED_RECORD_STATUS))
		{
			List<GroupMembers> gm = getListById("From GroupMembers", id, "groups.groupId");
			for (GroupMembers groupMembers : gm)
			{
				delete(groupMembers);
			}
			result = 1;
		}

		session.flush();
		session.close();
		return result;
	}

	protected Integer removeGroupMember(Integer id)
	{
		int result = 0;
		Integer membid = id;

		Session session2 = getHibernateSession();
		GroupMembers gm = (GroupMembers) get(GroupMembers.class, id);
		gm = (GroupMembers) delete(gm);

		System.out.println("menttttttttttttt" + id);
		if (gm.getRecordStatus().equals(Constants.DELETED_RECORD_STATUS))
		{
			result = 1;
		}
		session2.flush();
		session2.close();

		/*
		 * try {
		 *
		 * Session session1 = getHibernateSession(); Criteria criteria1 =
		 * getCriteriaForSelect(GroupMembers.class);
		 * criteria1.add(Restrictions.eq("membserId", membid)); GroupMembers grpmember =
		 * (GroupMembers) criteria1.uniqueResult();
		 * System.out.println("grroooooooooooup" + grpmember);
		 *
		 * Criteria criteria2 = getCriteriaForSelect(Groups.class);
		 * criteria2.add(Restrictions.eq("gfm_id", grpmember.getGroups().getGroupId()));
		 * Groups groups = (Groups) criteria2.uniqueResult();
		 * System.out.println("grrrrrrrrrrrrrroooooo" + groups);
		 *
		 * if (groups.getGfm_id() != null) {
		 *
		 * Criteria criteria3 = getCriteriaForSelect(Mentor.class);
		 * criteria3.add(Restrictions.eq("id", groups.getGfm_id())); Mentor mentorr =
		 * (Mentor) criteria3.uniqueResult(); System.out.println("groupid" +
		 * mentorr.getId());
		 *
		 * Criteria criteria4 = getCriteriaForSelect(Student.class);
		 * criteria4.add(Restrictions.eq("comClientName.id",
		 * grpmember.getComClientName().getId())); Student studentssss = (Student)
		 * criteria4.uniqueResult(); System.out.println("studentid" +
		 * studentssss.getStudentId());
		 *
		 * System.out.println("iddddddddd" + id);
		 * System.out.println("mentttttttttttttttttorrrr");
		 *
		 * Criteria criteria = getCriteriaForSelect(MentorStudent.class);
		 * criteria.add(Restrictions.eq("mentor.id", mentorr.getId()));
		 * criteria.add(Restrictions.eq("student.studentId",
		 * studentssss.getStudentId())); MentorStudent mentorstudd = (MentorStudent)
		 * criteria.uniqueResult();
		 *
		 * System.out.println("mentorstudd" + mentorstudd.getMid());
		 *
		 * String hql = "DELETE MentorStudent where mid=:memId"; Query query =
		 * session1.createQuery(hql); query.setParameter("memId", mentorstudd.getMid());
		 * query.executeUpdate(); session1.flush(); session1.close(); } } catch
		 * (Exception e) { System.out.println("null pointer exceprtion"); }
		 */
		return result;

	}

	protected Integer updateGroupMember(List<GroupMembers> gp, Groups group)
	{
		int result = 0;
		Session session = getHibernateSession();
		GroupMembers gm = new GroupMembers();
		for (GroupMembers groupMembers : gp)
		{
			gm = (GroupMembers) get(GroupMembers.class, groupMembers.getMembserId());
			gm = (GroupMembers) Update(gm);
			result = 1;
		}
		session.flush();
		session.close();
		return result;

	}

	private Session getHibernateSession()
	{
		return this.sessionFactory.withOptions().interceptor(new AuditInterceptor()).openSession();
	}

	protected Staff getUserAuthentication(String name)
	{
		System.out.println("base model use auth meyhod...............");

		Session session = getHibernateSession();
		Staff staff = new Staff();
		ComUserDetails usr = new ComUserDetails();
		Criteria c = session.createCriteria(ComUserDetails.class, "comClientNameAlies");
		// c.createAlias("comClientNameAlies.comUserDetails", "comUserDetailsAlies");
		c.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		c.add(Restrictions.eq("comClientNameAlies.userName", name));
		usr = (ComUserDetails) c.uniqueResult();
		System.out.println("b model use auth meyhod...............");

		if (usr.getUserRole().equals("ROLE_STUDENT"))
		{
			Criteria cr = session.createCriteria(Student.class);
			cr.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
			cr.add(Restrictions.eq("comClientName.id", usr.getComClientName().getId()));
			Student student = (Student) cr.uniqueResult();
			staff.setComClientName(student.getComClientName());
			staff.setDesignation("Student");
			session.flush();
			session.close();
			return staff;

		} else
		{
			Criteria cr = session.createCriteria(Staff.class, "staffAlies");
			cr.createAlias("staffAlies.comClientName", "comClient");
			cr.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
			cr.add(Restrictions.eq("comClient.id", usr.getComClientName().getId()));
			staff = (Staff) cr.uniqueResult();
			// System.out.println("Test :" + staff);
			session.flush();
			session.close();
			return staff;

		}

	}

	public ComClientName getUserAuthenticationOfMobileUser(DeviceDetails deviceDetails)
	{

		Session session = getHibernateSession();
		ComClientName clientName = new ComClientName();
		Criteria c = session.createCriteria(ComClientName.class, "comClientNameAlies");
		c.createAlias("comClientNameAlies.comUserDetails", "comUserDetailsAlies");
		c.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		c.add(Restrictions.eq("comUserDetailsAlies.userName", deviceDetails.getComUserDetails().getUserName()));
		c.add(Restrictions.eq("comUserDetailsAlies.password",
				userDao.enCoder256(deviceDetails.getComUserDetails().getPassword())));
		clientName = (ComClientName) c.uniqueResult();

		if (clientName != null)
		{
			ComUserDetails comuserdetails = new ComUserDetails();
			comuserdetails.setId(clientName.getComUserDetails().getId());

			/*
			 * DeviceDetails details = (DeviceDetails) session
			 * .createQuery("from DeviceDetails as d where  d.recordStatus = 'A' and  (d.comUserDetails.id = "
			 * + clientName.getComUserDetails().getId() + " or d.deviceId = '" +
			 * deviceDetails.getDeviceId() + "')") .uniqueResult();
			 */

			Criteria cr = session.createCriteria(DeviceDetails.class);
			// cr.add(Restrictions.eq("comUserDetails.id",
			// clientName.getComUserDetails().getId()));
			cr.add(Restrictions.eq("deviceId", deviceDetails.getDeviceId()));
			cr.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
			DeviceDetails details = (DeviceDetails) cr.uniqueResult();

			if (details != null && details.getComUserDetails().getId() != clientName.getComUserDetails().getId())
			{
				details.setDeviceId(deviceDetails.getDeviceId());
				details.setDeviceModel(deviceDetails.getDeviceModel());
				details.setDeviceType(deviceDetails.getDeviceType());
				details.setInstituteId(clientName.getInstituteId());
				details.setComUserDetails(comuserdetails);
				session.update(details);

			} else if (details == null)
			{

				DeviceDetails detail = new DeviceDetails();
				detail.setDeviceId(deviceDetails.getDeviceId());
				detail.setDeviceModel(deviceDetails.getDeviceModel());
				detail.setDeviceType(deviceDetails.getDeviceType());
				detail.setInstituteId(clientName.getInstituteId());
				detail.setComUserDetails(comuserdetails);
				session.save(detail);
			}

			try
			{
				Info info = getDepartment(clientName);
				clientName.setInfo(info);
				clientName.setDepartment(info.getDep());
			} catch (Exception e)
			{
				// TODO: handle exception
			}

		}
		session.flush();
		session.close();
		return clientName;
	}

	public String getYearName(Integer id)
	{

		String yr = "FE";
		if (id != null)
		{
			if (id == 0)
			{
				yr = "All";
			} else if (id == 2)
			{
				yr = "SE";
			} else if (id == 3)
			{
				yr = "TE";
			} else if (id == 4)
			{
				yr = "BE";
			}
		}
		return yr;
	}

	public Department getDepartment(Integer id)
	{
		return (Department) getObjectById("From Department", id, "dep_id");
	}

	public List<DepartmentDTO> getDepartmentListByClientId(Integer cid)
	{
		/*
		 * Staff staff = (Staff) getObjectById("From Staff", cid, "comClientName.id");
		 * // List list = getListById("From Department", staff.getDepartment(),
		 * "dep_id"); Session session = this.sessionFactory.getCurrentSession();
		 * Criteria cr = session.createCriteria(Department.class);
		 * cr.setProjection(Projections.projectionList().add(Property.forName("dep_id"),
		 * "dep_id") .add(Property.forName("dep_name"), "dep_name")); //
		 * cr.add(Restrictions.eq("dep_id", 0)); Criterion criterion1 =
		 * Restrictions.eq("dep_id", 0); Criterion criterion2 =
		 * Restrictions.eq("dep_id", staff.getDepartment()); Criterion criterion3 =
		 * Restrictions.or(criterion1, criterion2); cr.add(criterion3);
		 * cr.setResultTransformer(Transformers.aliasToBean(DepartmentDTO.class));
		 * session.flush(); return cr.list();
		 */
		return null;
	}

	public Info getDepartment(ComClientName clientName)
	{
		Info info = new Info();
		Student s = new Student();
		Staff stf = new Staff();
		Session session = getHibernateSession();
		if (clientName.getComUserDetails().getUserRole().equals("ROLE_STUDENT"))
		{
			Criteria cr = session.createCriteria(Student.class, "student");
			cr.createAlias("student.comClientName", "comClientName");
			// cr.setProjection(Projections.projectionList().add(Property.forName("branch")));
			cr.add(Restrictions.eq("comClientName.id", clientName.getId()));
			s = (Student) cr.uniqueResult();
			info.setClas(getYearName(s.getYear()));
			info.setDivs(s.getStandard().toString());
			info.setRollNo(s.getRollNo());
			info.setYear(s.getYear().toString());
			Department department = (Department) get(Department.class, s.getBranch());
			info.setDep(department.getDep_id());
			info.setDeptName(department.getDep_name());
		} else
		{
			Criteria cr = session.createCriteria(Staff.class, "staff");
			cr.createAlias("staff.comClientName", "comClientName");
			// cr.setProjection(Projections.projectionList().add(Property.forName("department")));
			cr.add(Restrictions.eq("comClientName.id", clientName.getId()));
			stf = (Staff) cr.uniqueResult();
			Department department = (Department) get(Department.class, stf.getDepartment());
			info.setDep(department.getDep_id());
			info.setDeptName(department.getDep_name());
			info.setDesignation(stf.getDesignation());
		}
		session.flush();
		session.close();
		return info;
	}

	public List<ComChatMessages> getChat(Integer id)
	{
		Session session = getHibernateSession();
		Query query = session.createQuery("from ComChatMessages where toId =:id and viewStatus =:sts");
		query.setInteger("id", id);
		query.setInteger("sts", 0);
		List<ComChatMessages> list = query.list();
		session.flush();
		session.close();
		return list;
	}

	protected List getNotificationByDate(Date fromDate, Date toDate)
	{
		Criteria cr = getCriteriaForSelect(Notification.class);
		cr.setProjection(Constants.NOTIFICATION_PROJECTION_LIST);
		cr.add(Restrictions.isNull("groups"));
		Criterion f = Restrictions.lt("notificationFromDate", toDate);
		Criterion x = Restrictions.ge("notificationToDate", fromDate);
		cr.add(x);
		cr.add(f);
		cr.setResultTransformer(Transformers.aliasToBean(NotificationDTO.class));
		List list = cr.list();
		// System.out.println("List :" + list);
		return list;
	}

	public void saveCahtMsg(ComChatMessages comChatMessages)
	{
		Session session = getHibernateSession();
		ComUserDetails user = getUserDetails();
		comChatMessages.setInstituteId(user.getInstituteId());
		session.saveOrUpdate(comChatMessages);
		session.flush();
		session.close();
	}

	public List<Staff> getUserList(Integer id)
	{
		Session session = getHibernateSession();
		ComUserDetails user = getUserDetails();
		Criteria c = session.createCriteria(Staff.class);
		c.add(Restrictions.eq("staffType", id));
		c.add(Restrictions.eq("instituteId", user.getInstituteId()));
		c.add(Restrictions.eq("recordStatus", Constants.ACTIVE_RECORD_STATUS));
		List<Staff> list = c.list();
		session.flush();
		session.close();
		return list;

	}

	protected Object Update(BaseModel baseModel)
	{
		ComUserDetails user = getUserDetails();
		Date dt = new Date();
		Session session = getHibernateSession();
		baseModel.setUpdatedBy(user.getUpdatedBy());
		baseModel.setUpdatedDate(dt);
		baseModel.setRecordStatus(Constants.ACTIVE_RECORD_STATUS);
		baseModel.setInstituteId(user.getInstituteId());
		session.update(baseModel);
		session.flush();
		session.close();
		return baseModel;
	}

	@Override
	public String uploadImgOnCloud(MultipartFile file) throws IOException
	{
		Map config = ObjectUtils.asMap("cloud_name", "dcptr5ivh", "api_key", "297869565358947", "api_secret",
				"Lca6u4EW3lo-weSppOf1MwGz6v0");
		Cloudinary cloudinary = new Cloudinary(config);
		Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
		return uploadResult.get("url").toString();
	}

	public List getGroupDetail(Integer id)
	{
		Session session = getHibernateSession();
		Query query = session.createQuery("from Groups where groupId =:id ");
		query.setInteger("id", id);
		List list = query.list();
		session.flush();
		session.close();
		return list;

	}

	public ComClientName findUserByEmailAddress(String emailAddress)
	{
		Session session = getHibernateSession();
		Query query = session.createQuery("from ComClientName where emailId =:emailid ");
		query.setString("emailid", emailAddress);
		List<ComClientName> comClientName = query.list();
		ComClientName c = null;
		if (comClientName.size() == 1)
		{
			c = comClientName.get(0);
		}
		session.flush();
		session.close();
		return c;
	}

	protected Object saveObject(Object userToken)
	{
		Session session = getHibernateSession();
		UserToken user = (UserToken) userToken;
		UserToken usertkn = new UserToken();
		usertkn = (UserToken) getUser("from UserToken", "comClientName", user.getComClientName().getId());

		if (usertkn != null)
		{
			usertkn.setVerificationToken(user.getVerificationToken());
			usertkn.setVerificatioAttempt(0);
			usertkn.setRecordStatus(0);
			usertkn.setTokenExpiration(user.getTokenExpiration());
			session.update(usertkn);
		} else
		{
			user.setVerificatioAttempt(0);
			user.setRecordStatus(0);
			session.saveOrUpdate(user);
		}
		session.flush();
		session.close();
		return userToken;
	}

	protected Object getUser(String hql, String clause, String param)
	{
		Session session = getHibernateSession();
		Query query = session.createQuery(hql + "  where " + clause + " =:token ");
		query.setString("token", param);
		Object user = query.uniqueResult();
		session.flush();
		session.close();
		return user;
	}

	protected Object getUser(String hql, String clause, Integer id)
	{
		Session session = getHibernateSession();
		Query query = session.createQuery(hql + "  where " + clause + " =:id ");
		query.setInteger("id", id);
		Object user = query.uniqueResult();
		session.flush();
		session.close();
		return user;
	}

	protected Object updateUserToken(Object user)
	{
		Session session = getHibernateSession();
		session.update(user);
		session.flush();
		session.close();
		return user;
	}

	protected List getListOfGroupByUser(Integer id)
	{
		Session session = getHibernateSession();
		ComUserDetails user = getUserDetails();
		String hql = "from GroupMembers gm" + " where gm.comclientName.id = " + id + "and " + Constants.RECORD_STATUS
				+ " = " + "'" + Constants.ACTIVE_RECORD_STATUS + "'";
		Query query = session.createQuery(hql);
		return query.list();

	}

	protected Boolean getUserDtl(ComUserDetails userdtl)
	{
		Boolean result = false;
		try
		{
			Session session = getHibernateSession();
			Criteria criteria = session.createCriteria(ComUserDetails.class);
			criteria.add(Restrictions.eq("userName", userdtl.getUserName()));
			criteria.add(Restrictions.eq("password", userdtl.getPassword()));
			if (criteria.uniqueResult() != null)
			{
				result = true;
			}
			session.flush();
			session.close();
		} catch (Exception e)
		{
			result = true;
			e.getMessage();
		}
		return result;
	}

	protected Integer deleteMentorStuddent(Integer[] i)
	{

		Session session = getHibernateSession();
		MentorStudent ms = (MentorStudent) get(MentorStudent.class, i[0]);
		Mentor m = (Mentor) get(Mentor.class, ms.getMentor().getId());

		Criteria cr = getCriteriaForSelect(Groups.class);
		cr.add(Restrictions.eq("gfm_id", m.getId()));
		Groups groups = (Groups) cr.uniqueResult();
		System.out.println("mentorgroupssssssss" + groups);
		for (Integer mentId : i)
		{
			MentorStudent mentorStudent = session.get(MentorStudent.class, mentId);
			Criteria groum = getCriteriaForSelect(GroupMembers.class);
			groum.add(Restrictions.eq("groups.groupId", groups.getGroupId()));
			groum.add(Restrictions.eq("comClientName.id", mentorStudent.getStudent().getComClientName().getId()));
			GroupMembers groupMembers = (GroupMembers) groum.uniqueResult();
			session.delete(mentorStudent);
			session.delete(groupMembers);
		}
		session.flush();
		session.close();
		return m.getId();

	}

	@Override
	public List<ComClientNameDTO> getGroupMemberByGroupId(Integer gid)
	{

		Criteria cm = getCriteriaForSelect(GroupMembers.class);
		cm.createAlias("comClientName", "comClientName");
		cm.setProjection(Projections.projectionList().add(Property.forName("membserId"), "membserId")
				.add(Property.forName("comClientName.id"), "id")
				.add(Property.forName("comClientName.firstName"), "firstName")
				.add(Property.forName("comClientName.lastName"), "lastName")
				.add(Property.forName("comClientName.imgUrl"), "imgUrl"));
		cm.add(Restrictions.eq("groups.groupId", gid));
		cm.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		cm.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));
		List<ComClientNameDTO> l = cm.list();
		return l;
	}

	protected Integer findYearUsingSem(String semNo)
	{
		if (semNo.equals("1") || semNo.equals("2"))
		{
			return 1;
		} else if (semNo.equals("3") || semNo.equals("4"))
		{
			return 2;
		} else if (semNo.equals("5") || semNo.equals("6"))
		{
			return 3;
		} else if (semNo.equals("7") || semNo.equals("8"))
		{
			return 4;
		} else
		{
			return null;
		}
	}

	protected Integer findStanderdUSingDivision(String div)
	{
		if (div.equalsIgnoreCase("A"))
		{
			return 1;
		} else if (div.equalsIgnoreCase("B"))
		{
			return 2;
		} else if (div.equalsIgnoreCase("C"))
		{
			return 3;
		} else if (div.equalsIgnoreCase("D"))
		{
			return 4;
		} else if (div.equalsIgnoreCase("E"))
		{
			return 5;
		} else
		{
			return 0;
		}
	}

	protected String findDivisionusingStanderd(int standerd)
	{
		if (standerd == 1)
		{
			return "A";
		} else if (standerd == 2)
		{
			return "B";
		} else if (standerd == 3)
		{
			return "C";
		} else if (standerd == 4)
		{
			return "D";
		} else if (standerd == 5)
		{
			return "E";
		} else
		{
			return null;
		}
	}

	protected List<Integer> getid(int id, String PERfieldname, String getfieldname, String tablename)
	{

		Session session = getHibernateSession();
		String hql = "select " + PERfieldname + " from " + tablename + " where " + getfieldname + "=" + id;
		Query query = session.createQuery(hql);
		List<Integer> idlist = query.list();
		return idlist;
	}

	protected Boolean findUser(String emailid)
	{
		Boolean result = false;
		try
		{
			Session session = getHibernateSession();
			Criteria criteria = session.createCriteria(ComClientName.class);
			criteria.add(Restrictions.eq("emailId", emailid));

			if (criteria.uniqueResult() != null)
			{
				result = true;
			}
			session.flush();
			session.close();
		} catch (Exception e)
		{
			result = true;
			e.getMessage();
		}
		return result;
	}

	protected AttendanceDTOWeb getDalilyAttendance(ComAttendance comAttendance, int subjectID)
	{
		Session session = this.sessionFactory.getCurrentSession();
		ComUserDetails user = getUserDetails();
		DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy");
		Date date = comAttendance.getClass_date();
		Calendar cal = Calendar.getInstance();
		String[] strDays = new String[]
		{ "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday", "Friday", "Saturday" };
		cal.setTime(date);

		// Set time fields to zero
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		// Put it back in the Date object

		Criteria cr = getCriteriaForSelect(ComAttendance.class);
		date = cal.getTime();
		cr.add(Restrictions.eq("class_date", date));
		if (subjectID != 0)
		{
			Criteria cr11 = getCriteriaForSelect(ComStaffSubject.class);
			cr11.add(Restrictions.eq("academicSubject.sub_id", subjectID));
			cr11.add(Restrictions.eq("clientName.id", user.getComClientName().getId()));
			ComStaffSubject comStaffSubject1 = (ComStaffSubject) cr11.uniqueResult();
			comAttendance.setSem(Integer.parseInt(comStaffSubject1.getAcademicSubject().getSemister()));
			comAttendance.setYear(findYearUsingSem(comStaffSubject1.getAcademicSubject().getSemister()));
			comAttendance
					.setDep_Id(Integer.toString(comStaffSubject1.getAcademicSubject().getDepartment().getDep_id()));

			cr.add(Restrictions.eq("comStaffSubject.staff_sub_id", comStaffSubject1.getStaff_sub_id()));

		}

		cr.add(Restrictions.eq("Dep_Id", comAttendance.getDep_Id()));
		cr.add(Restrictions.eq("division", comAttendance.getDivision()));
		cr.add(Restrictions.eq("year", comAttendance.getYear()));
		cr.add(Restrictions.eq("sem", comAttendance.getSem()));

		List<ComAttendance> comAttendanceList = cr.list();

		// set attendance data.....................
		AttendanceDTOWeb attendanceDTOWeb = new AttendanceDTOWeb();
		attendanceDTOWeb.setClass_name(getYearName(comAttendance.getYear()));
		attendanceDTOWeb.setAt_Date(dateFormat.format(comAttendance.getClass_date()));
		attendanceDTOWeb.setSemister(Integer.toString(comAttendance.getSem()));
		attendanceDTOWeb.setDay(strDays[cal.get(Calendar.DAY_OF_WEEK) - 1]);
		attendanceDTOWeb.setDivision(comAttendance.getDivision());
		attendanceDTOWeb.setAcadmic_year(cal.get(Calendar.YEAR));

		Department department = session.get(Department.class, Integer.parseInt(comAttendance.getDep_Id()));
		attendanceDTOWeb.setDepartment(department.getDep_name());

		int count = 1;
		List<Integer> presentList = null;
		List<AttendanceStudentDTO> attendanceStudentDTOs = new ArrayList<>();
		if (!comAttendanceList.isEmpty())
		{

			// student List get
			attendanceStudentDTOs = getStudentListForAttendance(comAttendance);

			// use for total present student
			AttendanceStudentDTO lastTotalElement = new AttendanceStudentDTO();
			lastTotalElement.setFirstName("Total");
			attendanceStudentDTOs.add(attendanceStudentDTOs.size(), lastTotalElement);

			// System.out.println("com list.............." + attendanceStudentDTOs);

			for (ComAttendance comAttendance2 : comAttendanceList)
			{

				attendanceDTOWeb.getLectureList().add(comAttendance2.getLectureTheoryTime().getStart_time() + "-"
						+ comAttendance2.getLectureTheoryTime().getEnd_time());
				attendanceDTOWeb.getSubList()
						.add(comAttendance2.getComStaffSubject().getAcademicSubject().getSubject_name());
				presentList = new ArrayList();
				// present stud list
				for (int i = 1; i <= 200; i++)
				{
					try
					{
						Class ftClass = comAttendance2.getClass();
						Field f1 = ftClass.getField("roll_" + i);
						if (f1.get(comAttendance2) == null)
						{
							break;
						} else
						{
							presentList.add((Integer) f1.get(comAttendance2));
						}

					} catch (Exception e)
					{
						System.out.println(e);
					}
				}
				for (int i = 0; i < attendanceStudentDTOs.size() - 1; i++)
				{

					if (presentList.contains(attendanceStudentDTOs.get(i).getId()))
					{
						attendanceStudentDTOs.get(i).getStudAttendance().add("P");

					} else
					{
						attendanceStudentDTOs.get(i).getStudAttendance().add("A");

					}

				}

				attendanceStudentDTOs.get(attendanceStudentDTOs.size() - 1).getStudAttendance()
						.add(Integer.toString(presentList.size()));

			}
		} else
		{
			return null;

		}

		attendanceDTOWeb.getStudentList().addAll(attendanceStudentDTOs);
		return attendanceDTOWeb;
	}

	protected List<AttendanceStudentDTO> getStudentListForAttendance(ComAttendance comAttendance)
	{

		Criteria cr = getCriteriaForSelect(Student.class);
		cr.createAlias("comClientName", "comClientName");
		cr.setProjection(Projections.projectionList().add(Property.forName("comClientName.id"), "id")
				.add(Property.forName("comClientName.firstName"), "firstName")
				.add(Property.forName("comClientName.middleName"), "middleName")
				.add(Property.forName("comClientName.lastName"), "lastName").add(Property.forName("rollNo"), "rollNo"));
		cr.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		cr.add(Restrictions.eq("branch", Integer.parseInt(comAttendance.getDep_Id())));
		cr.add(Restrictions.eq("standard", findStanderdUSingDivision(comAttendance.getDivision())));
		cr.add(Restrictions.eq("year", comAttendance.getYear()));
		cr.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));

		List<ComClientNameDTO> list = new ArrayList<>();
		list = cr.list();

		for (ComClientNameDTO comClientNameDTO : list)
		{
			try
			{
				String no = comClientNameDTO.getRollNo();
				comClientNameDTO.setRollNoInt(Integer.parseInt(no));
			} catch (Exception e)
			{
				// TODO: handle exception
			}

		}

		Collections.sort(list, new ComClientNameDtoChainedCompareter(new ComClientNameDTORollNoComparator()));
		List<AttendanceStudentDTO> attendanceStudentDTOs = new ArrayList<>();
		AttendanceStudentDTO attendanceStudentDTO = null;
		for (ComClientNameDTO comClientNameDTO : list)
		{
			attendanceStudentDTO = new AttendanceStudentDTO();
			attendanceStudentDTO.setFirstName(comClientNameDTO.getFirstName());
			attendanceStudentDTO.setMiddleName(comClientNameDTO.getLastName());
			attendanceStudentDTO.setLastName(comClientNameDTO.getLastName());
			attendanceStudentDTO.setRoll_No(comClientNameDTO.getRollNo());
			attendanceStudentDTO.setId(comClientNameDTO.getId());
			attendanceStudentDTOs.add(attendanceStudentDTO);
		}

		return attendanceStudentDTOs;
	}

	protected List<AttendanceStudentDTO> getStudentListForPractical(ComAttendance comAttendance, String batch)
	{

		Criteria cr = getCriteriaForSelect(Student.class);
		cr.createAlias("comClientName", "comClientName");
		cr.setProjection(Projections.projectionList().add(Property.forName("comClientName.id"), "id")
				.add(Property.forName("comClientName.firstName"), "firstName")
				.add(Property.forName("comClientName.middleName"), "middleName")
				.add(Property.forName("comClientName.lastName"), "lastName").add(Property.forName("rollNo"), "rollNo"));
		cr.add(Restrictions.eq("branch", Integer.parseInt(comAttendance.getDep_Id())));
		cr.add(Restrictions.eq("standard", findStanderdUSingDivision(comAttendance.getDivision())));
		cr.add(Restrictions.eq("year", comAttendance.getYear()));
		cr.add(Restrictions.eq("batch", batch));
		cr.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));

		List<ComClientNameDTO> list = new ArrayList<>();
		list = cr.list();

		for (ComClientNameDTO comClientNameDTO : list)
		{
			try
			{
				String no = comClientNameDTO.getRollNo();
				comClientNameDTO.setRollNoInt(Integer.parseInt(no));
			} catch (Exception e)
			{
				// TODO: handle exception
			}

		}

		Collections.sort(list, new ComClientNameDtoChainedCompareter(new ComClientNameDTORollNoComparator()));
		List<AttendanceStudentDTO> attendanceStudentDTOs = new ArrayList<>();
		AttendanceStudentDTO attendanceStudentDTO = null;
		for (ComClientNameDTO comClientNameDTO : list)
		{
			attendanceStudentDTO = new AttendanceStudentDTO();
			attendanceStudentDTO.setFirstName(comClientNameDTO.getFirstName());
			attendanceStudentDTO.setMiddleName(comClientNameDTO.getLastName());
			attendanceStudentDTO.setLastName(comClientNameDTO.getLastName());
			attendanceStudentDTO.setRoll_No(comClientNameDTO.getRollNo());
			attendanceStudentDTO.setId(comClientNameDTO.getId());
			attendanceStudentDTOs.add(attendanceStudentDTO);
		}

		return attendanceStudentDTOs;
	}

	protected List<ComAttendance> getCumulativeAttendane(ComAttendance comAttendance, Date todate)
	{

		DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy");
		Date date = comAttendance.getClass_date();
		Calendar cal = Calendar.getInstance();
		String[] strDays = new String[]
		{ "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday", "Friday", "Saturday" };
		cal.setTime(date);

		// Set time fields to zero
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		Criteria cr = getCriteriaForSelect(ComAttendance.class);
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(todate);

		// Set time fields to zero
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);

		cal1.add(Calendar.DATE, 1);// greter than one day
		cal.add(Calendar.DATE, -1);// less than one day
		// Put it back in the Date object
		todate = cal1.getTime();
		date = cal.getTime();

		Property dates = Property.forName("class_date");
		cr.add(Restrictions.disjunction().add(Restrictions.and(dates.gt(date), dates.lt(todate))));
		cr.add(Restrictions.eq("Dep_Id", comAttendance.getDep_Id()));
		cr.add(Restrictions.eq("division", comAttendance.getDivision()));
		cr.add(Restrictions.eq("year", comAttendance.getYear()));
		cr.add(Restrictions.eq("sem", comAttendance.getSem()));

		return cr.list();

	}

	protected List<ComPracticalAttendance> getPracticalCumulativeAttendane(ComAttendance comAttendance, Date todate,
			String batch)
	{

		DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy");
		Date date = comAttendance.getClass_date();
		Calendar cal = Calendar.getInstance();
		String[] strDays = new String[]
		{ "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday", "Friday", "Saturday" };
		cal.setTime(date);

		// Set time fields to zero
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		Criteria cr = getCriteriaForSelect(ComPracticalAttendance.class);
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(todate);

		// Set time fields to zero
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);

		cal1.add(Calendar.DATE, 1);// greter than one day
		cal.add(Calendar.DATE, -1);// less than one day
		// Put it back in the Date object
		todate = cal1.getTime();
		date = cal.getTime();

		Property dates = Property.forName("class_date");
		cr.add(Restrictions.disjunction().add(Restrictions.and(dates.gt(date), dates.lt(todate))));
		cr.add(Restrictions.eq("Dep_Id", comAttendance.getDep_Id()));
		cr.add(Restrictions.eq("division", comAttendance.getDivision()));
		cr.add(Restrictions.eq("year", comAttendance.getYear()));
		cr.add(Restrictions.eq("sem", comAttendance.getSem()));
		cr.add(Restrictions.eq("batch_name", batch));

		return cr.list();

	}

	protected AcademicSubject getAcadamicSubject(int id)
	{
		Session session = this.sessionFactory.getCurrentSession();
		AcademicSubject subject = session.get(AcademicSubject.class, id);
		return subject;
	}

	protected boolean attendanceReportDownloadDaily(ComAttendance comAttendance, String batch, int id)
	{
		AttendanceDTOWeb attendanceDTOWeb = null;
		if (id == 2)
		{
			attendanceDTOWeb = getPracticalDailyAttendance(comAttendance, 0, batch);
		} else
		{
			attendanceDTOWeb = getDalilyAttendance(comAttendance, 0);
		}

		try
		{
			if (attendanceDTOWeb.getLectureList().isEmpty())
			{
				return false;
			}
		} catch (NullPointerException e)
		{
			// TODO: handle exception
			return false;
		}

		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletResponse responses = ((ServletRequestAttributes) requestAttributes).getResponse();

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		HSSFRow row = sheet.createRow(0);
		sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
				0, // last row (0-based)
				0, // first column (0-based)
				6 // last column (0-based)
		));
		row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell = row.createCell(0);
		if (id == 2)
		{
			cell.setCellValue(new HSSFRichTextString("Daily Practical Attendance Report"));
		} else
		{
			cell.setCellValue(new HSSFRichTextString("Daily Attendance Report"));
		}

		row = sheet.createRow(1);
		cell = row.createCell(0);

		ComUserDetails user = getUserDetails();
		InstituteInfoMaster info = getInstitutesInfoMasterById(Integer.parseInt(user.getInstituteId()));

		cell.setCellValue(new HSSFRichTextString(info.getName()));
		// Style Font in Cell 2B
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle = wb.createCellStyle();
		HSSFFont hSSFFont = wb.createFont();
		hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
		hSSFFont.setFontHeightInPoints((short) 12);
		hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		hSSFFont.setColor(HSSFColor.GREEN.index);
		cellStyle.setFont(hSSFFont);
		cell.setCellStyle(cellStyle);

		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue("Department:" + attendanceDTOWeb.getDepartment());
		cell = row.createCell(2);
		cell.setCellValue("Academic Year:" + attendanceDTOWeb.getAcadmic_year());
		cell = row.createCell(4);
		cell.setCellValue("SEM:" + attendanceDTOWeb.getSemister());
		cell = row.createCell(4);
		cell.setCellValue("Class:" + attendanceDTOWeb.getClass_name());

		if (id == 2)
		{
			cell = row.createCell(5);
			cell.setCellValue("Batch:" + attendanceDTOWeb.getBatch());

		}
		row = sheet.createRow(3);
		cell = row.createCell(0);
		cell.setCellValue("Date:" + attendanceDTOWeb.getAt_Date());
		cell = row.createCell(2);
		cell.setCellValue("Day:" + attendanceDTOWeb.getDay());

		row = sheet.createRow(5);
		cell = row.createCell(0);
		cell.setCellValue("Roll No.");
		cell = row.createCell(1);
		cell.setCellValue("Name of Student");
		int leccount = 2;
		for (String lec : attendanceDTOWeb.getLectureList())
		{
			cell = row.createCell(leccount);
			cell.setCellValue(lec);
			leccount++;
		}
		cell = row.createCell(leccount);
		cell.setCellValue("Present / Total");
		leccount = 2;
		row = sheet.createRow(6);
		for (String Subject : attendanceDTOWeb.getSubList())
		{
			cell = row.createCell(leccount);
			cell.setCellValue(Subject);
			leccount++;
		}
		int studlist = 8;
		int presentAbsentColum = 1;
		int counter = 0;
		for (AttendanceStudentDTO attendance : attendanceDTOWeb.getStudentList())
		{

			row = sheet.createRow(studlist);
			cell = row.createCell(0);
			cell.setCellValue(attendance.getRoll_No());
			cell = row.createCell(1);
			cell.setCellValue(attendance.getFirstName() + "  " + attendance.getLastName());

			counter = 2;
			int prsentstud = 0;
			for (String attendancestatus : attendance.getStudAttendance())
			{
				cell = row.createCell(counter);
				cell.setCellValue(attendancestatus);
				counter++;
				if (attendancestatus.equals("P"))
				{
					prsentstud++;

				}
			}
			cell = row.createCell(counter);
			cell.setCellValue(prsentstud + " / " + attendance.getStudAttendance().size());
			studlist++;
			presentAbsentColum++;
		}
		cell = row.createCell(counter);
		cell.setCellValue("");
		// write it as an excel attachment
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		try
		{
			wb.write(outByteStream);
			byte[] outArray = outByteStream.toByteArray();
			responses.setContentType("application/ms-excel");
			responses.setContentLength(outArray.length);
			responses.setHeader("Expires:", "0"); // eliminates browser caching
			if (id == 2)
			{
				responses.setHeader("Content-Disposition", "attachment; filename=DailyPracticalAttendance.xls");

			} else
			{
				responses.setHeader("Content-Disposition", "attachment; filename=DailyAttendance.xls");

			}
			OutputStream outStream = responses.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
			outStream.close();

		} catch (Exception e)
		{
			System.out.println("excepotion......" + e);
		}

		return true;
	}

	protected AttendanceDTOWeb getPracticalDailyAttendance(ComAttendance comAttendance, int subjectID, String batch)
	{
		Session session = this.sessionFactory.getCurrentSession();
		ComUserDetails user = getUserDetails();
		DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy");
		Date date = comAttendance.getClass_date();
		Calendar cal = Calendar.getInstance();
		String[] strDays = new String[]
		{ "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday", "Friday", "Saturday" };
		cal.setTime(date);

		// Set time fields to zero
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		// Put it back in the Date object

		Criteria cr = getCriteriaForSelect(ComPracticalAttendance.class);
		date = cal.getTime();
		cr.add(Restrictions.eq("class_date", date));
		if (subjectID != 0)
		{
			Criteria cr11 = getCriteriaForSelect(ComStaffSubject.class);
			cr11.add(Restrictions.eq("academicSubject.sub_id", subjectID));
			cr11.add(Restrictions.eq("clientName.id", user.getComClientName().getId()));
			ComStaffSubject comStaffSubject1 = (ComStaffSubject) cr11.uniqueResult();
			comAttendance.setSem(Integer.parseInt(comStaffSubject1.getAcademicSubject().getSemister()));
			comAttendance.setYear(findYearUsingSem(comStaffSubject1.getAcademicSubject().getSemister()));
			comAttendance
					.setDep_Id(Integer.toString(comStaffSubject1.getAcademicSubject().getDepartment().getDep_id()));

			cr.add(Restrictions.eq("comStaffSubject.staff_sub_id", comStaffSubject1.getStaff_sub_id()));

		}

		cr.add(Restrictions.eq("Dep_Id", comAttendance.getDep_Id()));
		cr.add(Restrictions.eq("division", comAttendance.getDivision()));
		cr.add(Restrictions.eq("year", comAttendance.getYear()));
		cr.add(Restrictions.eq("sem", comAttendance.getSem()));
		cr.add(Restrictions.eq("batch_name", batch));

		List<ComPracticalAttendance> comAttendanceList = cr.list();

		// set attendance data.....................
		AttendanceDTOWeb attendanceDTOWeb = new AttendanceDTOWeb();
		attendanceDTOWeb.setClass_name(getYearName(comAttendance.getYear()));
		attendanceDTOWeb.setAt_Date(dateFormat.format(comAttendance.getClass_date()));
		attendanceDTOWeb.setSemister(Integer.toString(comAttendance.getSem()));
		attendanceDTOWeb.setDay(strDays[cal.get(Calendar.DAY_OF_WEEK) - 1]);
		attendanceDTOWeb.setDivision(comAttendance.getDivision());
		attendanceDTOWeb.setAcadmic_year(cal.get(Calendar.YEAR));
		attendanceDTOWeb.setBatch(batch);
		Department department = session.get(Department.class, Integer.parseInt(comAttendance.getDep_Id()));
		attendanceDTOWeb.setDepartment(department.getDep_name());

		int count = 1;
		List<Integer> presentList = null;
		List<AttendanceStudentDTO> attendanceStudentDTOs = new ArrayList<>();
		if (!comAttendanceList.isEmpty())
		{

			// student List get
			attendanceStudentDTOs = getStudentListForPractical(comAttendance, batch);

			// use for total present student
			AttendanceStudentDTO lastTotalElement = new AttendanceStudentDTO();
			lastTotalElement.setFirstName("Total");
			attendanceStudentDTOs.add(attendanceStudentDTOs.size(), lastTotalElement);

			for (ComPracticalAttendance comPracticalAttendance : comAttendanceList)
			{

				attendanceDTOWeb.getLectureList().add(comPracticalAttendance.getLecturePracticalTime().getStart_time()
						+ "-" + comPracticalAttendance.getLecturePracticalTime().getEnd_time());
				attendanceDTOWeb.getSubList()
						.add(comPracticalAttendance.getComStaffSubject().getAcademicSubject().getSubject_name());
				presentList = new ArrayList();
				// present stud list
				for (int i = 1; i <= 200; i++)
				{
					try
					{
						Class ftClass = comPracticalAttendance.getClass();
						Field f1 = ftClass.getField("roll_" + i);
						if (f1.get(comPracticalAttendance) == null)
						{
							break;
						} else
						{
							presentList.add((Integer) f1.get(comPracticalAttendance));
						}

					} catch (Exception e)
					{
						System.out.println(e);
					}
				}
				for (int i = 0; i < attendanceStudentDTOs.size() - 1; i++)
				{

					if (presentList.contains(attendanceStudentDTOs.get(i).getId()))
					{
						attendanceStudentDTOs.get(i).getStudAttendance().add("P");

					} else
					{
						attendanceStudentDTOs.get(i).getStudAttendance().add("A");

					}

				}

				attendanceStudentDTOs.get(attendanceStudentDTOs.size() - 1).getStudAttendance()
						.add(Integer.toString(presentList.size()));

			}
		} else
		{
			return null;

		}

		attendanceDTOWeb.getStudentList().addAll(attendanceStudentDTOs);
		return attendanceDTOWeb;
	}

	protected Groups saveGroupsObj(Groups group, Integer[] member)
	{

		Object obj = new Object();
		Object grp = new Object();

		for (Integer element : member)
		{
			GroupMembers gm = new GroupMembers();
			ComClientName c = new ComClientName();
			c.setId(element);
			gm.setGroups(group);
			gm.setComClientName(c);
			grp = save(group);
			obj = save(gm);
		}

		if (obj != null)
		{
			if (group.getGroupIncharge_1() != null)
			{
				GroupMembers gm = new GroupMembers();
				ComClientName c = new ComClientName();
				c.setId(group.getGroupIncharge_1());
				gm.setGroups(group);
				gm.setComClientName(c);
				save(group);
				save(gm);
			}
			if (group.getGroupIncharge_2() != group.getGroupIncharge_1())
			{
				GroupMembers gm = new GroupMembers();
				ComClientName c = new ComClientName();
				c.setId(group.getGroupIncharge_2());
				gm.setGroups(group);
				gm.setComClientName(c);
				save(group);
				save(gm);
			}
			return (Groups) grp;
		} else
		{
			return (Groups) grp;
		}

	}

	protected InstituteInfoMaster getInstitutesInfoMasterById(int id)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(InstituteInfoMaster.class);
		criteria.add(Restrictions.eq("instCode", id));
		InstituteInfoMaster instituteInfoMaster = (InstituteInfoMaster) criteria.uniqueResult();
		session.flush();
		return instituteInfoMaster;
	}

	protected boolean checkapplayForDrive(int clientId, int driveId)
	{

		Criteria criteria = getCriteriaForSelect(ApplyedStudentForCompany.class);

		criteria.add(Restrictions.eq("comClientName.id", clientId));
		criteria.add(Restrictions.eq("recruitmentInfo.reInfoId", driveId));
		if (criteria.uniqueResult() == null)
		{
			return true;
		} else
		{
			return false;
		}

	}

	protected Integer deletementeestudent(Integer id)
	{

		return null;
	}

	protected String findivisionusingstandard(String div)
	{
		if (div.equalsIgnoreCase("1"))
		{
			return "A";
		} else if (div.equalsIgnoreCase("2"))
		{
			return "B";
		} else if (div.equalsIgnoreCase("3"))
		{
			return "C";
		} else if (div.equalsIgnoreCase("4"))
		{
			return "D";
		} else if (div.equalsIgnoreCase("5"))
		{
			return "E";
		} else if (div.equalsIgnoreCase("6"))
		{
			return "F";
		} else if (div.equalsIgnoreCase("7"))
		{
			return "G";
		} else if (div.equalsIgnoreCase("8"))
		{
			return "H";
		} else if (div.equalsIgnoreCase("9"))
		{
			return "I";
		} else if (div.equalsIgnoreCase("10"))
		{
			return "j";
		} else if (div.equalsIgnoreCase("11"))
		{
			return "K";
		} else if (div.equalsIgnoreCase("12"))
		{
			return "L";
		} else
		{
			return "null";
		}
	}

}
