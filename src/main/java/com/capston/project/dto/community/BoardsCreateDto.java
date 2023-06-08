package com.capston.project.dto.community;

import com.capston.project.entity.community.Boards;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardsCreateDto {

    private Long boardsId;
    private String boardsTitle;
    @Lob
    private String boardsContent;

    public static BoardsCreateDto toDto(Boards boards) {
        return new BoardsCreateDto(boards.getBoardsId(), boards.getBoardsTitle(), boards.getBoardsContent());
    }


}
