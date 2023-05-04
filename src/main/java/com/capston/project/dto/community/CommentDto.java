package com.capston.project.dto.community;

import com.capston.project.entity.community.Boards;
import com.capston.project.entity.community.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Long commentId;
    private String commentContent;
    private String commentWriter;
    private LocalDateTime commentCreatedAt;

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(
                comment.getCommentId(),
                comment.getCommentContent(),
                comment.getUser().getNickname(),
                comment.getCreatedAt()
        );
    }

}
