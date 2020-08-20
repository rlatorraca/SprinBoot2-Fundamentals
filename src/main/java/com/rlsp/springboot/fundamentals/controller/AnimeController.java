package com.rlsp.springboot.fundamentals.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rlsp.springboot.fundamentals.domain.Anime;
import com.rlsp.springboot.fundamentals.repository.AnimeRepository;
import com.rlsp.springboot.fundamentals.util.Utils;

import lombok.extern.slf4j.Slf4j;

/**
* E um RECURSO para API
* 
* Quando se adiciona @RestContoller é como se configurarmos a aplicacao com @ResponseBody (adicionado para todos os metodos da classe)
* 
* @ResponseBody => anotacao que diz que o resultado retornara um JSON, sem trabalhar com VIEW
* 
* OBS: se configurarmos com  @Contoller deve-se incluir @ResponseBody, pois caso contrario retornara uma VIEW
* 
* @RequestMapping => apresenta a URL para acesso (no caso, ex: localhost/student
* 
* @author rlatorraca
*
*/


@RestController
@RequestMapping("animes") //sempre no PLURAL o requestMapping , e o padrao e pegar o unico metodo GET
@Slf4j
public class AnimeController {

	private static final Logger logger = LoggerFactory.getLogger(AnimeController.class);
	
	 // Faz a injecao de DEPENDENCIA, instanciando o Objeto
	@Autowired
	private Utils dateUtil;
	
	@Autowired
	private AnimeRepository animeRepository;
	
	
	/**
	 * Usado para retorna seu STATUS CODE (um codigo de resposta da requisicao)
	 * @return
	 */
	@GetMapping("/list")
	private ResponseEntity<List<Anime>> listAll(){
		logger.info("Date formatted {}", dateUtil.formatLocalDateTimeToDBStyle(LocalDateTime.now()));
		return new ResponseEntity<>(animeRepository.listAll(), HttpStatus.OK); // Retorna a lista de Student e o Status da resposta HTTP
		//return ResponseEntity.ok(animeRepository.listAll()); => tera o mesmo resultado que o de cima
	}
	
	/**
	 * Metodo para buscar um valor da lista de Students
	 * 
	 * @PathVariable => usado para pegar o ID da assinatura da requisicao
	 */
	@GetMapping(path ="/{id}")
	private ResponseEntity<?> getAnimeBydId(@PathVariable("id") int id){
		 
		return ResponseEntity.ok(animeRepository.findById(id));
	}
	
	/**
	 * Exsitem 4 modos para retorna um POST
	 * 1) Retornando 201 Created
	 * 2) Retornando apenas o ID
	 * 3) Retornando o objeto criado (inteiro)
	 * 4) Retornando o padrao HATEOS
	 */
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Anime anime){
		
		return ResponseEntity.ok(animeRepository.save(anime));
	}
	
	@DeleteMapping(path ="/{id}")
	private ResponseEntity<?> deleteAnimeBydId(@PathVariable("id") int id){
	
		animeRepository.delete(id);	
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No_Content is a success response + don,t return any information
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Anime anime){
		animeRepository.update(anime);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}


