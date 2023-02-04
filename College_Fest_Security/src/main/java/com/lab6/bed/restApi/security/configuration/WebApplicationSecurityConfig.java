package com.lab6.bed.restApi.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lab6.bed.restApi.security.services.DomainUserDetailsServices;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final DomainUserDetailsServices userDomainUserDetailsServices;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// user authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDomainUserDetailsServices).passwordEncoder(passwordEncoder());
	}

	// user authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login", "/h2-console/**", "/h2-console**", "/h2-console/login**")
				.permitAll()
				.antMatchers("/students/save/**", "/students/save**", "/students/addStudents", "/students/403")
				.hasAnyRole("USER", "ADMIN").antMatchers(HttpMethod.POST, "/students/save**").hasRole("ADMIN")
				.antMatchers("/students/updateStudent", "/students/delete").hasRole("ADMIN").anyRequest()
				.authenticated().and().formLogin().loginProcessingUrl("/login").successForwardUrl("/students/list")
				.permitAll().and().logout().logoutSuccessUrl("/login").permitAll().and().exceptionHandling()
				.accessDeniedPage("/students/403").and().cors().disable().csrf().disable().headers().frameOptions()
				.disable();
	}

}
