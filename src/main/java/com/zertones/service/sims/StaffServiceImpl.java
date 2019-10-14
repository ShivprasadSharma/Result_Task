package com.zertones.service.sims;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.zertones.dao.sims.StaffDAO;
import com.zertones.model.StudentForm;
import com.zertones.model.DataTransferObjectModel.ComClientNameDTO;
import com.zertones.model.common.AdminForm;
import com.zertones.model.common.Days;
import com.zertones.model.master.AcademicSemester;
import com.zertones.model.master.AcademicYear;
import com.zertones.model.sims.Mentor;
import com.zertones.model.sims.Staff;

/**
 * @author Zerton Team
 * @Created Date : Oct 6, 2015
 */
@Service("staffService")
public class StaffServiceImpl implements StaffService
{
	@Autowired
	private StaffDAO stafftDAO;

	public void setCommonDAO(StaffDAO stafftDAO)
	{
		this.stafftDAO = stafftDAO;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.zertones.service.sims.StudentService#studentSignUp(com.zertones.model
	 * .sims.Student)
	 */
	@Override
	@Transactional
	public Staff staffSignUp(Staff staff)
	{
		return stafftDAO.staffSignUp(staff);
	}

	@Override
	@Transactional
	public List<Mentor> getMentorProfile()
	{
		return stafftDAO.getMentorProfile();
	}

	@Override
	@Transactional
	public Staff saveStaff(Staff staff, MultipartFile image)
	{
		return stafftDAO.saveStaffProfile(staff, image);
	}

	@Override
	public List<ComClientNameDTO> getStaffList()
	{
		return stafftDAO.getStaffList();
	}

	@Override
	public List<ComClientNameDTO> getStaffList(Integer staffID)
	{
		return stafftDAO.getStaffList(staffID);
	}

	@Override
	public List<ComClientNameDTO> getStaffListbydept(Integer client_id)
	{
		return stafftDAO.getStaffListbydept(client_id);
	}

	@Override
	public String saveStaffInfoViaExcelFile(MultipartFile excelFile) throws Exception
	{

		return stafftDAO.saveStaffInfoViaExcelFile(excelFile);
	}

	@Override
	public boolean createMentorProfile(Mentor mentor, Integer[] students)
	{

		return stafftDAO.saveMentorProfile(mentor, students);

	}

	@Override
	public List<AcademicSemester> getSemisterList()
	{
		return stafftDAO.getSemisterList();
	}

	@Override
	public List<AcademicYear> getyearList()
	{
		return stafftDAO.getyearList();
	}

	@Override
	public List<Days> getDayList()
	{
		// TODO Auto-generated method stub
		return stafftDAO.getDaysList();
	}

	@Override
	public String updateAdminForm(AdminForm admin)
	{
		// TODO Auto-generated method stub
		return stafftDAO.updateAdminForm(admin);
	}

	@Override
	public String updatestaffData(StudentForm staff, Integer clientId)
	{
		// TODO Auto-generated method stub
		return stafftDAO.updatestaffData(staff, clientId);

	}

	@Override
	public List<Staff> getallStaffList()
	{
		// TODO Auto-generated method stub
		return stafftDAO.getallStaffList();
	}

	@Override
	public List<ComClientNameDTO> getStaffListbydepts(Integer dept)
	{
		// TODO Auto-generated method stub
		return stafftDAO.getStaffListbydepts(dept);
	}

	@Override
	public void deletestaff(int clientID)
	{
		// TODO Auto-generated method stub
		stafftDAO.deletestaff(clientID);
	}

}
