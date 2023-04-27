package com.capston.project.service;

import com.capston.project.config.SecurityUtil;
import com.capston.project.dto.community.BoardsCreateDto;
import com.capston.project.dto.community.BoardsDto;
import com.capston.project.dto.community.BoardsRequestDto;
import com.capston.project.entity.community.Boards;
import com.capston.project.entity.user.User;
import com.capston.project.exception.MemberNotFoundException;
import com.capston.project.exception.BoardNotFoundException;
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

    @Transactional(readOnly = true)
    public BoardsDto findBoards(Long id){ //게시글 단건 조회

        Boards boards = boardsRepository.findById(id)
                .orElseThrow(BoardNotFoundException::new);
        User user = boards.getUser();
        return BoardsDto.toDto(boards, user.getNickname());
    }

    @Transactional
    public BoardsDto updateBoard (Long id, BoardsRequestDto req){ //게시글 수정
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        Boards boards = boardsRepository.findById(id)
                .orElseThrow(BoardNotFoundException::new);
        if(user != boards.getUser()) {
            throw new MemberNotFoundException();
        }
        boards.setBoardsTitle(req.getTitle());
        boards.setBoardsContent(req.getContent());

        return BoardsDto.toDto(boards, user.getNickname());
    }

    @Transactional
    public void deleteBoard(Long id) { //게시물 삭제
        Boards boards = boardsRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);

        if(user != boards.getUser()){
            throw new MemberNotFoundException();
        }
        boardsRepository.delete(boards);
    }
}
