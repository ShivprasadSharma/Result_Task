package com.zertones.service.sims;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.zertones.dao.sims.StudentDAO;
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
@Service("studentService")
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private StudentDAO studentDAO;

	public void setCommonDAO(StudentDAO studentDAO)
	{
		this.studentDAO = studentDAO;
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
	public Student studentSignUp(Student student)
	{
		return studentDAO.studentSignUp(student);
	}

	@Override
	public List<Student> getStudentDetails()
	{

		return studentDAO.getStudentInfo();
	}

	@Override
	public List<Student> getStudentDetailsById(Integer StudId)
	{
		return studentDAO.getStudentInfoById(StudId);
	}

	@Override
	public List<Student> getStudentList(Integer depId, Integer divId)
	{
		return studentDAO.getStudentList(depId, divId);
	}

	@Override
	public List<Student> getStudentbysortList(Integer yr, Integer div, Integer client_id)
	{
		return studentDAO.getStudentbysortList(yr, div, client_id);
	}

	@Override
	public List<ComClientNameDTO> getStudentList()
	{
		return studentDAO.getStudentList();
	}

	@Override
	public List<ComClientNameDTO> getStudentList(Integer gid)
	{
		return studentDAO.getStudentList(gid);
	}

	@Override
	public List<ComClientNameDTO> getStudentListByDep(Integer depId)
	{
		return studentDAO.getStudentListByDep(depId);
	}

	@Override
	public List<Department> getDepartment()
	{
		return studentDAO.getDepartment();

	}

	@Override
	public String saveStudetInfoViaExcelFile(MultipartFile excelFile) throws Exception
	{

		return studentDAO.saveStudetInfoViaExcelFile(excelFile);

	}

	@Override
	public ComClientName updateStudentProfile(ComClientName student)
	{
		return studentDAO.upadteStudentProfile(student);
	}

	@Override
	public ComClientName setCredential(ComUser comUser)
	{
		return studentDAO.setStudentCredential(comUser);
	}

	@Override
	public List<Mentor> getStudentListByStaffId(Integer id)
	{
		return studentDAO.setStudentListForMentor(id);
	}

	@Override
	public List<MentorStudent> getStudentListbyid(Integer id)
	{
		return studentDAO.getStudentListRemove(id);
	}

	@Override
	public List<ComClientNameDTO> getStudentListByDepartment()
	{
		return studentDAO.getStudentListByDepartment();
	}

	@Override
	public List<ComClientNameDTO> getStudentForMentor(Integer id)
	{
		return studentDAO.getStudentListForAddMentorMember(id);
	}

	@Override
	public Boolean updateMember(Mentor mentor, Integer[] member)
	{
		return studentDAO.addMentorMember(mentor, member);
	}

	@Override
	public Integer deleteMentorStudent(Integer[] member)
	{
		return studentDAO.removeMentorMember(member);
	}

	@Override
	public String uploadImageOnCloud(byte[] image)
	{
		Map config = ObjectUtils.asMap("cloud_name", "dcptr5ivh", "api_key", "297869565358947", "api_secret",
				"Lca6u4EW3lo-weSppOf1MwGz6v0");
		Cloudinary cloudinary = new Cloudinary(config);
		Map uploadResult;
		String url = "";
		try
		{
			uploadResult = cloudinary.uploader().upload(image, ObjectUtils.emptyMap());
			url = uploadResult.get("url").toString();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return url;
	}

	@Override
	public List<BatchTable> getBatchList(Integer SubId)
	{
		// TODO Auto-generated method stub
		return studentDAO.getBatchList(SubId);
	}

	@Override
	public List<Student> getVerifyingStud()
	{
		// TODO Auto-generated method stub
		return studentDAO.getVerifyingStud();
	}

	@Override
	public Boolean updateverifystud(Integer id)
	{
		return studentDAO.updateverifystud(id);
	}

	@Override
	public Boolean deleteverifystud(Integer id)
	{
		// TODO Auto-generated method stub

		return studentDAO.deleteverifystud(id);
	}

	@Override
	public Student getStudentDetails(Integer clientId)
	{
		// TODO Auto-generated method stub
		return studentDAO.getStudentDetails(clientId);
	}

	@Override
	public ComClientName getStudentData(Integer clientId)
	{
		// TODO Auto-generated method stub
		return studentDAO.getStudentData(clientId);
	}

	@Override
	public Integer updateStudentform(StudentForm stud)
	{
		// TODO Auto-generated method stub
		return studentDAO.updateStudentform(stud);
	}

	@Override
	public List<Parent> getParentDetails(Integer clientId)
	{
		// TODO Auto-generated method stub
		return studentDAO.getParentDetails(clientId);
	}

	@Override
	public void deleteStudent(int clientID)
	{
		// TODO Auto-generated method stub
		studentDAO.deleteStudent(clientID);
	}

	@Override
	public List<ComClientNameDTO> getStudentlist()
	{
		// TODO Auto-generated method stub
		return studentDAO.getStudentlist();
	}

	@Override
	public List<Student> getStudentListByDept(Integer dep)
	{
		// TODO Auto-generated method stub
		return studentDAO.getStudentListByDept(dep);
	}

	@Override
	public List<ComClientNameDTO> getforthyrstudent()
	{
		// TODO Auto-generated method stub
		return studentDAO.getforthyrstudent();

	}

	@Override
	public List<ExtraActivityDTO> getStudentinternshipbysortList()
	{
		// TODO Auto-generated method stub
		return studentDAO.getStudentinternshipbysortList();
	}

	@Override
	public List<ExtraActivities> getFutureInteresttList()
	{
		// TODO Auto-generated method stub
		return studentDAO.getFutureInteresttList();
	}

	@Override
	public List<FeeDetails> getfeestructureofstudent(Integer id)
	{
		// TODO Auto-generated method stub
		return studentDAO.getfeestructureofstudent(id);
	}

	@Override
	public List<Parent> getparentdtlofstudent(Integer id)
	{
		// TODO Auto-generated method stub
		return studentDAO.getparentdtlofstudent(id);
	}

}
