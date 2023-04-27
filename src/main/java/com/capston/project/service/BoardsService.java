package com.capston.project.service;

import com.capston.project.config.SecurityUtil;
import com.capston.project.dto.community.BoardsCreateDto;
import com.capston.project.dto.community.BoardsRequestDto;
import com.capston.project.entity.community.Boards;
import com.capston.project.entity.user.User;
import com.capston.project.repository.BoardsRepository;
import com.capston.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardsService {
    private final BoardsRepository boardsRepository;
    private final UserRepository userRepository;

    @Transactional
    public BoardsCreateDto createBoard (BoardsRequestDto req) { //게시글 작성
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        Boards boards = boardsRepository.save(new Boards(req.getTitle(), req.getContent(), user));
        return BoardsCreateDto.toDto(boards);
    }
}
