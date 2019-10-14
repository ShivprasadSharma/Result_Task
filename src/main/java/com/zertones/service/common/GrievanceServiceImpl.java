package com.zertones.service.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zertones.dao.common.GrievanceDAO;
import com.zertones.model.DataTransferObjectModel.ComClientNameDTO;
import com.zertones.model.DataTransferObjectModel.GrievanceDTO;
import com.zertones.model.common.GrievanceAssign_MemberList;
import com.zertones.model.common.Grievance_Committee_Members;
import com.zertones.model.common.Grievance_List;

@Service("grievanceService")
public class GrievanceServiceImpl implements GrievanceService
{

	@Autowired
	private GrievanceDAO grievanceDAO;

	@Override
	public List<Grievance_Committee_Members> getGC_Members_List()
	{
		// TODO Auto-generated method stub

		return grievanceDAO.getGC_Members_List();
	}

	@Override
	public void addGCMembers(int[] clientId)
	{
		// TODO Auto-generated method stub
		grievanceDAO.addGCMembers(clientId);

	}

	@Override
	public List<Grievance_List> getgrievanceList()
	{
		// TODO Auto-generated method stub
		return grievanceDAO.getgrievanceList();
	}

	@Override
	public void grievance_Assign_to_Members(int[] clientId, Integer girvanceId)
	{
		// TODO Auto-generated method stub
		grievanceDAO.grievance_Assign_to_Members(clientId, girvanceId);
	}

	@Override
	public List<GrievanceAssign_MemberList> getGrievance_AssignMembers(Integer girvanceId)
	{
		// TODO Auto-generated method stub
		return grievanceDAO.getGrievance_AssignMembers(girvanceId);
	}

	@Override
	public Grievance_List addGrievance_Replay(String replayText, Integer girvanceId, Integer Id)
	{
		// TODO Auto-generated method stub
		return grievanceDAO.addGrievance_Replay(replayText, girvanceId, Id);
	}

	@Override
	public List<GrievanceDTO> getGirvanceList(Integer id)
	{
		// TODO Auto-generated method stub
		return grievanceDAO.getGirvanceList(id);
	}

	@Override
	public Boolean addGrievance(Grievance_List grievance_List)
	{
		// TODO Auto-generated method stub
		return grievanceDAO.addGrievance(grievance_List);
	}

	@Override
	public Boolean reopenGrievance(Boolean reopenRP, Integer griId)
	{
		// TODO Auto-generated method stub
		return grievanceDAO.reopenGrievance(reopenRP, griId);
	}

	@Override
	public Boolean grievanceDownload(Date startDate, Date endDate, HttpServletResponse response)
	{
		// TODO Auto-generated method stub
		return grievanceDAO.grievanceDownload(startDate, endDate, response);
	}

	@Override
	public List<ComClientNameDTO> getGrievanceMembers()
	{
		// TODO Auto-generated method stub
		return grievanceDAO.getGrievanceMembers();
	}

	@Override
	public List<Grievance_Committee_Members> getGC_Members_List(Integer staff_id)
	{
		// TODO Auto-generated method stub
		return grievanceDAO.getGC_Members_ListDao(staff_id);
	}

}
