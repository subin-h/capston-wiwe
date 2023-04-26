package com.capston.project.controller;


import com.capston.project.dto.SignUpRequestDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import com.capston.project.dto.LoginRequestDto;

import com.capston.project.response.Response;
import com.capston.project.service.AuthService;

import javax.validation.Valid;

import static com.capston.project.response.Response.success;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @ApiOperation(value = "회원가입", notes = "회원가입 진행")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public Response register(@Valid @RequestBody SignUpRequestDto registerDto) throws Exception{
        authService.signup(registerDto);
        return success(null);
    }

    @ApiOperation(value = "로그인", notes = "로그인을 한다.")
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public Response signIn(@Valid @RequestBody LoginRequestDto req) {
        return success(authService.signIn(req));
    }


}