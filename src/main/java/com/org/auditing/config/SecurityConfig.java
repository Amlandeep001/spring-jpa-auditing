package com.org.auditing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{

	@Bean
	UserDetailsService userDetailsService()
	{
		UserDetails user1 = User.withUsername("javatechie")
				.password("{noop}password123") // Keep {noop} for plain text passwords
				.roles("USER")
				.build();

		UserDetails user2 = User.withUsername("admin")
				.password("{noop}admin123")
				.roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user1, user2);
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http
				.csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/items/add", "/items/update/**", "/items/delete/**")
						.authenticated()
						.anyRequest()
						.permitAll() // Allow all other endpoints
				)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}

}
