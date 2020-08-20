package com.rlsp.springboot.fundamentals.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import com.rlsp.springboot.fundamentals.domain.Anime;
import com.rlsp.springboot.fundamentals.util.Utils;

import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class AnimeRepository {

	private final Utils util;
	
	private static List<Anime> animesList;
	
	static {
		animesList= new ArrayList<>(List.of(
				new Anime(1, "Jyraia"), 
				new Anime(2, "Jaspion"), 
				new Anime(3, "Naruto")));
	}

	public List<Anime> listAll() {
		return animesList;
	}
	
	public Anime save(Anime anime) {
		anime.setId(ThreadLocalRandom.current().nextInt(4, 1000));
		animesList.add(anime);
		return anime;
	}
	
	public void delete (int id) {
		animesList.remove(util.findAnimeOrThrowNotFound(id, animesList));
		
	}
	
	public Anime findById(int id) {
		return util.findAnimeOrThrowNotFound(id, animesList);
	}
	
	public void update (Anime anime) {
		animesList.remove(util.findAnimeOrThrowNotFound(anime.getId(), animesList));
		animesList.add(anime);
		
	}
	
}
