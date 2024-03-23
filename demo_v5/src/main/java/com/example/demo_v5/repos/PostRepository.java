package com.example.demo_v5.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo_v5.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUserId(Integer integer);
	//jpa repository ile gelen bir findBy kalıbı ile interfacede istenen bir metod u oluşturup kullanıyoruz.
	//yazdığımız custom findBy kalıbı ile istediğimiz metodu oluşturuyoruz ve gerisini jpa düzenliyor.
	
	@Query(value = "select id from dbo.demov5post where user_id in (:userId)", nativeQuery = true)
	List<Integer> findTopByUserId(@Param("userId")Integer userId);
}
