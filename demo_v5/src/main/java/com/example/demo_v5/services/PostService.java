
package com.example.demo_v5.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo_v5.controllers.LikeController;
import com.example.demo_v5.entities.Comment;
import com.example.demo_v5.entities.Like;
import com.example.demo_v5.entities.Post;
import com.example.demo_v5.entities.User;
import com.example.demo_v5.repos.CommentRepository;
import com.example.demo_v5.repos.LikeRepository;
import com.example.demo_v5.repos.PostRepository;
import com.example.demo_v5.repos.UserRepository;
import com.example.demo_v5.requests.PostCreateRequest;
import com.example.demo_v5.requests.PostUpdateRequest;
import com.example.demo_v5.responses.CommentActivityResponse;
import com.example.demo_v5.responses.LikeActivityResponse;
import com.example.demo_v5.responses.LikeResponse;
import com.example.demo_v5.responses.PostResponse;

@Service
public class PostService {
	private PostRepository postRepo; //spring boot otomatik olarak repository i inject eder. bu injectler içerisinden constructor injection'ı tercih ettik.
	private UserService userService;
	private LikeRepository likeRepo;
	private CommentRepository commentRepo;
	
	public PostService(PostRepository postRepo, UserService userService,LikeRepository likeRepo,CommentRepository commentRepo) {
		this.postRepo = postRepo;
		this.userService = userService;
		this.likeRepo = likeRepo;
		this.commentRepo = commentRepo;
	}
	
	
	public List<PostResponse> getAllPosts(Optional<Integer> userId) {
		List <Post> list;
		if(userId.isPresent())
			list = postRepo.findByUserId(userId.get());
		
		list = postRepo.findAll();	
		return list.stream().map(p -> {
			List<LikeResponse> likes = likeRepo.findByPostId(p.getId()).stream().map((l) -> new LikeResponse(l)).collect(Collectors.toList());
			return new PostResponse(p, likes);
			}).collect(Collectors.toList());
		
	}

	public Post getOnePostById(Integer postId) {
		// exception handle!!
		return postRepo.findById(postId).orElse(null);
	}

	public Post createPost(PostCreateRequest newPostRequest) {
		User user=userService.getUserWithID(newPostRequest.getUserId());
			if(user == null) {
				return null;
			}
		Post toSave = new Post();
		toSave.setText(newPostRequest.getText());
		toSave.setTitle(newPostRequest.getTitle());
		toSave.setUser(user);
		 return postRepo.save(toSave);
	}


	public Post updatePost(Integer postId, PostUpdateRequest postUpdateRequest) {
		Optional<Post> post = postRepo.findById(postId);
		if(post.isEmpty()) {
			return null;
		}
		Post toUpdate = post.get();
		toUpdate.setText(postUpdateRequest.getText());
		toUpdate.setTitle(postUpdateRequest.getTitle());
		postRepo.save(toUpdate);
		return toUpdate;
	}


	public void deletePost(Integer postId) {
		 postRepo.deleteById(postId);
	}
	
	public List<Object> getUserActivity(Integer userId) {
		 List <Integer> postIds = postRepo.findTopByUserId(userId);
		 System.out.println(postIds);
		if(postIds.isEmpty())
			return null;
		List<Comment> comments = commentRepo.findUserCommentByPostId(postIds);
		List<Like> likes = likeRepo.findUserLikesByPostId(postIds);
		List<LikeActivityResponse> likeActivityResponses = new ArrayList<>();
		List<CommentActivityResponse> commentActivityResponse = new ArrayList<>();

		for (Like like : likes) {
		    LikeActivityResponse response = new LikeActivityResponse(like);
		    likeActivityResponses.add(response);
		}
		for (Comment comment : comments) {
		    CommentActivityResponse response = new CommentActivityResponse(comment);
		    commentActivityResponse.add(response);
		}
		List<Object> result = new ArrayList<>();
		result.addAll(commentActivityResponse);
		result.addAll(likeActivityResponses);
		return result;
	}
}
