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
/*Sprint boot - 카멜 표기법, Mysql = Snake 표기법 권장으로 인하여
테이블의 이름은 Snake 표기법으로 변경하여 저장*/
@Table(name = "result_info")
public class ResultInfo {

    @Id
    @Column(name = "result_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;
    @Lob //MySql에 data를 대용량 저장 가능
    @Column(name = "result", nullable = false)
    private String result;

    @Lob //MySql에 data를 대용량 저장 가능
    @Column(name = "result2", nullable = false)
    private String result2;

    @Lob //MySql에 data를 대용량 저장 가능
    @Column(name = "result3", nullable = false)
    private String result3;

    public ResultInfo(String result, String result2, String result3) {
        this.result = result;
        this.result2 = result2;
        this.result3 = result3;
    }
}
