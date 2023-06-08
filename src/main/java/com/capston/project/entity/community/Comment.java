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
/*Sprint boot - 카멜 표기법, Mysql = Snake 표기법 권장으로 인하여
테이블의 이름은 Snake 표기법으로 변경하여 저장*/
@Table(name = "comment")
public class Comment extends Auditor {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "comment_content", nullable = false)
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
