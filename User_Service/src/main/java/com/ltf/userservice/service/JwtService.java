package com.ltf.userservice.service;

import java.security.Key;
import java.util.Map;

public interface JwtService {
	void validateToken(final String token);
	String createToken(Map<String, Object> claims, String userName, String role);
	Key getSignKey();
	String generateToken(String userName, String role);
}
