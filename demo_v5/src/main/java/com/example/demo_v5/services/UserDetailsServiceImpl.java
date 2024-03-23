package com.example.demo_v5.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo_v5.entities.User;
import com.example.demo_v5.repos.UserRepository;
import com.example.demo_v5.security.JwtUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepo;
	
	public UserDetailsServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(userName);
		return JwtUserDetails.create(user);
	}
	
	public UserDetails loadUserById(Integer id) {
		User user = userRepo.findById(id).get();
		return JwtUserDetails.create(user);
	}
}
