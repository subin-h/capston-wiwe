package com.capston.project.repository;

import com.capston.project.entity.community.Boards;
import com.capston.project.entity.memo.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
