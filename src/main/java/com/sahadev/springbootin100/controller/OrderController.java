package com.sahadev.springbootin100.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahadev.springbootin100.entities.Order;
import com.sahadev.springbootin100.entities.User;
import com.sahadev.springbootin100.exceptions.OrderNotFoundException;
import com.sahadev.springbootin100.exceptions.UserNotFoundException;
import com.sahadev.springbootin100.repository.OrderRepository;
import com.sahadev.springbootin100.repository.UserRepository;

@RestController
@RequestMapping(value="/users")
public class OrderController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
     
	//get all Order for a user
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid)throws UserNotFoundException{
		Optional<User> userOptional =userRepository.findById(userid);
		if(!userOptional.isPresent()) 
		 throw new UserNotFoundException("User not found");
		return userOptional.get().getOrders();
	}
	//create order
	@PostMapping("/{userid}/orders")
	public Order createOrder(@PathVariable Long userid,@RequestBody Order order) throws UserNotFoundException{
		Optional<User> userOptional =userRepository.findById(userid);
		if(!userOptional.isPresent()) 
		 throw new UserNotFoundException("User not found");
		User user=userOptional.get();
		order.setUser(user);
		return orderRepository.save(order);
		
	}
	//get order by id
	@GetMapping("/{userid}/orders/{orderid}")
	public User getOrderById(@PathVariable Long userid) throws UserNotFoundException {
		Optional<User> userOptional =userRepository.findById(userid);
		if(!userOptional.isPresent()) 
		 throw new UserNotFoundException("User not found");
		return userOptional.get();
		
	}
}
