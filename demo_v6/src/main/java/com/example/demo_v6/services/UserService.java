package com.example.demo_v6.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo_v6.entities.User;
//import com.example.demo_v6.repos.PostRepository;
import com.example.demo_v6.repos.UserRepository;

@Service
public class UserService {
	private UserRepository userRepo; //spring boot otomatik olarak repository i inject eder. bu injectler içerisinden constructor injection'ı tercih ettik.
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	

	public List<User> getAllUsers() {
		return userRepo.findAll();	
	}
	public User getUserWithID(Integer id) {
		//path varible annotation u ile gelen id yi getmapping pathine yönlendirdik
		// dbde olmayan user için exception eklenecek
		return userRepo.findById(id).orElse(null);
	}

	public User createUser(User newUser) {
		return userRepo.save(newUser);
	}
	
	public User updateUser(Integer id,User newUser) {
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			User updatedUser = new User();
			updatedUser.setUserId(newUser.getUserId());
			updatedUser.setPassword(newUser.getPassword());
			updatedUser.setUserName(newUser.getUserName());
			return updatedUser;
		}else {
			return null;
		}
	}
	public void DeleteUser(Integer id) {
		userRepo.deleteById(id);
	}

	public User getUserWithUsername(String username) {
		return userRepo.findByUserName(username);
	}

}
