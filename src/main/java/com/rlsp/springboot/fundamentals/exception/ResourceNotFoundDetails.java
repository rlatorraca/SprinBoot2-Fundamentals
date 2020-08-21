package com.rlsp.springboot.fundamentals.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResourceNotFoundDetails {

	public String title;
	private int status;
	private String details;
	private LocalDateTime timestamp;
	private String developerMessage;
	
}
