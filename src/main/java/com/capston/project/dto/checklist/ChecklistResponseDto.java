package com.capston.project.dto.checklist;

import com.capston.project.dto.community.BoardsDto;
import com.capston.project.entity.checklist.ResultInfo;
import com.capston.project.entity.community.Boards;
import com.capston.project.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistResponseDto {

    private String userNickname;
    private String result;
    private String result2;
    private String result3;

    public static ChecklistResponseDto toDto(User user, ResultInfo resultInfo) {
        return new ChecklistResponseDto(
                user.getNickname(),
                resultInfo.getResult(),
                resultInfo.getResult2(),
                resultInfo.getResult3()
        );
    }
}
