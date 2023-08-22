package com.example.miniproject.global.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.example.miniproject.global.constant.ErrorCode;
import com.example.miniproject.global.error.exception.DomainException;

public class DateUtil {
	public static String toDateFormat(LocalDate date) {
		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public static String toDateFormat(LocalDateTime dateTime) {
		return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public static int getDateDiff(LocalDateTime startDate, LocalDateTime endDate) {
		int diff = (int)Duration.between(startDate, endDate).toDays() + 1;

		return diff;
	}

	public static LocalDate parseLocalDate(String date) {
		LocalDate localDate;

		try {
			localDate = LocalDate.parse(date);
		} catch (DateTimeParseException e) {
			throw new DomainException(ErrorCode.DATE_PARSE_ERROR);
		}

		return localDate;
	}
}
