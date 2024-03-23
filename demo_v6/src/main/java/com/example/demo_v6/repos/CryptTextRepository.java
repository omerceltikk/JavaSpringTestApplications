package com.example.demo_v6.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo_v6.entities.CryptText;

public interface CryptTextRepository extends JpaRepository<CryptText,Integer> {

	List<CryptText> findByUserUserId(Integer userId);
	
}
