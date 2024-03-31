package io.spring.security.oauth2.oauth2login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(
							request -> {
								request.requestMatchers("/").permitAll();
								request.anyRequest().authenticated();
							})
				.oauth2Login(Customizer.withDefaults())
//				.formLogin(Customizer.withDefaults())
				.exceptionHandling(Customizer.withDefaults())
				.build();
	}

}
