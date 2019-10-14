package com.zertones.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zertones.dao.UserDetailsDAO;
import com.zertones.dao.UserDetailsDAOImpl;
import com.zertones.model.ComUserDetails;
import com.zertones.security.model.UserRole;

/**
 * @author Abhijit
 * @Created Date : Oct 26, 2015
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private UserDetailsDAO userDetailsDAO;

	public void setDeviceDAO(UserDetailsDAO userDetailsDAO)
	{
		this.userDetailsDAO = userDetailsDAO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		ComUserDetails comUserDetails = userDetailsDAO.loadUserByUsername(username);
		List<GrantedAuthority> authorities = buildUserAuthority(comUserDetails.getUserRoles());
		return buildUserForAuthentication(comUserDetails, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles)
	{

		Set<GrantedAuthority> setAuths = new HashSet<>();

		// Build user's authorities
		for (UserRole userRole : userRoles)
		{
			setAuths.add(new SimpleGrantedAuthority(userRole.getRoleName()));
		}

		List<GrantedAuthority> Result = new ArrayList<>(setAuths);

		return Result;
	}

	private User buildUserForAuthentication(ComUserDetails user, List<GrantedAuthority> authorities)
	{
		boolean isEnabled = false;

		if (user.getRecordStatus() != null && user.getRecordStatus() == "A")
		{
			isEnabled = true;
		} else
		{
			isEnabled = false;
		}
		return new User(user.getUserName(), user.getPassword().toString(), isEnabled, true, true, true, authorities);
	}

	@Override
	public ComUserDetails getUserByUsername(String userName)
	{
		if (userDetailsDAO == null)
		{
			userDetailsDAO = new UserDetailsDAOImpl();
		}
		return userDetailsDAO.loadUserByUsername(userName);
	}

}
