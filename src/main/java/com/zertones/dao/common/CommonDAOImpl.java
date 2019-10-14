package com.zertones.dao.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Disjunction;
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
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.zertones.dao.BaseDAOImpl;
import com.zertones.dao.UserDetailsDAO;
import com.zertones.model.ComAttendance;
import com.zertones.model.ComClientAddress;
import com.zertones.model.ComClientName;
import com.zertones.model.ComPracticalAttendance;
import com.zertones.model.ComUser;
import com.zertones.model.ComUserDetails;
import com.zertones.model.DeviceDetails;
import com.zertones.model.EducationDetails;
import com.zertones.model.PlacementYears;
import com.zertones.model.ResulSubjectlist;
import com.zertones.model.ResultAcadyear;
import com.zertones.model.ResultMarksheet;
import com.zertones.model.ResultModelForPlacement;
import com.zertones.model.ResultStudent;
import com.zertones.model.ResultYear;
import com.zertones.model.Resultsemester;
import com.zertones.model.Semester;
import com.zertones.model.StudentForm;
import com.zertones.model.SubjectList;
import com.zertones.model.DataTransferObjectModel.AttendanceDTOWeb;
import com.zertones.model.DataTransferObjectModel.AttendanceStudentDTO;
import com.zertones.model.DataTransferObjectModel.CollegeCountDTO;
import com.zertones.model.DataTransferObjectModel.CollegeTotalFee;
import com.zertones.model.DataTransferObjectModel.ComClientNameDTO;
import com.zertones.model.DataTransferObjectModel.DepartmentDTO;
import com.zertones.model.DataTransferObjectModel.DirectoryDTO;
import com.zertones.model.DataTransferObjectModel.ExtraActivityDTO;
import com.zertones.model.DataTransferObjectModel.GfmInformation;
import com.zertones.model.DataTransferObjectModel.GroupDTO;
import com.zertones.model.DataTransferObjectModel.MonthAndAttendanceDto;
import com.zertones.model.DataTransferObjectModel.Navbar_MenuList;
import com.zertones.model.DataTransferObjectModel.NewUserDTO;
import com.zertones.model.DataTransferObjectModel.OnCampusPlaceStudListDTO;
import com.zertones.model.DataTransferObjectModel.PlacementDriveStatus;
import com.zertones.model.DataTransferObjectModel.PortFolioInfo;
import com.zertones.model.DataTransferObjectModel.RecruitmentInfoDTO;
import com.zertones.model.DataTransferObjectModel.ResultInstituteList;
import com.zertones.model.DataTransferObjectModel.StaffInfoDto;
import com.zertones.model.DataTransferObjectModel.StudentAttendanceDto;
import com.zertones.model.DataTransferObjectModel.Studentskillsdto;
import com.zertones.model.DataTransferObjectModel.SubjectResultDataModel;
import com.zertones.model.DataTransferObjectModel.SubjectsDTO;
import com.zertones.model.DataTransferObjectModel.TimeTableDTO;
import com.zertones.model.DataTransferObjectModel.emailData;
import com.zertones.model.common.AdminForm;
import com.zertones.model.common.ApplyedStudentForCompany;
import com.zertones.model.common.AssignedCoordinators;
import com.zertones.model.common.AttendancePunch;
import com.zertones.model.common.AwardsAndAchievementModel;
import com.zertones.model.common.BatchDetails;
import com.zertones.model.common.BatchList;
import com.zertones.model.common.BatchTable;
import com.zertones.model.common.ComVideoURL;
import com.zertones.model.common.CompanyRepresentative;
import com.zertones.model.common.CompanySelectionRounds;
import com.zertones.model.common.ContactUsModel;
import com.zertones.model.common.Days;
import com.zertones.model.common.Department;
import com.zertones.model.common.Depatmentlistofdrive;
import com.zertones.model.common.ExamSchedule;
import com.zertones.model.common.ExtraActivities;
import com.zertones.model.common.FeeDetails;
import com.zertones.model.common.FeedbackQue;
import com.zertones.model.common.GroupMembers;
import com.zertones.model.common.Groups;
import com.zertones.model.common.Help;
import com.zertones.model.common.Html_menu;
import com.zertones.model.common.InstituteInfoDetails;
import com.zertones.model.common.InstituteInfoMaster;
import com.zertones.model.common.LecturePracticalTime;
import com.zertones.model.common.LectureTheoryTime;
import com.zertones.model.common.LifeOfCampus;
import com.zertones.model.common.MenuList;
import com.zertones.model.common.Notification;
import com.zertones.model.common.OffcampusPlaceStud;
import com.zertones.model.common.Parentcallrecord;
import com.zertones.model.common.RecruitmentInfo;
import com.zertones.model.common.StudentFeeDtl;
import com.zertones.model.common.TimeTable;
import com.zertones.model.common.TotalStudentCount;
import com.zertones.model.common.TypeOfIndustry;
import com.zertones.model.common.UserToken;
import com.zertones.model.master.AcademicSemester;
import com.zertones.model.master.AcademicSubject;
import com.zertones.model.master.AcademicYear;
import com.zertones.model.master.CollegeMenuList;
import com.zertones.model.master.ComListMaster;
import com.zertones.model.master.ComStaffSubject;
import com.zertones.model.master.CourseMaster;
import com.zertones.model.master.MarkSheet;
import com.zertones.model.master.MianManuList;
import com.zertones.model.master.ResultFile;
import com.zertones.model.master.ValueAddedProgram;
import com.zertones.model.sims.Mentor;
import com.zertones.model.sims.MentorStudent;
import com.zertones.model.sims.Parent;
import com.zertones.model.sims.RemarkOption;
import com.zertones.model.sims.Remarks;
import com.zertones.model.sims.Staff;
import com.zertones.model.sims.Student;
import com.zertones.model.sims.StudentRemark;
import com.zertones.model.sims.StudentRemarkOption;
import com.zertones.security.model.UserRole;
import com.zertones.service.Constants;
import com.zertones.system.utility.ComClientNameDTORollNoComparator;
import com.zertones.system.utility.ComClientNameDtoChainedCompareter;

@Repository
@Transactional
public class CommonDAOImpl extends BaseDAOImpl implements CommonDAO
{
	private static final Logger	logger	= LoggerFactory.getLogger(CommonDAOImpl.class);

	@Autowired
	protected SessionFactory	sessionFactory;

	@Autowired
	protected UserDetailsDAO	userDao;

	@Autowired
	private AttendancePunch		attendancePunch;

	@Override
	public void addInstituteInfoMaster(InstituteInfoMaster instituteInfoMaster)
	{
		save(instituteInfoMaster);
		logger.info("InstituteInfoMaster saved successfully, Notification Details = " + instituteInfoMaster);
	}

	@Override
	public void updateInstituteInfoMatser(InstituteInfoMaster instituteInfoMaster)
	{
		save(instituteInfoMaster);
		logger.info("InstituteInfoMaster updated successfully, Notification Details = " + instituteInfoMaster);

	}

	@Override
	public InstituteInfoMaster getInstituteInfoMasterById(Integer id)
	{
		/*
		 * Session session = this.sessionFactory.getCurrentSession();
		 * InstituteInfoMaster instituteInfoMaster = (InstituteInfoMaster)
		 * get(InstituteInfoMaster.class, id); logger.
		 * info("InstituteInfoMatster loaded successfully, InstituteInfoMatster " );
		 * session.flush(); session.close(); return instituteInfoMaster;
		 */
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(InstituteInfoMaster.class);
		criteria.add(Restrictions.eq("instCode", id));
		InstituteInfoMaster instituteInfoMaster = (InstituteInfoMaster) criteria.uniqueResult();

		logger.info("InstituteInfoMatster loaded successfully, InstituteInfoMatster ");
		session.flush();

		return instituteInfoMaster;
	}

	@Override
	public List<InstituteInfoMaster> getInstituteInfoMatserList()
	{
		logger.info("Listing Institute Master");
		List<InstituteInfoMaster> instituteInfoMaster = get("select info from InstituteInfoMaster info ");// query.list();
		return instituteInfoMaster;
	}

	@Override
	public void addInstituteInfoDetails(InstituteInfoDetails instituteInfoDetails)
	{
		save(instituteInfoDetails);
		logger.info("Institute Info details saved successfully, Institute Info details = " + instituteInfoDetails);
	}

	@Override
	public void updateInstituteInfoDetails(InstituteInfoDetails instituteInfoDetails)
	{

	}

	@Override
	public List<InstituteInfoDetails> getInstituteInfoDetailsList(Integer instituteInfoMasterId)
	{
		logger.info("Get List of Institute Information Master");
		List<InstituteInfoDetails> instituteInfoDetails = getInfo(
				"select masterDtl from InstituteInfoDetails masterDtl where instituteInfotMaster.listId = "
						+ instituteInfoMasterId);
		return instituteInfoDetails;
	}

	@Override

	public List<MenuList> getMenuList()
	{
		logger.info("Listing Menu");
		Criteria criteria = getCriteriaForSelect(MenuList.class);
		List<MenuList> menuList = criteria.list();
		menuList.sort(Comparator.comparing(MenuList::getParentId, Comparator.nullsLast(Comparator.naturalOrder())));
		menuList.sort((p1, p2) -> p1.getMenuId().compareTo(p2.getMenuId()));
		return menuList;
	}

	@Override
	public InstituteInfoDetails getMasterDtlById(Integer id)
	{
		InstituteInfoDetails instituteInfoDetails = (InstituteInfoDetails) get(InstituteInfoDetails.class, id);
		// session.get(InstituteInfoDetails.class,
		/*
		 * Hibernate.initialize(instituteInfoDetails.getInstituteInfotMaster());
		 * instituteInfoDetails.setInstituteInfotMaster(instituteInfoDetails.
		 * getInstituteInfotMaster());
		 */ logger.debug("InstituteInfoDetails loaded successfully, InstituteInfoDetails details.");
		return instituteInfoDetails;
	}

	@Override
	public ComListMaster getCommonMasterList(Integer listId)
	{
		logger.info("Getting master list");
		ComListMaster commonMaster = (ComListMaster) get(ComListMaster.class, listId);
		return commonMaster;
	}

	@Override
	public List<ComUserDetails> searchUsser(String userName)
	{

		logger.info("Searching is user exists or not");
		Criteria criteria = getCriteria(ComUserDetails.class);
		criteria.add(Restrictions.eq("userName", userName));
		List<ComUserDetails> users = criteria.list();
		return users;
	}

	@Override
	public TimeTable addTimeTable(TimeTable timeTable)
	{
		logger.info("Adding Time Table : ");
		save(timeTable);
		logger.info("Added Time Table Successfully");
		return timeTable;
	}

	@Override
	public ExamSchedule saveExamSchedule(ExamSchedule examSchedule)
	{
		logger.info("adding exam schedule : ");
		save(examSchedule);
		logger.info("added exam schedule Successfully : ");
		return examSchedule;
	}

	@Override
	public CourseMaster saveCourseMaster(CourseMaster courseMaster)
	{
		logger.info("adding course details : ");
		save(courseMaster);
		logger.info("added course details successfully : ");
		return courseMaster;
	}

	@Override
	public AcademicYear saveAcademicYear(AcademicYear academicYear)
	{
		logger.info("adding academic year : ");
		Session session = this.sessionFactory.getCurrentSession();
		session.save(academicYear);
		logger.info("added academic year successfully : ");
		return academicYear;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Days> getTimeTable(Integer yearid)
	{
		logger.info("fetching time table");
		Criteria cr = getCriteriaForSelect(Days.class);
		cr.setProjection(Projections.projectionList().add(Property.forName("day_vid"), "day_vid")
				.add(Property.forName("day_name"), "day_name"));
		cr.setResultTransformer(Transformers.aliasToBean(Days.class));
		List<Days> days = cr.list();
		for (Days days2 : days)
		{
			days2.setTimeTableDTO(getTimeTableDTO(days2.getDay_vid()));
		}
		return days;
	}

	public List<TimeTableDTO> getTimeTableDTO(Integer day_vid)
	{
		Criteria cr = getCriteriaForSelect(TimeTable.class);
		cr.createAlias("department", "department");
		// cr.createAlias("academicYear", "academicYear");
		cr.createAlias("academicSubject", "academicSubject");
		cr.createAlias("comClientName", "comClientName");
		cr.createAlias("days", "days");
		cr.createAlias("lectures", "lectures");
		cr.setProjection(Projections.projectionList().add(Property.forName("department.dep_name"), "dep_name")
				// .add(Property.forName("academicYear.yearStartDate"),
				// "yearStartDate")
				// .add(Property.forName("academicYear.yearEndDate"),
				// "yearEndDate")
				.add(Property.forName("academicSubject.subject_name"), "subject_name")
				.add(Property.forName("comClientName.firstName"), "firstName")
				.add(Property.forName("comClientName.middleName"), "middleName")
				.add(Property.forName("comClientName.lastName"), "lastName")
				.add(Property.forName("days.day_vid"), "day_VID").add(Property.forName("division"), "division")
				.add(Property.forName("TheoryorPractical"), "theoryorPractical")
				.add(Property.forName("batch_name"), "batch_name")
				.add(Property.forName("lectures.start_time"), "periodStartTime")
				.add(Property.forName("lectures.end_time"), "periodEndTime"));
		cr.add(Restrictions.eq("days.day_vid", day_vid));
		cr.setResultTransformer(Transformers.aliasToBean(TimeTableDTO.class));
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamSchedule> getExamSchedule()
	{
		Criteria criteria = getCriteriaForSelect(ExamSchedule.class);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseMaster> getCourseMaster()
	{
		logger.info("fetching course master");

		return get("from CourseMaster");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AcademicYear> getAcademicYear()
	{
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(AcademicYear.class);
		return criteria.list();
	}

	@Override

	public AcademicSemester getYearSemester(Integer id)
	{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(AcademicSemester.class);
		criteria.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		criteria.add(Restrictions.eq("semesterId", id));
		AcademicSemester academicSemester = (AcademicSemester) criteria.uniqueResult();
		return academicSemester;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComListMaster> getCommonMasterList()
	{
		logger.info("fetching common list master");
		return getCriteriaForSelect(ComListMaster.class).list();
	}

	@Override

	public List<Staff> getTeacherProfile()
	{
		logger.info("fetching common Teacher Profile");
		@SuppressWarnings("unchecked")
		List<Staff> list = getList("From Staff");
		return list;
	}

	@Override

	public Staff getTeacherProfileById(Integer clientId)
	{
		/*
		 * logger.info("fetching common Teacher Profile By Id");
		 *
		 * @SuppressWarnings("unchecked") List<Object> list = getListById(
		 * "From ComTeacherName", clientId); return list;
		 */
		logger.info("Getting master list");
		Staff staff = (Staff) get(Staff.class, clientId);
		return staff;
	}

	@Override

	public Staff userLoginCheck(String name)
	{
		logger.info("Check User Credential" + name);
		Staff comClient = getUserAuthentication(name);
		return comClient;
	}

	@Override

	public ComClientName adduser(ComClientName comClientName)
	{

		save(comClientName);

		return comClientName;
	}

	@Override

	public List<ComClientName> getUserList()
	{
		List<ComClientName> list = getList("from ComClientName");
		return list;
	}

	@Override
	public ComClientName getUserDtlById(Integer userId)
	{
		logger.info("Getting User Details");
		ComClientName comClientName = (ComClientName) get(ComClientName.class, userId);
		return comClientName;
	}

	@Override

	public ComClientName removeUser(Integer userId)
	{
		Session session = this.sessionFactory.getCurrentSession();
		ComClientName comClientName = session.get(ComClientName.class, new Integer(userId));
		if (null != comClientName)
		{
			session.delete(comClientName);
		}
		session.flush();
		session.close();
		return comClientName;
	}

	@Override

	public List<ComClientName> getTeacherList(String query)
	{
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria c = session.createCriteria(ComClientName.class, "comClientNameAlies");
		c.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		c.add(Restrictions.like("firstName", query));
		List<ComClientName> teacher_List = c.list();
		session.flush();
		session.close();
		return teacher_List;
	}

	@Override

	public ComClientName chklogin(DeviceDetails deviceDetails)
	{
		logger.info("Check User Credential");
		return getUserAuthenticationOfMobileUser(deviceDetails);

	}

	@Override

	public List<DepartmentDTO> getDepartmentList()
	{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Department.class);
		criteria.setProjection(Projections.projectionList().add(Property.forName("dep_id"), "dep_id")
				.add(Property.forName("dep_name"), "dep_name"));
		criteria.setResultTransformer(Transformers.aliasToBean(DepartmentDTO.class));
		return criteria.list();

	}

	@Override

	public List<emailData> getEmailIds()
	{

		Criteria cr = getCriteriaForSelect(ComUserDetails.class);
		cr.setProjection(Projections.projectionList().add(Property.forName("userName"), "emailId"));
		cr.setResultTransformer(Transformers.aliasToBean(emailData.class));
		return cr.list();
	}

	@Override
	public List<DepartmentDTO> getDepartmentList(Integer cid)
	{

		return getDepartmentListByClientId(cid);
	}

	@Override

	public List<Staff> getStaffById(Integer id)
	{

		List<Staff> detail = getUserList(id);
		return detail;
	}

	@Override

	public Groups saveGroups(Groups group, Integer[] member)
	{
		return saveGroupsObj(group, member);
	}

	@Override
	public List<GroupDTO> getListGroup(Integer cid)
	{
		Criteria c = getCriteriaForSelect(GroupMembers.class);
		c.createAlias("groups", "groups");
		c.setProjection(Projections.projectionList().add(Property.forName("groups.groupId"), "groupId")
				.add(Property.forName("groups.groupName"), "groupName")
				.add(Property.forName("groups.groupType"), "groupType")
				.add(Property.forName("groups.groupfor"), "groupfor"));
		c.add(Restrictions.eq("comClientName.id", cid));
		c.setResultTransformer(Transformers.aliasToBean(GroupDTO.class));
		List<GroupDTO> l = c.list();
		for (GroupDTO groupDTO : l)
		{
			groupDTO.setMembers(getListById("from GroupMembers", groupDTO.getGroupId(), "groups").size());
		}
		return l;

	}

	@Override

	public List<Groups> getListGroupByDepartment(Integer depId)
	{
		return getListById("from Groups", depId, "department");
	}

	@Override

	public List<GroupDTO> getGroupDetails(Integer groupId)
	{
		Criteria c = getCriteriaForSelect(Groups.class);
		c.setProjection(Projections.projectionList().add(Property.forName("groupId"), "groupId")
				.add(Property.forName("gfm_id"), "gfm_id").add(Property.forName("groupName"), "groupName")
				.add(Property.forName("groupType"), "groupType").add(Property.forName("groupfor"), "groupfor")
				.add(Property.forName("groupIncharge_2"), "groupIncharge_2"));
		// c.add(Restrictions.eq(Constants.RECORD_STATUS,
		// Constants.ACTIVE_RECORD_STATUS));
		c.add(Restrictions.eq("groupId", groupId));
		c.setResultTransformer(Transformers.aliasToBean(GroupDTO.class));
		List<GroupDTO> l = c.list();
		for (GroupDTO groupDTO : l)
		{
			groupDTO.setGroupMembers(getGroupMemberByGroupId(groupDTO.getGroupId()));
		}
		return l;
		// return getListById("from Groups", groupId, "groupId");
	}

	@Override
	public boolean updateGroups(Groups group, Integer[] member)
	{
		System.out.println("update groupssss");
		System.out.println("groupsss" + group.getGroupId());

		System.out.println("..........");

		Criteria cr = getCriteriaForSelect(Groups.class);
		cr.add(Restrictions.eq("groupId", group.getGroupId()));
		Groups grp = (Groups) cr.uniqueResult();

		System.out.println("gruppppppppppp.........." + grp.getGroupId());
		System.out.println("gruppppppppppp.........." + grp.getGroupName());

		System.out.println("mentorr............/////...............");
		if (grp.getGfm_id() != null)
		{
			System.out.println("come in if condition");
			MentorStudent s = null;
			for (Integer integer : member)
			{
				System.out.println("come in mentor loop");

				Criteria cr1 = getCriteriaForSelect(Mentor.class);
				cr1.add(Restrictions.eq("id", grp.getGfm_id()));
				Mentor mentee = (Mentor) cr1.uniqueResult();
				System.out.println("menttttttttttttttttttt" + mentee);

				Criteria criteria = getCriteria(Student.class);
				criteria.add(Restrictions.eq("comClientName.id", integer));
				Student student = (Student) criteria.uniqueResult();

				Criteria criteria1 = getCriteria(MentorStudent.class);
				criteria1.add(Restrictions.eq("mentor.id", mentee.getId()));
				criteria1.add(Restrictions.eq("student.studentId", student.getStudentId()));

				if (criteria1.uniqueResult() == null)
				{
					s = new MentorStudent();
					s.setMentor(mentee);
					s.setStudent(student);
					System.out.println("add student group table  to mentor table ");
					save(s);

				}

			}
		}

		Object obj = new Object();
		for (Integer element : member)
		{
			System.out.println("come in groups");
			GroupMembers gm = new GroupMembers();
			ComClientName c = new ComClientName();
			c.setId(element);
			gm.setGroups(group);
			gm.setComClientName(c);
			obj = save(gm);
		}
		if (obj != null)
		{
			return true;
		} else
		{
			return false;
		}

	}

	@Override

	public Boolean updateGroupsInfo(Groups group)
	{
		System.out.println("Group Update :" + group);
		Boolean reslt = false;
		Groups gp = (Groups) getObjectById("from Groups", group.getGroupId(), "groupId");
		// Session session = this.sessionFactory.getCurrentSession();
		// Criteria c = session.createCriteria(GroupMembers.class,
		// "groupMember");
		// Criteria c = getCriteriaForSelect(GroupMembers.class);
		// c.createAlias("comClientName", "comClientName");
		// c.createAlias("groups", "groups");
		// c.add(Restrictions.eq("comClientName.id", gp.getGroupIncharge_1()));
		// c.add(Restrictions.eq("groups.groupId", gp.getGroupId()));
		// GroupMembers groupMember = new GroupMembers();
		// if (gp.getGroupIncharge_1() != null)
		// {
		// groupMember = (GroupMembers) c.uniqueResult();
		// ComClientName clientname = new ComClientName();
		// clientname.setId(group.getGroupIncharge_1());
		// groupMember.setComClientName(clientname);
		// gp.setGroupIncharge_1(group.getGroupIncharge_1());
		// }
		gp.setGroupName(group.getGroupName());

		Object obj = update(gp);
		if (obj != null)
		{
			// if (gp.getGroupIncharge_1() != null)
			// {
			// Object obj1 = update(groupMember);
			// if (obj1 != null)
			// {
			// reslt = true;
			// }
			// }
			reslt = true;
		}
		return reslt;
	}

	@Override

	public boolean removeGroup(Integer id)
	{

		Integer result = remove(id);
		if (result == 1)
		{
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public Boolean removeGroupMember(Groups group, Integer[] member)
	{
		int i = 0;
		for (Integer element : member)
		{
			i = removeGroupMember(element);
		}
		if (i == 1)
		{
			return true;
		} else
		{
			return false;
		}
	}

	@Override

	public ComClientName findUserByEmailAdd(String emailAddress)
	{
		return findUserByEmailAddress(emailAddress);
	}

	@Override

	public UserToken saveUserVerificationToken(UserToken userToken)
	{
		return (UserToken) saveObject(userToken);
	}

	@Override

	public UserToken getUserByToken(String authToken)
	{
		UserToken user = (UserToken) getUser("from UserToken", "verificationToken", authToken);
		if (user.getVerificatioAttempt() == 0)
		{
			user.setVerificatioAttempt(1);
			user.setRecordStatus(0);
			updateUserToken(user);
		} else
		{
			user.setTokenId(0);
		}
		return user;
	}

	@Override

	public boolean updateUserEntity(ComUser comUser)
	{
		ComClientName clientName = (ComClientName) getUser("from ComClientName", "id", comUser.getId());
		boolean result = false;
		// This is From Web Call
		if (comUser.getOldCredential() != null)
		{
			if (userDao.enCoder256(comUser.getOldCredential()).equals(clientName.getComUserDetails().getPassword()))
			{
				// clientName.getComUserDetails().setPassword(comUser.getNewCredential());
				clientName.getComUserDetails().setPassword(userDao.enCoder256(comUser.getNewCredential()));
				if (save(clientName) != null)
				{
					result = true;
				}
			}
		} else
		{
			// Email Verification URL Call Comes Here
			UserToken user = (UserToken) getUser("from UserToken", "comClientName", clientName.getId());
			// clientName.getComUserDetails().setPassword(comUser.getNewCredential());
			clientName.getComUserDetails().setPassword(userDao.enCoder256(comUser.getNewCredential()));

			if (updateUserToken(clientName) != null)
			{

				if (user.getVerificatioAttempt() == 1) // Checking User
														// Verification Attempt
				{
					user.setRecordStatus(1);
					updateUserToken(user);
					result = true;
				}
			}
		}
		return result;
	}

	@Override

	public List<GroupMembers> getGroupListByStdId(Integer id)
	{
		return getListOfGroupByUser(id);
	}

	@Override

	public Boolean saveAttendance(ComAttendance attendance)
	{
		Boolean b = false;
		Object obj = save(attendance);

		if (obj != null)
		{
			b = true;
		}
		return b;
	}

	@Override

	public List<GroupDTO> getGroupListByType(Integer type, Integer cid)
	{
		Criteria c = getCriteriaForSelect(GroupMembers.class);
		c.createAlias("groups", "groups");
		c.setProjection(Projections.projectionList().add(Property.forName("groups.groupId"), "groupId")
				.add(Property.forName("groups.groupName"), "groupName")
				.add(Property.forName("groups.groupType"), "groupType"));
		c.add(Restrictions.eq("comClientName.id", cid));
		c.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		c.add(Restrictions.eq("groups.groupType", type));
		c.setResultTransformer(Transformers.aliasToBean(GroupDTO.class));
		List<GroupDTO> l = c.list();
		for (GroupDTO groupDTO : l)
		{
			groupDTO.setGroupMembers(getGroupMemberByGroupId(groupDTO.getGroupId()));
		}
		return l;
	}

	@Override

	public List<ComAttendance> getAttendenacBySub(Integer subCode)
	{
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(ComAttendance.class);
		cr.setProjection(Projections.avg("total"));
		cr.add(Restrictions.eq("comStaffSubject.staff_sub_id", subCode));
		List<ComAttendance> attendances = cr.list();
		return attendances;
	}

	@Override

	public List<ComStaffSubject> getSubjectByStaff(Integer staffempid)
	{

		return getListById("from ComStaffSubject", staffempid, "clientName.id");
	}

	@Override

	public double getMonthlyAttenedance(LocalDate date, Integer staffSub_id)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(ComAttendance.class);
		c.add(Restrictions.eq("comStaffSubject.staff_sub_id", staffSub_id));
		c.add(Restrictions.between("class_date", java.sql.Date.valueOf(date),
				java.sql.Date.valueOf(date.getYear() + "-" + date.getMonthValue() + "-" + date.lengthOfMonth())));
		// c.setProjection(Projections.avg("total"));

		double monthAttendance = 0.0;
		if (c.uniqueResult() != null)
		{
			monthAttendance = (double) c.uniqueResult();
			// System.out.println("Attendance :" + monthAttendance);
		}
		return monthAttendance;
	}

	public Student getStudent(String prnNo)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Student.class);
		c.add(Restrictions.eq("universityEnrollNo", prnNo));
		return (Student) c.uniqueResult();
	}

	public AcademicSubject getSubject(String subCode)
	{

		Session session = this.sessionFactory.getCurrentSession();
		Criteria sub = session.createCriteria(AcademicSubject.class);
		sub.add(Restrictions.eq("subject_code", subCode));

		return (AcademicSubject) sub.uniqueResult();

		/*
		 * Session session = this.sessionFactory.getCurrentSession(); Criteria cr =
		 * session.createCriteria(AcademicSubject.class);
		 * cr.add(Restrictions.eq("subject_code", subCode)); return (AcademicSubject)
		 * cr.uniqueResult();
		 */
	}

	public MarkSheet getMarkSheet(AcademicSubject sub, Student s)
	{

		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(MarkSheet.class);
		cr.add(Restrictions.eq("student.studentId", s.getStudentId()));
		cr.add(Restrictions.eq("subject.sub_id", sub.getSub_id()));
		return (MarkSheet) cr.uniqueResult();

	}

	@Override

	public boolean saveMarkSheetDao(MarkSheet sheet)
	{

		System.out.println("printsheet" + sheet);
		MarkSheet markSheet1 = null;
		System.out.println("prnno1" + sheet.getPrn_no());
		System.out.println("subcode" + sheet.getSubCode());
		// System.out.println("branchcode" + sheet.getBranch());
		Boolean b = false;

		try
		{
			markSheet1 = new MarkSheet();
			markSheet1 = sheet;

			Session session = this.sessionFactory.getCurrentSession();

			AcademicSubject acSub = getSubject(markSheet1.getSubCode());
			System.out.println("rrrr1" + acSub);

			Student s = getStudent(markSheet1.getPrn_no());
			System.out.println("rrrr2" + s);

			// AcademicSubject acSub1 = session.get(AcademicSubject.class, 1);
			// System.out.println("acdemicrohan" + acSub1);
			// AcademicSubject acad2 =
			// getAcademicSubject(Integer.parseInt(markSheet1.getSubCode()));
			// System.out.println("sonoooooo" + acad2);
			// System.out.println("enrollNo:" + s.getUniversityEnrollNo());

			/*
			 * Query query1 = session.createQuery("FROM AcademicSubject where sub_id =?");
			 * AcademicSubject acSub2 = (AcademicSubject) query1.setParameter(0,
			 * 1).uniqueResult(); System.out.println("testsubcode" + acSub1);
			 */
			/*
			 * Query query2 = session.createQuery("FROM student where studentId=?"); Student
			 * stud = (Student) query2.setParameter(0, 1).uniqueResult();
			 * System.out.println("enrollment" + stud);
			 */

			/*
			 * AcademicSubject Academic = new AcademicSubject(); String acSub =
			 * Academic.getSubject_code();
			 */

			// AcademicSubject acsub = session.get(AcademicSubject.class, new
			// Integer(1));
			// System.out.println("sumitbhau" + acsub);

			// System.out.println("Subject_code():" + acSub);
			// System.out.println("markSheet1.getPrn_no():" +
			// markSheet1.getPrn_no());

			// System.out.println("enrollNo:" + s.getUniversityEnrollNo());

			if (acSub != null && s != null)
			{
				MarkSheet m = getMarkSheet(acSub, s);

				if (m != null)
				{
					sheet.setSeq_no(m.getSeq_no());
				}
			}

			sheet.setSubject(acSub);
			sheet.setStudent(s);
			save(sheet);
			// session.save(markSheet1);
			// save(markSheet1);

			if (save(markSheet1) != null)
			{
				b = true;
			}
			sheet = null;
		} catch (

		Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		markSheet1 = null;
		return b;
	}

	@Override

	public List<LifeOfCampus> getlifeOfCampuse()
	{
		return get("From LifeOfCampus");
	}

	@Override
	public List<AwardsAndAchievementModel> getAwardsAndAchievements()
	{
		List<AwardsAndAchievementModel> list = new ArrayList<>();
		List<Notification> notification = getListById("From Notification", 2, "notificationType");
		for (Notification notics : notification)
		{
			AwardsAndAchievementModel awardAndAchivement = new AwardsAndAchievementModel();
			awardAndAchivement.setHeader(notics.getNotificatiosHeadline());
			awardAndAchivement.setNews(notics.getNotificationDetails());
			list.add(awardAndAchivement);
		}
		return list;
	}

	@Override

	public ComListMaster getAboutUsModelDao()
	{

		return (ComListMaster) getCriteriaForSelect(ComListMaster.class).uniqueResult();
	}

	@Override

	public List<DirectoryDTO> getDirectoryList()
	{
		// Session session = this.sessionFactory.getCurrentSession();
		// Query directory = session.createQuery(
		// "select
		// s.comClientName.firstName,s.comClientName.lastName,s.comClientName.emailId,s.designation,s.department
		// from Staff s ");
		// List<Object[]> employees = directory.list();
		// List<Directory> direcctorylist = new ArrayList<>();
		// for (Object[] employee : employees)
		// {
		// Directory dir = new Directory();
		// try
		// {
		// dir.setName((String) employee[0] + " " + (String) employee[1]);
		// dir.setContact((String) employee[2]);
		// dir.setDesignation((String) employee[3]);
		// dir.setDepartment((Integer) employee[4]);
		// } catch (Exception e)
		// {
		// e.getMessage();
		// }
		// direcctorylist.add(dir);
		// }
		// // session.flush();
		// // session.close();
		// return direcctorylist;
		List<DirectoryDTO> directoryDTOs = new ArrayList<>();
		Criteria cr = getCriteriaForSelect(Department.class);
		List<Department> departments = cr.list();
		for (Department department : departments)
		{
			if (department.getDep_id() != 0)
			{
				DirectoryDTO directoryDTO = getStaffInfoForDirectory(department);
				if (!directoryDTO.getStaffinfo().isEmpty())
				{
					directoryDTOs.add(directoryDTO);
				}
			}
		}
		// System.out.println(directoryDTOs);
		return directoryDTOs;
	}

	public DirectoryDTO getStaffInfoForDirectory(Department department)
	{
		DirectoryDTO directoryDTO = new DirectoryDTO();
		;
		Criteria cr = getCriteriaForSelect(Staff.class);
		cr.createAlias("comClientName", "comClientName");
		cr.setProjection(Projections.projectionList().add(Property.forName("comClientName.firstName"), "firstName")
				.add(Property.forName("comClientName.lastName"), "lastName")
				.add(Property.forName("comClientName.emailId"), "contact")
				.add(Property.forName("designation"), "designation"));
		cr.add(Restrictions.eq("department", department.getDep_id()));
		cr.setResultTransformer(Transformers.aliasToBean(StaffInfoDto.class));
		List<StaffInfoDto> dtos = cr.list();
		if (!dtos.isEmpty() || dtos.size() != 0)
		{
			directoryDTO.setDepName(department.getDep_name());
			directoryDTO.setStaffinfo(dtos);
		}
		return directoryDTO;
	}

	@Override

	public List<ResultFile> getResultFileList()
	{
		return get("from ResultFile");
	}

	@Override

	public boolean addContactUs(ContactUsModel contactUsModel)
	{
		if (save(contactUsModel) != null)
		{
			return true;
		} else
		{
			return false;
		}
	}

	public List<AcademicSubject> getAcademicSubBySem(Integer sem, Integer dep_id)
	{
		// logger.info("Sem" + sem + "ANd Dept_ID" + dep_id);
		System.out.println("Sem" + sem + "ANd Dept_ID" + dep_id);
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(AcademicSubject.class);
		cr.add(Restrictions.eq("semester", sem)); // confusion semester year
		cr.add(Restrictions.eq("dep_id", dep_id));
		return cr.list();
	}

	public Object getTotalStudentAttendTheExam(AcademicSubject subject, Integer exam_yr, Integer sem_id)
	{
		Criteria cr = getCriteriaForSelect(ResultMarksheet.class);
		cr.setProjection(Projections.projectionList().add(Projections.rowCount()));
		cr.add(Restrictions.eq("year", exam_yr));
		cr.add(Restrictions.in("subresult", subject));
		return cr.uniqueResult();

	}

	@Override

	public List<Object[]> getSummaryResult(Integer exam_yr, Integer sem, Integer depid, Integer subid)
	{
		// System.out.println("exam_yr :" + exam_yr + " sem id :" + sem);
		Session session = this.sessionFactory.getCurrentSession();
		Department d = session.get(Department.class, new Integer(depid));
		Criteria cr = session.createCriteria(ResultMarksheet.class);
		cr.setProjection(
				Projections.projectionList().add(Projections.groupProperty("seat_no")).add(Projections.rowCount()));
		cr.add(Restrictions.eq("grade", "F"));
		cr.add(Restrictions.eq("year", exam_yr));
		cr.add(Restrictions.eq("semister", sem));
		// cr.add(Restrictions.eq("academiyr", d.getDep_name().trim()));
		System.out.println("come in academic subjectssssss");

		if (subid != 0)
		{
			cr.add(Restrictions.in("subresult", getListById("From AcademicSubject", subid, "sub_id")));
		}

		System.out.println("no count.................." + cr.list().toString());
		return cr.list();// noOfFails.list();
	}

	@Override
	public List<Object[]> getAllStudentListFromMrksheet(Integer depid, Integer sem, Integer subid)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Department d = session.get(Department.class, new Integer(depid));
		Criteria cr = getCriteriaForSelect(ResultMarksheet.class);
		cr.setProjection(
				Projections.projectionList().add(Projections.rowCount()).add(Projections.groupProperty("seat_no")));
		// cr.add(Restrictions.eq("academiyr", d.getDep_name().trim()));
		cr.add(Restrictions.eq("semister", sem));
		if (subid != 0)
		{
			cr.add(Restrictions.in("subresult", getListById("From AcademicSubject", subid, "sub_id")));
		}
		System.out.println("alllis from marksheet" + cr.list().toString());
		return cr.list();
	}

	@Override

	public List<Object[]> getSubjectRsultPersentage(Integer sem_id, Integer dep_id, Integer exam_yr, Integer subid)
	{
		int count = 0;
		System.out.println("subjectttttttttttttttttt" + "semester" + sem_id + "dept" + dep_id + "examyear" + exam_yr
				+ "examyear" + subid);
		List<Object[]> test = new ArrayList<>();
		Session session = this.sessionFactory.getCurrentSession();
		Department d = session.get(Department.class, new Integer(dep_id));

		List<AcademicSubject> sub;
		if (subid == 0)
		{
			sub = getAcademicSubBySem(sem_id, dep_id);
		} else
		{
			sub = getListById("From AcademicSubject", subid, "sub_id");
		}

		Criteria cr = session.createCriteria(ResultMarksheet.class);
		cr.setProjection(
				Projections.projectionList().add(Projections.groupProperty("subresult")).add(Projections.rowCount()));
		cr.add(Restrictions.ne("grade", "F"));
		cr.add(Restrictions.eq("year", exam_yr));
		cr.add(Restrictions.eq("semister", sem_id));
		// cr.add(Restrictions.eq("academiyr", d.getDep_name().trim()));
		// //subject pass
		// is not equal to grades fail

		cr.add(Restrictions.in("subresult", sub));

		if (!sub.isEmpty())
		{
			test = cr.list();
		}
		System.out.println(test);
		for (Object[] objects : test)
		{

			AcademicSubject subject = (AcademicSubject) objects[0];
			objects[0] = subject.getSubject_name();
			DecimalFormat f = new DecimalFormat("##.00");
			System.out.println("subjectsssssssssssssssssssssssss" + objects[0].toString());
			objects[1] = f.format((((Long) objects[1]).floatValue()
					/ (Long) getTotalStudentAttendTheExam(subject, exam_yr, sem_id) * 100));

			count++;

		}
		return test;
	}

	@Override

	public List<SubjectList> getSubjectdata(Integer dep, Integer sem)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Query sublist = session.createQuery("from SubjectList where dep_id=" + dep + " AND semester=" + sem);
		List<SubjectList> subjectlist = sublist.list();
		// System.out.println("Subject List :" + subjectlist);
		return subjectlist;
	}

	@Override
	public List<AcademicSubject> getAcademicSubData(Integer dep, Integer sem)
	{
		Criteria cr = getCriteriaForSelect(AcademicSubject.class);
		cr.setProjection(Projections.projectionList().add(Property.forName("sub_id"), "sub_id")
				.add(Property.forName("subject_code"), "subject_code")
				.add(Property.forName("subject_name"), "subject_name"));
		cr.add(Restrictions.eq("department.dep_id", dep));
		cr.add(Restrictions.eq("semister", sem.toString()));
		cr.setResultTransformer(Transformers.aliasToBean(AcademicSubject.class));
		return cr.list();
	}

	@Override
	public void insertTimeTable(TimeTable timeTable)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Department d = session.get(Department.class, timeTable.getDepartment().getDep_id());
		timeTable.setDepartment(d);
		timeTable.setBatch_name(0);
		save(timeTable);

	}

	@Override

	public String getSubjectTeacherdata(Integer subId)
	{

		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from ComStaffSubject where SUB_ID=" + subId;
		Query query = session.createQuery(hql);
		String subjectTecher = "";
		List<ComStaffSubject> list = query.list();
		if (list != null)
		{
			for (ComStaffSubject comStaffSubject : list)
			{
				subjectTecher = comStaffSubject.getClientName().getId() + "."
						+ comStaffSubject.getClientName().getFirstName() + " "
						+ comStaffSubject.getClientName().getLastName();
			}
		}
		return subjectTecher;
	}

	@Override

	public void addSubjectdata(Integer depId, Integer semId, Integer[] subject)
	{
		// TODO Auto-generated method stub

		AcademicSubject academicSubject = null;
		Session session = this.sessionFactory.getCurrentSession();
		SubjectList subjectList = new SubjectList();
		for (Integer integer : subject)
		{

			academicSubject = new AcademicSubject();
			subjectList = session.get(SubjectList.class, integer);
			Criteria cr = session.createCriteria(AcademicSubject.class);
			cr.add(Restrictions.eq("department.dep_id", depId));
			cr.add(Restrictions.eq("semister", Integer.toString(semId)));
			cr.add(Restrictions.eq("subject_code", subjectList.getSubjectcode()));
			if (cr.uniqueResult() == null)
			{
				academicSubject.setSubject_code(subjectList.getSubjectcode());
				academicSubject.setSubject_name(subjectList.getSubjectname());
				String str = String.valueOf(semId);
				academicSubject.setSemister(str);
				academicSubject.setDepartment(getDepartment(depId));
				session.save(academicSubject);
			}
		}
	}

	@Override

	public List<SubjectList> getAcademicSubject()
	{
		return getList("From AcademicSubject");
	}

	@Override

	public void insertTeacherSubject(Integer[] subject, Integer cid)
	{
		Session session = this.sessionFactory.getCurrentSession();
		AcademicSubject academicSubject;
		ComStaffSubject comStaffSubject;
		ComClientName clientName = new ComClientName();
		clientName.setId(cid);
		// ComClientName clientName = session.get(ComClientName.class, cid);
		for (Integer subId : subject)
		{

			Criteria cr = session.createCriteria(ComStaffSubject.class);
			cr.add(Restrictions.eq("academicSubject.sub_id", subId));
			cr.add(Restrictions.eq("clientName.id", cid));
			ComStaffSubject comStaffSubject2 = (ComStaffSubject) cr.uniqueResult();
			System.out.println("ben,....................................." + cr.uniqueResult());
			if (comStaffSubject2 == null)
			{
				System.out.println("inside if.............");
				academicSubject = new AcademicSubject();
				comStaffSubject = new ComStaffSubject();
				academicSubject.setSub_id(subId);
				comStaffSubject.setAcademicSubject(academicSubject);
				comStaffSubject.setClientName(clientName);
				session.save(comStaffSubject);
			} else
			{

			}

		}
	}

	@Override

	public List<TimeTableDTO> getStaffLectures(Integer id)
	{
		Criteria cr = getCriteriaForSelect(TimeTable.class);
		cr.createAlias("comClientName", "comClientName");
		cr.createAlias("academicSemester", "academicSemester");
		cr.createAlias("department", "department");
		// cr.createAlias("days", "days");
		// cr.createAlias("courseMaster", "courseMaster");
		// cr.createAlias("academicYear", "academicYear");
		cr.createAlias("academicSubject", "academicSubject");

		cr.setProjection(Projections.projectionList().add(Projections.groupProperty("department.dep_name"), "dep_name")
				.add(Projections.groupProperty("academicSubject.subject_name"), "subject_name"));
		cr.add(Restrictions.eq("comClientName.id", 3));
		cr.setResultTransformer(Transformers.aliasToBean(TimeTableDTO.class));
		return cr.list();
	}

	@Override

	public List<TimeTableDTO> getTimetableSubjectDao(String date)
	{
		ComUserDetails user = getUserDetails();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		Date dt1 = null;
		try
		{
			dt1 = format1.parse(date);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		DateFormat format2 = new SimpleDateFormat("EEEE");
		String finalDay = format2.format(dt1);
		Criteria cr = getCriteriaForSelect(Days.class);
		cr.add(Restrictions.eq("day_name", finalDay));
		Days day = (Days) cr.uniqueResult();
		Criteria ttcr = getCriteriaForSelect(TimeTable.class);
		ttcr.createAlias("days", "days");
		ttcr.createAlias("department", "department");
		ttcr.createAlias("comClientName", "comClientName");
		ttcr.createAlias("academicSubject", "academicSubject");
		ttcr.setProjection(Projections.projectionList().add(Projections.groupProperty("timeTableId"), "timeTableId")
				.add(Projections.groupProperty("department.dep_name"), "dep_name")
				// .add(Projections.groupProperty("academicSubject.sub_id"),
				// "subject_id")
				.add(Projections.groupProperty("division"), "division")
				.add(Projections.groupProperty("academicSubject.subject_name"), "subject_name")
				.add(Projections.groupProperty("periodStartTime"), "periodStartTime")
				.add(Projections.groupProperty("periodEndTime"), "periodEndTime")
				.add(Projections.groupProperty("batch_name"), "batch_name")
				.add(Projections.groupProperty("TheoryorPractical"), "theoryorPractical"));
		ttcr.add(Restrictions.eq("comClientName.id", user.getComClientName().getId()));
		ttcr.add(Restrictions.eq("days.day_vid", day.getDay_vid()));
		// ttcr.add(Restrictions.eq("division", Integer.toString(division)));
		ttcr.setResultTransformer(Transformers.aliasToBean(TimeTableDTO.class));
		return ttcr.list();
	}

	public TimeTableDTO getTimeTableObject(Integer id)
	{
		Criteria ttcr = getCriteriaForSelect(TimeTable.class);
		ttcr.createAlias("department", "department");
		// ttcr.createAlias("academicYear", "academicYear");
		ttcr.setProjection(Projections.projectionList().add(Projections.groupProperty("department.dep_id"), "dep_id")
				.add(Projections.groupProperty("academicYear"), "academicYear")
				.add(Projections.groupProperty("batch_name"), "batch_name")
				.add(Projections.groupProperty("division"), "division")
				.add(Projections.groupProperty("academicSubject.sub_id"), "subject_id")
				.add(Projections.groupProperty("TheoryorPractical"), "theoryorPractical"));
		ttcr.add(Restrictions.eq("timeTableId", id));
		ttcr.setResultTransformer(Transformers.aliasToBean(TimeTableDTO.class));
		return (TimeTableDTO) ttcr.uniqueResult();
	}

	public List<Integer> getBatchStudentList(Integer id, Integer subjectId)
	{
		List<Integer> l = new ArrayList<>();
		Criteria cr = getCriteriaForSelect(BatchTable.class);
		cr.setProjection(Constants.BATCHPROJECTION);
		cr.add(Restrictions.eq("Sub_Id", subjectId));
		cr.add(Restrictions.eq("Batch_name", id));
		cr.setResultTransformer(CriteriaSpecification.PROJECTION);
		List<Object[]> ll = cr.list();
		for (Object[] object : ll)
		{
			for (Object object2 : object)
			{
				if (object2 != null)
				{
					l.add((Integer) object2);
				}
				System.out.println();
			}
		}
		return l;
	}

	@Override

	public Integer addAttendance(ComAttendance comAttendance, Integer[] studId, Integer subId)
	{
		int id = 1;
		ComUserDetails user = getUserDetails();
		Integer status = user.getComClientName().getId();
		Session session = this.sessionFactory.getCurrentSession();

		Date date = comAttendance.getClass_date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// Set time fields to zero
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		// Put it back in the Date object
		date = cal.getTime();
		comAttendance.setClass_date(date);

		// get Acadamic Subject
		Criteria cr = session.createCriteria(ComStaffSubject.class);
		cr.add(Restrictions.eq("academicSubject.sub_id", subId));
		cr.add(Restrictions.eq("clientName.id", user.getComClientName().getId()));
		cr.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		ComStaffSubject comStaffSubject = (ComStaffSubject) cr.uniqueResult();

		// it will check attendance taken or not
		Criteria cr12 = session.createCriteria(ComAttendance.class);
		date = cal.getTime();
		cr12.add(Restrictions.eq("class_date", date));
		cr12.add(Restrictions.eq("division", comAttendance.getDivision()));
		cr12.add(Restrictions.eq("lectureTheoryTime.the_no", comAttendance.getLectureTheoryTime().getThe_no()));
		cr12.add(Restrictions.eq("comStaffSubject.staff_sub_id", comStaffSubject.getStaff_sub_id()));

		// System.out.println("no.........." +
		// comAttendance.getLectures().getLecture_no());

		if (cr12.uniqueResult() != null)
		{
			return status;
		}

		try
		{
			for (Integer element : studId)
			{
				try
				{
					Class ftClass = comAttendance.getClass();
					Field f1 = ftClass.getField("roll_" + id);
					System.out.println("fileld id........" + id);
					System.out.println("fileld...." + f1.toString());
					System.out.println("fileld...." + f1.getName());
					f1.set(comAttendance, element);
				} catch (Exception e)
				{
					System.out.println(e);
				}
				id++;

			}

		} catch (Exception e)
		{
			// TODO: handle exception
		}

		comAttendance.setComStaffSubject(comStaffSubject);
		comAttendance.setDep_Id(Integer.toString(comStaffSubject.getAcademicSubject().getDepartment().getDep_id()));
		comAttendance.setSem(Integer.parseInt(comStaffSubject.getAcademicSubject().getSemister()));
		comAttendance.setYear(findYearUsingSem(comStaffSubject.getAcademicSubject().getSemister()));
		// save(comAttendance);
		save(comAttendance);
		return status;
	}

	@Override

	public Map<Integer, String> getpracticalSubdata(Integer staffId, Integer sem)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Staff staff = session.get(Staff.class, staffId);

		Criteria cr = session.createCriteria(AcademicSubject.class);
		cr.add(Restrictions.eq("semister", String.valueOf(sem)));
		cr.add(Restrictions.eq("department.dep_id", staff.getDepartment()));
		List<AcademicSubject> list = cr.list();

		Map<Integer, String> map = new HashMap<>();
		for (AcademicSubject academicSubject : list)
		{
			map.put(academicSubject.getSub_id(), academicSubject.getSubject_name());
		}

		session.flush();

		return map;
	}

	@Override

	public String saveSubjectListViaExcelFile(MultipartFile excelFile) throws Exception
	{
		// TODO Auto-generated method stub
		// System.out.println("coming :-" + excelFile.getOriginalFilename());

		String msg = "error while rading file. ";
		SubjectList subjectList = null;
		XSSFWorkbook wb;
		System.out.println("1");
		wb = new XSSFWorkbook(excelFile.getInputStream());
		XSSFSheet sheet;
		// System.out.println("Number of sheet............" +
		// wb.getNumberOfSheets());
		Row row;
		sheet = wb.getSheetAt(2);
		// System.out.println("Sheet Count............" +
		// sheet.getLastRowNum());
		if (sheet.getLastRowNum() > 0)
		{
			System.out.println("2");
			for (int i = 1; i <= sheet.getLastRowNum(); i++)
			{
				row = sheet.getRow(i);
				subjectList = new SubjectList();

				for (int j = 1; j < row.getLastCellNum(); j++)
				{
					subjectList.setDep_id((int) row.getCell(1).getNumericCellValue());
					subjectList.setSemester((int) row.getCell(2).getNumericCellValue());
					subjectList.setSubjectcode(row.getCell(3).toString());
					subjectList.setSubjectname(row.getCell(4).toString());
				}
				// System.out.println("list" + subjectList);
				save(subjectList);
				msg = "Congratulation..! File Upload Successfully... ";
			}
		} else
		{
			// System.out.println("last");
			msg = "Sorry..! Your file is empty cant process.";
		}
		wb.close();
		System.out.println("last1");
		return msg;

	}

	@Override

	public List<AcademicSubject> getSubjectToAssign()
	{
		Criteria cr = getCriteriaForSelect(AcademicSubject.class);
		cr.setProjection(Projections.projectionList().add(Projections.groupProperty("sub_id"), "sub_id")
				.add(Projections.groupProperty("subject_code"), "subject_code")
				.add(Projections.groupProperty("subject_name"), "subject_name"));
		cr.setResultTransformer(Transformers.aliasToBean(AcademicSubject.class));
		List<AcademicSubject> list = cr.list();
		return list;
	}

	@Override

	public List<SubjectsDTO> getStaffSubject(Integer staffClientId)
	{
		ArrayList str = new ArrayList();
		Criteria cr = getCriteriaForSelect(TimeTable.class);
		cr.createAlias("comClientName", "comClientName");
		cr.createAlias("academicSubject", "academicSubject");

		cr.setProjection(Projections.projectionList().add(Projections.groupProperty("academicSubject.sub_id"), "sub_id")
				.add(Projections.groupProperty("division"), "div_name")
				.add(Projections.groupProperty("academicSubject.subject_name"), "sub_name"));
		cr.add(Restrictions.eq("comClientName.id", staffClientId));
		cr.setResultTransformer(Transformers.aliasToBean(SubjectsDTO.class));
		List<SubjectsDTO> list = cr.list();
		for (SubjectsDTO subjectsDTO : list)
		{
			str.add(subjectsDTO.getDiv_name());
			subjectsDTO.setStr(str);
		}
		// subjectsDTO.se
		return list;
	}

	@Override

	public List<ComClientNameDTO> getAcademiStudListDto(Integer subid, String div, int attendanceid, String batchID)
	{

		AcademicSubject academicSubject = (AcademicSubject) get(AcademicSubject.class, subid);
		Criteria cr = getCriteriaForSelect(Student.class);
		cr.createAlias("comClientName", "comClientName");
		cr.setProjection(Projections.projectionList().add(Property.forName("comClientName.id"), "id")
				.add(Property.forName("comClientName.firstName"), "firstName")
				.add(Property.forName("comClientName.middleName"), "middleName")
				.add(Property.forName("comClientName.lastName"), "lastName").add(Property.forName("rollNo"), "rollNo"));
		cr.add(Restrictions.eq("branch", academicSubject.getDepartment().getDep_id()));
		cr.add(Restrictions.eq("standard", findStanderdUSingDivision(div)));
		cr.add(Restrictions.eq("year", findYearUsingSem(academicSubject.getSemister())));
		if (attendanceid == 2)
		{
			cr.add(Restrictions.eq("batch", batchID));

		}

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

		return list;
	}

	@Override

	public boolean addStudAttendance(SubjectsDTO studsub)
	{
		// TODO Auto-generated method stub
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("date.................... " + studsub.getDate());
		Date dt1 = null;
		try
		{
			dt1 = format1.parse(studsub.getDate());
		} catch (ParseException e)
		{
			e.printStackTrace();
		}

		Session session = this.sessionFactory.getCurrentSession();
		ComAttendance comAttendance = new ComAttendance();
		comAttendance.setDivision(studsub.getDiv_name());
		// TimeTable timetable = (TimeTable) get(TimeTable.class,
		// studsub.getSub_id());

		// get Acadamic Subject
		Criteria cr = session.createCriteria(ComStaffSubject.class);
		cr.add(Restrictions.eq("academicSubject.sub_id", studsub.getSub_id()));
		cr.add(Restrictions.eq("clientName.id", studsub.getClientId()));
		cr.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		ComStaffSubject comStaffSubject = (ComStaffSubject) cr.uniqueResult();

		// it will check attendance taken or not
		Criteria cr12 = session.createCriteria(ComAttendance.class);
		cr12.add(Restrictions.eq("class_date", dt1));
		cr12.add(Restrictions.eq("division", studsub.getDiv_name()));
		cr12.add(Restrictions.eq("lectureTheoryTime.the_no", studsub.getPeriodsTime()));
		cr12.add(Restrictions.eq("comStaffSubject.staff_sub_id", comStaffSubject.getStaff_sub_id()));

		if (cr12.uniqueResult() == null)
		{

			comAttendance.setComStaffSubject(comStaffSubject);
			comAttendance.setDep_Id(Integer.toString(comStaffSubject.getAcademicSubject().getDepartment().getDep_id()));
			comAttendance.setClass_date(dt1);
			comAttendance.setSem(Integer.parseInt(comStaffSubject.getAcademicSubject().getSemister()));
			comAttendance.setYear(findYearUsingSem(comStaffSubject.getAcademicSubject().getSemister()));

			LectureTheoryTime lectureTheoryTime = session.get(LectureTheoryTime.class, studsub.getPeriodsTime());
			comAttendance.setLectureTheoryTime(lectureTheoryTime);
			ArrayList<String> list = (ArrayList<String>) studsub.getStr();
			int id = 1;

			try
			{
				for (String clientid : list)
				{
					try
					{
						Class ftClass = comAttendance.getClass();
						Field f1 = ftClass.getField("roll_" + id);
						f1.set(comAttendance, Integer.parseInt(clientid));
					} catch (Exception e)
					{
						System.out.println(e);
					}
					id++;
				}

			} catch (Exception e)
			{
				// TODO: handle exception
			}

			save(comAttendance);
			return true;
		} else
		{
			return false;
		}

	}

	@Override

	public String saveFeedbackListViaExcelFile(MultipartFile excelFile)
	{
		// TODO Auto-generated method stub
		System.out.println("coming....in DAo");
		String msg = "error while rading file. ";
		FeedbackQue feedbackQue = null;
		XSSFWorkbook wb = null;
		XSSFSheet sheet = null;
		Row row;
		// System.out.println("1");
		try
		{
			wb = new XSSFWorkbook(excelFile.getInputStream());

			sheet = wb.getSheetAt(2);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("sheet" + sheet.getFirstRowNum() + ".........." + sheet.getLastRowNum());
		if (sheet.getLastRowNum() > 0)
		{
			for (int i = 1; i <= sheet.getLastRowNum(); i++)
			{
				row = sheet.getRow(i);
				feedbackQue = new FeedbackQue();
				for (int j = 1; j < row.getLastCellNum(); j++)
				{

					feedbackQue.setQuestions(row.getCell(1).toString());
					// feedbackQue.setVote((int)
					// row.getCell(2).getNumericCellValue());
					feedbackQue.setChecked(false);
				}
				// System.out.println("list" + feedbackQue.getQuestions() +
				// feedbackQue);
				save(feedbackQue);
				msg = "Congratulation..! File Upload Successfully... ";
			}
		} else
		{
			msg = "Sorry..! Your file is empty cant process.";
		}
		try
		{
			wb.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	@Override

	public Staff getstaffData(Integer staff_id)
	{
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Staff where CLIENT_ID=" + staff_id;
		Query query = session.createQuery(hql);
		List<Staff> idlist = query.list();
		Staff bean = null;
		if (!idlist.isEmpty())
		{
			for (Staff staff : idlist)
			{
				System.out.println("staffdata::" + staff);
				bean = staff;
			}
		}
		return bean;
	}

	@Override

	public List<Mentor> getMentorStudentList(Integer staff_id)
	{

		/*
		 * System.out.println("//////////" + staff_id); List<Student> studdata = null;
		 * try {
		 *
		 * System.out.println("coming"); String PERfieldname = "id"; String getfieldname
		 * = "STAFF_ID"; String tablename = "Mentor"; List<Integer> list =
		 * getid(staff_id, PERfieldname, getfieldname, tablename); Session session =
		 * this.sessionFactory.getCurrentSession(); ArrayList<Integer> studlist = new
		 * ArrayList<>(); if (!list.isEmpty()) { for (Integer id : list) {
		 * System.out.println("..." + id); String hql = "from MentorStudent where ID=" +
		 * id; Query query = session.createQuery(hql); List<MentorStudent> idlist =
		 * query.list(); if (!idlist.isEmpty()) { for (MentorStudent mentorStudent :
		 * idlist) { studlist.add(mentorStudent.getStudent().getStudentId()); } } } }
		 *
		 * if (!list.isEmpty()) { Query query2 = session.
		 * createQuery("from  Student e where e.studentId in (:Students)")
		 * .setParameterList("Students", studlist); studdata = query2.list();
		 *
		 * }
		 *
		 * } catch (Exception e) {
		 *
		 * } return studdata; }
		 */
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Staff.class, "Staff");
		c.add(Restrictions.eq("Staff.comClientName.id", staff_id));
		Staff staff = (Staff) c.uniqueResult();
		staff.getStaffId();
		Criteria cr = getCriteriaForSelect(Mentor.class);
		cr.add(Restrictions.eq("staff.staffId", staff.getStaffId()));
		List<Mentor> mentor = cr.list();
		return mentor;
	}

	@Override
	public List<ComStaffSubject> getAssigendStaffSubject(Integer staffId)
	{
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = getCriteriaForSelect(ComStaffSubject.class);
		cr.createAlias("academicSubject", "academicSubject");
		cr.setProjection(Projections.projectionList().add(Property.forName("academicSubject"), "academicSubject")
				.add(Property.forName("staff_sub_id"), "staff_sub_id"));
		cr.add(Restrictions.eq("clientName.id", staffId));
		cr.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		cr.setResultTransformer(Transformers.aliasToBean(ComStaffSubject.class));
		return cr.list();
	}

	@Override
	public List<String> getdivision(Integer staffId, Integer subId)
	{

		Session session = this.sessionFactory.getCurrentSession();
		AcademicSubject academicSubject = session.get(AcademicSubject.class, subId);
		Criteria cr = getCriteriaForSelect(Student.class);
		cr.setProjection(Projections.projectionList().add(Projections.groupProperty("standard")));
		cr.add(Restrictions.eq("branch", academicSubject.getDepartment().getDep_id()));
		cr.add(Restrictions.eq("year", findYearUsingSem(academicSubject.getSemister())));
		List<Integer> standard = cr.list();
		List<String> division = new ArrayList<>();
		for (Integer id : standard)
		{
			division.add(findDivisionusingStanderd(id));
		}

		return division;
	}

	@Override

	public List<DepartmentDTO> getDepartmentListForAttendance(Integer staffID)
	{

		Session session = this.sessionFactory.getCurrentSession();
		Staff staff = session.get(Staff.class, staffID);
		Criteria cr = getCriteriaForSelect(Department.class);
		cr.setProjection(Projections.projectionList().add(Property.forName("dep_id"), "dep_id")
				.add(Property.forName("dep_name"), "dep_name"));
		if (staff.getDepartment() != null && staff.getDepartment() != 0)
		{
			cr.add(Restrictions.eq("dep_id", staff.getDepartment()));
		}
		cr.setResultTransformer(Transformers.aliasToBean(DepartmentDTO.class));
		return cr.list();
	}

	@Override
	public AttendanceDTOWeb getDailyAttendanceData(ComAttendance comAttendance, int SubjectId)
	{

		return getDalilyAttendance(comAttendance, SubjectId);

	}

	@Override

	public Boolean dailyAttendanceReportDownload(ComAttendance comAttendance, int reportId, String todate)
	{
		if (reportId == 1)
		{
			return attendanceReportDownloadDaily(comAttendance, null, 1);
		} else
		{
			// cimulative attendance......

			Session session = this.sessionFactory.getCurrentSession();
			DateFormat dateFormat1 = new SimpleDateFormat("M/dd/yyyy");

			System.out.println("to date.........." + todate);

			Date toDate = null;
			try
			{
				toDate = dateFormat1.parse(todate);
			} catch (ParseException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// get subject attendance list
			List<ComAttendance> comAttendances = getCumulativeAttendane(comAttendance, toDate);
			try
			{
				if (comAttendances.isEmpty())
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
			cell.setCellValue("Cumulative Attendance Report");

			row = sheet.createRow(1);
			cell = row.createCell(0);
			cell.setCellValue("ZertonES ");
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
			Department department = session.get(Department.class, Integer.parseInt(comAttendance.getDep_Id()));
			row = sheet.createRow(2);
			cell = row.createCell(0);
			cell.setCellValue("Department:" + department.getDep_name());
			cell = row.createCell(1);
			cell.setCellValue("SEM:" + comAttendance.getSem());
			cell = row.createCell(2);
			cell.setCellValue("Class:" + getYearName(comAttendance.getYear()));

			cell = row.createCell(3);
			cell.setCellValue("Division:" + comAttendance.getDivision());

			DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy");
			String fdate = dateFormat.format(comAttendance.getClass_date());
			String tdate = dateFormat.format(toDate);

			row = sheet.createRow(3);
			cell = row.createCell(0);
			cell.setCellValue("Date");
			cell = row.createCell(1);
			cell.setCellValue(fdate + " to " + tdate);

			Map<String, Integer> subjectList = new HashMap<>();
			// use for
			// <studentId,<subjectId,presenttotal>
			Map<Integer, Map<Integer, Integer>> studentPresentLecture = new LinkedHashMap();

			// All student List
			List<AttendanceStudentDTO> sudentList = getStudentListForAttendance(comAttendance);
			Map<Integer, Integer> subjCount = new HashMap<>();
			for (ComAttendance attendance : comAttendances)// calculate student
															// present lecture
			{

				List<Integer> presentList = new ArrayList();

				// present student List....
				for (int i = 1; i <= 80; i++)
				{
					try
					{
						Class ftClass = attendance.getClass();
						Field f1 = ftClass.getField("roll_" + i);
						if (f1.get(attendance) == null)
						{
							break;
						} else
						{
							presentList.add((Integer) f1.get(attendance));
						}

					} catch (Exception e)
					{
						System.out.println(e);
					}
				}
				System.out.println("size.................." + sudentList.size());
				for (AttendanceStudentDTO studData : sudentList)
				{
					System.out.println("student data.............." + studData);
					if (studentPresentLecture.containsKey(studData.getId()))// check
																			// student
																			// id
																			// all
																			// ready
																			// present
																			// or
																			// not
					{

						Map<Integer, Integer> map = studentPresentLecture.get(studData.getId());
						if (map.containsKey(attendance.getComStaffSubject().getAcademicSubject().getSub_id()))
						{

							if (presentList.contains(studData.getId()))
							{
								map.put(attendance.getComStaffSubject().getAcademicSubject().getSub_id(),
										map.get(attendance.getComStaffSubject().getAcademicSubject().getSub_id()) + 1);
							} else
							{
								map.put(attendance.getComStaffSubject().getAcademicSubject().getSub_id(),
										map.get(attendance.getComStaffSubject().getAcademicSubject().getSub_id()));

							}
						} else
						{

							HashMap<Integer, Integer> subCount = new HashMap<>();
							if (presentList.contains(studData.getId()))
							{

								studentPresentLecture.get(studData.getId())
										.put(attendance.getComStaffSubject().getAcademicSubject().getSub_id(), 1);
							} else
							{

								System.out.println("present List........."
										+ attendance.getComStaffSubject().getAcademicSubject().getSub_id());
								// studentPresentLecture.put(studData.getId(),
								// null);
								studentPresentLecture.get(studData.getId())
										.put(attendance.getComStaffSubject().getAcademicSubject().getSub_id(), 0);
							}

						}

					} else
					{
						Map<Integer, Integer> subCount = new HashMap<>();

						if (presentList.contains(studData.getId()))
						{
							subCount.put(attendance.getComStaffSubject().getAcademicSubject().getSub_id(), 1);
							studentPresentLecture.put(studData.getId(), subCount);
						} else
						{

							subCount.put(attendance.getComStaffSubject().getAcademicSubject().getSub_id(), 0);
							studentPresentLecture.put(studData.getId(), subCount);
						}
					}

					System.out.println("student List for......" + studentPresentLecture);

				}

				System.out.println("attendam List for......" + studentPresentLecture);

				if (subjCount.containsKey(attendance.getComStaffSubject().getAcademicSubject().getSub_id()))
				{
					subjCount.put(attendance.getComStaffSubject().getAcademicSubject().getSub_id(),
							subjCount.get(attendance.getComStaffSubject().getAcademicSubject().getSub_id()) + 1);
				} else
				{
					subjCount.put(attendance.getComStaffSubject().getAcademicSubject().getSub_id(), 1);

				}

			}

			// add student and present lecture count
			int displaySubCounter = 1;
			Iterator<Entry<Integer, Map<Integer, Integer>>> parent = studentPresentLecture.entrySet().iterator();
			int srno = 1;
			int cellno = 8;
			List<Integer> list = new ArrayList();
			while (parent.hasNext())
			{

				Entry<Integer, Map<Integer, Integer>> parentPair = parent.next();
				Iterator<Entry<Integer, Integer>> child = (parentPair.getValue()).entrySet().iterator();
				Map<Integer, Integer> subjectAndTotal = new HashMap<>();
				row = sheet.createRow(cellno);
				cell = row.createCell(0);
				cell.setCellValue(srno);

				ComClientName clientName = session.get(ComClientName.class, parentPair.getKey());

				Criteria cr = getCriteriaForSelect(Student.class);
				cr.createAlias("comClientName", "comClientName");
				cr.setProjection(Projections.projectionList().add(Property.forName("comClientName.id"), "id")
						.add(Property.forName("comClientName.firstName"), "firstName")
						.add(Property.forName("comClientName.middleName"), "middleName")
						.add(Property.forName("comClientName.lastName"), "lastName")
						.add(Property.forName("rollNo"), "rollNo"));
				cr.add(Restrictions.eq("comClientName.id", parentPair.getKey()));
				cr.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
				cr.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));
				ComClientNameDTO comClientNameDTO = (ComClientNameDTO) cr.uniqueResult();

				cell = row.createCell(0);
				cell.setCellValue(comClientNameDTO.getRollNo());
				cell = row.createCell(1);
				cell.setCellValue(comClientNameDTO.getFirstName() + " " + comClientNameDTO.getMiddleName() + " "
						+ comClientNameDTO.getLastName());

				int celltotal = 2;
				int presentTotal = 0;
				while (child.hasNext())
				{

					Map.Entry childPair = child.next();
					cell = row.createCell(celltotal);
					cell.setCellValue((Integer) childPair.getValue());
					presentTotal = presentTotal + (Integer) childPair.getValue();

					// use for add subject name and total number subject
					if (displaySubCounter == 1)
					{

						list.add((Integer) childPair.getKey());
					}

					celltotal++;

				}
				displaySubCounter++;
				cell = row.createCell(celltotal);
				cell.setCellValue(presentTotal);
				cellno++;
				srno++;
			}
			row = sheet.createRow(5);
			cell = row.createCell(0);
			cell.setCellValue("Sr No.");
			cell = row.createCell(1);
			AcademicSubject academicSubject = null;
			cell.setCellValue("Name of Student");
			int cellCount = 2;
			// add subject name........
			for (Integer integer : list)
			{

				System.out.println("subject ,,,,,,,,,,,,,,,,,,,,");
				academicSubject = new AcademicSubject();
				academicSubject = getAcadamicSubject(integer);
				cell = row.createCell(cellCount);
				cell.setCellValue(academicSubject.getSubject_name());
				cellCount++;
			}
			cell = row.createCell(cellCount);
			cell.setCellValue("Total");

			// total subject count
			double totalLec = 0;
			int cellCount1 = 2;
			row = sheet.createRow(6);
			for (Integer integer : list)
			{
				cell = row.createCell(cellCount1);
				cell.setCellValue(subjCount.get(integer));
				cellCount1++;
				totalLec = totalLec + subjCount.get(integer);
			}
			cell = row.createCell(cellCount1);
			cell.setCellValue(totalLec);

			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			try
			{
				wb.write(outByteStream);
				byte[] outArray = outByteStream.toByteArray();
				responses.setContentType("application/ms-excel");
				responses.setContentLength(outArray.length);
				responses.setHeader("Expires:", "0"); // eliminates browser
														// caching
				responses.setHeader("Content-Disposition", "attachment; filename=CumulativeAttendance.xls");
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
	}

	@Override

	public List<Mentor> getMenterGroupList(Integer staffId)
	{
		// TODO Auto-generated method stub

		System.out.println("Dao id is" + staffId);
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = getCriteriaForSelect(Mentor.class);
		// cr.setProjection(Projections.projectionList().add(Projections.groupProperty("division")));
		cr.add(Restrictions.eq("staff.staffId", staffId));
		cr.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		System.out.println("list" + cr.list());
		return cr.list();
	}

	@Override

	public void deleteMenterGroup(Integer groupid)
	{

		Session session = this.sessionFactory.getCurrentSession();
		Mentor mentor = session.get(Mentor.class, groupid);
		mentor.setRecordStatus(Constants.DELETED_RECORD_STATUS);

		Criteria cr1 = getCriteriaForSelect(Groups.class);
		cr1.add(Restrictions.eq("gfm_id", mentor.getId()));
		Groups grpid = (Groups) cr1.uniqueResult();

		Groups g = (Groups) get(Groups.class, grpid.getGroupId());
		Groups gp = (Groups) delete(g);
		if (gp.getRecordStatus().equals(Constants.DELETED_RECORD_STATUS))
		{
			List<GroupMembers> gm = getListById("From GroupMembers", grpid.getGroupId(), "groups.groupId");
			for (GroupMembers groupMembers : gm)
			{
				delete(groupMembers);
			}
		}

	}

	@Override
	public Integer SaveStudFeeDetails(Integer clientID, FeeDetails feeDetails)
	{
		// TODO Auto-generated method stub

		Session session = this.sessionFactory.getCurrentSession();

		Criteria cr1 = getCriteriaForSelect(Student.class);
		cr1.add(Restrictions.eq("comClientName.id", clientID));
		Student stud = (Student) cr1.uniqueResult();

		Criteria cr = getCriteriaForSelect(FeeDetails.class);
		cr.add(Restrictions.eq("student.studentId", stud.getStudentId()));
		FeeDetails feeDtl = (FeeDetails) cr.uniqueResult();
		System.out.println("/././././././." + feeDtl);

		if (feeDtl == null)
		{
			feeDetails.setStudent(stud);
			save(feeDetails);
		} else
		{
			feeDtl.setTotal_fee(feeDetails.getTotal_fee());
			feeDtl.setCategory(feeDetails.getCategory());
			feeDtl.setOutstanding(feeDetails.getOutstanding());
			feeDtl.setPaid(feeDetails.getPaid());
			feeDtl.setRemaining(feeDetails.getRemaining());
			feeDtl.setInstallment1(feeDetails.getInstallment1());
			feeDtl.setInstallment2(feeDetails.getInstallment2());
			feeDtl.setInstallment3(feeDetails.getInstallment3());
			feeDtl.setInstallment4(feeDetails.getInstallment4());

			update(feeDtl);

			update(feeDtl);
		}

		return stud.getComClientName().getId();
	}

	@Override

	public FeeDetails getFeeDetails(Integer clientId)
	{
		// TODO Auto-generated method stub
		System.out.println("///////////////////" + clientId);
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = getCriteriaForSelect(Student.class);
		cr.add(Restrictions.eq("comClientName.id", clientId));
		Student stud = (Student) cr.uniqueResult();
		Criteria c = getCriteriaForSelect(FeeDetails.class);
		c.add(Restrictions.eq("student.studentId", stud.getStudentId()));
		FeeDetails fd = (FeeDetails) c.uniqueResult();

		System.out.println("bean found..........................................." + stud);

		return fd;
	}

	@Override

	public void deleteAssingedSubject(Integer clientID, Integer subjectId)
	{
		System.out.println(subjectId + "///////////////////" + clientID);
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = getCriteriaForSelect(ComStaffSubject.class);
		cr.add(Restrictions.eq("clientName.id", clientID));
		cr.add(Restrictions.eq("staff_sub_id", subjectId));
		ComStaffSubject comStaffSubject = (ComStaffSubject) cr.uniqueResult();
	}

	@Override

	public List<String> getDivision_For_Batch(Integer depId, Integer classId)
	{
		Criteria cr = getCriteriaForSelect(Student.class);
		cr.setProjection(Projections.projectionList().add(Projections.groupProperty("standard")));
		cr.add(Restrictions.eq("branch", depId));
		cr.add(Restrictions.eq("year", classId));
		List<Integer> standard = cr.list();
		List<String> division = new ArrayList<>();
		for (Integer id : standard)
		{
			division.add(findDivisionusingStanderd(id));
		}

		return division;
	}

	@Override

	public List<ComClientNameDTO> getStudent_For_Batch(Integer depId, Integer classId, String divId)
	{
		System.out.println("dept id is...." + depId + classId + divId);
		Criteria cr = getCriteriaForSelect(Student.class);
		cr.createAlias("comClientName", "comClientName");
		cr.setProjection(Projections.projectionList().add(Property.forName("studentId"), "id")
				.add(Property.forName("comClientName.firstName"), "firstName")
				.add(Property.forName("comClientName.middleName"), "middleName")
				.add(Property.forName("comClientName.lastName"), "lastName").add(Property.forName("rollNo"), "rollNo"));
		cr.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		cr.add(Restrictions.eq("branch", depId));
		cr.add(Restrictions.eq("standard", findStanderdUSingDivision(divId)));
		cr.add(Restrictions.eq("year", classId));

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

		return list;

	}

	@Override

	public int add_Student_Batch(BatchList batchList, int depId, int classId, String divId)
	{
		Session session = this.sessionFactory.getCurrentSession();
		ComUserDetails user = getUserDetails();

		BatchTable batchTable = null;
		for (BatchDetails batch : batchList.getBatchDetails())
		{
			Criteria cr = getCriteriaForSelect(Student.class);
			cr.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
			cr.add(Restrictions.eq("studentId", batch.getStudId()));
			Student student = (Student) cr.uniqueResult();
			student.setBatch(batch.getBatch());
		}
		Criteria cr1 = getCriteriaForSelect(Staff.class);
		cr1.add(Restrictions.eq("comClientName.id", user.getComClientName().getId()));
		Staff staff = (Staff) cr1.uniqueResult();
		return staff.getStaffId();
	}

	@Override

	public List<String> getPractical_Batch(Integer subId, String divisionid)
	{
		Session session = this.sessionFactory.getCurrentSession();
		AcademicSubject academicSubject = session.get(AcademicSubject.class, subId);
		Criteria cr = getCriteriaForSelect(Student.class);
		cr.setProjection(Projections.projectionList().add(Projections.groupProperty("batch")));
		cr.add(Restrictions.eq("branch", academicSubject.getDepartment().getDep_id()));
		cr.add(Restrictions.eq("year", findYearUsingSem(academicSubject.getSemister())));
		cr.add(Restrictions.eq("standard", findStanderdUSingDivision(divisionid)));
		List<String> batchList = cr.list();
		return batchList;
	}

	@Override

	public int addPracticalAttendance(ComAttendance comAttendance, Integer[] studId, Integer subjectId, String batch)
	{

		int id = 1;
		ComUserDetails user = getUserDetails();
		Integer status = user.getComClientName().getId();
		Session session = this.sessionFactory.getCurrentSession();
		ComPracticalAttendance comPracticalAttendance = new ComPracticalAttendance();
		Date date = comAttendance.getClass_date();

		System.out.println("date..........................." + date);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// Set time fields to zero
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		// Put it back in the Date object
		date = cal.getTime();
		comPracticalAttendance.setClass_date(date);

		// get Acadamic Subject
		Criteria cr = getCriteriaForSelect(ComStaffSubject.class);
		cr.add(Restrictions.eq("academicSubject.sub_id", subjectId));
		cr.add(Restrictions.eq("clientName.id", user.getComClientName().getId()));
		ComStaffSubject comStaffSubject = (ComStaffSubject) cr.uniqueResult();

		// it will check attendance taken or not
		Criteria cr12 = getCriteriaForSelect(ComPracticalAttendance.class);
		date = cal.getTime();
		cr12.add(Restrictions.eq("class_date", date));

		cr12.add(Restrictions.eq("lecturePracticalTime.pre_no", comAttendance.getLectureTheoryTime().getThe_no()));
		cr12.add(Restrictions.eq("comStaffSubject.staff_sub_id", comStaffSubject.getStaff_sub_id()));
		cr12.add(Restrictions.eq("batch_name", batch));
		cr12.add(Restrictions.eq("division", comAttendance.getDivision()));
		// System.out.println("no.........." +
		// comAttendance.getLectures().getLecture_no());

		if (cr12.uniqueResult() != null)
		{

			return status;
		}

		try
		{
			for (Integer element : studId)
			{
				try
				{
					Class ftClass = comPracticalAttendance.getClass();
					Field f1 = ftClass.getField("roll_" + id);

					f1.set(comPracticalAttendance, element);
				} catch (Exception e)
				{
					System.out.println(e);
				}
				id++;

			}

		} catch (Exception e)
		{
			// TODO: handle exception
		}

		LecturePracticalTime lecturePracticalTime = session.get(LecturePracticalTime.class,
				comAttendance.getLectureTheoryTime().getThe_no());
		comPracticalAttendance.setBatch_name(batch);
		comPracticalAttendance.setLecturePracticalTime(lecturePracticalTime);
		comPracticalAttendance.setDivision(comAttendance.getDivision());
		comPracticalAttendance.setComStaffSubject(comStaffSubject);
		comPracticalAttendance
				.setDep_Id(Integer.toString(comStaffSubject.getAcademicSubject().getDepartment().getDep_id()));
		comPracticalAttendance.setSem(Integer.parseInt(comStaffSubject.getAcademicSubject().getSemister()));
		comPracticalAttendance.setYear(findYearUsingSem(comStaffSubject.getAcademicSubject().getSemister()));

		// save(comAttendance);
		save(comPracticalAttendance);
		return status;
	}

	@Override

	public boolean addBatchStudAttendance(SubjectsDTO studsub)
	{
		// TODO Auto-generated method stub
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		Date dt1 = null;
		try
		{
			dt1 = format1.parse(studsub.getDate());
		} catch (ParseException e)
		{
			e.printStackTrace();
		}

		Session session = sessionFactory.getCurrentSession();
		ComPracticalAttendance comPracticalAttendance = new ComPracticalAttendance();
		comPracticalAttendance.setDivision(studsub.getDiv_name());
		// TimeTable timetable = (TimeTable) get(TimeTable.class,
		// studsub.getSub_id());

		// get Acadamic Subject
		Criteria cr = getCriteriaForSelect(ComStaffSubject.class);
		cr.add(Restrictions.eq("academicSubject.sub_id", studsub.getSub_id()));
		cr.add(Restrictions.eq("clientName.id", studsub.getClientId()));
		ComStaffSubject comStaffSubject = (ComStaffSubject) cr.uniqueResult();

		// it will check attendance taken or not
		Criteria cr12 = getCriteriaForSelect(ComPracticalAttendance.class);
		cr12.add(Restrictions.eq("class_date", dt1));
		cr12.add(Restrictions.eq("batch_name", studsub.getBatch()));
		cr12.add(Restrictions.eq("division", studsub.getDiv_name()));
		cr12.add(Restrictions.eq("lecturePracticalTime.pre_no", studsub.getPeriodsTime()));
		cr12.add(Restrictions.eq("comStaffSubject.staff_sub_id", comStaffSubject.getStaff_sub_id()));

		if (cr12.uniqueResult() == null)
		{

			comPracticalAttendance.setComStaffSubject(comStaffSubject);
			comPracticalAttendance
					.setDep_Id(Integer.toString(comStaffSubject.getAcademicSubject().getDepartment().getDep_id()));

			comPracticalAttendance.setClass_date(dt1);
			comPracticalAttendance.setSem(Integer.parseInt(comStaffSubject.getAcademicSubject().getSemister()));
			comPracticalAttendance.setYear(findYearUsingSem(comStaffSubject.getAcademicSubject().getSemister()));
			LecturePracticalTime lecturePracticalTime = session.get(LecturePracticalTime.class,
					studsub.getPeriodsTime());
			comPracticalAttendance.setLecturePracticalTime(lecturePracticalTime);

			comPracticalAttendance.setBatch_name(studsub.getBatch());
			ArrayList<String> list = (ArrayList<String>) studsub.getStr();
			int id = 1;

			try
			{
				for (String clientid : list)
				{
					try
					{
						Class ftClass = comPracticalAttendance.getClass();
						Field f1 = ftClass.getField("roll_" + id);
						f1.set(comPracticalAttendance, Integer.parseInt(clientid));
					} catch (Exception e)
					{
						System.out.println(e);
					}
					id++;
				}

			} catch (Exception e)
			{
				// TODO: handle exception
			}

			save(comPracticalAttendance);
			return true;
		} else
		{
			return false;
		}
	}

	@Override

	public List<LecturePracticalTime> getPracticalLectureTime()
	{
		// TODO Auto-generated method stub

		Criteria cr = getCriteriaForSelect(LecturePracticalTime.class);
		return cr.list();
	}

	@Override

	public List<LectureTheoryTime> getTheoryLectureTime()
	{
		Criteria cr = getCriteriaForSelect(LectureTheoryTime.class);
		return cr.list();
	}

	@Override

	public AttendanceDTOWeb getPracticalDailyAttendanceData(ComAttendance comAttendance, int subjectId, String batch)
	{

		return getPracticalDailyAttendance(comAttendance, subjectId, batch);

	}

	@Override

	public Boolean daily_PracticalAttendanceReportDownload(ComAttendance comAttendance, int reportid, String todate,
			String batch)
	{
		// TODO Auto-generated method stub

		if (reportid == 1)
		{
			return attendanceReportDownloadDaily(comAttendance, batch, 2);
		}
		{
			// cimulative attendance......

			Session session = this.sessionFactory.getCurrentSession();
			DateFormat dateFormat1 = new SimpleDateFormat("M/dd/yyyy");

			System.out.println("to date.........." + todate);

			Date toDate = null;
			try
			{
				toDate = dateFormat1.parse(todate);
			} catch (ParseException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// get subject attendance list
			List<ComPracticalAttendance> comPracticalAttendances = getPracticalCumulativeAttendane(comAttendance,
					toDate, batch);
			try
			{
				if (comPracticalAttendances.isEmpty())
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
			cell.setCellValue("Cumulative Practical Attendance Report");

			row = sheet.createRow(1);
			cell = row.createCell(0);
			cell.setCellValue("G S Moze College of Engineering , Balewadi ");
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
			Department department = session.get(Department.class, Integer.parseInt(comAttendance.getDep_Id()));
			row = sheet.createRow(2);
			cell = row.createCell(0);
			cell.setCellValue("Department:" + department.getDep_name());
			cell = row.createCell(1);
			cell.setCellValue("SEM:" + comAttendance.getSem());
			cell = row.createCell(2);
			cell.setCellValue("Class:" + getYearName(comAttendance.getYear()));

			cell = row.createCell(3);
			cell.setCellValue("Division:" + comAttendance.getDivision());

			cell = row.createCell(4);
			cell.setCellValue("Batch:" + batch);

			DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy");
			String fdate = dateFormat.format(comAttendance.getClass_date());
			String tdate = dateFormat.format(toDate);

			row = sheet.createRow(3);
			cell = row.createCell(0);
			cell.setCellValue("Date");
			cell = row.createCell(1);
			cell.setCellValue(fdate + " to " + tdate);

			Map<String, Integer> subjectList = new HashMap<>();
			// use for
			// <studentId,<subjectId,presenttotal>
			Map<Integer, Map<Integer, Integer>> studentPresentLecture = new LinkedHashMap();

			// All student List
			List<AttendanceStudentDTO> sudentList = getStudentListForPractical(comAttendance, batch);
			Map<Integer, Integer> subjCount = new HashMap<>();
			for (ComPracticalAttendance attendance : comPracticalAttendances)// calculate
																				// student
																				// present
																				// lecture
			{

				List<Integer> presentList = new ArrayList();

				// present student List....
				for (int i = 1; i <= 80; i++)
				{
					try
					{
						Class ftClass = attendance.getClass();
						Field f1 = ftClass.getField("roll_" + i);
						if (f1.get(attendance) == null)
						{
							break;
						} else
						{
							presentList.add((Integer) f1.get(attendance));
						}

					} catch (Exception e)
					{
						System.out.println(e);
					}
				}
				System.out.println("size.................." + sudentList.size());
				for (AttendanceStudentDTO studData : sudentList)
				{
					System.out.println("student data.............." + studData);
					if (studentPresentLecture.containsKey(studData.getId()))// check
																			// student
																			// id
																			// all
																			// ready
																			// present
																			// or
																			// not
					{

						Map<Integer, Integer> map = studentPresentLecture.get(studData.getId());
						if (map.containsKey(attendance.getComStaffSubject().getAcademicSubject().getSub_id()))
						{

							if (presentList.contains(studData.getId()))
							{
								map.put(attendance.getComStaffSubject().getAcademicSubject().getSub_id(),
										map.get(attendance.getComStaffSubject().getAcademicSubject().getSub_id()) + 1);
							} else
							{
								map.put(attendance.getComStaffSubject().getAcademicSubject().getSub_id(),
										map.get(attendance.getComStaffSubject().getAcademicSubject().getSub_id()));

							}
						} else
						{

							HashMap<Integer, Integer> subCount = new HashMap<>();
							if (presentList.contains(studData.getId()))
							{

								studentPresentLecture.get(studData.getId())
										.put(attendance.getComStaffSubject().getAcademicSubject().getSub_id(), 1);
							} else
							{

								System.out.println("present List........."
										+ attendance.getComStaffSubject().getAcademicSubject().getSub_id());
								// studentPresentLecture.put(studData.getId(),
								// null);
								studentPresentLecture.get(studData.getId())
										.put(attendance.getComStaffSubject().getAcademicSubject().getSub_id(), 0);
							}

						}

					} else
					{
						Map<Integer, Integer> subCount = new HashMap<>();

						if (presentList.contains(studData.getId()))
						{
							subCount.put(attendance.getComStaffSubject().getAcademicSubject().getSub_id(), 1);
							studentPresentLecture.put(studData.getId(), subCount);
						} else
						{

							subCount.put(attendance.getComStaffSubject().getAcademicSubject().getSub_id(), 0);
							studentPresentLecture.put(studData.getId(), subCount);
						}
					}

				}

				if (subjCount.containsKey(attendance.getComStaffSubject().getAcademicSubject().getSub_id()))
				{
					subjCount.put(attendance.getComStaffSubject().getAcademicSubject().getSub_id(),
							subjCount.get(attendance.getComStaffSubject().getAcademicSubject().getSub_id()) + 1);
				} else
				{
					subjCount.put(attendance.getComStaffSubject().getAcademicSubject().getSub_id(), 1);

				}

			}

			// add student and present lecture count
			int displaySubCounter = 1;
			Iterator<Entry<Integer, Map<Integer, Integer>>> parent = studentPresentLecture.entrySet().iterator();
			int srno = 1;
			int cellno = 8;
			List<Integer> list = new ArrayList();
			while (parent.hasNext())
			{

				Entry<Integer, Map<Integer, Integer>> parentPair = parent.next();
				Iterator<Entry<Integer, Integer>> child = (parentPair.getValue()).entrySet().iterator();
				Map<Integer, Integer> subjectAndTotal = new HashMap<>();
				row = sheet.createRow(cellno);
				cell = row.createCell(0);
				cell.setCellValue(srno);

				ComClientName clientName = session.get(ComClientName.class, parentPair.getKey());

				Criteria cr = getCriteriaForSelect(Student.class);
				cr.createAlias("comClientName", "comClientName");
				cr.setProjection(Projections.projectionList().add(Property.forName("comClientName.id"), "id")
						.add(Property.forName("comClientName.firstName"), "firstName")
						.add(Property.forName("comClientName.middleName"), "middleName")
						.add(Property.forName("comClientName.lastName"), "lastName")
						.add(Property.forName("rollNo"), "rollNo"));
				cr.add(Restrictions.eq("comClientName.id", parentPair.getKey()));
				cr.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
				cr.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));
				ComClientNameDTO comClientNameDTO = (ComClientNameDTO) cr.uniqueResult();

				cell = row.createCell(0);
				cell.setCellValue(comClientNameDTO.getRollNo());
				cell = row.createCell(1);
				cell.setCellValue(comClientNameDTO.getFirstName() + " " + comClientNameDTO.getMiddleName() + " "
						+ comClientNameDTO.getLastName());

				int celltotal = 2;
				int presentTotal = 0;
				while (child.hasNext())
				{

					Map.Entry childPair = child.next();
					cell = row.createCell(celltotal);
					cell.setCellValue((Integer) childPair.getValue());
					presentTotal = presentTotal + (Integer) childPair.getValue();

					// use for add subject name and total number subject
					if (displaySubCounter == 1)
					{

						list.add((Integer) childPair.getKey());
					}

					celltotal++;

				}
				displaySubCounter++;
				cell = row.createCell(celltotal);
				cell.setCellValue(presentTotal);
				cellno++;
				srno++;
			}
			row = sheet.createRow(5);
			cell = row.createCell(0);
			cell.setCellValue("Sr No.");
			cell = row.createCell(1);
			AcademicSubject academicSubject = null;
			cell.setCellValue("Name of Student");
			int cellCount = 2;
			// add subject name........
			for (Integer integer : list)
			{

				academicSubject = new AcademicSubject();
				academicSubject = getAcadamicSubject(integer);
				cell = row.createCell(cellCount);
				cell.setCellValue(academicSubject.getSubject_name());
				cellCount++;
			}
			cell = row.createCell(cellCount);
			cell.setCellValue("Total");

			// total subject count
			double totalLec = 0;
			int cellCount1 = 2;
			row = sheet.createRow(6);
			for (Integer integer : list)
			{
				cell = row.createCell(cellCount1);
				cell.setCellValue(subjCount.get(integer));
				cellCount1++;
				totalLec = totalLec + subjCount.get(integer);
			}
			cell = row.createCell(cellCount1);
			cell.setCellValue(totalLec);

			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			try
			{
				wb.write(outByteStream);
				byte[] outArray = outByteStream.toByteArray();
				responses.setContentType("application/ms-excel");
				responses.setContentLength(outArray.length);
				responses.setHeader("Expires:", "0"); // eliminates browser
														// caching
				responses.setHeader("Content-Disposition", "attachment; filename=PracticalCumulativeAttendance.xls");
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

	}

	@Override

	public AttendancePunch addStudentPunch(AttendancePunch attendancePunch1)
	{
		try
		{
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			// Set time fields to zero
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			date = cal.getTime();
			Criteria cr = getCriteriaForSelect(AttendancePunch.class);
			cr.add(Restrictions.eq("tdate", date));
			cr.add(Restrictions.eq("studId", attendancePunch1.getStudId()));
			AttendancePunch attendancePunch12 = (AttendancePunch) cr.uniqueResult();
			if (attendancePunch12 == null)
			{
				attendancePunch1.setTdate(date);
				return (AttendancePunch) save(attendancePunch1);
			} else
			{

				attendancePunch1.setPid(attendancePunch12.getPid());
				attendancePunch1.setTdate(date);
				return (AttendancePunch) save(attendancePunch1);

			}

		} catch (Exception e)
		{
			// TODO: handle exception
			return attendancePunch1;
		}
	}

	@Override

	public AttendancePunch addStudentPunch(Integer clientID)
	{
		// TODO Auto-generated method stub
		try
		{
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			// Set time fields to zero
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Date datenew = cal.getTime();

			System.out.println("date ...." + datenew);
			Criteria cr = getCriteriaForSelect(AttendancePunch.class);
			cr.add(Restrictions.eq("tdate", datenew));
			cr.add(Restrictions.eq("studId", clientID));
			AttendancePunch attendancePunch1 = (AttendancePunch) cr.uniqueResult();

			if (attendancePunch1 == null)
			{
				attendancePunch.setCheckIn(false);
				attendancePunch.setCheckOut(false);

				return attendancePunch;
			} else
			{
				return attendancePunch1;
			}

		} catch (Exception e)
		{
			System.out.println("Exp....." + e);
			// TODO: handle exception
			attendancePunch.setCheckIn(false);
			attendancePunch.setCheckOut(false);
			return attendancePunch;

		}

	}

	@Override

	public AttendancePunch getStudentPunchCount(AttendancePunch attendancePunch, int id)
	{

		try
		{
			Date date = attendancePunch.getTdate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			// Set time fields to zero
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Date datenew = cal.getTime();

			// Count present Student
			Criteria cr = getCriteriaForSelect(AttendancePunch.class);

			// Count total Student....
			Criteria cr1 = getCriteriaForSelect(Student.class);
			if (id == 1)
			{
				cr.add(Restrictions.eq("year", attendancePunch.getYear()));
				cr.add(Restrictions.eq("div", attendancePunch.getDiv()));
				cr.add(Restrictions.eq("dept", attendancePunch.getDept()));

				cr1.add(Restrictions.eq("year", attendancePunch.getYear()));
				cr1.add(Restrictions.eq("standard", Integer.parseInt(attendancePunch.getDiv())));
				cr1.add(Restrictions.eq("branch", attendancePunch.getDept()));
			} else if (id == 2)
			{
				cr.add(Restrictions.eq("dept", attendancePunch.getDept()));
				cr1.add(Restrictions.eq("branch", attendancePunch.getDept()));
			} else if (id == 3)
			{
				cr.add(Restrictions.eq("year", attendancePunch.getYear()));
				cr1.add(Restrictions.eq("year", attendancePunch.getYear()));
			}

			cr.add(Restrictions.eq("tdate", datenew));
			cr.setProjection(Projections.rowCount());
			long count = (long) cr.uniqueResult();

			cr1.setProjection(Projections.rowCount());
			long toatal = (long) cr1.uniqueResult();

			attendancePunch.setYear((int) count);
			attendancePunch.setDept((int) toatal);
			return attendancePunch;
		} catch (Exception e)
		{
			attendancePunch.setYear(0);
			attendancePunch.setDept(0);
			return attendancePunch;
		}

	}

	@Override
	public Groups createSubjectGroup(Integer subjectId, Integer clientID, Integer div)
	{

		System.out.println(" div is " + div);
		Session session = this.sessionFactory.getCurrentSession();
		AcademicSubject academicSubject = session.get(AcademicSubject.class, subjectId);

		Criteria cr = getCriteriaForSelect(Student.class);
		cr.createAlias("comClientName", "comClientName");
		cr.setProjection(Projections.projectionList().add(Property.forName("comClientName.id"), "id"));
		cr.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		cr.add(Restrictions.eq("branch", academicSubject.getDepartment().getDep_id()));
		cr.add(Restrictions.eq("year", findYearUsingSem(academicSubject.getSemister())));
		cr.add(Restrictions.eq("standard", div));
		cr.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));

		List<ComClientNameDTO> list = cr.list();
		if (list.isEmpty())
		{
			return null;
		} else
		{
			try
			{

				Integer[] studId = new Integer[list.size()];
				int i = 0;
				for (ComClientNameDTO comClientNameDTO : list)
				{
					studId[i] = comClientNameDTO.getId();
					System.out.println(comClientNameDTO.getId());
					i++;
				}
				Groups groups = new Groups();
				groups.setGroupName("Div " + div + " SubjectGroup:" + academicSubject.getSubject_name());
				groups.setGroupIncharge_1(clientID);
				groups.setGroupIncharge_2(clientID);
				groups.setDepartment(academicSubject.getDepartment().getDep_id());
				groups.setGroupfor(11);
				groups.setGroupType(2);
				return saveGroupsObj(groups, studId);
			} catch (Exception e)
			{
				return null;
			}
		}

	}

	@Override
	public boolean checkTermsAndCondition(Integer clientID)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			ComClientName comClientName = session.get(ComClientName.class, clientID);
			if (comClientName != null)
			{

				if (comClientName.isTermsCondition() != true)
				{
					comClientName.setTermsCondition(true);
					return true;
				} else
				{
					return false;
				}
			} else
			{
				return false;
			}

		} catch (Exception e)
		{
			// System.out.println(e);
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Help> commonDAOgetHelpUrl(int id, int menuid)
	{
		// System.out.println("roleid" + id + "menuid" + menuid);

		try
		{
			Criteria criteria = getCriteriaForSelect(Help.class);
			criteria.add(Restrictions.eq("roleId", id));
			criteria.add(Restrictions.eq("menuId", menuid));
			criteria.addOrder(Order.asc("orderID"));
			// System.out.println("helplist" + helpurlist);
			return criteria.list();

		} catch (Exception e)
		{
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Groups createClassGroup(Integer staffId, Integer yearId, Integer div)
	{
		// System.out.println("all date will come in dao class..." + staffId +
		// ".." +
		// yearId + ".." + div);f
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria1 = getCriteriaForSelect(Staff.class);
		criteria1.add(Restrictions.eq("comClientName.id", staffId));
		Staff staff = (Staff) criteria1.uniqueResult();

		Criteria criteria2 = getCriteriaForSelect(Department.class);
		criteria2.add(Restrictions.eq("dep_id", staff.getDepartment()));
		Department dept = (Department) criteria2.uniqueResult();

		Criteria cr = getCriteriaForSelect(Student.class);
		cr.createAlias("comClientName", "comClientName");
		cr.setProjection(Projections.projectionList().add(Property.forName("comClientName.id"), "id"));
		cr.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		cr.add(Restrictions.eq("branch", staff.getDepartment()));
		cr.add(Restrictions.eq("year", yearId));
		cr.add(Restrictions.eq("standard", div));
		cr.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));
		List<ComClientNameDTO> list = cr.list();

		if (list.isEmpty())
		{
			return null;
		} else
		{
			try
			{

				Integer[] studId = new Integer[list.size()];
				int i = 0;
				for (ComClientNameDTO comClientNameDTO : list)
				{
					studId[i] = comClientNameDTO.getId();
					// System.out.println(comClientNameDTO.getId());
					i++;
				}
				Groups groups = new Groups();
				groups.setGroupName(getYearName(yearId) + " " + dept.getDep_name() + " Class Group Div:" + div);
				// groups.setGroupIncharge_1(staffId);
				groups.setGroupIncharge_2(staffId);
				groups.setDepartment(staff.getDepartment());
				groups.setGroupfor(11);
				groups.setGroupType(4);
				return saveGroupsObj(groups, studId);
			} catch (Exception e)
			{
				return null;
			}
		}

	}

	@Override

	public Groups createStudentGroup(Groups group, Integer id)
	{
		// TODO Auto-generated method stub
		try
		{
			Integer[] studId = new Integer[1];
			studId[0] = id;
			group.setGroupfor(11);
			group.setGroupId(null);
			group.setStudCreated(true);
			return saveGroupsObj(group, studId);

		} catch (Exception e)
		{

			return null;
		}
	}

	@Override
	public List<Groups> joinGroupList(int id)
	{
		Criteria cr = getCriteriaForSelect(Groups.class);
		cr.add(Restrictions.eq("studCreated", true));
		cr.setProjection(Projections.projectionList().add(Property.forName("groupId"), "groupId")
				.add(Property.forName("groupName"), "groupName").add(Property.forName("createdBy"), "createdBy")
				.add(Property.forName("groupType"), "groupType"));
		cr.setResultTransformer(Transformers.aliasToBean(Groups.class));

		List<Groups> grouplist = cr.list();
		List<Groups> list = new ArrayList<>();
		for (Groups groups : grouplist)
		{
			Criteria criteria = getCriteriaForSelect(GroupMembers.class);
			criteria.add(Restrictions.eq("groups.groupId", groups.getGroupId()));
			criteria.add(Restrictions.eq("comClientName.id", id));

			if (criteria.uniqueResult() == null)
			{
				list.add(groups);
			}
		}

		return list;
	}

	@Override

	public boolean UpdateMemberGroup(Groups group, int id)
	{
		Object obj = new Object();
		GroupMembers gm = new GroupMembers();
		ComClientName c = new ComClientName();
		c.setId(id);
		gm.setGroups(group);
		gm.setComClientName(c);
		obj = save(gm);

		if (obj != null)
		{
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public AttendancePunch studPunchAdd(int punchId, int studId)
	{
		Criteria criteria = getCriteriaForSelect(Student.class);
		criteria.add(Restrictions.eq("comClientName.id", studId));
		Student student = (Student) criteria.uniqueResult();

		AttendancePunch attendancePunch = new AttendancePunch();
		try
		{
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			// Set time fields to zero
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			date = cal.getTime();
			Criteria cr = getCriteriaForSelect(AttendancePunch.class);
			cr.add(Restrictions.eq("tdate", date));
			cr.add(Restrictions.eq("studId", studId));
			AttendancePunch attendancePunch12 = (AttendancePunch) cr.uniqueResult();
			if (attendancePunch12 == null)
			{
				attendancePunch.setTdate(date);
				attendancePunch.setStudId(studId);
				attendancePunch.setDept(student.getBranch());
				attendancePunch.setDiv(Integer.toString(student.getStandard()));
				attendancePunch.setCheckIn(true);
				attendancePunch.setCheckOut(false);
				attendancePunch.setYear(student.getYear());
				return (AttendancePunch) save(attendancePunch);
			} else
			{

				if (punchId == 2)
				{

					attendancePunch12.setCheckOut(true);
					return (AttendancePunch) save(attendancePunch12);

				} else
				{

					return attendancePunch12;
				}
			}

		} catch (Exception e)
		{
			// TODO: handle exception
			return attendancePunch;
		}
	}

	@Override
	public String getmenupage(Integer institute_id, Integer menuid)
	{
		// TODO Auto-generated method stub

		String str3 = String.valueOf(institute_id);
		Session session = this.sessionFactory.getCurrentSession();
		MenuList menulist = session.get(MenuList.class, menuid);
		Criteria criteria = getCriteriaForSelect(Html_menu.class);
		criteria.add(Restrictions.eq("menu_name", menulist.getValue()));
		criteria.add(Restrictions.eq("instituteId", str3));
		Html_menu menulistpg = (Html_menu) criteria.uniqueResult();
		return menulistpg.getHtmlstring();
	}

	@Override
	public List<CollegeMenuList> college_DynamicMenu()
	{
		Criteria criteria = getCriteriaForSelect(CollegeMenuList.class);
		return criteria.list();
	}
	// TODO Auto-generated method stub

	@Override
	public List<MianManuList> getMainMenuList()
	{
		Criteria criteria = getCriteriaForSelect(MianManuList.class);
		return criteria.list();
	}

	@Override
	public void instituteDataAdd(InstituteInfoMaster instituteInfoMaster, int[] menuiId)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(InstituteInfoMaster.class);
			criteria.add(Restrictions.eq("instCode", instituteInfoMaster.getInstCode()));
			if (criteria.uniqueResult() == null)
			{
				instituteInfoMaster.setInstituteId(Integer.toString(instituteInfoMaster.getInstCode()));
				instituteInfoMaster.setRecordStatus(Constants.ACTIVE_RECORD_STATUS);
				session.save(instituteInfoMaster);

				CollegeMenuList collegeMenuList = null;
				if (menuiId.length >= 1)
				{
					for (int i : menuiId)
					{
						collegeMenuList = new CollegeMenuList();
						MianManuList manuList = session.get(MianManuList.class, i);
						collegeMenuList.setMainMenuList(manuList);
						collegeMenuList.setInstituteId(Integer.toString(instituteInfoMaster.getInstCode()));
						collegeMenuList.setRecordStatus(Constants.ACTIVE_RECORD_STATUS);
						session.save(collegeMenuList);

					}
				}

			}
		} catch (Exception e)
		{
			System.out.println(e);
		}

	}

	@Override
	public List<InstituteInfoMaster> getInstituteInfoDetailsList()
	{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(InstituteInfoMaster.class);
		System.out.println("......." + criteria.list());
		return criteria.list();
	}

	@Override
	public List<CollegeMenuList> getInstitutemenuInfoById(Integer instid)
	{
		// TODO Auto-generated method stub
		System.out.println("college id is" + instid);

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(CollegeMenuList.class);
		criteria.setProjection(Projections.projectionList().add(Property.forName("mainMenuList.id"), "id"));
		criteria.add(Restrictions.eq(Constants.INSTITUTE_VARIABLE_NAME, Integer.toString(instid)));
		criteria.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		criteria.setResultTransformer(Transformers.aliasToBean(CollegeMenuList.class));
		List<CollegeMenuList> list = criteria.list();
		return list;

	}

	@Override
	public void updateInstitute(InstituteInfoMaster instituteInfoMaster, int[] menuiId)
	{
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		instituteInfoMaster.setInstituteId(Integer.toString(instituteInfoMaster.getInstCode()));
		instituteInfoMaster.setRecordStatus(Constants.ACTIVE_RECORD_STATUS);
		session.update(instituteInfoMaster);

		if (menuiId != null)
		{
			List idlist = new ArrayList<>();
			for (int i : menuiId)
			{
				idlist.add(i);
			}

			Criteria criteria = session.createCriteria(CollegeMenuList.class);
			criteria.add(Restrictions.eq(Constants.INSTITUTE_VARIABLE_NAME,
					Integer.toString(instituteInfoMaster.getInstCode())));

			List<CollegeMenuList> list = criteria.list();
			Set<Integer> menuId = new HashSet<>();
			for (CollegeMenuList collegeMenuList : list)
			{
				if (idlist.contains(collegeMenuList.getMainMenuList().getId()))
				{
					menuId.add(collegeMenuList.getMainMenuList().getId());
				} else if (collegeMenuList.getRecordStatus().equals("A"))
				{
					collegeMenuList.setRecordStatus(Constants.DELETED_RECORD_STATUS);
				}
			}
			idlist.forEach(id ->
			{
				menuId.add((Integer) id);
			});

			for (Integer id : menuId)
			{
				CollegeMenuList collegeMenuList1 = null;
				CollegeMenuList collegeMenuList2 = null;
				Session session2 = sessionFactory.getCurrentSession();
				Criteria criteria1 = session2.createCriteria(CollegeMenuList.class);
				criteria1.add(Restrictions.eq(Constants.INSTITUTE_VARIABLE_NAME,
						Integer.toString(instituteInfoMaster.getInstCode())));
				criteria1.add(Restrictions.eq("mainMenuList.id", id));
				collegeMenuList1 = (CollegeMenuList) criteria1.uniqueResult();
				if (criteria1.uniqueResult() == null)
				{
					collegeMenuList2 = new CollegeMenuList();
					MianManuList manuList = session.get(MianManuList.class, id);
					collegeMenuList2.setMainMenuList(manuList);
					collegeMenuList2.setInstituteId(Integer.toString(instituteInfoMaster.getInstCode()));
					collegeMenuList2.setRecordStatus(Constants.ACTIVE_RECORD_STATUS);
					session.save(collegeMenuList2);

				} else if (collegeMenuList1.getRecordStatus().equals("D"))
				{
					collegeMenuList1.setRecordStatus(Constants.ACTIVE_RECORD_STATUS);
				}
				session2.flush();
			}

		} else
		{

			Criteria criteria = session.createCriteria(CollegeMenuList.class);
			criteria.add(Restrictions.eq(Constants.INSTITUTE_VARIABLE_NAME,
					Integer.toString(instituteInfoMaster.getInstCode())));
			criteria.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
			List<CollegeMenuList> list = criteria.list();
			for (CollegeMenuList collegeMenuList : list)
			{
				collegeMenuList.setRecordStatus(Constants.DELETED_RECORD_STATUS);
			}

		}

	}

	@Override
	public List<Navbar_MenuList> getNabBar_MenuList(Integer institute_id)
	{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria2 = session.createCriteria(MianManuList.class);
		List<MianManuList> manuList = criteria2.list();

		Criteria criteria = session.createCriteria(CollegeMenuList.class);
		criteria.setProjection(Projections.projectionList().add(Property.forName("mainMenuList.id"), "id"));
		criteria.add(Restrictions.eq(Constants.INSTITUTE_VARIABLE_NAME, Integer.toString(institute_id)));
		criteria.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		criteria.setResultTransformer(Transformers.aliasToBean(CollegeMenuList.class));
		List<CollegeMenuList> list = criteria.list();
		List idlist = new ArrayList<>();
		for (CollegeMenuList collegeMenuList : list)
		{
			idlist.add(collegeMenuList.getId());
		}

		List<Navbar_MenuList> namMenuList = new ArrayList<>();
		Navbar_MenuList navbar_MenuList = null;
		for (MianManuList manuList1 : manuList)
		{
			navbar_MenuList = new Navbar_MenuList();
			if (idlist.contains(manuList1.getId()))
			{
				navbar_MenuList.setStaus(true);
				navbar_MenuList.setMenuName(manuList1.getMenu_Name());

			} else
			{
				navbar_MenuList.setStaus(false);
				navbar_MenuList.setMenuName(manuList1.getMenu_Name());
			}
			namMenuList.add(navbar_MenuList);
		}
		return namMenuList;
	}

	@Override
	public List<String> getDivisionAtten_Report(Integer deptId, Integer yearId)
	{

		Criteria cr = getCriteriaForSelect(Student.class);
		cr.setProjection(Projections.projectionList().add(Projections.groupProperty("standard")));
		cr.add(Restrictions.eq("branch", deptId));
		cr.add(Restrictions.eq("year", yearId));
		List<Integer> standard = cr.list();
		List<String> division = new ArrayList<>();
		for (Integer id : standard)
		{
			division.add(findDivisionusingStanderd(id));
		}
		return division;
	}

	@Override
	public List<String> getPractical_Batch_forAtt_Report(Integer deptId, Integer yearId, String divId)
	{
		Criteria cr = getCriteriaForSelect(Student.class);
		cr.setProjection(Projections.projectionList().add(Projections.groupProperty("batch")));
		cr.add(Restrictions.eq("branch", deptId));
		cr.add(Restrictions.eq("year", yearId));
		cr.add(Restrictions.eq("standard", findStanderdUSingDivision(divId)));
		List<String> batchList = cr.list();
		return batchList;
	}

	@Override

	public List<AcademicSubject> getassignsubject(Integer staff_id)
	{

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(ComStaffSubject.class);

		criteria.createAlias("academicSubject", "academicSubject");

		criteria.setProjection(
				Projections.projectionList().add(Property.forName("academicSubject.subject_name"), "subject_name")
						.add(Property.forName("academicSubject.semister"), "semister")
						.add(Property.forName("academicSubject.sem_year"), "sem_year"));
		criteria.setResultTransformer(Transformers.aliasToBean(AcademicSubject.class));

		criteria.add(Restrictions.eq("clientName.id", staff_id));

		List<AcademicSubject> academicSubject = criteria.list();

		System.out.println("prinsubjects" + academicSubject);

		return academicSubject;

	}

	@Override

	public List<Groups> getgroup(Integer clientid)
	{
		// TODO Auto-generated method stub

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Groups.class);
		criteria.add(Restrictions.eq("groupIncharge_2", clientid));
		List<Groups> group = criteria.list();
		System.out.println("grouplists" + group);
		return group;
	}

	// result url start

	@Override
	public String getResultreport(Integer AcadamicYear, Integer deptid, Integer semId)
	{

		System.out.println("come in dao class" + AcadamicYear + "departmentid" + deptid + "semesterid" + semId);
		Session session = sessionFactory.getCurrentSession();
		String year = "";
		System.out.println("acdemics year" + AcadamicYear);
		Criteria cr = session.createCriteria(AcademicSubject.class);
		cr.add(Restrictions.eq("department.dep_id", deptid));
		if ((semId % 2) == 0)
		{
			System.out.println("come in if conditon subject");
			System.out.println("semid" + semId);
			if (semId == 2)
			{
				String sem1 = "1";
				String sem2 = "2";
				cr.add(Restrictions.between("semister", sem1, sem2));
				System.out.println("testing" + cr.list());
			} else if (semId == 4)
			{
				String sem3 = "3";
				String sem4 = "4";
				cr.add(Restrictions.between("semister", sem3, sem4));
				System.out.println("testing" + cr.list());
			} else if (semId == 6)
			{
				String sem5 = "5";
				String sem6 = "6";
				cr.add(Restrictions.between("semister", sem5, sem6));
				System.out.println("testing" + cr.list());
			} else if (semId == 8)
			{
				String sem7 = "7";
				String sem8 = "8";
				cr.add(Restrictions.between("semister", sem7, sem8));
				System.out.println("testing" + cr.list());
			}
		} else
		{
			String sem = Integer.toString(semId);
			cr.add(Restrictions.eq("semister", sem));
			System.out.println("testing2" + cr.list());
		}

		String staffcollegename = null;
		List<AcademicSubject> academicSubjects = cr.list();

		Criteria criteria = session.createCriteria(Department.class);
		criteria.add(Restrictions.eq("dep_id", deptid));
		Department deptname1 = (Department) criteria.uniqueResult();

		if (semId == 1 || semId == 2)
		{
			year = "F.E.";
		} else if (semId == 4 || semId == 3)
		{
			year = "S.E.";

		} else if (semId == 5 || semId == 6)
		{
			year = "T.E.";

		} else
		{
			year = "B.E.";
		}
		System.out.println("years" + year);
		Criteria cr4 = getCriteriaForSelect(ResultStudent.class);
		cr4.add(Restrictions.eq("academiyr", year));
		cr4.add(Restrictions.eq("year", AcadamicYear));
		cr4.add(Restrictions.eq("branch", deptid));
		List<ResultStudent> deptlist = cr4.list();

		for (ResultStudent resultStudent : deptlist)
		{
			staffcollegename = resultStudent.getStudcollegename();
			System.out.println("depname" + staffcollegename);
			break;
		}

		System.out.println("academicssub" + academicSubjects.size());

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
		cell.setCellValue(staffcollegename);

		row = sheet.createRow(1);
		cell = row.createCell(0);

		cell.setCellValue("Acadamic Year");
		cell = row.createCell(1);
		cell.setCellValue(AcadamicYear);

		row = sheet.createRow(2);
		cell = row.createCell(0);

		cell.setCellValue("Semester");
		cell = row.createCell(1);
		cell.setCellValue(semId);

		row = sheet.createRow(3);
		cell = row.createCell(0);

		cell.setCellValue("Department");
		cell = row.createCell(1);
		cell.setCellValue(deptname1.getDep_name());

		row = sheet.createRow(4);
		cell = row.createCell(0);
		cell.setCellValue("Sr.No");

		cell = row.createCell(1);
		cell.setCellValue("Subject Name");

		cell = row.createCell(2);
		cell.setCellValue("Total Student");

		cell = row.createCell(3);
		cell.setCellValue("Passed Student");

		cell = row.createCell(4);
		cell.setCellValue("Fail Student");

		cell = row.createCell(5);
		cell.setCellValue("Subject Marks%");

		int count = 5;
		int srno = 1;
		List<Object[]> test = new ArrayList<>();
		for (AcademicSubject academicSubject : academicSubjects)
		{

			Criteria cr1 = getCriteriaForSelect(ResultMarksheet.class);
			cr1.setProjection(Projections.projectionList().add(Projections.groupProperty("subresult"))
					.add(Projections.rowCount()));
			cr1.add(Restrictions.ne("grade", "F"));
			cr1.add(Restrictions.eq("year", AcadamicYear));
			cr1.add(Restrictions.eq("subresult.sub_id", academicSubject.getSub_id()));
			test = cr1.list();

			row = sheet.createRow(count);
			cell = row.createCell(0);
			cell.setCellValue(srno);

			cell = row.createCell(1);
			cell.setCellValue(academicSubject.getSubject_name());
			System.out.println("last.........sub lsist...." + test);
			try
			{

				for (Object[] objects : test)
				{
					System.out.println("object sub test...." + objects);

					DecimalFormat f = new DecimalFormat("##.00");

					System.out.println("pass student...." + objects[1]);

					System.out.println("percentage......" + f.format((((Long) objects[1]).floatValue()
							/ (Long) getTotalStudentAttendTheExam(academicSubject, AcadamicYear, null) * 100)));

					cell = row.createCell(2);
					cell.setCellValue((Long) getTotalStudentAttendTheExam(academicSubject, AcadamicYear, null));

					cell = row.createCell(3);
					cell.setCellValue(objects[1].toString());

					Long toatl = (Long) getTotalStudentAttendTheExam(academicSubject, AcadamicYear, null);
					cell = row.createCell(4);
					cell.setCellValue(toatl - ((Long) objects[1]).floatValue());

					cell = row.createCell(5);
					cell.setCellValue(f.format((((Long) objects[1]).floatValue() / toatl * 100)));

				}

			} catch (Exception e)
			{
				// TODO: handle exception
				System.out.println("eee" + e);
			}
			count++;
			srno++;
		}

		// write it as an excel attachment
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		try
		{
			wb.write(outByteStream);
			byte[] outArray = outByteStream.toByteArray();
			responses.setContentType("application/ms-excel");
			responses.setContentLength(outArray.length);
			responses.setHeader("Expires:", "0"); // eliminates browser caching
			responses.setHeader("Content-Disposition", "attachment; filename=Result_Report" + AcadamicYear + ".xls");
			OutputStream outStream = responses.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
			outStream.close();

		} catch (Exception e)
		{
			System.out.println("excepotion......" + e);
		}
		System.out.println("report downloadddddddddddddddddddddddddddddddddd");
		return "null";
	}

	@Override
	public String getSubjectelist(MultipartFile subjectfile) throws Exception
	{

		String msg = "error while rading file. ";
		ResulSubjectlist subjectlist = null;
		XSSFWorkbook wb;
		System.out.println("1");
		wb = new XSSFWorkbook(subjectfile.getInputStream());
		XSSFSheet sheet;
		// System.out.println("Number of sheet............" +
		// wb.getNumberOfSheets());
		Row row;
		sheet = wb.getSheetAt(2);
		// System.out.println("Sheet Count............" +
		// sheet.getLastRowNum());
		if (sheet.getLastRowNum() > 0)
		{
			System.out.println("2");
			for (int i = 1; i <= sheet.getLastRowNum(); i++)
			{
				row = sheet.getRow(i);
				subjectlist = new ResulSubjectlist();

				for (int j = 1; j < row.getLastCellNum(); j++)
				{
					subjectlist.setDep_id((int) row.getCell(1).getNumericCellValue());
					subjectlist.setSemester((int) row.getCell(2).getNumericCellValue());
					int subcode = (int) row.getCell(3).getNumericCellValue();
					subjectlist.setSubjectcode(Integer.toString(subcode));
					subjectlist.setSubjectname(row.getCell(4).toString());
				}
				// System.out.println("list" + subjectList);
				Criteria criteria = getCriteria(ResulSubjectlist.class);
				criteria.add(Restrictions.eq("subjectcode", subjectlist.getSubjectcode()));
				if (criteria.uniqueResult() == null)
				{
					save(subjectlist);
				}

				msg = "Congratulation..! File Upload Successfully... ";
			}
		} else
		{
			// System.out.println("last");
			msg = "Sorry..! Your file is empty cant process.";
		}
		wb.close();
		System.out.println("last1");
		return msg;
	}

	@Override
	public boolean saveResulMarksheet(ResultMarksheet sheet, String subjectCode)
	{

		Boolean b = false;
		Session session = this.sessionFactory.getCurrentSession();
		ResultMarksheet resultMarksheet = new ResultMarksheet();

		System.out.println("spring termwork" + resultMarksheet.getTw_obtained());

		System.out.println("branchdaooooooooooooooo" + sheet.getBranch());
		String bid = sheet.getBranch();
		System.out.println("branchiddaooooooooooooooooooooooooooooo" + bid);

		AcademicSubject sublist = getSubjectForResult(subjectCode, bid);

		System.out.println("subbbbbbbbbbbbbbb" + sublist);

		if (sublist != null)
		{

			Criteria cr = getCriteriaForSelect(ResultMarksheet.class);
			cr.add(Restrictions.eq("subresult.sub_id", sublist.getSub_id()));
			cr.add(Restrictions.eq("studresult.studId", sheet.getStudresult().getStudId()));
			resultMarksheet = (ResultMarksheet) cr.uniqueResult();

			if (resultMarksheet != null)
			{
				if (resultMarksheet.getUpdateStatus() != true)
				{
					resultMarksheet.setTw_obtained(sheet.getTw_obtained());
					resultMarksheet.setPr_obtained(sheet.getPr_obtained());
					resultMarksheet.setOl_obtained(sheet.getOl_obtained());
					resultMarksheet.setUpdateStatus(true);
					save(resultMarksheet);
				}

			} else
			{
				System.out.println("come in else  condition");
				sheet.setSubresult(sublist);
				sheet.setUpdateStatus(false);
				save(sheet);
			}
		}
		session.flush();
		return b;
	}

	public AcademicSubject getSubjectForResult(String subCode, String bid)
	{
		System.out.println("bhausaheb" + subCode);
		Session session = this.sessionFactory.getCurrentSession();
		Criteria sub = session.createCriteria(AcademicSubject.class);
		sub.add(Restrictions.eq("subject_code", subCode));
		sub.add(Restrictions.eq("department.dep_id", Integer.parseInt(bid)));
		return (AcademicSubject) sub.uniqueResult();
	}

	public ResultStudent getresultstud(String prnNo)
	{
		System.out.println("ppppppppp" + prnNo);
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(ResultStudent.class);
		c.add(Restrictions.eq("prnumber", prnNo));
		System.out.println("syssssssss" + c.uniqueResult());
		return (ResultStudent) c.uniqueResult();

	}

	public ResultMarksheet getresultmarksheet(AcademicSubject sub, ResultStudent stud)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(AcademicSubject.class);
		cr.add(Restrictions.eq("studresult.studId", stud.getStudId()));
		cr.add(Restrictions.eq("subresult.sub_id", sub.getSubject_code()));
		System.out.println("twomethod" + cr.list());
		return (ResultMarksheet) cr.uniqueResult();
	}

	@Override

	public ResultStudent saveStudentResult(ResultStudent student)
	{

		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(ResultStudent.class);
		cr.add(Restrictions.eq("prnumber", student.getPrnumber()));

		ResultStudent stud = (ResultStudent) cr.uniqueResult();
		System.out.println("studeeeeeeeeeeeeeeeeeee" + stud);

		ResultStudent studentObj = new ResultStudent();
		if (stud != null)
		{
			System.out.println("come in if conditon" + stud);
			return stud;
		} else
		{
			System.out.println("come in else condition" + save(student));
			studentObj = (ResultStudent) save(student);
		}

		System.out.println("studet bean................." + studentObj);

		return studentObj;
	}

	@Override

	public void getResultReportStudent(int aid, int bid, int cid)
	{

		System.out.println("academciid" + aid);
		// TODO Auto-generated method stub
		String collegename = null;
		String year = "";
		int sem = 0;
		if (cid == 1 || cid == 2)
		{

			year = "F.E.";

		} else if (cid == 4 || cid == 3)
		{

			year = "S.E.";

		} else if (cid == 5 || cid == 6)
		{
			year = "T.E.";

		} else
		{
			year = "B.E.";
		}

		System.out.println("year...." + year);
		System.out.println("year...." + bid);

		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = getCriteriaForSelect(ResultStudent.class);
		cr.add(Restrictions.eq("academiyr", year));
		cr.add(Restrictions.eq("year", aid));
		cr.add(Restrictions.eq("branch", bid));
		List<ResultStudent> students = cr.list();

		for (ResultStudent resultStudent : students)
		{
			collegename = resultStudent.getStudcollegename();
			break;
		}
		Criteria departmentlist = session.createCriteria(Department.class);
		departmentlist.add(Restrictions.eq("dep_id", bid));
		Department deptname = (Department) departmentlist.uniqueResult();
		// System.out.println("student list............" + students);

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
		cell.setCellValue(collegename);

		row = sheet.createRow(1);
		cell = row.createCell(0);

		cell.setCellValue("Acadamic Year");
		cell = row.createCell(1);
		cell.setCellValue(aid);

		row = sheet.createRow(2);
		cell = row.createCell(0);

		cell.setCellValue("Semester");
		cell = row.createCell(1);
		cell.setCellValue(cid);

		row = sheet.createRow(3);
		cell = row.createCell(0);

		cell.setCellValue("Department");
		cell = row.createCell(1);
		cell.setCellValue(deptname.getDep_name());

		int rows = 6;
		int count = 1;
		int setsubcount = 2;
		int studid = 0;

		for (ResultStudent student : students)
		{

			Criteria c = session.createCriteria(ResultMarksheet.class);
			c.add(Restrictions.eq("studresult.studId", student.getStudId()));
			c.add(Restrictions.eq("year", aid));
			c.add(Restrictions.eq("semister", (cid % 2 == 0) ? 2 : 1));
			List<ResultMarksheet> list = c.list();

			row = sheet.createRow(rows);
			cell = row.createCell(0);
			cell.setCellValue(count);

			cell = row.createCell(1);
			cell.setCellValue(student.getPrnumber());

			cell = row.createCell(2);
			cell.setCellValue(student.getFirstName() + " " + student.getLastName());

			int i = 1;
			studid = student.getStudId();
			System.out.println("studentid..........." + studid);
			int count1 = 2;

			for (ResultMarksheet markSheet : list)
			{

				System.out.println("subject name........" + markSheet.getSubresult().getSubject_name());

				cell = row.createCell(++count1);
				cell.setCellValue(markSheet.getIn_obtained());

				cell = row.createCell(++count1);
				cell.setCellValue(markSheet.getTh_obtained());

				cell = row.createCell(++count1);
				cell.setCellValue(markSheet.getTw_obtained());

				cell = row.createCell(++count1);
				cell.setCellValue(markSheet.getPr_obtained());

				cell = row.createCell(++count1);
				cell.setCellValue(markSheet.getOl_obtained());

				cell = row.createCell(++count1);
				cell.setCellValue(markSheet.getTo_obtained());

				i++;
			}
			count++;
			rows++;
			setsubcount++;

		}
		Criteria c = session.createCriteria(ResultMarksheet.class);
		c.add(Restrictions.eq("studresult.studId", studid));
		c.add(Restrictions.eq("year", aid));
		c.add(Restrictions.eq("semister", (cid % 2 == 0) ? 2 : 1));
		List<ResultMarksheet> list = c.list();

		row = sheet.createRow(4);
		cell = row.createCell(0);
		cell.setCellValue("Sr No");

		cell = row.createCell(1);
		cell.setCellValue("PRN Number");

		cell = row.createCell(2);
		cell.setCellValue("Name of Student");

		int counttest = 4;
		for (ResultMarksheet markSheet : list)
		{
			// row = sheet.createRow(4);
			cell = row.createCell(counttest);
			cell.setCellValue(markSheet.getSubresult().getSubject_name());
			System.out.println("printsubjectbeforechange" + markSheet.getSubresult().getSubject_name());
			counttest += 6;
			System.out.println("countest" + counttest);
		}
		// write it as an excel attachment
		int var = 2;
		row = sheet.createRow(5);
		for (int i = 1; i <= list.size(); i++)
		{

			cell = row.createCell(++var);
			cell.setCellValue("Internal");

			cell = row.createCell(++var);
			cell.setCellValue("Theary");

			cell = row.createCell(++var);
			cell.setCellValue("Terwork");

			cell = row.createCell(++var);
			cell.setCellValue("practical");

			cell = row.createCell(++var);
			cell.setCellValue("Oral");

			cell = row.createCell(++var);
			cell.setCellValue("Total");

		}

		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		try
		{
			wb.write(outByteStream);
			byte[] outArray = outByteStream.toByteArray();
			responses.setContentType("application/ms-excel");
			responses.setContentLength(outArray.length);
			responses.setHeader("Expires:", "0"); // eliminates browser caching
			responses.setHeader("Content-Disposition", "attachment; filename=Student_Result_Report" + aid + ".xls");
			OutputStream outStream = responses.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
			outStream.close();

		} catch (Exception e)
		{
			System.out.println("excepotion......" + e);
		}

	}

	// First Report Coding
	/*
	 * @Override
	 *
	 * public void getResultReportStudent(int aid, int bid, int cid) {
	 * System.out.println("academciid" + aid); // TODO Auto-generated method stub
	 *
	 * String year = ""; int sem = 0; if (cid == 1 || cid == 2) { year = "F.E.";
	 *
	 * } else if (cid == 4 || cid == 3) { year = "S.E.";
	 *
	 * } else if (cid == 5 || cid == 6) { year = "T.E.";
	 *
	 * } else { year = "B.E."; }
	 *
	 * System.out.println("year...." + year); System.out.println("year...." + bid);
	 *
	 * Session session = this.sessionFactory.getCurrentSession(); Criteria cr =
	 * getCriteriaForSelect(ResultStudent.class);
	 * cr.add(Restrictions.eq("academiyr", year)); cr.add(Restrictions.eq("year",
	 * aid)); cr.add(Restrictions.eq("branch", bid)); List<ResultStudent> students =
	 * cr.list(); // System.out.println("student list............" + students);
	 *
	 * RequestAttributes requestAttributes =
	 * RequestContextHolder.getRequestAttributes(); HttpServletResponse responses =
	 * ((ServletRequestAttributes) requestAttributes).getResponse();
	 *
	 * HSSFWorkbook wb = new HSSFWorkbook(); HSSFSheet sheet = wb.createSheet();
	 * HSSFRow row = sheet.createRow(0); sheet.addMergedRegion(new
	 * CellRangeAddress(0, // first row (0-based) 0, // last row (0-based) 0, //
	 * first column (0-based) 6 // last column (0-based) )); row =
	 * sheet.createRow(0); HSSFCell cell = row.createCell(0); cell =
	 * row.createCell(0); cell.setCellValue("Student Result Report");
	 *
	 * row = sheet.createRow(1); cell = row.createCell(0);
	 *
	 * cell.setCellValue("Acadamic Year"); cell = row.createCell(1);
	 * cell.setCellValue(aid);
	 *
	 * row = sheet.createRow(2); cell = row.createCell(0);
	 *
	 * cell.setCellValue("Semester"); cell = row.createCell(1);
	 * cell.setCellValue(cid);
	 *
	 * row = sheet.createRow(4); cell = row.createCell(0);
	 * cell.setCellValue("Sr No");
	 *
	 * cell = row.createCell(1); cell.setCellValue("Name of Student");
	 *
	 * cell = row.createCell(2); cell.setCellValue("Subject Name");
	 *
	 * cell = row.createCell(3); cell.setCellValue("Internal");
	 *
	 * cell = row.createCell(4); cell.setCellValue("Theary");
	 *
	 * cell = row.createCell(5); cell.setCellValue("Terwork");
	 *
	 * cell = row.createCell(6); cell.setCellValue("practical");
	 *
	 * cell = row.createCell(7); cell.setCellValue("Oral");
	 *
	 * cell = row.createCell(8); cell.setCellValue("Total");
	 *
	 * int rows = 5; int count = 1; for (ResultStudent student : students) {
	 * Criteria c = session.createCriteria(ResultMarksheet.class);
	 * c.add(Restrictions.eq("studresult.studId", student.getStudId()));
	 * c.add(Restrictions.eq("year", aid)); c.add(Restrictions.eq("semister", (cid %
	 * 2 == 0) ? 2 : 1)); List<ResultMarksheet> list = c.list();
	 *
	 * row = sheet.createRow(rows); cell = row.createCell(0);
	 * cell.setCellValue(count);
	 *
	 * cell = row.createCell(1); cell.setCellValue(student.getFirstName() + " " +
	 * student.getLastName()); int i = 1; for (ResultMarksheet markSheet : list) {
	 * if (i != 1) { row = sheet.createRow(rows); }
	 * System.out.println("subject name........" +
	 * markSheet.getSubresult().getSubjectname());
	 *
	 * cell = row.createCell(2);
	 * cell.setCellValue(markSheet.getSubresult().getSubjectname());
	 *
	 * cell = row.createCell(3); cell.setCellValue(markSheet.getIn_obtained());
	 *
	 * cell = row.createCell(4); cell.setCellValue(markSheet.getTh_obtained());
	 *
	 * cell = row.createCell(5); cell.setCellValue(markSheet.getTw_obtained());
	 *
	 * cell = row.createCell(6); cell.setCellValue(markSheet.getPr_obtained());
	 *
	 * cell = row.createCell(7); cell.setCellValue(markSheet.getOl_obtained());
	 *
	 * cell = row.createCell(8); cell.setCellValue(markSheet.getTo_obtained());
	 * rows++; i++;
	 *
	 * } count++;
	 *
	 * } // write it as an excel attachment ByteArrayOutputStream outByteStream =
	 * new ByteArrayOutputStream(); try { wb.write(outByteStream); byte[] outArray =
	 * outByteStream.toByteArray();
	 * responses.setContentType("application/ms-excel");
	 * responses.setContentLength(outArray.length); responses.setHeader("Expires:",
	 * "0"); // eliminates browser caching
	 * responses.setHeader("Content-Disposition",
	 * "attachment; filename=Student_Result_Report" + aid + ".xls"); OutputStream
	 * outStream = responses.getOutputStream(); outStream.write(outArray);
	 * outStream.flush(); outStream.close();
	 *
	 * } catch (Exception e) { System.out.println("excepotion......" + e); }
	 *
	 * }
	 */
	@Override

	public List<AcademicSubject> getresultsubjectlist(Integer deptid, Integer classid, Integer semid)
	{

		System.out.println("commndao");
		System.out.println("deptid" + deptid + "classid" + classid + "semid" + semid);
		// String s = String.valueOf(semid);
		Integer semester = 0;

		if (classid == 1)
		{
			semester = ((semid == 1) ? 1 : 2);

		} else if (classid == 2)
		{
			semester = ((semid == 1) ? 3 : 4);
		} else if (classid == 3)
		{
			semester = ((semid == 1) ? 5 : 6);

		} else if (classid == 4)
		{
			semester = ((semid == 1) ? 7 : 8);
		}

		String sem = String.valueOf(semester);
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(AcademicSubject.class);
		System.out.println("academicssss...................." + cr.list());

		cr.add(Restrictions.eq("department.dep_id", deptid));
		cr.add(Restrictions.eq("semister", sem));

		cr.setProjection(Projections.projectionList().add(Property.forName("subject_name"), "subject_name")

				.add(Property.forName("sub_id"), "sub_id"));
		cr.setResultTransformer(Transformers.aliasToBean(AcademicSubject.class));

		List<AcademicSubject> list = cr.list();
		System.out.println("academicsssslisttttttttt...................." + list);

		return list;
	}

	@Override
	public List<EducationDetails> getEducationDetailsDao(Integer clientId)
	{

		Criteria cr = getCriteriaForSelect(EducationDetails.class);
		cr.setProjection(Projections.projectionList().add(Property.forName("standard"), "standard")
				.add(Property.forName("isBacklog"), "isBacklog").add(Property.forName("edu_id"), "edu_id")
				.add(Property.forName("universityName"), "universityName").add(Property.forName("semester"), "semester")
				.add(Property.forName("marksObtain"), "marksObtain").add(Property.forName("persentage"), "persentage")
				.add(Property.forName("resultModel"), "resultModel").add(Property.forName("noOfBacklog"), "noOfBacklog")
				.add(Property.forName("isYearDown"), "isYearDown"));
		cr.add(Restrictions.eq("clientName.id", clientId));
		cr.addOrder(Order.asc("semester"));
		cr.setResultTransformer(Transformers.aliasToBean(EducationDetails.class));
		List<EducationDetails> educationalDtl = cr.list();
		return educationalDtl;
	}

	@Override
	public void removeEducationDetailsDao(EducationDetails educatinalDtl)
	{
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(educatinalDtl);
		// delete(educatinalDtl);
	}

	@Override
	public EducationDetails addEducationDetailsDao(EducationDetails educatinalDtl)
	{
		EducationDetails obj = null;
		ComUserDetails user = getUserDetails();
		ComClientName com = new ComClientName();
		com.setId(user.getId());
		educatinalDtl.setClientName(com);
		if (!(educatinalDtl.getStandard().equalsIgnoreCase("SSC") || educatinalDtl.getStandard().equalsIgnoreCase("HSC")
				|| educatinalDtl.getStandard().equalsIgnoreCase("DIPLOMA")))
		{
			obj = getEducationDtl(user.getId(), educatinalDtl.getStandard(), educatinalDtl.getSemester().getSemid());
		} else
		{
			obj = getEducationDtl(user.getId(), educatinalDtl.getStandard(), null);
		}

		if (obj == null)
		{
			if (educatinalDtl.getSemester().getSemid() == null)
			{
				educatinalDtl.setSemester(null);
			}
			if (educatinalDtl.getNoOfBacklog() != null)
			{
				educatinalDtl.setIsBacklog(true);
			} else
			{
				educatinalDtl.setIsBacklog(false);
			}

			obj = (EducationDetails) save(educatinalDtl);

		} else

		{
			educatinalDtl.setEdu_id(obj.getEdu_id());
			educatinalDtl.setIsBacklog(obj.getIsBacklog());
			obj = educatinalDtl;
			Update(educatinalDtl);
		}
		return obj;
	}

	@Override
	public List<ResultModelForPlacement> getEducationResultModelDao()
	{
		return getList("from ResultModelForPlacement");
	}

	public EducationDetails getEducationDtl(Integer cid, String standerd, Integer semid)
	{
		Criteria cr = getCriteriaForSelect(EducationDetails.class);
		cr.add(Restrictions.eq("clientName.id", cid));
		cr.add(Restrictions.eq("standard", standerd));
		if (semid != null)
		{
			cr.add(Restrictions.eq("semester.semid", semid));
		}

		return (EducationDetails) cr.uniqueResult();
	}

	@Override
	public void updateStudentEducationalDtlsDao(EducationDetails educatinalDtl)
	{
		Update(educatinalDtl);
	}

	@Override
	public List<ResultModelForPlacement> getResultModelForPlacementDao()
	{
		return get("From ResultModelForPlacement");
	}

	@Override
	public List<Semester> getSemesterInfoForPlacementDao()
	{
		ComUserDetails d = getUserDetails();

		Student s = (Student) getObjectById("From Student", d.getComClientName().getId(), "comClientName.id");

		Criteria cr = getCriteriaForSelect(Semester.class);

		if (s.getYear() == 1)
		{
			cr.add(Restrictions.le("sem_name", "II"));
		} else if (s.getYear() == 2)
		{
			cr.add(Restrictions.le("sem_name", "IV"));
		} else if (s.getYear() == 3)
		{
			cr.add(Restrictions.le("sem_name", "VI"));
		}
		// else if (s.getYear() == 4)
		// {
		// cr.add(Restrictions.le("sem_name", "VIII"));
		// }

		cr.addOrder(Order.asc("sem_name"));

		List<Semester> list = cr.list();

		return list;
	}

	@Override
	public List<PlacementYears> getYearForPlacementDao()
	{
		ComUserDetails d = getUserDetails();

		Student s = (Student) getObjectById("From Student", d.getComClientName().getId(), "comClientName.id");

		String yrname = getYearName(s.getYear());

		Criteria cr = getCriteriaForSelect(PlacementYears.class);
		List<PlacementYears> l = cr.list();
		List<PlacementYears> lf = new ArrayList<>();
		PlacementYears ff = new PlacementYears();
		for (PlacementYears placementYears : l)
		{
			if (placementYears.getYear_name().equalsIgnoreCase(yrname))
			{
				ff = placementYears;
			}
		}
		for (int i = 0; i <= l.indexOf(ff); i++)
		{
			lf.add(l.get(i));
		}

		return lf;
	}
	// ......................................TPO..............................

	@Override

	public void saveAssingedCoordinator(Integer client_id, Integer co_type, Integer dept_id)
	{

		// System.out.println("......................" + client_id + "/////" +
		// co_type +
		// "/////" + dept_id);

		Session session = this.sessionFactory.getCurrentSession();
		ComClientName client = session.get(ComClientName.class, client_id);
		client.setCoordinatortype(co_type);
		session.update(client);
		AssignedCoordinators assignco = null;
		assignco = new AssignedCoordinators();
		assignco.setCo_type(co_type);
		assignco.setDep_id(dept_id);
		assignco.setComClientName(client);
		save(assignco);

	}

	@Override

	public List<AssignedCoordinators> getcoordinatorlist(Integer client_id)
	{
		// TODO Auto-generated method stub
		// System.out.println("client id" + client_id);
		String value = "A";
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria1 = session.createCriteria(Staff.class);
		criteria1.add(Restrictions.eq("comClientName.id", client_id));
		Staff staff = (Staff) criteria1.uniqueResult();
		// staff.getDepartment();
		ComUserDetails user = getUserDetails();
		user.getUserRole();
		if (user.getUserRole().equals("ROLE_HOD"))
		{
			Criteria cr = session.createCriteria(AssignedCoordinators.class);
			cr.add(Restrictions.eq("dep_id", staff.getDepartment()));
			cr.add(Restrictions.eq("recordStatus", value));
			List<AssignedCoordinators> list = cr.list();
			return cr.list();
		} else
		{
			Criteria cr = getCriteriaForSelect(AssignedCoordinators.class);
			cr.add(Restrictions.eq("co_type", 4));
			return cr.list();
		}

	}

	@Override
	@Transactional
	public void deleteAssigncoordinator(Integer client_id, Integer cid)
	{
		// TODO Auto-generated method stub

		Session session = this.sessionFactory.getCurrentSession();
		ComClientName client = session.get(ComClientName.class, client_id);
		client.setCoordinatortype(0);
		session.update(client);
		Criteria cr = getCriteriaForSelect(AssignedCoordinators.class);
		cr.add(Restrictions.eq("co_id", cid));
		AssignedCoordinators assco = (AssignedCoordinators) cr.uniqueResult();
		assco.setRecordStatus(Constants.DELETED_RECORD_STATUS);
	}

	@Override
	public List<AssignedCoordinators> getdepartmentTpoList()
	{
		// TODO Auto-generated method stub
		Criteria cr = getCriteriaForSelect(AssignedCoordinators.class);
		cr.add(Restrictions.eq("co_type", 4));
		return cr.list();
	}

	@Override
	public void add_Recruitment_Info(RecruitmentInfo recruitmentInfo, String[] selectionRound,
			RecruitmentInfoDTO recruitmentInfoDTO, String[] typeindustry, String[] dept)
	{

		RecruitmentInfo recruitmentInfo2 = (RecruitmentInfo) save(recruitmentInfo);

		CompanySelectionRounds companySelectionRounds = null;
		for (String name : selectionRound)
		{
			companySelectionRounds = new CompanySelectionRounds();
			companySelectionRounds.setRoundName(name);
			companySelectionRounds.setRecruitmentInfo(recruitmentInfo2);
			save(companySelectionRounds);
		}

		for (CompanyRepresentative companyRepresentative : recruitmentInfoDTO.getList())
		{

			if (companyRepresentative.getFname() != null)
			{
				companyRepresentative.setRecruitmentInfo(recruitmentInfo2);
				save(companyRepresentative);
			}
		}

		TypeOfIndustry typeOfIndustry = null;
		for (String name : typeindustry)
		{
			typeOfIndustry = new TypeOfIndustry();
			typeOfIndustry.setIndustryname(name);
			typeOfIndustry.setRecruitmentInfo(recruitmentInfo2);
			save(typeOfIndustry);
		}

		Depatmentlistofdrive depatmentlistofdrive = null;
		for (String name : dept)
		{
			depatmentlistofdrive = new Depatmentlistofdrive();
			depatmentlistofdrive.setDeptname(name);
			depatmentlistofdrive.setRecruitmentInfo(recruitmentInfo2);
			save(depatmentlistofdrive);
		}

	}

	@Override
	public List<RecruitmentInfo> getRecuitmentcmpnylogo()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Criteria cr = getCriteriaForSelect(RecruitmentInfo.class);
		cr.add(Restrictions.gt("dateInfo", date));
		System.out.println(",,,,,,,,,,,,," + cr.list());
		return cr.list();

	}

	@Override
	public List<RecruitmentInfoDTO> getpastcompnydtl()
	{
		// TODO Auto-generated method stub
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Criteria cr = getCriteriaForSelect(RecruitmentInfo.class);
		cr.setProjection(Projections.projectionList().add(Property.forName("reInfoId"), "reInfoId")
				.add(Property.forName("companyName"), "companyName")
				.add(Property.forName("jobDescription"), "jobDescription").add(Property.forName("criteria"), "criteria")
				.add(Property.forName("salary"), "salary").add(Property.forName("time"), "time")
				.add(Property.forName("logoUrl"), "logoUrl").add(Property.forName("venue"), "venue")
				.add(Property.forName("jobtitle"), "jobtitle").add(Property.forName("dateInfo"), "dateInfo")
				.add(Property.forName("slectionProcess"), "slectionProcess"));
		cr.add(Restrictions.lt("dateInfo", date));
		cr.setResultTransformer(Transformers.aliasToBean(RecruitmentInfoDTO.class));
		List<RecruitmentInfoDTO> listofpast = cr.list();

		return listofpast;

	}

	@Override
	public RecruitmentInfoDTO getcompnybyid(Integer id)
	{
		Criteria cr = getCriteriaForSelect(RecruitmentInfo.class);
		cr.setProjection(Projections.projectionList().add(Property.forName("reInfoId"), "reInfoId")
				.add(Property.forName("companyName"), "companyName")
				.add(Property.forName("jobDescription"), "jobDescription").add(Property.forName("criteria"), "criteria")
				.add(Property.forName("salary"), "salary").add(Property.forName("time"), "time")
				.add(Property.forName("venue"), "venue").add(Property.forName("dateInfo"), "dateInfo")
				.add(Property.forName("logoUrl"), "logoUrl").add(Property.forName("jobtitle"), "jobtitle")
				.add(Property.forName("year"), "year").add(Property.forName("tenth"), "tenth")
				.add(Property.forName("twelveth"), "twelveth").add(Property.forName("degree"), "degree")
				.add(Property.forName("backlog"), "backlog")
				.add(Property.forName("slectionProcess"), "slectionProcess"));
		cr.add(Restrictions.eq("reInfoId", id));
		cr.setResultTransformer(Transformers.aliasToBean(RecruitmentInfoDTO.class));
		RecruitmentInfoDTO cmpnydtl = (RecruitmentInfoDTO) cr.uniqueResult();

		Criteria cr1 = getCriteriaForSelect(CompanyRepresentative.class);
		cr1.setProjection(
				Projections.projectionList().add(Property.forName("id"), "id").add(Property.forName("fname"), "fname")
						.add(Property.forName("lname"), "lname").add(Property.forName("email"), "email")
						.add(Property.forName("mobno"), "mobno").add(Property.forName("designation"), "designation"));
		cr1.add(Restrictions.eq("recruitmentInfo.reInfoId", id));
		cr1.setResultTransformer(Transformers.aliasToBean(CompanyRepresentative.class));
		List<CompanyRepresentative> compnyrep = cr1.list();
		cmpnydtl.setList(compnyrep);

		Criteria cr2 = getCriteriaForSelect(CompanySelectionRounds.class);
		cr2.setProjection(Projections.projectionList().add(Property.forName("id"), "id")
				.add(Property.forName("roundName"), "roundName"));
		cr2.add(Restrictions.eq("recruitmentInfo.reInfoId", id));
		cr2.setResultTransformer(Transformers.aliasToBean(CompanySelectionRounds.class));
		List<CompanySelectionRounds> rounds = cr2.list();
		cmpnydtl.setRounds(rounds);

		Criteria cr3 = getCriteriaForSelect(TypeOfIndustry.class);
		cr3.setProjection(Projections.projectionList().add(Property.forName("id"), "id")
				.add(Property.forName("industryname"), "industryname"));
		cr3.add(Restrictions.eq("recruitmentInfo.reInfoId", id));
		cr3.setResultTransformer(Transformers.aliasToBean(TypeOfIndustry.class));
		List<TypeOfIndustry> industrytype = cr3.list();
		cmpnydtl.setIndustrytype(industrytype);

		Criteria cr4 = getCriteriaForSelect(Depatmentlistofdrive.class);
		cr4.setProjection(Projections.projectionList().add(Property.forName("id"), "id")
				.add(Property.forName("deptname"), "deptname"));
		cr4.add(Restrictions.eq("recruitmentInfo.reInfoId", id));
		cr4.setResultTransformer(Transformers.aliasToBean(Depatmentlistofdrive.class));
		List<Depatmentlistofdrive> deptdrive = cr4.list();
		cmpnydtl.setDeptlist(deptdrive);
		System.out.println("data of drive " + cmpnydtl);
		return cmpnydtl;
	}

	@Override
	public List<Student> getstudentlistbydept(Integer id)
	{
		Criteria cr = getCriteriaForSelect(Student.class);
		cr.add(Restrictions.eq("branch", id));
		cr.add(Restrictions.ne("year", 2));
		// System.out.println(".;.;.;.;.;.;.;.;.;.;.;.;.;." + cr.list());
		return cr.list();

	}

	@Override
	public List<EducationDetails> getfiltereducationalDTlofStudent(Integer year, Integer[] depts, double tencriteria,
			double deplocriteria, double degreecriteria, Integer backlog)
	{

		try
		{

			Criteria cr = getCriteriaForSelect(Student.class);
			cr.add(Restrictions.eq("year", year));
			cr.add(Restrictions.in("branch", depts));
			cr.setProjection(Projections.property("comClientName.id"));
			List<Integer> rounds = cr.list();
			Criteria criteria = getCriteriaForSelect(EducationDetails.class);
			criteria.setProjection(Projections.projectionList().add(Property.forName("standard"), "standard")
					.add(Property.forName("clientName"), "clientName").add(Property.forName("persentage"), "persentage")
					.add(Property.forName("noOfBacklog"), "noOfBacklog").add(Property.forName("semester"), "semester"));
			criteria.add(Restrictions.in("clientName.id", rounds));
			criteria.setResultTransformer(Transformers.aliasToBean(EducationDetails.class));
			return criteria.list();

		} catch (Exception e)
		{
			List<EducationDetails> list = new ArrayList<>();
			// TODO: handle exception
			return list;
		}
	}

	@Override
	public List<ComClientNameDTO> getStudentListForTPO(List<Integer> clientIdList)
	{
		// TODO Auto-generated method stub

		try
		{

			Criteria cr = getCriteriaForSelect(Student.class);
			cr.createAlias("comClientName", "comClientName");
			cr.setProjection(Projections.projectionList().add(Property.forName("comClientName.id"), "id")
					.add(Property.forName("comClientName.firstName"), "firstName")
					.add(Property.forName("comClientName.middleName"), "middleName")
					.add(Property.forName("comClientName.lastName"), "lastName")
					.add(Property.forName("comClientName.emailId"), "emailId").add(Property.forName("branch"), "depId")
					.add(Property.forName("comClientName.contactNos"), "contactNos")
					.add(Property.forName("year"), "year_id"));
			cr.add(Restrictions.in("comClientName.id", clientIdList));
			cr.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));
			return cr.list();
		} catch (Exception e)
		{
			List<ComClientNameDTO> list = new ArrayList<>();
			// TODO: handle exception
			return list;
		}

	}

	@Override
	public List<SubjectResultDataModel> getResultBySubjectDao(Map<String, String> parameters)
	{
		List<SubjectResultDataModel> list = new ArrayList<>();
		Criteria cr = getCriteriaForSelect(ResultMarksheet.class);
		cr.setProjection(
				Projections.projectionList().add(Projections.groupProperty("grade")).add(Projections.count("grade")));
		cr.add(Restrictions.eq("year", Integer.parseInt(parameters.get("resultyr"))));
		cr.add(Restrictions.eq("subresult.sub_id", Integer.parseInt(parameters.get("subid"))));
		// cr.setResultTransformer(Transformers.aliasToBean(SubjectResultDataModel.class));
		List<Object[]> l = cr.list();
		for (Object[] object : l)
		{
			SubjectResultDataModel d = new SubjectResultDataModel();
			d.setLabel((String) object[0]);
			d.setY((Long) object[1]);
			list.add(d);

		}
		return list;
	}

	@Override
	public ComClientNameDTO getStudentDataOnStaffTable_UsingClientId(Integer id)
	{
		try
		{

			Criteria cr = getCriteriaForSelect(Student.class);
			cr.createAlias("comClientName", "comClientName");
			cr.setProjection(Projections.projectionList().add(Property.forName("comClientName.id"), "id")
					.add(Property.forName("branch"), "depId").add(Property.forName("year"), "year_id"));
			cr.add(Restrictions.in("comClientName.id", id));
			cr.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));
			return (ComClientNameDTO) cr.uniqueResult();
		} catch (Exception e)
		{
			return null;
		}
	}

	@Override
	public RecruitmentInfo getDriveDetailsUsingId(int driveId)
	{
		Criteria criteria = getCriteriaForSelect(RecruitmentInfo.class);
		criteria.add(Restrictions.eq("reInfoId", driveId));
		return (RecruitmentInfo) criteria.uniqueResult();

	}

	@Override
	public List<EducationDetails> getEducationDetailsUsingID(int clientId)
	{
		// TODO Auto-generated method stub
		Criteria criteria = getCriteriaForSelect(EducationDetails.class);
		criteria.setProjection(Projections.projectionList().add(Property.forName("standard"), "standard")
				.add(Property.forName("clientName"), "clientName").add(Property.forName("persentage"), "persentage")
				.add(Property.forName("noOfBacklog"), "noOfBacklog").add(Property.forName("semester"), "semester"));
		criteria.add(Restrictions.in("clientName.id", clientId));
		criteria.setResultTransformer(Transformers.aliasToBean(EducationDetails.class));
		return criteria.list();
	}

	@Override
	public boolean applayForDrive(int clientId, int driveId)
	{

		if (checkapplayForDrive(clientId, driveId))
		{
			ApplyedStudentForCompany applyedStudentForCompany = new ApplyedStudentForCompany();
			Session session = this.sessionFactory.getCurrentSession();
			ComClientName comClientName = session.get(ComClientName.class, clientId);
			applyedStudentForCompany.setComClientName(comClientName);
			RecruitmentInfo recruitmentInfo = session.get(RecruitmentInfo.class, driveId);
			applyedStudentForCompany.setRecruitmentInfo(recruitmentInfo);
			save(applyedStudentForCompany);
			return true;

		}

		return false;
	}

	@Override
	public boolean checkapplayForRe_Drive(int clientId, int driveId)
	{
		return checkapplayForDrive(clientId, driveId);
	}

	@Override
	public List<AssignedCoordinators> getcoordinatorlistDao(Integer staff_id, String dt, String option)
	{
		// System.out.println(dt);
		Criteria cr = getCriteriaForSelect(AssignedCoordinators.class);
		cr.add(Restrictions.eq("forYr", Integer.parseInt(option)));
		// System.out.println(cr.list());
		return cr.list();
	}

	@Override
	public List<ComStaffSubject> getAcademicSubjectByStaffDao(Integer staff_id, String dt, String option)
	{
		Criteria cr = getCriteriaForSelect(ComStaffSubject.class);
		cr.createAlias("academicSubject", "academicSubject");
		cr.add(Restrictions.eq("clientName.id", staff_id));
		cr.add(Restrictions.eq("academicSubject.sem_year", Integer.parseInt(option)));
		return cr.list();
	}

	@Override
	public List<SubjectResultDataModel> getAttendanceBySubjectDao(Map<String, String> parameters)
	{

		return null;
	}

	@Override
	public List<PortFolioInfo> getCoordinatorInfoDao(Integer staff_id, String dt, String option)
	{
		Criteria cr = getCriteriaForSelect(Notification.class);
		cr.add(Restrictions.eq("facultyIncharge", staff_id));
		List<Notification> notification = cr.list();

		Iterator itr = notification.iterator();

		List<PortFolioInfo> cordinates = new ArrayList<>();

		while (itr.hasNext())
		{
			PortFolioInfo c = new PortFolioInfo();
			Notification n = (Notification) itr.next();
			c.setEventName("Event Incharge");
			cordinates.add(c);
		}

		return cordinates;
	}

	@Override
	public List<ApplyedStudentForCompany> getapplyedStudentlist(Integer reInfoId, Integer id)
	{
		// TODO Auto-generated method stub

		Criteria cr = getCriteriaForSelect(ApplyedStudentForCompany.class);
		cr.add(Restrictions.eq("recruitmentInfo.reInfoId", reInfoId));

		if (id == 1)
		{
			cr.add(Restrictions.eq("finalfilter", true));
		} else if (id == 2)
		{
			cr.add(Restrictions.eq("Round1", true));
		} else if (id == 3)
		{
			cr.add(Restrictions.eq("Round2", true));
		} else if (id == 4)
		{
			cr.add(Restrictions.eq("Round3", true));
		} else if (id == 5)
		{
			cr.add(Restrictions.eq("Round4", true));
		} else if (id == 6)
		{
			cr.add(Restrictions.eq("Round5", true));
		} else if (id == 7)
		{
			cr.add(Restrictions.eq("Round6", true));
		}

		System.out.println("fianal Data..../....../..." + cr.list());
		return cr.list();

	}

	@Override
	public List<ApplyedStudentForCompany> getcountround(Integer id)
	{
		// TODO Auto-generated method stub
		Criteria cr = getCriteriaForSelect(ApplyedStudentForCompany.class);
		cr.add(Restrictions.eq("recruitmentInfo.reInfoId", id));
		List<ApplyedStudentForCompany> list = cr.list();
		return list;
	}

	@Override
	public Object SaveAelectedStudNextRound(Integer[] studId, Integer roundId, Integer reInfoId)
	{
		// TODO Auto-generated method stub
		Criteria cr = getCriteriaForSelect(ApplyedStudentForCompany.class);
		cr.add(Restrictions.in("comClientName.id", studId));
		cr.add(Restrictions.in("recruitmentInfo.reInfoId", reInfoId));
		List<ApplyedStudentForCompany> list = cr.list();

		for (ApplyedStudentForCompany applyedStudentForCompany : list)
		{
			if (roundId == 0)
			{
				applyedStudentForCompany.setFinalfilter(true);
			}
			if (roundId == 1)
			{
				applyedStudentForCompany.setRound1(true);
			}
			if (roundId == 2)
			{
				applyedStudentForCompany.setRound2(true);
			}
			if (roundId == 3)
			{
				applyedStudentForCompany.setRound3(true);
			}
			if (roundId == 4)
			{
				applyedStudentForCompany.setRound4(true);
			}
			if (roundId == 5)
			{
				applyedStudentForCompany.setRound5(true);
			}
			if (roundId == 6)
			{
				applyedStudentForCompany.setRound6(true);
			}
			if (roundId == 7)
			{
				applyedStudentForCompany.setSelectstud(true);
			}
		}
		return null;
	}

	@Override
	public List<OnCampusPlaceStudListDTO> getplaceStudentList()
	{
		// TODO Auto-generated method stub
		Criteria cr = getCriteriaForSelect(ApplyedStudentForCompany.class);
		cr.createAlias("comClientName", "comClientName");
		cr.createAlias("recruitmentInfo", "recruitmentInfo");
		cr.setProjection(Projections.projectionList()

				.add(Property.forName("comClientName.firstName"), "firstName")
				.add(Property.forName("comClientName.lastName"), "lastName")
				.add(Property.forName("comClientName.middleName"), "middleName")
				.add(Property.forName("recruitmentInfo.companyName"), "companyName")
				.add(Property.forName("recruitmentInfo.jobtitle"), "jobtitle")
				.add(Property.forName("recruitmentInfo.salary"), "salary"));
		cr.add(Restrictions.eq("Round6", true));
		cr.setResultTransformer(Transformers.aliasToBean(OnCampusPlaceStudListDTO.class));

		System.out.println("././././././././." + cr.list());
		return cr.list();
	}

	@Override
	public boolean upadteStudentAcadmicYear(int clientId, int year, String acYear)
	{
		try
		{
			Criteria cr = getCriteriaForSelect(Student.class);
			cr.add(Restrictions.eq("comClientName.id", clientId));
			Student student = (Student) cr.uniqueResult();
			student.setYear(year);
			student.setPassoutYear(acYear);
			return true;
		} catch (Exception e)
		{
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public List<ComClientNameDTO> getPassoutYearDownStudent(int deptID, int yearID, String aCyear)
	{
		try
		{
			Criteria cr = getCriteriaForSelect(Student.class);
			cr.createAlias("comClientName", "comClientName");
			cr.setProjection(Projections.projectionList().add(Property.forName("comClientName.id"), "id")
					.add(Property.forName("branch"), "depId").add(Property.forName("year"), "year_id")
					.add(Property.forName("comClientName.firstName"), "firstName")
					.add(Property.forName("comClientName.lastName"), "lastName")
					.add(Property.forName("comClientName.middleName"), "middleName")
					.add(Property.forName("comClientName.contactNos"), "contactNos")

					.add(Property.forName("comClientName.emailId"), "emailId"));

			cr.add(Restrictions.eq("branch", deptID));
			cr.add(Restrictions.eq("passoutYear", aCyear));
			cr.add(Restrictions.eq("year", yearID));
			cr.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));
			return cr.list();
		} catch (Exception e)
		{
			return null;
		}
	}

	@Override
	public List<ComVideoURL> getVideoURL_List(int instCode)
	{
		Session session = this.sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(ComVideoURL.class);
		criteria.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		criteria.add(Restrictions.eq(Constants.INSTITUTE_VARIABLE_NAME, String.valueOf(instCode)));
		return criteria.list();
	}

	@Override
	@Transactional
	public List<ExtraActivities> getStudentExtraActivitiesDao(Integer clientId)
	{
		Criteria cr = getCriteriaForSelect(ExtraActivities.class);
		cr.setProjection(Projections.projectionList().add(Property.forName("activity_id"), "activity_id")
				.add(Property.forName("eventname"), "eventname").add(Property.forName("dateOfEvent"), "dateOfEvent")
				.add(Property.forName("toDate"), "toDate").add(Property.forName("eventType"), "eventType")
				.add(Property.forName("levelOfEvent"), "levelOfEvent").add(Property.forName("organizer"), "organizer")
				.add(Property.forName("className"), "className").add(Property.forName("duration"), "duration")
				.add(Property.forName("venue"), "venue").add(Property.forName("prizewon"), "prizewon")
				.add(Property.forName("coordinateName"), "coordinateName").add(Property.forName("sponsor"), "sponsor")
				.add(Property.forName("remark"), "remark")

		);

		cr.createAlias("comClientName", "comClientName");
		cr.add(Restrictions.eq("comClientName.id", clientId));
		cr.setResultTransformer(Transformers.aliasToBean(ExtraActivities.class));
		return cr.list();
	}

	@Override
	@Transactional
	public List<ValueAddedProgram> getValueAddedProgamDao(Integer clientId)
	{
		Criteria cr = getCriteriaForSelect(ValueAddedProgram.class);
		cr.add(Restrictions.eq("comClientName.id", clientId));
		return cr.list();
	}

	@Override
	@Transactional
	public List<ValueAddedProgram> getValueAddedProgamDao(Integer clientId, Integer option)
	{
		Criteria cr = getCriteriaForSelect(ValueAddedProgram.class);
		cr.setProjection(Projections.projectionList().add(Property.forName("vap_id"), "vap_id")
				.add(Property.forName("class_or_sem"), "class_or_sem")
				.add(Property.forName("name_cource"), "name_cource")
				.add(Property.forName("duration_from"), "duration_from").add(Property.forName("remark"), "remark")
				.add(Property.forName("vpa_or_intranship"), "vpa_or_intranship"));
		cr.add(Restrictions.eq("comClientName.id", clientId));
		cr.add(Restrictions.eq("vpa_or_intranship", option));
		cr.setResultTransformer(Transformers.aliasToBean(ValueAddedProgram.class));
		return cr.list();
	}

	private Integer getStudentCurrentBranch(Integer clientId)
	{
		Criteria cr = getCriteriaForSelect(Student.class);
		cr.setProjection(Projections.property("branch"));
		cr.add(Restrictions.eq("comClientName.id", clientId));
		return (Integer) cr.uniqueResult();
	}

	private Integer getStudentCurrentYr(Integer clientId)
	{
		Criteria cr = getCriteriaForSelect(Student.class);
		cr.setProjection(Projections.property("year"));
		cr.add(Restrictions.eq("comClientName.id", clientId));
		return (Integer) cr.uniqueResult();
	}

	@Override
	public List<StudentAttendanceDto> getStudentCurrentAttendanceDao(Integer clientId)
	{
		Integer yr = getStudentCurrentYr(clientId);
		List<AcademicSubject> subject = getCurrentSubjectList(yr, getStudentCurrentBranch(clientId));

		List<AcademicSemester> semester = getAcademicSemester();
		List<StudentAttendanceDto> listOfMonth = new ArrayList<>();

		for (AcademicSubject academicSubject : subject)
		{
			StudentAttendanceDto studentAttendance = new StudentAttendanceDto();
			// System.out.println("subject " +
			// academicSubject.getSubject_name());
			studentAttendance.setSubName(academicSubject.getSubject_name());

			List<MonthAndAttendanceDto> motheAndAttendance = new ArrayList<>();
			MonthAndAttendanceDto monthandattendance = new MonthAndAttendanceDto();
			AcademicSemester sem = semester.get(0);
			String[] s = sem.getSemesterStartDate().toString().split("-");
			String[] s2 = sem.getSemesterEndDate().toString().split("-");
			LocalDate date1 = LocalDate.of(Integer.parseInt(s[0]), Integer.parseInt(s[1]), 01);
			LocalDate date2 = LocalDate.of(Integer.parseInt(s2[0]), Integer.parseInt(s2[1]), 20);
			while (date1.isBefore(date2))
			{
				int p = 1;
				int totalLecture = 1;
				List<ComAttendance> list = getAttendanceBySubjectbyMonth(date1, academicSubject.getSub_id());
				for (ComAttendance comAttendance : list)
				{
					if (list.contains(clientId))
					{
						p++;
					}
				}
				monthandattendance.setMonthName(Month.of(date1.getMonthValue()).name());
				monthandattendance.setAttendance((totalLecture / p) / 100);
				date1 = date1.plus(Period.ofMonths(1));
				motheAndAttendance.add(monthandattendance);
				studentAttendance.setList(motheAndAttendance);
			}

			listOfMonth.add(studentAttendance);
		}

		return listOfMonth;
	}

	private List<AcademicSubject> getCurrentSubjectList(Integer yr, Integer branch)
	{
		Criteria cr = getCriteriaForSelect(AcademicSubject.class);
		Disjunction or = Restrictions.disjunction();
		if (yr == 1)
		{

			or.add(Restrictions.eq("semister", "1"));
			or.add(Restrictions.eq("semister", "2"));

		} else if (yr == 2)
		{
			or.add(Restrictions.eq("semister", "3"));
			or.add(Restrictions.eq("semister", "4"));
		} else if (yr == 3)
		{
			or.add(Restrictions.eq("semister", "5"));
			or.add(Restrictions.eq("semister", "6"));
		} else if (yr == 4)
		{
			or.add(Restrictions.eq("semister", "7"));
			or.add(Restrictions.eq("semister", "8"));
		}
		cr.add(Restrictions.eq("department.dep_id", branch));
		cr.add(or);
		return cr.list();
	}

	public List<ComAttendance> getAttendanceBySubjectbyMonth(LocalDate date, Integer Sub_id)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(ComAttendance.class);
		c.createAlias("comStaffSubject", "comStaffSubject");
		c.add(Restrictions.eq("comStaffSubject.academicSubject.sub_id", Sub_id));
		c.add(Restrictions.between("class_date", java.sql.Date.valueOf(date),
				java.sql.Date.valueOf(date.getYear() + "-" + date.getMonthValue() + "-" + date.lengthOfMonth())));
		// c.setProjection(Projections.avg("total"));
		return c.list();

	}

	private List<AcademicSemester> getAcademicSemester()
	{
		Criteria cr = getCriteriaForSelect(AcademicSemester.class);
		return cr.list();
	}

	@Override
	public List<GfmInformation> getMentorListByStudentDao(Integer staff_id)
	{
		Criteria cr = getCriteriaForSelect(MentorStudent.class);
		cr.createAlias("mentor", "mentor");
		cr.setProjection(Projections.projectionList().add(Property.forName("mentor.sem"), "sem")
				.add(Property.forName("mentor.ac_yr"), "yr").add(Property.forName("mentor.staff.staffId"), "staffid"));
		cr.add(Restrictions.eq("student.studentId", staff_id));
		cr.setResultTransformer(Transformers.aliasToBean(GfmInformation.class));
		List<GfmInformation> list = cr.list();
		for (GfmInformation gfm : list)
		{
			ComClientNameDTO comclient = getMentorInfo(gfm.getStaffid());
			gfm.setFirstName(comclient.getFirstName());
			gfm.setMiddleName(comclient.getMiddleName());
			gfm.setLastName(comclient.getLastName());
		}
		return list;
	}

	private ComClientNameDTO getMentorInfo(Integer id)
	{

		Criteria cr = getCriteriaForSelect(Staff.class);
		cr.createAlias("comClientName", "comClientName");
		cr.setProjection(Projections.projectionList().add(Property.forName("comClientName.firstName"), "firstName")
				.add(Property.forName("comClientName.lastName"), "lastName")
				.add(Property.forName("comClientName.middleName"), "middleName"));
		cr.add(Restrictions.eq("staffId", id));
		cr.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));
		return (ComClientNameDTO) cr.uniqueResult();
	}

	@Override
	@Transactional
	public ExtraActivities saveExtraActivityDao(ExtraActivities extraActivities)
	{

		Object o = save(extraActivities);
		return extraActivities;
	}

	@Override
	public RecruitmentInfo saveExtraActivityDao(Integer[] studId, Integer reInfoId)
	{
		try
		{
			for (Integer integer : studId)
			{
				Criteria cr = getCriteriaForSelect(ApplyedStudentForCompany.class);
				cr.add(Restrictions.in("comClientName.id", integer));
				cr.add(Restrictions.in("recruitmentInfo.reInfoId", reInfoId));
				ApplyedStudentForCompany applyedStudentForCompany = (ApplyedStudentForCompany) cr.uniqueResult();
				applyedStudentForCompany.setSelectstud(true);
			}
		} catch (Exception e)
		{
		}
		Session session = this.sessionFactory.getCurrentSession();

		return session.get(RecruitmentInfo.class, reInfoId);

	}

	@Override
	@Transactional
	public PlacementDriveStatus getDriveSatus(Integer studId, Integer driveID)
	{
		// TODO Auto-generated method stub

		PlacementDriveStatus placementDriveStatus = new PlacementDriveStatus();

		Criteria cr = getCriteriaForSelect(ApplyedStudentForCompany.class);
		cr.add(Restrictions.in("comClientName.id", studId));
		cr.add(Restrictions.in("recruitmentInfo.reInfoId", driveID));
		ApplyedStudentForCompany applyedStudentForCompany = (ApplyedStudentForCompany) cr.uniqueResult();
		Criteria cr1 = getCriteriaForSelect(CompanySelectionRounds.class);
		cr1.add(Restrictions.in("recruitmentInfo.reInfoId", driveID));
		cr1.setProjection(Projections.projectionList().add(Property.forName("roundName"), "roundName"));
		placementDriveStatus.setRoundList(cr1.list());
		placementDriveStatus.setRound1(applyedStudentForCompany.isRound1());
		placementDriveStatus.setRound2(applyedStudentForCompany.isRound2());
		placementDriveStatus.setRound3(applyedStudentForCompany.isRound3());
		placementDriveStatus.setRound4(applyedStudentForCompany.isRound4());
		placementDriveStatus.setRound5(applyedStudentForCompany.isRound5());
		placementDriveStatus.setSelectstud(applyedStudentForCompany.isSelectstud());
		placementDriveStatus.setAccepted(applyedStudentForCompany.isAccepted());
		return placementDriveStatus;
	}

	@Override
	@Transactional
	public Object saveOfferAcceptedStudent(Integer studId, Integer driveID)
	{
		// TODO Auto-generated method stub
		Criteria cr = getCriteriaForSelect(ApplyedStudentForCompany.class);
		cr.add(Restrictions.in("comClientName.id", studId));
		cr.add(Restrictions.in("recruitmentInfo.reInfoId", driveID));
		ApplyedStudentForCompany applyedStudentForCompany = (ApplyedStudentForCompany) cr.uniqueResult();
		applyedStudentForCompany.setAccepted(true);
		applyedStudentForCompany.setOfferaccepted(true);
		save(applyedStudentForCompany);
		return applyedStudentForCompany.isAccepted();
	}

	@Override
	public List<ApplyedStudentForCompany> getplacementroundDetails(Integer clientId)
	{
		// TODO Auto-generated method stub
		Criteria cr1 = getCriteriaForSelect(ApplyedStudentForCompany.class);
		cr1.add(Restrictions.in("comClientName.id", clientId));
		return cr1.list();
	}

	@Override
	public List<CompanySelectionRounds> ggetroundlistofdrive(Integer id)
	{
		Criteria cr1 = getCriteriaForSelect(CompanySelectionRounds.class);
		cr1.add(Restrictions.in("recruitmentInfo.reInfoId", id));
		cr1.setProjection(Projections.projectionList().add(Property.forName("roundName"), "roundName"));
		return cr1.list();
	}

	@Override
	public List<Student> getstudentdata()
	{

		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = getCriteriaForSelect(Student.class);
		List<Student> student = cr.list();
		System.out.println("list:::" + student.size());

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
		cell.setCellValue("Student Data");

		row = sheet.createRow(1);
		cell = row.createCell(0);

		cell.setCellValue("sr.No");
		cell = row.createCell(1);

		cell.setCellValue("Frist Name");
		cell = row.createCell(2);

		cell.setCellValue("Middle Name");
		cell = row.createCell(3);

		cell.setCellValue("Last Name");
		cell = row.createCell(4);

		cell.setCellValue("Motaher");
		cell = row.createCell(5);

		cell.setCellValue("Gender");
		cell = row.createCell(6);

		cell.setCellValue("DOB");
		cell = row.createCell(7);

		cell.setCellValue("Emailid");
		cell = row.createCell(8);

		cell.setCellValue("Contact No");
		cell = row.createCell(9);

		cell.setCellValue("Handicap");
		cell = row.createCell(10);

		cell.setCellValue("Address");
		cell = row.createCell(11);

		cell.setCellValue("City");
		cell = row.createCell(12);

		cell.setCellValue("State");
		cell = row.createCell(13);

		cell.setCellValue("Pincode");
		cell = row.createCell(14);

		cell.setCellValue("Country");
		cell = row.createCell(15);

		cell.setCellValue("UserName");
		cell = row.createCell(16);

		cell.setCellValue("Password");
		cell = row.createCell(17);

		cell.setCellValue("Role");
		cell = row.createCell(18);

		cell.setCellValue("Registrationno");
		cell = row.createCell(19);

		cell.setCellValue("RollNo");
		cell = row.createCell(20);

		cell.setCellValue("Standard");
		cell = row.createCell(21);

		cell.setCellValue("Year");
		cell = row.createCell(22);

		cell.setCellValue("Branch");
		cell = row.createCell(23);

		cell.setCellValue("University PRN No");
		cell = row.createCell(24);

		int num = 3;
		int srno = 1;

		if (student.size() != 0)
		{
			for (Student stud : student)
			{
				row = sheet.createRow(num);
				cell = row.createCell(0);

				cell.setCellValue(srno);
				cell = row.createCell(1);

				cell.setCellValue(stud.getComClientName().getFirstName());
				cell = row.createCell(2);

				cell.setCellValue(stud.getComClientName().getMiddleName());
				cell = row.createCell(3);

				cell.setCellValue(stud.getComClientName().getLastName());
				cell = row.createCell(4);

				cell.setCellValue(stud.getComClientName().getMotherName());
				cell = row.createCell(5);

				if (stud.getComClientName().getGender() != null)
				{
					cell.setCellValue(stud.getComClientName().getGender());
					cell = row.createCell(6);
					System.out.println(stud.getComClientName().getGender());

				} else
				{
					cell.setCellValue("null");
					cell = row.createCell(6);
				}

				if (stud.getComClientName().getDateOfBirth() != null)
				{
					cell.setCellValue(stud.getComClientName().getDateOfBirth());
					cell = row.createCell(7);
				} else
				{
					cell.setCellValue("null");
					cell = row.createCell(7);
				}

				cell.setCellValue(stud.getComClientName().getEmailId());
				cell = row.createCell(8);

				cell.setCellValue(stud.getComClientName().getContactNos());
				cell = row.createCell(9);

				// cell.setCellValue(stud.getComClientName().getIsHandicaped());
				// cell = row.createCell(10);

				Set<ComClientAddress> address = stud.getComClientName().getComClientAddresses();

				Iterator<ComClientAddress> itr = address.iterator();
				while (itr.hasNext())
				{

					ComClientAddress type = itr.next();
					cell.setCellValue(type.getAddress1());
					cell = row.createCell(11);

					cell.setCellValue(type.getCity());
					cell = row.createCell(12);

					cell.setCellValue(type.getState());
					cell = row.createCell(13);

					cell.setCellValue(type.getPostalCode());
					cell = row.createCell(14);

					cell.setCellValue(type.getCountry());
					cell = row.createCell(15);

				}

				cell.setCellValue(stud.getComClientName().getComUserDetails().getUserName());
				cell = row.createCell(16);

				cell.setCellValue(stud.getComClientName().getComUserDetails().getPassword());
				cell = row.createCell(17);

				cell.setCellValue(stud.getComClientName().getComUserDetails().getUserRole());
				cell = row.createCell(18);

				cell.setCellValue(stud.getRegistrationNo());
				cell = row.createCell(19);

				cell.setCellValue(stud.getRollNo());
				cell = row.createCell(20);

				if (stud.getStandard() != null)
				{
					cell.setCellValue(stud.getStandard());
					cell = row.createCell(21);
				} else
				{
					cell.setCellValue("null");
					cell = row.createCell(21);

				}
				if (stud.getStandard() != null)
				{
					cell.setCellValue(stud.getYear());
					cell = row.createCell(22);
				} else
				{
					cell.setCellValue("null");
					cell = row.createCell(22);
				}

				cell.setCellValue(stud.getBranch());
				cell = row.createCell(23);

				cell.setCellValue(stud.getUniversityEnrollNo());
				cell = row.createCell(24);
				num++;
				srno++;
			}
			// write it as an excel attachment
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			try
			{
				wb.write(outByteStream);
				byte[] outArray = outByteStream.toByteArray();
				responses.setContentType("application/ms-excel");
				responses.setContentLength(outArray.length);
				responses.setHeader("Expires:", "0"); // eliminates browser caching
				responses.setHeader("Content-Disposition", "attachment; filename=Grievance_List.xls");
				OutputStream outStream = responses.getOutputStream();
				outStream.write(outArray);
				outStream.flush();
				outStream.close();

			} catch (Exception e)
			{
				System.out.println("excepotion......" + e);
			}

		}
		return null;

	}

	@Override
	public List<Staff> getstaffdata()
	{

		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = getCriteriaForSelect(Staff.class);
		List<Staff> staff = cr.list();
		System.out.println("list:::" + staff.size());

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

		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue("sr.No");
		cell = row.createCell(1);
		cell.setCellValue("Frist Name");
		cell = row.createCell(2);
		cell.setCellValue("Middle Name");
		cell = row.createCell(3);
		cell.setCellValue("Last Name");
		cell = row.createCell(4);
		cell.setCellValue("Motaher");
		cell = row.createCell(5);
		cell.setCellValue("Gender");
		cell = row.createCell(6);
		cell.setCellValue("DOB");
		cell = row.createCell(7);
		cell.setCellValue("Emailid");
		cell = row.createCell(8);
		cell.setCellValue("Contact No");
		cell = row.createCell(9);

		cell.setCellValue("Handicap");
		cell = row.createCell(10);

		cell.setCellValue("Address");
		cell = row.createCell(11);

		cell.setCellValue("City");
		cell = row.createCell(12);

		cell.setCellValue("State");
		cell = row.createCell(13);

		cell.setCellValue("Pincode");
		cell = row.createCell(14);

		cell.setCellValue("Country");
		cell = row.createCell(15);

		cell.setCellValue("UserName");
		cell = row.createCell(16);

		cell.setCellValue("Password");
		cell = row.createCell(17);

		cell.setCellValue("Role");
		cell = row.createCell(18);

		cell.setCellValue("Registrationno");
		cell = row.createCell(19);

		cell.setCellValue("Employee Number");
		cell = row.createCell(20);

		cell.setCellValue("Designation");
		cell = row.createCell(21);

		cell.setCellValue("Staff TYpe");
		cell = row.createCell(22);

		cell.setCellValue("Branch");
		cell = row.createCell(23);

		cell.setCellValue("University PRN No");
		cell = row.createCell(24);

		int num = 3;
		int srno = 1;

		if (staff.size() != 0)
		{
			for (Staff stafflist : staff)
			{
				row = sheet.createRow(num);
				cell = row.createCell(0);

				cell.setCellValue(srno);
				cell = row.createCell(1);

				cell.setCellValue(stafflist.getComClientName().getFirstName());
				cell = row.createCell(2);

				cell.setCellValue(stafflist.getComClientName().getMiddleName());
				cell = row.createCell(3);

				cell.setCellValue(stafflist.getComClientName().getLastName());
				cell = row.createCell(4);

				cell.setCellValue(stafflist.getComClientName().getMotherName());
				cell = row.createCell(5);

				if (stafflist.getComClientName().getGender() != null)
				{
					cell.setCellValue(stafflist.getComClientName().getGender());
					cell = row.createCell(6);
					System.out.println(stafflist.getComClientName().getGender());

				} else
				{
					cell.setCellValue("null");
					cell = row.createCell(6);
				}

				if (stafflist.getComClientName().getDateOfBirth() != null)
				{
					cell.setCellValue(stafflist.getComClientName().getDateOfBirth());
					cell = row.createCell(7);
				} else
				{
					cell.setCellValue("null");
					cell = row.createCell(7);
				}

				cell.setCellValue(stafflist.getComClientName().getEmailId());
				cell = row.createCell(8);

				cell.setCellValue(stafflist.getComClientName().getContactNos());
				cell = row.createCell(9);

				// cell.setCellValue(stafflist.getComClientName().getIsHandicaped());
				// cell = row.createCell(10);

				Set<ComClientAddress> address = stafflist.getComClientName().getComClientAddresses();

				Iterator<ComClientAddress> itr = address.iterator();
				while (itr.hasNext())
				{
					ComClientAddress type = itr.next();

					cell.setCellValue(type.getAddress1());
					cell = row.createCell(11);

					cell.setCellValue(type.getCity());
					cell = row.createCell(12);

					cell.setCellValue(type.getState());
					cell = row.createCell(13);

					cell.setCellValue(type.getPostalCode());
					cell = row.createCell(14);

					cell.setCellValue(type.getCountry());
					cell = row.createCell(15);

				}

				cell.setCellValue(stafflist.getComClientName().getComUserDetails().getUserName());
				cell = row.createCell(16);

				cell.setCellValue(stafflist.getComClientName().getComUserDetails().getPassword());
				cell = row.createCell(17);

				cell.setCellValue(stafflist.getComClientName().getComUserDetails().getUserRole());
				cell = row.createCell(18);

				cell.setCellValue(stafflist.getRegistrationNo());
				cell = row.createCell(19);

				cell.setCellValue(stafflist.getEmployeeNo());
				cell = row.createCell(20);

				cell.setCellValue(stafflist.getDesignation());
				cell = row.createCell(21);
				if (stafflist.getStaffType() != null)
				{

					cell.setCellValue(stafflist.getStaffType());
					cell = row.createCell(22);

				} else
				{

					cell.setCellValue("null");
					cell = row.createCell(22);

				}
				cell.setCellValue(stafflist.getDepartment());
				cell = row.createCell(23);

				cell.setCellValue(stafflist.getUniversityEnrollNo());
				cell = row.createCell(24);

				num++;
				srno++;
			}
			// write it as an excel attachment
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			try
			{
				wb.write(outByteStream);
				byte[] outArray = outByteStream.toByteArray();
				responses.setContentType("application/ms-excel");
				responses.setContentLength(outArray.length);
				responses.setHeader("Expires:", "0"); // eliminates browser caching
				responses.setHeader("Content-Disposition", "attachment; filename=Grievance_List.xls");
				OutputStream outStream = responses.getOutputStream();
				outStream.write(outArray);
				outStream.flush();
				outStream.close();

			} catch (Exception e)
			{
				System.out.println("excepotion......" + e);
			}

		}
		return null;
	}

	@Override
	public Groups savegroup(Mentor mentor, Integer[] students)
	{

		System.out.println("mentorrr daogroup" + mentor.getId());
		String gfmname = "Gfm Group";
		Boolean b = false;
		Groups grp = new Groups();
		GroupMembers grpmember = new GroupMembers();

		grp.setDepartment(mentor.getStaff().getDepartment());
		grp.setGfm_id(mentor.getId());
		grp.setGroupIncharge_1(mentor.getStaff().getComClientName().getId());
		grp.setGroupIncharge_2(mentor.getStaff().getComClientName().getId());
		grp.setGroupName(gfmname);
		grp.setGroupType(4);
		grp.setGroupfor(22);

		Object obj = new Object();
		Object group = new Object();

		Staff stf = (Staff) getListById("From Staff", mentor.getStaff().getComClientName().getId(), "comClientName.id")
				.get(0);

		for (Integer element : students)
		{
			GroupMembers gm = new GroupMembers();
			ComClientName c = new ComClientName();
			c.setId(element);
			gm.setGroups(grp);
			gm.setComClientName(c);
			group = save(grp);
			obj = save(gm);
		}

		if (obj != null)
		{
			if (grp.getGroupIncharge_1() != null)
			{
				GroupMembers gm = new GroupMembers();
				ComClientName c = new ComClientName();
				c.setId(grp.getGroupIncharge_1());
				gm.setGroups(grp);
				gm.setComClientName(c);
				save(grp);
				save(gm);
			}
			if (grp.getGroupIncharge_2() != grp.getGroupIncharge_1())
			{
				GroupMembers gm = new GroupMembers();
				ComClientName c = new ComClientName();
				c.setId(grp.getGroupIncharge_2());
				gm.setGroups(grp);
				gm.setComClientName(c);
				save(grp);
				save(gm);
			}
			return grp;

		} else
		{
			return grp;
		}

	}

	@Override
	public String updateStudentform(AdminForm student)
	{
		System.out.println("stuuuuuuuuuuuuuuuudaoooooooooooo" + student);
		String message = "Email id allrady Present";
		ComUserDetails userDetails = null;
		UserRole role = null;
		ComClientAddress address = null;
		ComClientName clientName = null;
		Student stud = null;

		clientName = new ComClientName();
		userDetails = new ComUserDetails();
		address = new ComClientAddress();
		role = new UserRole();
		stud = new Student();
		Date date1;

		if (!findUser(student.getEmailId()))
		{
			clientName.setFirstName(student.getFirstName());
			clientName.setMiddleName(student.getMiddleName());
			clientName.setLastName(student.getLastName());
			clientName.setEmailId(student.getEmailId());
			clientName.setContactNos(student.getContactNos());
			clientName.setGender(student.getGender());
			try
			{
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(student.getDateofbirth());
				clientName.setDateOfBirth(date1);

			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			address.setComClientName(clientName);
			userDetails.setComClientName(clientName);
			userDetails.setUserName(student.getEmailId());

			// String password = generatePswd(admin.getFirstName(), admin.getLastName());
			userDetails.setPassword("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");

			userDetails.setUserRole(student.getDesignation());
			role.setComUserDetails(userDetails);
			role.setRoleName(student.getDesignation());

			stud.setComClientName(clientName);
			stud.setBranch(student.getDepartment());
			stud.setStandard(student.getStandard());
			stud.setYear(student.getYear());
			stud.setRollNo(student.getRollNo());

			try
			{
				save(clientName);
				save(userDetails);
				save(address);
				save(role);
				save(stud);
				// javaSendMailService.sendPasswordToMail(admin.getEmailId(),
				// admin.getFirstName(), password);
				// message = "data upload Sucsessfully";
			} catch (Exception e)
			{
				// TODO: handle exception
				System.out.println("Exception::::");

			}
		}
		return message;
	}

	@Override
	public String deletementeestudent(Integer[] member)
	{
		System.out.println("groupmemeberrr" + member[0]);

		int i = 0;
		for (Integer element : member)
		{
			i = deletementeestudent(element);
		}
		return null;

	}

	@Override
	public StudentForm getstudinfodtltoparent(Integer studId)
	{
		// TODO Auto-generated method stub
		StudentForm studentform = new StudentForm();

		Criteria cr = getCriteriaForSelect(Student.class);
		cr.add(Restrictions.in("comClientName.id", studId));
		Student stud = (Student) cr.uniqueResult();

		Criteria criteria = getCriteriaForSelect(Parent.class);
		criteria.add(Restrictions.eq("Stud_id", stud.getStudentId()));
		Parent p = (Parent) criteria.uniqueResult();

		studentform.setParentContactNo1(p.getContact_no1());
		studentform.setParentEmail(p.getEmail());
		studentform.setProfesion(p.getProfesion());
		studentform.setRelation(p.getRelation());

		return studentform;
	}

	@Override
	public List<ExtraActivities> getfutureinterestInfo(Integer cid, Integer type)
	{

		Criteria cr = getCriteriaForSelect(ExtraActivities.class);
		cr.setProjection(Projections.projectionList().add(Property.forName("activity_id"), "activity_id")

				.add(Property.forName("placement"), "placement").add(Property.forName("higherstudy"), "higherstudy")
				.add(Property.forName("bussiness"), "bussiness").add(Property.forName("status"), "status")

		);
		cr.createAlias("comClientName", "comClientName");
		cr.add(Restrictions.eq("comClientName.id", cid));
		cr.add(Restrictions.eq("eventType", type));
		cr.setResultTransformer(Transformers.aliasToBean(ExtraActivities.class));
		return cr.list();
	}

	@Override
	public ExtraActivities savefuturelist(ExtraActivities extraActivities)
	{
		System.out.println("/././././." + extraActivities);
		if (extraActivities.isStatus() == true)
		{
			Session session = this.sessionFactory.getCurrentSession();
			ExtraActivities eeee = session.get(ExtraActivities.class, extraActivities.getActivity_id());
			eeee.setPlacement(extraActivities.isPlacement());
			eeee.setHigherstudy(extraActivities.isHigherstudy());
			eeee.setBussiness(extraActivities.isBussiness());
			eeee.setStatus(extraActivities.isStatus());
			session.update(eeee);

		} else if (extraActivities.isStatus() == false)
		{
			Object o = save(extraActivities);
		}
		return extraActivities;

	}

	@Override
	public Map<String, List<TotalStudentCount>> getsudenttotalcount(Integer deptid)
	{

		TotalStudentCount totalcountstud = null;
		TreeMap<String, List<TotalStudentCount>> studcountmap = new TreeMap<>();

		System.out.println("deptid" + deptid);
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// Set time fields to zero
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date datenew = cal.getTime();

		System.out.println("student count.........ajax date" + datenew);

		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = getCriteriaForSelect(Department.class);
		cr.add(Restrictions.eq("dep_id", deptid));

		Department department = (Department) cr.uniqueResult();

		Map<Integer, List<Integer>> map = new HashMap();

		if (department.getDep_name().equals("FE"))
		{

			System.out.println("come in if condition");

			Criteria cr1 = getCriteriaForSelect(Student.class);
			cr1.setProjection(Projections.projectionList().add(Projections.groupProperty("standard")));
			cr1.add(Restrictions.eq("year", 1));
			cr1.add(Restrictions.eq("branch", deptid));
			List<Integer> standard = cr1.list();
			map.put(1, standard);

		} else
		{

			for (int i = 2; i <= 4; i++)
			{
				Criteria cr2 = getCriteriaForSelect(Student.class);
				cr2.setProjection(Projections.projectionList().add(Projections.groupProperty("standard")));
				cr2.add(Restrictions.eq("year", i));
				cr2.add(Restrictions.eq("branch", deptid));
				List<Integer> standard = cr2.list();
				map.put(i, standard);
			}

		}

		for (Map.Entry<Integer, List<Integer>> countdiv : map.entrySet())
		{

			List<TotalStudentCount> total = new ArrayList<>();

			for (Integer division : countdiv.getValue())
			{

				Criteria cr3 = getCriteriaForSelect(AttendancePunch.class);
				cr3.add(Restrictions.eq("year", countdiv.getKey()));
				cr3.add(Restrictions.eq("dept", deptid));
				cr3.add(Restrictions.eq("div", String.valueOf(division)));
				cr3.add(Restrictions.eq("tdate", datenew));
				cr3.setProjection(Projections.rowCount());
				long present = (long) cr3.uniqueResult();

				Criteria cr4 = getCriteriaForSelect(Student.class);
				cr4.add(Restrictions.eq("year", countdiv.getKey()));
				cr4.add(Restrictions.eq("branch", deptid));
				cr4.add(Restrictions.eq("standard", division));
				cr4.setProjection(Projections.rowCount());
				long totalcount = (long) cr4.uniqueResult();

				totalcountstud = new TotalStudentCount();
				totalcountstud.setDiv(findivisionusingstandard(String.valueOf(division)));
				totalcountstud.setTotalcount(totalcount);
				totalcountstud.setPresentcount(present);
				totalcountstud.setDept_id(deptid);
				total.add(totalcountstud);

			}
			studcountmap.put(String.valueOf(countdiv.getKey()), total);
		}

		return studcountmap;

	}

	@Override
	public CollegeCountDTO gettotolcollegecount()
	{
		CollegeCountDTO collegecount = new CollegeCountDTO();

		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// Set time fields to zero
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date dt = cal.getTime();

		Criteria cr1 = getCriteriaForSelect(AttendancePunch.class);
		cr1.add(Restrictions.eq("tdate", dt));
		cr1.setProjection(Projections.rowCount());
		long collegepresent = (long) cr1.uniqueResult();

		Criteria cr = getCriteriaForSelect(Student.class);
		cr.setProjection(Projections.rowCount());
		long totalcollegecount = (long) cr.uniqueResult();
		collegecount.setPresentcount(collegepresent);
		collegecount.setTotalcount(totalcollegecount);
		return collegecount;
	}

	@Override
	public List<TotalStudentCount> gettotaldepartmentcount()
	{

		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// Set time fields to zero
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date d = cal.getTime();

		System.out.println("studnet date onload function" + d);
		List<TotalStudentCount> totalcountlist = new ArrayList<>();
		TotalStudentCount student;
		Criteria cr = getCriteriaForSelect(Department.class);
		List<Department> list = cr.list();
		for (Department department : list)
		{
			student = new TotalStudentCount();
			Criteria cr1 = getCriteriaForSelect(AttendancePunch.class);
			cr1.add(Restrictions.eq("dept", department.getDep_id()));
			cr1.add(Restrictions.eq("tdate", d));
			cr1.setProjection(Projections.rowCount());
			long presentcount = (long) cr1.uniqueResult();

			Criteria cr2 = getCriteriaForSelect(Student.class);
			cr2.add(Restrictions.eq("branch", department.getDep_id()));
			cr2.setProjection(Projections.rowCount());
			long totaldeptcount = (long) cr2.uniqueResult();

			student.setPresentcount(presentcount);
			student.setTotalcount(totaldeptcount);
			student.setDept_id(department.getDep_id());
			student.setDeptname(department.getDep_name());
			totalcountlist.add(student);
		}

		return totalcountlist;
	}

	@Override
	public List<Student> getfilterstudentlist(Integer year, Integer depts, Integer div)
	{
		// TODO Auto-generated method stub
		Criteria criteria = getCriteriaForSelect(Student.class);
		criteria.add(Restrictions.eq("branch", depts));
		criteria.add(Restrictions.eq("year", year));
		criteria.add(Restrictions.eq("standard", div));
		List<Student> list = criteria.list();

		return criteria.list();
	}

	@Override
	public OffcampusPlaceStud Saveoffcampusstudlist(OffcampusPlaceStud data)
	{
		// TODO Auto-generated method stub

		DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

		String todateStr = dateFormat.format(data.getPlacdate());

		OffcampusPlaceStud offcampusPlaceStud = null;
		offcampusPlaceStud = new OffcampusPlaceStud();

		offcampusPlaceStud.setCmpnyname(data.getCmpnyname());
		offcampusPlaceStud.setPlacdate(data.getPlacdate());
		offcampusPlaceStud.setStudname(data.getStudname());
		offcampusPlaceStud.setSalary(data.getSalary());
		offcampusPlaceStud.setToDateString(todateStr);
		System.out.println("///////////......" + offcampusPlaceStud);
		save(offcampusPlaceStud);
		return offcampusPlaceStud;
	}

	@Override
	public List<OffcampusPlaceStud> getoffcampusstudlist()
	{
		Criteria cr = getCriteriaForSelect(OffcampusPlaceStud.class);
		return cr.list();
	}

	@Override
	public List<ExtraActivityDTO> getstudentcarrierlist(Integer eid, Integer type)
	{
		Criteria cr = getCriteriaForSelect(ExtraActivities.class);
		cr.createAlias("comClientName", "comClientName");
		cr.setProjection(Projections.projectionList().add(Property.forName("comClientName.firstName"), "firstName")
				.add(Property.forName("comClientName.lastName"), "lastName")
				.add(Property.forName("comClientName.middleName"), "middleName").add(Property.forName("dept"), "dept")
				.add(Property.forName("className"), "className"));
		cr.add(Restrictions.eq("eventType", eid));
		if (type == 1)
		{
			cr.add(Restrictions.eq("placement", true));
		} else if (type == 2)
		{
			cr.add(Restrictions.eq("higherstudy", true));

		} else if (type == 3)
		{
			cr.add(Restrictions.eq("bussiness", true));

		}
		cr.setResultTransformer(Transformers.aliasToBean(ExtraActivityDTO.class));
		return cr.list();
	}

	@Override
	public List<StudentFeeDtl> getfeedepartmentdtl()
	{
		List<StudentFeeDtl> feedtl = new ArrayList<>();
		StudentFeeDtl feedetails;

		Criteria cr = getCriteriaForSelect(Department.class);
		List<Department> list = cr.list();

		for (Department department : list)
		{
			long sum1 = 0, sum2 = 0;

			Criteria cr2 = getCriteriaForSelect(FeeDetails.class);
			cr2.createAlias("student", "student");
			cr2.setProjection(Projections.projectionList().add(Property.forName("student.studentId"), "studentId"));
			cr2.add(Restrictions.eq("student.branch", department.getDep_id()));
			List<Integer> stud = cr2.list();

			for (Integer studid : stud)
			{
				Criteria cr1 = getCriteriaForSelect(FeeDetails.class);
				cr1.add(Restrictions.eq("student.studentId", studid));
				List<FeeDetails> dtl = cr1.list();

				for (FeeDetails feeDetails2 : dtl)
				{
					sum1 = sum1 + feeDetails2.getTotal_fee();
					sum2 = sum2 + feeDetails2.getPaid();
				}

			}
			feedetails = new StudentFeeDtl();
			feedetails.setDept_id(department.getDep_id());
			feedetails.setDept_name(department.getDep_name());
			feedetails.setPaid_fee(sum2);
			feedetails.setTotal_fee(sum1);
			feedtl.add(feedetails);
		}
		return feedtl;
	}

	@Override
	public CollegeTotalFee getcollegetotalfee()
	{
		CollegeTotalFee collegefee = new CollegeTotalFee();
		long totalcollgfee = 0;
		long totalpaidfee = 0;
		Criteria cr1 = getCriteriaForSelect(FeeDetails.class);
		List<FeeDetails> dtl = cr1.list();
		for (FeeDetails feeDetails2 : dtl)
		{
			totalcollgfee = totalcollgfee + feeDetails2.getTotal_fee();
			totalpaidfee = totalpaidfee + feeDetails2.getPaid();
		}
		collegefee.setCollegestudentfee(totalcollgfee);
		collegefee.setCollgestudentppiadfee(totalpaidfee);
		return collegefee;
	}

	@Override
	public Map<String, List<StudentFeeDtl>> gettotalstudentfee(Integer deptid)
	{
		StudentFeeDtl totalstudentfee = null;
		TreeMap<String, List<StudentFeeDtl>> studentfeedtl = new TreeMap<>();

		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = getCriteriaForSelect(Department.class);
		cr.add(Restrictions.eq("dep_id", deptid));

		Department department = (Department) cr.uniqueResult();

		Map<Integer, List<Integer>> map = new HashMap();

		if (department.getDep_name().equals("FE"))
		{

			Criteria cr1 = getCriteriaForSelect(Student.class);
			cr1.setProjection(Projections.projectionList().add(Projections.groupProperty("standard")));
			cr1.add(Restrictions.eq("year", 1));
			cr1.add(Restrictions.eq("branch", deptid));
			List<Integer> standard = cr1.list();
			map.put(1, standard);

		} else
		{

			for (int j = 2; j <= 4; j++)
			{
				Criteria cr2 = getCriteriaForSelect(Student.class);
				cr2.setProjection(Projections.projectionList().add(Projections.groupProperty("standard")));
				cr2.add(Restrictions.eq("year", j));
				cr2.add(Restrictions.eq("branch", deptid));
				List<Integer> standard = cr2.list();
				map.put(j, standard);
			}

		}
		for (Map.Entry<Integer, List<Integer>> countdiv : map.entrySet())
		{

			List<StudentFeeDtl> totaldtl = new ArrayList<>();

			for (Integer division : countdiv.getValue())
			{
				long totalstud = 0;
				long paidstud = 0;
				Criteria cr2 = getCriteriaForSelect(FeeDetails.class);
				cr2.createAlias("student", "student");
				cr2.setProjection(Projections.projectionList().add(Property.forName("student.studentId"), "studentId"));
				cr2.add(Restrictions.eq("student.branch", deptid));
				cr2.add(Restrictions.eq("student.year", countdiv.getKey()));
				cr2.add(Restrictions.eq("student.standard", division));

				List<Integer> studentlist = cr2.list();

				for (Integer stdentid : studentlist)
				{

					Criteria cr1 = getCriteriaForSelect(FeeDetails.class);
					cr1.add(Restrictions.eq("student.studentId", stdentid));
					List<FeeDetails> studid = cr1.list();

					for (FeeDetails feeDetails2 : studid)
					{

						totalstud = totalstud + feeDetails2.getTotal_fee();
						paidstud = paidstud + feeDetails2.getPaid();
					}

				}
				totalstudentfee = new StudentFeeDtl();
				totalstudentfee.setDept_id(deptid);
				totalstudentfee.setDiv(findivisionusingstandard(String.valueOf(division)));
				totalstudentfee.setTotal_fee(totalstud);
				totalstudentfee.setPaid_fee(paidstud);

				totaldtl.add(totalstudentfee);
			}

			studentfeedtl.put(String.valueOf(countdiv.getKey()), totaldtl);
		}
		return studentfeedtl;
	}

	@Override
	public Parentcallrecord saveparentcall(Integer studid, Date date, String remark)
	{
		// TODO Auto-generated method stub
		Parentcallrecord parentcall = null;
		parentcall = new Parentcallrecord();
		parentcall.setCal_Date(date);
		parentcall.setRemark(remark);
		Criteria cr1 = getCriteriaForSelect(Student.class);
		cr1.add(Restrictions.eq("studentId", studid));
		Student studdata = (Student) cr1.uniqueResult();
		parentcall.setStudent(studdata);
		save(parentcall);

		return parentcall;
	}

	@Override
	public List<Mentor> getmentorfprofile()
	{
		// TODO Auto-generated method stub
		Criteria criteria = getCriteriaForSelect(Mentor.class);
		List<Mentor> mentorlist = criteria.list();
		return mentorlist;
	}

	@Override
	public String getgfmrstudemark(Studentskillsdto gfmremark)
	{
		System.out.println("in getgfmrstudemark " + gfmremark);
		RemarkOption remarkoption = null;
		Remarks remark = null;

		if (gfmremark.getMid() != null && gfmremark.getStudentid() != null)
		{
			Criteria cr1 = getCriteriaForSelect(Remarks.class);
			cr1.add(Restrictions.eq("studentid", gfmremark.getStudentid()));
			cr1.add(Restrictions.eq("mid", String.valueOf(gfmremark.getMid())));
			remark = (Remarks) cr1.uniqueResult();
			if (remark == null)
			{
				Remarks remarks = new Remarks();
				remarks.setStudentid(gfmremark.getStudentid());
				remarks.setMid(String.valueOf(gfmremark.getMid()));
				remark = (Remarks) save(remarks);

			}

		}

		String data[] = gfmremark.getTechFields().split("\\,");
		Criteria cr1 = getCriteriaForSelect(RemarkOption.class);
		cr1.add(Restrictions.eq("remarks.remarkid", remark.getRemarkid()));
		List<RemarkOption> list = cr1.list();
		Session session = this.sessionFactory.getCurrentSession();
		for (RemarkOption remarkOption2 : list)
		{

			RemarkOption option = session.get(RemarkOption.class, remarkOption2.getRemarkoptid());
			session.delete(option);
		}
		for (String element : data)
		{

			remarkoption = new RemarkOption();
			remarkoption.setSkillstypeid1(element.split("\\-")[0]);
			remarkoption.setRemarks(remark);
			remarkoption.setStudentid(gfmremark.getStudentid());
			remarkoption.setSkilltype(element.split("\\-")[1]);
			remarkoption.setMid(String.valueOf(gfmremark.getMid()));
			save(remarkoption);

		}

		return null;

	}

	@Override
	public List<RemarkOption> getliststudentremarks(Integer id, Integer mentorid)
	{
		System.out.println("remarkoptionsssss" + id);
		List<RemarkOption> totaldtl = new ArrayList<>();

		Criteria cr1 = getCriteriaForSelect(Remarks.class);
		cr1.setProjection(Projections.projectionList().add(Projections.groupProperty("remarkid")));
		cr1.add(Restrictions.eq("studentid", id));
		cr1.add(Restrictions.eq("mid", String.valueOf(mentorid)));
		List<Integer> remarkid = cr1.list();
		System.out.println("remarklist" + remarkid);

		for (Integer rmkid : remarkid)
		{
			System.out.println("remarkid" + rmkid);
			Criteria cr2 = getCriteriaForSelect(RemarkOption.class);
			cr2.add(Restrictions.eq("remarks.remarkid", rmkid));
			List<RemarkOption> remklist = cr2.list();
			System.out.println("listttttttttttttt" + remklist);
			totaldtl.addAll(remklist);
		}
		return totaldtl;
	}

	@Override
	public HashMap<String, List<String>> getskilllist(Integer studentid, Integer mentorid)
	{

		System.out.println("wenebwhcfbkhefekjfc" + mentorid + "studdddddddddddddd" + studentid);
		List<RemarkOption> totaldtl1 = new ArrayList<>();
		Criteria cr1 = getCriteriaForSelect(Remarks.class);
		cr1.setProjection(Projections.projectionList().add(Projections.groupProperty("remarkid")));
		cr1.add(Restrictions.eq("studentid", studentid));
		cr1.add(Restrictions.eq("mid", String.valueOf(mentorid)));
		List<Integer> remarkid = cr1.list();
		System.out.println("remarklist123" + remarkid);
		HashMap<String, List<String>> map = new HashMap<>();
		List<String> remk = new ArrayList<>();
		List<String> value = new ArrayList<>();

		for (Integer rmkid : remarkid)
		{
			System.out.println("remarkid" + rmkid);
			Criteria cr2 = getCriteriaForSelect(RemarkOption.class);
			cr2.setProjection(Projections.projectionList().add(Projections.groupProperty("skillstypeid1")));
			cr2.add(Restrictions.eq("remarks.remarkid", rmkid));
			remk = cr2.list();
			System.out.println("listttttttttttttt123" + remk);
		}

		for (Integer rmkid : remarkid)
		{
			for (String key : remk)
			{
				Criteria cr3 = getCriteriaForSelect(RemarkOption.class);
				cr3.setProjection(Projections.projectionList().add(Projections.groupProperty("skilltype")));
				cr3.add(Restrictions.eq("remarks.remarkid", rmkid));
				cr3.add(Restrictions.eq("skillstypeid1", key));
				value = cr3.list();
				System.out.println("lisssssssssssssssssssssssssssvalueeeee" + value);
				map.put(key, value);
			}
			System.out.println("mappppppppppp" + map);
		}
		return map;
	}

	@Override
	public String getstudentremark(StudentRemark studentremark)
	{

		System.out.println("studentremarks" + studentremark);
		StudentRemarkOption studremarkopt = null;
		StudentRemark studremk = new StudentRemark();

		/*
		 * Criteria cr = getCriteriaForSelect(StudentRemark.class);
		 * cr.add(Restrictions.eq("studentid", studentremark.getStudentid()));
		 * cr.add(Restrictions.eq("mid", String.valueOf(studentremark.getMid())));
		 * StudentRemark remk = (StudentRemark) cr.uniqueResult();
		 */

		if (studentremark.getMid() != null && studentremark.getStudentid() != null)
		{
			studremk.setStudentid(studentremark.getStudentid());
			studremk.setMid(studentremark.getMid());
			save(studremk);
		}

		for (String remark : studentremark.getRemarkname())
		{
			System.out.println("bvhrwv" + studentremark.getStudentid());
			studremarkopt = new StudentRemarkOption();
			studremarkopt.setStudentid(studentremark.getStudentid());
			studremarkopt.setRemarks(studremk);
			studremarkopt.setRemarkname(remark);
			save(studremarkopt);
		}

		return null;
	}

	@Override
	public List<StudentRemarkOption> getcheckremarkliststud(Integer id, Integer mentorid)
	{
		System.out.println("remarkoptionsssss" + id);
		List<StudentRemarkOption> studentrmklist = new ArrayList<>();

		Criteria cr1 = getCriteriaForSelect(StudentRemark.class);
		cr1.setProjection(Projections.projectionList().add(Projections.groupProperty("studremarkid")));
		cr1.add(Restrictions.eq("studentid", id));
		cr1.add(Restrictions.eq("mid", String.valueOf(mentorid)));
		List<Integer> studremarklist = cr1.list();
		System.out.println("newremarkidlist" + studremarklist);

		for (Integer rmkid : studremarklist)
		{
			System.out.println("remarkid" + rmkid);
			Criteria cr3 = getCriteriaForSelect(StudentRemarkOption.class);
			cr3.setProjection(Projections.projectionList().add(Projections.groupProperty("remarkname")));
			cr3.add(Restrictions.eq("remarks.studremarkid", rmkid));
			List<StudentRemarkOption> remklist = cr3.list();
			studentrmklist.addAll(remklist);

		}

		System.out.println("remarklistststssststststs" + studentrmklist);
		return studentrmklist;
	}

	@Override
	public List<StudentRemarkOption> getstudentremarkview(Integer id, Integer mentorid)
	{
		List<StudentRemarkOption> studentremklist = new ArrayList<>();
		Set<StudentRemarkOption> set = new LinkedHashSet<>();

		Criteria cr1 = getCriteriaForSelect(StudentRemark.class);
		cr1.setProjection(Projections.projectionList().add(Projections.groupProperty("studremarkid")));
		cr1.add(Restrictions.eq("studentid", id));
		cr1.add(Restrictions.eq("mid", String.valueOf(mentorid)));
		List<Integer> studremarklist = cr1.list();
		System.out.println("newremarkidlist" + studremarklist);

		System.out.println("remarkoptionsssss" + id);

		for (Integer rmkid : studremarklist)
		{
			System.out.println("remarkid" + rmkid);
			Criteria cr3 = getCriteriaForSelect(StudentRemarkOption.class);
			cr3.setProjection(Projections.projectionList().add(Projections.groupProperty("remarkname")));
			cr3.add(Restrictions.eq("remarks.studremarkid", rmkid));
			List<StudentRemarkOption> remklist = cr3.list();
			studentremklist.addAll(remklist);
			set.addAll(studentremklist);
			studentremklist.clear();
			studentremklist.addAll(set);
		}
		System.out.println("settttttttttttt" + studentremklist);
		return studentremklist;
	}

	@Override
	public List<StudentRemarkOption> getstudentremarklist(Integer id)
	{
		List<StudentRemarkOption> studentremklist = new ArrayList<>();
		Set<StudentRemarkOption> set = new LinkedHashSet<>();
		Criteria cr3 = getCriteriaForSelect(StudentRemarkOption.class);
		cr3.setProjection(Projections.projectionList().add(Projections.groupProperty("remarkname")));
		cr3.add(Restrictions.eq("studentid", id));
		List<StudentRemarkOption> remklist = cr3.list();
		studentremklist.addAll(remklist);
		set.addAll(studentremklist);
		studentremklist.clear();
		studentremklist.addAll(set);
		System.out.println("studremarklisandroid" + studentremklist);
		return studentremklist;
	}

	@Override
	public String getuserregistration(NewUserDTO user)
	{

		System.out.println("userssssssssssssssssssssss" + user);

		ComUserDetails userDetails = null;
		UserRole role = null;
		ComClientAddress address = null;
		ComClientName clientName = null;
		Staff staff = null;
		String message = "";

		Session session2 = this.sessionFactory.getCurrentSession();
		try
		{
			Session session1 = this.sessionFactory.getCurrentSession();
			Criteria cr = session1.createCriteria(ComClientName.class);
			cr.add(Restrictions.eq("emailId", user.getEmailId()));

			if ((cr.uniqueResult()).equals("null"))
			{
				System.out.println("come in dao class" + user.getEmailId());

			} else
			{
				message = "emailid already exists";
			}
		} catch (NullPointerException e)
		{

			clientName = new ComClientName();
			userDetails = new ComUserDetails();
			address = new ComClientAddress();
			role = new UserRole();
			staff = new Staff();

			clientName.setEmailId(user.getEmailId());
			clientName.setFirstName(user.getFirstName());
			clientName.setLastName(user.getLastName());
			clientName.setRecordStatus("A");
			clientName.setInstituteId(String.valueOf(user.getInstituteid()));
			System.out.println("clientnameeeeeeeeeeeeeeee" + clientName);
			address.setComClientName(clientName);
			address.setRecordStatus("A");
			address.setInstituteId(String.valueOf(user.getInstituteid()));
			System.out.println("adresssssssssssssssssssss" + address);
			userDetails.setComClientName(clientName);
			userDetails.setUserName(user.getEmailId());
			userDetails.setPassword("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
			userDetails.setUserRole(user.getDesignations());
			userDetails.setRecordStatus("A");
			userDetails.setInstituteId(String.valueOf(user.getInstituteid()));
			System.out.println("userdetailsllllllll" + userDetails);
			role.setComUserDetails(userDetails);
			role.setRoleName(user.getDesignations());
			role.setRecordStatus("A");
			role.setInstituteId(String.valueOf(user.getInstituteid()));
			System.out.println("rolsessssssssssssssss" + role);
			staff.setComClientName(clientName);
			staff.setDepartment(user.getDeptid());
			staff.setInstituteId(String.valueOf(user.getInstituteid()));
			staff.setRecordStatus("A");

			System.out.println("stafssssssssssssssssssss" + staff);

			if (user.getDesignations().equals("ROLE_ADMIN"))
			{
				staff.setDesignation("system Admin");
				staff.setStaffType(4);
			}
			if (user.getDesignations().equals("ROLE_HOD"))
			{
				staff.setDesignation("HOD");
				staff.setStaffType(1);
			}
			if (user.getDesignations().equals("ROLE_TEACHER"))
			{
				staff.setDesignation("Assistant Professor");
				staff.setStaffType(2);
			}
			if (user.getDesignations().equals("ROLE_PRINCIPAL"))
			{
				staff.setDesignation("Principal");
				staff.setStaffType(1);
			}
			if (user.getDesignations().equals("ROLE_IT"))
			{
				staff.setDesignation("IT Support");
				staff.setStaffType(6);
			}
			System.out.println("///////////////" + clientName + userDetails + address + role + staff);
			try
			{
				session2.save(clientName);
				session2.save(userDetails);
				session2.save(address);
				session2.save(role);
				session2.save(staff);
			} catch (Exception e2)
			{

				System.out.println("eeeeeeeeeeeee" + e2);
			}

			message = "data saved successfully";
		}
		return message;

	}

	@Override
	public String getuseremailidcheck(String emailid)
	{
		String message12 = "";
		try
		{

			Session session1 = this.sessionFactory.getCurrentSession();
			Criteria cr = session1.createCriteria(ComClientName.class);
			cr.add(Restrictions.eq("emailId", emailid));
			System.out.println("CHECKEMAILID DAOOOOOOOOOOOOOOOOOOOO" + emailid);

			if ((cr.uniqueResult()).equals("null"))
			{
				System.out.println("come in dao class" + cr.uniqueResult());
			} else
			{
				message12 = "emailid already exists";
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return message12;
	}

	@Override
	public List<ResultInstituteList> getpdfresultlist()
	{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Department.class);
		criteria.setProjection(Projections.projectionList().add(Property.forName("dep_id"), "dep_id")
				.add(Property.forName("dep_name"), "dep_name"));
		criteria.setResultTransformer(Transformers.aliasToBean(DepartmentDTO.class));
		List<DepartmentDTO> departmentlist = criteria.list();

		Session session1 = sessionFactory.getCurrentSession();
		Criteria criteria1 = session1.createCriteria(Resultsemester.class);
		List<Resultsemester> semlist = criteria1.list();
		System.out.println("semesterlist" + semlist);

		Session session2 = sessionFactory.getCurrentSession();
		Criteria criteria2 = session1.createCriteria(ResultYear.class);
		List<ResultYear> yearlist = criteria2.list();
		System.out.println("academicyearlist" + yearlist);

		List<ResultInstituteList> resulist = new ArrayList<>();

		for (ResultYear academicYear : yearlist)
		{
			for (Resultsemester semid : semlist)
			{
				for (DepartmentDTO department : departmentlist)
				{

					Criteria marksheet = getCriteriaForSelect(ResultMarksheet.class);
					marksheet.add(Restrictions.eq("year", academicYear.getYear()));
					marksheet.add(Restrictions.eq("semister", semid.getSemester()));
					marksheet.add(Restrictions.eq("branch", String.valueOf(department.getDep_id())));
					marksheet.setProjection(Projections.projectionList().add(Property.forName("year"), "year")
							.add(Property.forName("semister"), "semister").add(Property.forName("branch"), "branch")
							.add(Property.forName("academiyr"), "academiyr"));
					marksheet.setResultTransformer(Transformers.aliasToBean(ResultInstituteList.class));
					List<ResultInstituteList> resltlist = marksheet.list();
					System.out.println("resultlist" + resltlist);
					ArrayList<ResultInstituteList> wordDulicate = new ArrayList<>();
					List<ResultInstituteList> newlist = new ArrayList<>();
					Set<ResultInstituteList> set = new HashSet<>();

					for (ResultInstituteList dupWord : resltlist)
					{
						if (!newlist.contains(dupWord))
						{
							wordDulicate.add(dupWord);
							break;
						}
					}
					set.addAll(wordDulicate);
					resulist.addAll(wordDulicate);
				}

			}

		}
		return resulist;
	}

	@Override
	public List<Resultsemester> getresultsemester()
	{
		Session session1 = sessionFactory.getCurrentSession();
		Criteria criteria1 = session1.createCriteria(Resultsemester.class);
		List<Resultsemester> semlist = criteria1.list();

		System.out.println("semesterlist" + semlist);
		return semlist;
	}

	@Override
	public List<ResultInstituteList> getInstituteResult()
	{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Department.class);
		criteria.setProjection(Projections.projectionList().add(Property.forName("dep_id"), "dep_id")
				.add(Property.forName("dep_name"), "dep_name"));
		criteria.setResultTransformer(Transformers.aliasToBean(DepartmentDTO.class));
		List<DepartmentDTO> departmentlist = criteria.list();

		Session session1 = sessionFactory.getCurrentSession();
		Criteria criteria1 = session1.createCriteria(Resultsemester.class);
		List<Resultsemester> semlist = criteria1.list();
		System.out.println("semesterlist" + semlist);

		Session session2 = sessionFactory.getCurrentSession();
		Criteria criteria2 = session2.createCriteria(ResultYear.class);
		List<ResultYear> yearlist = criteria2.list();
		System.out.println("academicyearlist" + yearlist);

		Session session6 = sessionFactory.getCurrentSession();
		Criteria criteria6 = session6.createCriteria(ResultMarksheet.class);
		criteria6.setProjection(Projections.projectionList().add(Property.forName("instituteId"), "instituteId"));
		List<String> institutelist = criteria6.list();
		Set<String> set = new LinkedHashSet<>();
		set.addAll(institutelist);
		institutelist.clear();
		institutelist.addAll(set);
		List<ResultInstituteList> resulist = new ArrayList<>();

		for (String institutecode : institutelist)
		{
			for (ResultYear academicYear : yearlist)
			{
				for (Resultsemester semid : semlist)
				{
					for (DepartmentDTO department : departmentlist)
					{
						Session session3 = sessionFactory.getCurrentSession();
						Criteria marksheet = session3.createCriteria(ResultMarksheet.class);
						marksheet.add(Restrictions.eq("year", academicYear.getYear()));
						marksheet.add(Restrictions.eq("instituteId", institutecode));
						marksheet.add(Restrictions.eq("semister", semid.getSemester()));
						marksheet.add(Restrictions.eq("branch", String.valueOf(department.getDep_id())));

						marksheet.setProjection(Projections.projectionList().add(Property.forName("year"), "year")
								.add(Property.forName("semister"), "semister").add(Property.forName("branch"), "branch")
								.add(Property.forName("academiyr"), "academiyr")
								.add(Property.forName("collegename"), "collegename")
								.add(Property.forName("instituteId"), "instituteId"));

						marksheet.setResultTransformer(Transformers.aliasToBean(ResultInstituteList.class));
						List<ResultInstituteList> resltlist = marksheet.list();

						System.out.println("resultlist" + resltlist);
						ArrayList<ResultInstituteList> wordDulicate = new ArrayList<>();
						List<ResultInstituteList> newlist = new ArrayList<>();

						for (ResultInstituteList dupWord : resltlist)
						{
							if (!newlist.contains(dupWord))
							{
								wordDulicate.add(dupWord);
								break;
							}
						}
						resulist.addAll(wordDulicate);

					}

				}

			}
		}
		return resulist;
	}

	@Override
	public String getsuperadminreport(Integer AcadamicYear, Integer deptid, Integer semId, Integer instid)
	{
		System.out.println("come in dao class" + AcadamicYear + "departmentid" + deptid + "semesterid" + semId
				+ "instituteid" + instid);
		Session session = sessionFactory.getCurrentSession();
		String year = "";
		System.out.println("acdemics year" + AcadamicYear);
		Criteria cr = session.createCriteria(AcademicSubject.class);
		cr.add(Restrictions.eq("department.dep_id", deptid));

		if ((semId % 2) == 0)
		{
			System.out.println("come in if conditon subject");
			System.out.println("semid" + semId);
			if (semId == 2)
			{
				String sem1 = "1";
				String sem2 = "2";
				cr.add(Restrictions.between("semister", sem1, sem2));
				System.out.println("testing" + cr.list());
			} else if (semId == 4)
			{
				String sem3 = "3";
				String sem4 = "4";
				cr.add(Restrictions.between("semister", sem3, sem4));
				System.out.println("testing" + cr.list());
			} else if (semId == 6)
			{
				String sem5 = "5";
				String sem6 = "6";
				cr.add(Restrictions.between("semister", sem5, sem6));
				System.out.println("testing" + cr.list());
			} else if (semId == 8)
			{
				String sem7 = "7";
				String sem8 = "8";
				cr.add(Restrictions.between("semister", sem7, sem8));
				System.out.println("testing" + cr.list());
			}
		} else
		{
			String sem = Integer.toString(semId);
			cr.add(Restrictions.eq("semister", sem));
			System.out.println("testing2" + cr.list());
		}

		String staffcollegename = null;
		List<AcademicSubject> academicSubjects = cr.list();

		Criteria criteria = session.createCriteria(Department.class);
		criteria.add(Restrictions.eq("dep_id", deptid));
		Department deptname1 = (Department) criteria.uniqueResult();

		if (semId == 1 || semId == 2)
		{
			year = "F.E.";
		} else if (semId == 4 || semId == 3)
		{
			year = "S.E.";

		} else if (semId == 5 || semId == 6)
		{
			year = "T.E.";

		} else
		{
			year = "B.E.";
		}
		System.out.println("years" + year);
		Session session5 = this.sessionFactory.getCurrentSession();
		Criteria cr4 = session5.createCriteria(ResultStudent.class);
		cr4.add(Restrictions.eq("academiyr", year));
		cr4.add(Restrictions.eq("year", AcadamicYear));
		cr4.add(Restrictions.eq("branch", deptid));
		cr4.add(Restrictions.eq("instituteId", String.valueOf(instid)));
		List<ResultStudent> deptlist = cr4.list();

		for (ResultStudent resultStudent : deptlist)
		{
			staffcollegename = resultStudent.getStudcollegename();
			System.out.println("depname" + staffcollegename);
			break;
		}

		System.out.println("academicssub" + academicSubjects.size());

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
		cell.setCellValue(staffcollegename);

		row = sheet.createRow(1);
		cell = row.createCell(0);

		cell.setCellValue("Acadamic Year");
		cell = row.createCell(1);
		cell.setCellValue(AcadamicYear);

		row = sheet.createRow(2);
		cell = row.createCell(0);

		cell.setCellValue("Semester");
		cell = row.createCell(1);
		cell.setCellValue(semId);

		row = sheet.createRow(3);
		cell = row.createCell(0);

		cell.setCellValue("Department");
		cell = row.createCell(1);
		cell.setCellValue(deptname1.getDep_name());

		row = sheet.createRow(4);
		cell = row.createCell(0);
		cell.setCellValue("Sr.No");

		cell = row.createCell(1);
		cell.setCellValue("Subject Name");

		cell = row.createCell(2);
		cell.setCellValue("Total Student");

		cell = row.createCell(3);
		cell.setCellValue("Passed Student");

		cell = row.createCell(4);
		cell.setCellValue("Fail Student");

		cell = row.createCell(5);
		cell.setCellValue("Subject Marks%");

		int count = 5;
		int srno = 1;

		List<Object[]> test = new ArrayList<>();

		for (AcademicSubject academicSubject : academicSubjects)
		{
			Session session6 = this.sessionFactory.getCurrentSession();
			Criteria cr1 = session5.createCriteria(ResultMarksheet.class);
			cr1.setProjection(Projections.projectionList().add(Projections.groupProperty("subresult"))
					.add(Projections.rowCount()));
			cr1.add(Restrictions.ne("grade", "F"));
			cr1.add(Restrictions.eq("year", AcadamicYear));
			cr1.add(Restrictions.eq("instituteId", String.valueOf(instid)));
			cr1.add(Restrictions.eq("subresult.sub_id", academicSubject.getSub_id()));
			test = cr1.list();

			row = sheet.createRow(count);
			cell = row.createCell(0);
			cell.setCellValue(srno);

			cell = row.createCell(1);
			cell.setCellValue(academicSubject.getSubject_name());
			System.out.println("last.........sub lsist...." + test);
			try
			{

				for (Object[] objects : test)
				{
					System.out.println("object sub test...." + objects);

					DecimalFormat f = new DecimalFormat("##.00");

					System.out.println("pass student...." + objects[1]);

					System.out.println("percentage......" + f.format((((Long) objects[1]).floatValue()
							/ (Long) gettotalstudcount(academicSubject, AcadamicYear, null, instid) * 100)));

					cell = row.createCell(2);
					cell.setCellValue((Long) gettotalstudcount(academicSubject, AcadamicYear, null, instid));

					cell = row.createCell(3);
					cell.setCellValue(objects[1].toString());

					Long toatl = (Long) gettotalstudcount(academicSubject, AcadamicYear, null, instid);
					cell = row.createCell(4);
					cell.setCellValue(toatl - ((Long) objects[1]).floatValue());

					cell = row.createCell(5);
					cell.setCellValue(f.format((((Long) objects[1]).floatValue() / toatl * 100)));

				}

			} catch (Exception e)
			{
				// TODO: handle exception
				System.out.println("eee" + e);
			}
			count++;
			srno++;
		}

		// write it as an excel attachment
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		try
		{
			wb.write(outByteStream);
			byte[] outArray = outByteStream.toByteArray();
			responses.setContentType("application/ms-excel");
			responses.setContentLength(outArray.length);
			responses.setHeader("Expires:", "0"); // eliminates browser caching
			responses.setHeader("Content-Disposition", "attachment; filename=Result_Report" + AcadamicYear + ".xls");
			OutputStream outStream = responses.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
			outStream.close();

		} catch (Exception e)
		{
			System.out.println("excepotion......" + e);
		}
		System.out.println("report downloadddddddddddddddddddddddddddddddddd");
		return "null";

	}

	public Object gettotalstudcount(AcademicSubject subject, Integer exam_yr, Integer sem_id, Integer instid)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(ResultMarksheet.class);
		cr.setProjection(Projections.projectionList().add(Projections.rowCount()));
		cr.add(Restrictions.eq("year", exam_yr));
		cr.add(Restrictions.eq("instituteId", String.valueOf(instid)));
		cr.add(Restrictions.in("subresult", subject));
		return cr.uniqueResult();
	}

	@Override
	public List<ResultInstituteList> getcommoninstitudereport()
	{

		Session session5 = sessionFactory.getCurrentSession();
		Criteria cr = session5.createCriteria(ResultAcadyear.class);
		List<ResultAcadyear> resultyear = cr.list();

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Department.class);
		criteria.setProjection(Projections.projectionList().add(Property.forName("dep_id"), "dep_id")
				.add(Property.forName("dep_name"), "dep_name"));
		criteria.setResultTransformer(Transformers.aliasToBean(DepartmentDTO.class));
		List<DepartmentDTO> deptlist = criteria.list();

		Session session2 = sessionFactory.getCurrentSession();
		Criteria criteria2 = session2.createCriteria(ResultYear.class);
		List<ResultYear> year = criteria2.list();
		List<ResultInstituteList> resulist = new ArrayList<>();

		for (DepartmentDTO department : deptlist)
		{

			for (ResultYear resultYear : year)
			{

				for (ResultAcadyear ayr : resultyear)
				{
					Session session3 = sessionFactory.getCurrentSession();
					Criteria marksheet = session3.createCriteria(ResultMarksheet.class);
					marksheet.add(Restrictions.eq("year", resultYear.getYear()));
					marksheet.add(Restrictions.eq("academiyr", ayr.getAcademicyear()));
					marksheet.add(Restrictions.eq("branch", String.valueOf(department.getDep_id())));
					marksheet.setProjection(Projections.projectionList().add(Property.forName("year"), "year")
							.add(Property.forName("branch"), "branch").add(Property.forName("academiyr"), "academiyr"));
					marksheet.setResultTransformer(Transformers.aliasToBean(ResultInstituteList.class));

					List<ResultInstituteList> resltlist = marksheet.list();

					System.out.println("resultlist" + resltlist);
					ArrayList<ResultInstituteList> wordDulicate = new ArrayList<>();
					List<ResultInstituteList> newlist = new ArrayList<>();
					Set<ResultInstituteList> set = new HashSet<>();

					for (ResultInstituteList dupWord : resltlist)
					{
						if (!newlist.contains(dupWord))
						{
							wordDulicate.add(dupWord);
							break;
						}
					}
					set.addAll(wordDulicate);
					resulist.addAll(wordDulicate);
				}
			}
		}
		System.out.println("resulttttttttttttt" + resulist);
		return resulist;
	}

	@Override
	public String getcommonsuperadmindeptreport(Integer aid, Integer bid, String cid)
	{
		System.out.println("come in dao class" + aid + "departmentid" + bid + "semesterid" + cid);
		Session session = sessionFactory.getCurrentSession();
		String year = "";
		System.out.println("acdemics year" + aid);

		Criteria cr = session.createCriteria(AcademicSubject.class);
		cr.add(Restrictions.eq("department.dep_id", bid));
		if (cid.equalsIgnoreCase("F.E."))
		{
			String sem1 = "1";
			String sem2 = "2";
			cr.add(Restrictions.between("semister", sem1, sem2));
			System.out.println("testing" + cr.list());
		} else if (cid.equalsIgnoreCase("S.E."))
		{
			String sem3 = "3";
			String sem4 = "4";
			cr.add(Restrictions.between("semister", sem3, sem4));
			System.out.println("testing" + cr.list());
		} else if (cid.equalsIgnoreCase("T.E."))
		{
			String sem5 = "5";
			String sem6 = "6";
			cr.add(Restrictions.between("semister", sem5, sem6));
			System.out.println("testing" + cr.list());
		} else if (cid.equalsIgnoreCase("B.E."))
		{
			String sem7 = "7";
			String sem8 = "8";
			cr.add(Restrictions.between("semister", sem7, sem8));
			System.out.println("testing" + cr.list());
		}
		String staffcollegename = null;
		List<AcademicSubject> academicSubjects = cr.list();

		Criteria criteria = session.createCriteria(Department.class);
		criteria.add(Restrictions.eq("dep_id", bid));
		Department deptname1 = (Department) criteria.uniqueResult();

		System.out.println("years" + year);

		System.out.println("academicssub" + academicSubjects.size());

		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletResponse responses = ((ServletRequestAttributes) requestAttributes).getResponse();
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		HSSFRow row = sheet.createRow(0);
		sheet.addMergedRegion(new CellRangeAddress(0, // first
														// row
														// (0-based)
				0, // last row (0-based)
				0, // first column (0-based)
				6 // last column (0-based)
		));
		row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell = row.createCell(0);
		cell.setCellValue("Common Institute Result Report");

		row = sheet.createRow(1);
		cell = row.createCell(0);

		cell.setCellValue("Acadamic Year");
		cell = row.createCell(1);
		cell.setCellValue(cid);

		row = sheet.createRow(2);
		cell = row.createCell(0);

		cell.setCellValue("Semester");
		cell = row.createCell(1);
		cell.setCellValue(cid);

		row = sheet.createRow(3);
		cell = row.createCell(0);

		cell.setCellValue("Department");
		cell = row.createCell(1);
		cell.setCellValue(deptname1.getDep_name());

		row = sheet.createRow(4);
		cell = row.createCell(0);
		cell.setCellValue("Sr.No");

		cell = row.createCell(1);
		cell.setCellValue("Subject Name");

		cell = row.createCell(2);
		cell.setCellValue("Total Student");

		cell = row.createCell(3);
		cell.setCellValue("Passed Student");

		cell = row.createCell(4);
		cell.setCellValue("Fail Student");

		cell = row.createCell(5);
		cell.setCellValue("Subject Marks%");

		int count = 5;
		int srno = 1;
		List<Object[]> test = new ArrayList<>();
		for (AcademicSubject academicSubject : academicSubjects)
		{
			Session session6 = this.sessionFactory.getCurrentSession();
			Criteria cr1 = session6.createCriteria(ResultMarksheet.class);
			cr1.setProjection(Projections.projectionList().add(Projections.groupProperty("subresult"))
					.add(Projections.rowCount()));
			cr1.add(Restrictions.ne("grade", "F"));
			cr1.add(Restrictions.eq("year", aid));
			cr1.add(Restrictions.eq("subresult.sub_id", academicSubject.getSub_id()));
			test = cr1.list();

			row = sheet.createRow(count);
			cell = row.createCell(0);
			cell.setCellValue(srno);

			cell = row.createCell(1);
			cell.setCellValue(academicSubject.getSubject_name());
			System.out.println("last.........sub lsist...." + test);
			try
			{

				for (Object[] objects : test)
				{
					System.out.println("object sub test...." + objects);

					DecimalFormat f = new DecimalFormat("##.00");

					System.out.println("pass student...." + objects[1]);

					System.out.println("percentage......" + f.format((((Long) objects[1]).floatValue()
							/ (Long) getuniversitycount(academicSubject, aid, cid) * 100)));

					cell = row.createCell(2);
					cell.setCellValue((Long) getuniversitycount(academicSubject, aid, cid));

					cell = row.createCell(3);
					cell.setCellValue(objects[1].toString());

					Long toatl = (Long) getuniversitycount(academicSubject, aid, cid);
					cell = row.createCell(4);
					cell.setCellValue(toatl - ((Long) objects[1]).floatValue());

					cell = row.createCell(5);
					cell.setCellValue(f.format((((Long) objects[1]).floatValue() / toatl * 100)));

				}

			} catch (Exception e)
			{
				// TODO: handle exception
				System.out.println("eee" + e);
			}
			count++;
			srno++;
		}
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		try
		{
			wb.write(outByteStream);
			byte[] outArray = outByteStream.toByteArray();
			responses.setContentType("application/ms-excel");
			responses.setContentLength(outArray.length);
			responses.setHeader("Expires:", "0"); // eliminates browser caching
			responses.setHeader("Content-Disposition", "attachment; filename=Result_Report" + aid + ".xls");
			OutputStream outStream = responses.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
			outStream.close();
		} catch (Exception e)
		{
			System.out.println("excepotion......" + e);
		}
		return "null";

	}

	public Object getuniversitycount(AcademicSubject subject, Integer exam_yr, String cid)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(ResultMarksheet.class);
		cr.setProjection(Projections.projectionList().add(Projections.rowCount()));
		cr.add(Restrictions.eq("year", exam_yr));
		cr.add(Restrictions.eq("academiyr", cid));
		cr.add(Restrictions.in("subresult", subject));
		return cr.uniqueResult();
	}
}