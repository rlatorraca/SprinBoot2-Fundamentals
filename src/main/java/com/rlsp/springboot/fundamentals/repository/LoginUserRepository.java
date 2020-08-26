package com.rlsp.springboot.fundamentals.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rlsp.springboot.fundamentals.domain.LoginUser;

public interface LoginUserRepository extends JpaRepository<LoginUser, Integer>{

	LoginUser findByUsername(String username);
	
}
