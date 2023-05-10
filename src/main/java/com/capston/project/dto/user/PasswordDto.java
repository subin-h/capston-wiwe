package com.capston.project.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "비밀번호 변경")
public class PasswordDto {
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String recentPassword;
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String newPassword;
}
