package com.rlsp.springboot.fundamentals.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rlsp.springboot.fundamentals.exception.ResourceNotFoundDetails;
import com.rlsp.springboot.fundamentals.exception.ResourceNotFoundExcpection;

/**
 * Por padrão, os métodos em um @ControllerAdvice se aplicam globalmente a todos os controladores. 
 * Use seletores como anotações, basePackageClasses e basePackages (ou seu valor de alias) para definir um subconjunto 
 * mais estreito de controladores direcionados. Se vários seletores forem declarados, a lógica booleana OR será aplicada, 
 * o que significa que os controladores selecionados devem corresponder a pelo menos um seletor. Observe que as verificações 
 * do seletor são realizadas no tempo de execução, portanto, adicionar muitos seletores pode afetar negativamente o desempenho 
 * e aumentar a complexidade.
 * Em resumo : sera o padrao usado todas que verificar a implementacao feito dentro dessa classe no caso em tela
 * 	um "exception handler|
 * @author RLATORRACA
 *
 */
@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ResourceNotFoundExcpection.class)
	public ResponseEntity<ResourceNotFoundDetails> handleResourceNotFoundExcpection(ResourceNotFoundExcpection resourceNotFoundExcpection) {
		
		return new ResponseEntity<ResourceNotFoundDetails>(
					ResourceNotFoundDetails.builder()
						.timestamp(LocalDateTime.now())
						.status(HttpStatus.NOT_FOUND.value())
						.details(resourceNotFoundExcpection.getMessage())
						.title("Sorry ...  Resource not Found !")
						.developerMessage(resourceNotFoundExcpection.getClass().getCanonicalName())
						.build(), HttpStatus.NOT_FOUND);
	}
}
