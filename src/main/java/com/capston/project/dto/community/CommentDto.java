package com.capston.project.dto.community;

import com.capston.project.entity.community.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long commentId;
    private String commentContent;
    private String commentWriter;



    public static CommentDto toDto(Comment comment) {
        return new CommentDto(
                comment.getCommentId(),
                comment.getCommentContent(),
                comment.getUser().getNickname()
        );
    }

}
