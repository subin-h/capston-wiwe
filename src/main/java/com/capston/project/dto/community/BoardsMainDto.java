package com.capston.project.dto.community;

import com.capston.project.entity.community.Boards;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardsMainDto {

    private Long boardsId;
    private String title;
    private String nickname;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime boardsDate;

    public BoardsMainDto toDto(Boards boards) {
        return new BoardsMainDto(boards.getBoardsId(), boards.getBoardsTitle(),boards.getUser().getNickname(), boards.getCreatedAt());
    }
}
