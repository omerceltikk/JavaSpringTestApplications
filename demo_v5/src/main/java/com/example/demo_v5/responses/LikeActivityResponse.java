package com.example.demo_v5.responses;

import com.example.demo_v5.entities.Like;

import lombok.Data;

@Data
public class LikeActivityResponse {
	String message = "liked";
	Integer id;
	String userName;
	Integer postId;
	public LikeActivityResponse(Like entity) {
		this.id = entity.getId();
		this.userName = entity.getUser().getUserName();
		this.postId = entity.getPost().getId();
	}
}
