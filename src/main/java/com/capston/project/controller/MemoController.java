package com.capston.project.controller;


import com.capston.project.dto.community.BoardsRequestDto;
import com.capston.project.dto.memo.CalendarRequestDto;
import com.capston.project.dto.memo.MemoRequestDto;
import com.capston.project.dto.memo.MemoUpdateDto;
import com.capston.project.response.Response;
import com.capston.project.service.MemoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation(value = "메모 단건 조회", notes = "메모를 단건 조회합니다")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response findMemo(@ApiParam(value = "메모 id", required = true) @PathVariable Long id) {
        return Response.success(memoService.singleMemoFind(id));
    }

    @ApiOperation(value = "메모 수정", notes = "메모를 수정합니다.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response editMemo(@PathVariable Long id, @Valid @RequestBody MemoUpdateDto req) {
        return Response.success(memoService.updateMemo(id, req));
    }

    @ApiOperation(value = "메모 삭제", notes = "메모를 삭제합니다.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteMemo(@PathVariable Long id) {
        memoService.deleteMemo(id);
        return Response.success(null);
    }

    @ApiOperation(value = "내가 쓴 메모 목록 조회", notes = "내가 쓴 메모 목록을 조회합니다.")
    @GetMapping("/memo")
    @ResponseStatus(HttpStatus.OK)
    public Response findAllMemo() {
        return Response.success(memoService.findAllMemo());
    }

    @ApiOperation(value = "날짜별 내가 쓴 메모 목록 조회", notes = "날빠졀로 내가 쓴 메모 목록을 조회합니다.")
    @GetMapping("/memo/calendar")
    @ResponseStatus(HttpStatus.OK)
    public Response findDateMemo(@Valid @RequestBody CalendarRequestDto req) {
        return Response.success(memoService.findCalendarMemo(req));
    }
}
