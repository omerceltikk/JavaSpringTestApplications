package com.example.demo_v5.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo_v5.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

 User findByUserName(String userName);

//jpaRepository altında findAll methodu select * from table a eşdeğer
	
	
}
