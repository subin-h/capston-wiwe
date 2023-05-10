package com.capston.project.service;

import com.capston.project.config.SecurityUtil;
import com.capston.project.dto.checklist.ChecklistSum1RequestDto;
import com.capston.project.dto.checklist.ChecklistSum2RequestDto;
import com.capston.project.dto.checklist.ChecklistSum3RequestDto;
import com.capston.project.dto.user.UserDto;
import com.capston.project.entity.user.User;
import com.capston.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChecklistService {

    private final UserRepository userRepository;

    @Transactional
    public UserDto updateChecklist1(ChecklistSum1RequestDto req) {
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        user.setChecklistSum1(req.getChecklistSum1());
        return UserDto.from(userRepository.save(user));
    }

    @Transactional
    public UserDto updateChecklist2(ChecklistSum2RequestDto req) {
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        user.setChecklistSum2(req.getChecklistSum2());
        return UserDto.from(userRepository.save(user));
    }

    @Transactional
    public UserDto updateChecklist3(ChecklistSum3RequestDto req) {
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        user.setChecklistSum3(req.getChecklistSum3());
        return UserDto.from(userRepository.save(user));
    }


}
