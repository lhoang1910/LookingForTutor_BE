package com.ltf.userservice.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ltf.userservice.service.JwtService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {

	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

	@Override
	public void validateToken(String token) {
		// TODO Auto-generated method stub
		Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
	}

	@Override
	public String generateToken(String userName, String role) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userName, role);
	}

	@Override
	public String createToken(Map<String, Object> claims, String userName, String role) {
		return Jwts.builder().setClaims(claims).setSubject(userName).claim("role", role)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	@Override
	public Key getSignKey() {
		// TODO Auto-generated method stub
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
