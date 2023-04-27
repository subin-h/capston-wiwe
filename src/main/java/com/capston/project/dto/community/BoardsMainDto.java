package com.capston.project.dto.community;

import com.capston.project.entity.community.Boards;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardsMainDto {
    private String title;
    private String nickname;

    public BoardsMainDto toDto(Boards boards) {
        return new BoardsMainDto(boards.getBoardsTitle(),boards.getUser().getNickname());
    }
}
