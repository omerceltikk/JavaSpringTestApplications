package com.example.demo_v5.requests;

import lombok.Data;

@Data
public class CommentCreateRequest {
	
	Integer id;
	Integer userId;
	Integer postId;
	String Text;
}
