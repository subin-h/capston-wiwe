package com.capston.project.dto.checklist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistSum2RequestDto {

    @NotNull(message = "체크리스트2번의 값을 입력해주세요.") //null 시 request error
    private Integer checklistSum2;
}
