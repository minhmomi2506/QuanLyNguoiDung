package com.example.QuanLyDanhSachNguoiDung.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @date 2022-01-06 - CREATE NEW
 *
 * @author MinhHL
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setUserDetailsService(userDetailsService());
		return authProvider;
	}

	@Bean
	public JwtAuthenticationFilter authenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/validate", "/register", "/findUserById/**").permitAll()
				.antMatchers("/findUserById/**", "/editUser/**", "/deleteUser/**", "/getAllUnits", "/addUnit/**",
						"/findUnitById/**", "/editUnit/**", "/deleteUnit/**", "/getAllRoles")
				.hasRole("ADMIN").antMatchers("/getAllUsers").hasAnyRole("USER", "ADMIN").anyRequest().authenticated();
		http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.cors();
	}

}
