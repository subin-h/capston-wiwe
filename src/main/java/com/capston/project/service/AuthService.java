package com.capston.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capston.project.config.jwt.TokenProvider;
import com.capston.project.dto.*;
import com.capston.project.entity.Authority;
import com.capston.project.entity.user.User;
import com.capston.project.exception.LoginFailureException;
import com.capston.project.exception.MemberNicknameAlreadyExistsException;
import com.capston.project.exception.MemberUsernameAlreadyExistsException;
import com.capston.project.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;


    @Transactional
    public User signup(SignUpRequestDto req) throws Exception{
        validateSignUpInfo(req);
        User user = createSignupFormOfUser(req);
        userRepository.save(user);
        return user;
    }


    @Transactional
    public TokenResponseDto signIn(LoginRequestDto req) {
        User user = userRepository.findByUsername(req.getUsername()).orElseThrow(() -> {
            return new LoginFailureException();
        });

        validatePassword(req, user);

        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = req.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);


        TokenResponseDto tokenResponseDto = new TokenResponseDto(tokenDto.getAccessToken());
        // 5. 토큰 발급
        return tokenResponseDto;
    }

    private void validateSignUpInfo(SignUpRequestDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername()))
            throw new MemberUsernameAlreadyExistsException(registerDto.getUsername());
        if (userRepository.existsByNickname(registerDto.getNickname()))
            throw new MemberNicknameAlreadyExistsException(registerDto.getNickname());
    }

    private void validatePassword(LoginRequestDto loginRequestDto, User user) {
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new LoginFailureException();
        }
    }
    private User createSignupFormOfUser(SignUpRequestDto req) {
        return User.builder()
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .name(req.getName())
                .nickname(req.getNickname())
                .authority(Authority.ROLE_USER)
                .build();
    }

}