package com.employeemanagement.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {
	
	//private static final String SECRET_KEY = "mysecretkeymysecretkeymysecretkeymysecretkey";
	
	@Value("${jwt.secretkey}")
	private String secretKey;
	
	@Value("${jwt.expiration}")
	private long jwtExpiration;
	
	
	
	private SecretKey getSigningKey() {
	//	byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	public String generateToken(String userName) {
		return Jwts.builder()
				.subject(userName)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + jwtExpiration))
				.signWith(getSigningKey())
				.compact();
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				   .verifyWith(getSigningKey())
				   .build()
				   .parseSignedClaims(token)
				   .getPayload();
	}
	
//	public String extractUsername(String token) {
//	    return extractAllClaims(token).getSubject();
//	}
	
	/**
     * Generic method to extract any claim
     */
    public <T> T extractClaim(String token,
                              Function<Claims, T> claimsResolver) {

        Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    /**
     * Extract Username (sub claim)
     */
    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }
    
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
    }
    
    public boolean isTokenValid(String token, String userName) {
    	String extractedUserName = extractUsername(token);
    	
    	return extractedUserName.equals(userName) && !isTokenExpired(token);
    }


}
