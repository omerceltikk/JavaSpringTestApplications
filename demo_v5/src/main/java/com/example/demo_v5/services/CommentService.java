package com.example.demo_v5.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo_v5.entities.Comment;
import com.example.demo_v5.entities.Post;
import com.example.demo_v5.entities.User;
import com.example.demo_v5.repos.CommentRepository;
import com.example.demo_v5.requests.CommentCreateRequest;
import com.example.demo_v5.requests.CommentUpdateRequest;
import com.example.demo_v5.responses.CommentResponse;

@Service
public class CommentService {
	private CommentRepository commentRepo;
	private PostService postService;
	private UserService userService;
	
	public CommentService(CommentRepository commentRepo, PostService postService,UserService userService) {
		this.commentRepo = commentRepo;
		this.postService = postService;
		this.userService = userService;
	}

	public List<CommentResponse> getAllComments(Optional<Integer> postId, Optional<Integer> userId) {
		List <Comment> list;
		if(userId.isPresent() && postId.isPresent()) {
			list = commentRepo.findByUserIdAndPostId(userId.get(),postId.get());
		}else if(userId.isPresent()) {
			list = commentRepo.findByUserId(userId.get());
		}else if(postId.isPresent()) {
			list = commentRepo.findByPostId(postId.get());
		}
		
		list = commentRepo.findAll();
		return list.stream().map((c) -> new CommentResponse(c)).collect(Collectors.toList());
	}

	public Comment getOneCommentById(Integer commentId) {
		return commentRepo.findById(commentId).orElse(null);
	}

	public Comment createOneRequest(CommentCreateRequest request) {
		User currUser = userService.getUserWithID(request.getUserId());
		Post currPost = postService.getOnePostById(request.getPostId());
		if(currUser != null && currPost != null) {
			Comment commentToSave = new Comment();
			commentToSave.setText(request.getText());
			commentToSave.setUser(currUser);
			commentToSave.setPost(currPost);
			return commentRepo.save(commentToSave);
		}else
		return null;
	}

	public Comment updateOneComment( Integer commentId, CommentUpdateRequest request) {
		Optional<Comment> currComment = commentRepo.findById(commentId);
		if(currComment.isPresent()) {
			Comment commentToUpdate = currComment.get();
			commentToUpdate.setText(request.getText());
			return commentRepo.save(commentToUpdate);
		}else
		return null;
	}

	public void deleteOneComment(Integer commentId) {
		commentRepo.deleteById(commentId);
		
	}
	
	
}
