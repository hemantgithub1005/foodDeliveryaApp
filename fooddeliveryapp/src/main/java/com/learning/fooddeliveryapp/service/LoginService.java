package com.learning.fooddeliveryapp.service;

import org.springframework.stereotype.Service;

import com.learning.fooddeliveryapp.dto.Login;

@Service
public interface LoginService {
	
	public String authenticateUser(Login login);
	public String addCredentials(Login login);
	public String deleteCredentials(String email);

}
