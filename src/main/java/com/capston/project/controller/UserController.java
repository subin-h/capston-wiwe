package com.capston.project.controller;

import com.capston.project.dto.community.CommentCreateDto;
import com.capston.project.dto.user.DeleteRequestDto;
import com.capston.project.dto.user.NicknameDto;
import com.capston.project.dto.user.PasswordDto;
import com.capston.project.response.Response;
import com.capston.project.service.BoardsService;
import com.capston.project.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원 정보", notes = "회원정보를 조회합니다.")
    @GetMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public Response userInfo() {
        return Response.success(userService.getMyInfo());
    }

    @ApiOperation(value = "닉네임 변경", notes = "닉네임을 변경합니다.")
    @PutMapping("/changeNickname")
    @ResponseStatus(HttpStatus.OK)
    public Response setNickname(@Valid @RequestBody NicknameDto req) {
        return Response.success(userService.updateNickname(req));
    }

    @ApiOperation(value = "비밀번호 변경", notes = "비밀번호을 변경합니다.")
    @PutMapping("/changePassword")
    @ResponseStatus(HttpStatus.OK)
    public Response changePassword(@Valid @RequestBody PasswordDto req) {
        return Response.success(userService.updatePassword(req));
    }

    @ApiOperation(value = "회원 탈퇴", notes = "회원정보를 삭제합니다.")
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteUser(@Valid @RequestBody DeleteRequestDto req) {
        userService.deleteUser(req);
        return Response.success(null);
    }

    @ApiOperation(value = "내가 쓴 글 조회", notes = "사용자가 작성한 게시글을 조회합니다.")
    @GetMapping("/myBoards")
    @ResponseStatus(HttpStatus.OK)
    public Response myPageBoards() {
        return Response.success(userService.myBoardsPage());
    }

    @ApiOperation(value = "내가 쓴 댓글 조회", notes = "사용자가 작성한 댓글을 조회합니다.")
    @GetMapping("/myComment")
    @ResponseStatus(HttpStatus.OK)
    public Response myPageComment() {
        return Response.success(userService.myCommentPage());
    }


}
