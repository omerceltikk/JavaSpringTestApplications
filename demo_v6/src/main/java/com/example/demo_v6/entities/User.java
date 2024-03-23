package com.example.demo_v6.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "demoV6user")
@Data //lombok ile otomatik olarak getter setter generate etmek için eklendi.
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // oto create id
	 Long userId;
	
	 String userName;
	 String password;
}