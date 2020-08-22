package com.rlsp.springboot.fundamentals.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExpectionDetails extends ExceptionDetails{

	private String field;
	private String fieldMessage;
}
