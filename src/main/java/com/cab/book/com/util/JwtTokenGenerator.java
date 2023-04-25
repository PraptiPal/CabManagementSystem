package com.cab.book.com.util;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenGenerator {
	
	private String secret = "secret";
	// Generating token
	
	//generate token for user
		public String generateToken(UserDetails userDetails) {
			Map<String,Object> claims = new HashMap<>();
			return doGenerateToken(claims, userDetails.getUsername());
		}
		
		private String doGenerateToken(Map<String,Object> claims, String subject) {
			
			return Jwts.builder().setClaims(claims)
					.setSubject(subject)
					.setIssuer("PraptiPal")
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(55)))
					.signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(secret.getBytes()))
					.compact();
		}
		
		
		
//		public String generateToken(String subject) {
//			return Jwts.builder()
//					.setSubject(subject)
//					.setIssuer("PraptiPal")
//					.setIssuedAt(new Date(System.currentTimeMillis()))
//					.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)))
//					.signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(secret.getBytes())) // signature algo and encoded secret key
//					.compact(); // compact returns the token in string format
//		}
		
		//Reading claims
		public Claims getClaims(String token) {
			return Jwts.parser()
					.setSigningKey(Base64.getEncoder().encode(secret.getBytes()))
					.parseClaimsJws(token).getBody();
			}
		
		
		// read expiry date from a given token
		public Date getExpirationDate(String token) {
			return getClaims(token).getExpiration();
		}
		
		// read subject/username
		public String getUsername(String token) {
			return getClaims(token).getSubject();
		}
		
		//validate token expiration date
		public boolean isTokenExpired(String token) {
			Date expiredDate = getExpirationDate(token);
			return expiredDate.before(new Date(System.currentTimeMillis()));
		}
		
		// validate username in token
		public boolean validateToken(String token,String username) {
			String tokenUsername = getUsername(token);
			return (username.equals(tokenUsername) && !isTokenExpired(token));
		}
}
