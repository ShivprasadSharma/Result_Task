package com.zertones.system.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter
{

	protected TokenAuthenticationFilter(String defaultFilterProcessesUrl)
	{
		super(defaultFilterProcessesUrl);
		// defaultFilterProcessesUrl - specified in applicationContext.xml.
		// Authentication will only be initiated for the request url matching
		// this pattern
		super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(defaultFilterProcessesUrl));
		setAuthenticationManager(new NoOpAuthenticationManager());
		setAuthenticationSuccessHandler(new TokenSimpleUrlAuthenticationSuccessHandler());
	}

	/**
	 * Attempt to authenticate request
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException
	{
		String token = request.getHeader("Authorization");
		logger.info("token found:" + token);

		AbstractAuthenticationToken userAuthenticationToken = authUserByToken(token);
		if (userAuthenticationToken == null)
		{
			throw new AuthenticationServiceException("Invalid Token");
		}
		return userAuthenticationToken;
	}

	/**
	 * authenticate the user based on token
	 *
	 * @return
	 */
	private AbstractAuthenticationToken authUserByToken(String token)
	{
		if (token == null)
		{
			return null;
		}

		String username = "";// getUserNameFromToken(); // logic to extract
								// username
								// from token
		String role = "";// getRolesFromToken(); // extract role information
							// from
							// token

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role));

		User principal = new User(username, "", authorities);
		AbstractAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(principal, "",
				principal.getAuthorities());

		return authToken;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException
	{
		super.doFilter(req, res, chain);
	}
}
