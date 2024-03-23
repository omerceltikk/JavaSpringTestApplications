package com.example.demo_v6.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	@Value("${demov5app.app.secret}")
	private String APP_SECRET;
	
	@Value("${demov5app.expires.in}")
	private long EXPIRES_IN;
	
	public String generateJwtToken(Authentication auth) {
	    try {
	        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();
	        Date expireDate = new Date(System.currentTimeMillis() + EXPIRES_IN);
	        
	        return Jwts.builder()
	                .setSubject(Long.toString(userDetails.getId()))
	                .setIssuedAt(new Date())
	                .setExpiration(expireDate)
	                .signWith(SignatureAlgorithm.HS512, APP_SECRET)
	                .compact();
	    } catch (Exception e) {
	        // Log the error
	        e.printStackTrace();
	        System.out.println(e);
	        return null; // or throw custom exception
	    }
	}
	
	public String generateJwtTokenByUserId(Long long1) {
		Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
		return Jwts.builder().setSubject(Long.toString(long1))
				.setIssuedAt(new Date()).setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
	}
	
	Integer getUserIdFromJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
		return  Integer.parseInt(claims.getSubject()) ;
	}
	
	boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
			return !isTokenExpired(token);
		} catch (SignatureException e) {
            return false;
        } catch (MalformedJwtException e) {
            return false;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (UnsupportedJwtException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
	}

	private boolean isTokenExpired(String token) {
		Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
		return expiration.before(new Date());
	}

}