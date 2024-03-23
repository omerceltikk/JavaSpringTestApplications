package com.example.demo_v5.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo_v5.entities.Like;
import com.example.demo_v5.entities.Post;
import com.example.demo_v5.entities.User;
import com.example.demo_v5.repos.LikeRepository;
import com.example.demo_v5.requests.LikeCreateRequest;
import com.example.demo_v5.responses.LikeResponse;

@Service
public class LikeService {
	private LikeRepository likeRepo;
	private UserService userService;
	private PostService postService;
	public LikeService(LikeRepository likeRepo, UserService userService, PostService postService) {
		this.likeRepo = likeRepo;
		this.userService = userService;
		this.postService = postService;
	}
	public List<LikeResponse> getAllLikes(Optional<Integer> userId, Optional<Integer> postId) {
		List <Like> list;
		if(userId.isPresent() && postId.isPresent()) {
			list = likeRepo.findByUserIdAndPostId(userId.get(),postId.get());
		}else if(userId.isPresent()) {
			list = likeRepo.findByUserId(userId.get());
		}else if(postId.isPresent()) {
			list = likeRepo.findByPostId(postId.get());
		}
		
		list = likeRepo.findAll();
		 return list.stream().map((l) -> new LikeResponse(l)).collect(Collectors.toList());
		
	}
	public Like getOneLikeById(Integer likeId) {
		return likeRepo.findById(likeId).orElse(null);
	}
	public Like createOneLike(LikeCreateRequest request) {
		Post currPost = postService.getOnePostById(request.getPostId());
		User currUser = userService.getUserWithID(request.getUserId());
		if(currUser != null && currPost != null) {
			Like createdLike = new Like();
			createdLike.setPost(currPost);
			createdLike.setUser(currUser);
			return likeRepo.save(createdLike);
		}
		return null;
	
	}
	public void deleteOneLike(Integer likeId) {
		likeRepo.deleteById(likeId);
	}
	
	
}
