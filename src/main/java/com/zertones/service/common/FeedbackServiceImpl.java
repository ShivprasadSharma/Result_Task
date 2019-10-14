package com.zertones.service.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zertones.dao.common.FeedbackDAO;
import com.zertones.model.DataTransferObjectModel.GroupPollBeanDTO;
import com.zertones.model.DataTransferObjectModel.GroupPollOptionPercentageDTO;
import com.zertones.model.DataTransferObjectModel.Group_PollDTO;
import com.zertones.model.DataTransferObjectModel.PollBeanDTO;
import com.zertones.model.DataTransferObjectModel.PollOptionPercentageDTO;
import com.zertones.model.DataTransferObjectModel.PollsDTO;
import com.zertones.model.common.FeedbackData;
import com.zertones.model.common.FeedbackProfile;
import com.zertones.model.common.FeedbackQue;
import com.zertones.model.common.FeedbackVote;
import com.zertones.model.common.Group_Poll;
import com.zertones.model.common.Group_PollOptions;
import com.zertones.model.common.InstituteInfoModel;
import com.zertones.model.common.PollOptions;
import com.zertones.model.common.Polls;
import com.zertones.system.beans.DropDownList;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService
{
	@Autowired
	private FeedbackDAO			feedbackDAO;

	private InstituteInfoModel	instituteInfoModel;

	@Autowired
	private DropDownList		dropDownList;

	@Override
	public List<FeedbackData> getfeedbsckdata(Integer studeId)
	{
		return feedbackDAO.getfeedbsckdata(studeId);

	}

	@Override
	public Boolean SaveFeedbackData(FeedbackData feedbackvote)
	{
		// TODO Auto-generated method stub
		return feedbackDAO.SaveFeedbackData(feedbackvote);
	}

	@Override
	public List<FeedbackVote> getfeedbacklist(Integer staff_id, Integer yrid)
	{
		// TODO Auto-generated method stub
		return feedbackDAO.getfeedbacklist(staff_id, yrid);
	}

	@Override
	public FeedbackProfile getfeedbackdetail(Integer id, Integer sem)
	{
		// TODO Auto-generated method stub
		return feedbackDAO.getfeedbackdetail(id, sem);
	}

	@Override
	public List<FeedbackQue> getque()
	{
		// TODO Auto-generated method stub
		return feedbackDAO.getque();
	}

	@Override
	public Polls addPollQuestion(Polls polls, String[] option)
	{
		// TODO Auto-generated method stub
		return feedbackDAO.addPollQuestion(polls, option);
	}

	@Override
	public List<Polls> getPoll_List()
	{
		// TODO Auto-generated method stub
		return feedbackDAO.getPoll_List();
	}

	@Override
	public List<Polls> getPast_PollList()
	{
		// TODO Auto-generated method stub
		return feedbackDAO.getPast_PollList();
	}

	@Override
	public boolean deletePoll(int id)
	{
		// TODO Auto-generated method stub
		return feedbackDAO.deletePoll(id);
	}

	@Override
	public List<PollsDTO> getPoll_ListRest(Integer id)
	{
		// TODO Auto-generated method stub
		return feedbackDAO.getPoll_ListRest(id);
	}

	@Override
	public List<PollOptions> getPoll_OptionList(Integer pollId)
	{
		// TODO Auto-generated method stub
		return feedbackDAO.getPoll_OptionList(pollId);
	}

	@Override
	public Polls udatePoll(Polls polls)
	{
		// TODO Auto-generated method stub
		return feedbackDAO.udatePoll(polls);
	}

	@Override
	public List<PollOptionPercentageDTO> getGraphResult(Integer poollId)
	{
		// TODO Auto-generated method stub
		return feedbackDAO.getGraphResult(poollId);
	}

	@Override
	public void addPoll_Answer(Integer pollId, Integer optionId, Integer clientId)
	{
		// TODO Auto-generated method stub
		feedbackDAO.addPoll_Answer(pollId, optionId, clientId);
	}

	@Override
	public Polls addRestPoll(PollBeanDTO pollBeanDTO)
	{
		// TODO Auto-generated method stub

		Polls polls = new Polls();
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		System.out.println("date.................... " + pollBeanDTO.getToDateString());
		Date dt1 = null;
		try
		{
			dt1 = format1.parse(pollBeanDTO.getToDateString());
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		polls.setTo_Date(dt1);
		polls.setQuestion(pollBeanDTO.getQuestion());
		polls.setDepID(pollBeanDTO.getDepID());
		polls.setYear(pollBeanDTO.getYear());
		polls.setClientId(pollBeanDTO.getClientId());
		String[] options = new String[pollBeanDTO.getOptions().size()];

		for (int i = 0; i < options.length; i++)
		{

			options[i] = pollBeanDTO.getOptions().get(i);
		}
		return feedbackDAO.addPollQuestion(polls, options);

	}

	@Override
	public List<PollsDTO> pollListStaff()
	{
		List<Polls> list = feedbackDAO.pollListStaff();
		List<PollsDTO> pollList = new ArrayList<>();
		PollsDTO pollsDTO = null;
		Date date = new Date();
		for (Polls polls : list)
		{
			pollsDTO = new PollsDTO();
			pollsDTO.setId(polls.getId());
			pollsDTO.setQuestion(polls.getQuestion());
			pollsDTO.setToDateString(polls.getToDateString());
			pollsDTO.setClientId(polls.getClientId());
			if (polls.getTo_Date().after(date))
			{

				pollsDTO.setPollStatus(true);

			} else
			{
				pollsDTO.setPollStatus(false);
			}
			pollList.add(pollsDTO);
		}
		return pollList;
	}

	@Override
	public Polls pollUpdateRest(PollBeanDTO pollBeanDTO)
	{

		return feedbackDAO.pollUpdateRest(pollBeanDTO);

	}

	@Override
	public List<Polls> getpoll(Integer clientid)
	{
		// TODO Auto-generated method stub
		return feedbackDAO.getpoll(clientid);

	}

	@Override
	public Group_Poll addGroupPollQuestion(Group_Poll gpolls, String[] option, Integer gropid)
	{
		// TODO Auto-generated method stub
		return feedbackDAO.addGroupPollQuestion(gpolls, option, gropid);
	}

	@Override
	public List<Group_Poll> getPast_GroupPollList(Integer gropid)
	{
		// TODO Auto-generated method stub
		return feedbackDAO.getPast_GroupPollList(gropid);
	}

	@Override
	public List<Group_Poll> getcurrent_GroupPollList(Integer groupId)
	{
		// TODO Auto-generated method stub
		return feedbackDAO.getcurrent_GroupPollList(groupId);
	}

	@Override
	public List<Group_PollDTO> getgroupPoll_ListRest(Integer id, Integer g_id)
	{
		// TODO Auto-generated method stub
		return feedbackDAO.getgroupPoll_ListRest(id, g_id);
	}

	@Override
	public List<Group_PollOptions> getGroupPoll_OptionList(Integer pollId)
	{
		// TODO Auto-generated method stub
		return feedbackDAO.getGroupPoll_OptionList(pollId);
	}

	@Override
	public void addgroupPoll_Answer(Integer pollId, Integer optionId, Integer clientId)
	{
		// TODO Auto-generated method stub
		feedbackDAO.addgroupPoll_Answer(pollId, optionId, clientId);
	}

	@Override
	public List<GroupPollOptionPercentageDTO> getgrpollGraphResult(Integer pollId)
	{
		// TODO Auto-generated method stub
		return feedbackDAO.getgrpollGraphResult(pollId);
	}

	@Override
	public List<Group_PollDTO> grouppollListStaff(Integer id, Integer g_id)
	{
		// TODO Auto-generated method stub

		return feedbackDAO.grouppollListStaff(id, g_id);
	}

	@Override
	public Group_Poll addRestgroupPoll(GroupPollBeanDTO grouppollBeanDTO)
	{
		// TODO Auto-generated method stub

		Group_Poll polls = new Group_Poll();
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		System.out.println("date.................... " + grouppollBeanDTO.getToDateString());
		Date dt1 = null;
		try
		{
			dt1 = format1.parse(grouppollBeanDTO.getToDateString());
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		polls.setTo_Date(dt1);
		polls.setQuestion(grouppollBeanDTO.getQuestion());
		polls.setG_id(grouppollBeanDTO.getG_id());
		polls.setClientId(grouppollBeanDTO.getClientId());
		String[] options = new String[grouppollBeanDTO.getOptions().size()];

		for (int i = 0; i < options.length; i++)
		{

			options[i] = grouppollBeanDTO.getOptions().get(i);
		}
		return feedbackDAO.addgroupPollQuestion(polls, options);

	}

	@Override
	public Group_Poll udategroupPoll(Group_Poll gpolls)
	{
		// TODO Auto-generated method stub
		return feedbackDAO.udategroupPoll(gpolls);
	}

	@Override
	public boolean deletegroupPoll(int id)
	{
		// TODO Auto-generated method stuby
		return feedbackDAO.deletegroupPoll(id);
	}

	@Override
	public Group_Poll grouppollUpdateRest(GroupPollBeanDTO grouppollBeanDTO)
	{
		// TODO Auto-generated method stub
		return feedbackDAO.grouppollUpdateRest(grouppollBeanDTO);
	}

}
