package com.zertones.service.sims;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.zertones.model.StudentForm;
import com.zertones.model.DataTransferObjectModel.ComClientNameDTO;
import com.zertones.model.common.AdminForm;
import com.zertones.model.common.Days;
import com.zertones.model.master.AcademicSemester;
import com.zertones.model.master.AcademicYear;
import com.zertones.model.sims.Mentor;
import com.zertones.model.sims.Staff;

/**
 * @author zerton team
 * @Created Date : Oct 6, 2015
 */
public interface StaffService
{
	public Staff staffSignUp(Staff staff);

	public List<Mentor> getMentorProfile();

	public Staff saveStaff(Staff staff, MultipartFile image);

	public List<ComClientNameDTO> getStaffList();

	public List<ComClientNameDTO> getStaffList(Integer staffID);

	public List<ComClientNameDTO> getStaffListbydept(Integer client_id);

	public boolean createMentorProfile(Mentor staff, Integer[] students);

	public String saveStaffInfoViaExcelFile(MultipartFile excelFile) throws Exception;

	public List<AcademicSemester> getSemisterList();

	public List<AcademicYear> getyearList();

	public List<Days> getDayList();

	public String updateAdminForm(AdminForm admin);

	public String updatestaffData(StudentForm staff, Integer clientId);

	public List<Staff> getallStaffList();

	public List<ComClientNameDTO> getStaffListbydepts(Integer dept);

	public void deletestaff(int clientID);
}
