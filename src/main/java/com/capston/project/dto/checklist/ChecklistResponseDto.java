package com.capston.project.dto.checklist;

import com.capston.project.dto.community.BoardsDto;
import com.capston.project.entity.checklist.ResultInfo;
import com.capston.project.entity.community.Boards;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistResponseDto {

    private String result;

    public static ChecklistResponseDto toDto(ResultInfo resultInfo) {
        return new ChecklistResponseDto(
                resultInfo.getResult()
        );
    }
}
