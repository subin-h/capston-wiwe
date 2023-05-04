package com.capston.project.entity.community;

import com.capston.project.entity.Auditor;
import com.capston.project.entity.user.User;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment extends Auditor {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long commentId;

    @Column(nullable = false,name = "comment_content")
    private String commentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boards_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Boards boards;

    public Comment(String content, User user, Boards board) {
        this.commentContent = content;
        this.user = user;
        this.boards = board;
    }

}
