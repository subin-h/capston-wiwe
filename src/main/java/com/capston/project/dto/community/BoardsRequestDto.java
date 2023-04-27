package com.capston.project.dto.community;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardsRequestDto {

    @NotBlank(message = "게시글 제목을 입력해주세요.")
    private String title;
    @NotBlank(message = "게시글 본문을 입력해주세요.")
    private String content;

}