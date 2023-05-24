package com.capston.project.dto.checklist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistSum1RequestDto {

    @NotNull(message = "체크리스트1번의 값을 입력해주세요.")
    private Integer checklistSum1;
}
