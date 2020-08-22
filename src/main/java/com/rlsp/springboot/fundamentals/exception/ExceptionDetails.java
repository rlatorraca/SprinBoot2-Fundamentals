package com.rlsp.springboot.fundamentals.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ExceptionDetails {

	protected String title;
	protected int status;
	protected String details;
	protected LocalDateTime timestamp;
	protected String developerMessage;
	
}
