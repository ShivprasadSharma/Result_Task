package com.zertones.dao;

import com.zertones.model.ComUserDetails;

/**
 * @author Abhijit
 * @Created Date : Oct 26, 2015
 */
public interface UserDetailsDAO
{
	public ComUserDetails loadUserByUsername(String username);

	public String enCoder256(String base);

}
