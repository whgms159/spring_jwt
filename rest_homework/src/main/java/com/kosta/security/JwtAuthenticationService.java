package com.kosta.security;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.kosta.domain.LoginResponse;
import com.kosta.entity.User;
import com.kosta.repository.UserRepository;
import com.kosta.utils.TokenUtils;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class JwtAuthenticationService {
	
	private final TokenUtils tokenUtils;
	private final UserRepository userRepository;
	
	void successAuthentication(HttpServletResponse response, Authentication authResult) throws IOException {
		//인증된 사용자 정보 가져오기
		User user = (User)authResult.getPrincipal();
		//사용자 정보로 토큰 생성
		Map<String, String> tokenMap = tokenUtils.generateToken(user);
		//변수에 토큰 넣어주기
		String accessToken = tokenMap.get("accessToken");
		String refreshToken = tokenMap.get("refreshToken");
		
		//리프레시 토큰을 DB에 저장
		user.setRefreshToken(refreshToken);
		userRepository.save(user);
		
		//생성된 리프레시 토큰을 cookie 에 담아 응답
		tokenUtils.setRefreshTokenCookie(response, refreshToken);
		
		//생성된 액세스 토큰을 LoginRepsonse에 담아 응답
		LoginResponse loginResponse = LoginResponse.builder().accessToken(accessToken).build();
		tokenUtils.writeResponse(response, loginResponse);
	}
}
