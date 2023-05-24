package com.capston.project.repository;

import com.capston.project.entity.memo.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByUserUserId(Long userId);
    List<Memo> findByUserUserIdAndMemoDate(Long userId, LocalDateTime memoDate);

}
