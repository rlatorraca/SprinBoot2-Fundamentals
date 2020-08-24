package com.rlsp.springboot.fundamentals.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import com.rlsp.springboot.fundamentals.exception.ExceptionDetails;
import com.rlsp.springboot.fundamentals.exception.ResourceNotFoundDetails;
import com.rlsp.springboot.fundamentals.exception.ResourceNotFoundException;
import com.rlsp.springboot.fundamentals.exception.ValidationExpectionDetails;

import lombok.extern.slf4j.Slf4j;

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
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

	/**
	 * Manuseia o erro quando o Recurso nao for encontrado
	 * @param resourceNotFoundExcpection
	 * @return
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResourceNotFoundDetails> handleResourceNotFoundExcpection(ResourceNotFoundException resourceNotFoundExcpection) {
		
		return new ResponseEntity<>(
					ResourceNotFoundDetails.builder()
						.timestamp(LocalDateTime.now())
						.status(HttpStatus.NOT_FOUND.value())
						.details(resourceNotFoundExcpection.getMessage())
						.title("Sorry ...  Resource not Found !")
						.developerMessage(resourceNotFoundExcpection.getClass().getCanonicalName())
						.build(), HttpStatus.NOT_FOUND);
	}
	
	/**
	 * MManuseio o erro de NAO RESPEITO as validacoes dos campos da clase
	 * @param methodArgumentNotValidException
	 * @return
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(" , "));
		String fieldsMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(" , "));
		
		
		return new ResponseEntity<>(
				ValidationExpectionDetails.builder()
						.timestamp(LocalDateTime.now())
						.status(HttpStatus.BAD_REQUEST.value())
						.details("Wrong fields is following")
						.title("Sorry ...  Field(s) validation Error !")
						.developerMessage(exception.getClass().getCanonicalName())
						.field(fields)
						.fieldMessage(fieldsMessages)
						.build(), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Faz a sobrescrita handleExceptionInternal (Erros Internos do Servidor) para o nosso padrao com messagens objetivas e curtas 
	 */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception exception, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionDetails exceptionDetails = ExceptionDetails.builder()
					.timestamp(LocalDateTime.now())
					.status(status.value())
					.details(exception.getMessage())
					.title(exception.getCause().getMessage())
					.developerMessage(exception.getClass().getCanonicalName())
					.build();
		
		return new ResponseEntity<>(exceptionDetails, headers, status);
	}
}
