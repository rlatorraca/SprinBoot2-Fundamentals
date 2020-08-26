package com.rlsp.springboot.fundamentals.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.rlsp.springboot.fundamentals.repository.LoginUserRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LoginUserDetailsService implements UserDetailsService{

		@Autowired
		private LoginUserRepository loginUserRepository;

	  @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        return Optional.ofNullable(loginUserRepository.findByUsername(username))
	            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	    }
	  

}
