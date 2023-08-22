package com.example.miniproject.domain.admin.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class AdminRequestDto {
	@Getter
	public static class ApplyDto {
		@NotNull(message = "게시글 정보가 없습니다.")
		private Long id;
	}
}
