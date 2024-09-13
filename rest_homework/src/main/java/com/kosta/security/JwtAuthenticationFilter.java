package com.kosta.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtProvider jwtProvider;
	private final static String HEADER_AUTHORIZATION = "Authorization";
	private final static String TOKEN_PREFIX = "Bearer ";
	
	// HTTP 요청이 들어올 때마다 실행되는 필터
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//요청해서 변수에 이름 맞는 헤더 가져와서 박고
		String header = request.getHeader(HEADER_AUTHORIZATION);
		//헤더에 있는 토큰 가져와서 박고
		String token = getAccessToken(header);
		//토큰이 유효한지 확인
		if(token != null && jwtProvider.validateToken(token)) {
			Authentication authentication = jwtProvider.getAuthenticationByToken(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
		
	}
	//헤더에서 토큰 가져오는 메소드
	private String getAccessToken(String header) {
		log.info("[getAccessToken] 토큰 값 추출, {}", header);
		
		//만약 헤더가 null 이 아니면서 TOKEN_PREFIX로 시작하면 _ 빼고 가져와 
		if(header != null && header.startsWith(TOKEN_PREFIX)) {
			return header.substring(TOKEN_PREFIX.length());
					
		}
		//아니면 null 반환
		return null;
	}
	
}
