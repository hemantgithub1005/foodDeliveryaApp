package com.learning.fooddeliveryapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.fooddeliveryapp.dto.Food;
import com.learning.fooddeliveryapp.dto.Login;
import com.learning.fooddeliveryapp.dto.Register;
import com.learning.fooddeliveryapp.exception.AlreadyExistsException;
import com.learning.fooddeliveryapp.exception.IdNotFoundException;
import com.learning.fooddeliveryapp.repository.FoodRepository;
import com.learning.fooddeliveryapp.repository.LoginRepository;
import com.learning.fooddeliveryapp.repository.UserRepository;
import com.learning.fooddeliveryapp.service.FoodService;
import com.learning.fooddeliveryapp.service.LoginService;

@Service
public class FoodServiceImpl implements FoodService {
	
	
	@Autowired
	private FoodRepository repository;
	
	


	@Override
	@org.springframework.transaction.annotation.Transactional(rollbackFor=AlreadyExistsException.class)
	public Food addFood(Food food) throws AlreadyExistsException {
		// TODO Auto-generated method stub
	
		if(repository.existsByFoodName(food.getFoodName()) == true) {
			throw new AlreadyExistsException("food already exists");
		}
		
		Food food2 = repository.save(food);
		if(food2!=null)
		{
			return food2;
		}
		else
		{
			return null;
		}

			
		
		}
				


	@Override
	public String updateFood(Integer id, Food food) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return null;
		//we dont write here coz update is automatically taken care of
	}

	@Override
	public Food getFoodById(Integer id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		
	   Optional<Food>optional=repository.findById(id);
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
	public Optional<List<Food>> getAllFood()
			 {
		// TODO Auto-generated method stub
		List<Food> list = repository.findAll();
		Register[] array = new Register[list.size()];
		return Optional.ofNullable(repository.findAll());
		
		
	}

	@Override
	public String deleteFoodById(Integer id) throws IdNotFoundException {
		
			Food optional;
			try {
				optional = this.getFoodById(id);
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




