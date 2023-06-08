package com.capston.project.dto.community;

import com.capston.project.entity.community.Boards;
import com.capston.project.entity.community.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentMainDto {

    private Long commentId;
    private String commentContent;
    private Long boardsId;
    private String commentWriter;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime commentCreatedAt;

    public CommentMainDto toDto(Comment comment ) {
        return new CommentMainDto(
                comment.getCommentId(),
                comment.getCommentContent(),
                comment.getBoards().getBoardsId(),
                comment.getUser().getNickname(),
                comment.getCreatedAt());
    }

}
