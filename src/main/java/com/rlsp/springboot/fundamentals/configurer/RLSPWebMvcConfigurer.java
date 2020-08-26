package com.rlsp.springboot.fundamentals.configurer;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RLSPWebMvcConfigurer implements WebMvcConfigurer{

	/**
	 * faz o set up a Global Configuratio (inicial) da paginacao por pageable
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		
		WebMvcConfigurer.super.addArgumentResolvers(resolvers);
		
		PageableHandlerMethodArgumentResolver pageHandler = new PageableHandlerMethodArgumentResolver();
		pageHandler.setFallbackPageable(PageRequest.of(0, 5)); // Seta qual sera a pagina 1 e o tamanho da pagina
		resolvers.add(pageHandler);
	}
	
	

}
