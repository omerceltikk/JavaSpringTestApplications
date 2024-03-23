package com.example.demo_v5.requests;

import lombok.Data;

@Data
public class LikeCreateRequest {
	Integer userId;
	Integer postId;
}
