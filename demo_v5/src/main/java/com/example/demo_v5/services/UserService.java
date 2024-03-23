package com.example.demo_v5.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo_v5.entities.User;
import com.example.demo_v5.repos.CommentRepository;
import com.example.demo_v5.repos.LikeRepository;
import com.example.demo_v5.repos.PostRepository;
import com.example.demo_v5.repos.UserRepository;

@Service
public class UserService {
	private UserRepository userRepo; //spring boot otomatik olarak repository i inject eder. bu injectler içerisinden constructor injection'ı tercih ettik.
	private LikeRepository likeRepo;
	private CommentRepository commentRepo;
	private PostRepository postRepo;
	
	public UserService(UserRepository userRepo, LikeRepository likeRepo, CommentRepository commentRepo,
			PostRepository postRepo) {
		this.userRepo = userRepo;
		this.likeRepo = likeRepo;
		this.commentRepo = commentRepo;
		this.postRepo = postRepo;
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
			updatedUser.setId(newUser.getId());
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
