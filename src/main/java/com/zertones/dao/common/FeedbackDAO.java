package com.zertones.dao.common;

import java.util.List;

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
import com.zertones.model.common.PollOptions;
import com.zertones.model.common.Polls;

public interface FeedbackDAO
{
	public List<FeedbackData> getfeedbsckdata(Integer studeId);

	public Boolean SaveFeedbackData(FeedbackData feedbackvote);

	public List<FeedbackVote> getfeedbacklist(Integer staff_id, Integer yrid);

	public FeedbackProfile getfeedbackdetail(Integer id, Integer sem);

	public List<FeedbackQue> getque();

	// poll

	public Polls addPollQuestion(Polls polls, String[] option);

	public List<Polls> getPoll_List();

	public List<Polls> getPast_PollList();

	public boolean deletePoll(int id);

	public List<PollsDTO> getPoll_ListRest(Integer id);

	public List<PollOptions> getPoll_OptionList(Integer pollId);

	public Polls udatePoll(Polls polls);

	public List<PollOptionPercentageDTO> getGraphResult(Integer poollId);

	public void addPoll_Answer(Integer pollId, Integer optionId, Integer clientId);

	public List<Polls> pollListStaff();

	public Polls pollUpdateRest(PollBeanDTO pollBeanDTO);

	public List<Polls> getpoll(Integer clientid);

	public Group_Poll addGroupPollQuestion(Group_Poll gpolls, String[] option, Integer gropid);

	public List<Group_Poll> getPast_GroupPollList(Integer gropid);

	public List<Group_Poll> getcurrent_GroupPollList(Integer groupId);

	public List<Group_PollDTO> getgroupPoll_ListRest(Integer id, Integer g_id);

	public List<Group_PollOptions> getGroupPoll_OptionList(Integer pollId);

	public void addgroupPoll_Answer(Integer pollId, Integer optionId, Integer clientId);

	public List<GroupPollOptionPercentageDTO> getgrpollGraphResult(Integer pollId);

	public List<Group_PollDTO> grouppollListStaff(Integer id, Integer g_id);

	public Group_Poll addgroupPollQuestion(Group_Poll polls, String[] options);

	public Group_Poll udategroupPoll(Group_Poll gpolls);

	public boolean deletegroupPoll(int id);

	public Group_Poll grouppollUpdateRest(GroupPollBeanDTO grouppollBeanDTO);

}
