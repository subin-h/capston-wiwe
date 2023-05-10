package com.capston.project.repository;

import com.capston.project.entity.community.Boards;
import com.capston.project.entity.community.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByBoardsBoardsId(Long boardsId);
    List<Comment> findByUserUserId(Long userId);

}
