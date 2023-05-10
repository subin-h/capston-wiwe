package com.capston.project.entity.user;

import com.capston.project.entity.Auditor;
import com.capston.project.entity.Authority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String nickname;
    @Column(name = "checklist_sum1", nullable = true)
    private Integer checklistSum1;
    @Column(name = "checklist_sum2", nullable = true)
    private Integer checklistSum2;
    @Column(name = "checklist_sum3", nullable = true)
    private Integer checklistSum3;


    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public User(String username, String password, String nickname, String name, Authority authority) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.authority = authority;
    }


}