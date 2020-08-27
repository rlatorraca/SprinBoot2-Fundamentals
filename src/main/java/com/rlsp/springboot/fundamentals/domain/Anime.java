package com.rlsp.springboot.fundamentals.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "Anime's name", example = "Shitori Iami", required = true)
	private String name;
	
    

}
