package com.example.miniproject.domain.admin.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class AdminRequestDto {
	@Getter
	public static class ApplyDto {
		@NotNull
		private Long id;
	}
}
