package com.brainworksexams.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(users());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService users() {
		UserDetails u1 = User.withUsername("user").password("$2a$10$OXmVVEItkLcCdJIwR3iXGewnYpreeK1qtxfrZsaESO//SYkMaX20y").roles("USER", "ADMIN").build();
		UserDetails u2 = User.withUsername("aabhijeett").password("$2a$10$OXmVVEItkLcCdJIwR3iXGewnYpreeK1qtxfrZsaESO//SYkMaX20y").roles("USER", "ADMIN").build();
		return new InMemoryUserDetailsManager(u1, u2);
	}
	
	
	// http://exams.brainworks.com			// frontend app domain 
	// http://exams-api.brainworks.com		// rest apis 
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.setAllowedOrigins(Arrays.asList("http://exams.brainworks.com", "http://localhost:3000"));
        config.setAllowedMethods(Arrays.asList("*"));
        source.registerCorsConfiguration("/**", config);
        return source;
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().configurationSource(corsConfigurationSource())
			.and().authorizeRequests()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/index").permitAll()
			.antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
			.anyRequest()
	        .authenticated()
	        .and()
	        .httpBasic();
	}
}
