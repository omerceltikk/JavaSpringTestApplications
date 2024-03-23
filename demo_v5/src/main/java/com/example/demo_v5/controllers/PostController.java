package com.example.demo_v5.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_v5.entities.Post;
import com.example.demo_v5.entities.User;
import com.example.demo_v5.requests.PostCreateRequest;
import com.example.demo_v5.requests.PostUpdateRequest;
import com.example.demo_v5.responses.PostResponse;
import com.example.demo_v5.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	@GetMapping
	public List<PostResponse> getAllPosts(@RequestParam Optional<Integer> userId ) {
		//request param bize gelen requestin içerisindeki parametreleri parse edip değişkenin içerisine atar burada gerekli id ile eşlesen postlar aldık
		return postService.getAllPosts(userId);
	}
	@GetMapping("/{postId}")
	public Post getOnePost(@PathVariable Integer postId) {
		return postService.getOnePostById(postId);
	}
	@PostMapping
	public Post createPost(@RequestBody PostCreateRequest newPostRequest) {
		return postService.createPost(newPostRequest);
	}
	@PutMapping("/{postId}")
	public Post updatePost (@PathVariable Integer postId, @RequestBody PostUpdateRequest postUpdateRequest) {
		return postService.updatePost(postId,postUpdateRequest);
	}
	@DeleteMapping("/{postId}")
	public void deletePost (@PathVariable Integer postId) {
		postService.deletePost(postId);
	}
	@GetMapping("/activity/{userId}")
	public List<Object> getUserActivity(@PathVariable Integer userId){
		return postService.getUserActivity(userId);
	}
	
}