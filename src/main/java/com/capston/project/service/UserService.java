package com.capston.project.service;

import com.capston.project.config.SecurityUtil;
import com.capston.project.dto.SignUpRequestDto;
import com.capston.project.dto.user.DeleteRequestDto;
import com.capston.project.dto.user.NicknameDto;
import com.capston.project.dto.user.PasswordDto;
import com.capston.project.dto.user.UserDto;
import com.capston.project.entity.user.User;
import com.capston.project.exception.MemberNicknameAlreadyExistsException;
import com.capston.project.exception.MemberUsernameAlreadyExistsException;
import com.capston.project.exception.PasswordMismatchException;
import com.capston.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //내 정보 조회
    @Transactional(readOnly = true)
    public UserDto getMyInfo() {
        return UserDto.from(SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null));
    }

    //닉네임 변경, 닉네임 존재시 변경 불가
    @Transactional
    public UserDto updateNickname(NicknameDto nicknameDto) {
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        if (userRepository.existsByNickname(nicknameDto.getNickname()))
            throw new MemberNicknameAlreadyExistsException(nicknameDto.getNickname());
        user.setNickname(nicknameDto.getNickname());
        return UserDto.from(userRepository.save(user));
    }

    //회원 탈퇴
    @Transactional
    public void deleteUser(DeleteRequestDto deleteRequestDto) {
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        if (passwordEncoder.matches(deleteRequestDto.getPassword(), user.getPassword())) {
            userRepository.delete(user);
        }
        else throw new PasswordMismatchException();
    }

    //비밀번호 일치 확인 후 변경
    @Transactional
    public UserDto updatePassword(PasswordDto passwordDto) {
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUsername).orElse(null);
        if (passwordEncoder.matches(passwordDto.getRecentPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
        } else throw new PasswordMismatchException();

        return UserDto.from(userRepository.save(user));
    }

}
