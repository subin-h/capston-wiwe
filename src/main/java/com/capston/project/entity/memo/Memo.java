package com.capston.project.entity.memo;

import com.capston.project.entity.Auditor;
import com.capston.project.entity.community.Boards;
import com.capston.project.entity.user.User;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "memo")
public class Memo extends Auditor {

    @Id
    @Column(name = "memo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memoId;

    @Column(name = "memo_title", nullable = false)
    private String memoTitle;

    @Column(name = "memo_content1",nullable = false)
    private String memoContent1;

    @Column(name = "memo_content2",nullable = false)
    private String memoContent2;

    @Column(name = "memo_date")
    private LocalDateTime memoDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Memo(String memoTitle, String memoContent1, String memoContent2, LocalDateTime memoDate, User user) {
      this.memoTitle = memoTitle;
      this.memoContent1 = memoContent1;
      this.memoContent2 = memoContent2;
      this.memoDate = memoDate;
      this.user = user;
    }
}
