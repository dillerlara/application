package br.com.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import br.com.application.config.JwtFilter;

import br.com.application.service.impl.TokenService;


@SpringBootApplication
public class SpringBootJwtApplication {
	


	@Autowired
	private TokenService tokenService;

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter(tokenService));
		registrationBean.addUrlPatterns("/secure/*");
	//	userProvider.findByMarinas_ActiveFalse().stream().forEach(BlackListAuthentication::controller);
		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtApplication.class, args);
	}
}
