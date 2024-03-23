package com.example.demo_v5.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_v5.entities.User;
import com.example.demo_v5.exceptions.UserNotFoundException;
import com.example.demo_v5.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController{
	private UserService userService; //spring boot otomatik olarak repository i inject eder. bu injectler içerisinden constructor injection'ı tercih ettik.
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();	
	}
	@GetMapping("/{id}")
	public User getUserWithID(@PathVariable Integer id) {
		User user = userService.getUserWithID(id);
		if(user == null) {
			throw new UserNotFoundException();
		}
		return user;
	}
	@PostMapping //post isteği atıldığında bu method çağırılacak. burada body de gelen bilgileri dbye mapleyip kaydeder.
	public User createUser(@RequestBody User newUser) {
		return userService.createUser(newUser);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable Integer id, @RequestBody User newUser) {
		return userService.updateUser(id, newUser);
	}
	@DeleteMapping("/{id}")
	public void DeleteUser(@PathVariable Integer id) {
		userService.DeleteUser(id);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private void handleUserNotFoundException() {
		
	}
	
}





