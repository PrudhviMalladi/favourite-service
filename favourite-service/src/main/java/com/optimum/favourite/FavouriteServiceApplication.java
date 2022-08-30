package com.optimum.favourite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.optimum.favourite.filter.JwtRequestFilter;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class FavouriteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavouriteServiceApplication.class, args);
	}
	
	@Bean
    public FilterRegistrationBean<JwtRequestFilter> jwtFilter() {
        FilterRegistrationBean<JwtRequestFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setOrder(1);
       // filterRegistrationBean.addUrlPatterns("/favourite/*");
        filterRegistrationBean.setName("JWTFilter");
        filterRegistrationBean.setFilter(new JwtRequestFilter());
        return filterRegistrationBean;
    }

}
