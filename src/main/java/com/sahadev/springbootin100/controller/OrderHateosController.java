package com.sahadev.springbootin100.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahadev.springbootin100.entities.Order;
import com.sahadev.springbootin100.entities.User;
import com.sahadev.springbootin100.exceptions.UserNotFoundException;
import com.sahadev.springbootin100.repository.OrderRepository;
import com.sahadev.springbootin100.repository.UserRepository;

@RestController
@RequestMapping(value="/hateos/users")
public class OrderHateosController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	//get all Order for a user
		@GetMapping("/{userid}/orders")
		public CollectionModel<Order> getAllOrders(@PathVariable Long userid)throws UserNotFoundException{
			Optional<User> userOptional =userRepository.findById(userid);
			if(!userOptional.isPresent()) 
			 throw new UserNotFoundException("User not found");
			List<Order> allorders= userOptional.get().getOrders();
			CollectionModel<Order> finalResource=new CollectionModel<Order>(allorders);
			return finalResource;
		}
}
