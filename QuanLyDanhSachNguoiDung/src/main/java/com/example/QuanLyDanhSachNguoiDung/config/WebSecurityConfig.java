//package com.example.QuanLyDanhSachNguoiDung.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setPasswordEncoder(passwordEncoder());
//		authProvider.setUserDetailsService(userDetailsService());
//		return authProvider;
//	}
//
//	@Bean
//	public JwtAuthenticationFilter authenticationFilter() {
//		return new JwtAuthenticationFilter();
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().antMatchers("/validate", "/register",
//				"/getAllUsers", "/getAllRoles", "/getAllUnits", "/findUserById/**").permitAll().anyRequest()
//				.authenticated();
//		http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//	}
//
//}
