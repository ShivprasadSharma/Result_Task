/**
 *
 */
package com.zertones.dao.sims;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.zertones.model.ComClientName;
import com.zertones.model.ComUser;
import com.zertones.model.StudentForm;
import com.zertones.model.DataTransferObjectModel.ComClientNameDTO;
import com.zertones.model.DataTransferObjectModel.ExtraActivityDTO;
import com.zertones.model.common.BatchTable;
import com.zertones.model.common.Department;
import com.zertones.model.common.ExtraActivities;
import com.zertones.model.common.FeeDetails;
import com.zertones.model.sims.Mentor;
import com.zertones.model.sims.MentorStudent;
import com.zertones.model.sims.Parent;
import com.zertones.model.sims.Student;

/**
 * @author Zerton Team
 * @Created Date : Oct 6, 2015
 */
public interface StudentDAO
{
	public Student studentSignUp(Student student);

	public List<Student> getStudentInfo();

	public List<Student> getStudentInfoById(Integer StudId);

	public List<Student> getStudentList(Integer depId, Integer divId);

	public List<ComClientNameDTO> getStudentListByDep(Integer depId);

	public List<Mentor> setStudentListForMentor(Integer id);

	public List<MentorStudent> getStudentListRemove(Integer id);

	public List<ComClientNameDTO> getStudentListByDepartment();

	public List<ComClientNameDTO> getStudentList();

	public List<ComClientNameDTO> getStudentList(Integer gid);

	public List<Department> getDepartment();

	public String saveStudetInfoViaExcelFile(MultipartFile excelFile) throws Exception;

	public ComClientName upadteStudentProfile(ComClientName student);

	public ComClientName setStudentCredential(ComUser comUser);

	public List<ComClientNameDTO> getStudentListForAddMentorMember(Integer id);

	public Boolean addMentorMember(Mentor m, Integer[] i);

	public Integer removeMentorMember(Integer[] i);

	public List<BatchTable> getBatchList(Integer SubId);

	public List<Student> getVerifyingStud();

	public Boolean updateverifystud(Integer id);

	public Boolean deleteverifystud(Integer id);

	public Student getStudentDetails(Integer clientId);

	public ComClientName getStudentData(Integer clientId);

	public Integer updateStudentform(StudentForm stud);

	public List<Parent> getParentDetails(Integer clientId);

	public List<Student> getStudentbysortList(Integer yr, Integer div, Integer client_id);

	public void deleteStudent(int clientID);

	public List<ComClientNameDTO> getStudentlist();

	public List<Student> getStudentListByDept(Integer dep);

	public List<ComClientNameDTO> getforthyrstudent();

	public List<ExtraActivityDTO> getStudentinternshipbysortList();

	public List<ExtraActivities> getFutureInteresttList();

	public List<FeeDetails> getfeestructureofstudent(Integer id);

	public List<Parent> getparentdtlofstudent(Integer id);

}
