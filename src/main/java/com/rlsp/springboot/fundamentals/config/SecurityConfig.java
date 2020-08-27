package com.rlsp.springboot.fundamentals.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rlsp.springboot.fundamentals.service.LoginUserDetailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@EnableWebSecurity
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true) 
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LoginUserDetailsService loginUserDetailsService; 

	// passawor para o usuarioem memoria
	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    PasswordEncoder passEncoder = passwordEncoderMethod();
		auth.inMemoryAuthentication()
			.withUser("rlsp")
			.password(passEncoder.encode("123"))
			.roles("USER")
			.and()
			.withUser("rodrigo")
			.password(passEncoder.encode("1234"))
			.roles("USER", "ADMIN");  
		
		log.info("Password encryptation testing {}", passEncoder.encode("teste"));
		auth.userDetailsService(loginUserDetailsService).passwordEncoder(passEncoder);
			
	}
	  

	  @Bean
	  public PasswordEncoder passwordEncoderMethod() {
			return new BCryptPasswordEncoder(12);
	   }

	
	/*
	 * Fitlros de Seguranca do Spring
	 * - BasicAuthenticantionFilter : procura o header do pedido Login and password
	 * - PasswordusernamePasswordAuthenticationFilter : tentar achar username e pasword no corpo do Body
	 * - DefaultLoginPageGeneratingFilter : gera a pagina de Login
	 * - DefaultLogoutPageGeneratingFilter : gera apagina de Logout
	 * - FilterSecurityInterceptor : onde vc faz a autorizacao (Authenticantion ->dd Authorization)
	 */
	// Segurana para todos endpoints
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
			.disable()
//			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//			.and()
			.authorizeRequests()
				.antMatchers("/animes/admin/**").hasAuthority("ADMIN")
				.antMatchers("/animes/**").hasAuthority("USER")
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
			.and()
				.httpBasic();
	}
	
	

}
