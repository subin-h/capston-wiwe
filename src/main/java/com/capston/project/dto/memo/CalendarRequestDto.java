package com.capston.project.dto.memo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarRequestDto {

    @NotBlank(message = "메모가 저장된 날짜를 입력해주세요.") //null 시 request error
    private String memoDate;
}
