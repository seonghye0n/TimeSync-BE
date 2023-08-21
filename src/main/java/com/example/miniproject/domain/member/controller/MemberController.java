package com.example.miniproject.domain.member.controller;

import java.util.Map;

import com.example.miniproject.domain.member.dto.MemberRequestDto;
import com.example.miniproject.domain.member.dto.MemberResponseDto;

import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.miniproject.domain.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/register")
	public ResponseEntity<Object> register(
		@RequestBody @Valid MemberRequestDto.CreateMember memberRequestDto
	) {

		memberService.register(memberRequestDto);

		return ResponseEntity.ok()
			.body(MemberResponseDto.ResponseSuccess.builder()
				.status(HttpStatus.OK.value())
				.message("회원가입에 성공하였습니다.")
				.build());
	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(
		HttpServletRequest request,
		@RequestBody @Valid MemberRequestDto.LoginMember memberRequestDto
	) {

		Map<String, String> response = memberService.login(request, memberRequestDto);

		// Cookie 에 refreshToken 을 저장함 이때 key 값은 UUID.randomUUID() !!
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

	@PostMapping("/logout")
	public ResponseEntity<Object> logout(
		@CookieValue("refreshToken") String refreshTokenId,
		HttpServletRequest request, Authentication authentication
	) {

		memberService.logout(request, refreshTokenId, authentication);

		return ResponseEntity.ok()
			.body(MemberResponseDto.ResponseSuccess.builder()
				.status(HttpStatus.OK.value())
				.message("로그아웃에 성공하였습니다.")
				.build());
	}

	@GetMapping("/healthcheck")
	public ResponseEntity<?> healthCheck() {
		return ResponseEntity.ok().build();
	}

}
