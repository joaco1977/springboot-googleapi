package com.asimplemodule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.asimplemodule.security.GoogleSecurityAuthenticationEntryPoint;
import com.asimplemodule.security.GoogleSecurityAuthenticationFilter;
import com.asimplemodule.security.GoogleSecurityAuthenticationProvider;
import com.asimplemodule.security.service.TimeoffUserDetailsService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(googleSecurityAuthenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.exceptionHandling().authenticationEntryPoint(
				googleSecurityEntryPoint());

		http.addFilterBefore(googleSecurityAuthenticationFilter(),
				AbstractPreAuthenticatedProcessingFilter.class);

		http.authorizeRequests().antMatchers("/").authenticated();
		//http.authorizeRequests().antMatchers("/*").permitAll();

	}
	
	@Bean
	public AuthenticationEntryPoint googleSecurityEntryPoint() {
		GoogleSecurityAuthenticationEntryPoint googleSecurityAuthenticationEntryPoint = new GoogleSecurityAuthenticationEntryPoint();

		return googleSecurityAuthenticationEntryPoint;
	}
	
	@Bean(name="googleAuthenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}
	
	@Bean
	public AuthenticationProvider googleSecurityAuthenticationProvider()
			throws Exception {
		GoogleSecurityAuthenticationProvider googleSecurityAuthenticationProvider = new GoogleSecurityAuthenticationProvider();
		googleSecurityAuthenticationProvider
				.setTimeoffUserDetailsService(timeoffUserDetailsService());
		
		return googleSecurityAuthenticationProvider;
	}

	
	
	@Bean
	public UserDetailsService timeoffUserDetailsService() {
		TimeoffUserDetailsService timeoffUserDetailsService = new TimeoffUserDetailsService();
		return timeoffUserDetailsService;
	}
	
	
	
	@Bean
	public GoogleSecurityAuthenticationFilter googleSecurityAuthenticationFilter()
			throws Exception {
		GoogleSecurityAuthenticationFilter googleSecurityAuthenticationFilter = new GoogleSecurityAuthenticationFilter();
		googleSecurityAuthenticationFilter
				.setAuthenticationManager(authenticationManagerBean());
		return googleSecurityAuthenticationFilter;
	}
}
