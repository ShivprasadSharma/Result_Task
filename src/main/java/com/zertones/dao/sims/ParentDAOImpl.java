package com.zertones.dao.sims;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.zertones.dao.BaseDAOImpl;
import com.zertones.model.sims.Parent;

public class ParentDAOImpl extends BaseDAOImpl implements ParentDAO
{
	@Override
	@Transactional
	public Parent ParentDetails(Parent parent)
	{
		// Student student = new Student();
		Session session = sessionFactory.getCurrentSession();
		// session.beginTransaction();
		session.save(parent.getComClientName());
		session.save(parent.getComClientName().getComUserDetails());
		// session.saveOrUpdate(student);
		session.save(parent);
		session.getTransaction().commit();
		session.close();
		// logger.info("Parent saved successfully, Parent Details = " + parent);
		return parent;
	}
}
