package com.learning.fooddeliveryapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learning.fooddeliveryapp.dto.Register;
import com.learning.fooddeliveryapp.exception.AlreadyExistsException;
import com.learning.fooddeliveryapp.exception.IdNotFoundException;


@Service
public interface UserService 

{
	public Register addUser(Register register) throws AlreadyExistsException;
	public String updateUser(Integer id, Register register) throws IdNotFoundException;
	public Register getUserById(Integer id) throws IdNotFoundException ;
	public Optional<List<Register>> getAllUsers();
	public String deleteUserById(Integer id) throws IdNotFoundException;
	

}
