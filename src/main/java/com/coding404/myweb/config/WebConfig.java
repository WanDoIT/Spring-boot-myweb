package com.coding404.myweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.coding404.myweb.util.interceptor.MenuHandler;
import com.coding404.myweb.util.interceptor.UserAuthHandler;

//스프링 설정파일
@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Bean
	public UserAuthHandler userAuthHandler() {
		
		return new UserAuthHandler();
	}
	
	//포스트 핸들러
	@Bean
	public MenuHandler menuHandler() {
		
		return new MenuHandler();
	}
	
	//인터셉터 추가
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
//		registry.addInterceptor(userAuthHandler())
//				.addPathPatterns("/main")
//				.addPathPatterns("/product/*")
//				.addPathPatterns("/user/*")
//				.excludePathPatterns("/user/login")
//				.excludePathPatterns("/user/join");
		
//				.addPathPatterns("/**")
//				.excludePathPatterns("/user/login")
//				.excludePathPatterns("/user/join")
//				.excludePathPatterns("/js/*")
//				.excludePathPatterns("/css/*")
//				.excludePathPatterns("/img/*");
				//REST API 패스에서 제외...?
		
		registry.addInterceptor(menuHandler())
				.addPathPatterns("/main")
				.addPathPatterns("/product/*")
				.addPathPatterns("/user/*");
		
		
		
	}
	
}
