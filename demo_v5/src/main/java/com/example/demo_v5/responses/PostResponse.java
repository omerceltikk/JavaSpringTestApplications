package com.example.demo_v5.responses;

import java.util.List;

import com.example.demo_v5.entities.Like;
import com.example.demo_v5.entities.Post;

import lombok.Data;

@Data
public class PostResponse {
	Integer id;
	Long userId;
	String userName;
	String title;
	String text;
	List<LikeResponse> likes;
	public PostResponse(Post entity, List<LikeResponse> likes) {
		this.id = entity.getId();
		this.userId = entity.getUser().getId();
		this.userName = entity.getUser().getUserName();
		this.title = entity.getTitle();
		this.text = entity.getText();
		this.likes =likes;
	}
}
