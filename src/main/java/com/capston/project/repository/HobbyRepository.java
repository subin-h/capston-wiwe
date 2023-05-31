package com.capston.project.repository;

import com.capston.project.entity.hobby.Hobby;
import com.capston.project.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
    List<Hobby> findByHobbyAttribute1ContainingAndHobbyAttribute2ContainingAndHobbyAttribute3Containing(String keyword1, String keyword2, String keyword3);
}
