package com.capston.project.repository;

import com.capston.project.entity.community.Boards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardsRepository extends JpaRepository<Boards, Long> {
    List<Boards> findByBoardsTitleContaining(String keyword); // BoardsTitle
    List<Boards> findByUserUserId(Long userId);
}
