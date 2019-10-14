package com.zertones.system.security;

/**
 * @author Zerton Team
 *@Created Date : Oct 24, 2015
 */
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class NoOpAuthenticationManager implements AuthenticationManager
{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		return authentication;
	}
}
