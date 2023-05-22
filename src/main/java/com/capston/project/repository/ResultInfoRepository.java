package com.capston.project.repository;

import com.capston.project.entity.checklist.ResultInfo;
import com.capston.project.entity.community.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultInfoRepository extends JpaRepository<ResultInfo,Long> {
}
