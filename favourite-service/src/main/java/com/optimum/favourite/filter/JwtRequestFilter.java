package com.optimum.favourite.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.GenericFilterBean;

import com.optimum.favourite.model.User;

public class JwtRequestFilter  extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		final String bearerToken = req.getHeader("Authorization");
		
		if(!ObjectUtils.isEmpty(bearerToken)) {
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Authorization", bearerToken);
				
				HttpEntity<String> httpEntity = new HttpEntity<>("body", headers);
				
				RestTemplate restTemplate = new RestTemplate();
				
				ResponseEntity<User> userResponse = restTemplate.exchange("http://localhost:8081/api/v1/user/authorize", HttpMethod.GET, httpEntity, User.class);
				
				if(!ObjectUtils.isEmpty(userResponse) && !ObjectUtils.isEmpty(userResponse.getBody())) {
					User user = userResponse.getBody();
					
					SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole());
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							user, null, Arrays.asList(authority));
					usernamePasswordAuthenticationToken
					.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			} catch (HttpClientErrorException ex) {
				res.setStatus(HttpStatus.UNAUTHORIZED.value());
				return;
			}
		} else {
			res.setStatus(HttpStatus.UNAUTHORIZED.value());
			return;
		}
		filterChain.doFilter(request, response);
	}

}