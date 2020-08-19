package com.rlsp.springboot.fundamentals.domain;

import lombok.AllArgsConstructor;
import lombok.Data;;

@Data // Gera GETTERS and SETTERS, HASHCODE and EQUALS
@AllArgsConstructor // gera construtor com todos os atrinburos
public class Anime {

    private int id;
    private String name;

}
