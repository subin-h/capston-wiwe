package com.capston.project.controller;

import com.capston.project.dto.community.BoardsRequestDto;
import com.capston.project.response.Response;
import com.capston.project.service.BoardsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/boards")
public class BoardsController {
    private final BoardsService boardsService;

    @ApiOperation(value = "게시글 생성", notes = "게시글 작성")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody BoardsRequestDto req) throws Exception{
        return Response.success(boardsService.createBoard(req));
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정합니다.")
    @PutMapping("/boards/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response editBoard(@PathVariable Long id, @Valid @RequestBody BoardsRequestDto req) {
        return Response.success(boardsService.updateBoard(id, req));
    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제합니다.")
    @DeleteMapping("/boards/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteBoard(@PathVariable Long id) {
        boardsService.deleteBoard(id);
        return Response.success(null);
    }
}
