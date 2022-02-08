package com.learning.fooddeliveryapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.fooddeliveryapp.dto.Food;
import com.learning.fooddeliveryapp.dto.Register;
import com.learning.fooddeliveryapp.exception.AlreadyExistsException;
import com.learning.fooddeliveryapp.exception.IdNotFoundException;
import com.learning.fooddeliveryapp.service.FoodService;
import com.learning.fooddeliveryapp.service.UserService;



@RestController

@RequestMapping("/food")
public class FoodController {

	@Autowired
	FoodService foodService;
	
	
	@PostMapping("/addFood")
	public ResponseEntity<?> addFood(@RequestBody Food food) throws AlreadyExistsException {
		
		Food result = foodService.addFood(food);
		return ResponseEntity.status(201).body(result);
			
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getFoodById(@PathVariable("id") Integer id) throws IdNotFoundException{
		Food result = foodService.getFoodById(id);
		return ResponseEntity.status(201).body(result);
		
	}
	
	
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllFood() 
	{
		Optional<List<Food>> optional = foodService.getAllFood();
		
		if(optional.isEmpty()) {
			Map<String, String> map = new HashMap<>();
			map.put("message", "no records found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(optional.get());
		
	}
	
}