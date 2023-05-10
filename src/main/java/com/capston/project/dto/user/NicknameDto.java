package com.capston.project.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "닉네임 추가 또는 수정")
public class NicknameDto {
    @NotBlank(message = "닉네임을 입력해주세요")
    private String nickname;
}
