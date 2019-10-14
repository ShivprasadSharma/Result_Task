package com.zertones.dao;

import java.security.MessageDigest;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zertones.model.ComUserDetails;

/**
 * @author Abhijit
 * @Created Date : Oct 26, 2015
 */
@Repository
public class UserDetailsDAOImpl extends BaseDAOImpl implements UserDetailsDAO
{
	/*
	 * @Autowired protected SessionFactory sessionFactory;
	 *
	 * @Override public void setSessionFactory(SessionFactory sf) {
	 * this.sessionFactory = sf; }
	 */

	@Override
	public ComUserDetails loadUserByUsername(String username)
	{
		try
		{
			Criteria criteria = getCriteria(ComUserDetails.class);
			criteria.add(Restrictions.eq("userName", username));
			criteria.setCacheable(true);
			System.out.println("bean first................");
			ComUserDetails comUserDetails = (ComUserDetails) criteria.uniqueResult();
			System.out.println("bean last................");

			if (comUserDetails == null)
			{
				return null;
			} else
			{
				return comUserDetails;
			}
		} catch (Exception e)
		{
			return null;
		}

	}

	@Override
	public String enCoder256(String base)
	{

		try
		{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (byte element : hash)
			{
				String hex = Integer.toHexString(0xff & element);
				if (hex.length() == 1)
				{
					hexString.append('0');
				}
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (Exception ex)
		{
			throw new RuntimeException(ex);
		}

	}

}
