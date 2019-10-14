package com.zertones.dao.common;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
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
import com.zertones.model.DataTransferObjectModel.GroupPollBeanDTO;
import com.zertones.model.DataTransferObjectModel.GroupPollOptionPercentageDTO;
import com.zertones.model.DataTransferObjectModel.Group_PollDTO;
import com.zertones.model.DataTransferObjectModel.PollBeanDTO;
import com.zertones.model.DataTransferObjectModel.PollOptionPercentageDTO;
import com.zertones.model.DataTransferObjectModel.PollsDTO;
import com.zertones.model.common.FeedbackData;
import com.zertones.model.common.FeedbackProfile;
import com.zertones.model.common.FeedbackQue;
import com.zertones.model.common.FeedbackStudVote;
import com.zertones.model.common.FeedbackVote;
import com.zertones.model.common.Group_Poll;
import com.zertones.model.common.Group_PollAnswer;
import com.zertones.model.common.Group_PollOptions;
import com.zertones.model.common.PollAnswer;
import com.zertones.model.common.PollOptions;
import com.zertones.model.common.Polls;
import com.zertones.model.master.AcademicSubject;
import com.zertones.model.master.ComStaffSubject;
import com.zertones.model.sims.Staff;
import com.zertones.model.sims.Student;
import com.zertones.service.Constants;

@Repository
@Transactional
public class FeedbackDAOImpl extends BaseDAOImpl implements FeedbackDAO
{
	private static final Logger	logger	= LoggerFactory.getLogger(CommonDAOImpl.class);

	@Autowired
	protected SessionFactory	sessionFactory;

	@Autowired
	protected UserDetailsDAO	userDao;

	@Override
	@Transactional
	public List<FeedbackData> getfeedbsckdata(Integer studeId)
	{
		String StartDate = "06";
		String EndDate = "12";
		System.out.println("..........." + studeId);
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Student.class, "Student");
		// c.createAlias("stud.comclientName", "comClientName");
		c.add(Restrictions.eq("Student.comClientName.id", studeId));
		Student student = (Student) c.uniqueResult();

		/*
		 * Criteria c1 = session.createCriteria(FeedbackStudVote.class,
		 * "FeedbackStudVote"); c1.add(Restrictions.eq("FeedbackStudVote.stud_id",
		 * student)); FeedbackStudVote student1 = (FeedbackStudVote) c1.uniqueResult();
		 * System.out.println("mmmmmmmmm...mmmmmmmmmm...mmmmmmmmm" + student1);
		 * List<FeedbackStudVote> acadmicsub = c.uniqueResult();;
		 */
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");
		LocalDateTime now = LocalDateTime.now();
		String strDate = dtf.format(now);
		String sem_id = "0";

		switch (student.getYear())
		{
		case 1:
			if (strDate.compareTo(StartDate) > 0 && strDate.compareTo(EndDate) < 0)
			{
				sem_id = "1";
			} else
			{
				sem_id = "2";
			}
			break;
		case 2:
			if (strDate.compareTo(StartDate) > 0 && strDate.compareTo(EndDate) < 0)
			{
				sem_id = "3";
			} else
			{
				sem_id = "4";
			}
			break;
		case 3:
			if (strDate.compareTo(StartDate) > 0 && strDate.compareTo(EndDate) < 0)
			{
				sem_id = "5";
			} else
			{
				sem_id = "6";
			}
			break;
		case 4:
			if (strDate.compareTo(StartDate) > 0 && strDate.compareTo(EndDate) < 0)
			{
				sem_id = "7";
			} else
			{
				sem_id = "8";
			}
			break;
		}

		Criteria cccc = session.createCriteria(FeedbackQue.class, "FeedbackQue");
		cccc.add(Restrictions.eq("instituteId", student.getInstituteId()));
		c.createAlias("FeedbackQue.questions", "questions");
		List<FeedbackQue> que = cccc.list();

		System.out.println("<<<>>>" + student.getBranch() + "..." + sem_id);
		Criteria cc = session.createCriteria(AcademicSubject.class, "AcademicSubject");
		// c.createAlias("AcademicSubject.sub_id", "sub_id");
		cc.add(Restrictions.eq("AcademicSubject.department.dep_id", student.getBranch()));
		cc.add(Restrictions.eq("AcademicSubject.semister", sem_id));
		List<AcademicSubject> acadmicsub = cc.list();

		System.out.println("//////// sub" + acadmicsub);
		List<FeedbackData> fdata = new ArrayList<>();
		FeedbackData feedbackData;
		FeedbackData feedbackData1 = new FeedbackData();
		for (AcademicSubject academicSubject : acadmicsub)
		{
			feedbackData = new FeedbackData();
			/*
			 * Criteria c1 = session.createCriteria(FeedbackStudVote.class,
			 * "FeedbackStudVote"); c1.add(Restrictions.eq("FeedbackStudVote.stud_id",
			 * studeId)); c1.add(Restrictions.eq("FeedbackStudVote.sub_id",
			 * academicSubject.getSub_id())); List<FeedbackStudVote> checklist = cc.list();
			 * System.out.println(".....+.....+....+" + checklist);
			 */

			Criteria ccc = getCriteriaForSelect(ComStaffSubject.class);
			// c.createAlias("comStaffSubject.comclientName.firstName", "firstName");
			ccc.add(Restrictions.eq("academicSubject.sub_id", academicSubject.getSub_id()));
			System.out.println("" + academicSubject.getSub_id());
			ComStaffSubject staffsub = (ComStaffSubject) ccc.uniqueResult();
			feedbackData.setSub_id(academicSubject.getSub_id());
			feedbackData.setSubject_name(academicSubject.getSubject_name());
			feedbackData.setStaff_firstName(staffsub.getClientName().getFirstName());
			feedbackData.setStaff_lastName(staffsub.getClientName().getLastName());
			feedbackData.setStaffclient_id(staffsub.getClientName().getId());
			feedbackData.setDep_name(academicSubject.getDepartment().getDep_name());
			feedbackData.setDep_id(academicSubject.getDepartment().getDep_id());
			feedbackData.setSemister(academicSubject.getSemister());
			// feedbackData.setList(que);
			fdata.add(feedbackData);

			System.out.println("//////////////////" + feedbackData);
		}
		feedbackData1.setList(que);
		fdata.add(feedbackData1);
		int i = fdata.size();
		System.out.println("final data" + fdata);
		return fdata;

	}

	@Override
	@Transactional
	public Boolean SaveFeedbackData(FeedbackData feedbackvote)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(FeedbackVote.class, "FeedbackVote");
		c.add(Restrictions.eq("comClientName.id", feedbackvote.getStaffclient_id()));
		c.add(Restrictions.eq("sub_id", feedbackvote.getSub_id()));
		FeedbackVote fv = (FeedbackVote) c.uniqueResult();
		if (c.uniqueResult() == null)
		{
			FeedbackVote f = new FeedbackVote();
			f = new FeedbackVote();
			f.setSub_id(feedbackvote.getSub_id());
			f.setSubject_name(feedbackvote.getSubject_name());
			f.setDep_id(feedbackvote.getDep_id());
			f.setDep_name(feedbackvote.getDep_name());
			f.setSemister(feedbackvote.getSemister());
			ComClientName clientName = session.get(ComClientName.class, feedbackvote.getStaffclient_id());
			f.setComClientName(clientName);

			FeedbackVote bean = (FeedbackVote) save(f);
			List<FeedbackQue> list = feedbackvote.getList();
			FeedbackStudVote feedbackStudVote = null;
			for (FeedbackQue feedbackQue : list)
			{
				System.out.println(".....que no=" + feedbackQue.getQue_no());
				feedbackStudVote = new FeedbackStudVote();
				feedbackStudVote.setQue_id(feedbackQue.getQue_no());
				feedbackStudVote.setVote(feedbackQue.getVote());
				feedbackStudVote.setStud_id((int) feedbackvote.getStud_cid());
				feedbackStudVote.setSub_id(feedbackvote.getSub_id());
				feedbackStudVote.setFeedbackVote(bean);
				save(feedbackStudVote);
			}
		} else
		{
			FeedbackStudVote feedbackStudVote = null;
			List<FeedbackQue> list = feedbackvote.getList();
			for (FeedbackQue feedbackQue : list)
			{
				System.out.println(".....que no=" + feedbackQue.getQue_no());
				feedbackStudVote = new FeedbackStudVote();
				feedbackStudVote.setQue_id(feedbackQue.getQue_no());
				feedbackStudVote.setVote(feedbackQue.getVote());
				feedbackStudVote.setStud_id((int) feedbackvote.getStud_cid());
				feedbackStudVote.setSub_id(feedbackvote.getSub_id());
				feedbackStudVote.setFeedbackVote(fv);
				save(feedbackStudVote);
			}
		}
		return true;
	}

	@Override
	@Transactional
	public List<FeedbackVote> getfeedbacklist(Integer staff_id, Integer yrid)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Staff.class, "Staff");
		c.add(Restrictions.eq("Staff.staffId", staff_id));
		Staff staff = (Staff) c.uniqueResult();
		staff.getDepartment();
		String sem1 = "null";
		String sem2 = "null";
		if (yrid == 1)
		{
			sem1 = "1";
			sem2 = "2";
		}
		if (yrid == 2)
		{
			sem1 = "3";
			sem2 = "4";
		}
		if (yrid == 3)
		{
			sem1 = "5";
			sem2 = "6";
		}
		if (yrid == 4)
		{
			sem1 = "7";
			sem2 = "8";
		}

		Criteria cc = session.createCriteria(FeedbackVote.class, "FeedbackVote");
		cc.add(Restrictions.eq("FeedbackVote.dep_id", staff.getDepartment()));
		cc.add(Restrictions.eq("FeedbackVote.semister", sem2));
		List<FeedbackVote> acadmicsub = cc.list();
		return acadmicsub;

	}

	@Override
	@Transactional
	public FeedbackProfile getfeedbackdetail(Integer id, Integer sem)
	{

		System.out.println("come in feedback dao class");
		String str = String.valueOf(sem);
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = getCriteriaForSelect(FeedbackVote.class);
		c.add(Restrictions.eq("comClientName.id", id));
		c.add(Restrictions.eq("semister", str));
		FeedbackVote fv = (FeedbackVote) c.uniqueResult();

		if (c.uniqueResult() != null)
		{
			FeedbackProfile feedbackprof = new FeedbackProfile();
			feedbackprof.setDepartmentname(fv.getDep_name());
			feedbackprof.setF_name(fv.getComClientName().getFirstName());
			feedbackprof.setL_name(fv.getComClientName().getLastName());
			feedbackprof.setSubname(fv.getSubject_name());
			feedbackprof.setSemester(fv.getSemister());

			Criteria c1 = getCriteriaForSelect(FeedbackStudVote.class);
			c1.add(Restrictions.eq("que_id", 1));
			c1.add(Restrictions.eq("feedbackVote.f_id", fv.getF_id()));
			c1.setProjection(Projections.avg("vote"));
			Double avg1 = (Double) c1.uniqueResult();
			feedbackprof.setAvg1(avg1);
			Criteria c2 = getCriteriaForSelect(FeedbackStudVote.class);
			c2.add(Restrictions.eq("que_id", 2));
			c2.add(Restrictions.eq("feedbackVote.f_id", fv.getF_id()));
			c2.setProjection(Projections.avg("vote"));
			Double avg2 = (Double) c2.uniqueResult();
			feedbackprof.setAvg2(avg2);
			Criteria c3 = getCriteriaForSelect(FeedbackStudVote.class);
			c3.add(Restrictions.eq("que_id", 4));
			c3.add(Restrictions.eq("feedbackVote.f_id", fv.getF_id()));
			c3.setProjection(Projections.avg("vote"));
			Double avg3 = (Double) c3.uniqueResult();
			feedbackprof.setAvg3(avg3);
			Criteria c4 = getCriteriaForSelect(FeedbackStudVote.class);
			c4.add(Restrictions.eq("que_id", 4));
			c4.add(Restrictions.eq("feedbackVote.f_id", fv.getF_id()));
			c4.setProjection(Projections.avg("vote"));
			Double avg4 = (Double) c4.uniqueResult();
			feedbackprof.setAvg4(avg4);
			Criteria c5 = getCriteriaForSelect(FeedbackStudVote.class);
			c5.add(Restrictions.eq("que_id", 5));
			c5.add(Restrictions.eq("feedbackVote.f_id", fv.getF_id()));
			c5.setProjection(Projections.avg("vote"));
			Double avg5 = (Double) c5.uniqueResult();
			feedbackprof.setAvg5(avg5);
			Criteria c6 = getCriteriaForSelect(FeedbackStudVote.class);
			c6.add(Restrictions.eq("que_id", 6));
			c6.add(Restrictions.eq("feedbackVote.f_id", fv.getF_id()));
			c6.setProjection(Projections.avg("vote"));
			Double avg6 = (Double) c6.uniqueResult();
			feedbackprof.setAvg6(avg6);
			Criteria c7 = getCriteriaForSelect(FeedbackStudVote.class);
			c7.add(Restrictions.eq("que_id", 7));
			c7.add(Restrictions.eq("feedbackVote.f_id", fv.getF_id()));
			c7.setProjection(Projections.avg("vote"));
			Double avg7 = (Double) c7.uniqueResult();
			feedbackprof.setAvg7(avg7);
			Criteria c8 = getCriteriaForSelect(FeedbackStudVote.class);
			c8.add(Restrictions.eq("que_id", 8));
			c8.add(Restrictions.eq("feedbackVote.f_id", fv.getF_id()));
			c8.setProjection(Projections.avg("vote"));
			Double avg8 = (Double) c8.uniqueResult();
			feedbackprof.setAvg8(avg8);
			Criteria c9 = getCriteriaForSelect(FeedbackStudVote.class);
			c9.add(Restrictions.eq("que_id", 9));
			c9.add(Restrictions.eq("feedbackVote.f_id", fv.getF_id()));
			c9.setProjection(Projections.avg("vote"));
			Double avg9 = (Double) c9.uniqueResult();
			feedbackprof.setAvg9(avg9);
			Criteria c10 = getCriteriaForSelect(FeedbackStudVote.class);
			c10.add(Restrictions.eq("que_id", 10));
			c10.add(Restrictions.eq("feedbackVote.f_id", fv.getF_id()));
			c10.setProjection(Projections.avg("vote"));
			Double avg10 = (Double) c10.uniqueResult();
			feedbackprof.setAvg10(avg10);
			Double tavg = (avg1 + avg2 + avg3 + avg4 + avg5 + avg6 + avg7 + avg8 + avg9 + avg10) / 10;
			feedbackprof.setTotal_avg_Rate(tavg);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
			LocalDateTime now = LocalDateTime.now();
			String AcademicDate = dtf.format(now);
			feedbackprof.setAcadmic_Year(AcademicDate);
			Criteria cc = getCriteriaForSelect(FeedbackQue.class);
			List<FeedbackQue> que = cc.list();
			for (FeedbackQue feedbackQue : que)
			{
				if (feedbackQue.getQue_no() == 1)
				{
					feedbackprof.setQ1(feedbackQue.getQuestions());
				}
				if (feedbackQue.getQue_no() == 2)
				{
					feedbackprof.setQ2(feedbackQue.getQuestions());
				}
				if (feedbackQue.getQue_no() == 3)
				{
					feedbackprof.setQ3(feedbackQue.getQuestions());
				}
				if (feedbackQue.getQue_no() == 4)
				{
					feedbackprof.setQ4(feedbackQue.getQuestions());
				}
				if (feedbackQue.getQue_no() == 5)
				{
					feedbackprof.setQ5(feedbackQue.getQuestions());
				}
				if (feedbackQue.getQue_no() == 6)
				{
					feedbackprof.setQ6(feedbackQue.getQuestions());
				}
				if (feedbackQue.getQue_no() == 7)
				{
					feedbackprof.setQ7(feedbackQue.getQuestions());
				}
				if (feedbackQue.getQue_no() == 8)
				{
					feedbackprof.setQ8(feedbackQue.getQuestions());
				}
				if (feedbackQue.getQue_no() == 9)
				{
					feedbackprof.setQ9(feedbackQue.getQuestions());
				}
				if (feedbackQue.getQue_no() == 10)
				{
					feedbackprof.setQ10(feedbackQue.getQuestions());
				}
			}

			return feedbackprof;

		} else
		{
			return null;
		}
	}

	@Override
	@Transactional
	public List<FeedbackQue> getque()
	{

		return getList("From FeedbackQue");
	}

	@Override
	@Transactional
	public Polls addPollQuestion(Polls polls, String[] option)
	{

		DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

		String todateStr = dateFormat.format(polls.getTo_Date());
		polls.setToDateString(todateStr);

		Polls polls2 = (Polls) save(polls);
		PollOptions pollOptions = null;
		for (String ops : option)
		{
			pollOptions = new PollOptions();
			pollOptions.setOption(ops);
			pollOptions.setPolls(polls2);
			save(pollOptions);
		}

		return polls2;
	}

	@Override
	@Transactional
	public List<Polls> getPoll_List()
	{
		Date date = new Date();
		Criteria criteria = getCriteriaForSelect(Polls.class);
		criteria.add(Restrictions.gt("to_Date", date));

		return criteria.list();
	}

	@Override
	@Transactional
	public List<Polls> getPast_PollList()
	{
		Date date = new Date();
		Criteria criteria = getCriteriaForSelect(Polls.class);
		criteria.add(Restrictions.le("to_Date", date));
		return criteria.list();
	}

	@Override
	@Transactional
	public boolean deletePoll(int id)
	{
		try
		{
			Session session = this.sessionFactory.getCurrentSession();
			Polls polls = session.get(Polls.class, id);
			polls.setRecordStatus(Constants.DELETED_RECORD_STATUS);
			save(polls);
			return true;
		} catch (Exception e)
		{
			return false;
		}

	}

	@Override
	@Transactional
	public List<PollsDTO> getPoll_ListRest(Integer id)
	{
		// TODO Auto-generated method stub

		Date date = new Date();
		Session session = this.sessionFactory.getCurrentSession();
		Criteria stud = getCriteriaForSelect(Student.class);
		stud.add(Restrictions.eq("comClientName.id", id));
		Student student = (Student) stud.uniqueResult();
		List<PollsDTO> list = new ArrayList<>();
		for (int i = 0; i < 4; i++)
		{

			Criteria criteria = getCriteriaForSelect(Polls.class);
			criteria.add(Restrictions.gt("to_Date", date));
			criteria.setProjection(Projections.projectionList().add(Property.forName("id"), "id")
					.add(Property.forName("question"), "question")
					.add(Property.forName("toDateString"), "toDateString"));

			if (i == 0)
			{
				criteria.add(Restrictions.eq("depID", 0));
				criteria.add(Restrictions.eq("year", 0));
				criteria.setResultTransformer(Transformers.aliasToBean(PollsDTO.class));
				list.addAll(criteria.list());
			} else if (i == 1)
			{
				criteria.add(Restrictions.eq("depID", student.getBranch()));
				criteria.add(Restrictions.eq("year", 0));
				criteria.setResultTransformer(Transformers.aliasToBean(PollsDTO.class));
				list.addAll(criteria.list());
			} else if (i == 2)
			{

				criteria.add(Restrictions.eq("depID", 0));
				criteria.add(Restrictions.eq("year", student.getYear()));
				criteria.setResultTransformer(Transformers.aliasToBean(PollsDTO.class));
				list.addAll(criteria.list());
			} else if (i == 3)
			{

				criteria.add(Restrictions.eq("depID", student.getBranch()));
				criteria.add(Restrictions.eq("year", student.getYear()));
				criteria.setResultTransformer(Transformers.aliasToBean(PollsDTO.class));
				list.addAll(criteria.list());
			}
		}

		for (PollsDTO pollsDTO : list)
		{
			Criteria polllist = getCriteriaForSelect(PollAnswer.class);
			polllist.add(Restrictions.eq("clientID", id));
			polllist.add(Restrictions.eq("pollId", pollsDTO.getId()));
			if (polllist.uniqueResult() == null)
			{
				pollsDTO.setPollStatus(false);
			} else
			{
				pollsDTO.setPollStatus(true);
			}

		}
		return list;
	}

	@Override
	@Transactional
	public List<PollOptions> getPoll_OptionList(Integer pollId)
	{
		Criteria criteria = getCriteriaForSelect(PollOptions.class);
		criteria.setProjection(Projections.projectionList().add(Property.forName("option_Id"), "option_Id")
				.add(Property.forName("option"), "option"));
		criteria.add(Restrictions.eq("polls.id", pollId));
		criteria.setResultTransformer(Transformers.aliasToBean(PollOptions.class));
		return criteria.list();

	}

	@Override
	@Transactional
	public Polls udatePoll(Polls polls)
	{
		// TODO Auto-generated method stub
		System.out.println("polls.." + polls);

		DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

		String todateStr = dateFormat.format(polls.getTo_Date());
		polls.setToDateString(todateStr);
		polls.setRecordStatus("A");
		return (Polls) save(polls);
	}

	@Override
	@Transactional
	public List<PollOptionPercentageDTO> getGraphResult(Integer poollId)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = getCriteriaForSelect(PollOptions.class);
		criteria.setProjection(Projections.projectionList().add(Property.forName("option_Id"), "option_Id")
				.add(Property.forName("option"), "option"));
		criteria.add(Restrictions.eq("polls.id", poollId));
		criteria.setResultTransformer(Transformers.aliasToBean(PollOptions.class));
		List<PollOptions> options = criteria.list();

		PollOptionPercentageDTO percentageDTO = new PollOptionPercentageDTO();
		List<PollOptionPercentageDTO> list = new ArrayList<>();
		Criteria criteria2 = getCriteriaForSelect(PollAnswer.class);
		criteria2.add(Restrictions.eq("pollId", poollId));
		criteria2.setProjection(Projections.count("pollId"));
		double total = (Long) criteria2.uniqueResult();
		double current = 0;
		double percentage = 0;

		DecimalFormat df = new DecimalFormat("####0.00");
		for (PollOptions pollOptions : options)
		{
			percentageDTO = new PollOptionPercentageDTO();
			Criteria criteria1 = getCriteriaForSelect(PollAnswer.class);
			criteria1.add(Restrictions.eq("pollId", poollId));
			criteria1.add(Restrictions.eq("optionID", pollOptions.getOption_Id()));
			criteria1.setProjection(Projections.count("optionID"));
			current = (Long) criteria1.uniqueResult();
			Float per = 0.0f;
			percentage = current / total * 100;
			try
			{
				per = Float.parseFloat(df.format(percentage));
			} catch (Exception e)
			{

			}

			percentageDTO.setOptionname(pollOptions.getOption());
			percentageDTO.setPercentage(per);
			list.add(percentageDTO);
		}
		return list;
	}

	@Override
	public void addPoll_Answer(Integer pollId, Integer optionId, Integer clientId)
	{
		PollAnswer pollAnswer = new PollAnswer();
		pollAnswer.setPollId(pollId);
		pollAnswer.setOptionID(optionId);
		pollAnswer.setClientID(clientId);
		save(pollAnswer);
	}

	@Override
	public List<Polls> pollListStaff()
	{
		Criteria criteria = getCriteriaForSelect(Polls.class);
		criteria.addOrder(Order.desc("createdDate"));
		criteria.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		return criteria.list();
	}

	@Override
	public Polls pollUpdateRest(PollBeanDTO pollBeanDTO)
	{

		Session session = this.sessionFactory.getCurrentSession();
		Polls polls = session.get(Polls.class, pollBeanDTO.getId());
		{
			try
			{
				polls.setQuestion(pollBeanDTO.getQuestion());
				SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				Date dt1 = null;
				try
				{
					dt1 = format1.parse(pollBeanDTO.getToDateString());

					DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
					String todateStr = dateFormat.format(dt1);

					polls.setTo_Date(dt1);
					polls.setToDateString(todateStr);
				} catch (ParseException e)
				{
					e.printStackTrace();
					return polls;
				}
				save(polls);
				return polls;
			} catch (Exception e)
			{
				// TODO: handle exception
				return polls;
			}
		}
	}

	@Override
	@Transactional
	public List<Polls> getpoll(Integer clientid)
	{

		long l = clientid;
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Polls.class);
		c.add(Restrictions.eq("clientId", l));
		List<Polls> poll = c.list();
		System.out.println("poll" + poll);
		return poll;
	}

	@Override
	public Group_Poll addGroupPollQuestion(Group_Poll gpolls, String[] option, Integer gropid)
	{
		System.out.println("...........pol result" + gpolls);
		DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

		String todateStr = dateFormat.format(gpolls.getTo_Date());
		gpolls.setToDateString(todateStr);

		Group_Poll gpolls2 = (Group_Poll) save(gpolls);
		Group_PollOptions gpollOptions = null;

		for (String ops : option)
		{
			gpollOptions = new Group_PollOptions();
			gpollOptions.setOption(ops);
			gpollOptions.setPolls(gpolls2);
			save(gpollOptions);
		}

		return gpolls2;
	}

	@Override
	public List<Group_Poll> getPast_GroupPollList(Integer gropid)
	{
		// TODO Auto-generated method stub
		Date date = new Date();
		Criteria criteria = getCriteriaForSelect(Group_Poll.class);
		criteria.add(Restrictions.eq("g_id", gropid));
		criteria.add(Restrictions.le("to_Date", date));
		return criteria.list();

	}

	@Override
	public List<Group_Poll> getcurrent_GroupPollList(Integer groupId)
	{
		Date date = new Date();
		Criteria criteria = getCriteriaForSelect(Group_Poll.class);
		criteria.add(Restrictions.eq("g_id", groupId));
		criteria.add(Restrictions.gt("to_Date", date));
		return criteria.list();
	}

	@Override
	public List<Group_PollDTO> getgroupPoll_ListRest(Integer id, Integer g_id)
	{
		// TODO Auto-generated method stub

		Long value1 = id.longValue();
		Criteria criteria1 = getCriteriaForSelect(Group_Poll.class);
		criteria1.add(Restrictions.eq("clientId", value1));
		criteria1.add(Restrictions.eq("g_id", g_id));
		System.out.println("........////.........." + criteria1.list());
		if (criteria1.list().isEmpty())
		{
			System.out.println("...... student///......");
			Date date = new Date();
			Session session = this.sessionFactory.getCurrentSession();
			Criteria stud = getCriteriaForSelect(Student.class);
			stud.add(Restrictions.eq("comClientName.id", id));
			Student student = (Student) stud.uniqueResult();
			List<Group_PollDTO> list = new ArrayList<>();

			Criteria criteria = getCriteriaForSelect(Group_Poll.class);
			criteria.add(Restrictions.gt("to_Date", date));
			criteria.setProjection(Projections.projectionList().add(Property.forName("id"), "id")
					.add(Property.forName("question"), "question")
					.add(Property.forName("toDateString"), "toDateString"));
			criteria.add(Restrictions.eq("g_id", g_id));
			criteria.setResultTransformer(Transformers.aliasToBean(Group_PollDTO.class));
			System.out.println("criteria.list(.................)" + criteria.list());
			list.addAll(criteria.list());

			for (Group_PollDTO GpollsDTO : list)
			{
				Criteria polllist = getCriteriaForSelect(Group_PollAnswer.class);
				polllist.add(Restrictions.eq("clientID", id));
				polllist.add(Restrictions.eq("pollId", GpollsDTO.getId()));
				if (polllist.uniqueResult() == null)
				{
					GpollsDTO.setVotedornot(false);
				} else
				{
					GpollsDTO.setVotedornot(true);
				}
			}
			System.out.println("......................." + list);
			return list;
		} else
		{
			System.out.println("......Staff///......");
			Criteria criteria = getCriteriaForSelect(Group_Poll.class);
			criteria.addOrder(Order.desc("createdDate"));
			criteria.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
			criteria.add(Restrictions.eq("clientId", value1));
			criteria.add(Restrictions.eq("g_id", g_id));
			List<Group_Poll> list = criteria.list();

			List<Group_PollDTO> pollList = new ArrayList<>();
			Group_PollDTO group_PollDTO = null;
			Date date = new Date();
			for (Group_Poll polls : list)
			{
				group_PollDTO = new Group_PollDTO();
				group_PollDTO.setId(polls.getId());
				group_PollDTO.setQuestion(polls.getQuestion());
				group_PollDTO.setToDateString(polls.getToDateString());
				group_PollDTO.setClientId(polls.getClientId());
				if (polls.getTo_Date().after(date))
				{
					group_PollDTO.setPollStatus(true);
				} else
				{
					group_PollDTO.setPollStatus(false);
				}
				pollList.add(group_PollDTO);
			}

			for (Group_PollDTO GpollsDTO : pollList)
			{
				Criteria polllist = getCriteriaForSelect(Group_PollAnswer.class);
				polllist.add(Restrictions.eq("clientID", id));
				polllist.add(Restrictions.eq("pollId", GpollsDTO.getId()));
				if (polllist.uniqueResult() == null)
				{
					GpollsDTO.setVotedornot(false);
				} else
				{
					GpollsDTO.setVotedornot(true);
				}
			}

			return pollList;

		}

	}

	@Override
	@Transactional

	public List<Group_PollOptions> getGroupPoll_OptionList(Integer pollId)
	{

		Criteria criteria = getCriteriaForSelect(Group_PollOptions.class);
		criteria.setProjection(Projections.projectionList().add(Property.forName("option_Id"), "option_Id")
				.add(Property.forName("option"), "option"));
		criteria.add(Restrictions.eq("polls.id", pollId));
		criteria.setResultTransformer(Transformers.aliasToBean(Group_PollOptions.class));
		return criteria.list();
	}

	@Override
	public void addgroupPoll_Answer(Integer pollId, Integer optionId, Integer clientId)
	{
		// TODO Auto-generated method stub

		Group_PollAnswer gpollAnswer = new Group_PollAnswer();
		gpollAnswer.setPollId(pollId);
		gpollAnswer.setOptionID(optionId);
		gpollAnswer.setClientID(clientId);
		save(gpollAnswer);

	}

	@Override
	public List<GroupPollOptionPercentageDTO> getgrpollGraphResult(Integer pollId)
	{
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = getCriteriaForSelect(Group_PollOptions.class);
		criteria.setProjection(Projections.projectionList().add(Property.forName("option_Id"), "option_Id")
				.add(Property.forName("option"), "option"));
		criteria.add(Restrictions.eq("polls.id", pollId));
		criteria.setResultTransformer(Transformers.aliasToBean(Group_PollOptions.class));
		List<Group_PollOptions> options = criteria.list();

		GroupPollOptionPercentageDTO percentageDTO = new GroupPollOptionPercentageDTO();
		List<GroupPollOptionPercentageDTO> list = new ArrayList<>();

		Criteria criteria2 = getCriteriaForSelect(Group_PollAnswer.class);
		criteria2.add(Restrictions.eq("pollId", pollId));
		criteria2.setProjection(Projections.count("pollId"));
		double total = (Long) criteria2.uniqueResult();
		double current = 0;
		double percentage = 0;

		DecimalFormat df = new DecimalFormat("####0.00");
		for (Group_PollOptions pollOptions : options)
		{
			percentageDTO = new GroupPollOptionPercentageDTO();
			Criteria criteria1 = getCriteriaForSelect(Group_PollAnswer.class);
			criteria1.add(Restrictions.eq("pollId", pollId));
			criteria1.add(Restrictions.eq("optionID", pollOptions.getOption_Id()));
			criteria1.setProjection(Projections.count("optionID"));
			current = (Long) criteria1.uniqueResult();
			Float per = 0.0f;
			percentage = current / total * 100;
			try
			{
				per = Float.parseFloat(df.format(percentage));
			} catch (Exception e)
			{

			}

			percentageDTO.setOptionname(pollOptions.getOption());
			percentageDTO.setPercentage(per);
			list.add(percentageDTO);
		}
		return list;
	}

	@Override
	public List<Group_PollDTO> grouppollListStaff(Integer id, Integer g_id)
	{
		// TODO Auto-generated method stub
		Long value1 = id.longValue();
		Criteria criteria = getCriteriaForSelect(Group_Poll.class);
		criteria.addOrder(Order.desc("createdDate"));
		criteria.add(Restrictions.eq(Constants.RECORD_STATUS, Constants.ACTIVE_RECORD_STATUS));
		criteria.add(Restrictions.eq("clientId", value1));
		criteria.add(Restrictions.eq("g_id", g_id));
		List<Group_Poll> list = criteria.list();

		List<Group_PollDTO> pollList = new ArrayList<>();
		Group_PollDTO group_PollDTO = null;
		Date date = new Date();
		for (Group_Poll polls : list)
		{
			group_PollDTO = new Group_PollDTO();
			group_PollDTO.setId(polls.getId());
			group_PollDTO.setQuestion(polls.getQuestion());
			group_PollDTO.setToDateString(polls.getToDateString());
			group_PollDTO.setClientId(polls.getClientId());
			if (polls.getTo_Date().after(date))
			{
				group_PollDTO.setPollStatus(true);
			} else
			{
				group_PollDTO.setPollStatus(false);
			}
			pollList.add(group_PollDTO);
		}
		return pollList;

	}

	@Override
	@Transactional
	public Group_Poll addgroupPollQuestion(Group_Poll polls, String[] options)
	{
		// TODO Auto-generated method stub

		DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

		String todateStr = dateFormat.format(polls.getTo_Date());
		polls.setToDateString(todateStr);

		Group_Poll polls2 = (Group_Poll) save(polls);
		Group_PollOptions pollOptions = null;
		for (String ops : options)
		{
			pollOptions = new Group_PollOptions();
			pollOptions.setOption(ops);
			pollOptions.setPolls(polls2);
			save(pollOptions);
		}

		return polls2;
	}

	@Override
	@Transactional
	public Group_Poll udategroupPoll(Group_Poll gpolls)
	{
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		System.out.println("polls.." + gpolls);

		DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

		String todateStr = dateFormat.format(gpolls.getTo_Date());
		gpolls.setToDateString(todateStr);
		gpolls.setRecordStatus("A");
		return (Group_Poll) save(gpolls);
	}

	@Override
	public boolean deletegroupPoll(int id)
	{
		// TODO Auto-generated method stub
		try
		{
			Session session = this.sessionFactory.getCurrentSession();
			Group_Poll polls = session.get(Group_Poll.class, id);
			polls.setRecordStatus(Constants.DELETED_RECORD_STATUS);
			save(polls);
			return true;
		} catch (Exception e)
		{
			return false;
		}

	}

	@Override
	public Group_Poll grouppollUpdateRest(GroupPollBeanDTO grouppollBeanDTO)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Group_Poll polls = session.get(Group_Poll.class, grouppollBeanDTO.getId());
		{
			try
			{
				polls.setQuestion(grouppollBeanDTO.getQuestion());
				SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				Date dt1 = null;
				try
				{
					dt1 = format1.parse(grouppollBeanDTO.getToDateString());

					DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
					String todateStr = dateFormat.format(dt1);

					polls.setTo_Date(dt1);
					polls.setToDateString(todateStr);
				} catch (ParseException e)
				{
					e.printStackTrace();
					return polls;
				}
				save(polls);
				return polls;
			} catch (Exception e)
			{
				// TODO: handle exception
				return polls;
			}
		}
	}
}
