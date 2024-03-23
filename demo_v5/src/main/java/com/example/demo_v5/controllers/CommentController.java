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

import com.example.demo_v5.entities.Comment;
import com.example.demo_v5.requests.CommentCreateRequest;
import com.example.demo_v5.requests.CommentUpdateRequest;
import com.example.demo_v5.responses.CommentResponse;
import com.example.demo_v5.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController{
	private CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	@GetMapping
	public List<CommentResponse> getAllComments( @RequestParam Optional <Integer> postId, @RequestParam Optional <Integer> userId){
		return commentService.getAllComments(postId,userId);
	}
	@GetMapping("/{commentId}")
	public Comment getOneCommentById(@PathVariable Integer commentId) {
		return commentService.getOneCommentById(commentId);
	}
	@PostMapping
	public Comment createOneComment(@RequestBody CommentCreateRequest request ) {
		return commentService.createOneRequest(request);
	}
	@PutMapping("/{commentId}")
	public Comment updateOneComment( @PathVariable Integer commentId,@RequestBody CommentUpdateRequest request) {
		return commentService.updateOneComment(commentId,request);
	}
	@DeleteMapping("/{commentId}")
	public void deleteOneComment(@PathVariable Integer commentId) {
		commentService.deleteOneComment(commentId);
	}
	
}