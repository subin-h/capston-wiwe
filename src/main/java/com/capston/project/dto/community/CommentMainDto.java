package com.capston.project.dto.community;

import com.capston.project.entity.community.Boards;
import com.capston.project.entity.community.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentMainDto {

    private Long commentId;
    private String commentContent;
    private Long boardsId;

    public CommentMainDto toDto(Comment comment ) {
        return new CommentMainDto(comment.getCommentId(), comment.getCommentContent(), comment.getBoards().getBoardsId());
    }

}
