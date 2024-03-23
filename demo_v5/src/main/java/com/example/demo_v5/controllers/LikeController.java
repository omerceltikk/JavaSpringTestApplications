package com.example.demo_v5.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_v5.entities.Like;
import com.example.demo_v5.requests.LikeCreateRequest;
import com.example.demo_v5.responses.LikeResponse;
import com.example.demo_v5.services.LikeService;


@RestController
@RequestMapping("/likes")
public class LikeController {
	private LikeService likeService;
	
	public LikeController(LikeService likeService) {
		this.likeService = likeService;
	}
	
	@GetMapping
	public List<LikeResponse> getAllLikes(@RequestParam Optional <Integer> userId, @RequestParam Optional <Integer> postId){
		return likeService.getAllLikes(userId,postId);
	}
	@GetMapping("/{likeId}")
	public Like getOneLikeById(@PathVariable Integer likeId) {
		return likeService.getOneLikeById(likeId);
	}
	@PostMapping
	public Like createOneLike(@RequestBody LikeCreateRequest request) {
		return likeService.createOneLike(request);
	}
	@DeleteMapping("/{likeId}")
	public void deleteOneLike(@PathVariable Integer likeId) {
		 likeService.deleteOneLike(likeId);
	}

}
	
