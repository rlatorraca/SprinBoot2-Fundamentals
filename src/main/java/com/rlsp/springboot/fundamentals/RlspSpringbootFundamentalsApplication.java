package com.rlsp.springboot.fundamentals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
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

	public static void main(String[] args) {
		SpringApplication.run(RlspSpringbootFundamentalsApplication.class, args); //Inicializa o SPRINGBOOT e Aplication
	}

}
