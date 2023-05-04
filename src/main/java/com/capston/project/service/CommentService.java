package com.capston.project.service;

import com.capston.project.config.SecurityUtil;
import com.capston.project.dto.community.*;
import com.capston.project.entity.community.Boards;
import com.capston.project.entity.community.Comment;
import com.capston.project.entity.user.User;
import com.capston.project.exception.BoardNotFoundException;
import com.capston.project.exception.CommentNotFoundException;
import com.capston.project.exception.MemberNotFoundException;
import com.capston.project.repository.BoardsRepository;
import com.capston.project.repository.CommentRepository;
import com.capston.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardsRepository boardsRepository;

    public CommentService(final CommentRepository commentRepository, final BoardsRepository boardRepository, final UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.boardsRepository = boardRepository;
        this.userRepository = userRepository;

    }

    //댓글 전체 조회
    @Transactional(readOnly = true)
    public List<CommentDto> findCommentAll(CommentReadBoardId req) {
        List<Comment> commentList = commentRepository.findByBoardsBoardsId(req.getBoardsId());
        List<CommentDto> commentDtoList = new ArrayList<>();
        commentList.stream().forEach(i -> commentDtoList.add(new CommentDto().toDto(i)));
        return commentDtoList;
    }

    //댓글 작성
    @Transactional
    public CommentDto createComment (CommentCreateDto req) {
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        Boards boards = boardsRepository.findById(req.getBoardsId()).orElseThrow(BoardNotFoundException::new);
        Comment comment = new Comment(req.getCommentContent(),user, boards);
        commentRepository.save(comment);
        return new CommentDto().toDto(comment);
    }
    //댓글 삭제
    @Transactional
    public void deleteComment(Long id) {
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        Boards boards = boardsRepository.findById(comment.getBoards().getBoardsId()).orElseThrow(BoardNotFoundException::new);

        if(user != comment.getUser()){
            throw new MemberNotFoundException();
        }
        commentRepository.delete(comment);
    }

    //댓글 수정
}
