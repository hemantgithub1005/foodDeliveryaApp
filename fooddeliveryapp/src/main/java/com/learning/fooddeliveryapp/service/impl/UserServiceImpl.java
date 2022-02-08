package com.learning.fooddeliveryapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.fooddeliveryapp.dto.Login;
import com.learning.fooddeliveryapp.dto.Register;
import com.learning.fooddeliveryapp.exception.AlreadyExistsException;
import com.learning.fooddeliveryapp.exception.IdNotFoundException;
import com.learning.fooddeliveryapp.repository.LoginRepository;
import com.learning.fooddeliveryapp.repository.UserRepository;
import com.learning.fooddeliveryapp.service.LoginService;
import com.learning.fooddeliveryapp.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private LoginService service;
	
	
	@Autowired
	private LoginRepository loginRepository;

	@Override
	@org.springframework.transaction.annotation.Transactional(rollbackFor=AlreadyExistsException.class)
	public Register addUser(Register register) throws AlreadyExistsException {
		// TODO Auto-generated method stub
	
		if(repository.existsByEmail(register.getEmail()) == true) {
			throw new AlreadyExistsException("record already exists");
		}
		Register register2 = repository.save(register);
		if (register2 != null) {
			Login login = new Login(register.getEmail(), register.getPassword(),register2);
			if(loginRepository.existsByEmail(register2.getEmail())) {
				throw new AlreadyExistsException("login already exists");
			}
			
			String result = service.addCredentials(login);
			if(result == "success") {
					return register2;
			}
			else
			{
				return null;
			}
		}	
		else {
			return null;
		}
				
	}

	@Override
	public String updateUser(Integer id, Register register) throws IdNotFoundException {
		// TODO Auto-generated method stub
		if(this.repository.existsById(id)==false)
		{
			return "fail";
		}
		return (this.repository.save(register)!=null)?"success":"fail";
		//we dont write here coz update is automatically taken care of
	}

	@Override
	public Register getUserById(Integer id) throws IdNotFoundException {
		// TODO Auto-generated method stub
	   Optional<Register>optional=repository.findById(id);
	   if(optional.isEmpty())
	   {
		   throw new IdNotFoundException("id not exist");
	   }
	   else
	   {
		return optional.get() ;
	   }
	}

	@Override
	public Optional<List<Register>> getAllUsers()
			 {
		// TODO Auto-generated method stub
		List<Register> list = repository.findAll();
		Register[] array = new Register[list.size()];
		return Optional.ofNullable(repository.findAll());
		
		
	}

	@Override
	public String deleteUserById(Integer id) throws IdNotFoundException {
		
			Register optional;
			try {
				optional = this.getUserById(id);
				if(optional==null) {
					throw new IdNotFoundException("record not found");
				}
				else {
					repository.deleteById(id);
					return "record deleted";
				}
			} catch (IdNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new IdNotFoundException(e.getMessage());
			}
		
	}
}


