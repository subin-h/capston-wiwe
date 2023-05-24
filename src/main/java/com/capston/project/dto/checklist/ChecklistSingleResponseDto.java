package com.capston.project.dto.checklist;

import com.capston.project.dto.community.CommentMainDto;
import com.capston.project.dto.user.UserDto;
import com.capston.project.entity.community.Comment;
import com.capston.project.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistSingleResponseDto {

    private String checklistResult;

    public static ChecklistSingleResponseDto toDto(String checklistResult) {
        return new ChecklistSingleResponseDto(
                checklistResult);
    }
}
