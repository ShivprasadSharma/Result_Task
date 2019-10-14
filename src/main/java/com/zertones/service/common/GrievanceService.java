package com.zertones.service.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.zertones.model.DataTransferObjectModel.ComClientNameDTO;
import com.zertones.model.DataTransferObjectModel.GrievanceDTO;
import com.zertones.model.common.GrievanceAssign_MemberList;
import com.zertones.model.common.Grievance_Committee_Members;
import com.zertones.model.common.Grievance_List;

public interface GrievanceService
{

	public void addGCMembers(int[] clientId);

	public List<Grievance_Committee_Members> getGC_Members_List();

	public List<Grievance_List> getgrievanceList();

	public void grievance_Assign_to_Members(int[] clientId, Integer girvanceId);

	public List<GrievanceAssign_MemberList> getGrievance_AssignMembers(Integer girvanceId);

	public Grievance_List addGrievance_Replay(String replayText, Integer girvanceId, Integer Id);

	public List<GrievanceDTO> getGirvanceList(Integer id);

	public Boolean addGrievance(Grievance_List grievance_List);

	public Boolean reopenGrievance(Boolean reopenRP, Integer griId);

	public Boolean grievanceDownload(Date startDate, Date endDate, HttpServletResponse response);

	public List<ComClientNameDTO> getGrievanceMembers();

	public List<Grievance_Committee_Members> getGC_Members_List(Integer staff_id);

}
