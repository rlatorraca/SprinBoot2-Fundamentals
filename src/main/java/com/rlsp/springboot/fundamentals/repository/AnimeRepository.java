package com.rlsp.springboot.fundamentals.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rlsp.springboot.fundamentals.domain.Anime;

public interface AnimeRepository extends JpaRepository<Anime, Integer>{

	List<Anime> findByName(String name);
}
