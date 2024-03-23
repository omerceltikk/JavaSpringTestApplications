package com.example.demo_v5.responses;

import com.example.demo_v5.entities.Comment;
import com.example.demo_v5.entities.Post;

import lombok.Data;

@Data
public class CommentResponse {
	Integer id;
	Integer postId;
	String text;
	Long userId;
	
	public CommentResponse(Comment entity) {
		this.id = entity.getId();
		this.postId = entity.getPost().getId();
		this.text = entity.getText();
		this.userId = entity.getUser().getId();
	}
}


	
	

