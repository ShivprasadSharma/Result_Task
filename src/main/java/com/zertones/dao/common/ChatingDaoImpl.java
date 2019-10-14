package com.zertones.dao.common;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zertones.controller.service.ChatController;
import com.zertones.dao.BaseDAOImpl;
import com.zertones.model.ComChatMessages;
import com.zertones.model.sims.Student;

@Repository
public class ChatingDaoImpl extends BaseDAOImpl implements ChatingDao
{
	private static final Logger	logger	= LoggerFactory.getLogger(ChatController.class);

	@Autowired
	protected SessionFactory	sessionFactory;

	@Override
	public List<ComChatMessages> getListOfChat(Integer id)
	{
		logger.info("Chat DAO Impl");
		List<ComChatMessages> list = getChat(id);
		return list;
	}

	@Override
	public List<Student> getMemberList(Integer viewId)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Student.class, "studentAlies");
		c.add(Restrictions.eq("studentAlies.branch", viewId));
		List<Student> studentList = c.list();
		System.out.println("Student List for chat :" + studentList);
		return studentList;
	}

	@Override
	public List<ComChatMessages> getChat(Integer fromId, Integer uid)
	{
		logger.info("Chat DAO Impl");
		System.out.println("Dao Impl:" + uid);
		Session session = this.sessionFactory.getCurrentSession();

		Criteria c = session.createCriteria(ComChatMessages.class);
		Criterion crforId = Restrictions.eq("fromId", fromId);
		Criterion crtoId = Restrictions.eq("toId", uid);
		LogicalExpression andExp = Restrictions.and(crforId, crtoId);

		Criterion crforId1 = Restrictions.eq("fromId", uid);
		Criterion crtoId1 = Restrictions.eq("toId", fromId);
		LogicalExpression andExap1 = Restrictions.and(crforId1, crtoId1);

		LogicalExpression orExap = Restrictions.or(andExp, andExap1);
		c.add(orExap);

		@SuppressWarnings("unchecked")
		List<ComChatMessages> list = c.list();

		System.out.println("List:" + list);
		return list;
	}

	@Override
	public void saveChtMsg(ComChatMessages comChatMessages)
	{
		logger.info("Save Chat Msg DAO Impl");

		saveCahtMsg(comChatMessages);
	}

}
