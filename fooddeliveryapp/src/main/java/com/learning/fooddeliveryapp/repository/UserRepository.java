package com.learning.fooddeliveryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.fooddeliveryapp.dto.Register;

public interface UserRepository extends JpaRepository<Register,Integer> {

	boolean existsByEmail(String email);
	
}
