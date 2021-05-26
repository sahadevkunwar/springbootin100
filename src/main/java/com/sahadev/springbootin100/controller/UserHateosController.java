package com.sahadev.springbootin100.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sahadev.springbootin100.entities.Order;
import com.sahadev.springbootin100.entities.User;
import com.sahadev.springbootin100.exceptions.UserNotFoundException;
import com.sahadev.springbootin100.repository.UserRepository;
import com.sahadev.springbootin100.services.UserService;

@RestController
@RequestMapping(value="/hateos/users")
@Validated
public class UserHateosController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	 @GetMapping("/{id}")
     public EntityModel<User> getUserById(@PathVariable("id")@Min(1) Long id){
   	  try {
   		  
       	  Optional<User> userOptional=userService.getUserById(id);
       	  User user=userOptional.get();
       	  Long userid=user.getId();
       	  Link selflink=WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
       	  user.add(selflink);
       	  EntityModel<User> finalResource=new EntityModel<User>(user);
       	  return finalResource;
       	  
   	  }catch(UserNotFoundException ex) {
   		  throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
   	  }
   	  }
	 
	@GetMapping
	public CollectionModel<User> getAllUser() throws UserNotFoundException{
		List<User> allusers= userService.getAllUser();
		for(User user:allusers) {
			//self link
			Long userid=user.getId();
			Link selflink=WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selflink);
			
			//Relationship link with getAllOrders
			CollectionModel<Order> orders=WebMvcLinkBuilder.methodOn(OrderHateosController.class)
					.getAllOrders(userid);
			
			Link ordersLink=WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(ordersLink);
		}
		//self link for getAllUsers
		Link selflinkgetAllUsers=WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		CollectionModel<User> finalResource=new CollectionModel<User>(allusers,selflinkgetAllUsers);
		return finalResource;
	}

}
