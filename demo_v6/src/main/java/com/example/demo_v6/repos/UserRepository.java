package com.example.demo_v6.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo_v6.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

 User findByUserName(String userName);

//jpaRepository altında findAll methodu select * from table a eşdeğer
	
	
}
