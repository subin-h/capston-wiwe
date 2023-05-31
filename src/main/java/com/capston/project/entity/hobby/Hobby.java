package com.capston.project.entity.hobby;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "hobby")
public class Hobby {

    @Id
    @Column(name = "hobby_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hobbyId;

    @Column(name = "hobby_title", nullable = false)
    private String hobbyTitle;

    @Column(name = "hobby_attribute1", nullable = false)
    private String hobbyAttribute1;

    @Column(name = "hobby_attribute2", nullable = false)
    private String hobbyAttribute2;

    @Column(name = "hobby_attribute3", nullable = false)
    private String hobbyAttribute3;

    @Column(name = "hobby_content", nullable = false)
    private String hobbyContent;

    public Hobby(String hobbyTitle, String hobbyAttribute1, String hobbyAttribute2, String hobbyAttribute3, String hobbyContent) {
        this.hobbyTitle = hobbyTitle;
        this.hobbyAttribute1 = hobbyAttribute1;
        this.hobbyAttribute2 = hobbyAttribute2;
        this.hobbyAttribute3 = hobbyAttribute3;
        this.hobbyContent = hobbyContent;
    }

}
