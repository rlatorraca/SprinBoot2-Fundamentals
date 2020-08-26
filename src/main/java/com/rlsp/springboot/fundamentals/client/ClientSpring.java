package com.rlsp.springboot.fundamentals.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.rlsp.springboot.fundamentals.domain.Anime;
import com.rlsp.springboot.fundamentals.wrapper.PageableResponseWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Esta classa sera um cliente que conecta a um servico de um servidor
 * @author rodrigo
 *
 */
@Slf4j
public class ClientSpring {
	
	public static void main(String[] args) {
		
		

//		tesGetWithRestTemplate();
		
		/**
		 * POST
		 */
		Anime newAnime = Anime.builder().name("Smurfs").build();
		Anime newAnimeSaved = new RestTemplate().postForObject("http://localhost:8080/animes", newAnime, Anime.class);
		log.info("New anime saved ID: {} " , newAnimeSaved.getId());
		
		
		
		Anime newAnime2 = Anime.builder().name("Peixe man").build();
		Anime newAnime2Saved = new RestTemplate().exchange("http://localhost:8080/animes", HttpMethod.POST, new HttpEntity<>(newAnime2) , Anime.class).getBody();
		log.info("New anime saved ID: {} " , newAnime2Saved.getId());
		
	
		/**
		 * PUT
		 */
		newAnime2.setName("Steins Gate Zero");

        ResponseEntity<Void> exchangeUpdated = new RestTemplate()
        			.exchange("http://localhost:8080/animes", HttpMethod.PUT,new HttpEntity<>(newAnime2, createJsonHeader()), Void.class);

        log.info("Anime updated status: {} ", exchangeUpdated.getStatusCode());

        /**
         * DELETE
         */
        ResponseEntity<Void> exchangeDeleted = new RestTemplate()
            .exchange("http://localhost:8080/animes/{id}", HttpMethod.DELETE, null, Void.class, newAnime2Saved.getId());

        log.info("Animedeleted status: {} ", exchangeDeleted.getStatusCode());
		
		
		/**
		 * Using Pageable (GET)
		 */
		ResponseEntity<PageableResponseWrapper<Anime>> animeResponseListPageable = new RestTemplate()
				.exchange("http://localhost:8080/animes/list?sort=name", HttpMethod.GET, null , new ParameterizedTypeReference<>() {});
		log.info("Anime List Pageable {}", animeResponseListPageable.getBody());
	}
	
	private static HttpHeaders createJsonHeader() {

		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.setContentType(MediaType.APPLICATION_JSON);
		//httpHeader.setBearerAuth(null);
		return httpHeader;
		
	}

	private static void tesGetWithRestTemplate() {
		
		/**
		 * Essa funcao RestaTemplate ira fazer com possamos usar os Jackoson para trabalharms com requisicoes JSON		 * 
		 */
		
		/**
		 * GET
		 */
		
		ResponseEntity<Anime> animeResponse = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class, 1);
		log.info("Response Entity {}", animeResponse);
		log.info("Response Entity {}", animeResponse.getBody());
		
		Anime animeResponseObj = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 1);
		log.info("Anime Objetct {}", animeResponseObj);
		
		Anime[] animeResponseArray = new RestTemplate().getForObject("http://localhost:8080/animes/list", Anime[].class);		
//		log.info("Anime Array {}", Arrays.toString(animeResponseArray));
		
		// As List (more powerfull)
//		ResponseEntity<List<Anime>> animeResponseList = new RestTemplate()
//				.exchange("http://localhost:8080/animes/list", HttpMethod.GET, null , new ParameterizedTypeReference<>() {});
//		log.info("Anime List {}", animeResponseList.getBody());
	}

}
