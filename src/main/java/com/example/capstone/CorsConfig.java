package com.example.capstone;
 
import org.springframework.context.annotation.Configuration;
 
import org.springframework.web.servlet.config.annotation.CorsRegistry;
 
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
 
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
@Configuration
 
@EnableWebMvc
 
public class CorsConfig implements WebMvcConfigurer {
 
	@Override
 
	public void addCorsMappings(CorsRegistry registry) {
 
		registry.addMapping("/**")
 
				.allowCredentials(true)
 
				.allowedOriginPatterns("*")
                .allowedMethods("OPTIONS", "GET", "HEAD", "PUT", "POST", "DELETE")
 
				.exposedHeaders("Authorization")
 
				.maxAge(3600);
 
	}
 
}