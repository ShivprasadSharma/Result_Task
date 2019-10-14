package com.zertones.service.sims;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

public interface StudentService
{
	public Student studentSignUp(Student student);

	public List<Student> getStudentDetails();

	public String uploadImageOnCloud(byte[] image);

	public List<Student> getStudentDetailsById(Integer StudId);

	public List<Student> getStudentList(Integer depId, Integer divId);

	public List<Student> getStudentbysortList(Integer yr, Integer div, Integer client_id);

	public List<ComClientNameDTO> getStudentListByDep(Integer depId);

	public List<Mentor> getStudentListByStaffId(Integer id);

	public List<MentorStudent> getStudentListbyid(Integer id);

	public List<ComClientNameDTO> getStudentList();

	public List<ComClientNameDTO> getStudentListByDepartment();

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
	public List<ComClientNameDTO> getStudentList(Integer gid);

	public List<Department> getDepartment();

	public String saveStudetInfoViaExcelFile(MultipartFile excelFile) throws Exception;

	public ComClientName updateStudentProfile(ComClientName student);

	public ComClientName setCredential(ComUser comUser);

	public List<ComClientNameDTO> getStudentForMentor(Integer id);

	public Boolean updateMember(Mentor mentor, Integer[] member);

	public Integer deleteMentorStudent(Integer[] member);

	public List<BatchTable> getBatchList(Integer SubId);

	public List<Student> getVerifyingStud();

	public Boolean updateverifystud(Integer id);

	public Boolean deleteverifystud(Integer id);

	public Student getStudentDetails(Integer clientId);

	public ComClientName getStudentData(Integer clientId);

	public Integer updateStudentform(StudentForm stud);

	public List<Parent> getParentDetails(Integer clientId);

	public void deleteStudent(int clientID);

	public List<ComClientNameDTO> getStudentlist();

	public List<Student> getStudentListByDept(Integer dep);

	public List<ComClientNameDTO> getforthyrstudent();

	public List<ExtraActivityDTO> getStudentinternshipbysortList();

	public List<ExtraActivities> getFutureInteresttList();

	public List<FeeDetails> getfeestructureofstudent(Integer id);

	public List<Parent> getparentdtlofstudent(Integer id);

}
