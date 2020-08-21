package com.rlsp.springboot.fundamentals.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;;

// Gera GETTERS and SETTERS, HASHCODE and EQUALS
@AllArgsConstructor // gera construtor com todos os atrinburos
@Data
@Entity
public class Anime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
	
    public Anime() {
		super();
	}
    
    

}
