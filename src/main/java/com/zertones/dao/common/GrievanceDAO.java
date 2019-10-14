package com.zertones.dao.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.zertones.model.DataTransferObjectModel.ComClientNameDTO;
import com.zertones.model.DataTransferObjectModel.GrievanceDTO;
import com.zertones.model.common.GrievanceAssign_MemberList;
import com.zertones.model.common.Grievance_Committee_Members;
import com.zertones.model.common.Grievance_List;

public interface GrievanceDAO
{

	List<Grievance_Committee_Members> getGC_Members_List();

	void addGCMembers(int[] clientId);

	List<Grievance_List> getgrievanceList();

	void grievance_Assign_to_Members(int[] clientId, Integer girvanceId);

	List<GrievanceAssign_MemberList> getGrievance_AssignMembers(Integer girvanceId);

	Grievance_List addGrievance_Replay(String replayText, Integer girvanceId, Integer id);

	List<GrievanceDTO> getGirvanceList(Integer id);

	Boolean addGrievance(Grievance_List grievance_List);

	Boolean reopenGrievance(Boolean reopenRP, Integer griId);

	Boolean grievanceDownload(Date startDate, Date endDate, HttpServletResponse response);

	List<ComClientNameDTO> getGrievanceMembers();

	List<Grievance_Committee_Members> getGC_Members_ListDao(Integer staff_id);

}
