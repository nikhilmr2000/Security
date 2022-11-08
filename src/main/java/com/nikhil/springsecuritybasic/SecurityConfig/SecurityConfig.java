package com.nikhil.springsecuritybasic.SecurityConfig;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig {
	
	/*CONTACT_API_URL : "/contact",
    LOGIN_API_URL : "/user",
    ACCOUNT_API_URL : "/myAccount",
    BALANCE_API_URL : "/myBalance",
    LOANS_API_URL : "/myLoans",
    CARDS_API_URL : "/myCards",
    NOTICES_API_URL : "/notices"*/
	
	@Bean
	SecurityFilterChain defaultFilterSecurityFilterChain(HttpSecurity http) throws Exception{
		 http.cors().configurationSource(new CorsConfigurationSource() {
	            @Override
	            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
	                CorsConfiguration config = new CorsConfiguration();
	                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
	                config.setAllowedMethods(Collections.singletonList("*"));
	                config.setAllowCredentials(true);
	                config.setAllowedHeaders(Collections.singletonList("*"));
	                config.setMaxAge(3600L);
	                return config;
	            }
	        }).and()
		.authorizeHttpRequests().antMatchers("/myAccount","/myBalance","/myLoans","/myCards","/user").authenticated()
		.antMatchers("/contact","/register","/notices").permitAll()
		.and().formLogin()
		.and().httpBasic()
		.and().csrf().disable();
		
		
		/*http.authorizeHttpRequests()
		.anyRequest().permitAll()
		.and().formLogin()
		.and().httpBasic();*/
		
		return http.build();	
	}
	
	/*@Bean
	public InMemoryUserDetailsManager getUser() {
		UserDetails user=User.withDefaultPasswordEncoder()
				.username("nikhil")
				.password("nikhil")
				.authorities("user")
				.build();
		UserDetails admin=User.withDefaultPasswordEncoder()
				.username("admin")
				.password("admin")
				.authorities("admin")
				.build();
		return new InMemoryUserDetailsManager(user,admin);
	}*/
	
	/*@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails user=User.withUsername("nikhil")
				.password("nikhil")
				.authorities("user")
				.build();
		UserDetails admin=User.withUsername("admin")
				.password("admin")
				.authorities("admin")
				.build();
		
		return new InMemoryUserDetailsManager(user,admin);
	}*/
	
	//JDBC UserDetailsManager Implementation
	/*@Bean
	public UserDetailsService userDetailsService(DataSource datasource) {
		return new JdbcUserDetailsManager(datasource);
	}*/
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
