package com.learning.fooddeliveryapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.fooddeliveryapp.dto.Login;

public interface LoginRepository extends JpaRepository<Login, String> 
{
	Boolean existsByEmail(String email );

	Optional<Login> findByEmail(String email);

}
