package com.capston.project.controller;

import com.capston.project.dto.community.CommentCreateDto;
import com.capston.project.dto.community.CommentReadBoardId;
import com.capston.project.response.Response;
import com.capston.project.service.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @ApiOperation(value = "댓글 작성", notes = "댓글을 작성 합니다.")
    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody CommentCreateDto req) {
        return Response.success(commentService.createComment(req));
    }

    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제 합니다.")
    @DeleteMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public Response delete(@ApiParam(value = "댓글 id", required = true) @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return Response.success(null);
    }

    @ApiOperation(value = "댓글 목록 조회", notes = "댓글을 조회 합니다.")
    @GetMapping("/comments")
    @ResponseStatus(HttpStatus.OK)
    public Response findAll(@Valid CommentReadBoardId commentReadBoardId) {
        return Response.success(commentService.findCommentAll(commentReadBoardId));
    }
}
