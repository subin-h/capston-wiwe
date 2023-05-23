package com.capston.project.controller;


import com.capston.project.dto.community.BoardsRequestDto;
import com.capston.project.dto.memo.MemoRequestDto;
import com.capston.project.response.Response;
import com.capston.project.service.MemoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/memo")
public class MemoController {

    private final MemoService memoService;

    @ApiOperation(value = "메모 생성", notes = "메모 작성")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Response memoCreate(@Valid @RequestBody MemoRequestDto req) throws Exception{
        return Response.success(memoService.createMemo(req));
    }
}
