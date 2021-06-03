package com.sahadev.springbootin100.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahadev.springbootin100.dtos.UserMsDto;
import com.sahadev.springbootin100.entities.User;
import com.sahadev.springbootin100.mappers.UserMapper;
import com.sahadev.springbootin100.repository.UserRepository;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {
   @Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping
	public List<UserMsDto> getAllUserDtos(){
		return userMapper.usersToUserDtos(userRepository.findAll());
	}
	@GetMapping("/{id}")
	public UserMsDto getUserById(@PathVariable Long id) {
		Optional<User> userOptional=userRepository.findById(id);
		User user=userOptional.get();
		return userMapper.userToUserMsDto(user);
	}
}
