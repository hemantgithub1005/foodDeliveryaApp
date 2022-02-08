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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.fooddeliveryapp.dto.Register;
import com.learning.fooddeliveryapp.exception.AlreadyExistsException;
import com.learning.fooddeliveryapp.exception.IdNotFoundException;
import com.learning.fooddeliveryapp.service.UserService;



@RestController

@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody Register register) throws AlreadyExistsException {
		
		Register result = userService.addUser(register);
		return ResponseEntity.status(201).body(result);
			
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUsersById(@PathVariable("id") Integer id) throws IdNotFoundException{
		Register result = userService.getUserById(id);
		return ResponseEntity.status(201).body(result);
		
	}
	
	
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllUsers() 
	{
		Optional<List<Register>> optional = userService.getAllUsers();
		
		if(optional.isEmpty()) {
			Map<String, String> map = new HashMap<>();
			map.put("message", "no records found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(optional.get());
		
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") Integer id,@RequestBody Register register) throws IdNotFoundException 
	{
		String reg=userService.updateUser(id,register);
		
		return ResponseEntity.status(201).body(reg);
		
		
	}
	
}