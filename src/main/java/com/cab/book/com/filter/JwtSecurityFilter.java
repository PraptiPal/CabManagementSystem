package com.cab.book.com.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cab.book.com.security.CustomUserDetailsService;
import com.cab.book.com.util.JwtTokenGenerator;


@Component
public class JwtSecurityFilter extends OncePerRequestFilter{

	@Autowired private JwtTokenGenerator jwtTokenGenerator;
	
	@Autowired private CustomUserDetailsService myUserDetailsService;
	
	//@Autowired private MyDriverDetailsService myDriverDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorizationHeader = request.getHeader("Authorization");
		
		String username = null;
		
		if(authorizationHeader != null ) {
			 username = jwtTokenGenerator.getUsername(authorizationHeader);
		} 
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
            
            //UserDetails driverDetails = myDriverDetailsService.loadUserByUsername(username); 
            

            if (jwtTokenGenerator.validateToken(authorizationHeader, userDetails.getUsername())) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
                			new UsernamePasswordAuthenticationToken(
                        username, userDetails.getPassword(), userDetails.getAuthorities());
                
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else {
            	System.out.println("Invalid Jwt token");
            }
		}
         else {
   		 System.out.println("Username is not null or context is not null");
        } 
		
		filterChain.doFilter(request, response);	
	}
}
