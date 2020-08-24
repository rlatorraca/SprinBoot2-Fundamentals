package com.rlsp.springboot.fundamentals.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Fara a gestao das acessos quando nao acharmos um RECURSO MAPEADO  em estado UP
 * @author rlatorraca
 *
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {


	private static final long serialVersionUID = -8390577476056760801L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
