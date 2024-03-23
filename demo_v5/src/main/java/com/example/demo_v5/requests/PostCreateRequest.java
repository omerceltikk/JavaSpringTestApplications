package com.example.demo_v5.requests;

import lombok.Data;

@Data

public class PostCreateRequest {
	String text;
	String title;
	Integer userId;
}
