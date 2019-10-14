package com.zertones.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zertones.model.ComUserDetails;
import com.zertones.model.DeviceDetails;
import com.zertones.model.common.GroupMembers;
import com.zertones.model.common.Group_Poll;
import com.zertones.model.common.Notification;
import com.zertones.model.common.Polls;
import com.zertones.model.sims.Student;

@Repository
public class DeviceDAOImpl extends BaseDAOImpl implements DeviceDAO, BaseDAO
{
	private static final Logger	logger	= LoggerFactory.getLogger(DeviceDAOImpl.class);

	@Autowired
	protected SessionFactory	sessionFactory;

	@Override
	public void setSessionFactory(SessionFactory sf)
	{
		this.sessionFactory = sf;
	}

	@Override
	public void addDevice(DeviceDetails device)
	{
		logger.info("Device saved successfully");
		Session session = sessionFactory.getCurrentSession();
		DeviceDetails details = (DeviceDetails) session
				.createQuery("from DeviceDetails  where recordStatus = 'A' and  comUserDetails.id ="
						+ device.getComUserDetails().getId())
				.uniqueResult();
		ComUserDetails comuserdetails = new ComUserDetails();
		comuserdetails.setId(device.getComUserDetails().getId());
		if (details != null)
		{
			details.setDeviceId(device.getDeviceId());
			details.setDeviceModel(device.getDeviceModel());
			details.setDeviceType(device.getDeviceType());
			details.setComUserDetails(comuserdetails);
			update(details);
		} else
		{
			DeviceDetails detail = new DeviceDetails();
			detail.setDeviceId(device.getDeviceId());
			detail.setDeviceModel(device.getDeviceModel());
			detail.setDeviceType(device.getDeviceType());
			detail.setComUserDetails(comuserdetails);
			save(device);
		}
		session.flush();
		session.close();
	}

	@Override
	public List<DeviceDetails> listDevices()
	{
		logger.info("Listing Notification");
		List<DeviceDetails> l2 = new ArrayList<>();
		Criteria cr = getCriteriaForSelect(DeviceDetails.class);
		cr.setProjection(Projections.projectionList().add(Property.forName("deviceId"), "deviceId"));
		l2.addAll(cr.list());

		return l2;
	}

	@Override
	public List<DeviceDetails> listDevices(Integer userId)
	{
		logger.info("Calling Device details....!");
		Criteria criteria = getCriteriaForSelect(DeviceDetails.class);
		criteria.add(Restrictions.eq("comUserDetails.id", userId));
		List<DeviceDetails> deviceList = criteria.list();
		logger.info("Device loaded successfully");
		return deviceList;
	}

	@Override
	public DeviceDetails getDeviceById(Integer id)
	{
		Session session = this.sessionFactory.getCurrentSession();
		DeviceDetails devices = session.get(DeviceDetails.class, new Integer(id));
		session.flush();
		logger.info("Device loaded successfully, Notification details");
		return devices;
	}

	@Override
	public int removeDevice(Integer id)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("update DeviceDetails set recordStatus ='D' where id = :id");
		query.setInteger("id", id);
		int modifications = query.executeUpdate();
		session.flush();
		session.close();
		return modifications;
	}

	@SuppressWarnings("null")
	@Override
	public List<DeviceDetails> listDevices(Notification notification)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = getCriteriaForSelect(Student.class);
		criteria.setProjection(Projections.projectionList().add(Property.forName("comClientName.id")));
		criteria.add(Restrictions.eq("branch", notification.getDepartment()));

		List l = criteria.list();
		Iterator it = l.iterator();
		List<Integer> l1 = new ArrayList<>();
		List<DeviceDetails> l2 = new ArrayList<>();

		// while (it.hasNext())
		// {
		// Query qry1 = session.createQuery(
		// "select c.id from ComUserDetails c inner join Student s on c.comClientName.id
		// = s.comClientName.id where c.comClientName.id="
		// + it.next());
		// l1.addAll(qry1.list());
		// }

		Criteria cr1 = getCriteriaForSelect(ComUserDetails.class);
		cr1.setProjection(Projections.projectionList().add(Property.forName("id"), "id"));
		cr1.add(Restrictions.in("comClientName.id", l));
		l1.addAll(cr1.list());

		// for (int i = 0; i < l1.size(); i++)
		// {
		// Query q = session
		// .createQuery("select p.deviceId from DeviceDetails p where
		// p.comUserDetails.id =" + l1.get(i));
		Criteria cr = getCriteriaForSelect(DeviceDetails.class);
		cr.setProjection(Projections.projectionList().add(Property.forName("deviceId"), "deviceId"));
		cr.add(Restrictions.in("comUserDetails.id", l1));
		l2.addAll(cr.list());

		// }
		return l2;
	}

	@SuppressWarnings("null")
	@Override
	public List<DeviceDetails> listOfDevices(Notification notification)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Query qry = session
				.createQuery("select p.comClientName.id from GroupMembers p where recordStatus='A' and groups = "
						+ notification.getGroups());
		List l = qry.list();
		Iterator it = l.iterator();
		List<Integer> l1 = new ArrayList<>();
		List<DeviceDetails> l2 = new ArrayList<>();

		while (it.hasNext())
		{
			Query qry1 = session.createQuery("select c.id from ComUserDetails c where c.comClientName.id=" + it.next());
			l1.addAll(qry1.list());

		}

		// for (int i = 0; i < l1.size(); i++)
		// {

		// ComUserDetails user = getUserDetails();
		// Query q = session
		// .createQuery("select p.deviceId from DeviceDetails p where
		// p.comUserDetails.id =" + l1.get(i));
		// l2.addAll(q.list());

		Criteria cr = getCriteriaForSelect(DeviceDetails.class);
		cr.setProjection(Projections.projectionList().add(Property.forName("deviceId"), "deviceId"));
		cr.add(Restrictions.in("comUserDetails.id", l1));
		l2.addAll(cr.list());
		// System.out.println("Group Notice :" + l2);
		// }
		// session.flush();
		// session.close();
		return l2;
	}

	@Override
	public List<DeviceDetails> getDevicesId(Integer clientID)
	{
		// TODO Auto-generated method stub

		logger.info("Calling Device details....!");
		Criteria criteria = getCriteriaForSelect(ComUserDetails.class);
		criteria.add(Restrictions.eq("comClientName.id", clientID));
		ComUserDetails userDetails = (ComUserDetails) criteria.uniqueResult();

		Criteria criteria1 = getCriteriaForSelect(DeviceDetails.class);
		criteria1.setProjection(Projections.projectionList().add(Property.forName("deviceId"), "deviceId"));
		criteria1.add(Restrictions.eq("comUserDetails.id", userDetails.getId()));
		return criteria1.list();

	}

	@SuppressWarnings("null")
	@Override
	public List<DeviceDetails> listDevicesComman(Polls poll)
	{

		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = getCriteriaForSelect(Student.class);
		criteria.setProjection(Projections.projectionList().add(Property.forName("comClientName.id")));
		criteria.add(Restrictions.eq("branch", poll.getDepID()));
		if (poll.getYear() != 0)
		{
			criteria.add(Restrictions.eq("year", poll.getYear()));
		}
		List l = criteria.list();
		List<Integer> l1 = new ArrayList<>();
		List<DeviceDetails> l2 = new ArrayList<>();
		if (!l.isEmpty())
		{
			try
			{

				Criteria cr1 = getCriteriaForSelect(ComUserDetails.class);
				cr1.setProjection(Projections.projectionList().add(Property.forName("id"), "id"));
				cr1.add(Restrictions.in("comClientName.id", l));
				l1.addAll(cr1.list());

				Criteria cr = getCriteriaForSelect(DeviceDetails.class);
				cr.setProjection(Projections.projectionList().add(Property.forName("deviceId"), "deviceId"));
				cr.add(Restrictions.in("comUserDetails.id", l1));
				l2.addAll(cr.list());
				return l2;
			} catch (Exception e)
			{
				// TODO: handle exception
				return l2;
			}

		} else
		{
			return l2;
		}

	}

	@Override
	@Transactional
	public List<DeviceDetails> getDiviceforTPO(List<Integer> idList)
	{
		List<Integer> l1 = new ArrayList<>();
		List<DeviceDetails> l2 = new ArrayList<>();
		try
		{

			Criteria cr1 = getCriteriaForSelect(ComUserDetails.class);
			cr1.setProjection(Projections.projectionList().add(Property.forName("id"), "id"));
			cr1.add(Restrictions.in("comClientName.id", idList));
			l1.addAll(cr1.list());

			System.out.println("client id list" + cr1.list());

			Criteria cr = getCriteriaForSelect(DeviceDetails.class);
			cr.setProjection(Projections.projectionList().add(Property.forName("deviceId"), "deviceId"));
			cr.add(Restrictions.in("comUserDetails.id", l1));
			l2.addAll(cr.list());

			System.out.println("divice  id list" + cr.list());
			return l2;
		} catch (Exception e)
		{
			return l2;
		}
	}

	@Override
	@Transactional

	public List<DeviceDetails> listDevicesforGPoll(Group_Poll gpolls1)
	{
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = getCriteriaForSelect(GroupMembers.class);
		criteria.setProjection(Projections.projectionList().add(Property.forName("comClientName.id")));
		criteria.add(Restrictions.eq("groups.groupId", gpolls1.getG_id()));

		List l = criteria.list();
		System.out.println("../././././././././" + l);
		List<Integer> l1 = new ArrayList<>();
		List<DeviceDetails> l2 = new ArrayList<>();
		if (!l.isEmpty())
		{
			try
			{
				Criteria cr1 = getCriteriaForSelect(ComUserDetails.class);
				cr1.setProjection(Projections.projectionList().add(Property.forName("id"), "id"));
				cr1.add(Restrictions.in("comClientName.id", l));
				l1.addAll(cr1.list());

				Criteria cr = getCriteriaForSelect(DeviceDetails.class);
				cr.setProjection(Projections.projectionList().add(Property.forName("deviceId"), "deviceId"));
				cr.add(Restrictions.in("comUserDetails.id", l1));
				l2.addAll(cr.list());
				System.out.println("Divice List is" + l2);
				return l2;
			} catch (Exception e)
			{
				// TODO: handle exception
				System.out.println("Exception");
				return l2;

			}

		} else
		{
			return l2;
		}

	}

}
