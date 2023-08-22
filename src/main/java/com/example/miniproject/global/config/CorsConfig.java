package com.example.miniproject.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("https://hmteresting.netlify.app");
		config.addAllowedMethod("*");
		config.addExposedHeader(HttpHeaders.SET_COOKIE);

		source.registerCorsConfiguration("/**", config);

		// Cors 전체 허용으로 변경
		return new CorsFilter(source);
	}

}
