package com.sahadev.springbootin100.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sahadev.springbootin100.entities.User;
import com.sahadev.springbootin100.exceptions.UserExistsException;
import com.sahadev.springbootin100.exceptions.UserNotFoundException;
import com.sahadev.springbootin100.repository.UserRepository;

@Service
public class UserService {
	@Autowired
 private UserRepository userRepository;
	
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	public User createUser(User user) throws UserExistsException{
		///if user exist using username
		User  existingUser=userRepository.findByUsername(user.getUsername());
		
		///if not exists throw UserExistsException
		if(existingUser!=null) {
			throw new UserExistsException("User already exists in repository");
		}
		return userRepository.save(user);
	}
    public Optional<User> getUserById(Long id)throws UserNotFoundException{
    	Optional<User> user=userRepository.findById(id);
    	if(!user.isPresent()) {
    		throw new UserNotFoundException("User Not Found in Repository");
    	}
    	return user;
    }
    public User updateUserById(Long id,User user)throws UserNotFoundException {
    	Optional<User> optionalUser=userRepository.findById(id);
    	if(!optionalUser.isPresent()) {
    		throw new UserNotFoundException("User Not Found in Repository,provide the correct id");
    	}
    	user.setId(id);
    	return userRepository.save(user);
    }
    public void deleteUserById(Long id) {
    	Optional<User> optionalUser=userRepository.findById(id);
    	if(!optionalUser.isPresent()) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not Found in Repository,provide the correct id to delete");
    	}
    	userRepository.deleteById(id);
    }
    public User getUserByUsername(String username) {
    	return userRepository.findByUsername(username);
    	
    }
}

