package com.todoapp.security.jwt;

import java.security.SignatureException;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.todoapp.common.SystemConstant;
import com.todoapp.security.TodoappUserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
	
	private String jwtSecret = "SECRET_KEY";

	private int jwtExpirationMs = 60 * 60 * 60;

	public String generateJwtToken(TodoappUserDetails userPrincipal) {
		return generateTokenFromUsername(userPrincipal.getUsername());
	}

	public String generateTokenFromUsername(String username) {
		return Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public String getUserNameFromJwtToken(String token) {
		String user;
		try {
			user = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token.replace(SystemConstant.TOKEN_PREFIX, "")).getBody().getSubject();
			return user;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	public boolean validateJwtToken(String authToken) throws SignatureException {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException e) {
			System.out.println("Invalid JWT token: {}" + e.getMessage());
		} catch (ExpiredJwtException e) {
			System.out.println("JWT token is expired: {}" + e.getMessage());
		} catch (UnsupportedJwtException e) {
			System.out.println("JWT token is unsupported: {}" + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("JWT claims string is empty: {}" + e.getMessage());
		}

		return false;
	}
}
