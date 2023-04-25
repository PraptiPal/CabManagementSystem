package com.cab.book.com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cab.book.com.filter.JwtSecurityFilter;
import com.cab.book.com.security.JwtAuthenticationEntryPoint;
import com.cab.book.com.security.CustomUserDetailsService;
	
	@Configuration
	@EnableWebSecurity
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private CustomUserDetailsService myUserDetailsService;
		
		@Autowired
		private JwtSecurityFilter jwtSecurityFilter;
		
	    @Autowired JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	    
		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.
			csrf().disable()
			.authorizeHttpRequests()
			.antMatchers("/api/v1/auth/user-registration", "/api/v1/auth/driver-registration","/api/v1/auth/login","/api/v1/driver/deleteDriver/").permitAll()
			.antMatchers("/api/v1/booking/**","/api/v1/user/**").hasAnyRole("ROLE_USER")
			.antMatchers("/api/v1/driver/**").hasRole("ROLE_DRIVER")
			.anyRequest()
			.authenticated().and()
			.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilterBefore(jwtSecurityFilter,UsernamePasswordAuthenticationFilter.class);
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(myUserDetailsService).passwordEncoder(encoder());
		}
			
		@Bean
	    public BCryptPasswordEncoder encoder(){
	        return new BCryptPasswordEncoder();
	    }
		
		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
}
