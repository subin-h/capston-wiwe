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
@Table(name = "boards")
public class Boards extends Auditor {

    @Id
    @Column(name = "boards_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardsId;

    @Column(name = "boards_title", nullable = false)
    private String boardsTitle;
    @Lob //MySql에 data를 대용량 저장 가능
    @Column(name = "boards_content", nullable = false)
    private String boardsContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Boards(String boardsTitle, String boardsContent, User user) {
        this.boardsTitle = boardsTitle;
        this.boardsContent = boardsContent;
        this.user = user;
    }
}
