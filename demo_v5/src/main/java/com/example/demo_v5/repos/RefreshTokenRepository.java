package com.example.demo_v5.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo_v5.entities.RefreshToken;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

	RefreshToken findByUserId(Long userId);
	
}