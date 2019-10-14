package com.zertones.dao.sims;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
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
import com.zertones.dao.UserDetailsDAO;
import com.zertones.model.ComClientAddress;
import com.zertones.model.ComClientName;
import com.zertones.model.ComUser;
import com.zertones.model.ComUserDetails;
import com.zertones.model.StudentForm;
import com.zertones.model.DataTransferObjectModel.ComClientNameDTO;
import com.zertones.model.DataTransferObjectModel.ExtraActivityDTO;
import com.zertones.model.DataTransferObjectModel.Info;
import com.zertones.model.common.BatchTable;
import com.zertones.model.common.Department;
import com.zertones.model.common.ExtraActivities;
import com.zertones.model.common.FeeDetails;
import com.zertones.model.common.GroupMembers;
import com.zertones.model.common.Groups;
import com.zertones.model.sims.Mentor;
import com.zertones.model.sims.MentorStudent;
import com.zertones.model.sims.Parent;
import com.zertones.model.sims.Staff;
import com.zertones.model.sims.Student;
import com.zertones.security.model.UserRole;
import com.zertones.service.Constants;
import com.zertones.service.common.JavaSendMailService;

/**
 * @author Zerton Team
 * @Created Date : Oct 6, 2015
 */
@Repository
@Transactional
public class StudentDAOImpl extends BaseDAOImpl implements StudentDAO
{
	private static final Logger	logger	= LoggerFactory.getLogger(StudentDAOImpl.class);
	@Autowired
	protected SessionFactory	sessionFactory;

	@Autowired
	protected UserDetailsDAO	userDao;

	@Autowired
	private JavaSendMailService	javaSendMailService;

	@Override
	public void setSessionFactory(SessionFactory sf)
	{
		this.sessionFactory = sf;
	}

	@Override
	public Student studentSignUp(Student student)
	{
		logger.debug("Saving Student :  ");
		signup(student.getComClientName());
		UserRole role = new UserRole();
		role.setComUserDetails((ComUserDetails) signup(student.getComClientName().getComUserDetails()));
		role.setRoleName((student.getComClientName().getComUserDetails().getUserRole()));
		ComClientAddress address = new ComClientAddress();
		address.setComClientName(student.getComClientName());
		signup(address);
		signup(role);
		signup(student);
		return student;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getStudentInfo()
	{
		logger.info("fetching Student");
		List<Student> ListOfStudent = get("from Student");
		return ListOfStudent;
	}

	@Override
	@Transactional
	public List<Student> getStudentInfoById(Integer StudId)
	{
		@SuppressWarnings("unchecked")
		List<Student> ListOfStudentById = getListById("from Student", StudId, "studentId");
		return ListOfStudentById;
	}

	@Override
	@Transactional
	public List<Student> getStudentList(Integer depId, Integer divId)
	{
		List<Student> ListStudent = getListForEvent(depId, divId);
		return ListStudent;
	}

	@Override
	@Transactional
	public List<ComClientNameDTO> getStudentList()
	{
		ComClientName c = getUserDetails().getComClientName();
		List<Department> depList = getDepartment();
		Info info = getDepartment(c);
		Integer depId = info.getDep();
		Criteria criteria = getCriteriaForSelect(Student.class);
		criteria.createAlias("comClientName", "ClientName");
		criteria.setProjection(Projections.projectionList().add(Projections.property("year"), "year_id")
				.add(Projections.property("branch"), "depId").add(Projections.property("ClientName.id"), "id")
				.add(Projections.property("ClientName.firstName"), "firstName")
				.add(Projections.property("ClientName.lastName"), "lastName"));
		criteria.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));
		List<ComClientNameDTO> l = criteria.list();
		for (ComClientNameDTO comClientNameDTO : l)
		{
			// Department d = getDepartment(comClientNameDTO.getDepId());
			for (Department department : depList)
			{
				if (comClientNameDTO.getDepId() != null)
				{
					if (department.getDep_id() == comClientNameDTO.getDepId())
					{
						comClientNameDTO.setDepName(department.getDep_name());
					}
				}
			}
			comClientNameDTO.setYear(getYearName(comClientNameDTO.getYear_id()));
		}
		return l;
	}

	@Override
	public List<ComClientNameDTO> getStudentListByDepartment()
	{
		ComClientName c = getUserDetails().getComClientName();
		List<Department> depList = getDepartment();
		Info info = getDepartment(c);
		Integer depId = info.getDep();
		Criteria criteria = getCriteriaForSelect(Student.class);// session.createCriteria(Student.class, "student");
		criteria.createAlias("comClientName", "ClientName");
		criteria.setProjection(Projections.projectionList().add(Projections.property("year"), "year_id")
				.add(Projections.property("branch"), "depId").add(Projections.property("ClientName.id"), "id")
				.add(Projections.property("ClientName.firstName"), "firstName")
				.add(Projections.property("ClientName.middleName"), "middleName")
				.add(Projections.property("ClientName.lastName"), "lastName"));
		criteria.add(Restrictions.eq("branch", depId));
		criteria.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));
		List<ComClientNameDTO> l = criteria.list();
		for (ComClientNameDTO comClientNameDTO : l)
		{
			// Department d = getDepartment(comClientNameDTO.getDepId());

			if (comClientNameDTO.getDepId() != null)
			{
				for (Department department : depList)
				{
					if (department.getDep_id() == comClientNameDTO.getDepId())
					{
						comClientNameDTO.setDepName(department.getDep_name());
					}
				}
			}
			comClientNameDTO.setYear(getYearName(comClientNameDTO.getYear_id()));
		}
		return l;
	}

	@Override
	@Transactional
	public List<ComClientNameDTO> getStudentList(Integer gid)
	{
		// Session session = sessionFactory.getCurrentSession();
		// Criteria criteria = session.createCriteria(Student.class, "student");
		Criteria criteria;
		ComClientName c = getUserDetails().getComClientName();
		List<Department> depList = getDepartment();
		Info info = getDepartment(c);
		Integer depId = info.getDep();
		List<Integer> clientId = getExistMemberList(gid);
		if (ckeckGroupCategory(clientId))
		{
			criteria = getCriteriaForSelect(Staff.class);
		} else
		{
			criteria = getCriteriaForSelect(Student.class);
		}
		criteria.createAlias("comClientName", "ClientName");
		criteria.setProjection(Projections.projectionList().add(Projections.property("ClientName.id"), "id")
				.add(Projections.property("ClientName.firstName"), "firstName")
				.add(Projections.property("ClientName.middleName"), "middleName")
				.add(Projections.property("ClientName.lastName"), "lastName"));
		criteria.add(Restrictions.not(Restrictions.in("ClientName.id", clientId)));
		criteria.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));
		// return criteria.list();
		List<ComClientNameDTO> l = criteria.list();
		for (ComClientNameDTO comClientNameDTO : l)
		{
			// Department d = getDepartment(comClientNameDTO.getDepId());
			for (Department department : depList)
			{
				if (comClientNameDTO.getDepId() != null)
				{

					if (department.getDep_id() == comClientNameDTO.getDepId())
					{
						comClientNameDTO.setDepName(department.getDep_name());
					}
				}
			}
			comClientNameDTO.setYear(getYearName(comClientNameDTO.getYear_id()));
		}
		return l;
	}

	public Boolean ckeckGroupCategory(List<Integer> clientId)
	{
		Boolean reBoolean = true;

		for (Integer integer : clientId)
		{
			Criteria c = getCriteriaForSelect(Staff.class);
			c.add(Restrictions.eq("comClientName.id", integer));
			// System.out.println("List :" + c.list() + "Client id " + integer);
			if (c.list().isEmpty())
			{
				reBoolean = false;
			}
		}

		return reBoolean;
	}

	public List<Integer> getExistMemberList(Integer gid)
	{
		Criteria c = getCriteriaForSelect(GroupMembers.class);
		c.setProjection(Projections.projectionList().add(Property.forName("comClientName.id"), "CLIENT_ID"));
		c.add(Restrictions.eq("groups.groupId", gid));
		c.setResultTransformer(CriteriaSpecification.PROJECTION);
		return c.list();
	}

	@Override
	@Transactional
	public List<ComClientNameDTO> getStudentListByDep(Integer depId)
	{

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Student.class, "student");
		criteria.createAlias("student.comClientName", "ClientName");
		criteria.setProjection(Projections.projectionList().add(Projections.property("ClientName.id"), "id")
				.add(Projections.property("ClientName.firstName"), "firstName")
				.add(Projections.property("ClientName.middleName"), "middleName")
				.add(Projections.property("ClientName.lastName"), "lastName"));
		criteria.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		criteria.add(Restrictions.eq("branch", depId));
		criteria.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));
		return criteria.list();

	}

	@Override
	@Transactional
	public List<Department> getDepartment()
	{

		return get("from Department");
	}

	@Override
	@Transactional
	public String saveStudetInfoViaExcelFile(MultipartFile excelFile) throws Exception
	{
		String message = "OOps..! Error Occured while processing file.";
		ComUserDetails userDetails = null;
		UserRole role = null;
		ComClientAddress address = null;
		ComClientName clientName = null;
		Student student = null;
		XSSFWorkbook wb;
		wb = new XSSFWorkbook(excelFile.getInputStream());
		XSSFSheet sheet;

		Row row;
		sheet = wb.getSheetAt(0);
		if (sheet.getLastRowNum() < 2)
		{
			message = "File is empty..!";
		}
		for (int i = 1; i <= sheet.getLastRowNum(); i++)
		{
			row = sheet.getRow(i);

			for (int j = 1; j < row.getLastCellNum(); j++)
			{
				clientName = new ComClientName();
				userDetails = new ComUserDetails();
				address = new ComClientAddress();
				role = new UserRole();
				student = new Student();
				clientName.setFirstName(row.getCell(1).toString());
				clientName.setMiddleName(row.getCell(2).toString());
				clientName.setLastName(row.getCell(3).toString());
				clientName.setMotherName(row.getCell(4).toString());
				clientName.setTermsCondition(false);
				if (row.getCell(5).toString().equalsIgnoreCase("F")
						|| row.getCell(5).toString().equalsIgnoreCase("Female"))
				{
					clientName.setGender(2);
				} else
				{
					clientName.setGender(1);
				}
				clientName.setDateOfBirth(row.getCell(6).getDateCellValue());
				clientName.setEmailId(row.getCell(7).toString());
				clientName.setContactNos(row.getCell(8).toString());
				if (row.getCell(9).toString().equalsIgnoreCase("Yes"))
				{
					clientName.setIsHandicaped(1);
				} else
				{
					clientName.setIsHandicaped(0);
				}
				address.setAddress1(row.getCell(10).toString());
				address.setCity(row.getCell(11).toString());
				address.setState(row.getCell(12).toString());
				address.setPostalCode(row.getCell(13).toString());
				address.setCountry(row.getCell(14).toString());
				address.setComClientName(clientName);
				userDetails.setComClientName(clientName);
				userDetails.setUserName(row.getCell(15).toString());
				userDetails.setPassword(row.getCell(16).toString());
				userDetails.setUserRole(row.getCell(17).toString());
				role.setRoleName(row.getCell(17).toString());
				role.setComUserDetails(userDetails);
				student.setRegistrationNo(row.getCell(18).toString());
				student.setRollNo(row.getCell(19).toString());
				student.setStandard((int) row.getCell(20).getNumericCellValue());
				student.setYear((int) row.getCell(21).getNumericCellValue());
				student.setBranch((int) row.getCell(22).getNumericCellValue());
				student.setUniversityEnrollNo(row.getCell(23).toString());
				student.setComClientName(clientName);
			}
			if (!getUserDtl(userDetails))
			{
				save(clientName);
				save(address);
				save(userDetails);
				save(role);
				save(student);
				message = "File Upload Successfully..!!";
			} else
			{
				message = "Duplicate recored...!!";
			}
		}
		wb.close();

		return message;
	}

	@Override
	@Transactional
	public ComClientName upadteStudentProfile(ComClientName student)
	{
		student.setProfileImage(null);

		Object obj = update(student);
		if (obj != null)
		{
			student = (ComClientName) get(ComClientName.class, student.getId());
		}
		try
		{
			Info info = getDepartment(student);
			student.setDepartment(info.getDep());
			student.setInfo(info);
		} catch (Exception e)
		{
			// TODO: handle exception
		}

		return student;

	}

	@Override
	@Transactional
	public ComClientName setStudentCredential(ComUser comUser)
	{
		ComClientName clientName = (ComClientName) getUser("from ComClientName", "id", comUser.getId());
		System.out.println("Set new Pass");
		if (comUser.getOldCredential() != null)
		{
			if (userDao.enCoder256(comUser.getOldCredential()).equals(clientName.getComUserDetails().getPassword()))
			{
				clientName.getComUserDetails().setPassword(userDao.enCoder256(comUser.getNewCredential()));
				clientName = (ComClientName) save(clientName);
			}
		}
		return clientName;
	}

	@Override
	public List<Mentor> setStudentListForMentor(Integer id)
	{

		Criteria cr = getCriteriaForSelect(Mentor.class);
		cr.add(Restrictions.eq("id", id));

		// Criteria c = getCriteriaForSelect(Mentor.class);
		// c.add(Restrictions.eq("staff.staffId", id));
		// Mentor mentors = (Mentor) c.list().get(0);
		//
		// Criteria cr = getCriteriaForSelect(MentorStudent.class);
		// cr.setProjection(Projections.property("student.studentId"));
		// cr.add(Restrictions.eq("mentor.id", mentors.getId()));
		// cr.setResultTransformer(CriteriaSpecification.PROJECTION);
		// // System.out.println(" Test :" + );
		//
		// Criteria criteria = getCriteriaForSelect(Student.class);
		// criteria.createAlias("comClientName", "ClientName");
		// criteria.setProjection(Constants.ComClientName_POJECTION);
		// criteria.add(Restrictions.not(Restrictions.in("ClientName.id", cr.list())));
		// criteria.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));

		List<Mentor> mentor = cr.list();

		return mentor;

	}

	@Override
	public List<MentorStudent> getStudentListRemove(Integer id)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(MentorStudent.class, "ms");
		cr.createAlias("ms.mentor", "m");
		cr.add(Restrictions.eq("m.id", id));
		cr.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		List<MentorStudent> mentorStudents = cr.list();
		return mentorStudents;
	}

	@Override
	public List<ComClientNameDTO> getStudentListForAddMentorMember(Integer id)
	{
		System.out.println("PrintigTest:" + id);
		return getStudentlist(id);
	}

	public List<ComClientNameDTO> getStudentlist(Integer id)
	{

		// Session session = this.sessionFactory.getCurrentSession();
		// Criteria cr = session.createCriteria(Student.class, "student");
		// Criteria cr = getCriteriaForSelect(Student.class);
		// // cr.createAlias("Student", "student");
		// cr.createAlias("comClientName", "ClientName");
		// cr.setProjection(Projections.projectionList().add(Projections.property("studentId"))
		// .add(Projections.property("ClientName.firstName")).add(Projections.property("ClientName.lastName"))
		// .add(Projections.property("ClientName.middleName")))
		// .add(Restrictions.not(Restrictions.in("studentId",
		// listOfMentorStudent(id))));
		// List<Object> object = cr.list();
		// return object;

		ComClientName c = getUserDetails().getComClientName();
		List<Department> depList = getDepartment();
		Info info = getDepartment(c);
		Integer depId = info.getDep();
		Criteria criteria = getCriteriaForSelect(Student.class);// session.createCriteria(Student.class, "student");
		criteria.createAlias("comClientName", "ClientName");
		criteria.setProjection(Projections.projectionList().add(Projections.property("year"), "year_id")
				.add(Projections.property("branch"), "depId").add(Projections.property("ClientName.id"), "id")
				.add(Projections.property("ClientName.firstName"), "firstName")
				.add(Projections.property("ClientName.middleName"), "middleName")
				.add(Projections.property("ClientName.lastName"), "lastName"));
		// criteria.add(Restrictions.eq("branch", id));
		criteria.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));
		List<ComClientNameDTO> l = criteria.list();
		for (ComClientNameDTO comClientNameDTO : l)
		{
			// Department d = getDepartment(comClientNameDTO.getDepId());
			for (Department department : depList)
			{
				if (comClientNameDTO.getDepId() != null)
				{
					if (department.getDep_id() == comClientNameDTO.getDepId())
					{
						comClientNameDTO.setDepName(department.getDep_name());
					}
				}
			}
			comClientNameDTO.setYear(getYearName(comClientNameDTO.getYear_id()));
		}
		return l;
	}

	public List<MentorStudent> listOfMentorStudent(Integer id)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(MentorStudent.class).add(Restrictions.eq("mentor.id", id))
				.setProjection(Projections.projectionList().add(Projections.property("student.studentId")));
		List<MentorStudent> mentorStudents = cr.list();
		return mentorStudents;

	}

	@Override
	@Transactional
	public Boolean addMentorMember(Mentor m, Integer[] i)
	{

		System.out.println("mmmmm" + m.getId());
		Criteria criteria2 = getCriteria(Groups.class);
		criteria2.add(Restrictions.eq("gfm_id", m.getId()));
		Groups grp = (Groups) criteria2.uniqueResult();

		Object obj = new Object();
		for (Integer integer : i)
		{
			System.out.println("come in group mentor");
			GroupMembers gm = new GroupMembers();
			ComClientName c = new ComClientName();
			c.setId(integer);
			gm.setGroups(grp);
			gm.setComClientName(c);
			obj = save(gm);
		}

		Boolean result = false;
		MentorStudent s = null;
		for (Integer integer : i)
		{

			Criteria criteria = getCriteria(Student.class);
			criteria.add(Restrictions.eq("comClientName.id", integer));
			Student student = (Student) criteria.uniqueResult();

			Criteria criteria1 = getCriteria(MentorStudent.class);
			criteria1.add(Restrictions.eq("mentor.id", m.getId()));
			criteria1.add(Restrictions.eq("student.studentId", student.getStudentId()));

			if (criteria1.uniqueResult() == null)
			{
				System.out.println("come in mentor add member");
				s = new MentorStudent();
				s.setMentor(m);
				s.setStudent(student);
				System.out.println("student mentor adddd" + s);
				save(s);
			}
		}
		return result;
	}

	@Override
	@Transactional
	public Integer removeMentorMember(Integer[] i)
	{
		return deleteMentorStuddent(i);
	}

	@Override
	public List<BatchTable> getBatchList(Integer SubId)
	{
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from BatchTable where Sub_Id=" + SubId;
		// System.out.println("query::" + hql);
		Query query = session.createQuery(hql);
		List<BatchTable> list = query.list();
		return list;
	}

	@Override
	@Transactional
	public List<Student> getVerifyingStud()
	{
		// TODO Auto-generated method stub
		Boolean isMatch = false;
		ComUserDetails user = getUserDetails();
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Student where comClientName.vstatus =" + isMatch
				+ " and comClientName.recordStatus = 'D' and comClientName.instituteId =" + user.getInstituteId();
		Query query = session.createQuery(hql);
		List<Student> list = query.list();
		return list;
	}

	@Override
	@Transactional
	public Boolean updateverifystud(Integer id)
	{
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(ComClientName.class, "ComClientName");
		cr.add(Restrictions.eq("ComClientName.id", id));
		ComClientName stud = (ComClientName) cr.uniqueResult();
		stud.setVstatus(true);
		stud.setRecordStatus("A");
		stud.getComUserDetails().setPassword("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
		stud.getComUserDetails().setRecordStatus(Constants.ACTIVE_RECORD_STATUS);

		Student student = (Student) session.createCriteria(Student.class).add(Restrictions.eq("comClientName.id", id))
				.uniqueResult();
		student.setRecordStatus(Constants.ACTIVE_RECORD_STATUS);

		String name = stud.getFirstName() + " " + stud.getLastName();
		String Password = "admin";
		String Ccode = stud.getInstituteId();
		javaSendMailService.sendVerifiedStudentPasswordToMail(stud.getEmailId(), name, Password, Ccode);
		// javaSendMailService.sendVerifiedStudentPasswordToMail(mail, Password, name);

		session.update(stud);
		return true;
	}

	@Override
	@Transactional
	public Boolean deleteverifystud(Integer id)
	{
		// TODO Auto-generated method stub

		Session session = this.sessionFactory.getCurrentSession();
		Criteria c1 = session.createCriteria(ComClientName.class, "ComClientName");
		c1.add(Restrictions.eq("ComClientName.id", id));
		ComClientName studs = (ComClientName) c1.uniqueResult();

		System.out.println("bean....." + studs);
		String name = studs.getFirstName() + " " + studs.getLastName();
		studs.getEmailId();
		javaSendMailService.sendMailToDeclineStudent(studs.getEmailId(), name);
		// javaSendMailService.sendMailToDeclineStudent(mail, name);

		Criteria cr = session.createCriteria(Student.class, "Student");
		cr.add(Restrictions.eq("Student.comClientName.id", id));
		Student stud = (Student) cr.uniqueResult();
		System.out.println("second student bean:::" + stud);
		session.delete(stud);

		return true;
	}

	@Override
	@Transactional
	public Student getStudentDetails(Integer clientId)
	{
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		String hql1 = "select studentId from Student where CLIENT_ID=" + clientId;
		Query q11 = session.createQuery(hql1);
		Object query = q11.uniqueResult();
		int i = (Integer) query;

		return session.get(Student.class, i);
		// return null;
	}

	@Override
	@Transactional
	public ComClientName getStudentData(Integer clientId)
	{
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(ComClientName.class, clientId);

	}

	@Override
	@Transactional
	public Integer updateStudentform(StudentForm stud)
	{

		Session session = this.sessionFactory.getCurrentSession();
		ComClientName client = session.get(ComClientName.class, stud.getClient_id());

		String hql = "select id from ComClientAddress where CLIENT_ID=" + stud.getClient_id();
		Query q = session.createQuery(hql);
		Object addressId = q.uniqueResult();
		int id = (Integer) addressId;
		ComClientAddress add = session.get(ComClientAddress.class, id);

		String hql1 = "select studentId from Student where CLIENT_ID=" + stud.getClient_id();
		Query q1 = session.createQuery(hql1);
		Object StudId = q1.uniqueResult();
		int id1 = (Integer) StudId;
		Student student = session.get(Student.class, id1);

		// client name table fields..........
		client.setFirstName(stud.getFirstName());
		client.setLastName(stud.getLastName());
		client.setMiddleName(stud.getMiddleName());
		client.setEmailId(stud.getEmailId());
		client.setContactNos(stud.getContactNos());
		client.setMotherName(stud.getMotherName());
		client.setContactNo2(stud.getContactNo2());

		System.out.println("[[[[" + stud.getDateOfBirth());
		if (stud.getDateOfBirth().equals(""))
		{
			System.out.println("date not change");

		} else
		{
			// DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			/* SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS"); */

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			Date fromDate = null;
			try
			{

				String d = (stud.getDateOfBirth() + " 00:00:00"); //
				// System.out.println(".,,,,.,.,.,,.,.," + stud.getDateOfBirth()); fromDate =
				fromDate = df.parse(d);

				System.out.println("...........date....." + df);

			} catch (Exception e)
			{
				System.out.println("Exception In get notification " + e.getLocalizedMessage());
			}
			client.setDateOfBirth(fromDate);
		}

		client.setUpdateStatus("true");

		// address table fields...........
		System.out.println("addresss is" + stud.getAddress1());

		add.setAddress1(stud.getAddress1());
		add.setCity(stud.getCity());
		add.setCountry(stud.getCountry());
		add.setPostalCode(stud.getPostalCode());
		add.setState(stud.getState());

		// student table fields............
		student.setBranch(stud.getBranch());
		// student.setGrade(stud.getGrade());
		student.setRollNo(stud.getRollNo());
		student.setStandard(stud.getStandard());
		student.setUniversityEnrollNo(stud.getUniversityEnrollNo());
		student.setYear(stud.getYear());
		student.setBatch(stud.getBatch());

		session.update(client);
		session.update(add);
		session.update(student);

		System.out.println(".....................Befor Save.........................");
		ComUserDetails userDetails = null;
		UserRole role = null;
		ComClientAddress address = null;
		ComClientName clientName = null;
		Parent parent = null;
		clientName = new ComClientName();
		userDetails = new ComUserDetails();
		address = new ComClientAddress();
		role = new UserRole();
		parent = new Parent();

		System.out.println(".....................After Save.........................");

		System.out.println(".,.,.,.,.,.,.," + stud.getStudentId());

		Criteria criteria = getCriteriaForSelect(Parent.class);
		criteria.add(Restrictions.eq("Stud_id", stud.getStudentId()));
		Parent p = (Parent) criteria.uniqueResult();
		System.out.println(".............//////....../////parent " + p);

		if (p == null)
		{
			clientName.setFirstName(stud.getMiddleName());
			clientName.setLastName(stud.getLastName());
			clientName.setEmailId(stud.getParentEmail());
			clientName.setContactNos(stud.getParentContactNo1());
			clientName.setContactNo2(stud.getParentContactNo2());
			clientName.setRecordStatus(Constants.ACTIVE_RECORD_STATUS);
			clientName.setParentstudcid(stud.getClient_id());
			clientName.setTermsCondition(false);

			address.setComClientName(clientName);
			address.setAddress1(stud.getAddress1());

			userDetails.setComClientName(clientName);
			userDetails.setPassword("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
			userDetails.setUserName(stud.getParentContactNo1());
			userDetails.setUserRole("ROLE_PARENT");
			userDetails.setRecordStatus(Constants.ACTIVE_RECORD_STATUS);

			role.setComUserDetails(userDetails);
			role.setRecordStatus(Constants.ACTIVE_RECORD_STATUS);
			role.setRoleName("ROLE_PARENT");

			parent.setComClientName(clientName);
			parent.setStud_id(stud.getStudentId());
			parent.setContact_no1(stud.getParentContactNo1());
			parent.setEmail(stud.getParentEmail());
			parent.setRecordStatus(Constants.ACTIVE_RECORD_STATUS);

			save(clientName);
			save(userDetails);
			save(role);
			save(parent);

		} else
		{
			int pid = p.getParentid();
			parent = session.get(Parent.class, pid);
			parent.setContact_no1(stud.getParentContactNo1());
			parent.setEmail(stud.getParentEmail());

			clientName = session.get(ComClientName.class, p.getComClientName().getId());
			clientName.setEmailId(stud.getParentEmail());
			clientName.setContactNos(stud.getParentContactNo1());
			clientName.setParentstudcid(stud.getClient_id());

			userDetails = (ComUserDetails) session.createCriteria(ComUserDetails.class)
					.add(Restrictions.eq("comClientName.id", p.getComClientName().getId())).uniqueResult();
			userDetails.setUserName(stud.getParentContactNo1());

			session.update(parent);
			session.update(clientName);
			session.update(userDetails);
		}

		return stud.getClient_id();
	}

	@Override
	public List<Parent> getParentDetails(Integer clientId)
	{

		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria1 = session.createCriteria(Student.class);
		criteria1.add(Restrictions.eq("comClientName.id", clientId));
		Student stud = (Student) criteria1.uniqueResult();
		System.out.println("........." + stud.getStudentId());

		String hql1 = "from Parent where Stud_id=" + stud.getStudentId();
		Query q11 = session.createQuery(hql1);
		List<Parent> list = q11.list();

		return list;
	}

	@Override
	@Transactional

	public List<Student> getStudentbysortList(Integer yr, Integer div, Integer client_id)
	{
		// TODO Auto-generated method stub
		// System.out.println(" data is comming dao" + yr + div + client_id);
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria1 = session.createCriteria(Staff.class);
		criteria1.add(Restrictions.eq("comClientName.id", client_id));
		Staff staff = (Staff) criteria1.uniqueResult();
		Criteria cr = session.createCriteria(Student.class);
		cr.add(Restrictions.eq("year", yr));
		cr.add(Restrictions.eq("standard", div));
		cr.add(Restrictions.eq("branch", staff.getDepartment()));
		List<Student> list = cr.list();
		return list;
	}

	@Override
	@Transactional
	public void deleteStudent(int clientID)
	{
		Session session = sessionFactory.getCurrentSession();
		Student student = (Student) session.createCriteria(Student.class)
				.add(Restrictions.eq("comClientName.id", clientID)).uniqueResult();

		System.out.println("stud id......." + student.getStudentId());
		String hql = "DELETE FROM MentorStudent WHERE STUDENT_ID = :STUDENT_ID12";
		Query query = session.createQuery(hql);
		query.setParameter("STUDENT_ID12", student.getStudentId());
		int result = query.executeUpdate();

		String hql1 = "DELETE FROM GroupMembers WHERE CLIENT_ID = :CLIENT_ID1";
		Query query1 = session.createQuery(hql1);
		query1.setParameter("CLIENT_ID1", clientID);
		int result2 = query1.executeUpdate();

		student.setRecordStatus(Constants.DELETED_RECORD_STATUS);
		student.setBranch(0);

		ComUserDetails comUserDetails = (ComUserDetails) session.createCriteria(ComUserDetails.class)
				.add(Restrictions.eq("comClientName.id", clientID)).uniqueResult();
		comUserDetails.setRecordStatus(Constants.DELETED_RECORD_STATUS);
		comUserDetails.setUserName("delete" + clientID + "@gmail.com");

		ComClientName clientName = session.get(ComClientName.class, clientID);
		clientName.setRecordStatus(Constants.DELETED_RECORD_STATUS);
		clientName.setEmailId("delete" + clientID + "@gmail.com");

	}

	@Override
	@Transactional
	public List<ComClientNameDTO> getStudentlist()
	{

		Session session = sessionFactory.getCurrentSession();
		ComUserDetails user = getUserDetails();
		Criteria criteria = session.createCriteria(Student.class, "student");
		criteria.createAlias("student.comClientName", "ClientName");
		criteria.setProjection(Projections.projectionList().add(Projections.property("ClientName.id"), "id")
				.add(Projections.property("ClientName.firstName"), "firstName")
				.add(Projections.property("ClientName.middleName"), "middleName")
				.add(Projections.property("ClientName.lastName"), "lastName"));
		criteria.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		criteria.add(Restrictions.eq(Constants.INSTITUTE_VARIABLE_NAME, user.getInstituteId()));
		criteria.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));
		return criteria.list();
	}

	@Override
	@Transactional
	public List<Student> getStudentListByDept(Integer dep)
	{
		// TODO Auto-generated method stub
		// Session session = sessionFactory.getCurrentSession();
		Integer year[] =
		{ 1, 2, 3, 4 };

		Criteria criteria = getCriteriaForSelect(Student.class);
		criteria.add(Restrictions.eq("branch", dep));
		criteria.add(Restrictions.in("year", year));
		List<Student> list = criteria.list();

		return criteria.list();
	}

	@Override
	public List<ComClientNameDTO> getforthyrstudent()
	{
		Criteria cr = getCriteriaForSelect(Student.class);
		cr.createAlias("comClientName", "comClientName");
		cr.setProjection(Projections.projectionList().add(Property.forName("comClientName.id"), "id")
				.add(Property.forName("comClientName.firstName"), "firstName")
				.add(Property.forName("comClientName.middleName"), "middleName")
				.add(Property.forName("comClientName.lastName"), "lastName")
				.add(Property.forName("comClientName.emailId"), "emailId").add(Property.forName("branch"), "depId"));
		cr.add(Restrictions.eq("year", 4));
		cr.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));
		List<ComClientNameDTO> list = cr.list();
		return list;

	}

	@Override

	public List<ExtraActivityDTO> getStudentinternshipbysortList()
	{

		Criteria cr = getCriteriaForSelect(ExtraActivities.class);
		cr.createAlias("comClientName", "comClientName");
		cr.setProjection(Projections.projectionList().add(Property.forName("comClientName.firstName"), "firstName")
				.add(Property.forName("comClientName.lastName"), "lastName")
				.add(Property.forName("comClientName.middleName"), "middleName")
				.add(Property.forName("organizer"), "organizer")
				.add(Property.forName("coordinateName"), "coordinateName")
				.add(Property.forName("className"), "className").add(Property.forName("dateOfEvent"), "dateOfEvent")
				.add(Property.forName("toDate"), "toDate"));
		cr.add(Restrictions.eq("eventType", 8));
		cr.setResultTransformer(Transformers.aliasToBean(ExtraActivityDTO.class));
		List<ExtraActivityDTO> list = cr.list();
		return list;
	}

	@Override
	public List<ExtraActivities> getFutureInteresttList()
	{
		Criteria cr = getCriteriaForSelect(ExtraActivities.class);
		cr.add(Restrictions.eq("eventType", 9));
		List<ExtraActivities> list = cr.list();
		return list;
	}

	@Override
	public List<FeeDetails> getfeestructureofstudent(Integer id)
	{
		// TODO Auto-generated method stub
		System.out.println("menter id" + id);
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(MentorStudent.class, "menterstudent");
		// Criteria cr = getCriteriaForSelect(MentorStudent.class);
		cr.add(Restrictions.eq("mentor.id", id));
		List<MentorStudent> list = cr.list();

		System.out.println("menter student list" + list);
		List<FeeDetails> dtl = new ArrayList<>();
		FeeDetails student = null;
		for (MentorStudent mstud : list)
		{
			student = new FeeDetails();
			Criteria cr1 = getCriteriaForSelect(FeeDetails.class);
			cr1.add(Restrictions.eq("student.studentId", mstud.getStudent().getStudentId()));
			FeeDetails feedtl = (FeeDetails) cr1.uniqueResult();

			if (feedtl == null)
			{
				// System.out.println("....../..../........../......./." + feedtl);
				student.setStudent(mstud.getStudent());
				student.setOutstanding(0);
				student.setPaid(0);
				student.setRemaining(0);
				student.setTotal_fee(0);
				student.setCategory(null);
				student.setInstallment1(0);
				student.setInstallment2(0);
				student.setInstallment3(0);
				student.setInstallment4(0);
				dtl.add(student);
			} else
			{

				// System.out.println("....../..../........../......./." + feedtl);
				student.setStudent(feedtl.getStudent());
				student.setOutstanding(feedtl.getOutstanding());
				student.setPaid(feedtl.getPaid());
				student.setRemaining(feedtl.getRemaining());
				student.setTotal_fee(feedtl.getTotal_fee());
				student.setCategory(feedtl.getCategory());
				student.setInstallment1(feedtl.getInstallment1());
				student.setInstallment2(feedtl.getInstallment2());
				student.setInstallment3(feedtl.getInstallment3());
				student.setInstallment4(feedtl.getInstallment4());

				dtl.add(student);
			}
		}
		System.out.println("feeeeee student list" + dtl);

		return dtl;

	}

	@Override
	public List<Parent> getparentdtlofstudent(Integer id)
	{
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(MentorStudent.class, "menterstudent");
		// Criteria cr = getCriteriaForSelect(MentorStudent.class);
		cr.add(Restrictions.eq("mentor.id", id));
		List<MentorStudent> list = cr.list();

		List<Parent> dtl = new ArrayList<>();
		Parent parent = null;
		for (MentorStudent mstud : list)
		{
			parent = new Parent();
			Criteria cr1 = getCriteriaForSelect(Parent.class);
			cr1.add(Restrictions.eq("Stud_id", mstud.getStudent().getStudentId()));
			Parent prntdtl = (Parent) cr1.uniqueResult();

			System.out.println("prntdtl..................." + prntdtl);
			if (prntdtl == null)
			{
				parent.setComClientName(mstud.getStudent().getComClientName());
				parent.setContact_no1("Parent Dtl Not Present");
				parent.setStud_id(0);
				dtl.add(parent);
			} else
			{
				parent.setComClientName(mstud.getStudent().getComClientName());
				parent.setContact_no1(prntdtl.getContact_no1());
				parent.setStud_id(prntdtl.getStud_id());
				dtl.add(parent);

			}

		}
		System.out.println("/,,,,,,,,,.///////,,,,,,,,,,./." + dtl);
		return dtl;
	}

}
