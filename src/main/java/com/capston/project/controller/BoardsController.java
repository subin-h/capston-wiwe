package com.capston.project.controller;

import com.capston.project.dto.community.BoardsRequestDto;
import com.capston.project.response.Response;
import com.capston.project.service.BoardsService;
import io.swagger.annotations.ApiOperation;
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
}
