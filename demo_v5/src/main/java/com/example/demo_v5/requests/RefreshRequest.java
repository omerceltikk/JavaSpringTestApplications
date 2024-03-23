package com.example.demo_v5.requests;

import lombok.Data;

@Data
public class RefreshRequest {
	Long userId;
	String RefreshToken;
}
