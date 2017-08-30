package com.basic.springsecurity;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select username,password,true from tuser where username=?")
			.authoritiesByUsernameQuery("select username, 'ROLE_USER' from tuser where username=?")
			.passwordEncoder(new Md5PasswordEncoder());
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  
	  http.authorizeRequests()
	  	.antMatchers("/", "/home").permitAll()
	  	.antMatchers("/admin/**").access("hasRole('ADMIN')")
	  	.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
	  	.and().formLogin().loginPage("/login")
	  	.usernameParameter("ssoId").passwordParameter("password")
	  	.and().csrf()
	  	.and().exceptionHandling().accessDeniedPage("/Access_Denied");
	}
}
