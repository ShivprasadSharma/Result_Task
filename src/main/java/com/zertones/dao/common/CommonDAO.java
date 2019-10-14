package com.zertones.dao.common;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

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
import com.zertones.model.common.ExamSchedule;
import com.zertones.model.common.ExtraActivities;
import com.zertones.model.common.FeeDetails;
import com.zertones.model.common.GroupMembers;
import com.zertones.model.common.Groups;
import com.zertones.model.common.Help;
import com.zertones.model.common.InstituteInfoDetails;
import com.zertones.model.common.InstituteInfoMaster;
import com.zertones.model.common.LecturePracticalTime;
import com.zertones.model.common.LectureTheoryTime;
import com.zertones.model.common.LifeOfCampus;
import com.zertones.model.common.MenuList;
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

public interface CommonDAO
{
	/*
	 * public void addAboutUs(AboutUsModel aboutUsModel);
	 *
	 * public void updateAboutUs(AboutUsModel aboutUsModel);
	 *
	 * public AboutUsModel getAboutUsModelId(Integer id);
	 *
	 * public void removeAboutUs(Integer id);
	 *
	 * public void addContactUs(ContactUsModel contactUsModel);
	 *
	 * public void updateContactUs(ContactUsModel contactUsModel);
	 *
	 * public ContactUsModel getContactUsModelId(Integer id);
	 *
	 * public List<ContactUsModel> getContactUsLists();
	 *
	 * public void removeContactUs(Integer id);
	 *
	 * public void addAwardsAndAchievements(AwardsAndAchievementModel
	 * awardsAndAchievementModel);
	 *
	 * public void updateAwardsAndAchievements(AwardsAndAchievementModel
	 * awardsAndAchievements);
	 *
	 * public AwardsAndAchievementModel getAwardsAndAchievementsById(Integer id);
	 *
	 * public void removeAwardsAndAchievement(Integer id);
	 */

	public boolean addContactUs(ContactUsModel contactUsModel);

	public List<AwardsAndAchievementModel> getAwardsAndAchievements();

	public void addInstituteInfoMaster(InstituteInfoMaster instituteInfoMaster);

	public void updateInstituteInfoMatser(InstituteInfoMaster instituteInfoMaster);

	public InstituteInfoMaster getInstituteInfoMasterById(Integer id);

	public InstituteInfoDetails getMasterDtlById(Integer id);

	public List<InstituteInfoMaster> getInstituteInfoMatserList();

	public List<LifeOfCampus> getlifeOfCampuse();

	public void addInstituteInfoDetails(InstituteInfoDetails instituteInfoDetails);

	public void updateInstituteInfoDetails(InstituteInfoDetails instituteInfoDetails);

	public List<InstituteInfoDetails> getInstituteInfoDetailsList(Integer instituteInfoMasterId);

	public List<MenuList> getMenuList();

	public ComListMaster getCommonMasterList(Integer valueId);

	public List<ComListMaster> getCommonMasterList();

	public List<ComUserDetails> searchUsser(String username);

	public TimeTable addTimeTable(TimeTable timeTable);

	public ExamSchedule saveExamSchedule(ExamSchedule timeTable);

	public CourseMaster saveCourseMaster(CourseMaster courseMaster);

	public AcademicYear saveAcademicYear(AcademicYear academicYear);

	public List<Days> getTimeTable(Integer yearid);

	public List<ExamSchedule> getExamSchedule();

	public List<CourseMaster> getCourseMaster();

	public List<AcademicYear> getAcademicYear();

	public AcademicSemester getYearSemester(Integer id);

	public List<Staff> getTeacherProfile();

	public Staff getTeacherProfileById(Integer clientId);

	public Staff userLoginCheck(String name);

	public ComClientName adduser(ComClientName comClientName);

	public List<ComClientName> getUserList();

	public ComClientName getUserDtlById(Integer userId);

	public ComClientName removeUser(Integer userId);

	public List<ComClientName> getTeacherList(String query);

	public ComClientName chklogin(DeviceDetails deviceDetails);

	public List<DepartmentDTO> getDepartmentList();

	public List<emailData> getEmailIds();

	public List<DepartmentDTO> getDepartmentList(Integer cid);

	public List<Staff> getStaffById(Integer id);

	public Groups saveGroups(Groups group, Integer[] member);

	public Boolean updateGroupsInfo(Groups group);

	public List<GroupDTO> getListGroup(Integer cid);

	public List<Groups> getListGroupByDepartment(Integer depId);

	public List<GroupDTO> getGroupDetails(Integer groupId);

	public boolean updateGroups(Groups group, Integer[] member);

	public boolean removeGroup(Integer id);

	public Boolean removeGroupMember(Groups group, Integer[] member);

	public ComClientName findUserByEmailAdd(String emailAddress);

	public UserToken saveUserVerificationToken(UserToken userToken);

	public UserToken getUserByToken(String authToken);

	public boolean updateUserEntity(ComUser comUser);

	public List<GroupMembers> getGroupListByStdId(Integer id);

	public Boolean saveAttendance(ComAttendance attendance);

	public List<GroupDTO> getGroupListByType(Integer type, Integer cid);

	public List<ComAttendance> getAttendenacBySub(Integer subCode);

	public List<ComStaffSubject> getSubjectByStaff(Integer staffempid);

	public double getMonthlyAttenedance(LocalDate date, Integer staffSub_id);

	public boolean saveMarkSheetDao(MarkSheet sheet);

	public List<Object[]> getSummaryResult(Integer exam_yr, Integer semid, Integer depid, Integer sub);

	public List<Object[]> getAllStudentListFromMrksheet(Integer yr, Integer sem, Integer sub);

	public ComListMaster getAboutUsModelDao();

	public List<DirectoryDTO> getDirectoryList();

	public List<ResultFile> getResultFileList();

	public List<Object[]> getSubjectRsultPersentage(Integer sem, Integer dep_id, Integer exam_yr, Integer sub);

	public List<SubjectList> getSubjectdata(Integer dep, Integer sem);

	public List<AcademicSubject> getAcademicSubData(Integer dep, Integer sem);

	public void insertTimeTable(TimeTable timeTable);

	public String getSubjectTeacherdata(Integer subId);

	public void addSubjectdata(Integer depId, Integer semId, Integer[] subject);

	public List<SubjectList> getAcademicSubject();

	public void insertTeacherSubject(Integer[] subject, Integer cid);

	public List<TimeTableDTO> getStaffLectures(Integer id);

	public List<TimeTableDTO> getTimetableSubjectDao(String date);

	public Integer addAttendance(ComAttendance comAttendance, Integer[] studId, Integer subjectId);

	public Map<Integer, String> getpracticalSubdata(Integer staffId, Integer sem);

	public String saveSubjectListViaExcelFile(MultipartFile excelFile) throws Exception;

	public List<AcademicSubject> getSubjectToAssign();

	public String saveFeedbackListViaExcelFile(MultipartFile excelFile);

	public List<SubjectsDTO> getStaffSubject(Integer staffClientId);

	public List<ComClientNameDTO> getAcademiStudListDto(Integer subid, String div, int attendanceid, String batchID);

	public boolean addStudAttendance(SubjectsDTO studsub);

	public Staff getstaffData(Integer staff_id);

	public List<Mentor> getMentorStudentList(Integer staff_id);

	public List<ComStaffSubject> getAssigendStaffSubject(Integer staffId);

	public List<String> getdivision(Integer staffId, Integer subId);

	public List<DepartmentDTO> getDepartmentListForAttendance(Integer clientId);

	public AttendanceDTOWeb getDailyAttendanceData(ComAttendance comAttendance, int subid);

	public Boolean dailyAttendanceReportDownload(ComAttendance comAttendance, int reportId, String todate);

	public List<Mentor> getMenterGroupList(Integer staffId);

	public void deleteMenterGroup(Integer groupid);

	public Integer SaveStudFeeDetails(Integer studId, FeeDetails feeDetails);

	public FeeDetails getFeeDetails(Integer clientId);

	public void deleteAssingedSubject(Integer clientID, Integer subjectId);

	public List<String> getDivision_For_Batch(Integer depId, Integer classId);

	public List<ComClientNameDTO> getStudent_For_Batch(Integer depId, Integer classId, String divId);

	public int add_Student_Batch(BatchList batchList, int depId, int classId, String divId);

	public List<String> getPractical_Batch(Integer subId, String division);

	public int addPracticalAttendance(ComAttendance comAttendance, Integer[] studId, Integer subjectId, String batch);

	public boolean addBatchStudAttendance(SubjectsDTO studsub);

	public List<LecturePracticalTime> getPracticalLectureTime();

	public List<LectureTheoryTime> getTheoryLectureTime();

	public AttendanceDTOWeb getPracticalDailyAttendanceData(ComAttendance comAttendance, int subjectId, String batch);

	public Boolean daily_PracticalAttendanceReportDownload(ComAttendance comAttendance, int reportid, String todate,
			String batch);

	public AttendancePunch addStudentPunch(AttendancePunch attendancePunch);

	public AttendancePunch addStudentPunch(Integer clientID);

	public AttendancePunch getStudentPunchCount(AttendancePunch attendancePunch, int id);

	public Groups createSubjectGroup(Integer subjectId, Integer clientID, Integer div);

	public Groups createClassGroup(Integer staffId, Integer yearId, Integer div);

	public Groups createStudentGroup(Groups group, Integer id);

	public List<Groups> joinGroupList(int id);

	public boolean UpdateMemberGroup(Groups group, int id);

	public boolean checkTermsAndCondition(Integer clientID);

	public List<Help> commonDAOgetHelpUrl(int id, int menuid);

	public AttendancePunch studPunchAdd(int punchId, int studId);

	public String getmenupage(Integer institute_id, Integer menuid);

	public List<CollegeMenuList> college_DynamicMenu();

	public List<MianManuList> getMainMenuList();

	public void instituteDataAdd(InstituteInfoMaster instituteInfoMaster, int[] menuiId);

	public List<InstituteInfoMaster> getInstituteInfoDetailsList();

	public List<CollegeMenuList> getInstitutemenuInfoById(Integer instid);

	public void updateInstitute(InstituteInfoMaster instituteInfoMaster, int[] menuiId);

	public List<Navbar_MenuList> getNabBar_MenuList(Integer institute_id);

	public List<String> getDivisionAtten_Report(Integer deptId, Integer yearId);

	public List<String> getPractical_Batch_forAtt_Report(Integer deptId, Integer yearId, String divId);

	public List<AcademicSubject> getassignsubject(Integer staff_id);

	public List<Groups> getgroup(Integer clientid);

	// result urls
	public String getResultreport(Integer aid, Integer bid, Integer sid);

	public void getResultReportStudent(int aid, int bid, int cid);

	public String getSubjectelist(MultipartFile subjectfile) throws Exception;

	public boolean saveResulMarksheet(ResultMarksheet sheet, String subjectCode);

	public ResultStudent saveStudentResult(ResultStudent student);

	public List<AcademicSubject> getresultsubjectlist(Integer deptid, Integer classid, Integer semid);

	public EducationDetails addEducationDetailsDao(EducationDetails educatinalDtl);

	public void removeEducationDetailsDao(EducationDetails educatinalDtl);

	public List<EducationDetails> getEducationDetailsDao(Integer clientId);

	public List<ResultModelForPlacement> getEducationResultModelDao();

	public void updateStudentEducationalDtlsDao(EducationDetails educatinalDtl);

	public List<ResultModelForPlacement> getResultModelForPlacementDao();

	public List<Semester> getSemesterInfoForPlacementDao();

	public List<PlacementYears> getYearForPlacementDao();

	// .............................TPO...........................
	public void saveAssingedCoordinator(Integer client_id, Integer co_type, Integer dept_id);

	public List<AssignedCoordinators> getcoordinatorlist(Integer client_id);

	public void deleteAssigncoordinator(Integer client_id, Integer cid);

	public List<AssignedCoordinators> getdepartmentTpoList();

	public void add_Recruitment_Info(RecruitmentInfo recruitmentInfo, String[] selectionRound,
			RecruitmentInfoDTO recruitmentInfoDTO, String[] typeindustry, String[] dept);

	public List<RecruitmentInfo> getRecuitmentcmpnylogo();

	public List<RecruitmentInfoDTO> getpastcompnydtl();

	public RecruitmentInfoDTO getcompnybyid(Integer id);

	public List<Student> getstudentlistbydept(Integer id);

	public List<EducationDetails> getfiltereducationalDTlofStudent(Integer year, Integer[] depts, double tencriteria,
			double deplocriteria, double degreecriteria, Integer backlog);

	public List<ComClientNameDTO> getStudentListForTPO(List<Integer> clientIdList);

	public List<SubjectResultDataModel> getResultBySubjectDao(Map<String, String> parameters);

	public List<AssignedCoordinators> getcoordinatorlistDao(Integer staff_id, String dt, String option);

	public List<ComStaffSubject> getAcademicSubjectByStaffDao(Integer staff_id, String dt, String option);

	public List<SubjectResultDataModel> getAttendanceBySubjectDao(Map<String, String> parameters);

	public List<PortFolioInfo> getCoordinatorInfoDao(Integer staff_id, String dt, String option);

	public ComClientNameDTO getStudentDataOnStaffTable_UsingClientId(Integer id);

	public RecruitmentInfo getDriveDetailsUsingId(int driveId);

	public List<EducationDetails> getEducationDetailsUsingID(int clientId);

	public boolean applayForDrive(int clientId, int driveId);

	public boolean checkapplayForRe_Drive(int clientId, int driveId);

	public List<ApplyedStudentForCompany> getapplyedStudentlist(Integer reInfoId, Integer id);

	public List<ApplyedStudentForCompany> getcountround(Integer id);

	public Object SaveAelectedStudNextRound(Integer[] studId, Integer roundId, Integer reInfoId);

	public List<OnCampusPlaceStudListDTO> getplaceStudentList();

	public boolean upadteStudentAcadmicYear(int clientId, int year, String acYear);

	public List<ComClientNameDTO> getPassoutYearDownStudent(int deptID, int yearID, String aCyear);

	public List<ComVideoURL> getVideoURL_List(int instCode);

	public List<ValueAddedProgram> getValueAddedProgamDao(Integer clientId);// done

	public List<GfmInformation> getMentorListByStudentDao(Integer clientId); // done

	public List<StudentAttendanceDto> getStudentCurrentAttendanceDao(Integer clientId);// done

	public List<ExtraActivities> getStudentExtraActivitiesDao(Integer clientId);// done

	public List<ValueAddedProgram> getValueAddedProgamDao(Integer clientId, Integer option);// done

	public ExtraActivities saveExtraActivityDao(ExtraActivities extraActivities);

	public RecruitmentInfo saveExtraActivityDao(Integer[] studId, Integer reInfoId);

	public PlacementDriveStatus getDriveSatus(Integer studId, Integer driveID);

	public Object saveOfferAcceptedStudent(Integer studId, Integer driveID);

	public List<ApplyedStudentForCompany> getplacementroundDetails(Integer clientId);

	public List<CompanySelectionRounds> ggetroundlistofdrive(Integer id);

	public List<Student> getstudentdata();

	public List<Staff> getstaffdata();

	public Groups savegroup(Mentor mentor, Integer[] students);

	public String updateStudentform(AdminForm student);

	public String deletementeestudent(Integer[] member);

	public StudentForm getstudinfodtltoparent(Integer studId);

	public List<ExtraActivities> getfutureinterestInfo(Integer cid, Integer type);

	public ExtraActivities savefuturelist(ExtraActivities extraActivities);

	public Map<String, List<TotalStudentCount>> getsudenttotalcount(Integer deptid);

	public CollegeCountDTO gettotolcollegecount();

	public List<TotalStudentCount> gettotaldepartmentcount();

	public List<Student> getfilterstudentlist(Integer year, Integer depts, Integer div);

	public OffcampusPlaceStud Saveoffcampusstudlist(OffcampusPlaceStud data);

	public List<OffcampusPlaceStud> getoffcampusstudlist();

	public List<StudentFeeDtl> getfeedepartmentdtl();

	public CollegeTotalFee getcollegetotalfee();

	public Map<String, List<StudentFeeDtl>> gettotalstudentfee(Integer id);

	public List<ExtraActivityDTO> getstudentcarrierlist(Integer eid, Integer type);

	public Parentcallrecord saveparentcall(Integer studid, Date date, String remark);

	public List<Mentor> getmentorfprofile();

	public String getgfmrstudemark(Studentskillsdto gfmremark);

	public List<RemarkOption> getliststudentremarks(Integer id, Integer mentorid);

	public HashMap<String, List<String>> getskilllist(Integer mid, Integer studentid);

	public String getstudentremark(StudentRemark studentremark);

	public List<StudentRemarkOption> getcheckremarkliststud(Integer id, Integer mentorid);

	public List<StudentRemarkOption> getstudentremarkview(Integer id, Integer mentorid);

	public List<StudentRemarkOption> getstudentremarklist(Integer id);

	public String getuserregistration(NewUserDTO user);

	public String getuseremailidcheck(String emailid);

	public List<ResultInstituteList> getpdfresultlist();

	public List<Resultsemester> getresultsemester();

	public List<ResultInstituteList> getInstituteResult();

	public String getsuperadminreport(Integer aid, Integer bid, Integer cid, Integer instid);

	public List<ResultInstituteList> getcommoninstitudereport();

	public String getcommonsuperadmindeptreport(Integer aid, Integer bid, String cid);

}