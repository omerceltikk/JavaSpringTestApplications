package com.example.demo_v5.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo_v5.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

	List<Comment> findByUserIdAndPostId(Integer userId, Integer postId);

	List<Comment> findByUserId(Integer userId);

	List<Comment> findByPostId(Integer postId);

	@Query(value="select * from dbo.demov5comment where post_id in (:postIds)", nativeQuery=true)
	List<Comment> findUserCommentByPostId(@Param("postIds") List<Integer> postIds);
}
