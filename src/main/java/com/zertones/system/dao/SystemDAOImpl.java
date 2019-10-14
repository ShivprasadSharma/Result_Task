package com.zertones.system.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.zertones.dao.BaseDAOImpl;
import com.zertones.model.common.InstituteInfoMaster;
import com.zertones.model.master.ComListMaster;

/**
 * @author Abhijit
 * @Created Date : Sep 29, 2015
 */
@Repository
public class SystemDAOImpl extends BaseDAOImpl implements SystemDAO
{
	private static final Logger logger = LoggerFactory.getLogger(SystemDAOImpl.class);

	/*
	 * @Autowired protected SessionFactory sessionFactory;
	 *
	 * @Override public void setSessionFactory(SessionFactory sf) {
	 * this.sessionFactory = sf; }
	 */

	@Override
	public List<InstituteInfoMaster> listInstituteInfoMasters()
	{
		logger.debug("Listing Master");
		// Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		/*
		 * List<InstituteInfoMaster> masterList = session .createSQLQuery(
		 * "select iim.listId,iim.value from InstituteInfoMaster iim")
		 * .addEntity(InstituteInfoMaster.class).list();
		 */

		Criteria cr = getCriteria(InstituteInfoMaster.class)// session.createCriteria(InstituteInfoMaster.class)
				.setProjection(Projections.projectionList().add(Projections.property("listId"), "listId")
						.add(Projections.property("value"), "value"))
				.setResultTransformer(Transformers.aliasToBean(InstituteInfoMaster.class));
		List<InstituteInfoMaster> masterList = cr.list();

		logger.debug("Master List : " + masterList);
		return masterList;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.zertones.system.dao.SystemDAO#listCommonMasters()
	 */
	@Override
	public List<ComListMaster> listCommonMasters()
	{
		logger.info("Listing Institute Master");
		// Session session = sessionFactory.getCurrentSession();
		// Query query = // session.createQuery("from
		// ComListMaster");
		// query.setCacheable(true);
		List<ComListMaster> instituteInfoMaster = get("from ComListMaster");// query.list();
		return instituteInfoMaster;
	}

}
