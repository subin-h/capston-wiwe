package com.capston.project.dto.checklist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistSum3RequestDto {

    @NotNull(message = "체크리스트3번의 값을 입력해주세요.")
    private Integer checklistSum3;
}
