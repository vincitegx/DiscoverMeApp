package com.discoverme.backend;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BackendApplication {
	private static String accessToken = "EAAXZCcYDYk7cBO9MVmtyiV7z8Jhb3kpVD0OIGqZB8jTUNBhrR80dQ1UwOqrKQXauJQ2Pzh3364MZAK1X4NoQarWp6Y6EZAugRh3eIbr9rScNTUVkXYafXffiUBrnyuAq84RbuRbGEoUuZAGARCAvyNnZBgxyjNvw5fNDAEIdnOZBghMZB7nQuAq03cacdAQLhx0ZA2aZA8ZCiWpbOBIh4WFFPMcLS5VRMLGZAcCGIYvs9ZAWgIvOmpmAzBEET9cIjjK4o2wZDZD";
//	public static final APIContext context = new APIContext(
//			"{EAAXZCcYDYk7cBO9MVmtyiV7z8Jhb3kpVD0OIGqZB8jTUNBhrR80dQ1UwOqrKQXauJQ2Pzh3364MZAK1X4NoQarWp6Y6EZAugRh3eIbr9rScNTUVkXYafXffiUBrnyuAq84RbuRbGEoUuZAGARCAvyNnZBgxyjNvw5fNDAEIdnOZBghMZB7nQuAq03cacdAQLhx0ZA2aZA8ZCiWpbOBIh4WFFPMcLS5VRMLGZAcCGIYvs9ZAWgIvOmpmAzBEET9cIjjK4o2wZDZD}",
//			"{a7d6d9d44204cd0cca390619cad6607b}"
//	);

	public static void main(String[] args) {

		FacebookClient client = new DefaultFacebookClient("EAAXZCcYDYk7cBO3avgC6VRmqAiQRVqZCNdtwZBj4sEZAYmEPUB0NDGeJXHwVnXm1uFZAeFbrU4QrPv5XRVlXVowvbWFZAN7OfBHmfgmVOSZC4lNxw9iRQU0PLti9HalxZBcXsZAqDtTKPVjVUK4DUF0FcZB1aheOGo0gFz3puw26NosGBIAuWnlI4FHwgucGGYg00pGCBk9fAhknsZCXgOxtZBqZA4GNgKnIA45pDApOA4bk2UQMyYgBKwhmEpEa6LOXm2AZDZD");
//		client.createClientWithAccessToken("EAAXZCcYDYk7cBO33PRsMxxPwcVG40VNOPUu8vV8O1ZB9rW53M4S9FFURPcUUYwOA9Vtta8N10fywvbtlQUUJT3RrsQ6aJw7QzMLfGZB2G4UYBXffy2ZCn6MDbvRM4lJ7LVgBM1yfdc8z5tbRk2ZAxO0Pw0ADZA3ohPqxDk2ZCU1ccaiZBZCuqQz5EZB4XP6VZAiePYVOuTMwhrmixlowvFugsgGS5O8MZAWZBNZAj3oYTi9G8HJRQxaFqXzPWJFkmU0uH7mCcZD");
		User user = client.fetchObject("me", User.class);
		System.out.println(user.getName());
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		corsConfiguration.setMaxAge(3600L);
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
