package com.aloespirita.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	 @Autowired
	 private AuthProvider authProvider;
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
		super.configure(auth);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests().antMatchers(HttpMethod.POST, "/login")
			.permitAll()
			.anyRequest().authenticated()
			.and()
			
			.addFilterBefore(new JwtLoginFilter("/login", authenticationManager()), 
					UsernamePasswordAuthenticationFilter.class)
			
			.addFilterBefore(new JwtAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
	}

}
