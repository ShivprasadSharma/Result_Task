package com.zertones.dao.common;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zertones.dao.BaseDAOImpl;
import com.zertones.dao.UserDetailsDAO;
import com.zertones.model.ComClientName;
import com.zertones.model.DataTransferObjectModel.ComClientNameDTO;
import com.zertones.model.DataTransferObjectModel.GrievanceDTO;
import com.zertones.model.common.GrievanceAssign_MemberList;
import com.zertones.model.common.Grievance_Committee_Members;
import com.zertones.model.common.Grievance_List;
import com.zertones.model.sims.Staff;

@Repository
public class GrievanceDaoImpl extends BaseDAOImpl implements GrievanceDAO
{

	private static final Logger	logger	= LoggerFactory.getLogger(GrievanceDaoImpl.class);

	@Autowired
	protected SessionFactory	sessionFactory;

	@Autowired
	protected UserDetailsDAO	userDao;

	@Override
	@Transactional
	public void addGCMembers(int[] clientId)
	{
		if (clientId != null)
		{
			Grievance_Committee_Members grievance_Committee_Members;
			Session session = this.sessionFactory.getCurrentSession();
			for (int id : clientId)
			{
				grievance_Committee_Members = new Grievance_Committee_Members();
				ComClientName clientName = session.get(ComClientName.class, id);
				clientName.setSalutation(1);
				grievance_Committee_Members.setComClientName(clientName);
				save(grievance_Committee_Members);
			}
		}

	}

	@Override
	@Transactional
	public List<Grievance_Committee_Members> getGC_Members_List()
	{

		Criteria criteria = getCriteriaForSelect(Grievance_Committee_Members.class);
		return criteria.list();

		// return get("from Grievance_Committee_Members");
	}

	@Override
	@Transactional
	public List<Grievance_List> getgrievanceList()
	{
		Criteria criteria = getCriteriaForSelect(Grievance_List.class);
		List<Grievance_List> list = criteria.list();

		long diffDays;
		long diff;
		if (list != null)
		{
			for (Grievance_List grievance_List : list)
			{

				try
				{
					if (grievance_List.getGrievance_Status())
					{

						Date cureentDate = new Date();
						Date assignDate = grievance_List.getCreatedDate();
						diff = cureentDate.getTime() - assignDate.getTime();
						diffDays = diff / (24 * 60 * 60 * 1000);
						if (diffDays == 0)
						{
							grievance_List.setProcess_Status(1);
						} else if (diffDays <= 3)
						{
							grievance_List.setProcess_Status(2);
						} else if (diffDays > 3)
						{
							grievance_List.setProcess_Status(3);
						}
					}
				} catch (Exception e)
				{
					System.out.println(e);
				}
				String desc = grievance_List.getDescription().toString();
			}

		}

		return list;
	}

	@Override
	@Transactional
	public void grievance_Assign_to_Members(int[] clientId, Integer girvanceId)
	{

		Session session = this.sessionFactory.getCurrentSession();
		GrievanceAssign_MemberList list;
		for (int id : clientId)
		{
			list = new GrievanceAssign_MemberList();
			list.setGrievanceId(girvanceId);
			ComClientName clientName = session.get(ComClientName.class, id);
			list.setComClientName(clientName);
			save(list);

		}
		Session session1 = this.sessionFactory.getCurrentSession();

		String update = "UPDATE GRIEVANCE_LIST set ASSIGN_TO_MEMBERS= " + true + " WHERE GRIEVANCE_ID=" + girvanceId;
		SQLQuery sqlQuery = session1.createSQLQuery(update);
		sqlQuery.executeUpdate();

	}

	@Override
	@Transactional
	public List<GrievanceAssign_MemberList> getGrievance_AssignMembers(Integer girvanceId)
	{
		Criteria c = getCriteriaForSelect(GrievanceAssign_MemberList.class);
		c.createAlias("comClientName", "comClientName");
		c.setProjection(Projections.projectionList().add(Property.forName("comClientName.id"), "id")
				.add(Property.forName("comClientName.firstName"), "firstName")
				.add(Property.forName("comClientName.lastName"), "lastName"));
		c.add(Restrictions.eq("grievanceId", girvanceId));
		c.setResultTransformer(Transformers.aliasToBean(ComClientName.class));
		return c.list();
	}

	@Override
	@Transactional
	public Grievance_List addGrievance_Replay(String replayText, Integer girvanceId, Integer Id)
	{
		Session session = this.sessionFactory.getCurrentSession();
		DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Date date = new Date();
		String strDate = dateFormat.format(date);
		Grievance_List grievance_List = session.get(Grievance_List.class, girvanceId);
		if (Id == 0)
		{
			grievance_List.setReplay_Date(strDate);
			grievance_List.setReplay(replayText);
			grievance_List.setGrievance_Status(false);
		} else
		{
			grievance_List.setReOpen(false);
			grievance_List.setReOpen_replay(replayText);
			grievance_List.setReopen_replay_Date(strDate);
		}

		return (Grievance_List) save(grievance_List);
	}

	@Override
	@Transactional
	public List<GrievanceDTO> getGirvanceList(Integer id)
	{
		Criteria criteria = getCriteriaForSelect(Grievance_List.class);
		criteria.setProjection(Projections.projectionList().add(Property.forName("id"), "id")
				.add(Property.forName("type"), "type").add(Property.forName("title"), "title")
				.add(Property.forName("description"), "description")
				.add(Property.forName("grievance_Date"), "grievance_Date")
				.add(Property.forName("grievance_Status"), "grievance_Status")
				.add(Property.forName("process_Status"), "process_Status")
				.add(Property.forName("assignToMembers"), "assignToMembers").add(Property.forName("replay"), "replay")
				.add(Property.forName("replay_Date"), "replay_Date")
				.add(Property.forName("oneTime_reOpen"), "oneTime_reOpen")
				.add(Property.forName("reOpen_replay"), "reOpen_replay").add(Property.forName("reOpen"), "reOpen")
				.add(Property.forName("reopen_replay_Date"), "reopen_replay_Date")
				.add(Property.forName("clientID"), "clientID"));
		criteria.add(Restrictions.eq("clientID", id));
		criteria.addOrder(Order.desc("id"));
		criteria.setResultTransformer(Transformers.aliasToBean(GrievanceDTO.class));
		List<GrievanceDTO> grievance_Lists = criteria.list();
		System.out.println("grievance_Listslist " + grievance_Lists);
		return grievance_Lists;
	}

	@Override
	@Transactional
	public Boolean addGrievance(Grievance_List grievance_List)
	{
		try
		{
			DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
			Date date = new Date();
			grievance_List.setGrievance_Date(dateFormat.format(date));
			/*
			 * String description =
			 * grievance_List.getDescription().replaceAll("\\s+$", "");
			 */

			if (save(grievance_List) != null)
			{
				return true;
			} else
			{
				return false;
			}
		} catch (Exception e)
		{
			return false;
		}
	}

	@Override
	@Transactional
	public Boolean reopenGrievance(Boolean reopenRP, Integer griId)
	{
		// TODO Auto-generated method stub

		try
		{
			Session session = this.sessionFactory.getCurrentSession();
			Grievance_List grievance_List = session.get(Grievance_List.class, griId);
			if (reopenRP)
			{
				grievance_List.setReOpen(false);
			} else
			{
				grievance_List.setOneTime_reOpen(true);
			}
			if (save(grievance_List) != null)
			{
				return true;
			} else
			{
				return false;
			}
		} catch (Exception e)
		{
			return false;
		}

	}

	@Override
	@Transactional
	public Boolean grievanceDownload(Date startDate, Date endDate, HttpServletResponse responses)
	{

		Session session = this.sessionFactory.getCurrentSession();
		Boolean status = false;
		Property dates = Property.forName("createdDate");
		Criteria criteria2 = getCriteriaForSelect(Grievance_List.class)
				.add(Restrictions.disjunction().add(Restrictions.and(dates.gt(startDate), dates.lt(endDate))));
		List<Grievance_List> list1 = criteria2.list();
		/*
		 * RequestAttributes requestAttributes =
		 * RequestContextHolder.getRequestAttributes(); HttpServletResponse
		 * response = ((ServletRequestAttributes)
		 * requestAttributes).getResponse();
		 */
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
		cell.setCellValue("Grievance List");

		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue("sr.No");
		cell = row.createCell(1);
		cell.setCellValue("Title");
		cell = row.createCell(2);
		cell.setCellValue("Type");
		cell = row.createCell(3);
		cell.setCellValue("description");
		cell = row.createCell(4);
		cell.setCellValue("Grievance Date");
		cell = row.createCell(5);
		cell.setCellValue("Replay");
		cell = row.createCell(6);
		cell.setCellValue("Replay Date");
		cell = row.createCell(7);
		cell.setCellValue("ReOpen Replay");
		cell = row.createCell(8);
		cell.setCellValue("Reopen replay Date");
		cell = row.createCell(9);
		cell.setCellValue("Grievance Status");
		cell = row.createCell(2);
		int num = 3;
		int srno = 1;

		if (list1.size() != 0)
		{
			for (Grievance_List grievance_List : list1)
			{
				row = sheet.createRow(num);
				cell = row.createCell(0);
				cell.setCellValue(srno);
				cell = row.createCell(1);
				cell.setCellValue(grievance_List.getTitle());
				cell = row.createCell(2);
				cell.setCellValue(grievance_List.getType());
				cell = row.createCell(3);
				cell.setCellValue(grievance_List.getDescription());
				cell = row.createCell(4);
				cell.setCellValue(grievance_List.getGrievance_Date());
				cell = row.createCell(5);
				cell.setCellValue(grievance_List.getReplay());
				cell = row.createCell(6);
				cell.setCellValue(grievance_List.getReplay_Date());
				cell = row.createCell(7);
				cell.setCellValue(grievance_List.getReOpen_replay());
				cell = row.createCell(8);
				cell.setCellValue(grievance_List.getReopen_replay_Date());
				cell = row.createCell(9);
				String Gristatus = "";
				if (grievance_List.getReOpen() == true && grievance_List.getOneTime_reOpen() == true)
				{
					Gristatus = "Reopened";
				} else if (grievance_List.getReOpen() == false)
				{
					Gristatus = "Disposed";
				} else if (grievance_List.getGrievance_Status() == false && grievance_List.getReOpen() == true)
				{
					Gristatus = "Replayed";
				} else
				{
					Gristatus = "Pending";
				}

				cell.setCellValue(Gristatus);
				num++;
				srno++;
			}
			// write it as an excel attachment
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			try
			{
				wb.write(outByteStream);
				byte[] outArray = outByteStream.toByteArray();
				responses.setContentType("application/ms-excel");
				responses.setContentLength(outArray.length);
				responses.setHeader("Expires:", "0"); // eliminates browser
														// caching
				responses.setHeader("Content-Disposition", "attachment; filename=Grievance_List.xls");
				OutputStream outStream = responses.getOutputStream();
				outStream.write(outArray);
				outStream.flush();
				outStream.close();
				status = true;

			} catch (Exception e)
			{
				System.out.println("excepotion......" + e);
			}

		}

		return status;
	}

	@Override
	@Transactional
	public List<ComClientNameDTO> getGrievanceMembers()
	{
		Criteria criteria = getCriteriaForSelect(Staff.class);
		criteria.createAlias("comClientName", "ClientName");
		criteria.setProjection(Projections.projectionList().add(Projections.property("ClientName.id"), "id")
				.add(Projections.property("ClientName.firstName"), "firstName")
				.add(Projections.property("ClientName.middleName"), "middleName")
				.add(Projections.property("ClientName.lastName"), "lastName"));
		criteria.setResultTransformer(Transformers.aliasToBean(ComClientNameDTO.class));
		List<ComClientNameDTO> stafflist = criteria.list();

		Criteria criteria1 = getCriteriaForSelect(Grievance_Committee_Members.class);
		List<Grievance_Committee_Members> list = criteria1.list();

		List<Integer> idlist = new ArrayList<>();
		for (Grievance_Committee_Members grievance_Committee_Members : list)
		{
			idlist.add(grievance_Committee_Members.getComClientName().getId());
		}

		List<ComClientNameDTO> list2 = new ArrayList<>();
		for (ComClientNameDTO comClientNameDTO : stafflist)
		{
			if (!idlist.contains(comClientNameDTO.getId()))
			{
				list2.add(comClientNameDTO);
			}
		}

		return list2;
	}

	@Override
	@Transactional
	public List<Grievance_Committee_Members> getGC_Members_ListDao(Integer staff_id)
	{
		Criteria cr = getCriteriaForSelect(Grievance_Committee_Members.class);
		cr.setProjection(Projections.property("comClientName.id"));
		cr.add(Restrictions.eq("comClientName.id", staff_id));
		return cr.list();
	}

}
