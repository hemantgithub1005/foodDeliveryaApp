package com.learning.fooddeliveryapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.fooddeliveryapp.dto.Food;
import com.learning.fooddeliveryapp.dto.FoodType;
import com.learning.fooddeliveryapp.service.FoodService;

@RestController

@RequestMapping("/api/request") 
public class FoodController {

	@Autowired
	FoodService foodService;
	
	@PostMapping("/food")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addFood(@Valid @RequestBody Food food){
		
		Food result = foodService.addFood(food);
		return ResponseEntity.status(201).body(result);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getFoodById(@PathVariable("id") Long id){
		
		Food result = foodService.getFoodById(id);
		return ResponseEntity.status(201).body(result);
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllFood(){
		Optional<List<Food>> optional = foodService.getAllFood();
		
		if(optional.isEmpty()) {
			Map<String, String> map = new HashMap<>();
			map.put("message", "no records found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(optional.get());
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateFood(@PathVariable("/id") Long id, @RequestBody Food food){
		
		return new ResponseEntity<>(foodService.updateFood(id, food),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteFood(@PathVariable("id") Long id){
		
		String result = foodService.deleteFoodById(id);
		
		if(result.equals("success")) {
			return new ResponseEntity<>(id, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/foodbytype")
	public ResponseEntity<?> getfooddetailsbyType(@RequestBody Food food){
		Optional<List<Food>> optional = foodService.getAllfoodbytypes(food.getFoodType());
		
		if(optional.isEmpty()) {
			Map<String,String> map = new HashMap<String, String>();
			map.put("message","no record found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		
		return ResponseEntity.of(optional);
	}

}
