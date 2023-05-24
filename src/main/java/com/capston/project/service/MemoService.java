package com.capston.project.service;

import com.capston.project.config.SecurityUtil;
import com.capston.project.dto.community.BoardsCreateDto;
import com.capston.project.dto.community.BoardsDto;
import com.capston.project.dto.community.BoardsMainDto;
import com.capston.project.dto.community.BoardsRequestDto;
import com.capston.project.dto.memo.*;
import com.capston.project.entity.community.Boards;
import com.capston.project.entity.memo.Memo;
import com.capston.project.entity.user.User;
import com.capston.project.exception.BoardNotFoundException;
import com.capston.project.exception.MemberNotFoundException;
import com.capston.project.repository.MemoRepository;
import com.capston.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final UserRepository userRepository;
    private final MemoRepository memoRepository;

    @Transactional
    public MemoCreateDto createMemo (MemoRequestDto req) { //메모 생성
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime memoDate = LocalDateTime.parse(req.getMemoDate() + " 00:00:00", formatter);

        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        Memo memo = memoRepository.save(new Memo(req.getMemoTitle(), req.getMemoContent1(),req.getMemoContent1(),memoDate, user));
        return MemoCreateDto.toDto(memo);
    }

    @Transactional(readOnly = true)
    public List<MemoMainDto> findAllMemo() { // 메모 전체 조회
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        List<Memo> memo = memoRepository.findByUserUserId(user.getUserId());
        List<MemoMainDto> memoMainDtoList = new ArrayList<>();
        memo.stream().forEach(i -> memoMainDtoList.add(new MemoMainDto().toDto(i)));
        return memoMainDtoList;
    }

    @Transactional
    public void deleteMemo(Long id) { //메모 삭제
        Memo memo = memoRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);

        if(user != memo.getUser()){
            throw new MemberNotFoundException();
        }
        memoRepository.delete(memo);
    }

    @Transactional
    public MemoResponseDto updateMemo (Long id, MemoUpdateDto req){ //메모 수정
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        Memo memo = memoRepository.findById(id)
                .orElseThrow(BoardNotFoundException::new);
        if(user != memo.getUser()) {
            throw new MemberNotFoundException();
        }
        memo.setMemoTitle(req.getMemoTitle());
        memo.setMemoContent1(req.getMemoContent1());
        memo.setMemoContent2(req.getMemoContent2());

        return MemoResponseDto.toDto(memo);
    }

    @Transactional(readOnly = true)
    public MemoResponseDto singleMemoFind(Long id){ //메모 단건 조회

        Memo memo = memoRepository.findById(id)
                .orElseThrow(BoardNotFoundException::new);
        return MemoResponseDto.toDto(memo);
    }

}
