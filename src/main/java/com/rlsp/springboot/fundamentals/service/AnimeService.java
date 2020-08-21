package com.rlsp.springboot.fundamentals.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import com.rlsp.springboot.fundamentals.domain.Anime;
import com.rlsp.springboot.fundamentals.repository.AnimeRepository;
import com.rlsp.springboot.fundamentals.util.Utils;

import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class AnimeService {

	private final Utils util;
	private final AnimeRepository animeRepository;
	
		

	public List<Anime> listAll() {
		return animeRepository.findAll();
	}
	
	public Anime save(Anime anime) {
		return animeRepository.save(anime);
	}
	
	public void delete (int id) {
		animeRepository.delete(util.findAnimeOrThrowNotFound(id, animeRepository));
		
	}
	
	public Anime findById(int id) {
		return util.findAnimeOrThrowNotFound(id, animeRepository);
	}
	
	public void update (Anime anime) {
		animeRepository.save(anime);
		
	}
	
}
