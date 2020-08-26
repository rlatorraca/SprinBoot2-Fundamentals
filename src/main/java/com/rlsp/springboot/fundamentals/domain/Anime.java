package com.rlsp.springboot.fundamentals.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;;

// Gera GETTERS and SETTERS, HASHCODE and EQUALS
@AllArgsConstructor // gera construtor com todos os atrinburos
@Data
@NoArgsConstructor
@Entity
@Builder
public class Anime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@NotNull
	@NotEmpty(message = "The name can't be empty")
	private String name;
	
    

}
