package com.example.certificatewebsite.repository;

import com.example.certificatewebsite.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}

