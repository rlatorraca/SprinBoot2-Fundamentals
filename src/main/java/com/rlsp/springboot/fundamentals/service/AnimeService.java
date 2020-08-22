package com.rlsp.springboot.fundamentals.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rlsp.springboot.fundamentals.domain.Anime;
import com.rlsp.springboot.fundamentals.repository.AnimeRepository;
import com.rlsp.springboot.fundamentals.util.Utils;

import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class AnimeService {

	private final Utils util;
	private final AnimeRepository animeRepository;
	
		

	public Page<Anime> listAll(Pageable pageable) {
		return animeRepository.findAll(pageable);
	}
	
	@Transactional
	public Anime save(Anime anime) {
		return animeRepository.save(anime);
	}
	
	public void delete (int id) {
		animeRepository.delete(util.findAnimeOrThrowNotFound(id, animeRepository));
		
	}
	
	public Anime findById(int id) {
		return util.findAnimeOrThrowNotFound(id, animeRepository);
	}
	
	public List<Anime> findByName(String name) {
		return animeRepository.findByName(name);
	}
	
	public void update (Anime anime) {
		animeRepository.save(anime);
		
	}
	
}
