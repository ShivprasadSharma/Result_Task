package com.zertones.service.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.zertones.dao.common.CommonDAO;
import com.zertones.dao.common.NotificationDAO;
import com.zertones.model.ComAttendance;
import com.zertones.model.ComClientName;
import com.zertones.model.ComUser;
import com.zertones.model.ComUserDetails;
import com.zertones.model.DeviceDetails;
import com.zertones.model.EducationDetails;
import com.zertones.model.PlacementYears;
import com.zertones.model.ResultMarksheet;
import com.zertones.model.ResultModelForPlacement;
import com.zertones.model.ResultStudent;
import com.zertones.model.Resultsemester;
import com.zertones.model.Semester;
import com.zertones.model.StudentForm;
import com.zertones.model.SubjectList;
import com.zertones.model.DataTransferObjectModel.AttendanceDTOWeb;
import com.zertones.model.DataTransferObjectModel.CollegeCountDTO;
import com.zertones.model.DataTransferObjectModel.CollegeTotalFee;
import com.zertones.model.DataTransferObjectModel.ComClientNameDTO;
import com.zertones.model.DataTransferObjectModel.DepartmentDTO;
import com.zertones.model.DataTransferObjectModel.DirectoryDTO;
import com.zertones.model.DataTransferObjectModel.DriveStudentRoundCount;
import com.zertones.model.DataTransferObjectModel.ExtraActivityDTO;
import com.zertones.model.DataTransferObjectModel.GfmInformation;
import com.zertones.model.DataTransferObjectModel.GroupDTO;
import com.zertones.model.DataTransferObjectModel.Navbar_MenuList;
import com.zertones.model.DataTransferObjectModel.NewUserDTO;
import com.zertones.model.DataTransferObjectModel.OnCampusPlaceStudListDTO;
import com.zertones.model.DataTransferObjectModel.PlacementDriveStatus;
import com.zertones.model.DataTransferObjectModel.PortFolioInfo;
import com.zertones.model.DataTransferObjectModel.RecruitmentInfoDTO;
import com.zertones.model.DataTransferObjectModel.ResultInstituteList;
import com.zertones.model.DataTransferObjectModel.StudentAttendanceDto;
import com.zertones.model.DataTransferObjectModel.Studentskillsdto;
import com.zertones.model.DataTransferObjectModel.SubjectResultDataModel;
import com.zertones.model.DataTransferObjectModel.SubjectsDTO;
import com.zertones.model.DataTransferObjectModel.TimeTableDTO;
import com.zertones.model.DataTransferObjectModel.TpoApplayCriteria;
import com.zertones.model.DataTransferObjectModel.TpoDrive;
import com.zertones.model.DataTransferObjectModel.emailData;
import com.zertones.model.common.AdminForm;
import com.zertones.model.common.ApplyedStudentForCompany;
import com.zertones.model.common.AssignedCoordinators;
import com.zertones.model.common.AttendancePunch;
import com.zertones.model.common.AwardsAndAchievementModel;
import com.zertones.model.common.BatchList;
import com.zertones.model.common.ComVideoURL;
import com.zertones.model.common.CompanySelectionRounds;
import com.zertones.model.common.ContactUsModel;
import com.zertones.model.common.Days;
import com.zertones.model.common.EducationFields;
import com.zertones.model.common.ExamSchedule;
import com.zertones.model.common.ExtraActivities;
import com.zertones.model.common.FeeDetails;
import com.zertones.model.common.GroupMembers;
import com.zertones.model.common.Groups;
import com.zertones.model.common.Help;
import com.zertones.model.common.InstituteInfoDetails;
import com.zertones.model.common.InstituteInfoMaster;
import com.zertones.model.common.InstituteInfoModel;
import com.zertones.model.common.LecturePracticalTime;
import com.zertones.model.common.LectureTheoryTime;
import com.zertones.model.common.LifeOfCampus;
import com.zertones.model.common.MenuList;
import com.zertones.model.common.Notification;
import com.zertones.model.common.NotificationFiles;
import com.zertones.model.common.OffcampusPlaceStud;
import com.zertones.model.common.Parentcallrecord;
import com.zertones.model.common.RecruitmentInfo;
import com.zertones.model.common.StudentFeeDtl;
import com.zertones.model.common.TimeTable;
import com.zertones.model.common.TotalStudentCount;
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
import com.zertones.model.sims.RemarkOption;
import com.zertones.model.sims.Staff;
import com.zertones.model.sims.Student;
import com.zertones.model.sims.StudentRemark;
import com.zertones.model.sims.StudentRemarkOption;
import com.zertones.system.beans.DropDownList;

@Service("commonService")
public class CommonServiceImpl implements CommonService
{
	@Autowired
	private CommonDAO				commonDAO;

	private InstituteInfoModel		instituteInfoModel;

	@Autowired
	private NotificationService		notificationService;

	@Autowired
	private NotificationDAO			notificationDAO;

	@Autowired
	private DropDownList			dropDownList;

	@Autowired
	private PushNotificationService	pushNotificationService;

	public void setCommonDAO(CommonDAO commonDAO)
	{
		this.commonDAO = commonDAO;
	}

	/*
	 * public void setInstituteInfoModel(InstituteInfoModel instituteInfoModel) {
	 * this.instituteInfoModel = instituteInfoModel; }
	 *
	 * @Override
	 *
	 * @Transactional public void addAboutUs(AboutUsModel aboutUsModel) {
	 * commonDAO.addAboutUs(aboutUsModel); }
	 *
	 * @Override
	 *
	 * @Transactional public void updateAboutUs(AboutUsModel aboutUsModel) {
	 * commonDAO.updateAboutUs(aboutUsModel); }
	 *
	 * @Override
	 *
	 * @Transactional public AboutUsModel getAboutUsModelId(Integer id) { return
	 * commonDAO.getAboutUsModelId(id); }
	 *
	 * @Override
	 *
	 * @Transactional public void removeAboutUs(Integer id) {
	 * commonDAO.removeAboutUs(id); }
	 *
	 * @Override
	 *
	 * @Transactional public void addContactUs(ContactUsModel contactUsModel) {
	 * commonDAO.addContactUs(contactUsModel); }
	 *
	 * @Override
	 *
	 * @Transactional public void updateContactUs(ContactUsModel contactUsModel) {
	 * commonDAO.updateContactUs(contactUsModel); }
	 *
	 * @Override
	 *
	 * @Transactional public ContactUsModel getContactUsModelId(Integer id) { return
	 * commonDAO.getContactUsModelId(id); }
	 *
	 * @Override
	 *
	 * @Transactional public List<ContactUsModel> getContactUsLists() { return
	 * commonDAO.getContactUsLists(); }
	 *
	 * @Override
	 *
	 * @Transactional public void removeContactUs(Integer id) {
	 * commonDAO.removeContactUs(id); }
	 *
	 * @Override
	 *
	 * @Transactional public void addAwardsAndAchievements(AwardsAndAchievementModel
	 * awardsAndAchievementModel) {
	 * commonDAO.addAwardsAndAchievements(awardsAndAchievementModel); }
	 *
	 * @Override
	 *
	 * @Transactional public void
	 * updateAwardsAndAchievements(AwardsAndAchievementModel awardsAndAchievements)
	 * { commonDAO.updateAwardsAndAchievements(awardsAndAchievements); }
	 *
	 * @Override
	 *
	 * @Transactional public AwardsAndAchievementModel
	 * getAwardsAndAchievementsById(Integer id) { return
	 * commonDAO.getAwardsAndAchievementsById(id); }
	 */
	@Override

	@Transactional
	public List<AwardsAndAchievementModel> getAwardsAndAchievements()
	{
		return commonDAO.getAwardsAndAchievements();
	}

	/*
	 * @Override
	 *
	 * @Transactional public void removeAwardsAndAchievement(Integer id) {
	 * commonDAO.removeAwardsAndAchievement(id); }
	 */

	@Override
	public List<MenuList> getAppMenuList()
	{
		return commonDAO.getMenuList();
	}

	@Override
	@Transactional
	public InstituteInfoModel getInstituteInfo()
	{
		instituteInfoModel.setInstituteInfoMatser(getInstituteInfoMatserList());
		return instituteInfoModel;
	}

	@Override
	@Transactional
	public void addInstituteInfoMaster(InstituteInfoMaster instituteInfoMaster)
	{
		commonDAO.addInstituteInfoMaster(instituteInfoMaster);
		dropDownList.setListMap();

	}

	@Override
	@Transactional
	public void updateInstituteInfoMatser(InstituteInfoMaster instituteInfoMaster)
	{
		commonDAO.updateInstituteInfoMatser(instituteInfoMaster);

	}

	@Override
	@Transactional
	public InstituteInfoMaster getInstituteInfoMasterById(Integer id)
	{
		return commonDAO.getInstituteInfoMasterById(id);
	}

	@Override
	@Transactional
	public List<InstituteInfoMaster> getInstituteInfoMatserList()
	{
		return commonDAO.getInstituteInfoMatserList();
	}

	@Override
	public List<LifeOfCampus> getClglifeOfCampuse()
	{

		return commonDAO.getlifeOfCampuse();
	}

	@Override
	@Transactional
	public void addInstituteInfoDetails(InstituteInfoDetails instituteInfoDetails)
	{
		commonDAO.addInstituteInfoDetails(instituteInfoDetails);
	}

	@Override
	@Transactional
	public void updateInstituteInfoDetails(InstituteInfoDetails instituteInfoDetails)
	{
		commonDAO.updateInstituteInfoDetails(instituteInfoDetails);
	}

	@Override
	@Transactional
	public List<InstituteInfoDetails> getInstituteInfoDetailsList(Integer instituteInfoMasterId)
	{
		List<InstituteInfoDetails> list = commonDAO.getInstituteInfoDetailsList(instituteInfoMasterId);
		return list;

	}

	@Override
	@Transactional
	public List<MenuList> getMenuList()
	{
		return commonDAO.getMenuList();
	}

	@Override
	@Transactional
	public InstituteInfoDetails getMasterDtlById(Integer id)
	{
		return commonDAO.getMasterDtlById(id);
	}

	@Override
	@Transactional
	public ComListMaster getCommonMasterList(Integer listId)
	{
		return commonDAO.getCommonMasterList(listId);
	}

	@Override
	@Transactional
	public List<ComUserDetails> searchUsser(String username)
	{
		return commonDAO.searchUsser(username);
	}

	@Override
	@Transactional
	public TimeTable saveTimeTable(TimeTable timeTable)
	{
		return commonDAO.addTimeTable(timeTable);
	}

	@Override
	@Transactional
	public CourseMaster saveCourseMaster(CourseMaster courseMaster)
	{
		return commonDAO.saveCourseMaster(courseMaster);
	}

	@Override
	@Transactional
	public ExamSchedule saveExamSchedule(ExamSchedule examSchedule)
	{
		return commonDAO.saveExamSchedule(examSchedule);
	}

	@Override
	@Transactional
	public AcademicYear saveAcademicYear(AcademicYear academicYear)
	{
		return commonDAO.saveAcademicYear(academicYear);
	}

	@Override
	@Transactional
	public List<Days> getTimeTable(Integer yearid)
	{
		return commonDAO.getTimeTable(yearid);
	}

	@Override
	@Transactional
	public List<ExamSchedule> getExamSchedule()
	{
		return commonDAO.getExamSchedule();
	}

	@Override
	@Transactional
	public List<CourseMaster> getCourseMaster()
	{
		return commonDAO.getCourseMaster();
	}

	@Override
	@Transactional
	public List<AcademicYear> getAcademiYear()
	{
		return commonDAO.getAcademicYear();
	}

	@Override
	public AcademicSemester getYearSemester(Integer id)
	{
		return commonDAO.getYearSemester(id);
	}

	@Override
	@Transactional
	public List<ComListMaster> getCommonMasterList()
	{
		return commonDAO.getCommonMasterList();
	}

	@Override
	public List<Staff> getTeacherProfile()
	{

		return commonDAO.getTeacherProfile();

	}

	@Override
	public Staff getTeacherProfileById(Integer clientId)
	{
		return commonDAO.getTeacherProfileById(clientId);
	}

	@Override
	public Staff userCheck(String name)
	{
		return commonDAO.userLoginCheck(name);
	}

	@Override
	public ComClientName saveUser(ComClientName comClientName)
	{
		return commonDAO.adduser(comClientName);

	}

	@Override
	public List<ComClientName> getUserInfoMatserList()
	{
		return commonDAO.getUserList();

	}

	@Override
	public ComClientName getUserDtlById(Integer userId)
	{
		return commonDAO.getUserDtlById(userId);
	}

	@Override
	public ComClientName getRemoveUser(Integer userId)
	{
		return commonDAO.removeUser(userId);
	}

	@Override
	public List<ComClientName> getTechList(String query)
	{
		return commonDAO.getTeacherList(query);

	}

	@Override
	public ComClientName loginCheck(DeviceDetails deviceDetails)
	{
		return commonDAO.chklogin(deviceDetails);
	}

	@Override
	@Transactional
	public List<DepartmentDTO> getDepartmentList()
	{

		return commonDAO.getDepartmentList();
	}

	@Override

	@Transactional
	public List<emailData> getEmailIds()
	{

		return commonDAO.getEmailIds();
	}

	@Override
	@Transactional
	public List<DepartmentDTO> getDepartmentList(Integer cid)
	{

		return commonDAO.getDepartmentList(cid);
	}

	@Override
	public List<Staff> getStaffByType(Integer id)
	{
		return commonDAO.getStaffById(id);
	}

	@Override
	public Groups saveGroup(Groups group, Integer[] member)
	{

		return commonDAO.saveGroups(group, member);
	}

	@Override
	public List<GroupDTO> getGroupList(Integer cid)
	{
		return commonDAO.getListGroup(cid);

	}

	@Override
	public List<Groups> getGroupListByDepartment(Integer depId)
	{
		return commonDAO.getListGroupByDepartment(depId);

	}

	@Override
	public List<GroupDTO> getGroupDetails(Integer groupId)
	{
		return commonDAO.getGroupDetails(groupId);
	}

	@Override
	public Boolean UpdateGroup(Groups group, Integer[] member)
	{
		return commonDAO.updateGroups(group, member);

	}

	@Override
	public Boolean updateGroup(Groups group)
	{
		return commonDAO.updateGroupsInfo(group);
	}

	@Override
	public Boolean removeGroup(Integer id)
	{
		return commonDAO.removeGroup(id);

	}

	@Override
	public Boolean removeGroupMember(Groups group, Integer[] member)
	{
		return commonDAO.removeGroupMember(group, member);
	}

	@Override
	public ComClientName findUserByEmail(String emailId)
	{
		return commonDAO.findUserByEmailAdd(emailId);
	}

	@Override
	public UserToken saveVerificationToken(UserToken userToken)
	{
		return commonDAO.saveUserVerificationToken(userToken);

	}

	@Override
	public UserToken getUserByToken(String authToken)
	{
		return commonDAO.getUserByToken(authToken);
	}

	@Override
	public Boolean updateUserEntity(ComUser comUser)
	{
		return commonDAO.updateUserEntity(comUser);

	}

	@Override
	public List<GroupMembers> getGroupListByStudentId(Integer id)
	{
		return commonDAO.getGroupListByStdId(id);

	}

	@Override
	public Boolean saveAttendance(ComAttendance attendance)
	{
		return commonDAO.saveAttendance(attendance);
	}

	@Override
	public List<GroupDTO> getGroupListByType(Integer type, Integer cid)
	{
		return commonDAO.getGroupListByType(type, cid);
	}

	@Override
	public List<ComAttendance> getAttendanceBySubject(Integer subCode)
	{
		return commonDAO.getAttendenacBySub(subCode);

	}

	@Override
	public List<ComStaffSubject> getAcademicSubjectByStaff(Integer staffEmpId)
	{
		return commonDAO.getSubjectByStaff(staffEmpId);
	}

	@Override
	public double getMonthlyAttenedance(LocalDate date, Integer staffSub_id)
	{
		return commonDAO.getMonthlyAttenedance(date, staffSub_id);
	}

	@Override
	public boolean saveMarkSheet(MarkSheet sheet)
	{
		return commonDAO.saveMarkSheetDao(sheet);

	}

	@Override
	public List<Object[]> getResultSummary(Integer exam_yr, Integer semid, Integer depid, Integer sub)
	{
		return commonDAO.getSummaryResult(exam_yr, semid, depid, sub);
	}

	@Override
	public List<Object[]> getAllStudentListFromMarksheet(Integer yr, Integer sem, Integer sub)
	{
		return commonDAO.getAllStudentListFromMrksheet(yr, sem, sub);
	}

	@Override
	public Boolean addContactUs(ContactUsModel contactUsModel)
	{
		return commonDAO.addContactUs(contactUsModel);
	}

	@Override
	public ComListMaster getAboutUsModel()
	{

		return commonDAO.getAboutUsModelDao();
	}

	@Override
	public List<DirectoryDTO> getDirectory()
	{
		return commonDAO.getDirectoryList();
	}

	@Override
	public List<ResultFile> getResultList()
	{
		return commonDAO.getResultFileList();
	}

	@Override
	public List<Object[]> getSubjectResult(Integer sem, Integer dep_id, Integer exam_yr, Integer sub)
	{
		return commonDAO.getSubjectRsultPersentage(sem, dep_id, exam_yr, sub);
	}

	@Override
	public List<SubjectList> getSubjectdata(Integer dep, Integer sem)
	{
		return commonDAO.getSubjectdata(dep, sem);
	}

	@Override
	public List<AcademicSubject> getAcademicSubData(Integer dep, Integer sem)
	{
		return commonDAO.getAcademicSubData(dep, sem);
	}

	@Override
	public void insertTimeTable(TimeTable timeTable)
	{
		commonDAO.insertTimeTable(timeTable);

	}

	@Override
	public String getSubjectTeacherdata(Integer subId)
	{
		return commonDAO.getSubjectTeacherdata(subId);

	}

	@Override
	public void addSubjectdata(Integer depId, Integer semId, Integer[] subject)
	{
		commonDAO.addSubjectdata(depId, semId, subject);
	}

	@Override
	public List<SubjectList> getSubject()
	{
		return commonDAO.getAcademicSubject();
	}

	@Override
	public void insertTeacherSubject(Integer[] subjectId, Integer cid)
	{
		commonDAO.insertTeacherSubject(subjectId, cid);
	}

	@Override
	public List<TimeTableDTO> getLectures(Integer id)
	{
		return commonDAO.getStaffLectures(id);
	}

	@Override
	public List<TimeTableDTO> getTimetableSubject(String date)
	{
		return commonDAO.getTimetableSubjectDao(date);

	}

	@Override
	public Integer addAttendance(ComAttendance comAttendance, Integer[] studId, Integer subjectId)
	{
		// TODO Auto-generated method stub
		return commonDAO.addAttendance(comAttendance, studId, subjectId);
	}

	@Override
	public Map<Integer, String> getpracticalSubdata(Integer staffId, Integer sem)
	{
		return commonDAO.getpracticalSubdata(staffId, sem);
	}

	@Override
	public String saveSubjectListViaExcelFile(MultipartFile excelFile) throws Exception
	{
		return commonDAO.saveSubjectListViaExcelFile(excelFile);
	}

	@Override
	public List<AcademicSubject> getSubjectToAssign()
	{
		// TODO Auto-generated method stub
		return commonDAO.getSubjectToAssign();
	}

	@Override
	public String saveFeedbackListViaExcelFile(MultipartFile excelFile) throws Exception
	{
		// TODO Auto-generated method stub
		return commonDAO.saveFeedbackListViaExcelFile(excelFile);

	}

	@Override
	public List<SubjectsDTO> getStaffSubject(Integer staffClientId)
	{
		// TODO Auto-generated method stub
		return commonDAO.getStaffSubject(staffClientId);

	}

	@Override
	public List<ComClientNameDTO> getAcademicStdList(Integer subid, String div, int attendanceid, String batchID)
	{
		// TODO Auto-generated method stub
		return commonDAO.getAcademiStudListDto(subid, div, attendanceid, batchID);

	}

	@Override
	public boolean addStudAttendance(SubjectsDTO studsub)
	{
		// TODO Auto-generated method stub
		return commonDAO.addStudAttendance(studsub);
	}

	@Override
	public Staff getstaffData(Integer staff_id)
	{
		// TODO Auto-generated method stub
		return commonDAO.getstaffData(staff_id);

	}

	@Override
	public List<Mentor> getMentorStudentList(Integer staff_id)
	{
		// TODO Auto-generated method stub
		return commonDAO.getMentorStudentList(staff_id);
	}

	@Override
	public List<ComStaffSubject> getAssigendStaffSubject(Integer staffId)
	{
		// TODO Auto-generated method stub
		return commonDAO.getAssigendStaffSubject(staffId);
	}

	@Override
	public List<String> getdivision(Integer staffId, Integer subId)
	{
		// TODO Auto-generated method stub
		return commonDAO.getdivision(staffId, subId);
	}

	@Override
	public List<DepartmentDTO> getDepartmentListForAttendance(Integer clientId)
	{
		// TODO Auto-generated method stub
		return commonDAO.getDepartmentListForAttendance(clientId);
	}

	@Override
	public AttendanceDTOWeb getDailyAttendanceData(ComAttendance comAttendance, int subId)
	{
		// TODO Auto-generated method stub
		return commonDAO.getDailyAttendanceData(comAttendance, subId);
	}

	@Override
	public Boolean dailyAttendanceReportDownload(ComAttendance comAttendance, int reportId, String todate)
	{
		// TODO Auto-generated method stub
		return commonDAO.dailyAttendanceReportDownload(comAttendance, reportId, todate);
	}

	@Override
	public List<Mentor> getMenterGroupList(Integer staffId)
	{
		return commonDAO.getMenterGroupList(staffId);

	}

	@Override
	public void deleteMenterGroup(Integer groupid)
	{
		// TODO Auto-generated method stub
		commonDAO.deleteMenterGroup(groupid);
	}

	@Override
	public Integer SaveStudFeeDetails(Integer studId, FeeDetails feeDetails)
	{
		return commonDAO.SaveStudFeeDetails(studId, feeDetails);
	}

	@Override
	public FeeDetails getFeeDetails(Integer clientId)
	{
		// TODO Auto-generated method stub
		return commonDAO.getFeeDetails(clientId);

	}

	@Override
	public void deleteAssingedSubject(Integer clientID, Integer subjectId)
	{
		// TODO Auto-generated method stub
		commonDAO.deleteAssingedSubject(clientID, subjectId);
	}

	@Override
	public List<String> getDivision_For_Batch(Integer depId, Integer classId)
	{
		// TODO Auto-generated method stub
		return commonDAO.getDivision_For_Batch(depId, classId);

	}

	@Override
	public List<ComClientNameDTO> getStudent_For_Batch(Integer depId, Integer classId, String divId)
	{
		// TODO Auto-generated method stub
		return commonDAO.getStudent_For_Batch(depId, classId, divId);
	}

	@Override
	public int add_Student_Batch(BatchList batchList, int depId, int classId, String divId)
	{
		// TODO Auto-generated method stub
		return commonDAO.add_Student_Batch(batchList, depId, classId, divId);
	}

	@Override
	public List<String> getPractical_Batch(Integer subId, String division)
	{
		// TODO Auto-generated method stub
		return commonDAO.getPractical_Batch(subId, division);
	}

	@Override
	public int addPracticalAttendance(ComAttendance comAttendance, Integer[] studId, Integer subjectId, String batch)
	{
		// TODO Auto-generated method stub
		return commonDAO.addPracticalAttendance(comAttendance, studId, subjectId, batch);
	}

	@Override
	public boolean addBatchStudAttendance(SubjectsDTO studsub)
	{
		// TODO Auto-generated method stub
		return commonDAO.addBatchStudAttendance(studsub);
	}

	@Override
	public List<LecturePracticalTime> getPracticalLectureTime()
	{
		// TODO Auto-generated method stub
		return commonDAO.getPracticalLectureTime();
	}

	@Override
	public List<LectureTheoryTime> getTheoryLectureTime()
	{
		// TODO Auto-generated method stub
		return commonDAO.getTheoryLectureTime();
	}

	@Override
	public AttendanceDTOWeb getPracticalDailyAttendanceData(ComAttendance comAttendance, int subjectId, String batch)
	{
		// TODO Auto-generated method stub
		return commonDAO.getPracticalDailyAttendanceData(comAttendance, subjectId, batch);

	}

	@Override
	public Boolean daily_PracticalAttendanceReportDownload(ComAttendance comAttendance, int reportid, String todate,
			String batch)
	{
		// TODO Auto-generated method stub
		return commonDAO.daily_PracticalAttendanceReportDownload(comAttendance, reportid, todate, batch);
	}

	@Override
	public AttendancePunch addStudentPunch(AttendancePunch attendancePunch)
	{
		// TODO Auto-generated method stub
		return commonDAO.addStudentPunch(attendancePunch);
	}

	@Override
	public AttendancePunch checkStudentPunch(Integer clientID)
	{
		// TODO Auto-generated method stub
		return commonDAO.addStudentPunch(clientID);
	}

	@Override
	public AttendancePunch getStudentPunchCount(AttendancePunch attendancePunch, int id)
	{
		// TODO Auto-generated method stub
		return commonDAO.getStudentPunchCount(attendancePunch, id);
	}

	@Override
	public Groups createSubjectGroup(Integer subjectId, Integer clientID, Integer div)
	{
		// TODO Auto-generated method stub
		return commonDAO.createSubjectGroup(subjectId, clientID, div);
	}

	@Override
	public Groups createClassGroup(Integer staffId, Integer yearId, Integer div)
	{
		// TODO Auto-generated method stub
		return commonDAO.createClassGroup(staffId, yearId, div);
	}

	@Override
	public Groups createStudentGroup(Groups group, Integer id)
	{
		// TODO Auto-generated method stub
		return commonDAO.createStudentGroup(group, id);
	}

	@Override
	public List<Groups> joinGroupList(int id)
	{
		// TODO Auto-generated method stub
		return commonDAO.joinGroupList(id);
	}

	@Override
	public boolean UpdateMemberGroup(Groups group, int id)
	{
		return commonDAO.UpdateMemberGroup(group, id);

	}

	@Override
	public List<Help> getHelpUrl(int id, int menuid)
	{
		return commonDAO.commonDAOgetHelpUrl(id, menuid);
	}

	@Override
	public boolean checkTermsAndCondition(Integer clientID)
	{
		// TODO Auto-generated method stub
		return commonDAO.checkTermsAndCondition(clientID);
	}

	@Override
	public AttendancePunch studPunchAdd(int punchId, int studId)
	{
		// TODO Auto-generated method stub

		return commonDAO.studPunchAdd(punchId, studId);
	}

	@Override
	public String getmenupage(Integer institute_id, Integer menuid)
	{
		// TODO Auto-generated method stub
		return commonDAO.getmenupage(institute_id, menuid);
	}

	@Override
	public List<CollegeMenuList> college_DynamicMenu()
	{
		// TODO Auto-generated method stub
		return commonDAO.college_DynamicMenu();
	}

	@Override
	public List<MianManuList> getMainMenuList()
	{
		// TODO Auto-generated method stub
		return commonDAO.getMainMenuList();
	}

	@Override
	public void instituteDataAdd(InstituteInfoMaster instituteInfoMaster, int[] menuiId)
	{
		commonDAO.instituteDataAdd(instituteInfoMaster, menuiId);
	}

	@Override
	public List<InstituteInfoMaster> getInstituteInfoDetailsList()
	{
		// TODO Auto-generated method stub
		return commonDAO.getInstituteInfoDetailsList();
	}

	@Override
	public List<CollegeMenuList> getInstitutemenuInfoById(Integer instid)
	{

		return commonDAO.getInstitutemenuInfoById(instid);
	}

	@Override
	public void updateInstitute(InstituteInfoMaster instituteInfoMaster, int[] menuiId)
	{
		// TODO Auto-generated method stub
		commonDAO.updateInstitute(instituteInfoMaster, menuiId);
	}

	@Override
	public List<Navbar_MenuList> getNabBar_MenuList(Integer institute_id)
	{
		// TODO Auto-generated method stub
		return commonDAO.getNabBar_MenuList(institute_id);
	}

	@Override
	public List<String> getDivisionAtten_Report(Integer deptId, Integer yearId)
	{
		// TODO Auto-generated method stub
		return commonDAO.getDivisionAtten_Report(deptId, yearId);
	}

	@Override
	public List<String> getPractical_Batch_forAtt_Report(Integer deptId, Integer yearId, String divId)
	{
		// TODO Auto-generated method stub
		return commonDAO.getPractical_Batch_forAtt_Report(deptId, yearId, divId);

	}

	@Override
	public List<AcademicSubject> getassignsubject(Integer staff_id)
	{
		// TODO Auto-generated method stub
		return commonDAO.getassignsubject(staff_id);
	}

	@Override
	public List<Groups> getgroup(Integer clientid)
	{

		return commonDAO.getgroup(clientid);

	}

	// result url
	@Override
	public String getResultreport(Integer aid, Integer bid, Integer sid)
	{
		return commonDAO.getResultreport(aid, bid, sid);
	}

	@Override
	public void getResultReportStudent(int aid, int bid, int cid)
	{
		// TODO Auto-generated method s
		commonDAO.getResultReportStudent(aid, bid, cid);
	}

	@Override
	public String getSubjectelist(MultipartFile subjectfile) throws Exception
	{
		return commonDAO.getSubjectelist(subjectfile);

	}

	@Override
	public boolean saveResulMarksheet(ResultMarksheet sheet, String subjectCode)
	{
		return commonDAO.saveResulMarksheet(sheet, subjectCode);
	}

	@Override
	public ResultStudent saveStudentResult(ResultStudent student)
	{

		return commonDAO.saveStudentResult(student);

	}

	@Override
	public List<AcademicSubject> getresultsubjectlist(Integer deptid, Integer classid, Integer semid)
	{
		// TODO Auto-generated method stu
		return commonDAO.getresultsubjectlist(deptid, classid, semid);
	}

	@Override
	public List<EducationDetails> getEducationDetails(Integer clientId)
	{
		List<EducationDetails> list = commonDAO.getEducationDetailsDao(clientId);
		ComClientName c = new ComClientName();
		for (EducationDetails educationDetails : list)
		{
			c.setId(clientId);
			educationDetails.setClientName(c);
		}
		return list;
	}

	@Override
	public void removeStudentEducationalDtls(EducationDetails educatinalDtl)
	{
		commonDAO.removeEducationDetailsDao(educatinalDtl);
	}

	@Override
	public EducationDetails addStudentEducationalInfo(EducationDetails educatinalDtl)
	{

		return commonDAO.addEducationDetailsDao(educatinalDtl);
	}

	@Override
	public List<ResultModelForPlacement> getEducationResultModel()
	{
		return commonDAO.getEducationResultModelDao();
	}

	@Override
	public void updateStudentEducationalDtls(EducationDetails educatinalDtl)
	{
		commonDAO.updateStudentEducationalDtlsDao(educatinalDtl);
	}

	@Override
	public List<ResultModelForPlacement> getResultModelForPlacement()
	{
		return commonDAO.getResultModelForPlacementDao();
	}

	@Override
	public List<Semester> getSemesterInfoForPlacement()
	{
		return commonDAO.getSemesterInfoForPlacementDao();
	}

	@Override
	public List<PlacementYears> getPlacementYr()
	{
		return commonDAO.getYearForPlacementDao();
	}

	// ............................................Tpo.............................
	@Override
	public void saveAssingedCoordinator(Integer client_id, Integer co_type, Integer dept_id)
	{
		// TODO Auto-generated method stub
		commonDAO.saveAssingedCoordinator(client_id, co_type, dept_id);
	}

	@Override
	public List<AssignedCoordinators> getcoordinatorlist(Integer client_id)
	{
		// TODO Auto-generated method stub
		return commonDAO.getcoordinatorlist(client_id);
	}

	@Override
	public void deleteAssigncoordinator(Integer client_id, Integer cid)
	{
		// TODO Auto-generated method stub
		commonDAO.deleteAssigncoordinator(client_id, cid);
	}

	@Override
	public List<AssignedCoordinators> getdepartmentTpoList()
	{
		// TODO Auto-generated method stub
		return commonDAO.getdepartmentTpoList();
	}

	@Override
	public void add_Recruitment_Info(RecruitmentInfoDTO recruitmentInfoDTO, String[] selectionRound, MultipartFile logo,
			String[] typeindustry, @RequestParam(value = "dept") String[] dept)
	{
		RecruitmentInfo recruitmentInfo = new RecruitmentInfo();
		String logurl = "";
		if (!logo.isEmpty())
		{
			try
			{
				logurl = notificationService.uploadImageOnCloud(logo);
				recruitmentInfo.setLogoUrl(logurl);
			} catch (Exception e)
			{
				e.getMessage();
			}
		}

		recruitmentInfo.setCompanyName(recruitmentInfoDTO.getCompanyName());
		recruitmentInfo.setDateInfo(recruitmentInfoDTO.getDateInfo());
		recruitmentInfo.setJobDescription(recruitmentInfoDTO.getJobDescription());
		recruitmentInfo.setTime(recruitmentInfoDTO.getTime());
		recruitmentInfo.setVenue(recruitmentInfoDTO.getVenue());
		recruitmentInfo.setSalary(recruitmentInfoDTO.getSalary());
		recruitmentInfo.setSlectionProcess(recruitmentInfoDTO.getSlectionProcess());
		recruitmentInfo.setCriteria(recruitmentInfoDTO.getCriteria());
		recruitmentInfo.setJobtitle(recruitmentInfoDTO.getJobtitle());
		recruitmentInfo.setDrivetype(recruitmentInfoDTO.getDrivetype());
		recruitmentInfo.setYear(recruitmentInfoDTO.getYear());
		recruitmentInfo.setTenth(recruitmentInfoDTO.getTenth());
		recruitmentInfo.setTwelveth(recruitmentInfoDTO.getTwelveth());
		recruitmentInfo.setDegree(recruitmentInfoDTO.getDegree());
		recruitmentInfo.setBacklog(recruitmentInfoDTO.getBacklog());
		recruitmentInfo.setApplydate(recruitmentInfoDTO.getApplydate());
		Thread eventNotice = new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					Notification notification = new Notification();
					Notification n = new Notification();

					notification.setNotificatiosHeadline("Recritment Info");
					java.util.Date d = new java.util.Date();

					notification.setNotificationToDate(d);
					notification.setNotificationFromDate(recruitmentInfo.getApplydate());
					notification.setNotificationDetails(recruitmentInfo.getCompanyName());
					notification.setDepartment(0);
					notification.setNotificationType(4);
					notification.setVenue("none");
					/// Set<NotificationFiles> f =
					/// notification.getNotificationFiles();
					NotificationFiles nf = new NotificationFiles();
					nf.setString1("");

					pushNotificationService.sendNotification(notification);

					/*
					 * try {
					 *
					 * nf.setNotification(notification);
					 * nf.setDocument1Type("application/octet-stream"); Set<NotificationFiles> nfs =
					 * new HashSet<>(); nfs.add(nf); notification.setNotificationFiles(nfs); n =
					 * notificationService.addNotification(notification); if (notification != null)
					 * { } } catch (Exception e) { e.printStackTrace(); }
					 */

				} catch (Exception e)
				{
					// TODO: handle exception
				}
			}
		};
		Thread mobileNotice = new Thread()
		{
			@Override
			public void run()
			{
				try
				{

				} catch (Exception ex)
				{
				}
			}
		};
		// eventNotice.start();
		// mobileNotice.start();

		commonDAO.add_Recruitment_Info(recruitmentInfo, selectionRound, recruitmentInfoDTO, typeindustry, dept);
		pushNotificationService.recruitmentInfoNotification(recruitmentInfo);

		System.out.println("after Data Inserted...............");

	}

	@Override
	public List<RecruitmentInfo> getRecuitmentcmpnylogo()
	{
		return commonDAO.getRecuitmentcmpnylogo();
	}

	@Override
	public List<RecruitmentInfoDTO> getpastcompnydtl()
	{
		return commonDAO.getpastcompnydtl();

	}

	@Override
	public RecruitmentInfoDTO getcompnybyid(Integer id)
	{
		return commonDAO.getcompnybyid(id);
	}

	@Override
	public List<Student> getstudentlistbydept(Integer id)
	{
		return commonDAO.getstudentlistbydept(id);
	}

	@Override
	public List<ComClientNameDTO> getfiltereducationalDTlofStudent(Integer year, Integer[] depts, double tencriteria,
			double deplocriteria, double degreecriteria, Integer backlog)
	{
		// TODO Auto-generated method stub

		// get all filtered student eduction record eg.HSC,SSC,DEP,BE
		List<EducationDetails> educationDetails = commonDAO.getfiltereducationalDTlofStudent(year, depts, tencriteria,
				deplocriteria, degreecriteria, backlog);
		if (educationDetails.isEmpty())
		{
			List<ComClientNameDTO> list = new ArrayList<>();
			return list;
		}
		Map<Integer, List<EducationDetails>> studentEduction = new LinkedHashMap<>();

		// store key as client id and value as that student Education recodr
		for (EducationDetails educationDetails2 : educationDetails)
		{
			if (studentEduction.containsKey(educationDetails2.getClientName().getId()))
			{
				List<EducationDetails> recods = studentEduction.get(educationDetails2.getClientName().getId());
				recods.add(educationDetails2);
				studentEduction.put(educationDetails2.getClientName().getId(), recods);

			} else
			{
				List<EducationDetails> record = new LinkedList<>();
				record.add(educationDetails2);
				studentEduction.put(educationDetails2.getClientName().getId(), record);
			}
		}
		// EducationFields class is to check satisfy all criteria or not
		EducationFields educationFields = null;
		List<Integer> clientIdList = new ArrayList<>();
		for (Map.Entry<Integer, List<EducationDetails>> studEducation : studentEduction.entrySet())
		{

			double totalval = 0;
			int backlogCount = 0;

			// if criteria is no EducationFields fields set true
			educationFields = new EducationFields();
			if (tencriteria == 0)
			{
				educationFields.setSsc(true);
			}
			if (deplocriteria == 0)
			{
				educationFields.setHscAnddiploma(true);
			}
			if (degreecriteria == 0)
			{
				educationFields.setDigree(true);
			}
			if (backlog == 11)
			{
				educationFields.setBackLog(true);
			}

			for (EducationDetails educationDetails2 : studEducation.getValue())
			{

				if (educationDetails2.getStandard().equals("SSC") && educationFields.isSsc() == false)
				{
					if (educationDetails2.getPersentage() >= tencriteria)
					{
						educationFields.setSsc(true);
					}
				} else if ((educationDetails2.getStandard().equals("HSC")
						|| educationDetails2.getStandard().equals("DIPLOMA"))
						&& educationFields.isHscAnddiploma() == false)
				{
					if (educationDetails2.getPersentage() >= deplocriteria)
					{
						educationFields.setHscAnddiploma(true);
					}
				} else if (educationDetails2.getStandard().equals("FE"))
				{
					educationFields.setFe(educationFields.getFe() + educationDetails2.getPersentage());
					totalval = totalval + 100;
					System.out.println(educationDetails2.getNoOfBacklog());

					backlogCount = backlogCount
							+ (educationDetails2.getNoOfBacklog() != null ? educationDetails2.getNoOfBacklog() : 0);

				} else if (educationDetails2.getStandard().equals("SE"))
				{
					educationFields.setSe(educationFields.getSe() + educationDetails2.getPersentage());
					totalval = totalval + 100;
					backlogCount = backlogCount
							+ (educationDetails2.getNoOfBacklog() != null ? educationDetails2.getNoOfBacklog() : 0);
				} else if (educationDetails2.getStandard().equals("TE"))
				{
					educationFields.setTe(educationFields.getTe() + educationDetails2.getPersentage());
					totalval = totalval + 100;
					backlogCount = backlogCount
							+ (educationDetails2.getNoOfBacklog() != null ? educationDetails2.getNoOfBacklog() : 0);

				} else if (educationDetails2.getStandard().equals("BE"))
				{
					educationFields.setBe(educationFields.getBe() + educationDetails2.getPersentage());
					totalval = totalval + 100;
					backlogCount = backlogCount
							+ (educationDetails2.getNoOfBacklog() != null ? educationDetails2.getNoOfBacklog() : 0);

				}

			}
			if (!educationFields.isDigree())
			{
				double total = educationFields.getFe() + educationFields.getSe() + educationFields.getTe()
						+ educationFields.getBe();
				double percentage = total / totalval * 100;

				if (percentage >= degreecriteria)
				{
					educationFields.setDigree(true);
				}
			}

			if (!educationFields.isBackLog())
			{
				if (backlogCount <= backlog)
				{
					educationFields.setBackLog(true);
				}
			}

			if (educationFields.isSsc() == true && educationFields.isHscAnddiploma() == true
					&& educationFields.isDigree() == true && educationFields.isBackLog() == true)
			{
				clientIdList.add(studEducation.getKey());
			}

		}
		if (clientIdList.isEmpty())
		{
			List<ComClientNameDTO> list = new ArrayList<>();
			return list;

		} else
		{
			List<ComClientNameDTO> students = commonDAO.getStudentListForTPO(clientIdList);
			return students;

		}

	}

	@Override
	public List<SubjectResultDataModel> getResultBySubject(Map<String, String> parameters)
	{
		return commonDAO.getResultBySubjectDao(parameters);
	}

	@Override
	public String tpoStudentlistDownloadyear(Integer year, Integer[] depts, double tencriteria, double deplocriteria,
			double degreecriteria, Integer backlog)
	{

		List<EducationDetails> educationDetails = commonDAO.getfiltereducationalDTlofStudent(year, depts, tencriteria,
				deplocriteria, degreecriteria, backlog);

		if (educationDetails.isEmpty())
		{
			return "Student List Not Found";
		}
		Map<Integer, String> deptList = new HashMap<>();
		List<DepartmentDTO> department = notificationDAO.getDepartment();
		for (DepartmentDTO departmentDTO : department)
		{
			deptList.put(departmentDTO.getDep_id(), departmentDTO.getDep_name());
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
		cell.setCellValue("Student List");

		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue("sr.No");
		cell = row.createCell(1);
		cell.setCellValue("Name of Student");
		cell = row.createCell(2);
		cell.setCellValue("Email ID");
		cell = row.createCell(3);
		cell.setCellValue("Mobile No");
		cell = row.createCell(4);
		cell.setCellValue("Department Name");
		cell = row.createCell(5);
		cell.setCellValue("SSC Mark");
		cell = row.createCell(6);
		cell.setCellValue("HSC Mark");
		cell = row.createCell(7);
		cell.setCellValue("Diploma Mark");
		cell = row.createCell(8);
		cell.setCellValue("FE Sem 1");
		cell = row.createCell(9);
		cell.setCellValue("FE Sem 2");
		cell = row.createCell(10);
		cell.setCellValue("SE Sem 3");
		cell = row.createCell(11);
		cell.setCellValue("SE Sem 4");
		cell = row.createCell(12);
		cell.setCellValue("TE Sem 5");
		cell = row.createCell(13);
		cell.setCellValue("TE Sem 6");
		cell = row.createCell(14);
		cell.setCellValue("BE Sem 7");
		cell = row.createCell(15);
		cell.setCellValue("BE Sem 8");
		cell = row.createCell(16);
		cell.setCellValue("BE Percentage");

		Map<Integer, List<EducationDetails>> studentEduction = new LinkedHashMap<>();
		// store key as client id and value as that student Education recodr
		for (EducationDetails educationDetails2 : educationDetails)
		{
			if (studentEduction.containsKey(educationDetails2.getClientName().getId()))
			{
				List<EducationDetails> recods = studentEduction.get(educationDetails2.getClientName().getId());
				recods.add(educationDetails2);
				studentEduction.put(educationDetails2.getClientName().getId(), recods);

			} else
			{
				List<EducationDetails> record = new LinkedList<>();
				record.add(educationDetails2);
				studentEduction.put(educationDetails2.getClientName().getId(), record);
			}
		}
		// EducationFields class is to check satisfy all criteria or not
		EducationFields educationFields = null;
		List<Integer> clientIdList = new ArrayList<>();
		int rowCount = 2;
		int sr_No = 1;
		for (Map.Entry<Integer, List<EducationDetails>> studEducation : studentEduction.entrySet())
		{

			double totalval = 0;
			int backlogCount = 0;

			// if criteria is no EducationFields fields set true
			educationFields = new EducationFields();
			if (tencriteria == 0)
			{
				educationFields.setSsc(true);
			}
			if (deplocriteria == 0)
			{
				educationFields.setHscAnddiploma(true);
			}
			if (degreecriteria == 0)
			{
				educationFields.setDigree(true);
			}
			if (backlog == 11)
			{
				educationFields.setBackLog(true);
			}

			for (EducationDetails educationDetails2 : studEducation.getValue())
			{

				if (educationDetails2.getStandard().equals("SSC") && educationFields.isSsc() == false)
				{
					if (educationDetails2.getPersentage() >= tencriteria)
					{
						educationFields.setSsc(true);
					}
				} else if ((educationDetails2.getStandard().equals("HSC")
						|| educationDetails2.getStandard().equals("DIPLOMA"))
						&& educationFields.isHscAnddiploma() == false)
				{
					if (educationDetails2.getPersentage() >= deplocriteria)
					{
						educationFields.setHscAnddiploma(true);
					}
				} else if (educationDetails2.getStandard().equals("FE"))
				{
					educationFields.setFe(educationFields.getFe() + educationDetails2.getPersentage());
					totalval = totalval + 100;
					System.out.println(educationDetails2.getNoOfBacklog());

					backlogCount = backlogCount
							+ (educationDetails2.getNoOfBacklog() != null ? educationDetails2.getNoOfBacklog() : 0);

				} else if (educationDetails2.getStandard().equals("SE"))
				{
					educationFields.setSe(educationFields.getSe() + educationDetails2.getPersentage());
					totalval = totalval + 100;
					backlogCount = backlogCount
							+ (educationDetails2.getNoOfBacklog() != null ? educationDetails2.getNoOfBacklog() : 0);
				} else if (educationDetails2.getStandard().equals("TE"))
				{
					educationFields.setTe(educationFields.getTe() + educationDetails2.getPersentage());
					totalval = totalval + 100;
					backlogCount = backlogCount
							+ (educationDetails2.getNoOfBacklog() != null ? educationDetails2.getNoOfBacklog() : 0);

				} else if (educationDetails2.getStandard().equals("BE"))
				{
					educationFields.setBe(educationFields.getBe() + educationDetails2.getPersentage());
					totalval = totalval + 100;
					backlogCount = backlogCount
							+ (educationDetails2.getNoOfBacklog() != null ? educationDetails2.getNoOfBacklog() : 0);

				}

			}
			if (!educationFields.isDigree())
			{
				double total = educationFields.getFe() + educationFields.getSe() + educationFields.getTe()
						+ educationFields.getBe();
				double percentage = total / totalval * 100;

				if (percentage >= degreecriteria)
				{
					educationFields.setDigree(true);
				}
			}

			if (!educationFields.isBackLog())
			{
				if (backlogCount <= backlog)
				{
					educationFields.setBackLog(true);
				}
			}

			if (educationFields.isSsc() == true && educationFields.isHscAnddiploma() == true
					&& educationFields.isDigree() == true && educationFields.isBackLog() == true)
			{

				EducationDetails studData = studEducation.getValue().get(0);
				row = sheet.createRow(rowCount);
				cell = row.createCell(0);
				cell.setCellValue(sr_No);
				cell = row.createCell(1);
				cell.setCellValue(studData.getClientName().getFirstName() + " "
						+ studData.getClientName().getMiddleName() + " " + studData.getClientName().getLastName());
				cell = row.createCell(2);
				cell.setCellValue(studData.getClientName().getEmailId());
				cell = row.createCell(3);
				cell.setCellValue(studData.getClientName().getContactNos());

				ComClientNameDTO student = commonDAO
						.getStudentDataOnStaffTable_UsingClientId(studData.getClientName().getId());
				cell = row.createCell(4);
				cell.setCellValue(deptList.get(student.getDepId()));

				double FE = 0, SE = 0, TE = 0, BE = 0;
				int sem = 0;
				for (EducationDetails educationDetails2 : studEducation.getValue())
				{

					if (educationDetails2.getStandard().equals("SSC"))
					{
						cell = row.createCell(5);
						cell.setCellValue(educationDetails2.getPersentage());

					} else if (educationDetails2.getStandard().equals("HSC"))
					{
						cell = row.createCell(6);
						cell.setCellValue(educationDetails2.getPersentage());
					} else if (educationDetails2.getStandard().equals("DIPLOMA"))
					{
						cell = row.createCell(7);
						cell.setCellValue(educationDetails2.getPersentage());
					} else if (educationDetails2.getStandard().equals("FE")
							&& educationDetails2.getSemester().getSemid() == 1)
					{
						cell = row.createCell(8);
						cell.setCellValue(educationDetails2.getPersentage());
						FE = FE + educationDetails2.getPersentage();
						sem++;
					} else if (educationDetails2.getStandard().equals("FE")
							&& educationDetails2.getSemester().getSemid() == 2)
					{
						cell = row.createCell(9);
						cell.setCellValue(educationDetails2.getPersentage());
						FE = FE + educationDetails2.getPersentage();
						sem++;
					} else if (educationDetails2.getStandard().equals("SE")
							&& educationDetails2.getSemester().getSemid() == 3)
					{
						cell = row.createCell(10);
						cell.setCellValue(educationDetails2.getPersentage());
						SE = SE + educationDetails2.getPersentage();
						sem++;

					} else if (educationDetails2.getStandard().equals("SE")
							&& educationDetails2.getSemester().getSemid() == 4)
					{
						cell = row.createCell(11);
						cell.setCellValue(educationDetails2.getPersentage());
						SE = SE + educationDetails2.getPersentage();
						sem++;

					} else if (educationDetails2.getStandard().equals("TE")
							&& educationDetails2.getSemester().getSemid() == 5)
					{
						cell = row.createCell(12);
						cell.setCellValue(educationDetails2.getPersentage());
						TE = TE + educationDetails2.getPersentage();
						sem++;

					} else if (educationDetails2.getStandard().equals("TE")
							&& educationDetails2.getSemester().getSemid() == 6)
					{
						cell = row.createCell(13);
						cell.setCellValue(educationDetails2.getPersentage());
						TE = TE + educationDetails2.getPersentage();
						sem++;
					} else if (educationDetails2.getStandard().equals("BE")
							&& educationDetails2.getSemester().getSemid() == 7)
					{
						cell = row.createCell(14);
						cell.setCellValue(educationDetails2.getPersentage());
						BE = BE + educationDetails2.getPersentage();
						sem++;
					} else if (educationDetails2.getStandard().equals("BE")
							&& educationDetails2.getSemester().getSemid() == 8)
					{
						cell = row.createCell(15);
						cell.setCellValue(educationDetails2.getPersentage());
						BE = BE + educationDetails2.getPersentage();
						sem++;
					}

				}

				double total = FE + SE + TE + BE;
				double totalSub = sem * 100;
				double percentage = total / totalSub * 100;
				DecimalFormat df = new DecimalFormat("#.##");

				cell = row.createCell(16);
				cell.setCellValue(df.format(percentage));

				rowCount++;
				sr_No++;
			}

		}

		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		try
		{
			wb.write(outByteStream);
			byte[] outArray = outByteStream.toByteArray();
			responses.setContentType("application/ms-excel");
			responses.setContentLength(outArray.length);
			responses.setHeader("Expires:", "0"); // eliminates browser caching
			responses.setHeader("Content-Disposition", "attachment; filename=StudentList.xls");
			OutputStream outStream = responses.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
			outStream.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "download";

	}

	@Override
	public TpoDrive companyCriteriaCheck(int clientId, int driveId)
	{
		RecruitmentInfo recruitmentInfo = commonDAO.getDriveDetailsUsingId(driveId);
		System.out.println("recruitmentInfo:::" + recruitmentInfo);
		List<EducationDetails> educationDetails = commonDAO.getEducationDetailsUsingID(clientId);
		EducationFields educationFields = null;

		educationFields = new EducationFields();
		if (recruitmentInfo.getTenth() == 0)
		{
			educationFields.setSsc(true);
		}
		if (recruitmentInfo.getTwelveth() == 0)
		{
			educationFields.setHscAnddiploma(true);
		}
		if (recruitmentInfo.getDegree() == 0)
		{
			educationFields.setDigree(true);
		}
		if (recruitmentInfo.getBacklog() == 11)
		{
			educationFields.setBackLog(true);
		}

		TpoApplayCriteria tpoApplayCriteria = null;
		double totalval = 0;
		int backlogCount = 0, count = 0;
		TpoDrive tpoDrive = new TpoDrive();
		tpoDrive.setReInfoId(recruitmentInfo.getReInfoId());
		for (EducationDetails educationDetails2 : educationDetails)
		{

			tpoApplayCriteria = new TpoApplayCriteria();

			if (educationDetails2.getStandard().equals("SSC"))
			{
				if (educationDetails2.getPersentage() >= recruitmentInfo.getTenth())
				{
					educationFields.setSsc(true);
					tpoApplayCriteria.setStatus(true);
				} else
				{
					tpoApplayCriteria.setStatus((educationFields.isSsc() == true ? true : false));
				}
				tpoApplayCriteria.setStandard("SSC");
				tpoApplayCriteria.setMark(educationDetails2.getPersentage());
				tpoApplayCriteria.setCompanyCriteria(
						(recruitmentInfo.getTenth() == 0 ? "NO Criteria" : String.valueOf(recruitmentInfo.getTenth())));
				count++;

			} else if (educationDetails2.getStandard().equals("HSC"))
			{
				if (educationDetails2.getPersentage() >= recruitmentInfo.getTwelveth())
				{
					educationFields.setHscAnddiploma(true);
					tpoApplayCriteria.setStatus(true);

				} else
				{
					tpoApplayCriteria.setStatus((educationFields.isHscAnddiploma() == true ? true : false));
				}
				tpoApplayCriteria.setStandard("HSC");
				tpoApplayCriteria.setMark(educationDetails2.getPersentage());
				tpoApplayCriteria.setCompanyCriteria((recruitmentInfo.getTwelveth() == 0 ? "NO Criteria"
						: String.valueOf(recruitmentInfo.getTwelveth())));
				count++;

			} else if (educationDetails2.getStandard().equals("DIPLOMA"))
			{
				if (educationDetails2.getPersentage() >= recruitmentInfo.getTwelveth())
				{
					educationFields.setHscAnddiploma(true);
					tpoApplayCriteria.setStatus(true);

				} else
				{
					tpoApplayCriteria.setStatus((educationFields.isHscAnddiploma() == true ? true : false));
				}
				tpoApplayCriteria.setStandard("DIPLOMA");
				tpoApplayCriteria.setMark(educationDetails2.getPersentage());
				tpoApplayCriteria.setCompanyCriteria((recruitmentInfo.getTwelveth() == 0 ? "NO Criteria"
						: String.valueOf(recruitmentInfo.getTwelveth())));
				count++;

			} else if (educationDetails2.getStandard().equals("FE"))
			{
				educationFields.setFe(educationFields.getFe() + educationDetails2.getPersentage());
				totalval = totalval + 100;

				backlogCount = backlogCount
						+ (educationDetails2.getNoOfBacklog() != null ? educationDetails2.getNoOfBacklog() : 0);
				count++;

			} else if (educationDetails2.getStandard().equals("SE"))
			{
				educationFields.setSe(educationFields.getSe() + educationDetails2.getPersentage());
				totalval = totalval + 100;
				backlogCount = backlogCount
						+ (educationDetails2.getNoOfBacklog() != null ? educationDetails2.getNoOfBacklog() : 0);
				count++;

			} else if (educationDetails2.getStandard().equals("TE"))
			{
				educationFields.setTe(educationFields.getTe() + educationDetails2.getPersentage());
				totalval = totalval + 100;
				backlogCount = backlogCount
						+ (educationDetails2.getNoOfBacklog() != null ? educationDetails2.getNoOfBacklog() : 0);
				count++;

			} else if (educationDetails2.getStandard().equals("BE"))
			{
				educationFields.setBe(educationFields.getBe() + educationDetails2.getPersentage());
				totalval = totalval + 100;
				backlogCount = backlogCount
						+ (educationDetails2.getNoOfBacklog() != null ? educationDetails2.getNoOfBacklog() : 0);
				count++;

			}
			if (tpoApplayCriteria.getStandard() != null)
			{
				tpoDrive.getList().add(tpoApplayCriteria);
			}

		}

		double total = educationFields.getFe() + educationFields.getSe() + educationFields.getTe()
				+ educationFields.getBe();
		double percentage = total / totalval * 100;

		System.out.println("percentage..getClass()...." + percentage);
		DecimalFormat df = new DecimalFormat("#.##");

		TpoApplayCriteria tpoApplayCriteria1 = new TpoApplayCriteria();
		if (percentage >= recruitmentInfo.getDegree())
		{
			educationFields.setDigree(true);
			tpoApplayCriteria1.setStatus(true);
		} else
		{
			tpoApplayCriteria1.setStatus((educationFields.isDigree() == true ? true : false));

		}
		String per = df.format(percentage);
		tpoApplayCriteria1.setStandard("DEGREE");
		try
		{
			tpoApplayCriteria1.setMark(Double.parseDouble(per));

		} catch (Exception e)
		{
			tpoApplayCriteria1.setMark(0);

		}
		tpoApplayCriteria1.setCompanyCriteria(
				(recruitmentInfo.getDegree() == 0 ? "NO Criteria" : String.valueOf(recruitmentInfo.getDegree())));

		TpoApplayCriteria tpoApplayCriteria2 = new TpoApplayCriteria();

		System.out.println(recruitmentInfo.getBacklog() + "........bean....." + educationFields);
		if (!educationFields.isBackLog())
		{

			System.out.println("inside if....." + backlogCount);
			if (backlogCount <= recruitmentInfo.getBacklog())
			{
				educationFields.setBackLog(true);
				tpoApplayCriteria2.setStatus(true);
			}
		} else
		{
			tpoApplayCriteria2.setStatus((educationFields.isBackLog() == true ? true : false));

		}
		tpoApplayCriteria2.setStandard("DEGREE BACKLOG");
		tpoApplayCriteria2.setMark(backlogCount);
		tpoApplayCriteria2.setCompanyCriteria(
				(recruitmentInfo.getBacklog() == 11 ? "NO Criteria" : String.valueOf(recruitmentInfo.getBacklog())));
		tpoDrive.getList().add(tpoApplayCriteria1);
		tpoDrive.getList().add(tpoApplayCriteria2);

		if (educationFields.isSsc() == true && educationFields.isHscAnddiploma() == true
				&& educationFields.isDigree() == true && educationFields.isBackLog() == true)
		{
			tpoDrive.setApplayStatus(true);
		}
		if (count >= 5)
		{
			tpoDrive.setMarkInsert(true);
		}
		tpoDrive.setCheckAllreadyApplay(commonDAO.checkapplayForRe_Drive(clientId, driveId));

		return tpoDrive;
	}

	@Override
	public boolean applayForDrive(int clientId, int driveId)
	{
		return commonDAO.applayForDrive(clientId, driveId);
	}

	@Override
	public List<AssignedCoordinators> getcoordinatorlist(Integer staff_id, String dt, String option)
	{
		return commonDAO.getcoordinatorlistDao(staff_id, dt, option);
	}

	@Override
	public List<ComStaffSubject> getAcademicSubjectByStaff(Integer staff_id, String dt, String option)
	{
		return commonDAO.getAcademicSubjectByStaffDao(staff_id, dt, option);
	}

	@Override
	public List<SubjectResultDataModel> getAttendanceBySubject(Map<String, String> parameters)
	{
		return commonDAO.getAttendanceBySubjectDao(parameters);
	}

	@Override
	public List<PortFolioInfo> getStaffPartipationlist(Integer staff_id, String dt, String option)
	{
		return commonDAO.getCoordinatorInfoDao(staff_id, dt, option);
	}

	@Override
	public List<ApplyedStudentForCompany> getapplyedStudentlist(Integer reInfoId, Integer id)
	{
		// TODO Auto-generated method stub
		return commonDAO.getapplyedStudentlist(reInfoId, id);
	}

	@Override
	public DriveStudentRoundCount getcountround(Integer id)
	{
		// TODO Auto-generated method stub
		List<ApplyedStudentForCompany> list = commonDAO.getcountround(id);
		int ffilter = 0, round1 = 0, round2 = 0, round3 = 0, round4 = 0, round5 = 0, round6 = 0;

		for (ApplyedStudentForCompany applyedStudentForCompany : list)
		{

			if (applyedStudentForCompany.isFinalfilter() == true)
			{
				ffilter++;
			}
			if (applyedStudentForCompany.isRound1() == true)
			{
				round1++;
			}
			if (applyedStudentForCompany.isRound2() == true)
			{
				round2++;
			}
			if (applyedStudentForCompany.isRound3() == true)
			{
				round3++;
			}
			if (applyedStudentForCompany.isRound4() == true)
			{
				round4++;
			}
			if (applyedStudentForCompany.isRound5() == true)
			{
				round5++;
			}
			if (applyedStudentForCompany.isRound6() == true)
			{
				round6++;
			}

		}
		DriveStudentRoundCount Dsrc = new DriveStudentRoundCount();

		Dsrc.setFfilter(ffilter);
		Dsrc.setRound1(round1);
		Dsrc.setRound2(round2);
		Dsrc.setRound3(round3);
		Dsrc.setRound4(round4);
		Dsrc.setRound5(round5);
		Dsrc.setRound6(round6);
		Dsrc.setTotalapply(list.size());

		return Dsrc;

	}

	@Override
	public Object SaveAelectedStudNextRound(Integer[] studId, Integer roundId, Integer reInfoId)
	{
		return commonDAO.SaveAelectedStudNextRound(studId, roundId, reInfoId);
	}

	@Override
	public boolean upadteStudentAcadmicYear(int clientId, int year, String acYear)
	{
		return commonDAO.upadteStudentAcadmicYear(clientId, year, acYear);
	}

	@Override
	public List<ComClientNameDTO> getPassoutYearDownStudent(int deptID, int yearID, String aCyear)
	{
		return commonDAO.getPassoutYearDownStudent(deptID, yearID, aCyear);

	}

	@Override
	public List<OnCampusPlaceStudListDTO> getplaceStudentList()
	{
		return commonDAO.getplaceStudentList();

	}

	@Override
	public List<ComVideoURL> getVideoURL_List(int instCode)
	{
		return commonDAO.getVideoURL_List(instCode);

	}

	@Override
	public List<ValueAddedProgram> getValueAddedProgam(Integer clientId)
	{
		return commonDAO.getValueAddedProgamDao(clientId);
	}

	@Override
	public List<GfmInformation> getMentorListByStudent(Integer clientId)
	{
		return commonDAO.getMentorListByStudentDao(clientId);
	}

	@Override
	public List<StudentAttendanceDto> getStudentCurrentAttendance(Integer clientId)
	{
		return commonDAO.getStudentCurrentAttendanceDao(clientId);
	}

	@Override
	public List<ExtraActivities> getStudentExtraActivities(Integer clientId)
	{
		return commonDAO.getStudentExtraActivitiesDao(clientId);
	}

	@Override
	public ExtraActivities saveExtraActivity(ExtraActivities extraActivities)
	{
		return commonDAO.saveExtraActivityDao(extraActivities);
	}

	@Override
	public Object sendOffertostudent(Integer[] studId, Integer reInfoId)
	{
		// TODO Auto-generated method stub
		RecruitmentInfo recruitmentInfo = commonDAO.saveExtraActivityDao(studId, reInfoId);

		pushNotificationService.sendOfferLaterNotification(recruitmentInfo, studId);
		return null;

	}

	@Override
	public PlacementDriveStatus getDriveSatus(Integer studId, Integer driveID)
	{
		// TODO Auto-generated method stub
		return commonDAO.getDriveSatus(studId, driveID);
	}

	@Override
	public Object saveOfferAcceptedStudent(Integer studId, Integer driveID)
	{
		// TODO Auto-generated method stub
		return commonDAO.saveOfferAcceptedStudent(studId, driveID);
	}

	@Override
	public List<ApplyedStudentForCompany> getplacementroundDetails(Integer clientId)
	{
		// TODO Auto-generated method stub

		return commonDAO.getplacementroundDetails(clientId);
	}

	@Override
	public List<CompanySelectionRounds> ggetroundlistofdrive(Integer id)
	{
		// TODO Auto-generated method stub
		return commonDAO.ggetroundlistofdrive(id);
	}

	@Override
	public List<Student> getstudentdata()
	{
		// TODO Auto-generated method stub
		return commonDAO.getstudentdata();
	}

	@Override
	public List<Staff> getstaffdata()
	{
		// TODO Auto-generated method stub
		return commonDAO.getstaffdata();
	}

	@Override
	public Groups savegroup(Mentor mentor, Integer[] students)
	{
		// TODO Auto-generated method stub
		return commonDAO.savegroup(mentor, students);
	}

	@Override
	public String updateStudentform(AdminForm student)
	{
		System.out.println("come in serviceee" + student);
		// TODO Auto-generated method stub
		return commonDAO.updateStudentform(student);
	}

	@Override
	public String deletementeestudent(Integer[] member)
	{
		return commonDAO.deletementeestudent(member);

	}

	@Override
	public StudentForm getstudinfodtltoparent(Integer studId)
	{
		// TODO Auto-generated method stub
		return commonDAO.getstudinfodtltoparent(studId);
	}

	@Override
	public List<ExtraActivities> getfutureinterestInfo(Integer cid, Integer type)
	{
		// TODO Auto-generated method stub
		return commonDAO.getfutureinterestInfo(cid, type);
	}

	@Override
	public ExtraActivities savefuturelist(ExtraActivities extraActivities)
	{
		// TODO Auto-generated method stub
		return commonDAO.savefuturelist(extraActivities);
	}

	@Override
	public Map<String, List<TotalStudentCount>> getsudenttotalcount(Integer deptid)
	{
		// TODO Auto-generated method stub
		return commonDAO.getsudenttotalcount(deptid);
	}

	@Override
	public CollegeCountDTO gettotolcollegecount()
	{
		// TODO Auto-generated method stub
		return commonDAO.gettotolcollegecount();
	}

	@Override
	public List<TotalStudentCount> gettotaldepartmentcount()
	{
		// TODO Auto-generated method stub
		return commonDAO.gettotaldepartmentcount();
	}

	@Override
	public List<Student> getfilterstudentlist(Integer year, Integer depts, Integer div)
	{
		// TODO Auto-generated method stub
		return commonDAO.getfilterstudentlist(year, depts, div);
	}

	@Override
	public OffcampusPlaceStud Saveoffcampusstudlist(OffcampusPlaceStud data)
	{
		return commonDAO.Saveoffcampusstudlist(data);
	}

	@Override
	public List<OffcampusPlaceStud> getoffcampusstudlist()
	{
		// TODO Auto-generated method stub
		return commonDAO.getoffcampusstudlist();
	}

	@Override
	public List<ExtraActivityDTO> getstudentcarrierlist(Integer eid, Integer type)
	{
		// TODO Auto-generated method stub
		return commonDAO.getstudentcarrierlist(eid, type);
	}

	@Override
	public List<StudentFeeDtl> getfeedepartmentdtl()
	{
		// TODO Auto-generated method stub
		return commonDAO.getfeedepartmentdtl();
	}

	@Override
	public CollegeTotalFee getcollegetotalfee()
	{
		// TODO Auto-generated method stub
		return commonDAO.getcollegetotalfee();
	}

	@Override
	public Map<String, List<StudentFeeDtl>> gettotalstudentfee(Integer id)
	{
		// TODO Auto-generated method stub
		return commonDAO.gettotalstudentfee(id);
	}

	@Override
	public Parentcallrecord saveparentcall(Integer studid, Date date, String remark)
	{
		// TODO Auto-generated method stub
		return commonDAO.saveparentcall(studid, date, remark);

	}

	@Override
	public List<Mentor> getmentorfprofile()
	{
		// TODO Auto-generated method stub
		return commonDAO.getmentorfprofile();
	}

	@Override
	public String getgfmrstudemark(Studentskillsdto gfmremark)
	{
		// TODO Auto-generated method stub
		return commonDAO.getgfmrstudemark(gfmremark);
	}

	@Override
	public List<RemarkOption> getliststudentremarks(Integer id, Integer mentorid)
	{
		// TODO Auto-generated method stub
		return commonDAO.getliststudentremarks(id, mentorid);
	}

	@Override
	public HashMap<String, List<String>> getskilllist(Integer mid, Integer studentid)
	{
		return commonDAO.getskilllist(mid, studentid);
	}

	@Override
	public String getstudentremark(StudentRemark studentremark)
	{

		return commonDAO.getstudentremark(studentremark);
	}

	@Override
	public List<StudentRemarkOption> getcheckremarkliststud(Integer id, Integer mentorid)
	{
		// TODO Auto-generated method stub
		return commonDAO.getcheckremarkliststud(id, mentorid);
	}

	@Override
	public List<StudentRemarkOption> getstudentremarkview(Integer id, Integer mentorid)
	{
		// TODO Auto-generated method stub
		return commonDAO.getstudentremarkview(id, mentorid);
	}

	@Override
	public List<StudentRemarkOption> getstudentremarklist(Integer id)
	{
		// TODO Auto-generated method stub
		return commonDAO.getstudentremarklist(id);
	}

	@Override
	public String getuserregistration(NewUserDTO user)
	{
		// TODO Auto-generated method stub
		return commonDAO.getuserregistration(user);

	}

	@Override
	public String getuseremailidcheck(String emailid)
	{
		// TODO Auto-generated method stub
		return commonDAO.getuseremailidcheck(emailid);
	}

	@Override
	public List<ResultInstituteList> getpdfresultlist()
	{
		// TODO Auto-generated method stub
		return commonDAO.getpdfresultlist();
	}

	@Override
	public List<Resultsemester> getresultsemester()
	{
		// TODO Auto-generated method stub
		return commonDAO.getresultsemester();
	}

	@Override
	public List<ResultInstituteList> getInstituteResult()
	{
		// TODO Auto-generated method stub
		return commonDAO.getInstituteResult();
	}

	@Override
	public String getsuperadminreport(Integer aid, Integer bid, Integer cid, Integer instid)
	{
		// TODO Auto-generated method stub
		return commonDAO.getsuperadminreport(aid, bid, cid, instid);
	}

	@Override
	public List<ResultInstituteList> getcommoninstitudereport()
	{

		// TODO Auto-generated method stub
		return commonDAO.getcommoninstitudereport();
	}

	@Override
	public String getcommonsuperadmindeptreport(Integer aid, Integer bid, String cid)
	{
		// TODO Auto-generated method stub
		return commonDAO.getcommonsuperadmindeptreport(aid, bid, cid);
	}

}
