package com.rlsp.springboot.fundamentals.wrapper;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageableResponseWrapper<T> extends PageImpl<T>{
	

	private static final long serialVersionUID = -3899108646798331236L;

	private boolean first;
	private boolean last;
	private int totalPages;
	private int numberOfElements;
	
	@JsonCreator(mode = Mode.PROPERTIES)
	public PageableResponseWrapper(
				@JsonProperty("content") List<T> content, 
				@JsonProperty("number") int number,
				@JsonProperty("size") int size,
				@JsonProperty("totalElements") int totalElements,
				@JsonProperty("last") boolean last,
				@JsonProperty("first") boolean first,
				@JsonProperty("totalPages") int totalPages,
				@JsonProperty("numberOfElemento") int numberOfElemento,				
				@JsonProperty("pageable")  JsonNode pageable, 
				@JsonProperty("sort")  JsonNode sort) {
		super(content, PageRequest.of(number, size), totalElements);
		this.last = last;
		this.first = first;
		this.totalPages = totalPages;
		
	
	}
	
	
	

}
