package com.rlsp.springboot.fundamentals.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	// passawor para o usuarioem memoria
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEnconder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		log.info("Password encryptation testing {}", passwordEnconder.encode("teste"));
		auth.inMemoryAuthentication()
			.withUser("rlsp")
			.password(passwordEnconder.encode("123"))
			.roles("USER")
			.and()
			.withUser("admin")
			.password(passwordEnconder.encode("1234"))
			.roles("USER", "ADMIN");
			
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
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
			.and()
				.httpBasic();
	}
	
	

}
