package com.capston.project.service;

import com.capston.project.config.SecurityUtil;
import com.capston.project.dto.community.*;
import com.capston.project.entity.community.Boards;
import com.capston.project.entity.community.Comment;
import com.capston.project.entity.user.User;
import com.capston.project.exception.CommentNotFoundException;
import com.capston.project.exception.MemberNotFoundException;
import com.capston.project.exception.BoardNotFoundException;
import com.capston.project.repository.BoardsRepository;
import com.capston.project.repository.CommentRepository;
import com.capston.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardsService {
    private final BoardsRepository boardsRepository;
    private final CommentRepository commentRepository;
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

    @Transactional(readOnly = true)
    public BoardsDto findBoards2(Long id){ //댓글 관련 게시글 단건 조회
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        Long boardsIdCheck;
        boardsIdCheck = comment.getBoards().getBoardsId();
        Boards boards = boardsRepository.findById(boardsIdCheck).orElseThrow(BoardNotFoundException::new);
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

    @Transactional(readOnly = true)
    public List<BoardsMainDto> findAllPage() { // 전체 조회
        List<Boards> boards = boardsRepository.findAll();
        List<BoardsMainDto> boardsMainDtoList = new ArrayList<>();
        boards.stream().forEach(i -> boardsMainDtoList.add(new BoardsMainDto().toDto(i)));
        return boardsMainDtoList;
    }

    @Transactional(readOnly = true) // 검색
    public List<BoardsMainDto> search(String keyword) {
        List<Boards> boards = boardsRepository.findByBoardsTitleContaining(keyword);
        List<BoardsMainDto> boardSimpleDtoList = new ArrayList<>();
        boards.stream().forEach(i -> boardSimpleDtoList.add(new BoardsMainDto().toDto(i)));
        return boardSimpleDtoList;
    }

}
