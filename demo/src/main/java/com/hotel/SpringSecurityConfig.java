package com.hotel;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	// Create 2 users role
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
        auth.inMemoryAuthentication()
        .withUser("ReadOnly").password("{noop}ReadOnly").roles("ReadOnly")
        .and()
        .withUser("admin").password("{noop}admin").roles("ReadOnly", "ADMIN");
	}

	//Secure the end points with HTTP Basic authentication
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/hotels/**").hasRole("ReadOnly")
        .antMatchers(HttpMethod.POST, "/hotels").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/swagger-ui/**").hasRole("ReadOnly")
        .antMatchers(HttpMethod.GET, "/hotelFilter/**").hasRole("ReadOnly")
        .and()
        .csrf().disable()
        .formLogin().disable();
	}
}