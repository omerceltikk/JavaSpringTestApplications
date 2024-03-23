package com.example.demo_v5.responses;

import com.example.demo_v5.entities.Comment;
import com.example.demo_v5.entities.Like;

import lombok.Data;
@Data
public class LikeResponse {
	Integer id;
	Integer postId;
	Long userId;
	
	public LikeResponse(Like entity) {
		this.id = entity.getId();
		this.postId = entity.getPost().getId();
		this.userId = entity.getUser().getId();
	}	
}
