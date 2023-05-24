package com.capston.project.entity.checklist;

import com.capston.project.entity.community.Boards;
import com.capston.project.entity.user.User;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "result_info")
public class ResultInfo {

    @Id
    @Column(name = "result_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;
    @Lob
    @Column(name = "result", nullable = false)
    private String result;

    @Lob
    @Column(name = "result2", nullable = false)
    private String result2;

    @Lob
    @Column(name = "result3", nullable = false)
    private String result3;

    public ResultInfo(String result, String result2, String result3) {
        this.result = result;
        this.result2 = result2;
        this.result3 = result3;
    }
}
