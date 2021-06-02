package com.sahadev.springbootin100.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.sahadev.springbootin100.entities.User;
import com.sahadev.springbootin100.entities.Views;
import com.sahadev.springbootin100.exceptions.UserNotFoundException;
import com.sahadev.springbootin100.services.UserService;

@RestController
@Validated
@RequestMapping(value="/jsonview/users")
public class UserJsonViewController {
	@Autowired
	private UserService userService;
	
	//getUserById-External
	@JsonView(Views.External.class)
	@GetMapping("/external/{id}")
    public Optional<User> getUserById(@PathVariable("id")@Min(1) Long id){
  	  try {
      	  return userService.getUserById(id);
  	  }catch(UserNotFoundException ex) {
  		  throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
  	  }

	}
	//getUserById-Internal
	@JsonView(Views.Internal.class)
	@GetMapping("/internal/{id}")
    public Optional<User> getUserById2(@PathVariable("id")@Min(1) Long id){
  	  try {
      	  return userService.getUserById(id);
  	  }catch(UserNotFoundException ex) {
  		  throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
  	  }

	}
}
