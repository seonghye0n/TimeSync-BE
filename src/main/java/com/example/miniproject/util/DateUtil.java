package com.example.miniproject.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
}
