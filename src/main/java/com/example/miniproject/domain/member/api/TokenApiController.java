package com.example.miniproject.domain.member.api;

import java.util.Map;

import com.example.miniproject.domain.member.dto.MemberResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.miniproject.global.jwt.service.TokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TokenApiController {

	private final TokenService tokenService;

	@PostMapping("/token")
	public ResponseEntity<Object> createNewAccessToken(
		@CookieValue("refreshToken") String refreshTokenId
	) {

		Map<String, String> response = tokenService.createNewTokens(refreshTokenId);

		ResponseCookie responseCookie = ResponseCookie.from("refreshToken", response.get("refreshTokenId"))
			.httpOnly(true)
			.secure(true)
			.path("/")
			.maxAge(60 * 60 * 24)
			.sameSite("None")
			.domain("hmteresting.netlify.app")
			.build();

		return ResponseEntity.ok()
			.header(HttpHeaders.SET_COOKIE, responseCookie.toString())
			.body(MemberResponseDto.ResponseAccessToken.builder()
				.accessToken(response.get("accessToken"))
				.role(response.get("role"))
				.build());
	}

}
