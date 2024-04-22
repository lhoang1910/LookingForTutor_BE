package com.ltf.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import com.ltf.apigateway.util.JwtUtil;

import reactor.core.publisher.Mono;

@Component
public class RoleCheckFilter {

	@Autowired
	private JwtUtil jwtUtil;

	public Mono<Void> checkRole(ServerWebExchange exchange) {
		ServerHttpRequest request = exchange.getRequest();
		String path = request.getURI().getPath();

		String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

		if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
			token = token.substring(7);
			String role = jwtUtil.extractRole(token);

			System.out.println(role);

			if (role != null) {
				boolean allowAccess = false;

				switch (role) {
					case "ROLE_ADMIN":
						allowAccess = true;
						break;
					case "ROLE_TUTOR":
						allowAccess = path.startsWith("/api/auth") ||
								path.startsWith("/api/user") ||
								path.startsWith("/api/tutor");
						break;
					case "ROLE_STUDENT":
						allowAccess = path.startsWith("/api/auth") ||
								path.startsWith("/api/user") ||
								path.startsWith("/api/student");
						break;
					default:
						break;
				}

				if (!allowAccess) {
					exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
					return exchange.getResponse().setComplete();
				}
			}
		}

		exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

		return exchange.getResponse().setComplete();
	}

}
