package com.example.demo_v5.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo_v5.entities.Comment;
import com.example.demo_v5.entities.Like;
import com.example.demo_v5.responses.LikeActivityResponse;

public interface LikeRepository extends JpaRepository<Like,Integer> {

	List<Like> findByUserIdAndPostId(Integer userId, Integer postId);

	List<Like> findByUserId(Integer userId);

	List<Like> findByPostId(Integer postId);
	
	@Query(value="select * from dbo.demov5like where post_id in :postIds", nativeQuery=true)
	List<Like> findUserLikesByPostId(@Param("postIds") List<Integer> postIds);

}
