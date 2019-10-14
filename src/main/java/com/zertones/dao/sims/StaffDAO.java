/**
 *
 */
package com.zertones.dao.sims;

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
 * @author Zerton Team
 * @Created Date : Oct 6, 2015
 */
public interface StaffDAO
{
	public Staff staffSignUp(Staff staff);

	public List<Mentor> getMentorProfile();

	public Staff saveStaffProfile(Staff staff, MultipartFile image);

	public List<ComClientNameDTO> getStaffList();

	public List<ComClientNameDTO> getStaffList(Integer staffID);

	public String saveStaffInfoViaExcelFile(MultipartFile excelFile) throws Exception;

	public boolean saveMentorProfile(Mentor mentor, Integer[] students);

	public List<AcademicSemester> getSemisterList();

	public List<AcademicYear> getyearList();

	public List<Days> getDaysList();

	public String updateAdminForm(AdminForm admin);

	public List<ComClientNameDTO> getStaffListbydept(Integer client_id);

	public String updatestaffData(StudentForm staff, Integer clientId);

	public List<Staff> getallStaffList();

	public List<ComClientNameDTO> getStaffListbydepts(Integer dept);

	public void deletestaff(int clientID);
}
