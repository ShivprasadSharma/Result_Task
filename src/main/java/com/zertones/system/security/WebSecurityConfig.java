package com.zertones.system.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.zertones.service.UserDetailsServiceImpl;

/**
 * @author Zerton Team
 * @Created Date : Oct 26, 2015
 */
@Configuration
@EnableWebSecurity
@Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

	@Autowired
	private UserDetailsServiceImpl				userDetailsServiceImpl;
	@Autowired
	private CustomTokenBasedRememberMeService	tokenBasedRememberMeService;
	@Autowired
	private RememberMeAuthenticationProvider	rememberMeAuthenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable().authorizeRequests().antMatchers("/resources/**").permitAll().antMatchers("/static/**")
				.permitAll().antMatchers("/sign-up").permitAll().antMatchers("/sign-in").permitAll().anyRequest()
				.authenticated().and().formLogin().loginPage("/").loginProcessingUrl("/loginprocess")
				.failureUrl("/mobile/app/sign-in?loginFailure=true").permitAll().and().rememberMe()
				.rememberMeServices(tokenBasedRememberMeService);
	}

	@Autowired
	protected void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception
	{
		// auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder());
		auth.authenticationProvider(rememberMeAuthenticationProvider);
	}

	@Bean(name = "appAuthenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}