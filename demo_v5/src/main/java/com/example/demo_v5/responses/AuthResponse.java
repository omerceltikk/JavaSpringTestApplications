package com.example.demo_v5.responses;

import lombok.Data;

@Data
public class AuthResponse {
	String message;
	Long userId;
	String accessToken;
	String refreshToken;
}
