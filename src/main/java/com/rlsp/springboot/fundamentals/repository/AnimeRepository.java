package com.rlsp.springboot.fundamentals.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Repository;

import com.rlsp.springboot.fundamentals.domain.Anime;


@Repository
public class AnimeRepository {

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
	
}
