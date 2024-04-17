package com.ltf.apigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {
	public static final List<String> openApiEndpoints = Arrays.asList(
			"/api/auth/signup", 
			"/api/auth/signin",
			"/api/auth/verify", 
			"/api/auth/send-token", 
			"/api/auth/forget-password",
			"/api/eureka", 
			"/api/student/add",
			"/api/class/all"
			);

	public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream()
			.noneMatch(uri -> request.getURI().getPath().equals(uri));
}
