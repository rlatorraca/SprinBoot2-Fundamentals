package com.rlsp.springboot.fundamentals;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.micrometer.core.instrument.MeterRegistry;
/**
 * Inicia a aplicacao WEB no SpringBoot2
 * @author rlatorraca
 * 
 * @SpringBootApplication ==> siginifica @EnableAutoConfiguration + @ComponentScan +  @Configuration
 * 
 * @EnableAutoConfiguration // Diz o que precisa ser configurado com base no POM.XML (nao precisaria ter, pos ja esta incluseo sno SpringBootApplication)
 * @ComponentScan // Scaneia os Beans que iram ser responsaveis pelas requisicoes (chamadas)
 * @Configuration //Usado para buscar as clases de configuracao
 *
 */

//@EnableAutoConfiguration
//@ComponentScan(basePackages = "com.rlsp.springboot.fundamentals.endpoint")
@SpringBootApplication
public class RlspSpringbootFundamentalsApplication {

	//inicializa o Spring Boot
	public static void main(String[] args) {
		SpringApplication.run(RlspSpringbootFundamentalsApplication.class, args); //Inicializa o SPRINGBOOT e Aplication
		
	}
	
	//Grafana
    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(
        @Value("${spring.application.name}") String applicationName) {
        return registry -> registry.config().commonTags("application", applicationName);
    }

}
