package com.zertones.dao.sims;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
import com.zertones.model.ComClientAddress;
import com.zertones.model.ComClientName;
import com.zertones.model.ComUserDetails;
import com.zertones.model.StudentForm;
import com.zertones.model.DataTransferObjectModel.ComClientNameDTO;
import com.zertones.model.common.AdminForm;
import com.zertones.model.common.Days;
import com.zertones.model.master.AcademicSemester;
import com.zertones.model.master.AcademicYear;
import com.zertones.model.sims.Mentor;
import com.zertones.model.sims.MentorStudent;
import com.zertones.model.sims.Staff;
import com.zertones.model.sims.Student;
import com.zertones.security.model.UserRole;
import com.zertones.service.Constants;
import com.zertones.service.common.NotificationService;

/**
 * @author Zerton Team
 * @Created Date : Oct 6, 2015
 */
@Repository
public class StaffDAOImpl extends BaseDAOImpl implements StaffDAO
{
	private static final Logger	logger	= LoggerFactory.getLogger(StaffDAOImpl.class);
	@Autowired
	protected SessionFactory	sessionFactory;

	@Autowired
	private NotificationService	notificationService;

	@Override
	public void setSessionFactory(SessionFactory sf)
	{
		this.sessionFactory = sf;
	}

	@Override
	public Staff staffSignUp(Staff staff)
	{
		logger.info("Saving Staff : ");
		Session session = this.sessionFactory.getCurrentSession();
		session.save(staff.getComClientName());
		session.save(staff.getComClientName().getComUserDetails());
		session.persist(staff);
		logger.info("Staff saved successfully, Staff Details = " + staff);
		session.flush();
		session.close();
		return staff;
	}

	@Override
	public List<Mentor> getMentorProfile()
	{

		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery(" select distinct m.staff from Mentor m ");
		return q.list();
	}

	@Override
	public Staff saveStaffProfile(Staff staff, MultipartFile image)
	{
		logger.info("Updating Staff ");
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		ComClientName client = session.get(ComClientName.class, staff.getComClientName().getId());
		client.setFirstName(staff.getComClientName().getFirstName());
		client.setLastName(staff.getComClientName().getLastName());
		client.setContactNos(staff.getComClientName().getContactNos());
		client.setEmailId(staff.getComClientName().getEmailId());
		try
		{
			if (!image.isEmpty())
			{
				client.setImgUrl(notificationService.uploadImageOnCloud(image));
				// client.setProfileImage(image.getBytes());
				// stf.setProfileImg(image.getBytes());
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		Staff stf = new Staff();
		if (staff.getDesignation().equals("Student"))
		{
			session.update(client);
			stf.setComClientName(client);
			stf.setDesignation("Student");
			return stf;
		} else
		{
			stf = session.get(Staff.class, staff.getStaffId());

			session.update(stf);
			logger.info("Staff Updated successfully...");
			return stf;
		}
	}

	@Override
	@Transactional
	public List<ComClientNameDTO> getStaffList()
	{
		Criteria criteria = getCriteriaForSelect(Staff.class);
		criteria.createAlias("comClientName", "ClientName");
		criteria.setProjection(Projections.projectionList().add(Projections.property("ClientName.id"), "id")
				.add(Projections.property("ClientName.firstName"), "firstName")
				.add(Projections.property("ClientName.middleName"), "middleName")
				.add(Projections.property("ClientName.lastName"), "lastName"));
		criteria.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));
		return criteria.list();
	}

	@Override
	@Transactional
	public List<ComClientNameDTO> getStaffList(Integer staffID)
	{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria1 = session.createCriteria(Staff.class);
		criteria1.add(Restrictions.eq("comClientName.id", staffID));
		Staff staff = (Staff) criteria1.uniqueResult();

		List<ComClientName> cList = new ArrayList<>();
		Criteria criteria = session.createCriteria(Staff.class, "staff");
		criteria.createAlias("staff.comClientName", "ClientName");
		criteria.setProjection(Projections.projectionList().add(Projections.property("ClientName.id"), "id")
				.add(Projections.property("ClientName.firstName"), "firstName")
				.add(Projections.property("ClientName.middleName"), "middleName")
				.add(Projections.property("ClientName.lastName"), "lastName"));
		criteria.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		criteria.add(Restrictions.eq("department", staff.getDepartment()));
		criteria.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));
		return criteria.list();
	}

	@Override
	@Transactional
	public String saveStaffInfoViaExcelFile(MultipartFile excelFile) throws Exception
	{
		String message = "OOps...! Error Occued while reading a file.";
		System.out.println("Test in uploding user excel file ");
		ComUserDetails userDetails = null;
		UserRole role = null;
		ComClientAddress address = null;
		ComClientName clientName = null;
		Staff staff = null;
		XSSFWorkbook wb;

		wb = new XSSFWorkbook(excelFile.getInputStream());
		XSSFSheet sheet;
		Row row;
		sheet = wb.getSheetAt(0);
		System.out.println("Row count" + sheet.getLastRowNum());
		for (int i = 1; i <= sheet.getLastRowNum(); i++)
		{
			row = sheet.getRow(i);

			System.out.println("column count" + row.getLastCellNum());

			for (int j = 1; j <= row.getLastCellNum(); j++)
			{
				System.out.println("cell 1 :" + row.getCell(1).toString());
				clientName = new ComClientName();
				userDetails = new ComUserDetails();
				address = new ComClientAddress();
				role = new UserRole();
				staff = new Staff();
				clientName.setFirstName(row.getCell(1).toString());
				clientName.setMiddleName(row.getCell(2).toString());
				clientName.setLastName(row.getCell(3).toString());
				if (row.getCell(4).toString().equalsIgnoreCase("F")
						|| row.getCell(4).toString().equalsIgnoreCase("female"))
				{
					clientName.setGender(2);
				} else
				{
					clientName.setGender(1);
				}
				clientName.setDateOfBirth(row.getCell(5).getDateCellValue());
				clientName.setEmailId(row.getCell(6).toString());
				clientName.setContactNos(row.getCell(7).toString());
				clientName.setTermsCondition(false);
				if (row.getCell(8).toString().equalsIgnoreCase("YES"))
				{
					clientName.setIsHandicaped(1);
				} else
				{
					clientName.setIsHandicaped(0);
				}
				address.setAddress1(row.getCell(9).toString());
				address.setCity(row.getCell(10).toString());
				address.setState(row.getCell(11).toString());
				address.setPostalCode(row.getCell(12).toString());
				address.setCountry(row.getCell(13).toString());
				address.setComClientName(clientName);
				userDetails.setComClientName(clientName);
				userDetails.setUserName(row.getCell(14).toString());
				userDetails.setPassword(row.getCell(15).toString());
				userDetails.setUserRole(row.getCell(16).toString());
				role.setRoleName(row.getCell(16).toString());
				role.setComUserDetails(userDetails);
				staff.setRegistrationNo(row.getCell(17).toString());
				staff.setEmployeeNo(row.getCell(18).toString());
				staff.setDesignation(row.getCell(19).toString());
				staff.setStaffType((int) row.getCell(20).getNumericCellValue());
				staff.setDepartment((int) row.getCell(21).getNumericCellValue());
				staff.setUniversityEnrollNo(row.getCell(22).toString());
				staff.setComClientName(clientName);

			}
			if (!getUserDtl(userDetails))
			{
				save(clientName);
				save(address);
				save(userDetails);
				save(role);
				save(staff);
				message = "File Upload Successfully ...!!";
			} else
			{
				message = "File is already exist  ...!!";
			}
		}
		wb.close();

		return message;
	}

	@Override
	@Transactional
	public boolean saveMentorProfile(Mentor mentor, Integer[] students)
	{

		boolean result = false;
		Staff stf = (Staff) getListById("From Staff", mentor.getStaff().getComClientName().getId(), "comClientName.id")
				.get(0);
		Set<MentorStudent> set = new HashSet();

		for (Integer clientId : students)
		{
			MentorStudent m = new MentorStudent();
			m.setMentor(mentor);
			m.setStudent((Student) getListById("From Student", clientId, "comClientName.id").get(0));
			set.add(m);

		}

		mentor.setStaff(stf);
		mentor.setMentorStudent(set);
		System.out.println("mentorrrrrrrrrr" + mentor);
		if (save(mentor) != null)
		{
			result = true;
		}
		return result;
	}

	@Override
	@Transactional
	public List<AcademicSemester> getSemisterList()
	{
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from AcademicSemester";
		Query query = session.createQuery(hql);
		List<AcademicSemester> list = query.list();
		return list;
	}

	@Override
	@Transactional
	public List<AcademicYear> getyearList()
	{
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from AcademicYear";
		Query query = session.createQuery(hql);
		System.out.println("List Of Student For Event :" + query.list());
		List<AcademicYear> list = query.list();
		System.out.println("list::" + list);
		return list;
	}

	@Override
	@Transactional
	public List<Days> getDaysList()
	{
		// TODO Auto-generated method stub
		Criteria cr = getCriteriaForSelect(Days.class);
		cr.setProjection(Projections.projectionList().add(Property.forName("day_vid"), "day_vid")
				.add(Property.forName("day_name"), "day_name"));
		cr.setResultTransformer(Transformers.aliasToBean(Days.class));
		return cr.list();
	}

	@Override
	@Transactional
	public String updateAdminForm(AdminForm admin)
	{
		System.out.println("admmmmm" + admin);
		// TODO Auto-generated method stub
		String message = "Email id allrady Present";
		ComUserDetails userDetails = null;
		UserRole role = null;
		ComClientAddress address = null;
		ComClientName clientName = null;
		Staff staff = null;

		clientName = new ComClientName();
		userDetails = new ComUserDetails();
		address = new ComClientAddress();
		role = new UserRole();
		staff = new Staff();

		if (!findUser(admin.getEmailId()))
		{
			clientName.setFirstName(admin.getFirstName());
			clientName.setMiddleName(admin.getMiddleName());
			clientName.setLastName(admin.getLastName());
			clientName.setEmailId(admin.getEmailId());
			clientName.setContactNos(admin.getContactNos());
			// clientName.setUpdateStatus("false");
			address.setComClientName(clientName);
			userDetails.setComClientName(clientName);
			userDetails.setUserName(admin.getEmailId());
			// String password = generatePswd(admin.getFirstName(), admin.getLastName());
			userDetails.setPassword("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
			userDetails.setUserRole(admin.getDesignation());
			role.setComUserDetails(userDetails);
			role.setRoleName(admin.getDesignation());
			staff.setComClientName(clientName);
			staff.setDepartment(admin.getDepartment());
			if (admin.getDesignation().equals("ROLE_ADMIN"))
			{
				staff.setDesignation("system Admin");
				staff.setStaffType(4);
			}
			if (admin.getDesignation().equals("ROLE_HOD"))
			{
				staff.setDesignation("HOD");
				staff.setStaffType(1);
			}
			if (admin.getDesignation().equals("ROLE_TEACHER"))
			{
				staff.setDesignation("Assistant Professor");
				staff.setStaffType(2);
			}
			if (admin.getDesignation().equals("ROLE_PRINCIPAL"))
			{
				staff.setDesignation("Principal");
				staff.setStaffType(1);
			}
			if (admin.getDesignation().equals("ROLE_IT"))
			{
				staff.setDesignation("IT Support");
				staff.setStaffType(6);
			}

			{
				try
				{
					save(clientName);
					save(userDetails);
					save(address);
					save(role);
					save(staff);
					// javaSendMailService.sendPasswordToMail(admin.getEmailId(),
					// admin.getFirstName(), password);
					// message = "data upload Sucsessfully";
				} catch (Exception e)
				{
					// TODO: handle exception
					System.out.println("Exception::::");
					message = "errorr";
				}
			}
			message = "data uploaded succesfully ...!! Add Another user";
		}
		System.out.println("Data Uploaded Successfully");
		return message;
	}

	@Override
	@Transactional
	public List<ComClientNameDTO> getStaffListbydept(Integer client_id)
	{
		// TODO Auto-generated method stub
		System.out.println("client id" + client_id);
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria1 = session.createCriteria(Staff.class);
		criteria1.add(Restrictions.eq("comClientName.id", client_id));
		Staff staff = (Staff) criteria1.uniqueResult();
		// staff.getDepartment();
		Criteria cr = session.createCriteria(Staff.class);
		cr.add(Restrictions.eq("department", staff.getDepartment()));
		List<Staff> list = cr.list();
		return cr.list();
	}

	@Override
	@Transactional
	public String updatestaffData(StudentForm staff, Integer clientId)
	{
		// TODO Auto-generated method stub
		System.out.println("data coming in dao" + staff);
		Session session = this.sessionFactory.getCurrentSession();
		ComClientName client = session.get(ComClientName.class, clientId);

		String hql = "select id from ComClientAddress where CLIENT_ID=" + clientId;
		Query q = session.createQuery(hql);
		Object addressId = q.uniqueResult();
		int id = (Integer) addressId;
		ComClientAddress add = session.get(ComClientAddress.class, id);

		String hql1 = "select staffId from Staff where CLIENT_ID=" + clientId;
		Query q1 = session.createQuery(hql1);
		Object StaffId = q1.uniqueResult();
		int id1 = (Integer) StaffId;
		Staff staffs = session.get(Staff.class, id1);

		// client name table fields..........
		client.setFirstName(staff.getFirstName());
		client.setLastName(staff.getLastName());
		client.setMiddleName(staff.getMiddleName());
		client.setEmailId(staff.getEmailId());
		client.setContactNos(staff.getContactNos());
		client.setMotherName(staff.getMotherName());
		// client.setContactNo2(staff.getContactNo2());

		System.out.println("[[[[" + staff.getDateOfBirth());
		if (staff.getDateOfBirth().equals(""))
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

				String d = (staff.getDateOfBirth() + " 00:00:00"); //
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
		add.setAddress1(staff.getAddress1());
		add.setCity(staff.getCity());
		add.setCountry(staff.getCountry());
		add.setPostalCode(staff.getPostalCode());
		add.setState(staff.getState());

		// staff table fields............
		staffs.setDepartment(staff.getBranch());
		staffs.setDesignation(staff.getDesignation());
		staffs.setEmployeeNo(staff.getEmployeeNo());
		if (staff.getDesignation().equals("Professor"))
		{
			staffs.setStaffType(2);
		} else if (staff.getDesignation().equals("HOD"))
		{
			staffs.setStaffType(1);
		} else if (staff.getDesignation().equals("Administrator"))
		{
			staffs.setStaffType(4);
		} else if (staff.getDesignation().equals("Principal"))
		{
			staffs.setStaffType(4);
		}

		System.out.println("data Is................" + staffs);

		session.update(client);
		session.update(add);
		session.update(staffs);

		return null;
	}

	@Override
	@Transactional
	public List<Staff> getallStaffList()
	{
		// TODO Auto-generated method stub
		Criteria criteria1 = getCriteriaForSelect(Staff.class);
		criteria1.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		List<Staff> list = criteria1.list();
		System.out.println("staffflistttt" + list);
		return list;
	}

	@Override
	@Transactional
	public List<ComClientNameDTO> getStaffListbydepts(Integer dept)
	{
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<ComClientName> cList = new ArrayList<>();
		Criteria criteria = session.createCriteria(Staff.class, "staff");
		criteria.createAlias("staff.comClientName", "ClientName");
		criteria.setProjection(Projections.projectionList().add(Projections.property("ClientName.id"), "id")
				.add(Projections.property("ClientName.firstName"), "firstName")
				.add(Projections.property("ClientName.middleName"), "middleName")
				.add(Projections.property("ClientName.lastName"), "lastName"));
		criteria.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		criteria.add(Restrictions.eq("department", dept));
		criteria.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));
		return criteria.list();
	}

	@Override
	@Transactional
	public void deletestaff(int clientID)
	{
		// TODO Auto-generated method stub

		System.out.println("............//////////..../." + clientID);

		Session session = sessionFactory.getCurrentSession();
		Staff staff = (Staff) session.createCriteria(Staff.class).add(Restrictions.eq("comClientName.id", clientID))
				.uniqueResult();

		System.out.println("stud id......." + staff.getStaffId());

		staff.setRecordStatus(Constants.DELETED_RECORD_STATUS);
		staff.setDepartment(0);

		ComUserDetails comUserDetails = (ComUserDetails) session.createCriteria(ComUserDetails.class)
				.add(Restrictions.eq("comClientName.id", clientID)).uniqueResult();
		comUserDetails.setRecordStatus(Constants.DELETED_RECORD_STATUS);
		comUserDetails.setUserName("delete" + clientID + "@gmail.com");

		ComClientName clientName = session.get(ComClientName.class, clientID);
		clientName.setRecordStatus(Constants.DELETED_RECORD_STATUS);
		clientName.setEmailId("delete" + clientID + "@gmail.com");

	}
}
