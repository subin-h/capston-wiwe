package com.capston.project.controller;

import com.capston.project.dto.hobby.HobbyMainRequest;
import com.capston.project.dto.memo.MemoUpdateDto;
import com.capston.project.response.Response;
import com.capston.project.service.HobbyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hobby")
public class HobbyController {

    private final HobbyService hobbyService;

    @ApiOperation(value = "취미 검색", notes = "취미를 검색합니다.")
    @PostMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Response hobbySearch(@Valid @RequestBody HobbyMainRequest req) {
        return Response.success(hobbyService.hobbySearch(req));
    }

    @ApiOperation(value = "취미 단건 조회", notes = "취미를 단건 조회합니다")
    @GetMapping("/{hobbyId}")
    @ResponseStatus(HttpStatus.OK)
    public Response findHobby(@ApiParam(value = "취미 id", required = true) @PathVariable Long hobbyId) {
        return Response.success(hobbyService.findHobby(hobbyId));
    }


}
