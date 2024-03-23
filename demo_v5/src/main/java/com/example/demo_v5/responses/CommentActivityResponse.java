package com.example.demo_v5.responses;

import com.example.demo_v5.entities.Comment;

import lombok.Data;

@Data
public class CommentActivityResponse {
	
	String message = "commented on";
	Integer id;
	String userName;
	Integer postId;
	public CommentActivityResponse(Comment entity) {
		this.id = entity.getId();
		this.userName = entity.getUser().getUserName();
		this.postId = entity.getPost().getId();
	}
}
