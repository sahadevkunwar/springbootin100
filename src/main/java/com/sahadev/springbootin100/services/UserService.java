package com.sahadev.springbootin100.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahadev.springbootin100.entities.User;
import com.sahadev.springbootin100.repository.UserRepository;

@Service
public class UserService {
	@Autowired
 private UserRepository userRepository;
	
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	public User createUser(User user) {
		return userRepository.save(user);
	}
    public Optional<User> getUserById(Long id){
    	Optional<User> user=userRepository.findById(id);
    	return user;
    }
    public User updateUserById(Long id,User user) {
    	user.setId(id);
    	return userRepository.save(user);
    }
    public void deleteUserById(Long id) {
    	if(userRepository.findById(id).isPresent()) {
    		userRepository.deleteById(id);
    	}
    }
    public User getUserByUsername(String username) {
    	return userRepository.findByUsername(username);
    	
    }
}

