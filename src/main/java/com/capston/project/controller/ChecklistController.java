package com.capston.project.controller;

import com.capston.project.dto.checklist.ChecklistSum1RequestDto;
import com.capston.project.dto.checklist.ChecklistSum2RequestDto;
import com.capston.project.dto.checklist.ChecklistSum3RequestDto;
import com.capston.project.response.Response;
import com.capston.project.service.ChecklistService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/checklist")
public class ChecklistController {
    private final ChecklistService checklistService;

    @ApiOperation(value = "체크리스트1 점수 입력", notes = "체크리스트1 점수를 입력합니다.")
    @PutMapping("/change1")
    @ResponseStatus(HttpStatus.OK)
    public Response change1(@Valid @RequestBody ChecklistSum1RequestDto req) {
        return Response.success(checklistService.updateChecklist1(req));
    }

    @ApiOperation(value = "체크리스트2 점수 입력", notes = "체크리스트2 점수를 입력합니다.")
    @PutMapping("/change2")
    @ResponseStatus(HttpStatus.OK)
    public Response change2(@Valid @RequestBody ChecklistSum2RequestDto req) {
        return Response.success(checklistService.updateChecklist2(req));
    }

    @ApiOperation(value = "체크리스트3 점수 입력", notes = "체크리스트3 점수를 입력합니다.")
    @PutMapping("/change3")
    @ResponseStatus(HttpStatus.OK)
    public Response change3(@Valid @RequestBody ChecklistSum3RequestDto req) {
        return Response.success(checklistService.updateChecklist3(req));
    }

    @ApiOperation(value = "체크리스트 단일 결과", notes = "1번 체크리스트의 단일 결과를 조회합니다.")
    @GetMapping("/result1")
    @ResponseStatus(HttpStatus.OK)
    public Response checklistResult1() {
        return Response.success(checklistService.resultChecklist1());
    }

    @ApiOperation(value = "체크리스트 단일 결과", notes = "2번 체크리스트의 단일 결과를 조회합니다.")
    @GetMapping("/result2")
    @ResponseStatus(HttpStatus.OK)
    public Response checklistResult2() {
        return Response.success(checklistService.resultChecklist2());
    }

    @ApiOperation(value = "체크리스트 단일 결과", notes = "3번 체크리스트의 단일 결과를 조회합니다.")
    @GetMapping("/result3")
    @ResponseStatus(HttpStatus.OK)
    public Response checklistResult3() {
        return Response.success(checklistService.resultChecklist3());
    }

    @ApiOperation(value = "전체 체크리스트 결과", notes = "전체 체크리스트 결과를 조회합니다.")
    @GetMapping("/allResult")
    @ResponseStatus(HttpStatus.OK)
    public Response checklistAllResult() {
        return Response.success(checklistService.getResult());
    }
}
