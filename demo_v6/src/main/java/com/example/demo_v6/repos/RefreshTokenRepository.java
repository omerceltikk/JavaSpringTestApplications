package com.example.demo_v6.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo_v6.entities.RefreshToken;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

	RefreshToken findByUserUserId(Long userId);
}