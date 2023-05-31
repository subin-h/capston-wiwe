package com.capston.project.dto.hobby;


import com.capston.project.entity.hobby.Hobby;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HobbySingleResponse {

    private Long hobbyId;
    private String hobbyTitle;

    private String hobbyAttribute1;
    private String hobbyAttribute2;
    private String hobbyAttribute3;

    private String hobbyContent;

    public static HobbySingleResponse toDto(Hobby hobby) {
        return new HobbySingleResponse(
                hobby.getHobbyId(),
                hobby.getHobbyTitle(),
                hobby.getHobbyAttribute1(),
                hobby.getHobbyAttribute2(),
                hobby.getHobbyAttribute3(),
                hobby.getHobbyContent());
    }

}
