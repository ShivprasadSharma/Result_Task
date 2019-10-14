package com.zertones.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.zertones.model.ComUserDetails;

/**
 * @author Abhijit
 * @Created Date : Oct 26, 2015
 */
public interface UserDetailsService // extends
									// org.springframework.security.core.userdetails.UserDetailsService
{
	// @Override
	public UserDetails loadUserByUsername(String username);

	public ComUserDetails getUserByUsername(String username);

}
