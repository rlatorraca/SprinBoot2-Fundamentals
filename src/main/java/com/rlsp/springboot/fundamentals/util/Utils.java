package com.rlsp.springboot.fundamentals.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.rlsp.springboot.fundamentals.domain.Anime;

/**
 * @Repository ==> especializacao de @Component para ser utulizada com DAO, faz com queas EXCECOES nao checadas 
 * 		serao traduzidas usando o Spring Data Access Exception
 * @Service ==> especializacao de @Component, mas sem adicionar qualquer funcionalidade, utilizada nas camadas de SERVICO
 * @Component  ==> Marca que a CLASSE sera SCANEADO pelo @ComponentScan (no classe de inicializacao)
 * 
 * @author rlatorraca
 *
 */
@Component // ==> Marca que a CLASSE sera SCANEADA pelo @ComponentScan (no classe de inicializacao)
public class Utils {

	public String formatLocalDateTimeToDBStyle (LocalDateTime localDateTime) {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
				
	}
	
	public Anime findAnimeOrThrowNotFound(int id, List<Anime> animesList) {
		
		return animesList.stream()
						 .filter(anime -> anime.getId() == id)
						 .findFirst()
						 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime Not Found"));
	}
}
