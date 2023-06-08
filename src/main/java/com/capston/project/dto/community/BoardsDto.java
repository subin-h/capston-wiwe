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
public class BoardsDto {
    private Long boardsId;
    private String boardsWriter;
    private String boardsTitle;
    private String boardsContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime boardsDate;

    public static BoardsDto toDto(Boards boards, String boardsWriter) {
        return new BoardsDto(
                boards.getBoardsId(),
                boardsWriter,
                boards.getBoardsTitle(),
                boards.getBoardsContent(),
                boards.getCreatedAt()
        );
    }
}
