package com.learning.fooddeliveryapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learning.fooddeliveryapp.dto.Food;
import com.learning.fooddeliveryapp.dto.Register;
import com.learning.fooddeliveryapp.exception.AlreadyExistsException;
import com.learning.fooddeliveryapp.exception.IdNotFoundException;

@Service
public interface FoodService {
	public Food addFood(Food food) throws AlreadyExistsException;
	public String updateFood(Integer id, Food food) throws IdNotFoundException;
	public Food getFoodById(Integer id) throws IdNotFoundException ;
	public Optional<List<Food>> getAllFood();
	public String deleteFoodById(Integer id) throws IdNotFoundException;
	

}
