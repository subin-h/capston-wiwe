package com.capston.project.repository;

import com.capston.project.entity.community.Boards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardsRepository extends JpaRepository<Boards, Long> {
}
