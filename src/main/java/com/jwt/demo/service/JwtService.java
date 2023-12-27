package com.jwt.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import com.jwt.demo.config.ConfigUtils;
import io.jsonwebtoken.JwtParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Autowired
	private ConfigUtils configUtils;

	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(configUtils.getProperty("myapp.security.secret")));
	}

	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		Date tokenCretionDate = new Date(System.currentTimeMillis());
		Date tokenValidity = new Date(System.currentTimeMillis() + Long.parseLong(configUtils.getProperty("myapp.security.access_validity")) * 60 * 1000);
        return Jwts.builder()
				.claims(claims)
				.subject(username)
				.issuedAt(tokenCretionDate)
				.expiration(tokenValidity)
				.signWith(getSigningKey())
				.compact();
	}

	public boolean isValidToken(String token,UserDetails userDetails){
		return extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	public String extractUsername(String token){
		return extractClaim(token, Claims::getSubject);
	}

	private boolean isTokenExpired(String token){
		return extractClaim(token, Claims::getExpiration).before(new Date(System.currentTimeMillis()));
	}


	private <T> T extractClaim(String token,Function<Claims,T> claimResolver){
		Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token){
		return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
	}

}
