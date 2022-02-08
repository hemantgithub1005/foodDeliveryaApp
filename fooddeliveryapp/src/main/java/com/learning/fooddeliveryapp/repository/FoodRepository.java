package com.learning.fooddeliveryapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.fooddeliveryapp.dto.Food;

public interface FoodRepository extends JpaRepository<Food, Integer> {
	
	boolean existsByFoodName(String foodName);

//	Optional<Food> findById(Integer id);

	void deleteById(Integer id);

	

	Optional<Food> findById(Integer id);

}
