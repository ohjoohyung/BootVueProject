package com.example.demo.repository;

import com.example.demo.entitiy.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select * from comment where board_no=:boardNo and id>0 order by id ASC", nativeQuery = true)
    List<Comment> getCommentsByBoardNo(@Param("boardNo") Long boardNo);
}
