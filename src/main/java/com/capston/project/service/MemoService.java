package com.capston.project.service;

import com.capston.project.config.SecurityUtil;
import com.capston.project.dto.community.BoardsCreateDto;
import com.capston.project.dto.community.BoardsRequestDto;
import com.capston.project.dto.memo.MemoCreateDto;
import com.capston.project.dto.memo.MemoRequestDto;
import com.capston.project.entity.community.Boards;
import com.capston.project.entity.memo.Memo;
import com.capston.project.entity.user.User;
import com.capston.project.repository.MemoRepository;
import com.capston.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final UserRepository userRepository;
    private final MemoRepository memoRepository;

    @Transactional
    public MemoCreateDto createMemo (MemoRequestDto req) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime memoDate = LocalDateTime.parse(req.getMemoDate() + " 00:00:00", formatter);

        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        Memo memo = memoRepository.save(new Memo(req.getMemoTitle(), req.getMemoContent1(),req.getMemoContent1(),memoDate, user));
        return MemoCreateDto.toDto(memo);
    }

}
