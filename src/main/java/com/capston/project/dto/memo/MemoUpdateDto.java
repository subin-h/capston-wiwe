package com.capston.project.dto.memo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoUpdateDto {

    @NotBlank(message = "메모의 제목을 입력해주세요") //null 시 request error
    private String memoTitle;

    @NotBlank(message = "메모의 내용1을 입력해주세요.") //null 시 request error
    private String memoContent1;

    @NotBlank(message = "메모의 내용2를 입력해주세요.") //null 시 request error
    private String memoContent2;
}
